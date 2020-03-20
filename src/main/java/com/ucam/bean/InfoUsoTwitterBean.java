package com.ucam.bean;

import java.util.ArrayList;
import java.util.Arrays;

public class InfoUsoTwitterBean {

  public ArrayList horas;
  public ArrayList labelsHoras;
  public ArrayList porcentHoras;
  public ArrayList franjaHoras;
  public ArrayList labelsFranjaHoras;
  public ArrayList porcentFranjaHoras;
  public ArrayList dias;
  public ArrayList labelsDias;
  public ArrayList porcentDias;
  public ArrayList meses;
  public ArrayList labelsMeses;
  public ArrayList porcentMeses;
  public ArrayList annios;
  public ArrayList labelsAnnios;
  public ArrayList porcentAnnios;

  public InfoUsoTwitterBean() {
    labelsHoras = new ArrayList<>(Arrays.asList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"));
    labelsFranjaHoras = new ArrayList<>(Arrays.asList("madrugada", "temprano", "mañana", "mediodia", "tarde", "noche"));
    labelsDias = new ArrayList<>(Arrays.asList("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"));
    labelsMeses = new ArrayList<>(Arrays.asList("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"));
    horas = new ArrayList<Integer>();
    porcentHoras = new ArrayList<Float>();
    labelsHoras.forEach((_item) -> {
      horas.add(0);
      porcentHoras.add(0.0F);
    });
    dias = new ArrayList<Integer>();
    porcentDias = new ArrayList<Float>();
    labelsDias.forEach((_item) -> {
      dias.add(0);
      porcentDias.add(0.0F);
    });
    meses = new ArrayList<Integer>();
    porcentMeses = new ArrayList<Float>();
    labelsMeses.forEach((_item) -> {
      meses.add(0);
      porcentMeses.add(0.0F);
    });
    annios = new ArrayList<Integer>();
    labelsAnnios = new ArrayList<String>();
    porcentAnnios = new ArrayList<Float>();
    franjaHoras = new ArrayList<Integer>();
    porcentFranjaHoras = new ArrayList<Float>();
    labelsFranjaHoras.forEach((_item) -> {
      franjaHoras.add(0);
      porcentFranjaHoras.add(0.0F);
    });

  }

  public ArrayList getHoras() {
    return horas;
  }

  public void setHoras(ArrayList horas) {
    this.horas = horas;
  }

  public ArrayList getLabelsHoras() {
    return labelsHoras;
  }

  public void setLabelsHoras(ArrayList labelsHoras) {
    this.labelsHoras = labelsHoras;
  }

  public ArrayList getPorcentHoras() {
    return porcentHoras;
  }

  public void setPorcentHoras(ArrayList porcentHoras) {
    this.porcentHoras = porcentHoras;
  }

  public ArrayList getDias() {
    return dias;
  }

  public void setDias(ArrayList dias) {
    this.dias = dias;
  }

  public ArrayList getLabelsDias() {
    return labelsDias;
  }

  public void setLabelsDias(ArrayList labelsDias) {
    this.labelsDias = labelsDias;
  }

  public ArrayList getPorcenDias() {
    return porcentDias;
  }

  public void setPorcentDias(ArrayList porcentDias) {
    this.porcentDias = porcentDias;
  }

  public ArrayList getMeses() {
    return meses;
  }

  public void setMeses(ArrayList meses) {
    this.meses = meses;
  }

  public ArrayList getLabelsMeses() {
    return labelsMeses;
  }

  public void setLabelsMeses(ArrayList labelsMeses) {
    this.labelsMeses = labelsMeses;
  }

  public ArrayList getPorcenMeses() {
    return porcentMeses;
  }

  public void setPorcentMeses(ArrayList porcentMeses) {
    this.porcentMeses = porcentMeses;
  }

  public ArrayList getAnnios() {
    return annios;
  }

  public void setAnnios(ArrayList annios) {
    this.annios = annios;
  }

  public ArrayList getLabelsAnnios() {
    return labelsAnnios;
  }

  public void setLabelsAnnios(ArrayList labelsAnnios) {
    this.labelsAnnios = labelsAnnios;
  }

  public ArrayList getPorcenAnnios() {
    return porcentAnnios;
  }

  public void setPorcentAnnios(ArrayList porcentAnnios) {
    this.porcentAnnios = porcentAnnios;
  }

  public ArrayList getFranjaHoras() {
    return franjaHoras;
  }

  public void setFranjaHoras(ArrayList franjaHoras) {
    this.franjaHoras = franjaHoras;
  }

  public ArrayList getLabelsFranjaHoras() {
    return labelsFranjaHoras;
  }

  public void setLabelsFranjaHoras(ArrayList labelsFranjaHoras) {
    this.labelsFranjaHoras = labelsFranjaHoras;
  }

  public ArrayList getPorcentFranjaHoras() {
    return porcentFranjaHoras;
  }

  public void setPorcentFranjaHoras(ArrayList porcentFranjaHoras) {
    this.porcentFranjaHoras = porcentFranjaHoras;
  }
  
  
  public String getStrFranjaFromLabelHora(int hora){
        switch (hora) {
          case 0:
          case 1:
          case 2:
          case 3:
          case 4:
          case 5:
            return (String)labelsFranjaHoras.get(0);
          case 6:
          case 7:
          case 8:
            return (String)labelsFranjaHoras.get(1);
          case 9:
          case 10:
          case 11:
            return (String)labelsFranjaHoras.get(2);
          case 12:
          case 13:
          case 14:
          case 15:
            return (String)labelsFranjaHoras.get(3);
          case 16:
          case 17:
          case 18:
          case 19:
          case 20:
            return (String)labelsFranjaHoras.get(4);
          case 21:
          case 22:
          case 23:
            return (String)labelsFranjaHoras.get(5);
        }
    return "";
  }

  public int getIntFranjaFromLabelHora(int hora){
        switch (hora) {
          case 0:
          case 1:
          case 2:
          case 3:
          case 4:
          case 5:
            return 0;
          case 6:
          case 7:
          case 8:
            return 1;
          case 9:
          case 10:
          case 11:
            return 2;
          case 12:
          case 13:
          case 14:
          case 15:
            return 3;
          case 16:
          case 17:
          case 18:
          case 19:
          case 20:
            return 4;
          case 21:
          case 22:
          case 23:
            return 5;
        }
    return -1;
  }
  
  
}
