/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.upt.pcbe;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.*;

/**
 *
 * @author Eniko
 */
public class ReaderCommunity {
    
    @Resource(mappedName = "MyFactory")
    private static ConnectionFactory connectionFactory;
  
    @Resource(mappedName = "MyTopic")
    private static Topic topic;
    
    @Resource(mappedName = "Sport")
    private static Topic sport;
    
    @Resource(mappedName = "ConfirmationTopic")
    private static Topic confirmationTopic;
    
    public static void main(String[] args){
        //new Reader("mimi").read();
        Connection connection = null;
        InputStreamReader inputStreamReader;
        char answer = '\0';
        
        try {
            connection = connectionFactory.createConnection();
            Reader r = new Reader("mimi",connection,null,confirmationTopic);
            r.subscribeToTopic(topic);
            r.subscribeToTopic(sport);
            
            System.out.println("To end program, type Q or q, " + "then <return>");
            inputStreamReader = new InputStreamReader(System.in);

            while (!((answer == 'q') || (answer == 'Q'))) {
                try {
                    answer = (char) inputStreamReader.read();
                } catch (IOException e) {
                    System.err.println("I/O exception: " + e.toString());
                }
            }
            
        } catch (JMSException ex) {
            Logger.getLogger(ReaderCommunity.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
        if (connection != null) {
            try {
                connection.close();
                } catch (JMSException e) {
                }
            }
        } 
    }
}
