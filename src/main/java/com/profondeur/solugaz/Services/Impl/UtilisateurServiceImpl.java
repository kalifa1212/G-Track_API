package com.profondeur.solugaz.Services.Impl;

import com.profondeur.solugaz.Dto.RoleDto;
import com.profondeur.solugaz.Dto.UtilisateurDto;
import com.profondeur.solugaz.Exceptions.EntityNotFoundException;
import com.profondeur.solugaz.Exceptions.ErrorCodes;
import com.profondeur.solugaz.Exceptions.InvalidEntityException;
import com.profondeur.solugaz.Model.Localisation;
import com.profondeur.solugaz.Model.Role;
import com.profondeur.solugaz.Model.Utilisateur;
import com.profondeur.solugaz.Repository.LocalisationRepository;
import com.profondeur.solugaz.Repository.RoleRepository;
import com.profondeur.solugaz.Repository.UtilisateurRepository;
import com.profondeur.solugaz.Services.UtilisateurService;
import com.profondeur.solugaz.Validation.UtilisateurValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private LocalisationRepository localisationRepository;
    @Autowired
    private RoleRepository roleRepository;
    // Variable Statique
    String Type_User="USER";
    @Autowired
    public UtilisateurServiceImpl(
            UtilisateurRepository utilisateurRepository,
            RoleRepository roleRepository,
            LocalisationRepository localisationRepository
    ) {
        this.utilisateurRepository=utilisateurRepository;
        this.roleRepository=roleRepository;
        this.localisationRepository=localisationRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto, boolean update) {
        // TODO Enregistrer un utilisateur
        Role role=new Role();
        role=roleRepository.findByName("ROLE_USER");
        if(update){
            Optional<Utilisateur> util= utilisateurRepository.findById(dto.getId());
            UtilisateurDto user= UtilisateurDto.fromEntity(util.get());
            dto.setRoles(user.getRoles());
            //log.error("enregistrement {} ",dto);
            return UtilisateurDto.fromEntity(utilisateurRepository.save(UtilisateurDto.toEntity(dto)));
        }
        List<String> errors = UtilisateurValidation.validate(dto);

        if(!errors.isEmpty()) {
            log.error("L'utilisateur est non valide {}",dto);
            throw new InvalidEntityException("L'utilisateur n'est pas valide ", ErrorCodes.UTILISATEUR_NOT_VALID,errors);
        }
        Optional<Utilisateur> util = utilisateurRepository.findUtilisateurByEmail(dto.getEmail());
        if(!util.isEmpty()) {
            throw new InvalidEntityException("L'utilisateur Existe deja ", ErrorCodes.UTILISATEUR_ALREADY_EXIST,errors);
        }
        Optional<Localisation> local=localisationRepository.findById(dto.getLocalisation().getId());
        if(local.isEmpty()){
            throw new InvalidEntityException("Localisation n'existe pas ", ErrorCodes.LOCALISATION_NOT_FOUND,errors);
        }
        String passwd=dto.getMotDePasse();
        dto.setMotDePasse(passwd);
        dto.setRoles(Arrays.asList(RoleDto.fromEntity(role)));
        dto.getNom().toLowerCase();
        dto.getPrenom().toLowerCase();
        return UtilisateurDto.fromEntity(utilisateurRepository.save(UtilisateurDto.toEntity(dto)));
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        // TODO Auto-generated method stub
        if(id==null) {
            log.warn("l'id de la utilisateur est null");
            return null;
        }
        Optional<Utilisateur> utilisateur= utilisateurRepository.findById(id);

        return Optional.of(UtilisateurDto.fromEntity(utilisateur.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune utilisateur avec l'id ="+id+"n'a ete trouver dans la BD",
                        ErrorCodes.UTILISATEUR_NOT_FOUND)
        );
    }

    @Override
    public UtilisateurDto findByEmail(String email) {
        return utilisateurRepository.findUtilisateurByEmail(email)
                .map(UtilisateurDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Aucun utiliseur trouver pour : "+email+"dans " +
                        "la base de donnée", ErrorCodes.UTILISATEUR_NOT_FOUND)
                );

    }

    @Override
    public Optional<Utilisateur> UniqueMail(String email) {
         return utilisateurRepository.findUtilisateurByEmail(email);
    }

    @Override
    public Page<UtilisateurDto> findAll(Pageable page) {
        // TODO Auto-generated method stub
        return utilisateurRepository.findAll(page).map(UtilisateurDto::fromEntity);
    }

    @Override
    public Boolean GranteRole(String Email, String roleName) {
        //TODO passage de role
        roleName.toLowerCase();
        Boolean reussit=false;
        Optional<Utilisateur> util = utilisateurRepository.findUtilisateurByEmail(Email);
        Role roleAdmin =roleRepository.findByName("ROLE_ADMIN");
        Role roleUser=roleRepository.findByName("ROLE_USER");
        Role roleRoot =roleRepository.findByName("ROLE_ROOT");
        Role roleStaf=roleRepository.findByName("ROLE_STAFF");
        if(util.isEmpty()) {
            throw new InvalidEntityException("L'utilisateur n'existe", ErrorCodes.UTILISATEUR_NOT_FOUND);
        }
        UtilisateurDto test= findByEmail(Email);
        Utilisateur user= UtilisateurDto.toEntity(test);
        switch (roleName) {
            case "root":
                user.setRoles(Arrays.asList(RoleDto.toEntity(RoleDto.fromEntity(roleRoot))));
                utilisateurRepository.save(user);
                reussit=true;
                break;
            case "admin":
                user.setRoles(Arrays.asList(RoleDto.toEntity(RoleDto.fromEntity(roleAdmin))));
                utilisateurRepository.save(user);
                reussit=true;
                break;
            case "staff":
                user.setRoles(Arrays.asList(RoleDto.toEntity(RoleDto.fromEntity(roleStaf))));
                utilisateurRepository.save(user);
                reussit=true;
                break;
            case "user":
                user.setRoles(Arrays.asList(RoleDto.toEntity(RoleDto.fromEntity(roleUser))));
                utilisateurRepository.save(user);
                reussit=true;
                break;
            default: reussit=false;

        }
        return reussit;
    }



    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        if(id==null) {
            throw new EntityNotFoundException("L'Id est null");
        }

        utilisateurRepository.deleteById(id);
    }
}
