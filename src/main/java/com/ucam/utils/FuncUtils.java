package com.ucam.utils;

import com.google.gson.Gson;
import com.ucam.bean.DispositivosPropertiesBean;
import com.ucam.bean.GeoJsonFeatures;
import com.ucam.bean.GeoJsonPlacesBean;
import com.ucam.bean.GeometryGeoJson;
import com.ucam.bean.InfoUsoTwitterBean;
import com.ucam.bean.PropertiesGeoJson;
import com.ucam.bean.TweetMultimediaBean;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import twitter4j.GeoLocation;
import twitter4j.MediaEntity;
import twitter4j.PagableResponseList;
import twitter4j.Place;
import twitter4j.Status;
import twitter4j.User;

public class FuncUtils {

  public static String claveNumGamers(int numGamers) {
    int gmr = numGamers;
    if (gmr == 0) {
      return Constantes.CLAVE_GUSTOS_GAMERS_NADA;
    }
    if (FuncUtils.isBetween(gmr, 0, Constantes.NUM_GAMERS_CIERTO, 3)) {
      return Constantes.CLAVE_GUSTOS_GAMERS_CIERTO;
    }
    if (FuncUtils.isBetween(gmr, Constantes.NUM_GAMERS_CIERTO + 1, Constantes.NUM_GAMERS_BASTANTES, 3)) {
      return Constantes.CLAVE_GUSTOS_GAMERS_BASTANTE;
    }
    if (gmr > Constantes.NUM_GAMERS_BASTANTES) {
      return Constantes.CLAVE_GUSTOS_GAMERS_MUCHO;
    }
    return Constantes.CLAVE_INFO_NO_DISPONIBLE;
  }

  public static String claveNumPoliticos(int numPoliticos) {
    int plt = numPoliticos;
    if (plt == 0) {
      return Constantes.CLAVE_GUSTOS_POLITICA_NADA;
    }
    if (FuncUtils.isBetween(plt, 0, Constantes.NUM_POLITICOS_CIERTO, 3)) {
      return Constantes.CLAVE_GUSTOS_POLITICA_CIERTO;
    }
    if (FuncUtils.isBetween(plt, Constantes.NUM_POLITICOS_CIERTO + 1, Constantes.NUM_POLITICOS_BASTANTES, 3)) {
      return Constantes.CLAVE_GUSTOS_POLITICA_BASTANTE;
    }
    if (plt > Constantes.NUM_POLITICOS_BASTANTES) {
      return Constantes.CLAVE_GUSTOS_POLITICA_MUCHO;
    }
    return Constantes.CLAVE_INFO_NO_DISPONIBLE;
  }

  public static String claveNumDeportes(int numDeportes) {
    int dpt = numDeportes;
    if (dpt == 0) {
      return Constantes.CLAVE_GUSTOS_DEPORTES_NADA;
    }
    if (FuncUtils.isBetween(dpt, 0, Constantes.NUM_DEPORTES_CIERTO, 3)) {
      return Constantes.CLAVE_GUSTOS_DEPORTES_CIERTO;
    }
    if (FuncUtils.isBetween(dpt, Constantes.NUM_DEPORTES_CIERTO + 1, Constantes.NUM_DEPORTES_BASTANTES, 3)) {
      return Constantes.CLAVE_GUSTOS_DEPORTES_BASTANTE;
    }
    if (dpt > Constantes.NUM_DEPORTES_BASTANTES) {
      return Constantes.CLAVE_GUSTOS_DEPORTES_MUCHO;
    }
    return Constantes.CLAVE_INFO_NO_DISPONIBLE;
  }

  public static boolean isBetween(int num, int inf, int sup, int included) {
    switch (included) {
      case 0:
        return (num > inf) && (num < sup);
      case 1:
        return (num >= inf) && (num < sup);
      case 2:
        return (num > inf) && (num <= sup);
      case 3:
        return (num >= inf) && (num <= sup);
    }
    return false;
  }

  public static int getRandomNumberInRange(int min, int max) {
    Random randomGenerator = new Random();
    return randomGenerator.nextInt(max) + min;
  }

  public static String getStringGsonLabelDataDisp(HashMap<String, Integer> dispositivos, String dataOLabel) {
    ArrayList list = new ArrayList();
    for (Map.Entry<String, Integer> dsp : dispositivos.entrySet()) {
      if ("data".equals(dataOLabel)) {
        list.add(dsp.getValue());
      } else {
        list.add(dsp.getKey());
      }

    }
    return new Gson().toJson(list);
  }

  public static Map<String, Integer> ordenarMapStrIntPorValor(Map<String, Integer> estructura) {
    return estructura.entrySet()
            .stream()
            .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
  }

  public static Map<String, Float> ordenarMapStrFloatPorValor(Map<String, Float> estructura) {
    return estructura.entrySet()
            .stream()
            .sorted((Map.Entry.<String, Float>comparingByValue().reversed()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
  }

  public static Map<Long, Integer> ordenarMapLongIntPorValor(Map<Long, Integer> estructura) {
    return estructura.entrySet()
            .stream()
            .sorted((Map.Entry.<Long, Integer>comparingByValue().reversed()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
  }

  public static boolean validaPatron(String cadena, String patronAVerificar) {
    Pattern patron = Pattern.compile(patronAVerificar);/* Ejs: "[0-9]{7,8}(-[A-Z])?" -- "[A-Za-z0-9 [Ññ]']*" */
    Matcher cumplePatron = patron.matcher(cadena);
    return cumplePatron.matches();
  }

  public static String validaCampoPerfilTwitter(String campo, boolean obligatorio) {
    if (obligatorio && campo.isEmpty()) {
      return "El campo es obligatorio";
    }
    if (!obligatorio && campo.isEmpty()) {
      return "";
    }
    if(campo.length()>15){
      return "Longitud del campo no permitida. Máximo 15 caracteres";
    }
    if (!validaPatron(campo, "([0-9a-zA-Z_]*)"))  {
      return "Formato de campo no válido. Permitido: letras (a-z,A-Z), números (0-9) y _ (guión bajo)";
    }
    return "";
  }

  //Metodo que elimina caracteres especiales para evitar los ataques de cross site scripting
  public static String reemplazosEvitaXSS(String campo) {
    try {
      if (campo != null) {
        campo = campo.replaceAll("\\\\", "\\\\"); //le añadimos un espacio al final para permitir la barra en campos libres pero evitar urls y rutas
        campo = campo.replaceAll("\\<", "");
        campo = campo.replaceAll("\\>", "");
        campo = campo.replaceAll("\"", "");
        //campo = campo.replaceAll("\'", "");
        campo = campo.replaceAll("\\&", "");
        campo = campo.replaceAll("!", "");
        campo = campo.replaceAll("\\$", "");
        campo = campo.replaceAll("%", "");
        campo = campo.replaceAll("\\(", "");
        campo = campo.replaceAll("\\)", "");
        campo = campo.replaceAll("=", "");
        campo = campo.replaceAll("\\+", "");
        campo = campo.replaceAll("\\{", "");
        campo = campo.replaceAll("\\}", "");
        campo = campo.replaceAll("\\[", "");
        campo = campo.replaceAll("\\]", "");
        campo = campo.replaceAll(";", "");
        campo = campo.replaceAll("\\|", "");
        campo = campo.replaceAll("\\?", "");
        campo = campo.replaceAll("/", "/"); //le añadimos un espacio al final para permitir la barra en campos libres pero evitar urls y rutas
        //la palabra script escrita de cualquier forma
        campo = campo.replaceAll("[sS][cC][rR][iI][pP][tT]", "");
      }
    } catch (Exception e) {
      System.out.println("ERROR REEMPLAZANDO CAMPOS PARA EVITAR XSS. CAMPO: " + campo + e.getMessage());
    }
    return campo;
  }

  public static List<User> getSeguidoresMutuos(PagableResponseList<User> followers, PagableResponseList<User> friends) {
    List<User> segMutuos = new ArrayList<User>();
    System.out.println("################################################");
    System.out.println("------------- SEGUIDORES MUTUOS -------------");
    if (followers != null && friends != null) {
      for (User flw : followers) {
        for (User frd : friends) {
          if (flw.getId() == frd.getId()) {
            segMutuos.add(flw);
          }
        }
      }
    }
    return segMutuos;
  }
  
  public static ArrayList getIdsSeguidoresMutuos(ArrayList idsFollowers, ArrayList idsFriends) {
    ArrayList segMutuos = new ArrayList();
    if (idsFollowers.size() < idsFriends.size()) {
      for (int i = 0; i < idsFollowers.size(); i++) {
        if (idsFriends.contains(idsFollowers.get(i))) {
          segMutuos.add(idsFollowers.get(i));
        }
      }
    } else {
      for (int i = 0; i < idsFriends.size(); i++) {
        if (idsFollowers.contains(idsFriends.get(i))) {
          segMutuos.add(idsFriends.get(i));
        }
      }
    }
    return segMutuos;
  }
  
  public static double[] getCentralPointOfPolygon(GeoLocation[][] geoLoc) {
    double[] centralPoint = new double[2];
    try {
      if (geoLoc != null) {
        double lat1 = geoLoc[0][0].getLatitude();
        double lon1 = geoLoc[0][0].getLongitude();
        double lat2 = geoLoc[0][2].getLatitude();
        double lon2 = geoLoc[0][2].getLongitude();
        if (lat1 != lat2 && lon1 != lon2) {
          centralPoint[0] = lon1 - ((lon1 - lon2) / 2);
          centralPoint[1] = lat2 - ((lat2 - lat1) / 2);
        } else {
          centralPoint[0] = lon1;
          centralPoint[1] = lat1;
        }
      }
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(FuncUtils.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
    return centralPoint;
  }
  
  public static GeoJsonPlacesBean getGeoJsonFeatures(List<Status> statuses) {
    GeoJsonPlacesBean gJsPl = new GeoJsonPlacesBean();
    System.out.println("--------------- GEOJSON PLACES ---------------");
    try {
      Place lugar;
      for (Status st : statuses) {
        //Este condicional porque retweets sin comentario no permiten indicar ubicación, igual que me gustas
        //En cualquier caso el Place sería del tweet retweeteado
        if (!st.isRetweeted()) {
          lugar = st.getPlace();
          if (lugar != null) {
            PropertiesGeoJson prop = new PropertiesGeoJson();
            prop.nombre = lugar.getName();
            prop.nombreCompleto = lugar.getFullName();
            prop.tipo = lugar.getPlaceType();
            prop.pais = lugar.getCountry();
            String fecha = new SimpleDateFormat("EEEEEEEEE, dd/MM/yyyy HH:mm:ss").format(st.getCreatedAt());
            prop.fechaTweet = fecha;

            GeometryGeoJson geom = new GeometryGeoJson();
            geom.type = "Point";
            GeoLocation[][] boxCoords = lugar.getBoundingBoxCoordinates();
            geom.coordinates = FuncUtils.getCentralPointOfPolygon(boxCoords);

            GeoJsonFeatures feat = new GeoJsonFeatures();
            feat.type = "Feature";
            feat.properties = prop;
            feat.geometry = geom;

            gJsPl.features.add(feat);
          }
        }
      }
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(FuncUtils.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }

    return gJsPl;
  }
  
  public static ArrayList getUserHobbiesInFriends(HashMap<Long, User> usrHobb, ArrayList idsFriends) {
    ArrayList frdHob = new ArrayList();
    System.out.println("-------------- USER HOBBIES SEGUIDOS -------------");
    try {
      for (Map.Entry<Long, User> usr : usrHobb.entrySet()) {
        if (idsFriends.contains(usr.getKey())) {
          frdHob.add(usr);
          //System.out.println("User hobbie seguido: " + usr.getValue().getScreenName());
        }
      }
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(FuncUtils.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
    //System.out.println("Nº de user hobbies seguidos: " + frdHob.size());
    return frdHob;
  }

  //La estructura que contiene los IDs y el recuento de lugares de tweets y favoritos tiene que estar ordenada en forma decreciente por el valor
  public static LinkedHashMap<String, String> getLugarDomicilio(HashMap<String, Integer> idsPlacesOrden, HashMap<String, Place> placesTwYFv, List<Place> userLocations, User usr, int xMatch) {
    LinkedHashMap<String, String> posiblesDomicilios = new LinkedHashMap();
    try {
      //Para todos los lugares obtenidos de la consulta sobre la localización libre que establece el usuario
      //se comprueba si coinciden con alguno de los lugares geolocalizados desde donde twitea
      for (Map.Entry<String, Integer> idPlc : idsPlacesOrden.entrySet()) {
        for (Place plc : userLocations) {
          if (!(idPlc.getKey() == null ? plc.getId() == null : idPlc.getKey().equals(plc.getId()))) {
          } else {
            if (posiblesDomicilios.size() <= xMatch) {
              posiblesDomicilios.put(plc.getId(), plc.getFullName());
            } else {
              break;
            }
          }
        }
      }
      //Si no se encuentra coincidencia entre los lugares desde donde twitea y la localización que ha dado para su perfil,
      //en ese caso nos fijamos en los lugares desde donde más twitea y extraemos hasta los X primeros
      if (posiblesDomicilios.size() < xMatch) {
        for (Map.Entry<String, Integer> idPlc : idsPlacesOrden.entrySet()) {
          if (posiblesDomicilios.size() <= xMatch) {
            posiblesDomicilios.put(idPlc.getKey(), placesTwYFv.get(idPlc.getKey()).getFullName());
          } else {
            break;
          }
        }
      }
      //Si aun no hemos podido determinar un lugar de domicilio, ahora tenemos en cuenta sólo los X resultados de userLocations
      if (posiblesDomicilios.size() < xMatch) {
        if (userLocations != null) {
          for (Place plc : userLocations) {
            if (posiblesDomicilios.size() <= xMatch) {
              posiblesDomicilios.put(plc.getId(), plc.getFullName());
            } else {
              break;
            }
          }
        }
      }
      //Si después de las comprobaciones anteriores no se identifica el domicilio se utiliza de forma textual el location del perfil
      if (posiblesDomicilios.size() < xMatch && !"".equals(usr.getLocation())) {
        posiblesDomicilios.put("LOCATION", usr.getLocation());
      }
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(FuncUtils.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
    return posiblesDomicilios;
  }

  public static LinkedHashMap getListaSegMutuosReteetsMe2(HashMap<Long, Integer> idsUsersRetweetsMe, ArrayList segMutuos) {
    LinkedHashMap<Long, Integer> listaSegMutuosRetweetsMe = new LinkedHashMap<>();
    System.out.println("------------ RETWEETS ME SEG MUTUOS -------------");
    for (Map.Entry<Long, Integer> ids : idsUsersRetweetsMe.entrySet()) {
      if (segMutuos.contains(ids.getKey())) {
        listaSegMutuosRetweetsMe.put(ids.getKey(), ids.getValue());
      }
    }
    //Ordena la estructura por el número de Retweets
    listaSegMutuosRetweetsMe = (LinkedHashMap<Long, Integer>) FuncUtils.ordenarMapLongIntPorValor(listaSegMutuosRetweetsMe);
    return listaSegMutuosRetweetsMe;
  }
  
  public static HashMap<String, TweetMultimediaBean> getTweetsMultimediaFavoritos(List<Status> tweets) {
    HashMap<String, TweetMultimediaBean> twsConMasMultimedia = new HashMap();
    System.out.println("--------- TWEETS MULTIMEDIA FAVORITOS ------------");
    try {
      for (Status tw : tweets) {
        //getRetweetedStatus() tiene contenido cuando se trata de un retweet sin comentario, los cuales no pueden tener foto
        //por este motivo hay que analizar todos aquellos que pueden tener foto getRetweetedStatus() == null
        //Además extraemos aquellos que hayan sido retwiteados o favoritos al menos una vez
        if ((tw.getRetweetCount() > 0 || tw.getFavoriteCount() > 0) && tw.getRetweetedStatus() == null) {
          MediaEntity[] mMedia = tw.getMediaEntities();
          if (mMedia.length != 0) {
            TweetMultimediaBean twMultimB = new TweetMultimediaBean(tw);
            String tipo = mMedia[0].getType();
            switch (tipo) {
              //Foto
              case "photo":
                if (!twsConMasMultimedia.containsKey("photo")) {
                  twsConMasMultimedia.put("photo", twMultimB);
                } else {
                  if (twsConMasMultimedia.get("photo").numRetyFav < tw.getRetweetCount() + tw.getFavoriteCount()) {
                    twsConMasMultimedia.put("photo", twMultimB);
                  }
                }
                break;
              //Gif
              case "animated_gif":
                if (!twsConMasMultimedia.containsKey("animated_gif")) {
                  twsConMasMultimedia.put("animated_gif", twMultimB);
                } else {
                  if (twsConMasMultimedia.get("animated_gif").numRetyFav < tw.getRetweetCount() + tw.getFavoriteCount()) {
                    twsConMasMultimedia.put("animated_gif", twMultimB);
                  }
                }
                break;
              //Video
              case "video":
                if (!twsConMasMultimedia.containsKey("video")) {
                  twsConMasMultimedia.put("video", twMultimB);
                } else {
                  if (twsConMasMultimedia.get("video").numRetyFav < tw.getRetweetCount() + tw.getFavoriteCount()) {
                    twsConMasMultimedia.put("video", twMultimB);
                  }
                }
                break;
            }
          }
        }
      }
      //Ordena la estructura por el número de Retweets
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(FuncUtils.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
    return twsConMasMultimedia;
  }

  public static int[] getMayorMenorHashMap(HashMap<Integer, Integer> usoAnnios) {
    int[] mayorMenor = new int[2];
    try {
      Date annioActual = new Date();
      String strAnnio = new SimpleDateFormat("yyyy").format(annioActual);
      int annio = Integer.parseInt(strAnnio);

      mayorMenor[0] = annio;
      mayorMenor[1] = annio;

      for (Map.Entry<Integer, Integer> usAn : usoAnnios.entrySet()) {
        if (usAn.getKey() < mayorMenor[0]) {
          mayorMenor[0] = usAn.getKey();
        }
        if (usAn.getKey() > mayorMenor[1]) {
          mayorMenor[1] = usAn.getKey();
        }
      }
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(FuncUtils.class
              .getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
    return mayorMenor;
  }

  public static LinkedHashMap getIdsUserAmigos2(LinkedHashMap<Long, Integer> idsUsrRtw, LinkedHashMap<Long, Integer> idsUsrFv, LinkedHashMap<Long, Integer> idsUsrRtwMe, ArrayList segMutuos, int maxAmigos) {
    LinkedHashMap<Long, Integer> posicionamientos = new LinkedHashMap();
    System.out.println("------------ IDs USERS AMIGOS -------------");

    int pos = 0;
    for (Map.Entry<Long, Integer> usrRtw : idsUsrRtw.entrySet()) {
      int inversaPosicion = idsUsrRtw.size() - pos;
      posicionamientos.put(usrRtw.getKey(), inversaPosicion + usrRtw.getValue());
      pos++;
    }
    pos = 0;
    for (Map.Entry<Long, Integer> usrFv : idsUsrFv.entrySet()) {
      int inversaPosicion = idsUsrFv.size() - pos;
      if (posicionamientos.containsKey(usrFv.getKey())) {
        int posExistente = posicionamientos.get(usrFv.getKey());
        posicionamientos.put(usrFv.getKey(), posExistente + inversaPosicion + usrFv.getValue());
      } else {
        posicionamientos.put(usrFv.getKey(), inversaPosicion + usrFv.getValue());
      }
      pos++;
    }
    pos = 0;
    for (Map.Entry<Long, Integer> usrRtwMe : idsUsrRtwMe.entrySet()) {
      int inversaPosicion = idsUsrRtwMe.size() - pos;
      if (posicionamientos.containsKey(usrRtwMe.getKey())) {
        int posExistente = posicionamientos.get(usrRtwMe.getKey());
        posicionamientos.put(usrRtwMe.getKey(), posExistente + inversaPosicion + usrRtwMe.getValue());
      } else {
        posicionamientos.put(usrRtwMe.getKey(), inversaPosicion + usrRtwMe.getValue());
      }
      pos++;
    }
    posicionamientos = (LinkedHashMap<Long, Integer>) FuncUtils.ordenarMapLongIntPorValor(posicionamientos);

    //Si el número de usuarios con los que se ha tenido interacción no alcanza el número mínimo pasado por parámetro
    //Entonces se extraeran como máximo este número de usuarios de entre los seguidores mutuos.
    for (int i = 0; i < segMutuos.size(); i++) {
      if (posicionamientos.size() < maxAmigos) {
        posicionamientos.put((Long) segMutuos.get(i), 1);
      } else {
        break;
      }
    }
    return posicionamientos;
  }
  
  public static InfoUsoTwitterBean getInfoUsoTwitter(List<Status> tweets, List<Status> favoritos) {
    InfoUsoTwitterBean usoTw = new InfoUsoTwitterBean();

    HashMap<Integer, Integer> usoAnnios = new HashMap<>();

    System.out.println("---------------- USO TWITTER -----------------");
    try {
      int totalItem = tweets.size() + favoritos.size();
      Date date;
      int numHora;
      int numDia;
      int numMes;
      int numAnnio;
      int valor = 0;

      for (Status tw : tweets) {
        date = tw.getCreatedAt();
        String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss u").format(date);
        System.out.println(fecha);
        numHora = Integer.parseInt(fecha.substring(11, 13));
        numDia = Integer.parseInt(fecha.substring(20, fecha.length()));
        numMes = Integer.parseInt(fecha.substring(3, 5));
        numAnnio = Integer.parseInt(fecha.substring(6, 10));
        valor = (Integer) usoTw.horas.get(numHora);
        valor++;
        usoTw.horas.set(numHora, valor);
        usoTw.porcentHoras.set(numHora, (float) (valor * 100) / totalItem);

        valor = (int) usoTw.franjaHoras.get(usoTw.getIntFranjaFromLabelHora(numHora));
        valor++;
        usoTw.franjaHoras.set(usoTw.getIntFranjaFromLabelHora(numHora), valor);
        usoTw.porcentFranjaHoras.set(usoTw.getIntFranjaFromLabelHora(numHora), (float) (valor * 100) / totalItem);

        valor = (Integer) usoTw.dias.get(numDia - 1);
        valor++;
        usoTw.dias.set(numDia - 1, valor);
        usoTw.porcentDias.set(numDia - 1, (float) (valor * 100) / totalItem);

        valor = (Integer) usoTw.meses.get(numMes - 1);
        valor++;
        usoTw.meses.set(numMes - 1, valor);
        usoTw.porcentMeses.set(numMes - 1, (float) (valor * 100) / totalItem);

        if (!usoAnnios.containsKey(numAnnio)) {
          usoAnnios.put(numAnnio, 1);
        } else {
          valor = usoAnnios.get(numAnnio);
          valor++;
          usoAnnios.put(numAnnio, valor);
        }
      }
      for (Status fv : favoritos) {
        date = fv.getCreatedAt();
        String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss u").format(date);
        numHora = Integer.parseInt(fecha.substring(11, 13));
        numDia = Integer.parseInt(fecha.substring(20, fecha.length()));
        numMes = Integer.parseInt(fecha.substring(3, 5));
        numAnnio = Integer.parseInt(fecha.substring(6, 10));
        valor = (Integer) usoTw.horas.get(numHora);
        valor++;
        usoTw.horas.set(numHora, valor);
        usoTw.porcentHoras.set(numHora, (float) (valor * 100) / totalItem);

        valor = (int) usoTw.franjaHoras.get(usoTw.getIntFranjaFromLabelHora(numHora));
        valor++;
        usoTw.franjaHoras.set(usoTw.getIntFranjaFromLabelHora(numHora), valor);
        usoTw.porcentFranjaHoras.set(usoTw.getIntFranjaFromLabelHora(numHora), (float) (valor * 100) / totalItem);

        valor = (Integer) usoTw.dias.get(numDia - 1);
        valor++;
        usoTw.dias.set(numDia - 1, valor);
        usoTw.porcentDias.set(numDia - 1, (float) (valor * 100) / totalItem);

        valor = (Integer) usoTw.meses.get(numMes - 1);
        valor++;
        usoTw.meses.set(numMes - 1, valor);
        usoTw.porcentMeses.set(numMes - 1, (float) (valor * 100) / totalItem);

        if (!usoAnnios.containsKey(numAnnio)) {
          usoAnnios.put(numAnnio, 1);
        } else {
          valor = usoAnnios.get(numAnnio);
          valor++;
          usoAnnios.put(numAnnio, valor);
        }
      }

      int[] mayorMenor = FuncUtils.getMayorMenorHashMap(usoAnnios);
      for (int i = mayorMenor[0]; i <= mayorMenor[1]; i++) {
        usoTw.labelsAnnios.add(i);
        if (usoAnnios.containsKey(i)) {
          usoTw.annios.add(usoAnnios.get(i));
          usoTw.porcentAnnios.add((float) (usoAnnios.get(i) * 100) / totalItem);

        } else {
          usoTw.annios.add(0);
          usoTw.porcentAnnios.add(0.0F);
        }
      }

    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(FuncUtils.class
              .getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }

    return usoTw;
  }

  public static HashMap getUserInteracciones(HashMap<Long, Integer> idsUsrRtw, HashMap<Long, Integer> idsUsrFv, HashMap<Long, Integer> idsUsrRtwMe) {
    HashMap<Long, Integer> interacciones = new HashMap();
    System.out.println("---------------- INTERACCIONES ---------------");
    try {
      int cont = 0;
      for (Map.Entry<Long, Integer> usrRtw : idsUsrRtw.entrySet()) {
        if (interacciones.containsKey(usrRtw.getKey())) {
          cont = interacciones.get(usrRtw.getKey()) + usrRtw.getValue();
          interacciones.replace(usrRtw.getKey(), cont);
        } else {
          interacciones.put(usrRtw.getKey(), usrRtw.getValue());
        }
      }
      for (Map.Entry<Long, Integer> usrFv : idsUsrFv.entrySet()) {
        if (interacciones.containsKey(usrFv.getKey())) {
          cont = interacciones.get(usrFv.getKey()) + usrFv.getValue();
          interacciones.replace(usrFv.getKey(), cont);
        } else {
          interacciones.put(usrFv.getKey(), usrFv.getValue());
        }
      }
      for (Map.Entry<Long, Integer> usrRtwMe : idsUsrRtwMe.entrySet()) {
        if (interacciones.containsKey(usrRtwMe.getKey())) {
          cont = interacciones.get(usrRtwMe.getKey()) + usrRtwMe.getValue();
          interacciones.replace(usrRtwMe.getKey(), cont);
        } else {
          interacciones.put(usrRtwMe.getKey(), usrRtwMe.getValue());
        }
      }

    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(FuncUtils.class
              .getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
    return interacciones;
  }

  public static LinkedHashMap getIdsUserAmigos(HashMap<Long, Integer> idsUsrRtw, HashMap<Long, Integer> idsUsrFv, HashMap<Long, Integer> idsUsrRtwMe) {
    LinkedHashMap<Long, Integer> idsAmigos = new LinkedHashMap();
    System.out.println("------------ IDs USERS AMIGOS -------------");
    LinkedHashMap<Long, Integer> idsAmigos3 = new LinkedHashMap();
    for (Map.Entry<Long, Integer> usrRtw : idsUsrRtw.entrySet()) {
      //Si el usuario se encuentra en las tres listas
      if (idsUsrFv.containsKey(usrRtw.getKey()) && idsUsrRtwMe.containsKey(usrRtw.getKey())) {
        idsAmigos3.put(usrRtw.getKey(), usrRtw.getValue() + idsUsrFv.get(usrRtw.getKey()) + idsUsrRtwMe.get(usrRtw.getKey()));
      }
    }
    idsAmigos3 = (LinkedHashMap<Long, Integer>) FuncUtils.ordenarMapLongIntPorValor(idsAmigos3);

    LinkedHashMap<Long, Integer> idsAmigos2 = new LinkedHashMap();
    //Si el usuario está en idsUsrRtw y en una de las otras dos
    for (Map.Entry<Long, Integer> usrRtw : idsUsrRtw.entrySet()) {
      if (idsUsrFv.containsKey(usrRtw.getKey()) && !idsAmigos3.containsKey(usrRtw.getKey())) {
        idsAmigos2.put(usrRtw.getKey(), usrRtw.getValue() + idsUsrFv.get(usrRtw.getKey()));
      }
      if (idsUsrRtwMe.containsKey(usrRtw.getKey()) && !idsAmigos3.containsKey(usrRtw.getKey())) {
        idsAmigos2.put(usrRtw.getKey(), usrRtw.getValue() + idsUsrRtwMe.get(usrRtw.getKey()));
      }
    }
    //Si el usuario está en idsUsrFv y en idsUsrRtwMe
    for (Map.Entry<Long, Integer> usrFv : idsUsrFv.entrySet()) {
      if (idsUsrRtwMe.containsKey(usrFv.getKey()) && !idsAmigos3.containsKey(usrFv.getKey())) {
        idsAmigos2.put(usrFv.getKey(), usrFv.getValue() + idsUsrFv.get(usrFv.getKey()));
      }
    }
    idsAmigos2 = (LinkedHashMap<Long, Integer>) FuncUtils.ordenarMapLongIntPorValor(idsAmigos2);

    LinkedHashMap<Long, Integer> idsAmigos1 = new LinkedHashMap();
    //Si el usuario está sólo en una de las listas
    for (Map.Entry<Long, Integer> usrRtw : idsUsrRtw.entrySet()) {
      if (!idsAmigos2.containsKey(usrRtw.getKey()) && !idsAmigos3.containsKey(usrRtw.getKey())) {
        idsAmigos1.put(usrRtw.getKey(), usrRtw.getValue());
      }
    }
    for (Map.Entry<Long, Integer> usrFv : idsUsrFv.entrySet()) {
      if (!idsAmigos2.containsKey(usrFv.getKey()) && !idsAmigos3.containsKey(usrFv.getKey())) {
        idsAmigos1.put(usrFv.getKey(), usrFv.getValue());
      }
    }
    for (Map.Entry<Long, Integer> usrRtwMe : idsUsrRtwMe.entrySet()) {
      if (!idsAmigos2.containsKey(usrRtwMe.getKey()) && !idsAmigos3.containsKey(usrRtwMe.getKey())) {
        idsAmigos1.put(usrRtwMe.getKey(), usrRtwMe.getValue());
      }
    }
    idsAmigos1 = (LinkedHashMap<Long, Integer>) FuncUtils.ordenarMapLongIntPorValor(idsAmigos1);

    for (Map.Entry<Long, Integer> fr3 : idsAmigos3.entrySet()) {
      idsAmigos.put(fr3.getKey(), fr3.getValue());
    }
    for (Map.Entry<Long, Integer> fr2 : idsAmigos2.entrySet()) {
      idsAmigos.put(fr2.getKey(), fr2.getValue());
    }
    for (Map.Entry<Long, Integer> fr1 : idsAmigos1.entrySet()) {
      idsAmigos.put(fr1.getKey(), fr1.getValue());
    }

    return idsAmigos;
  }

  public static HashMap getListaSegMutuosReteetsMe(HashMap<Long, Integer> idsUsersRetweetsMe, HashMap segMutuos) {
    HashMap<Long, Integer> listaSegMutuosRetweetsMe = new HashMap<>();
    System.out.println("------------ RETWEETS ME SEG MUTUOS -------------");
    for (Map.Entry<Long, Integer> ids : idsUsersRetweetsMe.entrySet()) {
      if (segMutuos.containsKey(ids.getKey())) {
        listaSegMutuosRetweetsMe.put(ids.getKey(), ids.getValue());
      }
    }
    //Ordena la estructura por el número de Retweets
    listaSegMutuosRetweetsMe = (HashMap<Long, Integer>) FuncUtils.ordenarMapLongIntPorValor(listaSegMutuosRetweetsMe);
    return listaSegMutuosRetweetsMe;
  }
  
  public static long[] getMaxArrayIdsRetweets(HashMap<Long, Integer> idsRetweets, int losXMasRetweeteados) {
    int arraySize = losXMasRetweeteados;
    if (idsRetweets.size() < losXMasRetweeteados) {
      arraySize = idsRetweets.size();
    }
    long[] idsRtw = new long[arraySize];
    int cont = 0;
    for (Map.Entry<Long, Integer> ids : idsRetweets.entrySet()) {
      if (cont < arraySize) {
        idsRtw[cont] = ids.getKey();
        cont = cont + 1;
      } else {
        break;
      }
    }
    return idsRtw;
  }

  public static LinkedHashMap getUserIdsFavoritos(List<Status> meGustas, ArrayList segMutuos) {
    LinkedHashMap<Long, Integer> usrMeGustas = new LinkedHashMap<>();
    System.out.println("------------ USER IDs FAVORITOS ------------");
    try {
      for (Status st : meGustas) {
        if (segMutuos.contains(st.getUser().getId())) {
          Long userId = st.getUser().getId();
          //System.out.println(st.getUser().getScreenName());
          if (usrMeGustas.containsKey(userId)) {
            int valor = (int) usrMeGustas.get(userId);
            valor = valor + 1;
            usrMeGustas.replace(userId, valor);
          } else {
            usrMeGustas.put(userId, 1);
          }
        }
      }
      //Ordena la estructura por el número de Me Gusta
      usrMeGustas = (LinkedHashMap<Long, Integer>) FuncUtils.ordenarMapLongIntPorValor(usrMeGustas);
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(FuncUtils.class
              .getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }

    return usrMeGustas;
  }

  public static HashMap getIdsRetweetsMe(String user, List<Status> status) {
    HashMap<Long, Integer> numsRetweets = new HashMap<Long, Integer>();
    System.out.println("----------------- IDS RETWEETS ME -----------------");
    try {
      for (Status st : status) {
        //Con getRetweetedStatus() == null obtenemos los tweets propios que no son retweet
        //Además extraemos aquellos que hayan sido retwiteados al menos una vez
        if (st.getRetweetCount() > 0 && st.getRetweetedStatus() == null) {
          long idRetweet = st.getId();
          numsRetweets.put(idRetweet, st.getRetweetCount());
        }
      }
      //Ordena la estructura por el número de Retweets
      numsRetweets = (HashMap<Long, Integer>) FuncUtils.ordenarMapLongIntPorValor(numsRetweets);
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(FuncUtils.class
              .getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
    return numsRetweets;
  }

  public static HashMap getListaUsersMeGustas(List<Status> meGustas, List<User> segMutuos) {
    HashMap<String, Integer> listaMeGustas = new HashMap<>();
    System.out.println("----------------- USER ME GUSTAS -----------------");
    for (Status st : meGustas) {
      if (segMutuos.contains(st.getUser())) {
        String userName = st.getUser().getScreenName();
        //System.out.println(st.getUser().getScreenName());
        if (listaMeGustas.containsKey(userName)) {
          int valor = (int) listaMeGustas.get(userName);
          valor = valor + 1;
          listaMeGustas.replace(userName, valor);
        } else {
          listaMeGustas.put(userName, 1);
        }
      }
    }
    //Ordena la estructura por el número de Me Gusta
    listaMeGustas = (HashMap<String, Integer>) FuncUtils.ordenarMapStrIntPorValor(listaMeGustas);
    return listaMeGustas;
  }

  public static HashMap getCountIdsPlaces(List<Place> places) {
    HashMap<String, Integer> lugares = new HashMap();
    System.out.println("---------------- IDs PLACES -----------------");
    try {
      String idPlace;
      int valor;
      for (Place pl : places) {
        idPlace = pl.getId();
        if (lugares.containsKey(idPlace)) {
          valor = lugares.get(idPlace);
          valor++;
          lugares.replace(idPlace, valor);
        } else {
          lugares.put(idPlace, 1);
        }
      }
      lugares = (HashMap<String, Integer>) FuncUtils.ordenarMapStrIntPorValor(lugares);
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(FuncUtils.class
              .getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }

    return lugares;
  }
  
  public static List<Place> getPlacesTweets(List<Status> statuses, boolean repetidos) {
    List<Place> lugares = new ArrayList<Place>();
    System.out.println("################################################");
    System.out.println("---------- LUGARES TWEETS Y FAVORITOS ----------");
    try {
      Place lugar;
      for (Status st : statuses) {
        //Este condicional es porque retweets sin comentario no permiten indicar ubicación, igual que me gustas
        //En cualquier caso el Place sería del tweet retweeteado
        if (!st.isRetweeted()) {
          lugar = st.getPlace();
          if (lugar != null && (!lugares.contains(lugar) || repetidos)) {
            lugares.add(st.getPlace());
          }
        }
      }
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(FuncUtils.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
    return lugares;
  }

  public static HashMap<String, Place> getMapPlaces(List<Place> places) {
    HashMap<String, Place> mapLugares = new HashMap<String, Place>();
    for (Place pl : places) {
      mapLugares.put(pl.getId(), pl);
    }
    return mapLugares;
  }

  public static HashMap getListadoSeguidoresMutuos(List<User> seguidoresMutuos) {
    HashMap<Long, String> segMutuos = new HashMap();
    System.out.println("------------ MAP SEGUIDORES MUTUOS ------------");
    for (User usr : seguidoresMutuos) {
      segMutuos.put(usr.getId(), usr.getScreenName());
    }
    return segMutuos;
  }

  public static LinkedHashMap getListaIdsUserRetweets(List<Status> tweets, ArrayList idsSegMutuos) {
    LinkedHashMap<Long, Integer> listaRetweets = new LinkedHashMap<>();
    System.out.println("------------ IDs USER RETWEETS -------------");
    Long usr = 0L;
    try {
      for (Status st : tweets) {
        //Aquí se hace diferenciación entre si es:
        //- ReTweet en respuesta a otro: se analiza el campo InReplyToScreenName
        //- ReTweet sin comentario: se analiza el campo RetweetedStatus
        //- ReTweet con comentario: se analiza el campo QuotedStatus
        if (st.getRetweetedStatus() != null || st.getInReplyToScreenName() != null || st.getQuotedStatus() != null) {
          if (st.getRetweetedStatus() != null) {
            usr = st.getRetweetedStatus().getUser().getId();
          }
          if (st.getInReplyToScreenName() != null) {
            usr = st.getInReplyToUserId();
          }
          if (st.getQuotedStatus() != null) {
            usr = st.getQuotedStatus().getUser().getId();
          }
          if (idsSegMutuos.contains(usr)) {
            if (listaRetweets.containsKey(usr)) {
              int valor = (int) listaRetweets.get(usr);
              valor = valor + 1;
              listaRetweets.replace(usr, valor);
            } else {
              listaRetweets.put(usr, 1);
            }
          }
        }
      }
      //Ordena la estructura por el número de Retweets o respuestas
      listaRetweets = (LinkedHashMap<Long, Integer>) FuncUtils.ordenarMapLongIntPorValor(listaRetweets);
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(FuncUtils.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
    return listaRetweets;
  }

  public static HashMap<String, Integer> getDispositivosTweets(List<Status> statuses) {
    HashMap<String, Integer> dispositivos = new HashMap<String, Integer>();
    System.out.println("################################################");
    System.out.println("----------------- DISPOSITIVOS -----------------");
    try {
      String dispositivo = "";
      String clave = "";
      String claveUnica = "";
      for (Status status : statuses) {
        dispositivo = status.getSource();
        for (Map.Entry<String, String> d : DispositivosPropertiesBean.allDisp.entrySet()) {
          if (dispositivo.contains(d.getValue())) {
            clave = d.getKey();
            claveUnica = clave.substring(0, clave.indexOf(Constantes.CHAR_SEPARADOR_PROPERTIES));
            switch (claveUnica) {
              case Constantes.DISP_ANDROID:
                if (!dispositivos.containsKey(Constantes.TXT_DISP_ANDROID)) {
                  dispositivos.put(Constantes.TXT_DISP_ANDROID, 1);
                } else {
                  int valor = dispositivos.get(Constantes.TXT_DISP_ANDROID);
                  dispositivos.replace(Constantes.TXT_DISP_ANDROID, ++valor);
                }
                break;
              case Constantes.DISP_APPLE:
                if (!dispositivos.containsKey(Constantes.TXT_DISP_APPLE)) {
                  dispositivos.put(Constantes.TXT_DISP_APPLE, 1);
                } else {
                  int valor = dispositivos.get(Constantes.TXT_DISP_APPLE);
                  dispositivos.replace(Constantes.TXT_DISP_APPLE, ++valor);
                }
                break;
              case Constantes.DISP_ORDENADOR:
                if (!dispositivos.containsKey(Constantes.TXT_DISP_ORDENADOR)) {
                  dispositivos.put(Constantes.TXT_DISP_ORDENADOR, 1);
                } else {
                  int valor = dispositivos.get(Constantes.TXT_DISP_ORDENADOR);
                  dispositivos.replace(Constantes.TXT_DISP_ORDENADOR, ++valor);
                }
                break;
              case Constantes.DISP_NAVEGADOR_WEB:
                if (!dispositivos.containsKey(Constantes.TXT_DISP_NAVEGADOR_WEB)) {
                  dispositivos.put(Constantes.TXT_DISP_NAVEGADOR_WEB, 1);
                } else {
                  int valor = dispositivos.get(Constantes.TXT_DISP_NAVEGADOR_WEB);
                  dispositivos.replace(Constantes.TXT_DISP_NAVEGADOR_WEB, ++valor);
                }
                break;
              case Constantes.DISP_OTRAS_REDES:
                if (!dispositivos.containsKey(Constantes.TXT_DISP_OTRAS_REDES)) {
                  dispositivos.put(Constantes.TXT_DISP_OTRAS_REDES, 1);
                } else {
                  int valor = dispositivos.get(Constantes.TXT_DISP_OTRAS_REDES);
                  dispositivos.replace(Constantes.TXT_DISP_OTRAS_REDES, ++valor);
                }
                break;
              case Constantes.DISP_APLICACIONES:
                if (!dispositivos.containsKey(Constantes.TXT_DISP_APLICACIONES)) {
                  dispositivos.put(Constantes.TXT_DISP_APLICACIONES, 1);
                } else {
                  int valor = dispositivos.get(Constantes.TXT_DISP_APLICACIONES);
                  dispositivos.replace(Constantes.TXT_DISP_APLICACIONES, ++valor);
                }
                break;
            }
          }
        }
      }
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(FuncUtils.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }

    return dispositivos;
  }
  
}
