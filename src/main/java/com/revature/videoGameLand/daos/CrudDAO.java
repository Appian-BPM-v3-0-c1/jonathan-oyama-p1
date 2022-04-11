package com.revature.videoGameLand.daos;

import java.util.List;

public interface CrudDAO <T> {
    int save(T newObj);
    List<T> findAll();
    T findById(String Id);
    boolean update(T updatedObj);
    boolean removeById(String Id);
}
