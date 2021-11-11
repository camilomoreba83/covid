package co.edu.unab.covid_app.entities;

public class DiagnosticoUser {
    private int id;
    private String name;
    private String estado;
    private String image;

    public DiagnosticoUser(int id, String name, String estado, String image) {
        this.id = id;
        this.name = name;
        this.estado = estado;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
