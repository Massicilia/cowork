package esgi.common.dto;

import java.util.UUID;

public class ClientFullDto {

    private java.util.UUID uuidClient;
    private String nameClient;
    private String surnameClient;
    private String mail;
    private boolean subscription;

    public ClientFullDto(java.util.UUID uuidClient, String nameClient,
                         String surnameClient, String mail, boolean subscription){
        this.uuidClient = uuidClient;
        this.nameClient = nameClient;
        this.surnameClient = surnameClient;
        this.mail = mail;
        this.subscription = subscription;
    }

    public ClientFullDto(){   }

    public java.util.UUID getUuidClient() {
        return uuidClient;
    }

    public void setUuidClient(java.util.UUID uuidClient) {
        this.uuidClient = uuidClient;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getSurnameClient() {
        return surnameClient;
    }

    public void setSurnameClient(String surnameClient) {
        this.surnameClient = surnameClient;
    }

    public boolean getSubscription() {
        return subscription;
    }

    public void setSubscription(boolean subscription) {
        this.subscription = subscription;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
