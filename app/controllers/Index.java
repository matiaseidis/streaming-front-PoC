package controllers;

import models.Pir;
import models.Video;
import net.sf.oval.constraint.NotBlank;
import play.data.validation.Email;
import play.data.validation.Required;

public class Index extends BaseService {
	
	public static void nuevoVideo(@Required @NotBlank String nombre, @Required @NotBlank String videoId, @Required @NotBlank @Email String email){
		
		Pir pir = Pir.find("email=?", email).first();
		if(pir == null){
			jsonError(String.format("no existe el pir %s", email));
		}
		
		Video video = new Video();
		
		video.nombre = nombre;
		video.videoId = videoId;
		video.compartidoPor = pir;
		
		video.save();
		
		jsonOk(video);
	}
	
	public static void nuevoUsuario(){}

}
