/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import bd.ConectorBaseDeDatos;
import java.sql.*;

/**
 *
 * @author juan
 */
public class DAOAut {
    
    public boolean aut(String usuario, String contrasena){
        String sql="select usuario,contrasena from empleado where usuario=? and contrasena=?";
        Connection con=null;
        boolean existe = false;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, usuario);
            query.setString(2, contrasena);
            ResultSet respuestaSQL = query.executeQuery();
            
            if(respuestaSQL.next()){
                existe = true;
            }
        } catch (Exception e) {
            System.out.println("Error de SQL: "+ e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return existe;
    }
    
    //para verificar si el usuario es admin
    public boolean verificarRol(String usuario){
        String sql="select usuario from empleado where rol=?";
        Connection con=null;
        boolean admin = false;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, usuario);
            query.setString(2, "ADMIN");
            ResultSet respuestaSQL = query.executeQuery();
            
            if(respuestaSQL.next()){
                admin = true;
            }
        } catch (Exception e) {
            System.out.println("Error de SQL: "+ e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return admin;
    }
}

