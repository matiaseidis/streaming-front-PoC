package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Video;
import models.VideoLaburante;

import org.apache.commons.lang.StringUtils;

import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Ficha extends Controller {

	
	@Check("pir")
	 public static void actualizar(){
			String videoId = params.get("videoId");
			String rottenTomatoesId  = params.get("rottenTomatoesId");
			String imdbId = params.get("imdbId");
			String nombre = params.get("title");
			String posterUrl = params.get("posterUrl");
			String year = params.get("year");
			String runtime = params.get("runtime");
			String synopsis = params.get("synopsis");
			String reparto =  params.get("reparto");
			
			Video video = new Video();
			video.imdbId = imdbId;
			video.rottenTomatoesId = rottenTomatoesId;
			video.nombre = nombre;
			video.videoId = videoId;
			video.creado = year;
			video.sinopsis = synopsis;
			video.poster = posterUrl;
			video.duracion= StringUtils.isNotEmpty(runtime) ? Integer.valueOf(runtime) : 0;
			
			render(video, reparto);	
		}

	    
	    public static void guardar(){
	    	
	    	System.out.println("reparto:" + params.get("reparto"));
	    	
			
	    	String videoId = params.get("videoId");

			Video video = Video.find("videoId=?", videoId).first();
			video.imdbId = params.get("imdbId");
			video.rottenTomatoesId = params.get("rottenTomatoesId");
			video.nombre = params.get("nombre");
			video.creado = params.get("creado");
			video.sinopsis = params.get("sinopsis");
			video.poster = params.get("poster");
			video.duracion= StringUtils.isNotEmpty( params.get("duracion")) ? Integer.valueOf( params.get("duracion")) : 0;
			video.publicado = true;
			
			validation.required(video.nombre);
			
			List<String> fields = new ArrayList<String>();
			fields.add(video.nombre);
			if(video.equipo!=null){
				for(VideoLaburante vl : video.equipo.videoLaburantes){
					fields.add(vl.nombre);
				}
			}
			
			for(String field : fields){
				video.tagItWith(field);
			}
			
			String reparto = params.get("reparto");
			if(StringUtils.isNotEmpty(reparto)){
				for(String actor: reparto.split("\\+") ) {
					if(StringUtils.isNotEmpty(actor) ) {
						video.equipo.addVideoLaburante(actor);
						video.tagItWith(actor);
					}
				}
			}

			video.save();
		    flash.success("El video %s fue creado y esta publicado", video.nombre);
		    Pirs.misVideos();
		}
}
