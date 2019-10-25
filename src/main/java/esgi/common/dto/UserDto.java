package esgi.common.dto;

import java.util.UUID;

public class UserDto {

    private UUID uuidUser;
    private boolean subscription;

    public UserDto (UUID uuidUser, boolean subscription){

        this.uuidUser = uuidUser;
        this.subscription = subscription;
    }

    public UUID getUuidUser() {
        return uuidUser;
    }

    public boolean getSubscription() {
        return subscription;
    }

    public void setUuidUser (final UUID uuidUser) {
        this.uuidUser = uuidUser;
    }

    public void setSubscription(boolean subscription) {
        this.subscription = subscription;
    }
}
