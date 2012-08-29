package models;

import javax.persistence.Entity;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Tag extends Model implements Comparable<Tag> {
 
    @Required
	public String name;
    
    public Tag(String name) {
    	this.name = name;
	}

	public String toString() {
        return name;
    }
    
    public static Tag findOrCreateByName(String name) {
        Tag tag = Tag.find("byName", name).first();
        if(tag == null) {
            tag = new Tag(name);
        }
        return tag;
    }
    
    public int compareTo(Tag otherTag) {
        return name.compareTo(otherTag.name);
    }
	
}
