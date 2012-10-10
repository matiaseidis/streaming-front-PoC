package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Temporada extends Model {
	
	public int numero;
	@OneToMany
	public List<Capitulo> capitulos= new ArrayList<Capitulo>();

}
