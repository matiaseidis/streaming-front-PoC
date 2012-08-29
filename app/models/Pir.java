package models;

import java.util.List;

import javax.persistence.Entity;

import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.Model;
@Entity
public class Pir extends Model {
	
	@Required
	public String nombre;
	@Required @Email
	public String email;
	@Required
	public String ip;
	@Required
	public int puerto;

	public Pir(String nombre, String email, String ip, int puerto) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.ip = ip;
		this.puerto = puerto;
	}

	public List<Video> getVideosCompartidos(){
		
		List<Video> videosCompartidos = Video.find("compartidoPor=?", this).fetch(100);
		return videosCompartidos;
	}

	@Override
	public String toString(){
		return nombre;
	}
}
