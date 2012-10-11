package base;

import java.util.List;

import models.Pir;
import models.Video;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Bootstrap extends Job {

	public void doJob() {
		// Check if the database is empty
//		if(Pir.count() == 0) {
//			Fixtures.loadModels("initial-data.yml");
//		}

//		crearDummyVideos(20);
	}

	private void crearDummyVideos(int cantidad) {

		int videosToCreate = cantidad;
		String fileNameBase = "test.video.file.name.";
		String videoId = "test.video.id.";
		String email = ".test.email3@cachos.com";
		String nombre = ".nombre";
		//creo usuarios 
		for(int i = 0; i<videosToCreate; i++) {
			Pir pir = Pir.find("email", ""+i+email).first();
			
			if(pir == null){
				pir = new Pir(""+i+nombre, ""+i+email, 1234);
				pir.save();
				System.out.println(pir.nombre);
			}
		}

		String razorBack = ""+0+email;
		for(int i = 0; i<videosToCreate; i++) {
			Video video = Video.find("videoId", videoId+i).first(); 
			
			if(video == null){

				video = new Video();
				video.nombre = fileNameBase+i;
				video.videoId = videoId+i;
				video.compartidoPor = Pir.find("email", razorBack).first();
				video.save();

			}
		}

	}

}