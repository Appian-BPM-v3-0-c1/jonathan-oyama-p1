package com.revature.videoGameLand.services;

import com.revature.videoGameLand.daos.VideoGameDAO;

public class VideoGameService {
    private final VideoGameDAO videoGameDAO;

    public VideoGameService(VideoGameDAO videoGameDAO) {
        this.videoGameDAO = videoGameDAO;
    }

    public VideoGameDAO getVideoGameDAO() {
        return videoGameDAO;
    }
}
