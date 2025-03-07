package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;
    String[] statusList = new String[]{"SUCCESS", "REJECTED"};
    String[] methodList = new String[]{"VOUCHER_CODE", "BANK_TRANSFER"};

    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;

        this.validatePayment();
    }

    public void setStatus(String status) {
        if  (!Arrays.asList(statusList).contains(status)){
            throw new IllegalArgumentException();
        }
        this.status = status;
    }

    public void setMethod(String method) {
        if  (!Arrays.asList(methodList).contains(method)){
            throw new IllegalArgumentException();
        }
        this.method = method;
    }

    public void validatePayment(){
        boolean isValid = false;
        switch (method){
            case "VOUCHER_CODE":
                isValid = validateVoucher();
                break;

            case "BANK_TRANSFER":
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
