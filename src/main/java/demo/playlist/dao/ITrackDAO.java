package demo.playlist.dao;

import demo.playlist.model.Track;

import java.util.List;

public interface ITrackDAO {

    void create(Track track);

    List<Track> findAll();

    Track findById(int id);

    void update(int id, Track track);

    void removeById(int id);

}
