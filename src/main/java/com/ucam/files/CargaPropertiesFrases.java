package com.ucam.files;

import com.ucam.utils.Constantes;
import com.ucam.utils.FuncUtils;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CargaPropertiesFrases {

  public HashMap<String, String> allFrases;
  public HashMap<String, String> randomFrases;

  public CargaPropertiesFrases() throws Exception {
    try {
      Properties prop = new Properties();
      FileReader fr = new FileReader(System.getProperty("com.sun.aas.instanceRoot") + Constantes.FICHERO_PROPIEDADES_FRASES);
      prop.load(fr);

      Enumeration<Object> claves = prop.keys();
      allFrases = new HashMap<String, String>();
      while (claves.hasMoreElements()) {
        Object clave = claves.nextElement();
        allFrases.put(clave.toString(), prop.get(clave).toString());
      }

      getRandomProperties();
      
    } catch (FileNotFoundException e) {
      throw new Exception("Error al cargar el fichero de propiedades:" + Constantes.FICHERO_PROPIEDADES_FRASES);
    }
  }

  public void getRandomProperties() {
    HashMap<String, Integer> tiposFrases = new HashMap();
    String clave = "";
    String claveUnica = "";
    int cont = 0;
    for (Map.Entry<String, String> af : allFrases.entrySet()) {
      clave = af.getKey();
      claveUnica = clave.substring(0, clave.indexOf(Constantes.CHAR_SEPARADOR_NUM_FRASE));
      if (tiposFrases.containsKey(claveUnica)) {
        cont = (int) tiposFrases.get(claveUnica);
        tiposFrases.put(claveUnica, ++cont);
      } else {
        tiposFrases.put(claveUnica, 1);
      }
    }
    
    int numFraseAleatoria = 0;
    randomFrases = new HashMap<String, String>();
    for (Map.Entry<String, Integer> tf : tiposFrases.entrySet()) {
       numFraseAleatoria = FuncUtils.getRandomNumberInRange(1, tf.getValue());
       claveUnica = tf.getKey() + Constantes.CHAR_SEPARADOR_NUM_FRASE + Integer.toString(numFraseAleatoria);
       String frase = allFrases.get(claveUnica);
       randomFrases.put(tf.getKey(), frase);
    }
  }

}
