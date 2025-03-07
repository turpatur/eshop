package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    SUCCESS("SUCCESS"),
    REJECTED("REJECTED");

    private final String value;
    private PaymentStatus(String value) {
        this.value = value;
    }

    public static boolean contains(String value){
        for (PaymentStatus paymentStatus : PaymentStatus.values()) {
            if (paymentStatus.value.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
