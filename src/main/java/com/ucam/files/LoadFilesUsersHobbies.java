package com.ucam.files;

import java.io.*;
import java.util.ArrayList;

public class LoadFilesUsersHobbies {

  public static String[] leerFicheroUsuarios(String nombreArchivo) {
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    String[] usuarios = null;

    try {
      // Apertura del fichero y creacion de BufferedReader para poder
      // hacer una lectura comoda (disponer del metodo readLine()).
      File f = new File("DBDriverInfo.properties");
      //System.out.println(f.getAbsolutePath());
      archivo = new File(System.getProperty("com.sun.aas.instanceRoot") + nombreArchivo);
      fr = new FileReader(archivo);
      br = new BufferedReader(fr);
      // Lectura del fichero
      ArrayList usrs = new ArrayList();
      String usr;
      while ((usr = br.readLine()) != null) {
        usrs.add(usr);
      }
      usuarios = new String[usrs.size()];
      for (int i = 0; i < usrs.size(); i++) {
        usuarios[i] = (String) usrs.get(i);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      // En el finally cerramos el fichero, para asegurarnos
      // que se cierra tanto si todo va bien como si salta 
      // una excepcion.
      try {
        if (null != fr) {
          fr.close();
        }
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    }
    return usuarios;
  }
}
