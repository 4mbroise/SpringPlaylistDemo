package demo.playlist.web.controller;

import demo.playlist.dao.ITrackDAO;
import demo.playlist.model.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TrackController {

    @Autowired
    private ITrackDAO dao;

    @ResponseBody
    @GetMapping(value = "/tracks")
    public List<Track> getTracks() {
        return this.dao.findAll();
    }


}
