package com.example.uas_pwdm.model;

public class MemberData {
    private String id, nama, no_telp, email, alamat;

    public MemberData() {

    }

    public MemberData(String id, String nama, String no_telp, String email, String alamat) {
        this.id = id;
        this.nama = nama;
        this.no_telp = no_telp;
        this.email = email;
        this.alamat = alamat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
