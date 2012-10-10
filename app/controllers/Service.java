package controllers;

import models.Pir;
import models.Video;

public class Service extends BaseService{
	
	public static void newUser(String email, String servlePort, String name){

		Pir pir = Pir.find("email", email).first();
		if(pir != null){
			play.Logger.info("No se crea al pir %s - %s por que ya existe", email, name);
			jsonOk("ya existe el pir");
			return;
		}
		pir = new Pir(name, email, Integer.parseInt(servlePort));
		if(pir.create()){
			play.Logger.info("Se crea al pir %s - %s", email, name);
			jsonOk("pir "+email+" creado");
		} else {
			play.Logger.info("No se pudo crear al pir %s - %s", email, name);
			jsonError("pir "+email+" no pudo ser creado");
		}
		return;
	}
	
	public static void getUser(String email){
		
		Pir pir = Pir.find("email", email).first();
		if(pir == null){
			jsonError("No existe el pir "+email);
			return;
		} else {
			jsonOk(pir);
			return;
		}
	}
	
	public static void newVideo(String videoId, String fileName, String sharedByEmail){
		
		Pir pir = Pir.find("email", sharedByEmail).first();
		
		if(pir == null){
			jsonError("No puede registrar un video un usuario no registrado");
			return;
		}
		
		Video video = Video.find("videoId", videoId).first();
		if(video != null){
			play.Logger.info("No se crea al video %s - %s por que ya existe", videoId, fileName);
			jsonOk("ya existe el pir");
			return;
		}
		
		video = new Video();
		video.videoId = videoId;
		video.nombre = fileName;
		video.compartidoPor = pir;
		
		if(video.create()){
			play.Logger.info("Se crea el video %s - %s", videoId, fileName);
			jsonOk("video "+videoId+" - "+fileName+" creado");
		} else {
			play.Logger.info("No se pudo crear el video %s - %s", videoId, fileName);
			jsonError("video "+videoId+" - "+fileName+" no pudo ser creado");
		}
		return;
	}
	
	public static void getVideo(String videoId){
		
		Video video = Video.find("videoId", videoId).first();
		
		if(video == null){
			jsonError("No existe el video "+videoId);
			return;
		} else {
			jsonOk(video);
			return;
		}
	}

}
