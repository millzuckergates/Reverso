package com.stephane.entity;

import java.util.ArrayList;

public class Prospects{
    private static ArrayList<Prospect> listProspects = new ArrayList<>();

    public static ArrayList<Prospect> getListProspects(){
        return listProspects;
    }
}
