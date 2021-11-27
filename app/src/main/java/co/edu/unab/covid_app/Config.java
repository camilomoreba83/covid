package co.edu.unab.covid_app;

import co.edu.unab.covid_app.entities.Usuario;

public class Config {
    public static final String SERVER= "https://abcuniversityservice.herokuapp.com/api/";
    public static final String URL_REGISTER= SERVER+"registerUser";
    public static final String URL_LOGIN= SERVER+"loginUser";
    public static Usuario usuario;
}
