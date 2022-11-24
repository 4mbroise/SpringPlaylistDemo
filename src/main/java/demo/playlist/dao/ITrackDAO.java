package demo.playlist.dao;

import demo.playlist.model.Track;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;

public interface ITrackDAO {

    Track create(Track track);

    Collection<Track> findAll();

    Track findById(int id);

    Track update(int id, Track track);

    Track deleteById(int id);

}
