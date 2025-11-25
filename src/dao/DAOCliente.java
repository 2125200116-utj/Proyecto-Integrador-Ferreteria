package dao;

import bd.ConectorBaseDeDatos;
import dto.Cliente;
import java.util.ArrayList;
import java.sql.*;

public class DAOCliente implements DAO<Cliente>{

    @Override
    public boolean insertar(Cliente cliente) throws Exception{
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
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
    }

    @Override
    public ArrayList<Cliente> seleccionarTodos() throws Exception{
        ArrayList<Cliente> registros=new ArrayList<>();
        String sql = "select * from cliente where estado=?";
        Connection con = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, "ACTIVO");
            ResultSet respuestaSQL = query.executeQuery();
            while(respuestaSQL.next()){
                Cliente registro = new Cliente();
                registro.setIdCliente(respuestaSQL.getInt("idCliente"));
                registro.setNombre(respuestaSQL.getString("nombre"));
                registro.setApellido(respuestaSQL.getString("apellido"));
                registro.setTelefono(respuestaSQL.getString("telefono"));
                registro.setCorreo(respuestaSQL.getString("correo"));
                registro.setImagen(respuestaSQL.getBytes("imagen"));
                registro.setEstado(respuestaSQL.getString("estado"));
                registros.add(registro);
            }     
        } catch (Exception e) {
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registros;
    }

    @Override
    public ArrayList<Cliente> seleccionarAlgunos(String nombre) throws Exception{
        ArrayList<Cliente> registros=new ArrayList<>();
        String sql = "select * from cliente where nombre like ? and estado=?";
        Connection con = null;
        Cliente registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, "%" +nombre+"%");
            query.setString(2, "ACTIVO");
            ResultSet respuestaSQL = query.executeQuery();
            while(respuestaSQL.next()){
                registro = new Cliente();
                registro.setIdCliente(respuestaSQL.getInt("idCliente"));
                registro.setNombre(respuestaSQL.getString("nombre"));
                registro.setApellido(respuestaSQL.getString("apellido"));
                registro.setTelefono(respuestaSQL.getString("telefono"));
                registro.setCorreo(respuestaSQL.getString("correo"));
                registro.setImagen(respuestaSQL.getBytes("imagen"));
                registro.setEstado(respuestaSQL.getString("estado"));
                registros.add(registro);
            }     
        } catch (Exception e) {
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registros;
    }

    @Override
    public Cliente seleccionarId(int idCliente) throws Exception{
        ArrayList<Cliente> registros=new ArrayList<>();
        String sql = "select * from cliente where idCliente=? and estado=?";
        Connection con = null;
        Cliente registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, idCliente);
            query.setString(2, "ACTIVO");
            ResultSet respuestaSQL = query.executeQuery();
            while(respuestaSQL.next()){
                registro = new Cliente();
                registro.setIdCliente(respuestaSQL.getInt("idCliente"));
                registro.setNombre(respuestaSQL.getString("nombre"));
                registro.setApellido(respuestaSQL.getString("apellido"));
                registro.setTelefono(respuestaSQL.getString("telefono"));
                registro.setCorreo(respuestaSQL.getString("correo"));
                registro.setImagen(respuestaSQL.getBytes("imagen"));
                registro.setEstado(respuestaSQL.getString("estado"));
            }     
        } catch (Exception e) {
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registro;
    }

    @Override
    public boolean actualizar(int idCliente, Cliente cliente) throws Exception{
        Connection con = null;
        PreparedStatement query = null;
        String sql = "update cliente set nombre=?, apellido=?, telefono=?, "
                + "correo=?, imagen=? where idCliente=? and estado=?";
        try {
            con=ConectorBaseDeDatos.conectar();
            query=con.prepareStatement(sql);
            query.setString(1, cliente.getNombre());
            query.setString(2, cliente.getApellido());
            query.setString(3, cliente.getTelefono());
            query.setString(4, cliente.getCorreo());
            query.setBytes(5, cliente.getImagen());
            query.setInt(6, idCliente);
            query.setString(7, "ACTIVO");
            int res=query.executeUpdate();
            return(res>0);
        } catch (Exception e) {
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
    }

    @Override
    public boolean borrar(int idCliente) throws Exception{
        Connection con = null;
        PreparedStatement query = null;
        String sql = "update cliente set estado=? where idCliente=?";
        try {
            con=ConectorBaseDeDatos.conectar();
            query=con.prepareStatement(sql);
            query.setString(1, "INACTIVO");
            query.setInt(2, idCliente);
            int res=query.executeUpdate();
            return(res>0);
        } catch (Exception e) {
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
    }
    
}
