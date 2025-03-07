package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private List<Order> orders;
    private List<Payment> payments;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductName("product1");
        product1.setProductId("random-id-1");
        product1.setProductQuantity(1);

        Product product2 = new Product();
        product2.setProductName("product2");
        product2.setProductId("random-id-2");
        product2.setProductQuantity(2);

        products.add(product1);
        products.add(product2);

        orders = new ArrayList<>();
        orders.add(new Order("order-id-1", products, 100000L, "yanto"));
        orders.add(new Order("order-id-2", products, 100001L, "yanti"));

        payments = new ArrayList<>();
        Map<String, String> mapVoucher = new HashMap<>();
        mapVoucher.put("voucherCode", "ESHOP1234ABC5678");
        payments.add(new Payment("payment-id-1", PaymentMethod.VOUCHER_CODE.getValue(), mapVoucher));

        Map<String, String> mapBankTransfer = new HashMap<>();
        mapBankTransfer.put("bankName", "bread-bank");
        mapBankTransfer.put("referenceCode", "refCode");
        payments.add(new Payment("payment-id-2", PaymentMethod.BANK_TRANSFER.getValue(), mapBankTransfer));
    }

    @Test
    void testAddVoucherPayment() {
        when(paymentRepository.findById("payment-id-1")).thenReturn(null);
        when(paymentRepository.save(any(Payment.class), any(Order.class))).thenReturn(payments.getFirst());

        Payment result = paymentService.addPayment("payment-id-1", orders.getFirst(), PaymentMethod.VOUCHER_CODE.getValue(), payments.getFirst().getPaymentData());

        assertNotNull(result);
        assertEquals("payment-id-1", result.getId());
        verify(paymentRepository, times(1)).save(any(Payment.class), any(Order.class));
    }

    @Test
    void testAddVoucherPaymentInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () -> paymentService.addPayment("payment-id-3", orders.getFirst(), "INVALID_METHOD", new HashMap<>()));
    }

    @Test
    void testAddBankPayment() {
        when(paymentRepository.findById("payment-id-2")).thenReturn(null);
        when(paymentRepository.save(any(Payment.class), any(Order.class))).thenReturn(payments.get(1));

        Payment result = paymentService.addPayment("payment-id-2", orders.get(1), PaymentMethod.BANK_TRANSFER.getValue(), payments.get(1).getPaymentData());

        assertNotNull(result);
        assertEquals("payment-id-2", result.getId());
        verify(paymentRepository, times(1)).save(any(Payment.class), any(Order.class));
    }

    @Test
    void testAddBankPaymentInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () ->
                paymentService.addPayment("payment-id-4", orders.get(1), "INVALID_METHOD", new HashMap<>()
                ));
    }

    @Test
    void testSetValidStatus() {
        Payment payment = payments.getFirst();
        Order order = orders.getFirst();

        when(paymentRepository.getOrder(payment.getId())).thenReturn(order);

        Payment result = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());

        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), order.getStatus());

        verify(paymentRepository, times(1)).update(any(Payment.class), eq(PaymentStatus.SUCCESS.getValue()));
    }


    @Test
    void testInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () ->
                paymentService.setStatus(payments.getFirst(), "INVALID_STATUS"));
    }

    @Test
    void testValidGetPayment() {
        when(paymentRepository.findById("payment-id-1")).thenReturn(payments.getFirst());

        Payment result = paymentService.getPayment("payment-id-1");

        assertNotNull(result);
        assertEquals("payment-id-1", result.getId());
        verify(paymentRepository, times(1)).findById("payment-id-1");
    }

    @Test
    void testInvalidGetPayment() {
        when(paymentRepository.findById("invalid-id")).thenReturn(null);
        Payment result = paymentService.getPayment("invalid-id");
        assertNull(result);
    }

    @Test
    void testGetAllPayment() {
        MockitoAnnotations.openMocks(this);
        when(paymentRepository.findAll()).thenReturn(payments);

        List<Payment> result = paymentService.getAllPayments();

        assertEquals(2, result.size());
        verify(paymentRepository, times(1)).findAll();
    }

    @Test
    void testGetAllPaymentsIfEmpty() {
        when(paymentRepository.findAll()).thenReturn(new ArrayList<>());
        List<Payment> result = paymentService.getAllPayments();
        assertTrue(result.isEmpty());
    }
}
