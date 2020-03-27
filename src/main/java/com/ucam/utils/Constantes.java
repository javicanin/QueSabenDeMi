package com.ucam.utils;

public class Constantes {

  public static final String FICHERO_PROPIEDADES_FRASES = "/config/frases.properties";
  public static final String FICHERO_PROPIEDADES_DISP = "/config/dispositivos.properties";
  public static final String FICHERO_USUARIOS_GAMERS = "/config/gamers.txt";
  
  //Máximo número de lugares GeoLocation en la búsqueda de la API, a partir del campo libre userLocation
  public static final int MAX_PLACES_SEARCH_API = 5;
  //Número máximo de lugares tenidos en cuenta para el domicilio y lugares frecuentes
  public static final int MAX_PLACES_DOMICILIO = 10;
  //Número máximo de mejores amigos. Serán los que se muestren por pantalla
  public static final int MAX_BEST_FRIENDS = 25;
  //Número máximo que indica el número tweets a consultar en API sobre nuestros tweets más retwiteados
  //De esta consulta se tratará de extraer información sobre mejores amigos. Máximo ventana 15 minutos: 75
  public static final int MAX_RTW_SEARCH_API = 25;


  public static final String DISP_ANDROID = "ANDROID";
  public static final String DISP_APPLE = "APPLE";
  public static final String DISP_ORDENADOR = "ORDENADOR";
  public static final String DISP_NAVEGADOR_WEB = "NAVEGADOR-WEB";
  public static final String DISP_OTRAS_REDES = "OTRAS-REDES-SOCIALES";
  public static final String DISP_APLICACIONES = "APLICACIONES";
  public static final String TXT_DISP_ANDROID = "Android";
  public static final String TXT_DISP_APPLE = "Apple";
  public static final String TXT_DISP_ORDENADOR = "Ordenador";
  public static final String TXT_DISP_NAVEGADOR_WEB = "Navegador Web";
  public static final String TXT_DISP_OTRAS_REDES = "Otras redes sociales";
  public static final String TXT_DISP_APLICACIONES = "Aplicaciones";
  

  
  public static final String FRASE_VERIFICADO = "VERIFICADO";
  public static final String FRASE_NO_VERIFICADO = "NO-VERIFICADO";
  public static final String FRASE_PROTEGIDO = "PROTEGIDO";
  public static final String FRASE_NO_PROTEGIDO = "NO-PROTEGIDO";
  public static final String FRASE_INFO_NO_DISPONIBLE = "INFO-NO-DISPONIBLE";

  public static final String CHAR_SEPARADOR_PROPERTIES = "_";
}
