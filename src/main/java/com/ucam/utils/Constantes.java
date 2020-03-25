package com.ucam.utils;

public class Constantes {

  public static final String FICHERO_PROPIEDADES_FRASES = "/config/frases.properties";
  public static final String FICHERO_USUARIOS_GAMERS = "/config/gamers.txt";
  
  //Máximo número de lugares GeoLocation en la búsqueda de la API, a partir del campo libre userLocation
  public static final int MAX_PLACES_SEARCH_API = 5;
  //Número máximo de lugares tenidos en cuenta para el domicilio y lugares frecuentes
  public static final int MAX_PLACES_DOMICILIO = 10;
  //Número máximo de mejores amigos. Serán los que se muestren por pantalla
  public static final int MAX_BEST_FRIENDS = 10;
  //Número máximo que indica el número tweets a consultar en API sobre nuestros tweets más retwiteados
  //De esta consulta se tratará de extraer información sobre mejores amigos. Máximo ventana 15 minutos: 75
  public static final int MAX_RTW_SEARCH_API = 25;


  public static final String DISP_ANDROID = "Twitter for Android";
  public static final String DISP_IPHONE = "Twitter for iPhone";
  public static final String DISP_MOBIL_WEB = "Mobile Web";
  public static final String DISP_WEBSITES = "Twitter for Websites";
  public static final String DISP_OTROS = "Otro acceso Twitter";

  
  public static final String FRASE_VERIFICADO = "VERIFICADO";
  public static final String FRASE_NO_VERIFICADO = "NO-VERIFICADO";
  public static final String FRASE_PROTEGIDO = "PROTEGIDO";
  public static final String FRASE_NO_PROTEGIDO = "NO-PROTEGIDO";
  public static final String FRASE_INFO_NO_DISPONIBLE = "INFO-NO-DISPONIBLE";

  public static final String CHAR_SEPARADOR_NUM_FRASE = "_";
}
