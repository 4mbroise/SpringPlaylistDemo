package demo.playlist;

import demo.playlist.dao.ITrackDAO;
import demo.playlist.dao.TrackDao;
import demo.playlist.model.Track;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "demo.playlist")
public class PlaylistApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaylistApplication.class, args);

	}

	/*
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext context){
		return args -> {
			ITrackDAO trackDAO;

			trackDAO = context.getBean(TrackDao.class);

			for(Track track:trackDAO.findAll()){
				System.out.println(track.toString());
			}

		};
	}*/

}
