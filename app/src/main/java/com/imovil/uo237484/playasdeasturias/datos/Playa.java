package com.imovil.uo237484.playasdeasturias.datos;


import android.os.Parcel;
import android.os.Parcelable;

public class Playa implements Parcelable {

    public String nombre;
    public String imagen;
    public String concejo;
    public double[] coordenadas;
    public String zona;
    public String informacion;
    public String tipodeplaya;
    public String longitud;
    public String accesos;
    public String servicios;


    public Playa(String nombre, String imagen, String concejo, double[] coordenadas, String zona, String informacion, String tipodeplaya, String longitud, String accesos, String servicios) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.concejo = concejo;
        this.coordenadas = coordenadas;
        this.zona = zona;
        this.informacion = informacion;
        this.tipodeplaya = tipodeplaya;
        this.longitud = longitud;
        this.accesos = accesos;
        this.servicios = servicios;
    }

    public Playa(String nombre, String imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public Playa(String nombre){
        this.nombre = nombre;
    }


    protected Playa(Parcel in) {
        nombre = in.readString();
        imagen = in.readString();
        concejo = in.readString();
        coordenadas = in.createDoubleArray();
        zona = in.readString();
        informacion = in.readString();
        tipodeplaya = in.readString();
        longitud = in.readString();
        accesos = in.readString();
        servicios = in.readString();
    }

    public static final Creator<Playa> CREATOR = new Creator<Playa>() {
        @Override
        public Playa createFromParcel(Parcel in) {
            return new Playa(in);
        }

        @Override
        public Playa[] newArray(int size) {
            return new Playa[0];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getConcejo() {
        return concejo;
    }

    public void setConcejo(String concejo) {
        this.concejo = concejo;
    }

    public double[] getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(double[] coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public String getTipodeplaya() {
        return tipodeplaya;
    }

    public void setTipodeplaya(String tipodeplaya) {
        this.tipodeplaya = tipodeplaya;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getAccesos() {
        return accesos;
    }

    public void setAccesos(String accesos) {
        this.accesos = accesos;
    }

    public String getServicios() {
        return servicios;
    }

    public void setServicios(String servicios) {
        this.servicios = servicios;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(imagen);
        dest.writeString(concejo);
        dest.writeDoubleArray(coordenadas);
        dest.writeString(zona);
        dest.writeString(informacion);
        dest.writeString(tipodeplaya);
        dest.writeString(longitud);
        dest.writeString(accesos);
        dest.writeString(servicios);
    }

    @Override
    public String toString() {
        return getNombre();

    }
}
