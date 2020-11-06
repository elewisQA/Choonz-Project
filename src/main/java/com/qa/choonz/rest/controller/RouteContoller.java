package com.qa.choonz.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouteContoller {
    @GetMapping(value = "/")
    public String index() {
        return "index.html";
    }

    @GetMapping(value = "/home")
    public String home() {
        return "index.html";
    }

    @GetMapping(value = "/tracks")
    public String tracks() {
        return "tracks.html";
    }

    @GetMapping(value = "/albums")
    public String albums() {
    	return "Albums.html";
    }
    
    @GetMapping(value = "/genres")
    public String genres() {
    	return "genres.html";
    }
    
    @GetMapping(value = "/playlists")
    public String playlists() {
    	return "playlists.html";
    }
    
    @GetMapping(value = "/artists")
    public String artists() {
    	return "artists.html";
    }
    
    // TODO expand this for mappings like "/artists/1" to point 
    // to viewArtist.html?=1 or something
}
