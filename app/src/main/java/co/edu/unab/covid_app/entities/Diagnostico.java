package co.edu.unab.covid_app.entities;

public class Diagnostico {
    private int id;
    private String nombre;
    private String apellido;
    private String programa;
    private String nit;
    private  String email;
    private  String image;
    private String fecha;
    private  String estado;

    public Diagnostico() {
    }

    public Diagnostico(int id, String nombre, String apellido, String programa,
                       String nit, String email, String image, String fecha, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.programa = programa;
        this.nit = nit;
        this.email = email;
        this.image = image;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
