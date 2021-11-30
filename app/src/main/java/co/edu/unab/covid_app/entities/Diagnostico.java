package co.edu.unab.covid_app.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Diagnostico implements Parcelable {
    private int id_user;
    private String name;
    private String surname;
    private String estado;
    private String image;

    protected Diagnostico(Parcel in) {
        id_user = in.readInt();
        name = in.readString();
        surname = in.readString();
        estado = in.readString();
        image = in.readString();
    }

    public static final Creator<Diagnostico> CREATOR = new Creator<Diagnostico>() {
        @Override
        public Diagnostico createFromParcel(Parcel in) {
            return new Diagnostico(in);
        }

        @Override
        public Diagnostico[] newArray(int size) {
            return new Diagnostico[size];
        }
    };

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Diagnostico(int id_user, String name, String surname, String estado, String image) {
        this.id_user = id_user;
        this.name = name;
        this.surname = surname;
        this.estado = estado;
        this.image = image;
    }

    public Diagnostico() {
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_user);
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeString(estado);
        dest.writeString(image);
    }
}
