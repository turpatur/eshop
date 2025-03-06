package id.ac.ui.cs.advprog.eshop.model;

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
    void TestPaymentInvalidSubFeature(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("random-id", "random-method", paymentData);
        });
    }

    @Test
    void TestPaymentInvalidStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("random-id","VOUCHER_CODE", paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("RANDOM_STATUS");
        });
    }

    @Test
    void testPaymentInvalidMap(){
        Map<String, String> paymentData = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("random-id", "VOUCHER_CODE", paymentData);
        });
    }

    @Test void testMismatchMethods(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("random-id", "CASH_ON_DELIVERY", paymentData);
        });
    }

    /*
    DELIVERY VALIDATION
     */
    @Test
    void testPaymentUnderZeroDeliveryFee(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "random-address");
        paymentData.put("deliveryFee", "-1");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("random-id", "CASH_ON_DELIVERY", paymentData);
        });
    }

    @Test
    void testPaymentNotNumberDeliveryFee(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "random-address");
        paymentData.put("deliveryFee", "im_not_a_number");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("random-id", "CASH_ON_DELIVERY", paymentData);
        });
    }

    @Test
    void testPaymentNullAddressDeliveryFee(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "random-address");
        paymentData.put("deliveryFee", null);

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("address", null);
        paymentData2.put("deliveryFee", "10");

        Payment payment = new Payment("random-id", "CASH_ON_DELIVERY", paymentData);
        Payment payment2 = new Payment("random-id", "CASH_ON_DELIVERY", paymentData2);
        assertEquals("REJECTED", payment.getStatus());
        assertEquals("REJECTED", payment2.getStatus());
    }

    @Test
    void testPaymentNullEmptyAddressDeliveryFee(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "random-address");
        paymentData.put("deliveryFee", "");

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("address", "");
        paymentData2.put("deliveryFee", "10");

        Payment payment = new Payment("random-id", "CASH_ON_DELIVERY", paymentData);
        Payment payment2 = new Payment("random-id", "CASH_ON_DELIVERY", paymentData2);
        assertEquals("REJECTED", payment.getStatus());
        assertEquals("REJECTED", payment2.getStatus());
    }

    /*
    VOUCHER VALIDATION
     */
    @Test
    void testPaymentVoucherNot16Chars(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678A");
        Payment payment = new Payment("random-id", "VOUCHER_CODE", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testPaymentVoucherNotStartWithEshop(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "EASHOP1234ABC5678");
        Payment payment = new Payment("random-id", "VOUCHER_CODE", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testPaymentVoucherNot8NumericalChars(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC56789");
        Payment payment = new Payment("random-id", "VOUCHER_CODE", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testPaymentNullVoucherCode(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", null);

        Payment payment = new Payment("random-id", "VOUCHER_CODE", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testPaymentEmptyVoucherCode(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "");

        Payment payment = new Payment("random-id", "VOUCHER_CODE", paymentData);
        assertEquals("REJECTED", payment.getStatus());
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

        Payment payment = new Payment("random-id", "BANK_TRANSFER", paymentData);
        Payment payment2 = new Payment("random-id", "BANK_TRANSFER", paymentData2);
        assertEquals("REJECTED", payment.getStatus());
        assertEquals("REJECTED", payment2.getStatus());
    }

    @Test
    void testPaymentEmptyBankNameRefCode(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "");
        paymentData.put("referenceCode", "aRefCode");

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData.put("bankName", "aBankName");
        paymentData.put("referenceCode", "");

        Payment payment = new Payment("random-id", "BANK_TRANSFER", paymentData);
        Payment payment2 = new Payment("random-id", "BANK_TRANSFER", paymentData2);
        assertEquals("REJECTED", payment.getStatus());
        assertEquals("REJECTED", payment2.getStatus());
    }

    /*
    HAPPY PATHS
     */
    @Test
    void testPaymentValidVoucher(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("random-id", "VOUCHER_CODE", paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testPaymentValidCashOnDelivery(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "random-address");
        paymentData.put("deliveryFee", "1000");
        Payment payment = new Payment("random-id", "CASH_ON_DELIVERY", paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testPaymentValidBankTransfer(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "random-bank-name");
        paymentData.put("referenceCode", "1000");
        Payment payment = new Payment("random-id", "BANK_TRANSFER", paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

}
