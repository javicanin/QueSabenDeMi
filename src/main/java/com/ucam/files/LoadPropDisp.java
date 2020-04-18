package com.ucam.files;

import com.ucam.bean.DispositivosPropertiesBean;
import com.ucam.utils.Constantes;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class LoadPropDisp {

  public static void cargaDispositivosFromProperties(String nombreFichero) throws Exception {
    try {
      Properties prop = new Properties();
      FileReader fr = new FileReader(System.getProperty("com.sun.aas.instanceRoot") + nombreFichero);
      prop.load(fr);

      Enumeration<Object> claves = prop.keys();
      DispositivosPropertiesBean.allDisp = new HashMap<String, String>();
      while (claves.hasMoreElements()) {
        String clave = claves.nextElement().toString();
        DispositivosPropertiesBean.allDisp.put(clave.toString(), prop.get(clave).toString());
      }
    } catch (FileNotFoundException e) {
      throw new Exception("Error al cargar el fichero de propiedades:" + nombreFichero);
    }
  }
  
}
