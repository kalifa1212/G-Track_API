package com.profondeur.solugaz.Model;

import com.profondeur.solugaz.Model.Enum.PaymentMethode;
import com.profondeur.solugaz.Model.Enum.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "payment") @Entity
@EqualsAndHashCode(callSuper=true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends AbstractEntity{

    private String Reference;
    private PaymentStatus paymentStatus;
    private PaymentMethode paymentMethode;
    private double montant;
    private Integer idCommande;


}
