package esgi.common.dto;

import java.util.UUID;

public class ClientDto {

    private java.util.UUID uuidClient;
    private boolean subscription;

    public ClientDto( java.util.UUID uuidClient){

        this.uuidClient = uuidClient;
        this.subscription = subscription;
    }

    public java.util.UUID getUuidClient() {
        return uuidClient;
    }

    public boolean getSubscription() {
        return subscription;
    }

    public void setSubscription(boolean subscription) {
        this.subscription = subscription;
    }
}
