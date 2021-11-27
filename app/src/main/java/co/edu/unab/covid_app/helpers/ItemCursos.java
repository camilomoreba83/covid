package co.edu.unab.covid_app.helpers;

import co.edu.unab.covid_app.entities.Curso;

public class ItemCursos {
    Curso curso1;
    Curso curso2;
    Curso curso3;

    public ItemCursos(Curso curso1, Curso curso2, Curso curso3) {
        this.curso1 = curso1;
        this.curso2 = curso2;
        this.curso3 = curso3;
    }

    public Curso getCurso1() {
        return curso1;
    }

    public void setCurso1(Curso curso1) {
        this.curso1 = curso1;
    }

    public Curso getCurso2() {
        return curso2;
    }

    public void setCurso2(Curso curso2) {
        this.curso2 = curso2;
    }

    public Curso getCurso3() {
        return curso3;
    }

    public void setCurso3(Curso curso3) {
        this.curso3 = curso3;
    }
}
