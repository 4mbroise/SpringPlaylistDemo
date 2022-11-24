package demo.playlist.dao;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import demo.playlist.model.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class TrackDao implements ITrackDAO{

    private List<Track> trackList;
    private int lastId;

    @Autowired
    public TrackDao(){
        this.lastId = 1;
        this.trackList = this.buildTrackList();
    }

    @Override
    public void create(Track track) {
        track.setId(this.lastId+1);
        this.trackList.add(track);
    }

    @Override
    public List<Track> findAll() {
        return this.trackList;
    }

    @Override
    public Track findById(int id) {
        return this.trackList.get(id);
    }

    @Override
    public void update(int id, Track track) {
        this.trackList.set(id, track);
    }

    @Override
    public void removeById(int id) {

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

        for(Track track:trackList){
            track.setId(this.lastId);
            this.lastId++;
        }

        return trackList;
    }

}
