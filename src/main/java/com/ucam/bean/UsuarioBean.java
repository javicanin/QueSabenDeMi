package com.ucam.bean;

import com.ucam.utils.Constantes;
import com.ucam.utils.FuncUtils;
import java.util.Date;
import twitter4j.User;

public class UsuarioBean{
    public long id;
    public String name;
    public String screenName;
    public String url;
    public String descripcion;
    public String lang;
    public String timeZone;
    public Date fechaCreacion;
    public String location;
    public int numTweets;
    public int numFriends;
    public int numFollowers;
    public int numFavoritos;
    public int numListas;
    public boolean protegido;
    public boolean geoEnabled;
    public boolean verified;
    public String urlImgProfile;
    public String urlImgProfileBig;
    
    public UsuarioBean() {
    }
    
    public String claveVolumenUso(){
      int volUso = numTweets + numFavoritos;
      if(FuncUtils.isBetween(volUso, 0, Constantes.USO_APENAS, 3))
        return Constantes.CLAVE_VOL_USO_APENAS;
      if(FuncUtils.isBetween(volUso, Constantes.USO_APENAS + 1, Constantes.USO_MODERADO, 3))
        return Constantes.CLAVE_VOL_USO_MODERADO;
      if(FuncUtils.isBetween(volUso, Constantes.USO_MODERADO + 1, Constantes.USO_INTENSIVO, 3))
        return Constantes.CLAVE_VOL_USO_INTENSIVO;
      if(volUso > Constantes.USO_INTENSIVO + 1)
        return Constantes.CLAVE_VOL_USO_EXTREMADO;
      return Constantes.CLAVE_INFO_NO_DISPONIBLE;
    }

    public String clavePerfilVerificado(){
      if(verified)
        return Constantes.CLAVE_VERIFICADO;
      if(!verified)
        return Constantes.CLAVE_NO_VERIFICADO;
      return Constantes.CLAVE_INFO_NO_DISPONIBLE;
    }

    public String clavePerfilProtegido(){
      if(protegido)
        return Constantes.CLAVE_PROTEGIDO;
      if(!protegido)
        return Constantes.CLAVE_NO_PROTEGIDO;
      return Constantes.CLAVE_INFO_NO_DISPONIBLE;
    }

    public String claveNumSeguidores(){
      int foll = numFollowers;
      if(foll == 0)
        return Constantes.CLAVE_NUM_SEGUIDORES_NINGUNO;
      if(FuncUtils.isBetween(foll, 0, Constantes.NUM_SEG_POCOS, 3))
        return Constantes.CLAVE_NUM_SEGUIDORES_POCOS;
      if(FuncUtils.isBetween(foll, Constantes.NUM_SEG_POCOS + 1, Constantes.NUM_SEG_ACEPTABLE, 3))
        return Constantes.CLAVE_NUM_SEGUIDORES_ACEPTABLE;
      if(FuncUtils.isBetween(foll, Constantes.NUM_SEG_ACEPTABLE + 1, Constantes.NUM_SEG_NOTABLE, 3))
        return Constantes.CLAVE_NUM_SEGUIDORES_NOTABLE;
      if(FuncUtils.isBetween(foll, Constantes.NUM_SEG_NOTABLE + 1, Constantes.NUM_SEG_MUCHOS, 3))
        return Constantes.CLAVE_NUM_SEGUIDORES_MUCHOS;
      if(foll > Constantes.NUM_SEG_MUCHOS)
        return Constantes.CLAVE_NUM_SEGUIDORES_MUCHISIMOS;
      return Constantes.CLAVE_INFO_NO_DISPONIBLE;
    }

    public String claveNumAmigos(){
      int frd = numFriends;
      if(frd == 0)
        return Constantes.CLAVE_NUM_AMIGOS_NINGUNO;
      if(FuncUtils.isBetween(frd, 0, Constantes.NUM_AMIGOS_POCOS, 3))
        return Constantes.CLAVE_NUM_AMIGOS_POCOS;
      if(FuncUtils.isBetween(frd, Constantes.NUM_AMIGOS_POCOS + 1, Constantes.NUM_AMIGOS_ACEPTABLE, 3))
        return Constantes.CLAVE_NUM_AMIGOS_ACEPTABLE;
      if(FuncUtils.isBetween(frd, Constantes.NUM_AMIGOS_ACEPTABLE + 1, Constantes.NUM_AMIGOS_NOTABLE, 3))
        return Constantes.CLAVE_NUM_AMIGOS_NOTABLE;
      if(FuncUtils.isBetween(frd, Constantes.NUM_AMIGOS_NOTABLE + 1, Constantes.NUM_AMIGOS_MUCHOS, 3))
        return Constantes.CLAVE_NUM_AMIGOS_MUCHOS;
      if(frd > Constantes.NUM_AMIGOS_MUCHOS)
        return Constantes.CLAVE_NUM_AMIGOS_MUCHISIMOS;
      return Constantes.CLAVE_INFO_NO_DISPONIBLE;
    }
    
    
    public UsuarioBean(User usuario) {
        id = usuario.getId();
        name = usuario.getName();
        screenName = usuario.getScreenName();
        url = usuario.getURL();
        descripcion = usuario.getDescription();
        lang = usuario.getLang();
        timeZone = usuario.getTimeZone();
        fechaCreacion = usuario.getCreatedAt();
        location = usuario.getLocation();
        numTweets = usuario.getStatusesCount();
        numFriends = usuario.getFriendsCount();
        numFollowers = usuario.getFollowersCount();
        numFavoritos = usuario.getFavouritesCount();
        numListas = usuario.getListedCount();
        protegido = usuario.isProtected();
        geoEnabled = usuario.isGeoEnabled();
        verified = usuario.isVerified();;
        urlImgProfile = usuario.getProfileImageURL();
        urlImgProfileBig = usuario.get400x400ProfileImageURL();
    }

    public long getId() {
        return id;
    }

    public void setId(long userId) {
        this.id = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String userName) {
        this.name = userName;
    }
    
    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String userScreenName) {
        this.screenName = userScreenName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String userLang) {
        this.lang = userLang;
    }
    
    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public int getNumTweets() {
        return numTweets;
    }

    public void setNumTweets(int tweets) {
        this.numTweets = tweets;
    }
    
    public int getNumFriends() {
        return numFriends;
    }

    public void setNumFriends(int friends) {
        this.numFriends = friends;
    }

    public int getNumFollowers() {
        return numFollowers;
    }

    public void setNumFollowers(int followers) {
        this.numFollowers = followers;
    }

    public int getNumFavoritos() {
        return numFavoritos;
    }

    public void setNumFavoritos(int favoritos) {
        this.numFavoritos = favoritos;
    }

    public int getNumListas() {
        return numListas;
    }

    public void setNumListas(int listas) {
        this.numListas = listas;
    }
    
    public boolean getProtegido() {
        return protegido;
    }

    public void setProtegido(boolean protegido) {
        this.protegido = protegido;
    }

    public boolean getGeoEnabled() {
        return geoEnabled;
    }

    public void setGeoEnabled(boolean geoEnabled) {
        this.geoEnabled = geoEnabled;
    }

    public boolean getVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getUrlImgProfile() {
        return urlImgProfile;
    }

    public void setUrlImgProfile(String urlImgProfile) {
        this.urlImgProfile = urlImgProfile;
    }

    public String getUrlImgProfileBig() {
        return urlImgProfileBig;
    }

    public void setUrlImgProfileBig(String urlImgProfileBig) {
        this.urlImgProfileBig = urlImgProfileBig;
    }

    

}
