package ro.upt.pcbe;

import java.util.Calendar;

public class News {

	private Calendar firstPublication;
	private Calendar lastPublication;
	private String source;
	private String title;
	private Editor author;
	private String topic;
	private String context;
	
	public News(Editor author,String title, String topic, String source,String context){
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


	public String getTopic() {
		return topic;
	}


	public String getContext() {
		return context;
	}

}
