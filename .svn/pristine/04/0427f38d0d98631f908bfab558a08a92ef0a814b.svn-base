/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.upt.pcbe;

/**
 *
 * @author Roxi
 */

import java.util.Calendar;
import java.util.Locale;
import javax.jms.Topic;

public class News {

	private Calendar firstPublication;
	private Calendar lastPublication;
	private String source;
	private String title;
	private Editor author;
	//private String topic;
        private Topic topic;
	private String context;
	
	public News(Editor author,String title, Topic topic, String source,String context){
		this.author = author;
		this.title = title;
		this.topic = topic;
		this.source = source;
		this.context = context;
		this.firstPublication = Calendar.getInstance();
		this.lastPublication = Calendar.getInstance();	
	}
	
	public String getSource() {
		return source;
	}

	public String getTitle() {
		return title;
	}


	public String getAuthor() {
		return this.author.getName();
	}


	public Topic getTopic() {
		return topic;
	}


	public String getContext() {
		return context;
	}
        
        public String getFirstPublication(){
                String str=firstPublication.get(Calendar.YEAR)+":"+(firstPublication.get(Calendar.MONTH)+1)+":"+
                        firstPublication.get(Calendar.DAY_OF_MONTH)+":"+firstPublication.get(Calendar.HOUR)+":"+
                        firstPublication.get(Calendar.MINUTE)+":"+firstPublication.get(Calendar.SECOND)+":"+
                        firstPublication.get(Calendar.MILLISECOND);
                return str;
        }
        
        public String getLastPublication(){
                String str=lastPublication.get(Calendar.YEAR)+":"+(lastPublication.get(Calendar.MONTH)+1)+":"+
                        lastPublication.get(Calendar.DAY_OF_MONTH)+":"+lastPublication.get(Calendar.HOUR)+":"+
                        lastPublication.get(Calendar.MINUTE)+":"+lastPublication.get(Calendar.SECOND)+":"+
                        lastPublication.get(Calendar.MILLISECOND);
                return str;
        }
}
