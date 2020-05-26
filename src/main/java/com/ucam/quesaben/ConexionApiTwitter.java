package com.ucam.quesaben;

import com.ucam.bean.KeyTokenTwitterBean;
import com.ucam.files.LoadTwitterProperties;
import com.ucam.utils.Constantes;
import java.util.Map;
import java.util.logging.Level;
import twitter4j.RateLimitStatus;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author USUARIO
 */
public class ConexionApiTwitter {

  public static Twitter getConexion() {
    Twitter twitter = null;
    try {
      ConfigurationBuilder cb = new ConfigurationBuilder();
      cb.setDebugEnabled(true)
              .setOAuthConsumerKey(KeyTokenTwitterBean.OAuthConsumerKey)
              .setOAuthConsumerSecret(KeyTokenTwitterBean.OAuthConsumerSecret)
              .setOAuthAccessToken(KeyTokenTwitterBean.OAuthAccessToken)
              .setOAuthAccessTokenSecret(KeyTokenTwitterBean.OAuthAccessTokenSecret);
      TwitterFactory tf = new TwitterFactory(cb.build());
      twitter = tf.getInstance();
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(ConexionApiTwitter.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }
    return twitter;

  }

  public static void getRateLimits(boolean soloUsados) {
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
      java.util.logging.Logger.getLogger(ConexionApiTwitter.class
              .getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }

  }

}
