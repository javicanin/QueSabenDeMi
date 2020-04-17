package com.ucam.utils;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class FuncUtils {

    public static String claveNumGamers(int numGamers){
      int gmr = numGamers;
      if(gmr == 0)
        return Constantes.CLAVE_GUSTOS_GAMERS_NADA;
      if(FuncUtils.isBetween(gmr, 0, Constantes.NUM_GAMERS_CIERTO, 3))
        return Constantes.CLAVE_GUSTOS_GAMERS_CIERTO;
      if(FuncUtils.isBetween(gmr, Constantes.NUM_GAMERS_CIERTO + 1, Constantes.NUM_GAMERS_BASTANTES, 3))
        return Constantes.CLAVE_GUSTOS_GAMERS_BASTANTE;
      if(gmr > Constantes.NUM_GAMERS_BASTANTES)
        return Constantes.CLAVE_GUSTOS_GAMERS_MUCHO;
      return Constantes.CLAVE_INFO_NO_DISPONIBLE;
    }

    public static String claveNumPoliticos(int numPoliticos){
      int plt = numPoliticos;
      if(plt == 0)
        return Constantes.CLAVE_GUSTOS_POLITICA_NADA;
      if(FuncUtils.isBetween(plt, 0, Constantes.NUM_POLITICOS_CIERTO, 3))
        return Constantes.CLAVE_GUSTOS_POLITICA_CIERTO;
      if(FuncUtils.isBetween(plt, Constantes.NUM_POLITICOS_CIERTO + 1, Constantes.NUM_POLITICOS_BASTANTES, 3))
        return Constantes.CLAVE_GUSTOS_POLITICA_BASTANTE;
      if(plt > Constantes.NUM_POLITICOS_BASTANTES)
        return Constantes.CLAVE_GUSTOS_POLITICA_MUCHO;
      return Constantes.CLAVE_INFO_NO_DISPONIBLE;
    }

  public static boolean isBetween(int num, int inf, int sup, int included){
    switch (included){
      case 0:
        return (num>inf) && (num<sup);
      case 1:
        return (num>=inf) && (num<sup);
      case 2:
        return (num>inf) && (num<=sup);
      case 3:
        return (num>=inf) && (num<=sup);
    }
    return false;
  }
  public static int getRandomNumberInRange(int min, int max) {
    Random randomGenerator = new Random();
    return randomGenerator.nextInt(max) + min;
  }

  public static String getStringGsonLabelDataDisp(HashMap<String, Integer> dispositivos, String dataOLabel) {
    ArrayList list = new ArrayList();
    for (Map.Entry<String, Integer> dsp : dispositivos.entrySet()) {
      if ("data".equals(dataOLabel)) {
        list.add(dsp.getValue());
      } else {
        list.add(dsp.getKey());
      }

    }
    return new Gson().toJson(list);
  }

  public static Map<String, Integer> ordenarMapStrIntPorValor(Map<String, Integer> estructura) {
    return estructura.entrySet()
            .stream()
            .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
  }

  public static Map<String, Float> ordenarMapStrFloatPorValor(Map<String, Float> estructura) {
    return estructura.entrySet()
            .stream()
            .sorted((Map.Entry.<String, Float>comparingByValue().reversed()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
  }

  public static Map<Long, Integer> ordenarMapLongIntPorValor(Map<Long, Integer> estructura) {
    return estructura.entrySet()
            .stream()
            .sorted((Map.Entry.<Long, Integer>comparingByValue().reversed()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
  }

}
