package esgi.common.dto;

public class AvailableRoomDto {


    private String type;
    private String space;
    private java.time.LocalDateTime dateStart;
    private java.time.LocalDateTime dateEnd;

    public AvailableRoomDto () {
    }

    public AvailableRoomDto (final String type, final String space, final java.time.LocalDateTime dateStart, final java.time.LocalDateTime dateEnd) {
        this.type = type;
        this.space = space;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
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

    public java.time.LocalDateTime getDateStart () {
        return dateStart;
    }

    public void setDateStart (final java.time.LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public java.time.LocalDateTime getDateEnd () {
        return dateEnd;
    }

    public void setDateEnd (final java.time.LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }
}