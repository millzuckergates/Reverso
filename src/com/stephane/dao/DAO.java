package com.stephane.dao;
import com.stephane.exceptions.ReversoException;
import java.util.ArrayList;

public abstract class DAO<T> {
    public abstract void save(Integer id, T obj) throws ReversoException, DAOException;

    public abstract void delete(int id) throws DAOException;
//
    public abstract ArrayList<T> findAll() throws ReversoException, DAOException;
//
    public abstract void find(int id) throws ReversoException, DAOException;
}
