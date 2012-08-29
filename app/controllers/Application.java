package controllers;

import java.util.List;

import models.Video;
import play.mvc.Controller;

public class Application extends Controller {

	public static void buscar(String searchParam){
		List<Video> results = Video.findTaggedWith(searchParam);
		render(results);
	}
	
	public static void index() {
        render();
    }
    
    public static void home() {
    	
    	List<Video> videos = Video.find("publicado=?", true).fetch(100); 
    	render(videos);
    }
    
    public static void buscar() {
        render();
    }
}
