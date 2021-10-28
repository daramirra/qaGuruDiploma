package api;

public class StartPwdRecoveryRequest {
    private String identifierType;
    private String login;
    private String recoveryPageUrl;
    private String service;
    private String storageId;

    public StartPwdRecoveryRequest(String login) {
        this.login = login;
        this.setRecoveryPageUrl("https://sdo-oib-test.it2g.ru/auth/recover-password?tokenId={token}");
        this.setIdentifierType("LOGIN");
        this.setService("https://sdo-oib-test.it2g.ru/admin");
        this.setStorageId("internal");
    }

    public StartPwdRecoveryRequest() {
    }

    public String getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(String identifierType) {
        this.identifierType = identifierType;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRecoveryPageUrl() {
        return recoveryPageUrl;
    }

    public void setRecoveryPageUrl(String recoveryPageUrl) {
        this.recoveryPageUrl = recoveryPageUrl;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }
}
