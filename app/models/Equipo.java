package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;
@Entity
public class Equipo extends Model {
	
	@OneToMany(cascade=CascadeType.ALL)
	public List<VideoLaburante> videoLaburantes = new ArrayList<VideoLaburante>();
	
	public Equipo addVideoLaburante(String nombre) {
		videoLaburantes.add(VideoLaburante.findOrCreateByName(nombre));
        return this;
	}

}
