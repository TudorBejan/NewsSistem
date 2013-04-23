package ro.upt.pcbe;

import javax.jms.*;


public class NewsListener implements MessageListener {
    private String readerName;
    private MessageProducer producer;
    private Session session;
    
    public void onMessage(Message message) {
        TextMessage msg = null;
        String topicName = null;
        String authorName = null;
        String firstPublication=null;
        String lastPublication=null;
        String source=null;
        String title=null;
       
        try {
            if (message instanceof TextMessage) {
                msg = (TextMessage) message;
                if(msg.getStringProperty("event_type").equals("publish") ){
               
                    if (msg.getJMSDestination() instanceof Topic)
                    {
                       topicName = ((Topic)msg.getJMSDestination()).getTopicName();
                    }                
                    authorName = msg.getStringProperty("author");
                    firstPublication=msg.getStringProperty("first_publication");
                    lastPublication=msg.getStringProperty("last_publication");
                    source=msg.getStringProperty("source");
                    title=msg.getStringProperty("title");

                    newsRead(authorName, title);
                    System.out.println("MESSAGE: "+msg.getText());
                    System.out.println("---------------------------------------------------------------------------");
                    System.out.println("Subscriber: "+readerName);
                    System.out.println("Publisher: "+authorName);
                    System.out.println("Title: "+title);
                    System.out.println("Source: "+source); 
                    System.out.println("First Publication: "+firstPublication);
                    System.out.println("Last Publication: "+lastPublication);
                    System.out.println("---------------------------------------------------------------------------");
                }
                else if(msg.getStringProperty("event_type").equals("delete")){
                     authorName = msg.getStringProperty("author");
                    
                     System.out.println("MESSAGE:" +msg.getText());
                     System.out.println("Author: "+authorName);
                     System.out.println("---------------------------------------------------------------------------");
                }
                else  if(msg.getStringProperty("event_type").equals("update") ){
                    
                   
                    authorName = msg.getStringProperty("author");
                    title=msg.getStringProperty("title");

                    System.out.println("MESSAGE: "+msg.getText());
                    System.out.println("---------------------------------------------------------------------------");
                    System.out.println("Publisher: "+authorName);
                    System.out.println("Title: "+title);
                    System.out.println("---------------------------------------------------------------------------");
                }
            } else {
               // System.err.println("Message is not a TextMessage");
            }
        } catch (JMSException e) {
            System.err.println("JMSException in onMessage(): " + e.toString());
        } catch (Throwable t) {
            System.err.println("Exception in onMessage():" + t.getMessage());
        }
    }
    
    public void newsRead (String authorName, String title) throws JMSException
    {
        TextMessage confirm = session.createTextMessage();
        confirm.setStringProperty(Constants.EDITOR, authorName);
        confirm.setStringProperty(Constants.TITLE, title);
        producer.send(confirm);
    }
    
   
    public NewsListener (String readerName, Topic confirmTopic, Connection connection) throws JMSException
    {
        this.readerName = readerName;
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        producer = session.createProducer(confirmTopic);
    }
}
