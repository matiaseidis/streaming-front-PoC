package controllers;

import models.Equipo;
import models.Video;

import com.google.gson.JsonObject;

public class Videos extends CRUD {

	public static void ver(Long id){
		Video video = Video.findById(id);
		render(video);
	}

	public static void nuevo(JsonObject body){

		Video video = new Video();
		try{
			video.nombre = body.get("nombre").getAsString();
			video.videoId = body.get("videoId").getAsString();
			//			video.creado = new Date(body.get("creado").getAsLong());
			video.creado = body.get("creado").getAsString();
			video.duracion = body.get("duracion").getAsInt();
			video.sinopsis = body.get("sinopsis").getAsString();
			video.puntuacion = body.get("puntuacion").getAsInt();
			video.poster = body.get("poster").getAsString();
//			video.compartidoPor = Pir.findById(body.get("compartidoPor").getAsString()/*.getAsLong()*/);
			video.equipo = Equipo.findById(body.get("equipo").getAsLong());
		} catch(Exception e) {
			play.Logger.error(e, "Error tratando de crear video nuevo %s", params.toString());
			renderJSON(e);
		}

		if(!validation.valid(video).ok ) {
			renderJSON(validation.errors());
		}
		
		video.save();

		renderJSON(video);
	}

	public static void detalle(Long id){

		Video video = Video.findById(id);

		if(video == null){
			flash.error("No existe el video");
			Application.home();
		}

		render(video);
	}


}
