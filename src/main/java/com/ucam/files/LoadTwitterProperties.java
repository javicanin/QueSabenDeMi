package com.ucam.files;

import com.ucam.bean.KeyTokenTwitterBean;
import com.ucam.utils.Constantes;
import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

public class LoadTwitterProperties {
  
  public static void cargaPropiedades(String nombreFichero) throws Exception {
    try {
      Properties prop = new Properties();
      FileReader fr = new FileReader(System.getProperty("com.sun.aas.instanceRoot") + nombreFichero);
      prop.load(fr);

      Enumeration<Object> claves = prop.keys();
      while (claves.hasMoreElements()) {
        String clave = claves.nextElement().toString();
        switch (clave) {
          case "OAuthConsumerKey":
            KeyTokenTwitterBean.OAuthConsumerKey = prop.get(clave).toString();
            break;
          case "OAuthConsumerSecret":
            KeyTokenTwitterBean.OAuthConsumerSecret = prop.get(clave).toString();
            break;
          case "OAuthAccessToken":
            KeyTokenTwitterBean.OAuthAccessToken = prop.get(clave).toString();
            break;
          case "OAuthAccessTokenSecret":
            KeyTokenTwitterBean.OAuthAccessTokenSecret = prop.get(clave).toString();
            break;
        }
      }
    } catch (FileNotFoundException e) {
      throw new Exception("Error al cargar el fichero de propiedades:" + nombreFichero);
    }
  }
  
}
