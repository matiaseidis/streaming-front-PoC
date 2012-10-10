package remote;

import java.util.HashMap;
import java.util.Map;

import models.Pir;

import org.junit.Ignore;
import org.junit.Test;

import play.libs.WS;
import play.libs.WS.HttpResponse;
import play.test.UnitTest;
@Ignore
public class CreateVideo extends UnitTest {

	@Test @Ignore
	public void createVideoRemotelyShouldWork() {

		Pir user = new Pir("test-pir", "test@pir.com", 8080);
		user.save();

		Map<String,String> params = new HashMap<String,String>();
		String nombre = "nombre-"+Math.random()*1000;
		
		
		
		params.put("nombre", nombre);
		params.put("videoId", nombre+nombre);
		params.put("creado", "0");
		params.put("duracion", "1000");
		params.put("sinopsis", "bla");
		params.put("puntuacion", "3");
		params.put("poster", "url");
		params.put("compartidoPor", ""+user.getId());
		
		String body = "{'nombre': 'caca','videoId': 'cccc','creado': '0','duracion': '1000','sinopsis': 'bla','puntuacio': '3','poster': 'url','compartidoPor': '1','equipo':'1'}";
		
		HttpResponse response = WS.url("http://localhost:9000/videos/nuevo").body(body).post();
		
		System.out.println(response.getString());
	}

}
