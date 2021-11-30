package co.edu.unab.covid_app.entities;

public class Curso {
    int id;
    String name_program;
    String image;

    public Curso() {
    }

    public Curso(int id, String name_program, String image) {
        this.id = id;
        this.name_program = name_program;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_program() {
        return name_program;
    }

    public void setName_program(String name_program) {
        this.name_program = name_program;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
