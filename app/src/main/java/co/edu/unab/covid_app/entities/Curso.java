package co.edu.unab.covid_app.entities;

public class Curso {
    int id;
    String nameCurso;
    String image;

    public Curso(int id, String nameCurso, String image) {
        this.id = id;
        this.nameCurso = nameCurso;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCurso() {
        return nameCurso;
    }

    public void setNameCurso(String nameCurso) {
        this.nameCurso = nameCurso;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
