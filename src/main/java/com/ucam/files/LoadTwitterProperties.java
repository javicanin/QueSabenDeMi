package com.ucam.files;

import com.ucam.utils.Constantes;
import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

public class LoadTwitterProperties {

  private String OAuthConsumerKey;
  private String OAuthConsumerSecret;
  private String OAuthAccessToken;
  private String OAuthAccessTokenSecret;

  public LoadTwitterProperties() throws Exception {
    try {
      Properties prop = new Properties();
      FileReader fr = new FileReader(System.getProperty("com.sun.aas.instanceRoot") + Constantes.FICHERO_PROPIEDADES_TWITTER);
      prop.load(fr);

      Enumeration<Object> claves = prop.keys();
      while (claves.hasMoreElements()) {
        String clave = claves.nextElement().toString();
        switch (clave) {
          case "OAuthConsumerKey":
            OAuthConsumerKey = prop.get(clave).toString();
            break;
          case "OAuthConsumerSecret":
            OAuthConsumerSecret = prop.get(clave).toString();
            break;
          case "OAuthAccessToken":
            OAuthAccessToken = prop.get(clave).toString();
            break;
          case "OAuthAccessTokenSecret":
            OAuthAccessTokenSecret = prop.get(clave).toString();
            break;
        }
        
      }
    } catch (FileNotFoundException e) {
      throw new Exception("Error al cargar el fichero de propiedades:" + Constantes.FICHERO_PROPIEDADES_TWITTER);
    }
  }
  
  public String getOAuthConsumerKey() {
    return OAuthConsumerKey;
  }

  public void setOAuthConsumerKey(String OAuthConsumerKey) {
    this.OAuthConsumerKey = OAuthConsumerKey;
  }

  public String getOAuthConsumerSecret() {
    return OAuthConsumerSecret;
  }

  public void setOAuthConsumerSecret(String OAuthConsumerSecret) {
    this.OAuthConsumerSecret = OAuthConsumerSecret;
  }

  public String getOAuthAccessToken() {
    return OAuthAccessToken;
  }

  public void setOAuthAccessToken(String OAuthAccessToken) {
    this.OAuthAccessToken = OAuthAccessToken;
  }

  public String getOAuthAccessTokenSecret() {
    return OAuthAccessTokenSecret;
  }

  public void setOAuthAccessTokenSecret(String OAuthAccessTokenSecret) {
    this.OAuthAccessTokenSecret = OAuthAccessTokenSecret;
  }

}
