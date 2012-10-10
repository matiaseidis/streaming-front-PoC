package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Serie extends Model {
	
	@Required @NotNull @NotEmpty
	public String nombre;
	@OneToMany
	public List<Temporada> temporadas = new ArrayList<Temporada>();
}
