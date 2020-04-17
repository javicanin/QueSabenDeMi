/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucam.quesaben;

import com.google.gson.Gson;
import com.ucam.bean.GeoJsonFeatures;
import com.ucam.bean.GeoJsonPlacesBean;
import com.ucam.bean.GeometryGeoJson;
import com.ucam.bean.InfoUsoTwitterBean;
import com.ucam.bean.PropertiesGeoJson;
import com.ucam.bean.TweetMultimediaBean;
import com.ucam.bean.UsuarioBean;
import com.ucam.files.LoadPropertiesFrases;
import com.ucam.files.LoadFilesUsersHobbies;
import com.ucam.files.LoadPropDisp;
import com.ucam.utils.Constantes;
import com.ucam.utils.FuncUtils;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import twitter4j.GeoLocation;
import twitter4j.IDs;
import twitter4j.PagableResponseList;
import twitter4j.Paging;
import twitter4j.Place;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.GeoQuery;
import twitter4j.MediaEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Relationship;
import twitter4j.UserList;

/**
 *
 * @author USUARIO
 */
public class ConexionTwitter extends HttpServlet {

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  User usuario;
  UsuarioBean usrBean;
  ArrayList idsFollowers;
  ArrayList idsFriends;
  //PagableResponseList<User> followers;
  //PagableResponseList<User> friends;
  List<UserList> listasPropias;
  List<UserList> listasSuscritas;
  List<UserList> listasMiembro;
  List<Status> statuses;
  List<Status> meGusta;
  //List<User> seguidoresMutuos;
  List<Place> placesTweets;
  GeoJsonPlacesBean geoJsonPlacesBean;
  List<Place> placesUserLocation;
  ArrayList idsSegMutuos;
  HashMap<String, String> dispProp;
  HashMap<String, Integer> dispositivos;
  HashMap mapPlacesTweets;
  HashMap listadoSeguidoresMutuos;
  HashMap meGustasToUserMutuos;
  HashMap retweetsToUserMutuos;
  HashMap idsTweetsRetweetsMe;
  LinkedHashMap idsUserFavoritos;
  LinkedHashMap idsUserRetweets;
  HashMap idsUsersRetweetsMe;
  LinkedHashMap idsSegMutuosReteetsMe;
  LinkedHashMap idsUserAmigos;
  LinkedHashMap listUsersAmigosFromIds;
  HashMap userInteracciones;
  HashMap idsPlacesTweets;
  HashMap lugaresResidenciaFrecuenta;
  ArrayList horasTweetsYFavoritos;
  InfoUsoTwitterBean usoTw;
  HashMap<String, TweetMultimediaBean> multimediaFavoritos;
  HashMap<Long, User> gamers;
  HashMap<Long, User> politicos;
  HashMap<String, String> frasesApp;

  GeoLocation geo;
  Place place;
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try {
      Twitter twitter = ConexionApiTwitter.getConexion();

      System.out.println("------------- USUARIO APLICACION -------------");
      User usuarioApp = twitter.verifyCredentials();
      System.out.println("Nombre: " + usuarioApp.getName());
      System.out.println("Descripcion: " + usuarioApp.getDescription());
      System.out.println("Id Usuario: " + usuarioApp.getId());
      System.out.println("Número Seguidores: " + usuarioApp.getFollowersCount());

      //javicanin1
      //tavito36301643
      //amsl3
      //Bonfanta
      //paquirodriguezf
      //danilof650gs
      //GonzaloViCar
      //snmr0551
      //LucianoNuevoS
      //Belenicenew
      //CarmenNB8391
      //COKEERISTOFF
      //alvaromanguero
      //Raul_17mg
      //ireneMM90
      //CristoferLK2
      //Viajoenmoto
      //DDoniga - places tweets
      //jcae - listas sucritas
      //Mdlosr
      //MariolaRodr
      //ricramsan
      //JesusCalleja
      //EstherMiguel87 - CUENTA PROTEGIDA
      //String user = "CarmenNB8391";
      String user = request.getParameter("userTwitter");

      System.out.println("------------- USUARIO CONSULTADO -------------");
      usuario = twitter.showUser(user);
      //System.out.println("Nombre: \n" + usuario.toString());
      usrBean = new UsuarioBean(usuario);
      System.out.println("Nombre: " + usuario.getName());
      System.out.println("ScreenName: " + usuario.getScreenName());
      System.out.println("Lang: " + usuario.getLang());
      System.out.println("Time/Zone: " + usuario.getTimeZone());
      Date fcrea = usuario.getCreatedAt();
//            String strFechaCreacion = fcrea.toString();
//            System.out.println("Creado: " + strFechaCreacion);
      System.out.println("Location: " + usuario.getLocation());
      System.out.println("Descripción: " + usuario.getDescription());
      System.out.println("Tweets: " + usuario.getStatusesCount());
      System.out.println("Amigos: " + usuario.getFriendsCount());
      System.out.println("Seguidores: " + usuario.getFollowersCount());
      System.out.println("Favoritos: " + usuario.getFavouritesCount());
      System.out.println("Listas: " + usuario.getListedCount());
      System.out.println("Protected: " + usuario.isProtected());
      System.out.println("Geo Enabled: " + usuario.isGeoEnabled());
      System.out.println("Verificado: " + usuario.isVerified());
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

      ConexionApiTwitter.getRateLimits(true);
      //getUserFriendships(370199897L, 385337732L);
      //searchTweets();
      idsFollowers = getIdsFollowers(user, 5000, 1);
      idsFriends = getIdsFriends(user, 5000, 1);
      idsSegMutuos = getIdsSeguidoresMutuos(idsFollowers, idsFriends);
      System.out.println("Seguidores Mutuos: " + idsSegMutuos.size());
      statuses = getTweets(user, 200, 20);
      meGusta = getFavoritos(user, 200, 5);
      usoTw = getInfoUsoTwitter(statuses, meGusta);

      //followers = getFollowers(user, 200, 6, false, true);
      //friends = getFriends(user, 200, 6, true, true);
      listasPropias = getListasPropias(user, 200, 1);
      listasSuscritas = getListasSuscritas(user, 200, 1);
      listasMiembro = getListasMiembro(user, 200, 1);

      LoadPropDisp lpd = new LoadPropDisp();
      dispProp = lpd.allDisp;
      dispositivos = getDispositivosTweets(statuses);

      //seguidoresMutuos = getSeguidoresMutuos(followers, friends);
      //listadoSeguidoresMutuos = getListadoSeguidoresMutuos(seguidoresMutuos);
      placesTweets = getPlacesTweets(statuses, true);
      geoJsonPlacesBean = getGeoJsonFeatures(statuses);

      idsPlacesTweets = getCountIdsPlaces(placesTweets);
      mapPlacesTweets = getMapPlaces(placesTweets);
      placesUserLocation = searchPlaces(999, 999, usuario.getLocation(), "city", null, Constantes.MAX_PLACES_SEARCH_API);
      lugaresResidenciaFrecuenta = getLugarDomicilio(idsPlacesTweets, mapPlacesTweets, placesUserLocation, usuario, Constantes.MAX_PLACES_DOMICILIO);

      //meGustasToUserMutuos = getListaUsersMeGustas(meGusta, seguidoresMutuos);
      //retweetsToUserMutuos = getListaReteetsToSegMutuos(statuses, listadoSeguidoresMutuos);
      idsUserRetweets = getListaIdsUserRetweets(statuses, idsSegMutuos);
      idsUserFavoritos = getUserIdsFavoritos(meGusta, idsSegMutuos);
      idsTweetsRetweetsMe = getIdsRetweetsMe(user, statuses);

      //El parametro losXMasRetweeteados indica el tamaño máximo de la estructura para después
      //solicitar los ids de usuarios. Tened en cuenta el número de peticiones por ventana es 75
      long[] idsRetweets = getMaxArrayIdsRetweets(idsTweetsRetweetsMe, Constantes.MAX_RTW_SEARCH_API);
      idsUsersRetweetsMe = getRetweetsMeForIds(idsRetweets, 200);
      //listaSegMutuosReteetsMe = getListaSegMutuosReteetsMe(idUsersRetweetsMe, listadoSeguidoresMutuos);
      idsSegMutuosReteetsMe = getListaSegMutuosReteetsMe2(idsUsersRetweetsMe, idsSegMutuos);

      idsUserAmigos = getIdsUserAmigos2(idsUserRetweets, idsUserFavoritos, idsSegMutuosReteetsMe, idsSegMutuos, Constantes.MAX_BEST_FRIENDS);
      long[] listIdsUserAmigos = getMaxArrayIdsRetweets(idsUserAmigos, Constantes.MAX_BEST_FRIENDS);

      listUsersAmigosFromIds = getListUserfromIds(listIdsUserAmigos, idsUserAmigos);

      userInteracciones = getUserInteracciones(idsUserRetweets, idsUserFavoritos, idsSegMutuosReteetsMe);

      multimediaFavoritos = getTweetsMultimediaFavoritos(statuses);

      LoadFilesUsersHobbies lfuh = new LoadFilesUsersHobbies(Constantes.FICHERO_USUARIOS_GAMERS);
      String[] gamersFile = lfuh.leerFicheroUsuarios();
      gamers = getListIdsUserFromScreenName(gamersFile);
      getUserHobbiesInFriends(gamers, idsFriends);

      lfuh = new LoadFilesUsersHobbies(Constantes.FICHERO_USUARIOS_POLITICA);
      String[] politicaFile = lfuh.leerFicheroUsuarios();
      politicos = getListIdsUserFromScreenName(politicaFile);
      getUserHobbiesInFriends(politicos, idsFriends);
      
      
      LoadPropertiesFrases propFrases = new LoadPropertiesFrases();
      propFrases.loadRandomProperties();
      frasesApp = selectFrases(propFrases);
      

      /*frasesApp = prop.loadAllProperties();
      String fraseVefificado = prop.getPropertiesAsAleatory(Constantes.FRASE_VERIFICADO);
      String fraseNoVefificado = prop.getPropertiesAsAleatory(Constantes.FRASE_NO_VERIFICADO);
      String fraseProtegido = prop.getPropertiesAsAleatory(Constantes.FRASE_PROTEGIDO);
      String fraseNoProtegido = prop.getPropertiesAsAleatory(Constantes.FRASE_NO_PROTEGIDO);*/
 /*            
            System.out.println("------------ RESUMEN DATOS -------------");
            System.out.println("followers: " + followers.size());
            System.out.println("friends: " + friends.size());
            System.out.println("statuses: " + statuses.size());
            System.out.println("favoritos: " + meGusta.size());
            System.out.println("listasPropias: " + listasPropias.size());
            System.out.println("listasSuscritas: " + listasSuscritas.size());
            System.out.println("listasMiembro: " + listasMiembro.size());
            System.out.println("places: " + placesTweets.size());
       */
      ConexionApiTwitter.getRateLimits(true);

///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
      /*request.setAttribute("fraseVerificado", fraseVefificado);
      request.setAttribute("fraseNoVerificado", fraseNoVefificado);
      request.setAttribute("fraseProtegido", fraseProtegido);
      request.setAttribute("fraseNoProtegido", fraseNoProtegido);*/
      request.setAttribute("frases", frasesApp);

//request.setAttribute("followers", followers);
      request.setAttribute("usrBean", usrBean);
      request.setAttribute("amigos", listUsersAmigosFromIds);
      request.setAttribute("usrInterac", userInteracciones);
      request.setAttribute("posFriend", idsUserAmigos);
      request.setAttribute("domicilio", lugaresResidenciaFrecuenta);
/////////////////////////////////////////////////////////
//Valores pasados para las pruebas con la tabla de amigos
      request.setAttribute("idRtw", idsUserRetweets);
      request.setAttribute("idFv", idsUserFavoritos);
      request.setAttribute("idRtwMe", idsSegMutuosReteetsMe);
/////////////////////////////////////////////////////////

      String usoTwt = new Gson().toJson(usoTw);
      request.setAttribute("usoTw", usoTwt);

      String fraseHoras = usoTw.titularUsoTwitter("horas");
      String fraseUsoGeneral = usoTw.titularUsoTwitter("general");

      request.setAttribute("fraseHoras", fraseHoras);
      request.setAttribute("fraseUsoGeneral", fraseUsoGeneral);

      /*      
      ArrayList datosDispositivos = new ArrayList();
      ArrayList labelsDispositivos = new ArrayList();
      for (Map.Entry<String, Integer> dsp : dispositivos.entrySet()) {
        labelsDispositivos.add(dsp.getKey());
        datosDispositivos.add(dsp.getValue());
      }
      String labelDispJson = new Gson().toJson(labelsDispositivos);
      String datosDispJson = new Gson().toJson(datosDispositivos);
       */
      request.setAttribute("labelsDispositivos", FuncUtils.getStringGsonLabelDataDisp(dispositivos, "label"));
      request.setAttribute("datosDispositivos", FuncUtils.getStringGsonLabelDataDisp(dispositivos, "data"));

      String geoJsonPlaces = new Gson().toJson(geoJsonPlacesBean);
      request.setAttribute("geoJsonPlaces", geoJsonPlaces);

      request.setAttribute("multimediaBean", multimediaFavoritos);

      RequestDispatcher dispatcher = request.getRequestDispatcher(response.encodeURL("index.jsp"));
      dispatcher.forward(request, response);

    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
      RequestDispatcher dispatcher = request.getRequestDispatcher(response.encodeURL("error.jsp"));
      dispatcher.forward(request, response);

    }
  }

///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
  public HashMap<String, String> selectFrases(LoadPropertiesFrases lpf){
    HashMap<String, String> frases = new HashMap();
    String clave = usrBean.clavePerfilVerificado();
    frases.put(Constantes.CLAVE_VERIFICADO, lpf.getRandomFraseByKey(clave));
    
    clave = usrBean.clavePerfilProtegido();
    frases.put(Constantes.CLAVE_PROTEGIDO, lpf.getRandomFraseByKey(clave));

    clave = usrBean.claveVolumenUso();
    frases.put(Constantes.CLAVE_VOL_USO, lpf.getRandomFraseByKey(clave));

    clave = usrBean.claveNumSeguidores();
    frases.put(Constantes.CLAVE_NUM_SEGUIDORES, lpf.getRandomFraseByKey(clave));

    clave = usrBean.claveNumAmigos();
    frases.put(Constantes.CLAVE_NUM_AMIGOS, lpf.getRandomFraseByKey(clave));

    clave = FuncUtils.claveNumGamers(getUserHobbiesInFriends(gamers, idsFriends).size());
    frases.put(Constantes.CLAVE_GUSTOS_GAMERS, lpf.getRandomFraseByKey(clave));

    clave = FuncUtils.claveNumPoliticos(getUserHobbiesInFriends(politicos, idsFriends).size());
    frases.put(Constantes.CLAVE_GUSTOS_POLITICA, lpf.getRandomFraseByKey(clave));

    return frases;
  }

  public ArrayList getIdsFollowers(String screenName, int groupSize, int numLlamadas) {
    ArrayList followers = new ArrayList();
    IDs followersPage;
    Twitter twitter = ConexionApiTwitter.getConexion();
    System.out.println("################################################");
    System.out.println("---------------- IDs FOLLOWERS -----------------");

    long cursor = -1;
    try {
      do {
        followersPage = twitter.getFollowersIDs(screenName, cursor, groupSize);

        long[] ids = followersPage.getIDs();
        for (long fp : ids) {
          followers.add(fp);
        }
        numLlamadas--;
        cursor = followersPage.getNextCursor();
      } while (cursor != 0 && numLlamadas > 0);

    } catch (TwitterException ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
    /*for (int i=0; i<followers.size(); i++) {
            System.out.println(followers.get(i));
        }*/
    System.out.println(followers.toString());
    return followers;
  }

  public PagableResponseList<User> getFollowers(String userName, int groupSize, int numLlamadas, boolean skipStatus, boolean includeUserEntities) {
    PagableResponseList<User> followers = null;
    PagableResponseList<User> followersPage;
    Twitter twitter = ConexionApiTwitter.getConexion();
    System.out.println("################################################");
    System.out.println("------------------ FOLLOWERS -------------------");

    long cursor = -1;
    try {

      do {
        followersPage = twitter.getFollowersList(userName, cursor, groupSize, skipStatus, includeUserEntities);
        if (cursor == -1) {
          followers = followersPage;
        } else {
          followers.addAll(followersPage);
        }
        numLlamadas--;
        cursor = followersPage.getNextCursor();
      } while (cursor != 0 && numLlamadas > 0);

    } catch (TwitterException ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
    /*        for (User flw : followers) {
            System.out.println(flw.getId() + ": " + flw.getScreenName());
        }
     */
    return followers;
  }

  public ArrayList getIdsFriends(String screenName, int groupSize, int numLlamadas) {
    ArrayList friends = new ArrayList();
    IDs friendsPage;
    Twitter twitter = ConexionApiTwitter.getConexion();
    System.out.println("################################################");
    System.out.println("---------------- IDs FRIENDS -----------------");

    long cursor = -1;
    try {
      do {
        friendsPage = twitter.getFriendsIDs(screenName, cursor, groupSize);
        numLlamadas--;
        long[] ids = friendsPage.getIDs();
        for (long fp : ids) {
          friends.add(fp);
        }
        cursor = friendsPage.getNextCursor();
      } while (cursor != 0 && numLlamadas > 0);

    } catch (TwitterException ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
    /*for (int i=0; i<friends.size(); i++) {
            System.out.println(friends.get(i));
        }*/
    System.out.println(friends.toString());
    return friends;
  }

  public PagableResponseList<User> getFriends(String userName, int groupSize, int numLlamadas, boolean skipStatus, boolean includeUserEntities) {
    PagableResponseList<User> friends = null;
    PagableResponseList<User> friendsPage;
    Twitter twitter = ConexionApiTwitter.getConexion();
    System.out.println("################################################");
    System.out.println("-------------------- FRIENDS --------------------");

    //Lista de seguidores followers
    long cursor = -1;
    try {
      do {
        friendsPage = twitter.getFriendsList(userName, cursor, groupSize, skipStatus, includeUserEntities);
        if (cursor == -1) {
          friends = friendsPage;
        } else {
          friends.addAll(friendsPage);
        }
        numLlamadas--;
        cursor = friendsPage.getNextCursor();
      } while (cursor != 0 && numLlamadas > 0);

    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }

    /*        for (User frd : friends) {
            System.out.println(frd.getId() + ": " + frd.getScreenName());
        }
     */
    return friends;
  }

  public ArrayList getIdsSeguidoresMutuos(ArrayList idsFollowers, ArrayList idsFriends) {
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

  public ResponseList<Status> getTweets(String userName, int pageSize, int numLlamadas) {
    ResponseList<Status> tweets = null;
    ResponseList<Status> tweetsPage;
    Twitter twitter = ConexionApiTwitter.getConexion();
    System.out.println("################################################");
    System.out.println("-------------------- TWEETS --------------------");
    //Listado de Tweets de un usuario
    Paging paginacion = new Paging(1, pageSize);
    ////////////////////////////
    //OJO PARA EVITAR PAGINACIÓN
    ////////////////////////////
    //List<Status> statuses = twitter.getUserTimeline(user);
    //statuses = twitter.getUserTimeline(user);
    try {
      do {
        tweetsPage = twitter.getUserTimeline(userName, paginacion);
        if (paginacion.getPage() == 1) {
          tweets = tweetsPage;
        } else {
          tweets.addAll(tweetsPage);
        }
        numLlamadas--;
        paginacion.setPage(paginacion.getPage() + 1);
      } while (tweetsPage.size() > 0 && numLlamadas > 0);

      /*for (Status st : tweets) {
        System.out.println(st.toString());
      }*/
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }

/*        for (Status st : tweets) {
            System.out.println("TXT: " + st.getText());
            System.out.println("ID: " + st.getId());
            System.out.println("IsRetweet/IsRetweeted/IsRetweetedByMe: " + st.isRetweet()+"/"+st.isRetweeted()+"/"+st.isFavorited()+"/"+st.isRetweetedByMe());
            System.out.println("InReply: " + st.getInReplyToScreenName());
            if(st.getRetweetedStatus()!=null)
              System.out.println("RetweetedStatus: "+st.getRetweetedStatus().toString());
            System.out.println("User: " + st.getUser().getScreenName());
            System.out.println("RwtCount/favoriteCount:" + st.getRetweetCount() + "/" + st.getFavoriteCount());
            if(st.getQuotedStatus()!=null)
              System.out.println("QuotedStatus/QuotedStatusId/QuotedStatus/User-ScreenName: \n"+st.getQuotedStatus()+"\n"+st.getQuotedStatusId()+"\n"+st.getQuotedStatus().getUser().getScreenName());
            if(st.getMediaEntities()!=null)
              System.out.println("MediaEntities: " + Arrays.toString(st.getMediaEntities()));
            System.out.println("\n");
        }*/

    /*
        for (Status st : tweets) {
            System.out.println(st.getId() + " # Retweets/Me Gusta: " + st.getRetweetCount() + "/" + st.getFavoriteCount() + " ### " + st.getText());
        }
     */
 /*    System.out.println("OJO SOLO SE ESTAN IMPRIMIENTO LOS QUE CONTIENEN MULTIMEDIA");
    for (Status st : tweets) {
      if (st.getMediaEntities().length != 0) {
        System.out.println(st.toString());
      }
    }
     */
    System.out.println(tweets.toString());
    return tweets;
  }

  //PRUEBA DE MÉTODO DE BÚSQUEDA DE TWEETS. Tened en cuenta que los resultados son por relevancia y no por integridad.
  public List<Status> searchTweets() {
    List<Status> tweets = null;
    Twitter twitter = ConexionApiTwitter.getConexion();
    System.out.println("-------------- BUSQUEDA PLACES ---------------");
    try {
      Query query = new Query("to@amsl3");
      query.count(100);
      //query.setSinceId(254878167343439872L);
      QueryResult result;
      result = twitter.search(query);
      tweets = result.getTweets();
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }

    return tweets;
  }

  public HashMap getListaReteetsToSegMutuos(List<Status> tweets, HashMap segMutuos) {
    HashMap<String, Integer> listaRetweets = new HashMap<>();
    System.out.println("------------ RETWEETS A SEG MUTUOS ---------------");
    String usr;
    for (Status st : tweets) {
      //Si es un retweet entonces el campo RetweetedStatus contendrá el usuario creador
      //Si es una respuesta a tweet entonces el campo InReplyToScreenName contendrá el usuario creador
      if (st.getRetweetedStatus() != null || st.getInReplyToScreenName() != null) {
        if (st.getRetweetedStatus() != null) {
          usr = st.getRetweetedStatus().getUser().getScreenName();
        } else {
          usr = st.getInReplyToScreenName();
        }
        if (segMutuos.containsValue(usr)) {
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
    listaRetweets = (HashMap<String, Integer>) FuncUtils.ordenarMapStrIntPorValor(listaRetweets);

    /*        for (Map.Entry<String, Integer> tw : listaRetweets.entrySet()) {
            System.out.println(tw.toString());
        }
     */
    return listaRetweets;
  }

  public LinkedHashMap getListaIdsUserRetweets(List<Status> tweets, ArrayList idsSegMutuos) {
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
      /*
        for (Map.Entry<Long, Integer> tw : listaRetweets.entrySet()) {
            System.out.println(tw.toString());
        }
       */
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }

    return listaRetweets;
  }

  public ResponseList<Status> getFavoritos(String userName, int pageSize, int numLlamadas) {
    ResponseList<Status> favoritos = null;
    ResponseList<Status> favoritosPage;
    Twitter twitter = ConexionApiTwitter.getConexion();
    System.out.println("################################################");
    System.out.println("------------------ FAVORITOS -------------------");
    //Listado de Tweets de un usuario
    Paging paginacion = new Paging(1, pageSize);
    ////////////////////////////
    //OJO PARA EVITAR PAGINACIÓN
    ////////////////////////////
    //List<Status> statuses = twitter.getUserTimeline(user);
    //statuses = twitter.getUserTimeline(user);
    try {
      do {
        favoritosPage = twitter.getFavorites(userName, paginacion);
        if (paginacion.getPage() == 1) {
          favoritos = favoritosPage;
        } else {
          favoritos.addAll(favoritosPage);
        }
        numLlamadas--;
        paginacion.setPage(paginacion.getPage() + 1);
      } while (favoritosPage.size() > 0 && numLlamadas > 0);

      /*for (Status st : favoritos) {
        System.out.println(st.getId() + ": " + st.getText() + "| Retweets/Me Gusta: " + st.getRetweetCount() + "/" + st.getFavoriteCount());
      }*/
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
    System.out.println(favoritos.toString());
    return favoritos;
  }

  public PagableResponseList<UserList> getListasPropias(String userName, int groupSize, int numLlamadas) {
    PagableResponseList<UserList> listas = null;
    PagableResponseList<UserList> listasPage;
    Twitter twitter = ConexionApiTwitter.getConexion();
    System.out.println("################################################");
    System.out.println("--------------- LISTAS PROPIAS -----------------");

    long cursor = -1;
    try {
      do {
        listasPage = twitter.getUserListsOwnerships(userName, groupSize, cursor);
        if (cursor == -1) {
          listas = listasPage;
        } else {
          listas.addAll(listasPage);
        }
        numLlamadas--;
        cursor = listasPage.getNextCursor();
      } while (cursor != 0 && numLlamadas > 0);

    } catch (TwitterException ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
    /*for (UserList ul : listas) {
            System.out.println("" + ul.toString());
        }*/
    System.out.println(listas.toString());
    return listas;
  }

  public PagableResponseList<UserList> getListasMiembro(String userName, int groupSize, int numLlamadas) {
    PagableResponseList<UserList> listas = null;
    PagableResponseList<UserList> listasPage;
    Twitter twitter = ConexionApiTwitter.getConexion();
    System.out.println("################################################");
    System.out.println("--------------- LISTAS MIEMBRO -----------------");

    long cursor = -1;
    try {
      do {
        listasPage = twitter.getUserListMemberships(userName, groupSize, cursor);
        if (cursor == -1) {
          listas = listasPage;
        } else {
          listas.addAll(listasPage);
        }
        numLlamadas--;
        cursor = listasPage.getNextCursor();
      } while (cursor != 0 && listas.size() < numLlamadas);

    } catch (TwitterException ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
    /*for (UserList ul : listas) {
            System.out.println(ul.toString());
        }*/
    System.out.println(listas.toString());
    return listas;
  }

  public PagableResponseList<UserList> getListasSuscritas(String userName, int groupSize, int numLlamadas) {
    PagableResponseList<UserList> listas = null;
    PagableResponseList<UserList> listasPage;
    Twitter twitter = ConexionApiTwitter.getConexion();
    System.out.println("################################################");
    System.out.println("-------------- LISTAS SUSCRITAS ---------------");

    long cursor = -1;
    try {
      do {
        listasPage = twitter.getUserListSubscriptions(userName, groupSize, cursor);
        if (cursor == -1) {
          listas = listasPage;
        } else {
          listas.addAll(listasPage);
        }
        numLlamadas--;
        cursor = listasPage.getNextCursor();
      } while (cursor != 0 && listas.size() < numLlamadas);

    } catch (TwitterException ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
    /*for (UserList ul : listas) {
            System.out.println("" + ul.toString());
        }*/
    System.out.println(listas.toString());
    return listas;
  }

  //REFACTORIZAR
  public HashMap<String, Integer> getDispositivosTweets(List<Status> statuses) {
    HashMap<String, Integer> dispositivos = new HashMap<String, Integer>();
    System.out.println("################################################");
    System.out.println("----------------- DISPOSITIVOS -----------------");
    try {
      String dispositivo = "";
      String clave = "";
      String claveUnica = "";
      for (Status status : statuses) {
        dispositivo = status.getSource();
        for (Map.Entry<String, String> d : dispProp.entrySet()) {
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
        /*
        if (dispositivo.contains(dispProp.get(Constantes.DISP_ANDROID))) {
          dispositivo = Constantes.DISP_ANDROID;
          if (!dispositivos.containsKey(Constantes.DISP_ANDROID)) {
            dispositivos.put(Constantes.DISP_ANDROID, 1);
          } else {
            int valor = dispositivos.get(dispositivo);
            valor++;
            dispositivos.replace(dispositivo, valor);
          }
        }
        /*        if (dispositivo.contains(Constantes.DISP_IPHONE)) {
          dispositivo = Constantes.DISP_IPHONE;
          if (!dispositivos.containsKey(Constantes.DISP_IPHONE)) {
            dispositivos.put(Constantes.DISP_IPHONE, 1);
          } else {
            int valor = dispositivos.get(dispositivo);
            valor++;
            dispositivos.replace(dispositivo, valor);
          }
        }
        if (dispositivo.contains(Constantes.DISP_MOBIL_WEB)) {
          dispositivo = Constantes.DISP_MOBIL_WEB;
          if (!dispositivos.containsKey(Constantes.DISP_MOBIL_WEB)) {
            dispositivos.put(Constantes.DISP_MOBIL_WEB, 1);
          } else {
            int valor = dispositivos.get(dispositivo);
            valor++;
            dispositivos.replace(dispositivo, valor);
          }
        }
        if (dispositivo.contains(Constantes.DISP_WEBSITES)) {
          dispositivo = Constantes.DISP_WEBSITES;
          if (!dispositivos.containsKey(Constantes.DISP_WEBSITES)) {
            dispositivos.put(Constantes.DISP_WEBSITES, 1);
          } else {
            int valor = dispositivos.get(dispositivo);
            valor++;
            dispositivos.replace(dispositivo, valor);
          }
        }
        if (!dispositivo.contains(Constantes.DISP_ANDROID) && !dispositivo.contains(Constantes.DISP_IPHONE)
                && !dispositivo.contains(Constantes.DISP_MOBIL_WEB) && !dispositivo.contains(Constantes.DISP_WEBSITES)) {
          dispositivo = Constantes.DISP_OTROS;
          if (!dispositivos.containsKey(Constantes.DISP_OTROS)) {
            dispositivos.put(Constantes.DISP_OTROS, 1);
          } else {
            int valor = dispositivos.get(dispositivo);
            valor++;
            dispositivos.replace(dispositivo, valor);
          }
        }*/
      }
      for (Map.Entry<String, Integer> dsp : dispositivos.entrySet()) {
        System.out.println(dsp.getKey() + ": " + dsp.getValue());
      }
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }

    return dispositivos;
  }

  public List<User> getSeguidoresMutuos(PagableResponseList<User> followers, PagableResponseList<User> friends) {
    List<User> segMutuos = new ArrayList<User>();
    System.out.println("################################################");
    System.out.println("------------- SEGUIDORES MUTUOS -------------");
    if (followers != null && friends != null) {
      for (User flw : followers) {
        for (User frd : friends) {
          if (flw.getId() == frd.getId()) {
            segMutuos.add(flw);
            //System.out.println(flw.getScreenName());
          }
        }
      }
    }
    /*
        for (User sgMt : followers) {
            System.out.println(sgMt.getId() + ": " + sgMt.getScreenName());
        }
     */
    return segMutuos;
  }

  public List<Place> getPlacesTweets(List<Status> statuses, boolean repetidos) {
    List<Place> lugares = new ArrayList<Place>();
    System.out.println("################################################");
    System.out.println("---------- LUGARES TWEETS Y FAVORITOS ----------");
    try {
      Place lugar;
      for (Status st : statuses) {
        //Este condicional porque retweets sin comentario no permiten indicar ubicación, igual que me gustas
        //En cualquier caso el Place sería del tweet retweeteado
        if (!st.isRetweeted()) {
          lugar = st.getPlace();
          if (lugar != null && (!lugares.contains(lugar) || repetidos)) {
            lugares.add(st.getPlace());
          }
        }
      }
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }

    return lugares;
  }

  public double[] getCentralPointOfPolygon(GeoLocation[][] geoLoc) {
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
      java.util.logging.Logger.getLogger(ConexionTwitter.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }

    return centralPoint;
  }

  public GeoJsonPlacesBean getGeoJsonFeatures(List<Status> statuses) {
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
            geom.coordinates = getCentralPointOfPolygon(boxCoords);

            GeoJsonFeatures feat = new GeoJsonFeatures();
            feat.type = "Feature";
            feat.properties = prop;
            feat.geometry = geom;

            gJsPl.features.add(feat);
          }
        }
      }
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }

    return gJsPl;
  }

  public HashMap getCountIdsPlaces(List<Place> places) {
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
      /*for (Map.Entry<String, Integer> lg : lugares.entrySet()) {
        System.out.println(lg.getKey() + ": " + lg.getValue());
      }*/

    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class
              .getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }

    return lugares;
  }

  public HashMap<String, Place> getMapPlaces(List<Place> places) {
    HashMap<String, Place> mapLugares = new HashMap<String, Place>();
    for (Place pl : places) {
      mapLugares.put(pl.getId(), pl);
    }
    return mapLugares;
  }

  //// METODO EN PRUEBAS PARA LOS LUGARES
  public ResponseList<Place> searchPlaces(long latitud, long longitud, String consulta, String granularidad, String precision, int maxResults) {
    ResponseList<Place> lugares = null;
    Twitter twitter = ConexionApiTwitter.getConexion();
    System.out.println("-------------- BUSQUEDA PLACES ---------------");
    try {
      //Los valores válidos para latitud son de -90.0 a +90.0 y para latitud de -180.0 a +180.0
      //Otros valores no serán tenidos en cuenta
      GeoLocation location = new GeoLocation(latitud, longitud);
      GeoQuery query = new GeoQuery(location);
      //Establece el texto libre del lugar a buscar
      query.setQuery(consulta);
      //Especifica el tipo y subtipos de Places a buscar desde el más alto indicado
      //La jerarquía en orden creciente es: neighborhood, city, admin y country
      query.setGranularity(granularidad);
      //Especifica la preción de la búsqueda en metros o pies
      query.accuracy(precision);
      query.setMaxResults(maxResults);
      lugares = twitter.searchPlaces(query);
      /*
            for (Place p : lugares) {
                System.out.println(p.getId() + " # " + p.getName() + " # " + p.getFullName() + " # " + p.getCountryCode() + " # " + p.getCountry());
            }
       */
    } catch (TwitterException ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class
              .getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
    System.out.println(lugares.toString());
    return lugares;
  }

  //LLAMADA A MÉTODO DE API QUE DEVUELVE INFORMACIÓN DE AMISTAD ENTRE DOS USUARIOS
  //NO APORTA MÁS INFORMACIÓN DE LA YA OBTENIDA CON LOS OTROS MÉTODOS
  public void getUserFriendships(long idSource, long idTarget) {
    Twitter twitter = ConexionApiTwitter.getConexion();
    System.out.println("-------------- RELACION AMISTAD ---------------");
    try {
      Relationship relShip = twitter.showFriendship(idSource, idTarget);
      /*  for (Place p : lugares) {
                System.out.println(p.getId() + " # " + p.getName() + " # " + p.getFullName() + " # " + p.getCountryCode() + " # " + p.getCountry());
            }
       */
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class
              .getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
    //return lugares;
  }

  public HashMap getListaUsersMeGustas(List<Status> meGustas, List<User> segMutuos) {
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
    /*
        for (Map.Entry<String, Integer> usr : listaMeGustas.entrySet()) {
            System.out.println(usr.getKey() + ":" + usr.getValue());
        }
     */
    return listaMeGustas;
  }

  public HashMap getListadoSeguidoresMutuos(List<User> seguidoresMutuos) {
    HashMap<Long, String> segMutuos = new HashMap();
    System.out.println("------------ MAP SEGUIDORES MUTUOS ------------");
    for (User usr : seguidoresMutuos) {
      segMutuos.put(usr.getId(), usr.getScreenName());
    }
    /*
        for (Map.Entry<Long, String> usr : segMutuos.entrySet()) {
            System.out.println(usr.getKey() + ":" + usr.getValue());
        }
     */
    return segMutuos;
  }

  public HashMap getIdsRetweetsMe(String user, List<Status> status) {
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
      /*
        for (Map.Entry<Long, Integer> rtm : numsRetweets.entrySet()) {
            System.out.println(rtm.getKey() + ":" + rtm.getValue());
        }
       */
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class
              .getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }

    return numsRetweets;
  }

  public LinkedHashMap getUserIdsFavoritos(List<Status> meGustas, ArrayList segMutuos) {
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
      /*
        for (Map.Entry<Long, Integer> usr : usrMeGustas.entrySet()) {
            System.out.println(usr.getKey() + ":" + usr.getValue());
        }
       */
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class
              .getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }

    return usrMeGustas;
  }

  public long[] getMaxArrayIdsRetweets(HashMap<Long, Integer> idsRetweets, int losXMasRetweeteados) {
    int arraySize = losXMasRetweeteados;
    if(idsRetweets.size() < losXMasRetweeteados)
      arraySize = idsRetweets.size();
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

  public LinkedHashMap getRetweetsMeForIds(long[] tweetsIds, int groupSize) {
    LinkedHashMap<Long, Integer> numsRetweets = new LinkedHashMap();
    IDs usersPage = null;
    Twitter twitter = ConexionApiTwitter.getConexion();
    System.out.println("################################################");
    System.out.println("----------- USER RETWEETME FOR IDs -------------");

    long cursor = -1;
    try {
      for (int id = 0; id < tweetsIds.length; id++) {
        usersPage = twitter.getRetweeterIds(tweetsIds[id], groupSize, cursor);
        if (usersPage.getIDs() != null) {
          long[] ids = usersPage.getIDs();
          for (int i = 0; i < ids.length; i++) {
            if (numsRetweets.containsKey(ids[i])) {
              int valor = (int) numsRetweets.get(ids[i]);
              valor = valor + 1;
              numsRetweets.replace(ids[i], valor);
            } else {
              numsRetweets.put(ids[i], 1);
            }
          }
        }
      }
      //numsRetweets = (LinkedHashMap<Long, Integer>) FuncUtils.ordenarMapLongIntPorValor(numsRetweets);
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class
              .getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }

    /*for (Map.Entry<Long, Integer> rtm : numsRetweets.entrySet()) {
      System.out.println(rtm.getKey() + ":" + rtm.getValue());
    }*/

    return numsRetweets;
  }

  public LinkedHashMap<Long, UsuarioBean> getListUserfromIds(long[] userIds, LinkedHashMap<Long, Integer> idsUserAmigos) {
    LinkedHashMap<Long, UsuarioBean> bestFriends = new LinkedHashMap();
    Twitter twitter = ConexionApiTwitter.getConexion();
    System.out.println("------------- LIST BEST FRIENDS ---------------");
    try {
      ResponseList<User> users = twitter.lookupUsers(userIds);
      //Se hace un doble bucle para insertar en la estructura según orden de amistad establecido
      for (User usr : users) {
        for (Map.Entry<Long, Integer> idsFr : idsUserAmigos.entrySet()) {
          if (idsUserAmigos.containsKey(usr.getId()) && !bestFriends.containsKey(usr.getId())) {
            UsuarioBean friend = new UsuarioBean(usr);
            bestFriends.put(usr.getId(), friend);
          }
        }
      }
      /*            
            for (Map.Entry<Long, UsuarioBean> lAmg : bestFriends.entrySet()) {
                System.out.println(lAmg.getKey() + ": " + lAmg.getValue());
            }
       */
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class
              .getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
    return bestFriends;
  }

  public HashMap<Long, User> getListIdsUserFromScreenName(String[] userScreenName) {
    HashMap<Long, User> usrScrNm = new HashMap<>();
    Twitter twitter = ConexionApiTwitter.getConexion();
    System.out.println("------------- IDS FROM SCREENNAME ---------------");
    try {
      int pos = 0;
      int nIter = ((int) userScreenName.length / 100) + 1;
      int cont = nIter;
      String[] dest = null;
      for (int i = 0; i < nIter; i++) {
        if (cont <= 1) {
          dest = new String[userScreenName.length % 100];
        } else {
          dest = new String[100];
        }
        System.arraycopy(userScreenName, pos, dest, 0, dest.length);
        ResponseList<User> listUser = twitter.lookupUsers(dest);
        for (User usr : listUser) {
          //System.out.println(usr.getScreenName() + ": " + usr.getId() + ":" + usr.getFollowersCount());
          usrScrNm.put(usr.getId(), usr);
        }
        pos = pos + 100;
        cont--;
      }
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class
              .getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
    return usrScrNm;
  }

  public HashMap getListaSegMutuosReteetsMe(HashMap<Long, Integer> idsUsersRetweetsMe, HashMap segMutuos) {
    HashMap<Long, Integer> listaSegMutuosRetweetsMe = new HashMap<>();
    System.out.println("------------ RETWEETS ME SEG MUTUOS -------------");
    for (Map.Entry<Long, Integer> ids : idsUsersRetweetsMe.entrySet()) {
      if (segMutuos.containsKey(ids.getKey())) {
        listaSegMutuosRetweetsMe.put(ids.getKey(), ids.getValue());
      }
    }
    //Ordena la estructura por el número de Retweets
    listaSegMutuosRetweetsMe = (HashMap<Long, Integer>) FuncUtils.ordenarMapLongIntPorValor(listaSegMutuosRetweetsMe);
    /*
        for (Map.Entry<Long, Integer> usrRtm : listaSegMutuosRetweetsMe.entrySet()) {
            System.out.println(usrRtm.getKey() + ":" + usrRtm.getValue());
        }
     */
    return listaSegMutuosRetweetsMe;
  }

  public LinkedHashMap getListaSegMutuosReteetsMe2(HashMap<Long, Integer> idsUsersRetweetsMe, ArrayList segMutuos) {
    LinkedHashMap<Long, Integer> listaSegMutuosRetweetsMe = new LinkedHashMap<>();
    System.out.println("------------ RETWEETS ME SEG MUTUOS -------------");
    for (Map.Entry<Long, Integer> ids : idsUsersRetweetsMe.entrySet()) {
      if (segMutuos.contains(ids.getKey())) {
        listaSegMutuosRetweetsMe.put(ids.getKey(), ids.getValue());
      }
    }
    //Ordena la estructura por el número de Retweets
    listaSegMutuosRetweetsMe = (LinkedHashMap<Long, Integer>) FuncUtils.ordenarMapLongIntPorValor(listaSegMutuosRetweetsMe);
    /*
        for (Map.Entry<Long, Integer> usrRtm : listaSegMutuosRetweetsMe.entrySet()) {
            System.out.println(usrRtm.getKey() + ":" + usrRtm.getValue());
        }
     */
    return listaSegMutuosRetweetsMe;
  }

  public LinkedHashMap getIdsUserAmigos(HashMap<Long, Integer> idsUsrRtw, HashMap<Long, Integer> idsUsrFv, HashMap<Long, Integer> idsUsrRtwMe) {
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

  public LinkedHashMap getIdsUserAmigos2(LinkedHashMap<Long, Integer> idsUsrRtw, LinkedHashMap<Long, Integer> idsUsrFv, LinkedHashMap<Long, Integer> idsUsrRtwMe, ArrayList segMutuos, int maxAmigos) {
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
    //if (posicionamientos.size() < maxAmigos) {
    for (int i = 0; i < segMutuos.size(); i++) {
      if (posicionamientos.size() < maxAmigos) {
        posicionamientos.put((Long) segMutuos.get(i), 1);
      } else {
        break;
      }
    }
    //}

    /*for (Map.Entry<Long, Integer> psc : posicionamientos.entrySet()) {
      System.out.println(psc.getKey() + ":" + psc.getValue());
    }*/
    return posicionamientos;
  }

  public HashMap getUserInteracciones(HashMap<Long, Integer> idsUsrRtw, HashMap<Long, Integer> idsUsrFv, HashMap<Long, Integer> idsUsrRtwMe) {
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
      java.util.logging.Logger.getLogger(ConexionTwitter.class
              .getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }

    return interacciones;
  }

  public InfoUsoTwitterBean getInfoUsoTwitter(List<Status> tweets, List<Status> favoritos) {
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

      int[] mayorMenor = getMayorMenorHashMap(usoAnnios);
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
      java.util.logging.Logger.getLogger(ConexionTwitter.class
              .getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }

    return usoTw;
  }

  public int[] getMayorMenorHashMap(HashMap<Integer, Integer> usoAnnios) {
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
      java.util.logging.Logger.getLogger(ConexionTwitter.class
              .getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
    return mayorMenor;
  }

  /*
  public ArrayList fraseGeneralUsoTwitter(InfoUsoTwitterBean usoTw) {
    ArrayList frases = new ArrayList();
    HashMap<String, Float> times = new HashMap();
    try {
      for (int i = 0; i < usoTw.horas.size(); i++) {
        times.put((String) usoTw.labelsHoras.get(i), (Float) usoTw.porcentHoras.get(i));
      }
      for (int i = 0; i < usoTw.dias.size(); i++) {
        times.put((String) usoTw.labelsDias.get(i), (Float) usoTw.porcentDias.get(i));
      }
      for (int i = 0; i < usoTw.meses.size(); i++) {
        times.put((String) usoTw.labelsMeses.get(i), (Float) usoTw.porcentMeses.get(i));
      }
      for (int i = 0; i < usoTw.annios.size(); i++) {
        times.put((String) usoTw.labelsAnnios.get(i), (Float) usoTw.porcentAnnios.get(i));
      }

      LinkedHashMap<String, Float> timesOrden = (LinkedHashMap<String, Float>) FuncUtils.ordenarMapStrFloatPorValor(times);

      for (Map.Entry<String, Float> h : timesOrden.entrySet()) {
        switch (h.getKey()) {
          case "00":
          case "01":
          case "02":
          case "03":
          case "04":
          case "05":
            frases.add("De madrugada");
            break;
          case "06":
          case "07":
          case "08":
            frases.add("Por la mañana temprano");
            break;
          case "09":
          case "10":
          case "11":
            frases.add("A media mañana");
            break;
          case "12":
          case "13":
          case "14":
          case "15":
            frases.add("A mediodía");
            break;
          case "16":
          case "17":
          case "18":
          case "19":
          case "20":
            frases.add("Por la tarde");
            break;
          case "21":
          case "22":
          case "23":
            frases.add("Por la noche");
            break;
          case "Lunes":
          case "Martes":
          case "Miércoles":
          case "Jueves":
          case "Viernes":
          case "Sábado":
          case "Domingo":
            frases.add("En el día de la semana " + h.getKey());
            break;
          case "Enero":
          case "Febrero":
          case "Marzo":
          case "Abril":
          case "Mayo":
          case "Junio":
          case "Julio":
          case "Agosto":
          case "Septiembre":
          case "Octubre":
          case "Noviembre":
          case "Diciembre":
            frases.add("En el mes de " + h.getKey());
            break;
          default:
            frases.add("En el año" + h.getKey());
            break;
        }
        break;
      }

    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
    return frases;
  }
   */
  public HashMap<String, TweetMultimediaBean> getTweetsMultimediaFavoritos(List<Status> tweets) {
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
      java.util.logging.Logger.getLogger(ConexionTwitter.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
    return twsConMasMultimedia;
  }

  public ArrayList getUserHobbiesInFriends(HashMap<Long, User> usrHobb, ArrayList idsFriends) {
    ArrayList frdHob = new ArrayList();
    System.out.println("-------------- USER HOBBIES SEGUIDOS -------------");
    try {
      for (Map.Entry<Long, User> usr : usrHobb.entrySet()) {
        if (idsFriends.contains(usr.getKey())) {
          frdHob.add(usr);
          System.out.println("User hobbie seguido: " + usr.getValue().getScreenName());
        }
      }
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
    System.out.println("Nº de user hobbies seguidos: " + frdHob.size());
    return frdHob;
  }

  //La estructura que contiene los IDs y el recuento de lugares de tweets y favoritos tiene que estar ordenada en forma decreciente por el valor
  public LinkedHashMap<String, String> getLugarDomicilio(HashMap<String, Integer> idsPlacesOrden, HashMap<String, Place> placesTwYFv, List<Place> userLocations, User usr, int xMatch) {
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
      java.util.logging.Logger.getLogger(ConexionTwitter.class
              .getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }

    return posiblesDomicilios;
  }

}
