package com.ucam.files;

import com.ucam.utils.Constantes;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class LoadPropDisp {

  public HashMap<String, String> allDisp;

  public LoadPropDisp() throws Exception {
    try {
      Properties prop = new Properties();
      FileReader fr = new FileReader(System.getProperty("com.sun.aas.instanceRoot") + Constantes.FICHERO_PROPIEDADES_DISP);
      prop.load(fr);

      Enumeration<Object> claves = prop.keys();
      allDisp = new HashMap<String, String>();
      while (claves.hasMoreElements()) {
        String clave = claves.nextElement().toString();
        allDisp.put(clave.toString(), prop.get(clave).toString());
      }
    } catch (FileNotFoundException e) {
      throw new Exception("Error al cargar el fichero de propiedades:" + Constantes.FICHERO_PROPIEDADES_DISP);
    }
  }
  
  public HashMap<String, String> getAllDisp() {
    return allDisp;
  }

  public void setAllDisp(HashMap<String, String> allDisp) {
    this.allDisp = allDisp;
  }

}
