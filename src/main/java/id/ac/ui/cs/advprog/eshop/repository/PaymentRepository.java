package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PaymentRepository {
    private List<Payment> paymentData = new ArrayList<>();
    private Map<String, Order> paymentOrder = new HashMap<>();

    public Payment save(Payment payment, Order order) {
        paymentData.add(payment);
        if (payment.getStatus().equals(PaymentStatus.SUCCESS.getValue())) {
            order.setStatus(OrderStatus.SUCCESS.getValue());
        } else {
            order.setStatus(OrderStatus.FAILED.getValue());
        }
        paymentOrder.put(payment.getId(), order);
        return payment;
    }

    public void update(Payment payment, String status) {
        payment.setStatus(status);
        Order order = paymentOrder.get(payment.getId());
        if (status.equals(PaymentStatus.SUCCESS.getValue())) {
            order.setStatus(OrderStatus.SUCCESS.getValue());
        } else {
            order.setStatus(OrderStatus.FAILED.getValue());
        }
    }

    public Payment findById(String paymentId) {
        for (Payment payment : paymentData) {
            if (payment.getId().equals(paymentId)) {
                return payment;
            }
        }
            return null;
    }

    public List<Payment> findAll() {
        return new ArrayList<>(paymentData);
    }

    public Order getOrder(String paymentId) {
        return paymentOrder.get(paymentId);
    }
}
