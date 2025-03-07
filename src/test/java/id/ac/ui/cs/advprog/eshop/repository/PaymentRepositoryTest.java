package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {
   PaymentRepository paymentRepository;
   List<Payment> payments;
   Order order = new Order(
            "13652556-012a-4c07-b546-54eb1396d79b",
            null,
            1708560000L,
            "Safira Sudrajat");

    @BeforeEach
    void setUp() {

        Map<String, String> paymentVoucher = new HashMap<>();
        paymentVoucher.put("voucherCode", "ESHOP1234ABC5678");
        Payment voucher = new Payment("random-id-1", PaymentMethod.VOUCHER_CODE.getValue(), paymentVoucher);
        payments.add(voucher);

        Map<String, String> paymentBankTransfer = new HashMap<>();
        paymentVoucher.put("bankName", "aBankName");
        paymentVoucher.put("referenceCode", "aRefCode");
        Payment bankTransfer = new Payment("random-id-2", PaymentMethod.BANK_TRANSFER.getValue(), paymentBankTransfer);
        payments.add(bankTransfer);
    }

    @Test
    void testSaveCreate(){
        Payment payment = payments.getFirst();
        Payment result = paymentRepository.save(payment, order);
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getStatus(), result.getStatus());
        assertEquals(payment.getMethod(), result.getMethod());
        assertEquals(payment.getPaymentData(), result.getPaymentData());
    }

    @Test
    void testUpdateStatus(){
        Payment payment = payments.getFirst();
        Payment result = paymentRepository.save(payment, order);
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), order.getStatus());

        paymentRepository.update(payment, PaymentStatus.REJECTED.getValue());
        Payment updatedPayment = paymentRepository.findById(payment.getId());
        Order updatedOrder = paymentRepository.getOrder(updatedPayment.getId());
        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), order.getStatus());

    }

    @Test
    void testUpdateInvalidStatus(){
        Payment payment = payments.getFirst();
        Payment result = paymentRepository.save(payment, order);
        assertThrows(IllegalArgumentException.class, () -> {
            paymentRepository.update(payment, "INVALID");
        });
    }

    @Test
    void testFindById(){
        for (Payment payment : payments) {
            paymentRepository.save(payment, order);
        }
        Payment targetPayment = payments.getFirst();

        Payment foundPayment = paymentRepository.findById(targetPayment.getId());
        assertEquals(targetPayment.getId(), foundPayment.getId());
        assertEquals(targetPayment.getStatus(), foundPayment.getStatus());
        assertEquals(targetPayment.getMethod(), foundPayment.getMethod());
        assertEquals(targetPayment.getPaymentData(), foundPayment.getPaymentData());
    }

    @Test
    void tesFindByIdNotFound(){
        for (Payment payment : payments) {
            paymentRepository.save(payment, order);
        }
        Payment targetSearchPayment = paymentRepository.findById("anId");
        assertNull(targetSearchPayment);
    }

    @Test
    void testFindAll(){
        for (Payment payment : payments) {
            paymentRepository.save(payment,order);
        }
        List<Payment> paymentList = paymentRepository.findAll();
        assertEquals(payments.size(), paymentList.size());
    }

    @Test
    void testFindAllIfEmpty(){
        List<Payment> paymentList = paymentRepository.findAll();
        assertEquals(0, paymentList.size());
    }

    @Test
    void testGetOrder(){
        Payment payment = payments.getFirst();
        paymentRepository.save(order, payment);

        Payment foundpayment = paymentRepository.findById(payment.getId());
        Order foundOrder = paymentRepository.getOrder(foundPayment.getId());
        assertEquals(order.getId(), foundOrder.getId());
        assertEquals(order.getStatus(), foundOrder.getStatus());
        assertEquals(order.getAuthor(), foundOrder.getAuthor());
        assertEquals(order.getOrderTime(), foundOrder.getOrderTime());
        assertEquals(order.getProducts(), foundOrder.getProducts());
    }

    @Test
    void testGetOrderIfNotFound(){
        Payment payment = payments.getFirst();

        Payment foundpayment = paymentRepository.findById(payment.getId());
        Order foundOrder = paymentRepository.getOrder(foundPayment.getId());
        assertNull(foundOrder);
    }
}
