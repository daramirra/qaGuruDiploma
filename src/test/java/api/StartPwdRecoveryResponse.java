package api;

public class StartPwdRecoveryResponse {

    private String errorCode;

    private String maskedEmail;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMaskedEmail() {
        return this.maskedEmail;
    }

    public void setMaskedEmail(String maskedEmail) {
        this.maskedEmail = maskedEmail;
    }
}
