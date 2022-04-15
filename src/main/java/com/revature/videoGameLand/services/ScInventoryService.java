package com.revature.videoGameLand.services;

import com.revature.videoGameLand.daos.ScInventoryDAO;

public class ScInventoryService {
    private final ScInventoryDAO scInventoryDAO;

    public ScInventoryService(ScInventoryDAO scInventoryDAO) {
        this.scInventoryDAO = scInventoryDAO;
    }

    public ScInventoryDAO getScInventoryDAO() {
        return scInventoryDAO;
    }
}
