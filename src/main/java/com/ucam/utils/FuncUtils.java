package com.ucam.utils;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class FuncUtils {

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
