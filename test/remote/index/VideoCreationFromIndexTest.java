package remote.index;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;
import models.Pir;
import models.Video;

import org.junit.Test;

import play.mvc.Http.Response;
import play.test.Fixtures;
import remote.BaseFunctionalTest;

public class VideoCreationFromIndexTest extends BaseFunctionalTest {
	
	@Test
	public void videoCreationFromIndexWorks(){
		
		/*
		 * limpiamos la db...
		 */
		Fixtures.deleteAllModels();
		
		int videosToCreate = 20;
		String fileNameBase = "test.video.file.name.";
		String videoId = "test.video.id.";
		String email = ".test.email@cachos.com";
		//creo usuarios 
		for(int i = 0; i<videosToCreate; i++) {
			Pir pir = new Pir(""+i+email, ""+i+email, 1234);
			pir.save();
		}
		//creo videos //TODO verque pinche si el user no existe
		
		String razorBack = ""+0+email;
		for(int i = 0; i<videosToCreate; i++) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("nombre", fileNameBase+i);
			params.put("videoId", videoId+i );
			params.put("email", razorBack);
			Response response = callService("/index/nuevoVideo", params);
			assertIsOk(response);
			Assert.assertTrue("Video "+videoId+i+" should exist", Video.find("videoId=?", videoId+i).first() != null);
		}
		Assert.assertEquals(videosToCreate, Video.count());
	}
		
}
