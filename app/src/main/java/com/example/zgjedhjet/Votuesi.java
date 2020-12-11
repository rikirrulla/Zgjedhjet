package com.example.zgjedhjet;

public class Votuesi {
    private String id;
    private String emri;
    private String mbiemri;
    private String emaili;
    private String fjalkalimi;
    private String adresa;
    private String vendi;
    private String komuna;
    private String numri_leternjofimit;
    private String data_lindjes;
    private String data_regjistrimit;

    public Votuesi(int anInt, String string) {
    }

    public Votuesi(String id, String emaili) {
        this.id = id;
        this.emaili = emaili;
    }

    public Votuesi(String id, String emri, String mbiemri, String emaili, String fjalkalimi, String adresa, String vendi, String komuna, String numri_leternjofimit, String data_lindjes, String data_regjistrimit) {
        this.id = id;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.emaili = emaili;
        this.fjalkalimi = fjalkalimi;
        this.adresa = adresa;
        this.vendi = vendi;
        this.komuna = komuna;
        this.numri_leternjofimit = numri_leternjofimit;
        this.data_lindjes = data_lindjes;
        this.data_regjistrimit = data_regjistrimit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    public String getEmaili() {
        return emaili;
    }

    public void setEmaili(String emaili) {
        this.emaili = emaili;
    }

    public String getFjalkalimi() {
        return fjalkalimi;
    }

    public void setFjalkalimi(String fjalkalimi) {
        this.fjalkalimi = fjalkalimi;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getVendi() {
        return vendi;
    }

    public void setVendi(String vendi) {
        this.vendi = vendi;
    }

    public String getKomuna() {
        return komuna;
    }

    public void setKomuna(String komuna) {
        this.komuna = komuna;
    }

    public String getNumri_leternjofimit() {
        return numri_leternjofimit;
    }

    public void setNumri_leternjofimit(String numri_leternjofimit) {
        this.numri_leternjofimit = numri_leternjofimit;
    }

    public String getData_lindjes() {
        return data_lindjes;
    }

    public void setData_lindjes(String data_lindjes) {
        this.data_lindjes = data_lindjes;
    }

    public String getData_regjistrimit() {
        return data_regjistrimit;
    }

    public void setData_regjistrimit(String data_regjistrimit) {
        this.data_regjistrimit = data_regjistrimit;
    }
}