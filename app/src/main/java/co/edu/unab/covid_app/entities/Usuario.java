package co.edu.unab.covid_app.entities;

import android.media.Image;

import com.google.gson.JsonObject;

public class Usuario {
    private String name;
    private JsonObject identify;
    private String surname;
    private String email;
    private String nit;
    private String imageUrl;
    private int id_program;
    private int id_rol;
    private String password;
    private String token;
    private String status;

    /*value:{"name":"Walter Giovanny",
            "surname":"Cuadros Rincón",
            "email":"user@email.com",
            "nit":"1234",
            "imageUrl":"imagen.png",
            "idProgram":"1",
            "idRol":"1",
            "password":"123"}*/

    public Usuario() {
    }

    public Usuario(JsonObject identify) {
        this.identify = identify;
    }

    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Usuario(String status) {
        this.status = status;
    }

    public Usuario(String name, String surname, String email, String nit, String imageUrl, int id_program, int id_rol, String password, String token) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.nit = nit;
        this.imageUrl = imageUrl;
        this.id_program = id_program;
        this.id_rol = id_rol;
        this.password = password;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId_program() {
        return id_program;
    }

    public void setId_program(int id_program) {
        this.id_program = id_program;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public JsonObject getIdentify() {
        return identify;
    }

    public void setIdentify(JsonObject identify) {
        this.identify = identify;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "name='" + name + '\'' +
                ", identify=" + identify +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", nit='" + nit + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", id_program=" + id_program +
                ", id_rol=" + id_rol +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
