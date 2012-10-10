package models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Capitulo extends Model {
	
	public int numero;
	@OneToOne
	public Video video;

}
