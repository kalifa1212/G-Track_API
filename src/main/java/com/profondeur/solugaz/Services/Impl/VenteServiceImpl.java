package com.profondeur.solugaz.Services.Impl;

import com.profondeur.solugaz.Dto.MouvementDto;
import com.profondeur.solugaz.Dto.StockDto;
import com.profondeur.solugaz.Dto.VenteDto;
import com.profondeur.solugaz.Exceptions.ErrorCodes;
import com.profondeur.solugaz.Exceptions.InvalidEntityException;
import com.profondeur.solugaz.Model.Commande;
import com.profondeur.solugaz.Model.Enum.PaymentMethode;
import com.profondeur.solugaz.Model.Enum.PaymentStatus;
import com.profondeur.solugaz.Model.Enum.TypeGaz;
import com.profondeur.solugaz.Model.Enum.TypeMouvement;
import com.profondeur.solugaz.Model.Gaz;
import com.profondeur.solugaz.Model.LigneCommande;
import com.profondeur.solugaz.Model.Payment;
import com.profondeur.solugaz.Repository.*;
import com.profondeur.solugaz.Services.MouvementService;
import com.profondeur.solugaz.Services.StockService;
import com.profondeur.solugaz.Services.VenteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class VenteServiceImpl implements VenteService {

    @Autowired
    private VenteRepository venteRepository;
    @Autowired
    private MouvementService mouvementService;
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private StockService stockService;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    public VenteServiceImpl(
            VenteRepository venteRepository,
            PaymentRepository paymentRepository,
            MouvementService mouvementService,
            StockService stockService,
            StockRepository stockRepository,
            LigneCommandeRepository ligneCommandeRepository
    ) {
        this.venteRepository=venteRepository;
        this.mouvementService=mouvementService;
        this.stockService=stockService;
        this.stockRepository=stockRepository;
        this.ligneCommandeRepository=ligneCommandeRepository;
        this.paymentRepository=paymentRepository;
    }

    @Override
    public VenteDto save(VenteDto dto) {
        List<String> errors = new ArrayList<>();
        if (dto.getQuantite()==0){
            errors.add("Entrer la quantité");
        }
        if (dto.getGaz()==null){
            errors.add("Entrer le gaz");
        }
        if(!errors.isEmpty()) {
            log.error("Vente non valide");
            throw new InvalidEntityException("Vente non valide ", ErrorCodes.VENTE_NOT_VALID,errors);
        }
        //TODO calcule du prix
        dto.setPrix(dto.getPrixUnitaire()*dto.getQuantite());
        //TODO recherche des stock lier a la vente  et modification du stock
        List<StockDto> listStock = stockRepository.findByGazIdAndDistributeurId(dto.getGaz().getId(),dto.getDistributeur().getId())
                .stream().map(StockDto::fromEntity).collect(Collectors.toList());
        if (listStock.isEmpty()){
            throw new InvalidEntityException("Aucune stock pour ce distributeur ", ErrorCodes.STOCK_NOT_FOUND,errors);
        }
        StockDto stockDto=listStock.get(0);
        if (stockDto.getQuantite()<dto.getQuantite()){
            throw new InvalidEntityException("Quantité en stock insufisante", ErrorCodes.STOCK_NOT_EXIST,errors);
        }
        stockDto.setQuantite(stockDto.getQuantite()-dto.getQuantite());
        //TODO creation d'un mouvement de type sorti
        MouvementDto mouvementDto= new MouvementDto();
        mouvementDto.setDate(new Date());
        mouvementDto.setTypeMouvement(TypeMouvement.Sortie);
        mouvementDto.setQuantite(dto.getQuantite());
        mouvementDto.setStock(stockDto);
        //TODO Payement
        //TODO Enregistremnet des entités
        stockService.save(stockDto);
        mouvementService.save(mouvementDto);
        return VenteDto.fromEntity(venteRepository.save(VenteDto.toEntity(dto)));
    }

    @Override
    public void commandePayment(Integer idCommande) {
        Optional<Commande> commande= commandeRepository.findById(idCommande);
        if (commande.isEmpty()){
            throw new InvalidEntityException("Commande introuvable");
        }
        Payment payment=new Payment();
        Gaz[] listeGaz = new Gaz[10];
        Integer[] idDistributeur = new Integer[10];
        Integer[] quantite = new Integer[10];
        double total_payment=0;
        int i=0;
        commande.get().setLigneCommande(ligneCommandeRepository.findLigneCommandeByCommandeId(commande.get().getId()));
        //TODO calcule de la somme a payer
        for (LigneCommande ligneCommande: commande.get().getLigneCommande()){
            log.error("i befoor {}",i);
            total_payment=+ligneCommande.getQuantite()*ligneCommande.getPrix_unitaire();
            idDistributeur[i]=ligneCommande.getDistributeur().getId();
            listeGaz[i]=ligneCommande.getGaz();
            quantite[i]= ligneCommande.getQuantite();
            ++i;
            log.info("i after= {} idDistributeur={} total ={} listeGaz={} quantite={}",i,idDistributeur,total_payment,listeGaz,quantite);

        }
        //TODO Mouvement de Stock
        for(int j=0; j<i; j++)
        {
            log.info("Mouvement de stock j= {}",j);
            List<StockDto> listStock = stockRepository.findByGazIdAndDistributeurId(listeGaz[j].getId(),idDistributeur[j])
                    .stream().map(StockDto::fromEntity).collect(Collectors.toList());
            if (listStock.isEmpty()){
                throw new InvalidEntityException("Aucune stock disponible pour ce distributeur ", ErrorCodes.STOCK_NOT_FOUND);
            }
            StockDto stockDto=listStock.get(0);
            if (stockDto.getQuantite()<quantite[j]){
                throw new InvalidEntityException("Quantité en stock insufisante our un element de la commande", ErrorCodes.STOCK_NOT_EXIST);
            }
            stockDto.setQuantite(stockDto.getQuantite()-quantite[j]);
            stockService.save(stockDto);
        }

        payment.setIdCommande(idCommande);
        payment.setPaymentMethode(PaymentMethode.ESPECE);
        payment.setPaymentStatus(PaymentStatus.VALIDE);
        payment.setMontant(total_payment);
        paymentRepository.save(payment);

    }

    @Override
    public VenteDto findById(Integer id) {
        return VenteDto.fromEntity(venteRepository.findById(id).orElseThrow());
    }

    @Override
    public List<VenteDto> findVenteByGaz(Integer id) {
        return venteRepository.findVenteByGazId(id).stream().map(VenteDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<VenteDto> findVenteByDistributeur(Integer id) {
        return venteRepository.findVenteByDistributeurId(id).stream().map(VenteDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<VenteDto> findVenteByLocalisationId(Integer id) {
        return venteRepository.findVenteByLocalisationId(id).stream().map(VenteDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<VenteDto> findVentesByLocalisationDetail(Integer idLocalisation, TypeGaz typeGaz, String fabricant) {
        return venteRepository.findVentesByLocalisation(idLocalisation, typeGaz, fabricant).stream().map(VenteDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<VenteDto> findVentesBydistributeurDetails(Integer idDistributeur, TypeGaz typeGaz, String fabricant) {
        return venteRepository.findVentesBydistributeur(idDistributeur, typeGaz, fabricant).stream().map(VenteDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public Page<VenteDto> findAll(Pageable page) {
        return venteRepository.findAll(page).map(VenteDto::fromEntity);
    }

    @Override
    public void delete(Integer id) {
        venteRepository.delete(venteRepository.findById(id).orElseThrow());
    }
}
