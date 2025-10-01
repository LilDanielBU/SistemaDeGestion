package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.exceptions;

public class CustomException extends RuntimeException {
    private String messege;
    public CustomException(String messege) {
        super(messege);
        this.messege=messege;

    }
    public String getMessege() {return messege;}
    public void setMessege(String messege) {this.messege=messege;}
}