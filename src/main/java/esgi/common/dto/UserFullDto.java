package esgi.common.dto;

import java.util.UUID;

public class UserFullDto {

    private java.util.UUID uuidUser;
    private String name;
    private String surname;
    private String mail;
    private java.util.Date dateEndSubscription;
    private int subscription;

    public UserFullDto (java.util.UUID uuidUser, String name,
                        String surname, String mail,
                        java.util.Date dateEndSubscription, int subscription){
        this.uuidUser = uuidUser;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.dateEndSubscription = dateEndSubscription;
        this.subscription = subscription;
    }

    public UserFullDto (){   }

    public java.util.UUID getUuidUser () {
        return uuidUser;
    }

    public void setUuidUser (final java.util.UUID uuidUser) {
        this.uuidUser = uuidUser;
    }

    public String getName () {
        return name;
    }

    public void setName (final String name) {
        this.name = name;
    }

    public String getSurname () {
        return surname;
    }

    public void setSurname (final String surname) {
        this.surname = surname;
    }

    public String getMail () {
        return mail;
    }

    public void setMail (final String mail) {
        this.mail = mail;
    }

    public java.util.Date getDateEndSubscription () {
        return dateEndSubscription;
    }

    public void setDateEndSubscription (final java.util.Date dateEndSubscription) {
        this.dateEndSubscription = dateEndSubscription;
    }

    public int isSubscription () {
        return subscription;
    }

    public void setSubscription (final int subscription) {
        this.subscription = subscription;
    }
}
