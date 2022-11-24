package demo.playlist.web.controller;

import demo.playlist.dao.ITrackDAO;
import demo.playlist.model.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

@Controller
public class TrackController {

    @Autowired
    private ITrackDAO dao;

    @ResponseBody
    @GetMapping(value = "/tracks")
    public ResponseEntity<Collection<Track>> getTracks() {
        return new ResponseEntity<Collection<Track>>(this.dao.findAll(), HttpStatus.FOUND);
    }

    @ResponseBody
    @GetMapping(value = "/track/{id}")
    public ResponseEntity<Track> getTrack(@PathVariable int id) {
        if( dao.findById(id) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The track with the ID +"+id+" does not exist");
        }
        return new ResponseEntity<Track>(this.dao.findById(id), HttpStatus.FOUND);
    }

    @ResponseBody
    @PostMapping(value = "/tracks")
    public ResponseEntity<Track> addTrack(@RequestBody Track track) {

        //Je vérifie que l'ID n'existe pas déjà sinon ça craint
        if(dao.findById(track.getId()) != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A track with the ID "+track.getId()+" already exists");
        }

        return new ResponseEntity<Track>(this.dao.create(track), HttpStatus.CREATED);
    }


}
