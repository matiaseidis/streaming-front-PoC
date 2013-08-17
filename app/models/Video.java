package models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;
@Entity
public class Video extends Model {

	@Required @NotNull @NotEmpty
	public String nombre;		
	@Required @Unique @NotNull @Basic(optional=false) 
	public String videoId;
	public String rottenTomatoesId;
	public String imdbId;
	@Required public String creado;
	public int duracion;
	@Lob 
	public String sinopsis;
	public int puntuacion;
	public String poster;
	@Required @OneToOne(cascade=CascadeType.REFRESH)
	public Pir compartidoPor;
	
	@OneToOne(cascade=CascadeType.ALL)
	public Equipo equipo = new Equipo();
	@ManyToMany(cascade=CascadeType.PERSIST)
    public Set<Tag> tags = new HashSet<Tag>();
    public boolean publicado;
    
    public Video tagItWith(String name) {
        tags.add(Tag.findOrCreateByName(name));
        return this;
    }
    
    public static List<Video> findTaggedWith(String tag) {
        return Video.find(
            "select distinct v from Video v join v.tags as t where t.name like ?",
            "%"+tag+"%"
        ).fetch(20);
    }
	
	@Override
	public String toString(){
		return nombre;
	}
	
}
