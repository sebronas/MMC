package com.example.mmc.DAO;

// Data Access Object

import java.util.List;

public interface DAOInterface<T> {

    public int addData(T data);
    public int delData(T data);
    public int updateData(T data);

    List<T> getAll();
}
