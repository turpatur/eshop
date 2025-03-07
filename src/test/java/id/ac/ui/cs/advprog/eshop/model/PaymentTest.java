package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    @BeforeEach
    void setUp() {
    }

    /*
    INVALIDS
     */
    @Test
    void TestPaymentInvalidStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("random-id",PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("RANDOM_STATUS");
        });
    }

    @Test
    void TestPaymentInvalidMethod(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("random-id",PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setMethod("RANDOM_METHOD");
        });
    }

    /*
    VOUCHER VALIDATION
     */
    @Test
    void testPaymentVoucherNot16Chars(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678A");
        Payment payment = new Payment("random-id", PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentVoucherNotStartWithEshop(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "EASHOP1234ABC5678");
        Payment payment = new Payment("random-id", PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentVoucherNot8NumericalChars(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC56789");
        Payment payment = new Payment("random-id", PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentNullVoucherCode(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", null);

        Payment payment = new Payment("random-id", PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentEmptyVoucherCode(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "");

        Payment payment = new Payment("random-id", PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    /*
    BANK VALIDATION
     */

    @Test
    void testPaymentNullBankNameRefCode(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", null);
        paymentData.put("referenceCode", "aRefCode");

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData.put("bankName", "aBankName");
        paymentData.put("referenceCode", null);

        Payment payment = new Payment("random-id", PaymentMethod.BANK_TRANSFER.getValue(), paymentData);
        Payment payment2 = new Payment("random-id", PaymentMethod.BANK_TRANSFER.getValue(), paymentData2);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment2.getStatus());
    }

    @Test
    void testPaymentEmptyBankNameRefCode(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "");
        paymentData.put("referenceCode", "aRefCode");

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData.put("bankName", "aBankName");
        paymentData.put("referenceCode", "");

        Payment payment = new Payment("random-id", PaymentMethod.BANK_TRANSFER.getValue(), paymentData);
        Payment payment2 = new Payment("random-id", PaymentMethod.BANK_TRANSFER.getValue(), paymentData2);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment2.getStatus());
    }

    @Test
    void testPaymentBankInvalidInformation(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "a-random-code");
        Payment payment = new Payment("random-id", PaymentMethod.BANK_TRANSFER.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    /*
    HAPPY PATHS
     */
    @Test
    void testPaymentValidVoucher(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("random-id", PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentValidBankTransfer(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "random-bank-name");
        paymentData.put("referenceCode", "1000");
        Payment payment = new Payment("random-id", PaymentMethod.BANK_TRANSFER.getValue(), paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

}
