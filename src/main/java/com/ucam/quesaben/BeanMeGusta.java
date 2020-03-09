/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucam.quesaben;

import twitter4j.User;

/**
 *
 * @author USUARIO
 */
public class BeanMeGusta {
    int numMeGustas = 0;
    User user;
    
    public int getNumMeGustas(){
        return numMeGustas;
    }

    public void setNumMeGustas(int num){
        numMeGustas = num;
    }
    
    public User getUser(){
        return user;
    }
    
    public void setUser(User usr){
        user = usr;
    }
    
}
