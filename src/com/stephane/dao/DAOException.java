package com.stephane.dao;

public class DAOException extends Exception{
    private int gravity;

    public DAOException(String message, int gravity){
        super(message);
        this.setGravity(gravity);
    }

    public DAOException(String message) {
        super(message);
    }
    public int getGravity(){
        return gravity;
    }

    public void setGravity(int gravity){
        this.gravity = gravity;
    }
}
