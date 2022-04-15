package com.revature.videoGameLand.services;

import com.revature.videoGameLand.daos.OInventoryDAO;

public class OInventoryService {
    private final OInventoryDAO oInventoryDAO;

    public OInventoryService(OInventoryDAO oInventoryDAO) {
        this.oInventoryDAO = oInventoryDAO;
    }

    public OInventoryDAO getOInventoryDAO() { return oInventoryDAO; }
}
