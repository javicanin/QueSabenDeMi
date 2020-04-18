package com.ucam.files;

import com.ucam.bean.FrasesBean;
import com.ucam.utils.Constantes;
import com.ucam.utils.FuncUtils;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class LoadPropertiesFrases {

  //public static HashMap<String, String> allFrases;
  
  public static void cargarPropertiesFrases(String nombreFichero) throws Exception {
    try {
      Properties prop = new Properties();
      FileReader fr = new FileReader(System.getProperty("com.sun.aas.instanceRoot") + nombreFichero);
      prop.load(fr);

      Enumeration<Object> claves = prop.keys();
      FrasesBean.allFrases = new HashMap();
      while (claves.hasMoreElements()) {
        Object clave = claves.nextElement();
        FrasesBean.allFrases.put(clave.toString(), prop.get(clave).toString());
      }

    } catch (FileNotFoundException e) {
      throw new Exception("Error al cargar el fichero de propiedades:" + nombreFichero);
    }
  }

  public static HashMap<String, String> getRandomFrases() {
    HashMap<String, Integer> tiposFrases = new HashMap();
    HashMap<String, String> randomFrases;
    String clave = "";
    String claveUnica = "";
    int cont = 0;
    for (Map.Entry<String, String> af : FrasesBean.allFrases.entrySet()) {
      clave = af.getKey();
      claveUnica = clave.substring(0, clave.indexOf(Constantes.CHAR_SEPARADOR_PROPERTIES));
      if (tiposFrases.containsKey(claveUnica)) {
        cont = (int) tiposFrases.get(claveUnica);
        tiposFrases.put(claveUnica, ++cont);
      } else {
        tiposFrases.put(claveUnica, 1);
      }
    }
    
    int numFraseAleatoria = 0;
    randomFrases = new HashMap();
    for (Map.Entry<String, Integer> tf : tiposFrases.entrySet()) {
       numFraseAleatoria = FuncUtils.getRandomNumberInRange(1, tf.getValue());
       claveUnica = tf.getKey() + Constantes.CHAR_SEPARADOR_PROPERTIES + Integer.toString(numFraseAleatoria);
       String frase = FrasesBean.allFrases.get(claveUnica);
       randomFrases.put(tf.getKey(), frase);
    }
    return randomFrases;
  }

}
