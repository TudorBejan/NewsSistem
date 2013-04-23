package ro.upt.pcbe;

/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Topic;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;
import javax.jms.JMSException;
import javax.annotation.Resource;
import javax.jms.MessageConsumer;



public class Editor {
    
    Connection connection;
    Session session;
    private String name;
    private List<News> entries; 
    private EditorialOffice office;
    
    public String getName(){
		return this.name;
    }

    public Editor(String name, Connection connection,EditorialOffice office){
		this.name = name;
		entries = new LinkedList<News>();
                this.connection = connection;
                this.office=office;
                office.addEditor(this);
		//cred ca trebuie sa il abonam la evenimentul : stire de a mea a fost citita (nu stiu cum se face inca ..)
    }
    
    public void addEntry (News news)
    {
        entries.add(news);
    }
    
    public void publish(String title,Topic topic,String source, 
            String content) throws JMSException { 
        
        News news = new News(this,title,topic,source,content);
        this.entries.add(news);

        session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        MessageProducer producer = session.createProducer(topic);
        //TextMessage message = session.createTextMessage();  
               
        TextMessage message = EventFactory.publishEvent(Constant.PUBLISH_EVENT_ID, topic.getTopicName(), news, session);
        System.out.println("Sending message: " + message.getText());
        producer.send(message);
        System.out.println("S-a publicat o noua stire");
        /*
         * Send a non-text control message indicating end of
         * messages.
         */
        producer.send(session.createMessage());
        
    }
     private String createSelector()
    {
        return Constant.EDITOR+" = '"+this.name+"'";
    }
    
    public void subscribeToConfirmation(Topic topic) throws JMSException{
             MessageConsumer consumer = null;
             session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
             if (this.name!= null) {
                consumer = session.createConsumer(topic, this.createSelector());
             }
             else {
                consumer = session.createConsumer(topic);
             }
             
             ReaderListener listener = new ReaderListener(this);
             consumer.setMessageListener(listener);
             
    }
    
    public void delete(String title,Topic topic) throws JMSException{
        
        Iterator it = entries.iterator();
        while(it.hasNext()){
            News news = (News)it.next();
            if(news.getTitle().equals(title)){
                 this.entries.remove(news);
            }
        }
        session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        MessageProducer producer = session.createProducer(topic);
        //TextMessage message = session.createTextMessage();  
               
        TextMessage message = EventFactory.deleteEvent(this.getName(),title, session);
        System.out.println("Sending message: " + message.getText());
        producer.send(message);
        System.out.println("S-a sters stirea cu titlul "+title);
        /*
         * Send a non-text control message indicating end of
         * messages.
         */
        producer.send(session.createMessage());
        
    }
    
    public void update(String title,String newContent, Topic topic) throws JMSException{
        News newNews=null;
        int index=0;
        Iterator it = entries.iterator();
        while(it.hasNext()){
            News news = (News)it.next();
            if(news.getTitle().equals(title)){
                 index = this.entries.indexOf(news);
                 newNews = new News(this,title,topic,news.getSource(),newContent);
            }
        }
        
        this.entries.set(index, newNews);
        
        session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        MessageProducer producer = session.createProducer(topic);
        //TextMessage message = session.createTextMessage();  
               
        TextMessage message = EventFactory.updateEvent(this.getName(),title,session,newContent);
        System.out.println("Sending message: " + message.getText());
        producer.send(message);
        System.out.println("S-a modificat stirea "+title);
        /*
         * Send a non-text control message indicating end of
         * messages.
         */
        producer.send(session.createMessage());
    }
    
    }
     
    

