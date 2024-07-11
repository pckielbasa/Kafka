package org.paymentservice.repository;

import org.paymentservice.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMongoRepository extends MongoRepository<Payment, String> {
}
