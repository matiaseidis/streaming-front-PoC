package controllers;

import java.util.List;

import models.Pir;
import models.Video;
import play.mvc.With;

@With(Secure.class)
public class Pirs extends CRUD {

	@Check("pir")
	public static void misVideos(){

//    	Pir pir = Pir.find("email=?", Security.connected()).first();
		System.out.println("conn: "+Security.connected());
    	Pir pir = Pir.find("email", Security.connected()).first();
    	if(pir == null){
    		flash.error("no auth user");
    		Application.home();
    	}
    	List<Video> misVideos = pir.getVideosCompartidos();
    	render(misVideos);
    }
    
	@Check("pir")
    public static void borrar(Long id){
    	Video video = Video.findById(id);
    	video.delete();
    	flash.success("Borrado el video '%s' de id %s", video.nombre, video.videoId);
    	Application.home();
    }
}
