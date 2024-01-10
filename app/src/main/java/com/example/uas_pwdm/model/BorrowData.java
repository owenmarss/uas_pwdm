package com.example.uas_pwdm.model;

public class BorrowData {
    private String id, book_id, member_id, judul, nama, telp, email;

    public  BorrowData(){

    }

    public BorrowData(String id, String book_id, String member_id, String judul, String nama, String telp, String email) {
        this.id = id;
        this.book_id = book_id;
        this.member_id = member_id;
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

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
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
