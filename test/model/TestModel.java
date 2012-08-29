package model;

import java.util.List;

import junit.framework.Assert;
import models.Equipo;
import models.Pir;
import models.Video;

import org.junit.Test;

import remote.BaseFunctionalTest;

public class TestModel extends BaseFunctionalTest {
	
	@Test 
	public void testVideoFullModel(){
		
		Equipo equipo = new Equipo();
		equipo.addVideoLaburante("carlos");
		Video video = new Video();
		video.nombre ="esperando la carrosa";
		video.videoId="test"+Math.random()*1000;
		video.save();
		
		
		
	}

}
