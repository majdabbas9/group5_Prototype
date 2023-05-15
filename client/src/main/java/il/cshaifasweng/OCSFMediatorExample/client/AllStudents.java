

package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Data;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;


public class AllStudents {

    //@FXML // fx:id="StudentsList"
    //private ListView<String> StudentsList; // Value injected by FXMLLoader

    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<Student, Integer> studentNo;

    @FXML
    private TableColumn<Student, String> firstName;
    @FXML
    private TableColumn<Student, String> secondName;
    @FXML
    private TableColumn<Student, String> studentID;

    @FXML // fx:id="studentName"
    private Label studentName; // Value injected by FXMLLoader

    @FXML
    void backToMain(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    void showStudentGrades(ActionEvent event) throws IOException {
        int index = tableView.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            App.setRoot("studentGrade");
        }
    }

    @FXML
    void initialize() {
        List<Student> students = (List<Student>) Data.students;
        ObservableList<Student> observableList = FXCollections.observableArrayList();
        for (Student s: students){
            //observableList.add(new Student(s.getId(),s.getFirstName(),s.getSecondName(),s.getIdNum()));
            observableList.add(s);
        }
        studentNo.setCellValueFactory(new PropertyValueFactory<Student,Integer>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<Student,String>("firstName"));
        secondName.setCellValueFactory(new PropertyValueFactory<Student,String>("secondName"));
        studentID.setCellValueFactory(new PropertyValueFactory<Student,String>("idNum"));
        tableView.setItems(observableList);
    }
    public void tableClicked(javafx.scene.input.MouseEvent mouseEvent) {
        int index=tableView.getSelectionModel().getSelectedIndex();
        if(index==-1) // if nothing selected
        {
            studentName.setText("");
        }
        else { // if something is selected
            Student s = (Student) tableView.getSelectionModel().getSelectedItem(); // getting the selected student
            Data.selectedStudent = s; //changing the selected student
            if (s.getFirstName().equals("Lionel")) { // this is not part of the main code just a meme
                studentName.setText("THE GOAT");
            }
            else if(s.getFirstName().equals("Cristiano"))// this is not part of the code just a meme
            {
                studentName.setText("THE FINISHED");
            }
            else
            {
                // this is part of the code
                studentName.setText(s.getFirstName() + " " + s.getSecondName()); // showing the student first and last name
            }

        }
    }
}