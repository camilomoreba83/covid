package co.edu.unab.covid_app.http;

import co.edu.unab.covid_app.entities.DiagnosticoB;
import co.edu.unab.covid_app.entities.Usuario;

public class Config {
    private  static  final String SERVER = "https://abcuniversityservice.herokuapp.com/api/";
    public static final String URL_Report = SERVER+"autodiagnostico/";
    public static final String URL_Report_Post = SERVER+"autodiagnostico";
    public static final String URL_REGISTER= SERVER+"registerUser";
    public static final String URL_LOGIN= SERVER+"loginUser";
    public static Usuario usuario;
    public static DiagnosticoB Diagnostico;
}
