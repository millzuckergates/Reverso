package com.stephane.entity;

import java.util.ArrayList;

public class Clients{
    private static ArrayList<Client> listClients = new ArrayList<>();

    public static ArrayList<Client> getListClients(){
        return listClients;
    }
}
