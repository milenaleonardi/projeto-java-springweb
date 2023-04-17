package com.projeto.java.projetojava.rh.model.dtos;

public class AutoCompleteDTO {

    private String label;
    private String valor;

    public AutoCompleteDTO(String label, String valor) {
        this.label = label;
        this.valor = valor;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }


}
