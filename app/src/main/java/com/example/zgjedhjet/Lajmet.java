package com.example.zgjedhjet;

public class Lajmet {
    private String id;
    private String titulliLajmit;
    private String pershkrimiLajmit;
    private String data_lajmit;

    public Lajmet(String id, String titulliLajmit, String pershkrimiLajmit, String data_lajmit) {
        this.id = id;
        this.titulliLajmit = titulliLajmit;
        this.pershkrimiLajmit = pershkrimiLajmit;
        this.data_lajmit = data_lajmit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulliLajmit() {
        return titulliLajmit;
    }

    public void setTitulliLajmit(String titulliLajmit) {
        this.titulliLajmit = titulliLajmit;
    }

    public String getPershkrimiLajmit() {
        return pershkrimiLajmit;
    }

    public void setPershkrimiLajmit(String pershkrimiLajmit) {
        this.pershkrimiLajmit = pershkrimiLajmit;
    }

    public String getData_lajmit() {
        return data_lajmit;
    }

    public void setData_lajmit(String data_lajmit) {
        this.data_lajmit = data_lajmit;
    }
}
