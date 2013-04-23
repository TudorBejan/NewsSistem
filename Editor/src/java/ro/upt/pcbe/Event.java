/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.upt.pcbe;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Roxi
 */
public class Event {
    private String id;
    private String source;
    private Object content;
    private ConcurrentHashMap<String,String> properties;  //properties: autor, topic
    
    public Event(String id, String source, Object content){
        this.id=id;
        this.source=source;
        this.content=content;
        this.properties = new ConcurrentHashMap<String, String>();
    }
    
    public void addProperty(String name, String value){
        this.properties.put(name, value);
    }
    
    public String getProperty(String name){
        return this.properties.get(name);
    }
    
}
