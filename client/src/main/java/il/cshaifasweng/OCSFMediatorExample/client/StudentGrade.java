/**
 * Sample Skeleton for 'studentGrade.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Data;
import il.cshaifasweng.OCSFMediatorExample.entities.GradeOfStudent;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentGrade {

    @FXML // fx:id="Grade"
    private TableColumn<GradeOfStudent, Integer> Grade; // Value injected by FXMLLoader

    @FXML // fx:id="StudentID"
    private Text StudentID; // Value injected by FXMLLoader

    @FXML // fx:id="gradeNO"
    private TableColumn<GradeOfStudent, Integer> gradeNO; // Value injected by FXMLLoader

    @FXML // fx:id="gradeTable"
    private TableView<GradeOfStudent> gradeTable; // Value injected by FXMLLoader

    @FXML // fx:id="newGrade"
    private TextField newGrade; // Value injected by FXMLLoader

    @FXML // fx:id="studentName"
    private Text studentName; // Value injected by FXMLLoader

    @FXML
    private Text warining;
    @FXML
    void backToAll(ActionEvent event) throws IOException {
        MsgClass msg = new MsgClass("#get all students", null);
        SimpleClient.getClient().sendToServer(msg);
    }

    @FXML
    void updateGrade(ActionEvent event) {
        int index = gradeTable.getSelectionModel().getSelectedIndex();
        int num;
        if (index != -1) {
            try {
                num = Integer.valueOf(newGrade.getText());
            } catch (Exception ex) {
                warining.setText("please Enter a valid number !");
                newGrade.setText("");
                return;
            }
            if (num < 0) {
                warining.setText("please Enter a non negative number !");
                newGrade.setText("");
                return;
            }
            String msgString = "#update";
            try {
                //,gradeTable.getSelectionModel().getSelectedIndex(),num
                MsgClass msg = new MsgClass(msgString, Data.selectedStudent, gradeTable.getSelectionModel().getSelectedIndex(), num);
                SimpleClient.getClient().sendToServer(msg);
                JOptionPane.showMessageDialog(null, "Grade Updated");
                gradeTable.getItems().get(gradeTable.getSelectionModel().getSelectedIndex()).setGrade(num);
                gradeTable.refresh();
                warining.setText("");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        } else {
            warining.setText("pick a grade plaese!");
        }

    }

    @FXML
    public void initialize() {
        newGrade.setDisable(true);
        Student st = Data.selectedStudent;
        studentName.setText(st.getFirstName()+" "+ st.getSecondName());
        StudentID.setText(st.getIdNum());
        ObservableList<GradeOfStudent> observableList = FXCollections.observableArrayList();
        observableList.add(new GradeOfStudent(1, st.getFirstGrade()));
        observableList.add(new GradeOfStudent(2, st.getSecondGrade()));
        observableList.add(new GradeOfStudent(3, st.getThirdGrade()));
        gradeNO.setCellValueFactory(new PropertyValueFactory<GradeOfStudent, Integer>("gradeNo"));
        Grade.setCellValueFactory(new PropertyValueFactory<GradeOfStudent, Integer>("grade"));
        gradeTable.setItems(observableList);
    }

    public void tableClicked(MouseEvent mouseEvent) {
        warining.setText("");
        newGrade.setDisable(false);
        newGrade.setText(String.valueOf(gradeTable.getSelectionModel().getSelectedItem().getGrade()));
    }
}