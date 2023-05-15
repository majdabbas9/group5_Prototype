package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Data;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;

import java.io.IOException;
import java.util.List;

public class SimpleClient extends AbstractClient {

	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) throws IOException {
		if (msg.getClass().equals(Warning.class)) {
			Warning w=(Warning)msg;
			//EventBus.getDefault().post(new WarningEvent((Warning) msg));
		}
		if(msg.getClass().equals(MsgClass.class)) {
			MsgClass msgFromServer = (MsgClass) msg;
			if(msgFromServer.getMsg().equals("all students")) { /* checking if need to pass the students to the Data class
			and the moving to the AllStudents window*/
				try {
					Data.students=msgFromServer.getObj();
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}

				try {
					App.setRoot("AllStudents"); // App.setRoot("AllStudents");
				}
				catch (IOException ex)
				{
					System.out.println(ex.getMessage());
				}
			}
		}

	}

	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3020);
		}
		return client;
	}

}
