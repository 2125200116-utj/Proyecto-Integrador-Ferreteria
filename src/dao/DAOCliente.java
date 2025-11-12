package dao;

import bd.ConectorBaseDeDatos;
import dto.Cliente;
import java.util.ArrayList;
import java.sql.*;

public class DAOCliente implements DAO<Cliente>{

    @Override
    public boolean insertar(Cliente cliente) {
        Connection con = null;
        PreparedStatement query = null;
        String sql = "insert into cliente (nombre, apellido, telefono, correo, imagen, estado)"
                +"values (?, ?, ?, ?, ?, ?)";
        try {
            con=ConectorBaseDeDatos.conectar();
            query=con.prepareStatement(sql);
            query.setString(1, cliente.getNombre());
            query.setString(2, cliente.getApellido());
            query.setString(3, cliente.getTelefono());
            query.setString(4, cliente.getCorreo());
            query.setBytes(5, cliente.getImagen());
            query.setString(6, cliente.getEstado());            
            int res=query.executeUpdate();
            return(res>0);
        } catch (Exception e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return false;
    }

    @Override
    public ArrayList<Cliente> seleccionarTodos() {
        ArrayList<Cliente> registros=new ArrayList<>();
        String sql = "select * from cliente";
        Connection con = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            ResultSet respuestaSQL = query.executeQuery();
            while(respuestaSQL.next()){
                Cliente registro = new Cliente();
                registro.setId(respuestaSQL.getInt("id"));
                registro.setNombre(respuestaSQL.getString("nombre"));
                registro.setApellido(respuestaSQL.getString("apellido"));
                registro.setTelefono(respuestaSQL.getString("telefono"));
                registro.setCorreo(respuestaSQL.getString("correo"));
                registro.setImagen(respuestaSQL.getBytes("imagen"));
                registro.setEstado(respuestaSQL.getString("estado"));
                registros.add(registro);
            }     
        } catch (Exception e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registros;
    }

    @Override
    public ArrayList<Cliente> seleccionarAlgunos(String nombre) {
        ArrayList<Cliente> registros=new ArrayList<>();
        String sql = "select * from cliente where nombre=?";
        Connection con = null;
        Cliente registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, nombre);
            ResultSet respuestaSQL = query.executeQuery();
            while(respuestaSQL.next()){
                registro = new Cliente();
                registro.setId(respuestaSQL.getInt("id"));
                registro.setNombre(respuestaSQL.getString("nombre"));
                registro.setApellido(respuestaSQL.getString("apellido"));
                registro.setTelefono(respuestaSQL.getString("telefono"));
                registro.setCorreo(respuestaSQL.getString("correo"));
                registro.setImagen(respuestaSQL.getBytes("imagen"));
                registro.setEstado(respuestaSQL.getString("estado"));
                registros.add(registro);
            }     
        } catch (Exception e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registros;
    }

    @Override
    public Cliente seleccionarId(int id) {
        ArrayList<Cliente> registros=new ArrayList<>();
        String sql = "select * from cliente where id=?";
        Connection con = null;
        Cliente registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, id);
            ResultSet respuestaSQL = query.executeQuery();
            while(respuestaSQL.next()){
                registro = new Cliente();
                registro.setId(respuestaSQL.getInt("id"));
                registro.setNombre(respuestaSQL.getString("nombre"));
                registro.setApellido(respuestaSQL.getString("apellido"));
                registro.setTelefono(respuestaSQL.getString("telefono"));
                registro.setCorreo(respuestaSQL.getString("correo"));
                registro.setImagen(respuestaSQL.getBytes("imagen"));
                registro.setEstado(respuestaSQL.getString("estado"));
            }     
        } catch (Exception e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registro;
    }

    @Override
    public boolean actualizar(int id, Cliente cliente) {
        Connection con = null;
        PreparedStatement query = null;
        String sql = "update cliente set nombre=?, apellido=?, telefono=?, "
                + "correo=?, imagen=? where id=?";
        try {
            con=ConectorBaseDeDatos.conectar();
            query=con.prepareStatement(sql);
            query.setString(1, cliente.getNombre());
            query.setString(2, cliente.getApellido());
            query.setString(3, cliente.getTelefono());
            query.setString(4, cliente.getCorreo());
            query.setBytes(5, cliente.getImagen());
            query.setInt(6, cliente.getId());
            int res=query.executeUpdate();
            return(res>0);
        } catch (Exception e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return false;
    }

    @Override
    public boolean borrar(int id) {
        Connection con = null;
        PreparedStatement query = null;
        String sql = "update cliente set estado=? where id=?";
        try {
            con=ConectorBaseDeDatos.conectar();
            query=con.prepareStatement(sql);
            query.setString(1, "INACTIVO");
            query.setInt(2, id);
            int res=query.executeUpdate();
            return(res>0);
        } catch (Exception e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return false;
    }
    
}
