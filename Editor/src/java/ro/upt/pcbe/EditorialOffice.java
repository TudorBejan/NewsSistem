package ro.upt.pcbe;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Locale;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Topic;

/**
 *
 * @author Roxi
 */
public class EditorialOffice {
    
        private LinkedList<Editor> editors;
    
        @Resource(mappedName = "MyFactory")
        private static ConnectionFactory connectionFactory;
    
        @Resource(mappedName = "MyTopic")
        private static Topic topic;
   
        @Resource(mappedName = "Sport")
        private static Topic sport;
        
        @Resource(mappedName = "ConfirmationTopic")
        private static Topic confirmationTopic;
    
//    @Resource(mappedName = "Sport/Fotbal")
//    private static Topic sport_fotbal;
    
        
    
	public EditorialOffice() {
		this.editors = new LinkedList<Editor>();
	}

	public void addEditor(Editor editor)
	{
		editors.add(editor);
	}

	public LinkedList<Editor> getEditors() {
		return editors;
	}
        
        public static void main(String[] args) {
            EditorialOffice office = new EditorialOffice();
            Connection connection = null;
            InputStreamReader inputStreamReader;
            char answer = '\0';
            try {
                connection = connectionFactory.createConnection();
               
                Editor ed = new Editor("bubu", connection,office);
               // System.out.println(office.getEditors().getFirst().getName());
                ed.subscribeToConfirmation(confirmationTopic);
                //News news = new News(ed, "Sport","Sport","BBC", "Sport news");
                ed.publish("Random Title", topic, "Breaking News", "Just me writing smth");
                //TopicsList list = new TopicsList();
                ed.publish("Sport Title", sport, "BBC", "Sport");
                
                ed.delete("Random Title", topic);
                ed.update("Sport Title", "Just me writing an update!!", topic);
                
                connection.start();
                
                System.out.println("To end program, type Q or q, " + "then <return>");
                inputStreamReader = new InputStreamReader(System.in);

                while (!((answer == 'q') || (answer == 'Q'))) {
                    try {
                        answer = (char) inputStreamReader.read();
                    } catch (IOException e) {
                        System.err.println("I/O exception: " + e.toString());
                    }
            }
                
                } catch (JMSException e) {
                System.err.println("Exception occurred: " + e.toString());
                } finally {
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (JMSException e) {
                        }
                    }
                }
           }
}
