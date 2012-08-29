package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class VideoLaburante extends Model {

	public String nombre;

	public VideoLaburante(String nombre) {
		this.nombre = nombre;
	}

	public static VideoLaburante findOrCreateByName(String nombre) {
		VideoLaburante vl = VideoLaburante.find("nombre=?", nombre).first();
		if(vl == null) {
			vl = new VideoLaburante(nombre);
		}
		return vl;
	}

	@Override
	public String toString() {
		return this.nombre;
	}
}
