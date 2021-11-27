package co.edu.unab.covid_app.entities;

public class DiagnosticoB {
    private int id_user;
    private String name;
    private String surname;
    private String programa;
    private String nit;
    private  String email;
    private  String image;
    private String date;
    private  String state;
    private  int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DiagnosticoB() {
    }

    public DiagnosticoB(int id_user, String nombre, String apellido, String programa,
                       String nit, String email, String image, String date, String state, int id) {
        this.id = id_user;
        this.name = name;
        this.surname = surname;
        this.programa = programa;
        this.nit = nit;
        this.email = email;
        this.image = image;
        this.date = date;
        this.state = state;
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
