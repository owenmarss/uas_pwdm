package com.example.uas_pwdm.model;

public class BorrowData {
    private String id, judul, nama, telp, email;

    public  BorrowData(){

    }

    public BorrowData(String id, String judul, String nama, String telp, String email) {
        this.id = id;
        this.judul = judul;
        this.nama = nama;
        this.telp = telp;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
