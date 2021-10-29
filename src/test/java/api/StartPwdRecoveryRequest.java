package api;

public class StartPwdRecoveryRequest {

    private LoginIdentifier identifier;
    private String recoveryPageUrl;
    private String service;
    private String storageId;

    public StartPwdRecoveryRequest(String login) {
        this.identifier = new LoginIdentifier(login);
        this.setRecoveryPageUrl("https://sdo-oib-test.it2g.ru/auth/recover-password?tokenId={token}");
        this.setService("https://sdo-oib-test.it2g.ru/admin");
        this.setStorageId("internal");
    }

    public StartPwdRecoveryRequest() {
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

    public LoginIdentifier getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(LoginIdentifier identifier) {
        this.identifier = identifier;
    }
}

class LoginIdentifier{
    private String login;

    public LoginIdentifier() {
    }

    public LoginIdentifier(String login) {
        this.login = login;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
