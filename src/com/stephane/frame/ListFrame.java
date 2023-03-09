package com.stephane.frame;

import com.stephane.entity.Crud;

import javax.swing.*;

public class ListFrame extends JFrame{
    private JPanel listPanel;

    public ListFrame(Crud action){
        initComponent();
//        actionListener();
    }

    public void initComponent(){
        setContentPane(listPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
