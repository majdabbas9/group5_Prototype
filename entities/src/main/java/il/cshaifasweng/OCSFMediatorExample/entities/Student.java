package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Students")
public class Student  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String secondName;

    private String idNum;

    @Column(name="BirthDay")
    private String birthDay;

    private Gender gender;

    @Column(name="Grade1")
    private int firstGrade;

    @Column(name="Grade2")
    private int secondGrade;

    @Column(name="Grade3")
    private int thirdGrade;

    public Student(String firstName, String secondName, String idNum,
                   String birthDay, Gender gender, int firstGrade, int secondGrade, int thirdGrade) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.idNum = idNum;
        this.birthDay = birthDay;
        this.gender = gender;
        this.firstGrade = firstGrade;
        this.secondGrade = secondGrade;
        this.thirdGrade = thirdGrade;
    }

    public Student(int id, String firstName, String secondName, String idNum) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.idNum = idNum;
    }

    public Student() {

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

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getFirstGrade() {
        return firstGrade;
    }

    public void setFirstGrade(int firstGrade) {
        this.firstGrade = firstGrade;
    }

    public int getSecondGrade() {
        return secondGrade;
    }

    public void setSecondGrade(int secondGrade) {
        this.secondGrade = secondGrade;
    }

    public int getThirdGrade() {
        return thirdGrade;
    }

    public void setThirdGrade(int thirdGrade) {
        this.thirdGrade = thirdGrade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", idNum='" + idNum + '\'' +
                ", birthDay=" + birthDay +
                ", gender=" + gender +
                ", firstGrade=" + firstGrade +
                ", secondGrade=" + secondGrade +
                ", thirdGrade=" + thirdGrade +
                '}';
    }
}
