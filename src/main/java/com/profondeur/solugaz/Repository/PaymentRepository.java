package com.profondeur.solugaz.Repository;

import com.profondeur.solugaz.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
