package com.ucam.bean;

import java.util.ArrayList;
import java.util.Arrays;

public class InfoUsoTwitterBean {
    public ArrayList horas;
    public ArrayList labelsHoras;
    public ArrayList dias;
    public ArrayList labelsDias;
    public ArrayList meses;
    public ArrayList labelsMeses;
    public ArrayList annios;
    public ArrayList labelsAnnios;

    public InfoUsoTwitterBean() {
        labelsHoras = new ArrayList<>(Arrays.asList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"));
        labelsDias = new ArrayList<>(Arrays.asList("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"));
        labelsMeses = new ArrayList<>(Arrays.asList("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" ));
        horas = new ArrayList<Integer>();
        labelsHoras.forEach((_item) -> {
            horas.add(0);
        });
        dias = new ArrayList<Integer>();
        labelsDias.forEach((_item) -> {
            dias.add(0);
        });
        meses = new ArrayList<Integer>();
        labelsMeses.forEach((_item) -> {
            meses.add(0);
        });
        annios = new ArrayList<Integer>();
        labelsAnnios = new ArrayList<String>();
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
    
}
