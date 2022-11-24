package demo.playlist.dao;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import demo.playlist.model.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

@Component
public class TrackDao implements ITrackDAO{

    private Map<Integer,Track> trackList;
    //private int lastId;

    @Autowired
    public TrackDao(){
        this.trackList = new HashMap<Integer, Track>();
        for(Track track:this.buildTrackList()){
            this.trackList.put(track.getId(), track);
        }
    }

    @Override
    public Track create(Track track) throws HttpClientErrorException {
        return this.trackList.put(track.getId(), track);
    }

    @Override
    public Collection<Track> findAll() {
        return this.trackList.values();
    }

    @Override
    public Track findById(int id) {
        return this.trackList.get(id);
    }

    @Override
    public void update(int id, Track track) {
        this.removeById(id);
        this.trackList.put(track.getId(), track);
    }

    @Override
    public void removeById(int id) {
        this.trackList.remove(id);

    }

    private List<Track> buildTrackList() {
        Reader reader = null;
        try {
            File trackCsvFile = new ClassPathResource("static/tracks.csv").getFile();
            reader = Files.newBufferedReader(trackCsvFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert reader != null;
        CsvToBean<Track> csvToBean = new CsvToBeanBuilder<Track>(reader)
                .withSkipLines(1)       //Il faut que j'ignore la ligne 1 sinon il va voulour me convertir les entêtes
                .withType(Track.class)
                .build();

        List<Track> trackList = csvToBean.parse();

        return trackList;
    }

}
