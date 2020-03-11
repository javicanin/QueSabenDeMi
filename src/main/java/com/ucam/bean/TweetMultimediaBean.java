/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucam.bean;

import twitter4j.MediaEntity;
import twitter4j.Status;

/**
 *
 * @author USUARIO
 */
public class TweetMultimediaBean {
    public long id;
    public String tipo;
    public int numRetweets = 0;
    public int numFavoritos = 0;
    public int numRetyFav = 0;
    public String urlArchivo;
    public String urlTweet;
    
    
    
    public TweetMultimediaBean() {
    }
    
    public TweetMultimediaBean(Status st) {
        id = st.getId();
        tipo = st.getMediaEntities()[0].getType();
        numRetweets = st.getRetweetCount();
        numFavoritos = st.getFavoriteCount();
        numRetyFav = numRetweets + numFavoritos;
        urlArchivo = st.getMediaEntities()[0].getMediaURL();
        urlTweet = st.getMediaEntities()[0].getURL();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumRetweets() {
        return numRetweets;
    }

    public void setNumRetweets(int numRetweets) {
        this.numRetweets = numRetweets;
    }

    public int getNumFavoritos() {
        return numFavoritos;
    }

    public void setNumFavoritos(int numFavoritos) {
        this.numFavoritos = numFavoritos;
    }

    public int getNumRetyFav() {
        return numRetyFav;
    }

    public void setNumRetyFav(int numRetyFav) {
        this.numRetyFav = numRetyFav;
    }

    public String getUrlArchivo() {
        return urlArchivo;
    }

    public void setUrlArchivo(String urlArchivo) {
        this.urlArchivo = urlArchivo;
    }

    public String getUrlTweet() {
        return urlTweet;
    }

    public void setUrlTweet(String urlTw) {
        this.urlTweet = urlTw;
    }
    
}
