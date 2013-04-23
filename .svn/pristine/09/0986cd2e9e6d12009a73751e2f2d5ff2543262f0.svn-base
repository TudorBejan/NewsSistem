package ro.upt.pcbe;

import javax.jms.*;

public class Reader {
    
    private String name;
    private NewsListener listener;
    private Connection connection;
    //private Topic topic;
    private String authorName;
    private Session session = null;        
    private MessageConsumer consumer = null;
    private MessageProducer producer = null;
    private Topic confirmationTopic;
    
    public Reader(String name, Connection connection,  
            String newsAuthorName, Topic confirmTopic ) throws JMSException{
        this.name=name;
        this.connection=connection;
        //this.topic = topic;
        this.authorName = newsAuthorName;
       
       // producer = session.createProducer(confirmationTopic);
        this.confirmationTopic = confirmTopic;
    }
    
    public void subscribeToTopic (Topic topic) throws JMSException
    {
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
       
        if (this.authorName!= null) {
            consumer = session.createConsumer(topic, this.createSelector());
        }
        else {
            consumer = session.createConsumer(topic);
        }
        listener = new NewsListener(this.getName(),confirmationTopic,connection);
        consumer.setMessageListener(listener);
        connection.start();
    }
    
    
    private String createSelector()
    {
        return Constants.EDITOR+" = '"+this.authorName+"'";
    }
    
    public String getName() {
        return name;
    }

    public String getAuthorName() {
        return authorName;
    }
    
//    public String getTopicName() {
//        try {
//            return topic.getTopicName();
//        } catch (JMSException ex) {
//            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
//            return "";
//        }
//    }
}
