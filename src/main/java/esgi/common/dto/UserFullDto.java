package esgi.common.dto;

import java.util.UUID;
import java.time.LocalDate;

public class UserFullDto {

    private UUID uuidUser;
    private String name;
    private String surname;
    private String mail;
    private LocalDate dateEndSubscription;
    private String dateEndSubscriptionString;
    private int subscription;
    private String type;
    private String identifiant;
    private String password;


    public UserFullDto (final String name, final String surname, final String mail, final java.time.LocalDate dateEndSubscription, final int subscription, final String type, final String identifiant, final String password) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.dateEndSubscription = dateEndSubscription;
        this.subscription = subscription;
        this.type = type;
        this.identifiant = identifiant;
        this.password = password;
    }

    public UserFullDto (final java.util.UUID uuidUser, final String name, final String surname, final String mail, final java.time.LocalDate dateEndSubscription, final int subscription, final String type, final String identifiant, final String password) {
        this.uuidUser = uuidUser;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.dateEndSubscription = dateEndSubscription;
        this.subscription = subscription;
        this.type = type;
        this.identifiant = identifiant;
        this.password = password;
    }

    public UserFullDto (final String name, final String surname, final String mail, final String dateEndSubscriptionString, final int subscription, final String type, final String identifiant, final String password) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.dateEndSubscriptionString = dateEndSubscriptionString;
        this.subscription = subscription;
        this.type = type;
        this.identifiant = identifiant;
        this.password = password;
    }

    public UserFullDto (final String name, final String surname) {
        this.name = name;
        this.surname = surname;
    }

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

    public java.time.LocalDate getDateEndSubscription () {
        return dateEndSubscription;
    }

    public void setDateEndSubscription (final java.time.LocalDate dateEndSubscription) {
        this.dateEndSubscription = dateEndSubscription;
    }

    public String getDateEndSubscriptionString () {
        return dateEndSubscriptionString;
    }

    public void setDateEndSubscriptionString (final String dateEndSubscriptionString) {
        this.dateEndSubscriptionString = dateEndSubscriptionString;
    }

    public int getSubscription () {
        return subscription;
    }

    public void setSubscription (final int subscription) {
        this.subscription = subscription;
    }

    public String getType () {
        return type;
    }

    public void setType (final String type) {
        this.type = type;
    }

    public String getIdentifiant () {
        return identifiant;
    }

    public void setIdentifiant (final String identifiant) {
        this.identifiant = identifiant;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (final String password) {
        this.password = password;
    }
}
