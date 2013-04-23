/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.upt.pcbe;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.*;


/**
 *
 * @author Roxi
 */
// creaza evemente de tipul PUBLISH, READ, DELETE
public class EventFactory {
    
    public static Event readEvent(String source, String topic, String autor){
        Event e = new Event(Constant.READ_EVENT_ID, source,null);
        e.addProperty(Constant.TOPIC, topic);
        e.addProperty(Constant.EDITOR, autor);
        
        return e;
    }
    
     public static TextMessage publishEvent(String source, String topic, News news, 
             Session session) throws JMSException{
        //Event e = new Event(PUBLISH_EVENT_ID, source, news);
        //e.addProperty(TOPIC, topic);
        TextMessage msg = session.createTextMessage();
        msg.setText(news.getContext());
        msg.setStringProperty(Constant.TOPIC, topic);
        msg.setStringProperty(Constant.EDITOR, news.getAuthor());
        msg.setStringProperty(Constant.FIRST_PUBLICATION, news.getFirstPublication());
        msg.setStringProperty(Constant.LAST_PUBLICATION, news.getLastPublication());
        msg.setStringProperty(Constant.SOURCE, news.getSource());
        msg.setStringProperty(Constant.TITLE, news.getTitle());
        msg.setStringProperty(Constant.EVENT_TYPE, Constant.PUBLISH);
        return msg;
    }
     
      public static TextMessage deleteEvent(String author, String title,Session session) throws JMSException{
       
        TextMessage msg = session.createTextMessage();
        msg.setText("The news "+ title +" was delete");
        msg.setStringProperty(Constant.EDITOR, author);
        msg.setStringProperty(Constant.TITLE, title);
        msg.setStringProperty(Constant.EVENT_TYPE, Constant.DELETE);
        
       
        return msg;
    }
      
    public static TextMessage updateEvent(String author, String title,Session session,String newContent) throws JMSException{

        TextMessage msg = session.createTextMessage();
        msg.setText("UPDATE:"+newContent);
        msg.setStringProperty(Constant.EDITOR, author);
        msg.setStringProperty(Constant.TITLE, title);
        msg.setStringProperty(Constant.EVENT_TYPE, Constant.UPDATE);
           
        return msg;
    }
}
