package id.ac.ui.cs.advprog.eshop.model;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import lombok.Getter;
import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;

    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id;
        this.setMethod(method);
        this.paymentData = paymentData;
        this.validatePayment();
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)){
            this.status = status;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public void setMethod(String method) {
        if  (PaymentMethod.contains(method)){
        this.method = method;
        } else{
            throw new IllegalArgumentException();
        }
    }

    public void validatePayment(){
        boolean isValid = false;
        switch (PaymentMethod.valueOf(method)){
            case PaymentMethod.VOUCHER_CODE:
                isValid = validateVoucher();
                break;

            case PaymentMethod.BANK_TRANSFER:
                isValid = validateBankTransfer();
                break;

            default:
                break;
        }

        if(isValid){
            setStatus("SUCCESS");
        } else {
            setStatus("REJECTED");
        }

    }

    public boolean validateVoucher(){
        String voucherCode = paymentData.get("voucherCode");
        boolean isVoucherCodeValid = voucherCode != null && !voucherCode.isEmpty();
        if(!isVoucherCodeValid){
            return false;
        }

        return validateVoucherCode(voucherCode);
    }

    public boolean validateVoucherLength(String voucherCode){
        return voucherCode.length() == 16;
    }

    public boolean validateVoucherPrefix(String voucherCode){
        return voucherCode.startsWith("ESHOP");
    }

    public boolean validateVoucherNumericValid(String voucherCode){
        String givenCode = voucherCode.substring(5);
        int numericCount = 0;
        for (char character : givenCode.toCharArray()){
            if (Character.isDigit(character)){
                numericCount++;
            }
        }
        return numericCount == 8;
    }

    public boolean validateVoucherCode(String voucher){
        boolean isVoucherLengthValid = validateVoucherLength(voucher);
        boolean isVoucherPrefixValid = validateVoucherPrefix(voucher);
        boolean isVoucherNumericValid = validateVoucherNumericValid(voucher);

        return  isVoucherLengthValid && isVoucherPrefixValid && isVoucherNumericValid;
    }

    public boolean validateBankTransfer(){
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");

        boolean isBankNameValid = bankName != null && !bankName.isEmpty();
        boolean isReferenceCodeValid = referenceCode != null && !referenceCode.isEmpty();
        return isBankNameValid && isReferenceCodeValid;
    }

}
