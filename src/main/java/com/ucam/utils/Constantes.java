package com.ucam.utils;

public class Constantes {

  public static final String FICHERO_PROPIEDADES_TWITTER = "/config/twitter.properties";
  public static final String FICHERO_PROPIEDADES_FRASES = "/config/frases.properties";
  public static final String FICHERO_PROPIEDADES_DISP = "/config/dispositivos.properties";
  public static final String FICHERO_USUARIOS_GAMERS = "/config/gamers.txt";
  public static final String FICHERO_USUARIOS_POLITICA = "/config/politica.txt";
  public static final String FICHERO_USUARIOS_DEPORTES = "/config/deportistas.txt";
  
  //Máximo número de lugares GeoLocation en la búsqueda de la API, a partir del campo libre userLocation
  public static final int MAX_PLACES_SEARCH_API = 5;
  //Número máximo de lugares tenidos en cuenta para el domicilio y lugares frecuentes
  public static final int MAX_PLACES_DOMICILIO = 10;
  //Número máximo de mejores amigos. Serán los que se muestren por pantalla
  public static final int MAX_BEST_FRIENDS = 25;
  //Número máximo que indica el número tweets a consultar en API sobre nuestros tweets más retwiteados
  //De esta consulta se tratará de extraer información sobre mejores amigos. Máximo ventana 15 minutos: 75
  public static final int MAX_RTW_SEARCH_API = 25;
  
  public static final int USO_APENAS = 100;
  public static final int USO_MODERADO = 500;
  public static final int USO_INTENSIVO = 2000;
  public static final int NUM_SEG_POCOS = 100;
  public static final int NUM_SEG_ACEPTABLE = 1000;
  public static final int NUM_SEG_NOTABLE = 5000;
  public static final int NUM_SEG_MUCHOS = 15000;
  public static final int NUM_AMIGOS_POCOS = 50;
  public static final int NUM_AMIGOS_ACEPTABLE = 500;
  public static final int NUM_AMIGOS_NOTABLE = 2500;
  public static final int NUM_AMIGOS_MUCHOS = 5000;
  public static final int NUM_GAMERS_CIERTO = 5;
  public static final int NUM_GAMERS_BASTANTES = 12;
  public static final int NUM_POLITICOS_CIERTO = 5;
  public static final int NUM_POLITICOS_BASTANTES = 12;
  public static final int NUM_DEPORTES_CIERTO = 5;
  public static final int NUM_DEPORTES_BASTANTES = 12;
  
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

  public static final String FRANJA_USO_HORAS_MADRUGADA = "madrugada";
  public static final String FRANJA_USO_HORAS_MANIANA = "mañana";
  public static final String FRANJA_USO_HORAS_MEDIODIA = "mediodia";
  public static final String FRANJA_USO_HORAS_TARDE = "tarde";
  public static final String FRANJA_USO_HORAS_NOCHE = "noche";
  

  public static final String CLAVE_VERIFICADO = "VERIFICADO";
  public static final String CLAVE_NO_VERIFICADO = "NO-VERIFICADO";
  public static final String CLAVE_PROTEGIDO = "PROTEGIDO";
  public static final String CLAVE_NO_PROTEGIDO = "NO-PROTEGIDO";
  public static final String CLAVE_INFO_NO_DISPONIBLE = "INFO-NO-DISPONIBLE";
  public static final String CLAVE_VOL_USO = "VOLUMEN-USO";
  public static final String CLAVE_VOL_USO_APENAS = "VOLUMEN-USO-APENAS";
  public static final String CLAVE_VOL_USO_MODERADO = "VOLUMEN-USO-MODERADO";
  public static final String CLAVE_VOL_USO_INTENSIVO = "VOLUMEN-USO-INTENSIVO";
  public static final String CLAVE_VOL_USO_EXTREMADO = "VOLUMEN-USO-EXTREMADO";
  public static final String CLAVE_NUM_SEGUIDORES = "NUM-SEGUIDORES";
  public static final String CLAVE_NUM_SEGUIDORES_NINGUNO = "NUM-SEGUIDORES-NINGUNO";
  public static final String CLAVE_NUM_SEGUIDORES_POCOS = "NUM-SEGUIDORES-POCOS";
  public static final String CLAVE_NUM_SEGUIDORES_ACEPTABLE = "NUM-SEGUIDORES-ACEPTABLE";
  public static final String CLAVE_NUM_SEGUIDORES_NOTABLE = "NUM-SEGUIDORES-NOTABLE";
  public static final String CLAVE_NUM_SEGUIDORES_MUCHOS = "NUM-SEGUIDORES-MUCHOS";
  public static final String CLAVE_NUM_SEGUIDORES_MUCHISIMOS = "NUM-SEGUIDORES-MUCHISIMOS";
  public static final String CLAVE_NUM_AMIGOS = "NUM-AMIGOS";
  public static final String CLAVE_NUM_AMIGOS_NINGUNO = "NUM-AMIGOS-NINGUNO";
  public static final String CLAVE_NUM_AMIGOS_POCOS = "NUM-AMIGOS-POCOS";
  public static final String CLAVE_NUM_AMIGOS_ACEPTABLE = "NUM-AMIGOS-ACEPTABLE";
  public static final String CLAVE_NUM_AMIGOS_NOTABLE = "NUM-AMIGOS-NOTABLE";
  public static final String CLAVE_NUM_AMIGOS_MUCHOS = "NUM-AMIGOS-MUCHOS";
  public static final String CLAVE_NUM_AMIGOS_MUCHISIMOS = "NUM-AMIGOS-MUCHISIMOS";
  
  public static final String CLAVE_GUSTOS_GAMERS = "GUSTOS-GAMERS";
  public static final String CLAVE_GUSTOS_GAMERS_NADA = "GUSTOS-GAMERS-NADA";
  public static final String CLAVE_GUSTOS_GAMERS_CIERTO = "GUSTOS-GAMERS-CIERTO";
  public static final String CLAVE_GUSTOS_GAMERS_BASTANTE = "GUSTOS-GAMERS-BASTANTE";
  public static final String CLAVE_GUSTOS_GAMERS_MUCHO = "GUSTOS-GAMERS-MUCHO";
  public static final String CLAVE_GUSTOS_POLITICA = "GUSTOS-POLITICA";
  public static final String CLAVE_GUSTOS_POLITICA_NADA = "GUSTOS-POLITICA-NADA";
  public static final String CLAVE_GUSTOS_POLITICA_CIERTO = "GUSTOS-POLITICA-CIERTO";
  public static final String CLAVE_GUSTOS_POLITICA_BASTANTE = "GUSTOS-POLITICA-BASTANTE";
  public static final String CLAVE_GUSTOS_POLITICA_MUCHO = "GUSTOS-POLITICA-MUCHO";
  public static final String CLAVE_GUSTOS_DEPORTES = "GUSTOS-DEPORTES";
  public static final String CLAVE_GUSTOS_DEPORTES_NADA = "GUSTOS-DEPORTES-NADA";
  public static final String CLAVE_GUSTOS_DEPORTES_CIERTO = "GUSTOS-DEPORTES-CIERTO";
  public static final String CLAVE_GUSTOS_DEPORTES_BASTANTE = "GUSTOS-DEPORTES-BASTANTE";
  public static final String CLAVE_GUSTOS_DEPORTES_MUCHO = "GUSTOS-DEPORTES-MUCHO";


  public static final String CHAR_SEPARADOR_PROPERTIES = "_";
}
