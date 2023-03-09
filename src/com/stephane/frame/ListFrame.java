package com.stephane.frame;

import com.stephane.entity.Client;
import com.stephane.entity.Clients;
import com.stephane.entity.Crud;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ListFrame extends JFrame{
    private JPanel listPanel;
    private JTable listTable;
    private DefaultTableModel listModel;
    private JButton retourAuMenuButton;

    public ListFrame(Crud action){
        initComponent();
        actionListener();
        initTable();
    }

    public void initComponent(){
        setContentPane(listPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionListener(){

    }

    public void initTable(){

    }
}
