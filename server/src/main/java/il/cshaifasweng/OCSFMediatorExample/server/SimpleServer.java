package il.cshaifasweng.OCSFMediatorExample.server;

import com.mysql.cj.xdevapi.Client;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.IOException;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.entities.Gender.female;
import static il.cshaifasweng.OCSFMediatorExample.entities.Gender.male;
import  il.cshaifasweng.OCSFMediatorExample.entities.Data;
public class SimpleServer extends AbstractServer {


	private static Session session;

	private static List<Student> getAllStudents() throws Exception {

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Student> query = builder.createQuery(Student.class);
		query.from(Student.class);
		List<Student> data = session.createQuery(query).getResultList();
		return data;
	}



	private static void generateStudents() {
		Student student1 = new Student("Mohamad","Hijazi","123123123", "28/08/2002" ,male,90,100,95);
		session.save(student1);
		session.flush();
		Student student2 = new Student("Majd","Abass","123456789", "15/05/1995" ,male,93,98,85);
		session.save(student2);
		session.flush();
		Student student3 = new Student("Mustafa","Jabareen","654213978", "15/02/2005" ,male,75,92,89);
		session.save(student3);
		session.flush();
		Student student4 = new Student("Adam","Rayan","223163544", "19/03/2006" ,male,75,86,91);
		session.save(student4);
		session.flush();
		Student student5 = new Student("mohamed","Jabreen","621354155", "30/02/2007" ,male,84,82,91);
		session.save(student5);
		session.flush();
		Student student6 = new Student("Yam","Yakov","621354122", "06/09/2006" ,female,65,100,98);
		session.save(student6);
		session.flush();
		Student student7 = new Student("Youval","Malog","545412362", "01/10/2010" ,female,95,90,85);
		session.save(student7);
		session.flush();
		Student student8 = new Student("Noya","Noam","621444878", "11/05/2007" ,female,100,100,95);
		session.save(student8);
		session.flush();
		Student student9 = new Student("lilia","Ben","333655829", "14/02/2008" ,female,84,96,55);
		session.save(student9);
		session.flush();
		Student student10 = new Student("Michal","Feldman","332658442", "23/08/2009" ,female,90,95,100);
		session.save(student10);
		session.flush();
		Student student11 = new Student("Lionel","Messi","99999999", "24/06/1987" ,male,100,100,100);
		session.save(student11);
		Student student12 = new Student("Cristiano","Ronaldo","00000000", "5/02/1985" ,male,0,0,0);
		session.save(student12);
		session.flush();

	}

	public static Session getSession() {
		return session;
	}

	public static void setSession(Session session) {
		SimpleServer.session = session;
	}


	private static SessionFactory getSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(Student.class);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.build();
		return configuration.buildSessionFactory(serviceRegistry);
	}

	public SimpleServer(int port) {
		super(port);
		SessionFactory sessionFactory = getSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		generateStudents();  // moving the students to the database
		session.getTransaction().commit();
	}
	private  void UpdateGrade(Student student,int newGrade,int gradeNum)
	{
		session.beginTransaction();
		switch (gradeNum) // checking which grade we need to update (the first or the second of the third)
		{
			case 0: // if the first
				student.setFirstGrade(newGrade);
				break;
			case 1:// if the second
				student.setSecondGrade(newGrade);
				break;
			case 2:// if the third
				student.setThirdGrade(newGrade);
		}
		session.update(student);
		session.getTransaction().commit();
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		if (msg.getClass().equals(MsgClass.class)) { // checking if the msg class is MsgClass
			MsgClass msgFromClient = (MsgClass) msg;
			String msgContent=msgFromClient.getMsg(); // getting the content of the msg
			try {
				if (msgContent.equals("#get all students")) { // checking if we need to return all students
					try {
						/*Creating a msg to the clint which contain all students int the database*/
						MsgClass msgToClient = new MsgClass("all students",getAllStudents());
						client.sendToClient(msgToClient);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				if(msgContent.equals("#update"))// checking if we need to update a certain student grade
				{
					try {
						Student student=(Student)(msgFromClient.getObj()); //getting the student from the msg sent
						/*updating the student with the new grade for the grade number*/
						UpdateGrade(session.get(Student.class,student.getId()),msgFromClient.getNewGrade(),msgFromClient.getGradeNum());
						MsgClass clientMsg=new MsgClass("#updated");
						client.sendToClient(clientMsg);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}


}




