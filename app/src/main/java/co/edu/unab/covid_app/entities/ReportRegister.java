package co.edu.unab.covid_app.entities;

public class ReportRegister {
    private String pregunta_1;
    private  StringBuilder pregunta_2;


    public ReportRegister(String pregunta_1, StringBuilder pregunta_2) {
        this.pregunta_1 = pregunta_1;
        this.pregunta_2 = pregunta_2;
    }

    public String getPregunta_1() {
        return pregunta_1;
    }

    public void setPregunta_1(String pregunta_1) {
        this.pregunta_1 = pregunta_1;
    }

    public StringBuilder getPregunta_2() {
        return pregunta_2;
    }

    public void setPregunta_2(StringBuilder pregunta_2) {
        this.pregunta_2 = pregunta_2;
    }
}
