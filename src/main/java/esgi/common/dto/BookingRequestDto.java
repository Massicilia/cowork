package esgi.common.dto;

public class BookingRequestDto {


    private String nameUser;
    private String surnameUser;
    private String type;
    private String space;
    private java.time.LocalDate dateStart;
    private java.time.LocalDate dateEnd;

    public BookingRequestDto (final String nameUser, final String surnameUser, final String type, final String space, final java.time.LocalDate dateStart, final java.time.LocalDate dateEnd) {
        this.nameUser = nameUser;
        this.surnameUser = surnameUser;
        this.type = type;
        this.space = space;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public BookingRequestDto () {
    }

    public String getNameUser () {
        return nameUser;
    }

    public void setNameUser (final String nameUser) {
        this.nameUser = nameUser;
    }

    public String getSurnameUser () {
        return surnameUser;
    }

    public void setSurnameUser (final String surnameUser) {
        this.surnameUser = surnameUser;
    }

    public String getType () {
        return type;
    }

    public void setType (final String type) {
        this.type = type;
    }

    public String getSpace () {
        return space;
    }

    public void setSpace (final String space) {
        this.space = space;
    }

    public java.time.LocalDate getDateStart () {
        return dateStart;
    }

    public void setDateStart (final java.time.LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public java.time.LocalDate getDateEnd () {
        return dateEnd;
    }

    public void setDateEnd (final java.time.LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }
}