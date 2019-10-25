package esgi.common.dto;

import java.util.UUID;

public class UserDto {

    private String identifiant;
    private String password;

    public UserDto (final String identifiant, final String password) {
        this.identifiant = identifiant;
        this.password = password;
    }

    public UserDto () {
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
