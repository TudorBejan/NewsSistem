/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.upt.pcbe;

import java.util.concurrent.atomic.AtomicInteger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 *
 * @author miha
 */
public class ReaderListener implements MessageListener{
    private Editor editor;
    private AtomicInteger counter;
    
    public ReaderListener(Editor editor){
        this.editor=editor;
        this.counter = new AtomicInteger(0);
    }
    
    public void onMessage(Message message) {
        TextMessage msg = null;
        try {
            if (message instanceof TextMessage) {
                msg = (TextMessage) message;
                System.out.println("Numarul de readeri "+counter.incrementAndGet());
                
            
            } else {
                System.err.println("Message is not a TextMessage");
            }
        
        } catch (Throwable t) {
            System.err.println("Exception in onMessage():" + t.getMessage());
        }
    }
    
   
    
}
