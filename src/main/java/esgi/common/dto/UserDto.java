package esgi.common.dto;

import java.util.UUID;

public class UserDto {

    private java.util.UUID uuidUser;
    private boolean subscription;

    public UserDto (java.util.UUID uuidUser, boolean subscription){

        this.uuidUser = uuidUser;
        this.subscription = subscription;
    }

    public java.util.UUID getUuidUser() {
        return uuidUser;
    }

    public boolean getSubscription() {
        return subscription;
    }

    public void setUuidUser (final java.util.UUID uuidUser) {
        this.uuidUser = uuidUser;
    }

    public void setSubscription(boolean subscription) {
        this.subscription = subscription;
    }
}
