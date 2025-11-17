
package dto;

import java.util.Objects;

public class Empleado {
    private int idEmpleado;
    private String usuario;
    private String contrasena;
    private String nombre;
    private String apellido;
    private String telefono;
    private String rol;
    private byte[] imagen;
    private String estado;

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.idEmpleado;
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
        final Empleado other = (Empleado) obj;
        return this.idEmpleado == other.idEmpleado;
    }

    @Override
    public String toString() {
        return "\n Empleado{" + "\nid=" + idEmpleado + ", \nusuario=" + usuario + 
                ", \ncontrasena=" + contrasena + ", \nnombre=" + nombre + 
                ", \napellido=" + apellido + ", \ntelefono=" + telefono + 
                ", \nrol=" + rol + ", \nimagen=" + imagen + ", \nestado=" 
                + estado + "}\n";
    }
    
    

    
}
