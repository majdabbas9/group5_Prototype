/**
 * Sample Skeleton for 'First.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class PrimaryController {

	@FXML
	void showAllStudents(ActionEvent event) throws IOException {
		MsgClass msg = new MsgClass("#get all students", null); // creaing a msg to the server demanding the students
		SimpleClient.getClient().sendToServer(msg); // sending the msg to the sevrer
	}

}
