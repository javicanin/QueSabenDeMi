package com.ucam.quesaben;

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
    public static Twitter getConexion(){
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("MHlYtJcH3WPGt2PLbCeEapbul")
                    .setOAuthConsumerSecret("d5pU1mZUZvnUnHiLuB9NesJmN7k34hfvVnuYvxQvErNSNXD2Hr")
                    .setOAuthAccessToken("2990804800-WFjZZIcVM1ZBE3MyihMQcv849seMLeDQEfSlRiw")
                    .setOAuthAccessTokenSecret("ZlwjV61jzUXxcOdu7WyJ2Gr6twJrp57lNC4vB2G5qtamR");
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();
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
      java.util.logging.Logger.getLogger(ConexionTwitter.class
              .getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERROR: " + ex.getMessage());
    }

  }
    
}
