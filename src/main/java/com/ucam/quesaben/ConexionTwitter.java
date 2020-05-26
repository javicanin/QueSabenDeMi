/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucam.quesaben;

import com.google.gson.Gson;
import com.ucam.bean.DispositivosPropertiesBean;
import com.ucam.bean.GeoJsonPlacesBean;
import com.ucam.bean.InfoUsoTwitterBean;
import com.ucam.bean.TweetMultimediaBean;
import com.ucam.bean.UsersGamersBean;
import com.ucam.bean.UsersPoliticsBean;
import com.ucam.bean.UsersSportsBean;
import com.ucam.bean.UsuarioBean;
import com.ucam.files.LoadPropertiesFrases;
import com.ucam.utils.Constantes;
import com.ucam.utils.FuncUtils;
import java.io.IOException;
import java.util.ArrayList;
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
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Relationship;
import twitter4j.UserList;

/**
 *
 * @author F. Javier GOMEZ FERNANDEZ
 */
public class ConexionTwitter extends HttpServlet {
  User usuario;
  UsuarioBean usrBean;
  ArrayList idsFollowers;
  ArrayList idsFriends;
  List<UserList> listasPropias;
  List<UserList> listasSuscritas;
  List<UserList> listasMiembro;
  List<Status> statuses;
  List<Status> meGusta;
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
  HashMap<Long, User> deportes;
  HashMap<String, String> frasesAleatorias;
  HashMap<String, String> frasesApp;
  GeoLocation geo;
  Place place;
  String mensajeError;
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

      //Se elimina posibles caracteres o cadenas para evitar ataques scripting
      String user = request.getParameter("userTwitter");
      user = FuncUtils.reemplazosEvitaXSS(user);
      //Se valida el contenido del campo de texto segun las especificaciones de los nombres de Twitter
      mensajeError = FuncUtils.validaCampoPerfilTwitter(user, true);
      if(!"".equals(mensajeError)){
        //Si el campo de usuario tiene errores se produce una nueva excepción para que la ejecución no continue
        throw new Exception(mensajeError);
      }

      System.out.println("------------- USUARIO CONSULTADO -------------");
      usuario = twitter.showUser(user);
      usrBean = new UsuarioBean(usuario);
      System.out.println("Nombre: " + usuario.getName());
      System.out.println("ScreenName: " + usuario.getScreenName());
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
      idsFollowers = getIdsFollowers(user, 5000, 1);
      idsFriends = getIdsFriends(user, 5000, 1);
      idsSegMutuos = FuncUtils.getIdsSeguidoresMutuos(idsFollowers, idsFriends);
      //System.out.println("Seguidores Mutuos: " + idsSegMutuos.size());
      statuses = getTweets(user, 200, 20);
      meGusta = getFavoritos(user, 200, 5);
      usoTw = FuncUtils.getInfoUsoTwitter(statuses, meGusta);

      //followers = getFollowers(user, 200, 6, false, true);
      //friends = getFriends(user, 200, 6, true, true);
      listasPropias = getListasPropias(user, 200, 1);
      listasSuscritas = getListasSuscritas(user, 200, 1);
      listasMiembro = getListasMiembro(user, 200, 1);

      dispositivos = FuncUtils.getDispositivosTweets(statuses);

      //seguidoresMutuos = getSeguidoresMutuos(followers, friends);
      //listadoSeguidoresMutuos = getListadoSeguidoresMutuos(seguidoresMutuos);
      placesTweets = FuncUtils.getPlacesTweets(statuses, true);
      geoJsonPlacesBean = FuncUtils.getGeoJsonFeatures(statuses);

      idsPlacesTweets = FuncUtils.getCountIdsPlaces(placesTweets);
      mapPlacesTweets = FuncUtils.getMapPlaces(placesTweets);
      placesUserLocation = searchPlaces(999, 999, usuario.getLocation(), "city", null, Constantes.MAX_PLACES_SEARCH_API);
      lugaresResidenciaFrecuenta = FuncUtils.getLugarDomicilio(idsPlacesTweets, mapPlacesTweets, placesUserLocation, usuario, Constantes.MAX_PLACES_DOMICILIO);

      //meGustasToUserMutuos = getListaUsersMeGustas(meGusta, seguidoresMutuos);
      //retweetsToUserMutuos = getListaReteetsToSegMutuos(statuses, listadoSeguidoresMutuos);
      idsUserRetweets = FuncUtils.getListaIdsUserRetweets(statuses, idsSegMutuos);
      idsUserFavoritos = FuncUtils.getUserIdsFavoritos(meGusta, idsSegMutuos);
      idsTweetsRetweetsMe = FuncUtils.getIdsRetweetsMe(user, statuses);

      //El parametro losXMasRetweeteados indica el tamaño máximo de la estructura para después
      //solicitar los ids de usuarios. Tened en cuenta el número de peticiones por ventana es 75
      long[] idsRetweets = FuncUtils.getMaxArrayIdsRetweets(idsTweetsRetweetsMe, Constantes.MAX_RTW_SEARCH_API);
      idsUsersRetweetsMe = getRetweetsMeForIds(idsRetweets, 200);
      //listaSegMutuosReteetsMe = getListaSegMutuosReteetsMe(idUsersRetweetsMe, listadoSeguidoresMutuos);
      idsSegMutuosReteetsMe = FuncUtils.getListaSegMutuosReteetsMe2(idsUsersRetweetsMe, idsSegMutuos);

      idsUserAmigos = FuncUtils.getIdsUserAmigos2(idsUserRetweets, idsUserFavoritos, idsSegMutuosReteetsMe, idsSegMutuos, Constantes.MAX_BEST_FRIENDS);
      long[] listIdsUserAmigos = FuncUtils.getMaxArrayIdsRetweets(idsUserAmigos, Constantes.MAX_BEST_FRIENDS);

      listUsersAmigosFromIds = getListUserfromIds(listIdsUserAmigos, idsUserAmigos);

      userInteracciones = FuncUtils.getUserInteracciones(idsUserRetweets, idsUserFavoritos, idsSegMutuosReteetsMe);

      multimediaFavoritos = FuncUtils.getTweetsMultimediaFavoritos(statuses);

      gamers = getListIdsUserFromScreenName(UsersGamersBean.usuariosGamers);
      FuncUtils.getUserHobbiesInFriends(gamers, idsFriends);

      politicos = getListIdsUserFromScreenName(UsersPoliticsBean.usuariosPoliticos);
      FuncUtils.getUserHobbiesInFriends(politicos, idsFriends);

      deportes = getListIdsUserFromScreenName(UsersSportsBean.usuariosDeportes);
      FuncUtils.getUserHobbiesInFriends(deportes, idsFriends);
      
      frasesAleatorias = LoadPropertiesFrases.getRandomFrases();
      frasesApp = selectFrases(frasesAleatorias);

      ConexionApiTwitter.getRateLimits(true);

///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
      request.setAttribute("frases", frasesApp);
      request.setAttribute("usrBean", usrBean);
      request.setAttribute("amigos", listUsersAmigosFromIds);
      request.setAttribute("usrInterac", userInteracciones);
      request.setAttribute("posFriend", idsUserAmigos);
      request.setAttribute("domicilio", lugaresResidenciaFrecuenta);
/////////////////////////////////////////////////////////
//Valores pasados para las pruebas con la tabla de amigos
      //request.setAttribute("idRtw", idsUserRetweets);
      //request.setAttribute("idFv", idsUserFavoritos);
      //request.setAttribute("idRtwMe", idsSegMutuosReteetsMe);
/////////////////////////////////////////////////////////

      String usoTwt = new Gson().toJson(usoTw);
      request.setAttribute("usoTw", usoTwt);

      String fraseHoras = usoTw.titularUsoTwitter("horas");
      String fraseUsoGeneral = usoTw.titularUsoTwitter("general");

      request.setAttribute("fraseHoras", fraseHoras);
      request.setAttribute("fraseUsoGeneral", fraseUsoGeneral);

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
      String error = ex.getMessage();
      if(error!=null){
        if(error.contains("User not found.")){
          mensajeError = "El usuario no existe";
          request.setAttribute("msgError", mensajeError);
        }else{
          request.setAttribute("msgError", ex.getMessage());
        }
      }
      else{
        mensajeError = "Error desconocido.\nInténtelo de nuevo más tarde.\nSi el error persiste consulte con el administrador del sistema.";
        request.setAttribute("msgError", mensajeError);
      }
      RequestDispatcher dispatcher = request.getRequestDispatcher(response.encodeURL("blank.jsp"));
      dispatcher.forward(request, response);

    }
  }

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
  public HashMap<String, String> selectFrases(HashMap<String, String> fr) {
    HashMap<String, String> frases = new HashMap();
    String clave = usrBean.clavePerfilVerificado();
    frases.put(Constantes.CLAVE_VERIFICADO, fr.get(clave));

    clave = usrBean.clavePerfilProtegido();
    frases.put(Constantes.CLAVE_PROTEGIDO, fr.get(clave));

    clave = usrBean.claveVolumenUso();
    frases.put(Constantes.CLAVE_VOL_USO, fr.get(clave));

    clave = usrBean.claveNumSeguidores();
    frases.put(Constantes.CLAVE_NUM_SEGUIDORES, fr.get(clave));

    clave = usrBean.claveNumAmigos();
    frases.put(Constantes.CLAVE_NUM_AMIGOS, fr.get(clave));

    clave = FuncUtils.claveNumGamers(FuncUtils.getUserHobbiesInFriends(gamers, idsFriends).size());
    frases.put(Constantes.CLAVE_GUSTOS_GAMERS, fr.get(clave));

    clave = FuncUtils.claveNumPoliticos(FuncUtils.getUserHobbiesInFriends(politicos, idsFriends).size());
    frases.put(Constantes.CLAVE_GUSTOS_POLITICA, fr.get(clave));

    clave = FuncUtils.claveNumDeportes(FuncUtils.getUserHobbiesInFriends(deportes, idsFriends).size());
    frases.put(Constantes.CLAVE_GUSTOS_DEPORTES, fr.get(clave));
    
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

    return friends;
  }

  public ResponseList<Status> getTweets(String userName, int pageSize, int numLlamadas) {
    ResponseList<Status> tweets = null;
    ResponseList<Status> tweetsPage;
    Twitter twitter = ConexionApiTwitter.getConexion();
    System.out.println("################################################");
    System.out.println("-------------------- TWEETS --------------------");
    //Listado de Tweets de un usuario
    Paging paginacion = new Paging(1, pageSize);
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
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
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
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
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
    return listas;
  }

  //// METODO EN PRUEBAS PARA OBTENER LUGARES
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
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(ConexionTwitter.class
              .getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
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

}