package il.cshaifasweng.OCSFMediatorExample.entities;

public class ShowStudent {
    private int id;

    private String firstName;
    private String secondName;

    private String idNum;
    public ShowStudent(int id, String firstName, String secondName, String idNum) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.idNum = idNum;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }
}
