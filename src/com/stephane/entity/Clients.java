/**
 *  Cette classe contient une liste de tous les clients enregistrés.
 *
 * @author Mill Zuckergates
 * @version 1.0
 */

package com.stephane.entity;

import java.util.ArrayList;

public class Clients{
    private static ArrayList<Client> listClients = new ArrayList<>();

    public static ArrayList<Client> getListClients(){
        return listClients;
    }
}
