/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Objects;

/**
 *
 * @author Felipe Torres
 */
public class Usuario {
    
    private String nombre;
    private String contrasenia;
    private boolean administrador;
    private String clave;

    public Usuario() {
    }

    public Usuario(String nombre, String contrasenia, boolean administrador, String clave) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.administrador = administrador;
        this.clave = clave;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.clave);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.clave, other.clave)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  "nombre=" + nombre + ", contraseña=" + contrasenia + ", administrador=" + administrador + ", clave=" + clave ;
    }
    
    
    
    
    
}
