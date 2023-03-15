/**
 *  Cette classe contient une liste de tous les prospects enregistrés.
 *
 * @author Mill Zuckergates
 * @version 1.0
 */

package com.stephane.entity;

import java.util.ArrayList;

public class Prospects{
    private static ArrayList<Prospect> listProspects = new ArrayList<>();

    public static ArrayList<Prospect> getListProspects(){
        return listProspects;
    }
}
