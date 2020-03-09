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
import com.ucam.bean.UsuarioBean;
import java.io.IOException;
import static java.lang.Math.abs;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.stream.Collectors;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.Chart;
import javax.json.Json;
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
import twitter4j.RateLimitStatus;
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
    final String DISP_ANDROID = "Twitter for Android";
    final String DISP_IPHONE = "Twitter for iPhone";
    final String DISP_MOBIL_WEB = "Mobile Web";
    final String DISP_WEBSITES = "Twitter for Websites";
    final String DISP_OTROS = "Otro acceso Twitter";

    User usuario;
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
    List<Place> placesTweetsYFavoritos;
    GeoJsonPlacesBean geoJsonPlacesBean;
    List<Place> placesUserLocation;
    ArrayList idsSegMutuos;
    HashMap<String, Integer> dispositivos;
    HashMap mapPlacesTweetsyFavoritos;
    HashMap listadoSeguidoresMutuos;
    HashMap meGustasToUserMutuos;
    HashMap retweetsToUserMutuos;
    HashMap idsTweetsRetweetsMe;
    HashMap idsUserFavoritos;
    HashMap idsUserRetweets;
    HashMap idsUsersRetweetsMe;
    HashMap idsSegMutuosReteetsMe;
    HashMap idsUserAmigos;
    HashMap listUsersAmigosFromIds;
    HashMap idsPlacesTweetsYFavoritos;
    HashMap lugaresResidenciaFrecuenta;
    ArrayList horasTweetsYFavoritos;
    InfoUsoTwitterBean usoTw;

    GeoLocation geo;
    Place place;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            /*            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("MHlYtJcH3WPGt2PLbCeEapbul")
                    .setOAuthConsumerSecret("d5pU1mZUZvnUnHiLuB9NesJmN7k34hfvVnuYvxQvErNSNXD2Hr")
                    .setOAuthAccessToken("2990804800-WFjZZIcVM1ZBE3MyihMQcv849seMLeDQEfSlRiw")
                    .setOAuthAccessTokenSecret("ZlwjV61jzUXxcOdu7WyJ2Gr6twJrp57lNC4vB2G5qtamR");
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();
             */
            Twitter twitter = ConexionApiTwitter.getConexion();

            System.out.println("------------- USUARIO APLICACION -------------");
            User usuarioApp = twitter.verifyCredentials();
            System.out.println("Nombre: " + usuarioApp.getName());
            System.out.println("Descripcion: " + usuarioApp.getDescription());
            System.out.println("Id Usuario: " + usuarioApp.getId());
            System.out.println("Número Seguidores: " + usuarioApp.getFollowersCount());

            //javicanin1
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
            UsuarioBean usrBean = new UsuarioBean(usuario);
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

            getRateLimits(true);
            //getUserFriendships(370199897L, 385337732L);
            //searchTweets();
            idsFollowers = getIdsFollowers(user, 5000, 3);
            idsFriends = getIdsFriends(user, 5000, 3);
            idsSegMutuos = getIdsSeguidoresMutuos(idsFollowers, idsFriends);
            System.out.println("Seguidores Mutuos: " + idsSegMutuos.size());
            statuses = getTweets(user, 200, 10);
            meGusta = getFavoritos(user, 200, 5);
            usoTw = getInfoUsoTwitter(statuses, meGusta);

            //followers = getFollowers(user, 200, 6, false, true);
            //friends = getFriends(user, 200, 6, true, true);
            listasPropias = getListasPropias(user, 200, 1);
            listasSuscritas = getListasSuscritas(user, 200, 1);
            listasMiembro = getListasMiembro(user, 200, 1);

            dispositivos = getDispositivosTweets(statuses);

            //seguidoresMutuos = getSeguidoresMutuos(followers, friends);
            //listadoSeguidoresMutuos = getListadoSeguidoresMutuos(seguidoresMutuos);
            placesTweetsYFavoritos = getPlacesTweetsYFavoritos(statuses, meGusta, true);
            geoJsonPlacesBean = getGeoJsonFeatures(statuses, meGusta);

            idsPlacesTweetsYFavoritos = getCountIdsPlaces(placesTweetsYFavoritos);
            mapPlacesTweetsyFavoritos = getMapPlaces(placesTweetsYFavoritos);
            placesUserLocation = searchPlaces(999, 999, usuario.getLocation(), "city", null, 5);
            lugaresResidenciaFrecuenta = getLugarDomicilio(idsPlacesTweetsYFavoritos, mapPlacesTweetsyFavoritos, placesUserLocation, usuario, 10);

            //meGustasToUserMutuos = getListaUsersMeGustas(meGusta, seguidoresMutuos);
            //retweetsToUserMutuos = getListaReteetsToSegMutuos(statuses, listadoSeguidoresMutuos);
            idsUserRetweets = getListaIdsUserRetweets(statuses, idsSegMutuos);
            idsUserFavoritos = getUserIdsFavoritos(meGusta, idsSegMutuos);
            idsTweetsRetweetsMe = getIdsRetweetsMe(user, statuses);

            //El parametro losXMasRetweeteados indica el tamaño máximo de la estructura para después
            //solicitar los ids de usuarios. Tened en cuenta el número de peticiones por ventana es 75
            long[] idsRetweets = getMaxArrayIdsRetweets(idsTweetsRetweetsMe, 25);
            idsUsersRetweetsMe = getRetweetsMeForIds(idsRetweets, 200);
            //listaSegMutuosReteetsMe = getListaSegMutuosReteetsMe(idUsersRetweetsMe, listadoSeguidoresMutuos);
            idsSegMutuosReteetsMe = getListaSegMutuosReteetsMe2(idsUsersRetweetsMe, idsSegMutuos);

            idsUserAmigos = getIdsUserAmigos2(idsUserRetweets, idsUserFavoritos, idsSegMutuosReteetsMe, idsSegMutuos, 5);
            long[] listIdsUserAmigos = getMaxArrayIdsRetweets(idsUserAmigos, 25);

            listUsersAmigosFromIds = getListUserfromIds(listIdsUserAmigos, idsUserAmigos);

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
            getRateLimits(true);

            //request.setAttribute("followers", followers);
            request.setAttribute("usrBean", usrBean);
            request.setAttribute("amigos", listUsersAmigosFromIds);
            request.setAttribute("domicilio", lugaresResidenciaFrecuenta);

            /*String horas = new Gson().toJson(usoTw.getHoras());
            String labelsHoras = new Gson().toJson(usoTw.getLabelsHoras());
            String dias = new Gson().toJson(usoTw.getDias());
            String labelsDias = new Gson().toJson(usoTw.getLabelsDias());
            String meses = new Gson().toJson(usoTw.getMeses());
            String labelsMeses = new Gson().toJson(usoTw.getLabelsMeses());
            String annios = new Gson().toJson(usoTw.getAnnios());
            String labelsAnnios = new Gson().toJson(usoTw.getLabelsAnnios());*/
            String usoTwt = new Gson().toJson(usoTw);
            request.setAttribute("usoTw", usoTwt);
            /*request.setAttribute("horas", horas);
            request.setAttribute("labelsHoras", labelsHoras);
            request.setAttribute("dias", dias);
            request.setAttribute("labelsDias", labelsDias);
            request.setAttribute("meses", meses);
            request.setAttribute("labelsMeses", labelsMeses);
            request.setAttribute("annios", annios);
            request.setAttribute("labelsAnnios", labelsAnnios);*/

            ArrayList datosDispositivos = new ArrayList();
            ArrayList labelsDispositivos = new ArrayList();
            for (Map.Entry<String, Integer> dsp : dispositivos.entrySet()) {
                labelsDispositivos.add(dsp.getKey());
                datosDispositivos.add(dsp.getValue());
            }

            String labelDispJson = new Gson().toJson(labelsDispositivos);
            String datosDispJson = new Gson().toJson(datosDispositivos);
            request.setAttribute("labelsDispositivos", labelDispJson);
            request.setAttribute("datosDispositivos", datosDispJson);

            String geoJsonPlaces = new Gson().toJson(geoJsonPlacesBean);
            request.setAttribute("geoJsonPlaces", geoJsonPlaces);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher(response.encodeURL("index.jsp"));
            dispatcher.forward(request, response);

        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ConexionTwitter.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

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
/*
        for (Status st : tweets) {
            System.out.println(st.getId() + " # Retweets/Me Gusta: " + st.getRetweetCount() + "/" + st.getFavoriteCount() + " ### " + st.getText());
        }
*/        
        System.out.println("OJO SOLO SE ESTAN IMPRIMIENTO LOS QUE CONTIENEN MULTIMEDIA");
        for (Status st : tweets) {
            if (st.getMediaEntities().length!= 0)
                System.out.println(st.toString());
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
        } catch (TwitterException ex) {
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
        listaRetweets = (HashMap<String, Integer>) ordenarMapStrIntPorValor(listaRetweets);

/*        for (Map.Entry<String, Integer> tw : listaRetweets.entrySet()) {
            System.out.println(tw.toString());
        }
*/        
        return listaRetweets;
    }

    public HashMap getListaIdsUserRetweets(List<Status> tweets, ArrayList idsSegMutuos) {
        HashMap<Long, Integer> listaRetweets = new HashMap<>();
        System.out.println("------------ IDs USER RETWEETS -------------");
        Long usr;
        for (Status st : tweets) {
            //Si es un retweet entonces el campo RetweetedStatus contendrá el usuario creador
            //Si es una respuesta a otro tweet entonces el campo InReplyToScreenName contendrá el usuario creador
            if (st.getRetweetedStatus() != null || st.getInReplyToScreenName() != null) {
                if (st.getRetweetedStatus() != null) {
                    usr = st.getRetweetedStatus().getUser().getId();
                } else {
                    usr = st.getInReplyToUserId();
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
        listaRetweets = (HashMap<Long, Integer>) ordenarMapLongIntPorValor(listaRetweets);
/*
        for (Map.Entry<Long, Integer> tw : listaRetweets.entrySet()) {
            System.out.println(tw.toString());
        }
*/       
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

        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ConexionTwitter.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: " + ex.getMessage());
        }

        for (Status st : favoritos) {
            System.out.println(st.getId() + ": " + st.getText() + "| Retweets/Me Gusta: " + st.getRetweetCount() + "/" + st.getFavoriteCount());
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
        /*for (UserList ul : listas) {
            System.out.println("" + ul.toString());
        }*/
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
        return listas;
    }

    //REFACTORIZAR
    public HashMap<String, Integer> getDispositivosTweets(List<Status> statuses) {
        HashMap<String, Integer> dispositivos = new HashMap<String, Integer>();
        System.out.println("################################################");
        System.out.println("----------------- DISPOSITIVOS -----------------");
        try {
            String dispositivo = "";
            for (Status status : statuses) {
                dispositivo = status.getSource();
                System.out.println(dispositivo);
                if (dispositivo.contains(DISP_ANDROID)) {
                    dispositivo = DISP_ANDROID;
                    if (!dispositivos.containsKey(DISP_ANDROID)) {
                        dispositivos.put(DISP_ANDROID, 1);
                    } else {
                        int valor = dispositivos.get(dispositivo);
                        valor++;
                        dispositivos.replace(dispositivo, valor);
                    }
                }
                if (dispositivo.contains(DISP_IPHONE)) {
                    dispositivo = DISP_IPHONE;
                    if (!dispositivos.containsKey(DISP_IPHONE)) {
                        dispositivos.put(DISP_IPHONE, 1);
                    } else {
                        int valor = dispositivos.get(dispositivo);
                        valor++;
                        dispositivos.replace(dispositivo, valor);
                    }
                }
                if (dispositivo.contains(DISP_MOBIL_WEB)) {
                    dispositivo = DISP_MOBIL_WEB;
                    if (!dispositivos.containsKey(DISP_MOBIL_WEB)) {
                        dispositivos.put(DISP_MOBIL_WEB, 1);
                    } else {
                        int valor = dispositivos.get(dispositivo);
                        valor++;
                        dispositivos.replace(dispositivo, valor);
                    }
                }
                if (dispositivo.contains(DISP_WEBSITES)) {
                    dispositivo = DISP_WEBSITES;
                    if (!dispositivos.containsKey(DISP_WEBSITES)) {
                        dispositivos.put(DISP_WEBSITES, 1);
                    } else {
                        int valor = dispositivos.get(dispositivo);
                        valor++;
                        dispositivos.replace(dispositivo, valor);
                    }
                }
                if (!dispositivo.contains(DISP_ANDROID) && !dispositivo.contains(DISP_IPHONE)
                        && !dispositivo.contains(DISP_MOBIL_WEB) && !dispositivo.contains(DISP_WEBSITES)) {
                    dispositivo = DISP_OTROS;
                    if (!dispositivos.containsKey(DISP_OTROS)) {
                        dispositivos.put(DISP_OTROS, 1);
                    } else {
                        int valor = dispositivos.get(dispositivo);
                        valor++;
                        dispositivos.replace(dispositivo, valor);
                    }
                }
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

    public List<Place> getPlacesTweetsYFavoritos(List<Status> statuses, List<Status> favoritos, boolean repetidos) {
        List<Place> lugares = new ArrayList<Place>();
        System.out.println("################################################");
        System.out.println("---------- LUGARES TWEETS Y FAVORITOS ----------");
        try {
            Place lugar;
            for (Status st : statuses) {
                lugar = st.getPlace();
                if (lugar != null && (!lugares.contains(lugar) || repetidos)) {
                    lugares.add(st.getPlace());
              }
            }
            for (Status fv : favoritos) {
                lugar = fv.getPlace();
                if (lugar != null && (!lugares.contains(lugar) || repetidos)) {
                    lugares.add(fv.getPlace());
                }
            }
/*
            for (Place p : lugares) {
                //System.out.println(p.getId() + " # " + p.getName() + " # " + p.getFullName());
                System.out.println(p.getName() + " # " + p.getFullName() + p.toString() + " ## ");
                GeoLocation[][] boxCoords = p.getBoundingBoxCoordinates();
                if (boxCoords != null) {
                    for (GeoLocation x[] : boxCoords) {
                        for (GeoLocation y : x) {
                            System.out.println("lat/long: " + y.getLatitude() + ", " + y.getLongitude());
                        }
                    }
                }
            }
*/
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

    public GeoJsonPlacesBean getGeoJsonFeatures(List<Status> statuses, List<Status> favoritos) {
        GeoJsonPlacesBean gJsPl = new GeoJsonPlacesBean();
        System.out.println("--------------- GEOJSON PLACES ---------------");
        try {
            Place lugar;
            for (Status st : statuses) {
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
            for (Status fv : favoritos) {
                lugar = fv.getPlace();
                if (lugar != null) {
                    PropertiesGeoJson prop = new PropertiesGeoJson();
                    prop.nombre = lugar.getName();
                    prop.nombreCompleto = lugar.getFullName();
                    prop.tipo = lugar.getPlaceType();
                    prop.pais = lugar.getCountry();
                    String fecha = new SimpleDateFormat("EEEEEEEEE, dd/MM/yyyy HH:mm:ss").format(fv.getCreatedAt());
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
            for (Map.Entry<String, Integer> lgr : lugares.entrySet()) {
                System.out.println(lgr.getKey() + ":" + lgr.getValue());
            }

            lugares = (HashMap<String, Integer>) ordenarMapStrIntPorValor(lugares);
            for (Map.Entry<String, Integer> lg : lugares.entrySet()) {
                System.out.println(lg.getKey() + ": " + lg.getValue());
            }

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
        } catch (TwitterException ex) {
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
        listaMeGustas = (HashMap<String, Integer>) ordenarMapStrIntPorValor(listaMeGustas);
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
        for (Status st : status) {
            //Con getRetweetedStatus() == null obtenemos los tweets propios que no son retweet
            //Además extraemos aquellos que hayan sido retwiteados al menos una vez
            if (st.getRetweetCount() > 0 && st.getRetweetedStatus() == null) {
                long idRetweet = st.getId();
                numsRetweets.put(idRetweet, st.getRetweetCount());
            }
        }
        //Ordena la estructura por el número de Retweets
        numsRetweets = (HashMap<Long, Integer>) ordenarMapLongIntPorValor(numsRetweets);
/*
        for (Map.Entry<Long, Integer> rtm : numsRetweets.entrySet()) {
            System.out.println(rtm.getKey() + ":" + rtm.getValue());
        }
*/
        return numsRetweets;
    }

    public HashMap getUserIdsFavoritos(List<Status> meGustas, ArrayList segMutuos) {
        HashMap<Long, Integer> usrMeGustas = new HashMap<>();
        System.out.println("------------ USER IDs FAVORITOS ------------");
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
        usrMeGustas = (HashMap<Long, Integer>) ordenarMapLongIntPorValor(usrMeGustas);
/*
        for (Map.Entry<Long, Integer> usr : usrMeGustas.entrySet()) {
            System.out.println(usr.getKey() + ":" + usr.getValue());
        }
*/
        return usrMeGustas;
    }


    public long[] getMaxArrayIdsRetweets(HashMap<Long, Integer> idsRetweets, int losXMasRetweeteados) {
        long[] idsRtw = new long[losXMasRetweeteados];
        int cont = 0;
        for (Map.Entry<Long, Integer> ids : idsRetweets.entrySet()) {
            if (cont < losXMasRetweeteados) {
                idsRtw[cont] = ids.getKey();
                cont = cont + 1;
            } else {
                break;
            }
        }
        return idsRtw;
    }

    public HashMap getRetweetsMeForIds(long[] tweetsIds, int groupSize) {
        HashMap<Long, Integer> numsRetweets = new HashMap<Long, Integer>();
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
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ConexionTwitter.class
                    .getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: " + ex.getMessage());
        }
/*
        for (Map.Entry<Long, Integer> rtm : numsRetweets.entrySet()) {
            System.out.println(rtm.getKey() + ":" + rtm.getValue());
        }
*/
        return numsRetweets;
    }

    
    public LinkedHashMap<Long, UsuarioBean> getListUserfromIds(long[] userIds, HashMap<Long, Integer> idsUserAmigos) {
        LinkedHashMap<Long, UsuarioBean> bestFriends = new LinkedHashMap<Long, UsuarioBean>();
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

    
    public HashMap getListaSegMutuosReteetsMe(HashMap<Long, Integer> idsUsersRetweetsMe, HashMap segMutuos) {
        HashMap<Long, Integer> listaSegMutuosRetweetsMe = new HashMap<>();
        System.out.println("------------ RETWEETS ME SEG MUTUOS -------------");
        for (Map.Entry<Long, Integer> ids : idsUsersRetweetsMe.entrySet()) {
            if (segMutuos.containsKey(ids.getKey())) {
                listaSegMutuosRetweetsMe.put(ids.getKey(), ids.getValue());
            }
        }
        //Ordena la estructura por el número de Retweets
        listaSegMutuosRetweetsMe = (HashMap<Long, Integer>) ordenarMapLongIntPorValor(listaSegMutuosRetweetsMe);
/*
        for (Map.Entry<Long, Integer> usrRtm : listaSegMutuosRetweetsMe.entrySet()) {
            System.out.println(usrRtm.getKey() + ":" + usrRtm.getValue());
        }
*/
        return listaSegMutuosRetweetsMe;
    }

    public HashMap getListaSegMutuosReteetsMe2(HashMap<Long, Integer> idsUsersRetweetsMe, ArrayList segMutuos) {
        HashMap<Long, Integer> listaSegMutuosRetweetsMe = new HashMap<>();
        System.out.println("------------ RETWEETS ME SEG MUTUOS -------------");
        for (Map.Entry<Long, Integer> ids : idsUsersRetweetsMe.entrySet()) {
            if (segMutuos.contains(ids.getKey())) {
                listaSegMutuosRetweetsMe.put(ids.getKey(), ids.getValue());
            }
        }
        //Ordena la estructura por el número de Retweets
        listaSegMutuosRetweetsMe = (HashMap<Long, Integer>) ordenarMapLongIntPorValor(listaSegMutuosRetweetsMe);
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
        idsAmigos3 = (LinkedHashMap<Long, Integer>) ordenarMapLongIntPorValor(idsAmigos3);

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
        idsAmigos2 = (LinkedHashMap<Long, Integer>) ordenarMapLongIntPorValor(idsAmigos2);

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
        idsAmigos1 = (LinkedHashMap<Long, Integer>) ordenarMapLongIntPorValor(idsAmigos1);

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

    public LinkedHashMap getIdsUserAmigos2(HashMap<Long, Integer> idsUsrRtw, HashMap<Long, Integer> idsUsrFv, HashMap<Long, Integer> idsUsrRtwMe, ArrayList segMutuos, int maxAmigos) {
        LinkedHashMap<Long, Integer> posicionamientos = new LinkedHashMap();
        System.out.println("------------ IDs USERS AMIGOS -------------");

        int pos = 0;
        for (Map.Entry<Long, Integer> usrRtw : idsUsrRtw.entrySet()) {
            int inversaPosicion = idsUsrRtw.size() - pos;
            posicionamientos.put(usrRtw.getKey(), inversaPosicion);
            pos++;
        }
        pos = 0;
        for (Map.Entry<Long, Integer> usrFv : idsUsrFv.entrySet()) {
            int inversaPosicion = idsUsrFv.size() - pos;
            if (posicionamientos.containsKey(usrFv.getKey())) {
                int posExistente = posicionamientos.get(usrFv.getKey());
                posicionamientos.put(usrFv.getKey(), posExistente + inversaPosicion);
            } else {
                posicionamientos.put(usrFv.getKey(), inversaPosicion);
            }
            pos++;
        }
        pos = 0;
        for (Map.Entry<Long, Integer> usrRtwMe : idsUsrRtwMe.entrySet()) {
            int inversaPosicion = idsUsrRtwMe.size() - pos;
            if (posicionamientos.containsKey(usrRtwMe.getKey())) {
                int posExistente = posicionamientos.get(usrRtwMe.getKey());
                posicionamientos.put(usrRtwMe.getKey(), posExistente + inversaPosicion);
            } else {
                posicionamientos.put(usrRtwMe.getKey(), inversaPosicion);
            }
            pos++;
        }
        posicionamientos = (LinkedHashMap<Long, Integer>) ordenarMapLongIntPorValor(posicionamientos);

        //Si no existen interacciones entre usuarios que sean seguidores mutuos, se elige un numero especificado de estos seguidores mutos
        if (posicionamientos.size() == 0) {
            for (int i = 0; i < segMutuos.size(); i++) {
                if (posicionamientos.size() < maxAmigos) {
                    posicionamientos.put((Long) segMutuos.get(i), 1);
                } else {
                    break;
                }
            }
        }

        for (Map.Entry<Long, Integer> psc : posicionamientos.entrySet()) {
            System.out.println(psc.getKey() + ":" + psc.getValue());
        }

        return posicionamientos;
    }

//REFACTORIZAR
    public InfoUsoTwitterBean getInfoUsoTwitter(List<Status> tweets, List<Status> favoritos) {
        InfoUsoTwitterBean usoTw = new InfoUsoTwitterBean();

        /*ArrayList<Integer> horas = new ArrayList();//(Arrays.asList("5", "9", "1", "0", "1", "3", "1", "3", "7", "4", "16", "16", "20", "11", "7", "27", "18", "11", "13", "18", "14", "12", "11", "25"));
        ArrayList<Integer> dias = new ArrayList(); //(Arrays.asList(5, 9, 5, 4, 8, 15, 150));
        ArrayList<Integer> meses = new ArrayList(); //(Arrays.asList("5", "9", "15", "54", "8", "15", "25", "15", "32", "15", "25", "34"));
        ArrayList<Integer> annios = new ArrayList(); //(Arrays.asList(35, 45, 62, 47, 85, 65, 120, 1560));
        ArrayList<String> labelAnnios = new ArrayList(); //(Arrays.asList(2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020));
        usoTw.setHoras(horas);
        usoTw.setDias(dias);
        usoTw.setMeses(meses);
        usoTw.setAnnios(annios);*/
        HashMap<Integer, Integer> usoAnnios = new HashMap<>();
        /*usoTw.setLabelsHoras(labelHoras);
        usoTw.setLabelsDias(labelDias);
        usoTw.setLabelsMeses(labelMeses);
        usoTw.setLabelsAnnios(labelAnnios);*/
        System.out.println("---------------- USO TWITTER -----------------");
        try {
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

                valor = (Integer) usoTw.dias.get(numDia - 1);
                valor++;
                usoTw.dias.set(numDia - 1, valor);

                valor = (Integer) usoTw.meses.get(numMes - 1);
                valor++;
                usoTw.meses.set(numMes - 1, valor);

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

                valor = (Integer) usoTw.dias.get(numDia - 1);
                valor++;
                usoTw.dias.set(numDia - 1, valor);

                valor = (Integer) usoTw.meses.get(numMes - 1);
                valor++;
                usoTw.meses.set(numMes - 1, valor);

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
                } else {
                    usoTw.annios.add(0);
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

    public void getRateLimits(boolean soloUsados) {
        Twitter twitter = ConexionApiTwitter.getConexion();
        System.out.println("----------------- RATE LIMITS -----------------");
        Map<String, RateLimitStatus> rateLimitStatus;
        try {
            rateLimitStatus = twitter.getRateLimitStatus();
            for (Map.Entry<String, RateLimitStatus> rLS : rateLimitStatus.entrySet()) {
                String key = rLS.getKey();
                RateLimitStatus value = rLS.getValue();
                if (soloUsados && (value.getLimit() != value.getRemaining())) {
                    System.out.println(key + "(Limit/Remaining -> seconds): " + value.getLimit() + "/" + value.getRemaining() + " -> " + value.getSecondsUntilReset());
                }
            }
        } catch (TwitterException ex) {
            java.util.logging.Logger.getLogger(ConexionTwitter.class
                    .getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: " + ex.getMessage());
        }

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

    public Map<String, Integer> ordenarMapStrIntPorValor(Map<String, Integer> estructura) {
        return estructura.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public Map<Long, Integer> ordenarMapLongIntPorValor(Map<Long, Integer> estructura) {
        return estructura.entrySet()
                .stream()
                .sorted((Map.Entry.<Long, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

}
