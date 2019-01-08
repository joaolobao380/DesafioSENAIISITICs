package com.senaitec.desafio_isi_tics.model;

import android.graphics.Bitmap;

public class Usuario {

    private String nome;
    private String email;
    private String endereco;
    private String nascimento;
    private String telefone;
    private String foto;
    private String sobrenome;
    private String cidade;
    private String estado;
    private String latitude;
    private String longitude;

    public Usuario(String first,String sobrenome,String picture, String email, String endereco,
                   String cidade, String estado, String dataNascimento, String telefone, String latitude, String longitude) {
        this.nome = first;
        this.sobrenome = sobrenome;
        this.foto = picture;
        this.email = email;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.nascimento = dataNascimento;
        this.telefone = telefone;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
