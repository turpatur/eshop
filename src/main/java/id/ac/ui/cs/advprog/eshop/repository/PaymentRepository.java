package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PaymentRepository {

    public Payment save(Payment payment, Order order) {
        return null;
    }

    public void update(Payment payment, String status) {
        return;
    }

    public Payment findById(String paymentId) {
        return null;
    }

    public List<Payment> findAll() {
        return null;
    }

    public Order getOrder(String paymentId) {
        return null;
    }
}
