package esgi.common.dto;


public class OrderDto {

    private String nomUser;
    private String surnomUser;
    private String date;

    public OrderDto (final String nomUser, final String surnomUser, final String date) {
        this.nomUser = nomUser;
        this.surnomUser = surnomUser;
        this.date = date;
    }

	public OrderDto () {
	}

	public String getNomUser () {
        return nomUser;
    }

    public void setNomUser (final String nomUser) {
        this.nomUser = nomUser;
    }

    public String getSurnomUser () {
        return surnomUser;
    }

    public void setSurnomUser (final String surnomUser) {
        this.surnomUser = surnomUser;
    }

    public String getDate () {
        return date;
    }

    public void setDate (final String date) {
        this.date = date;
    }
}
