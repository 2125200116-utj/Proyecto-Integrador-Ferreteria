package adminDao;

import bd.ConectorBaseDeDatos;
import dto.Cliente;
import java.util.ArrayList;
import java.sql.*;

public class adminDAOCliente implements adminDAO<Cliente>{

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
            System.out.println("Error de SQL: " + e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registros;
    }

    @Override
    public Cliente seleccionarId(int idCliente) {
        ArrayList<Cliente> registros=new ArrayList<>();
        String sql = "select * from cliente where idCliente=?";
        Connection con = null;
        Cliente registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, idCliente);
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
            System.out.println("Error de SQL: " + e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registro;
    }

    @Override
    public boolean actualizar(int idCliente, Cliente cliente) {
        Connection con = null;
        PreparedStatement query = null;
        String sql = "update cliente set nombre=?, apellido=?, telefono=?, "
                + "correo=?, imagen=? where idCliente=?";
        try {
            con=ConectorBaseDeDatos.conectar();
            query=con.prepareStatement(sql);
            query.setString(1, cliente.getNombre());
            query.setString(2, cliente.getApellido());
            query.setString(3, cliente.getTelefono());
            query.setString(4, cliente.getCorreo());
            query.setBytes(5, cliente.getImagen());
            query.setString(6, cliente.getEstado());
            query.setInt(7, cliente.getIdCliente());
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
    public boolean borrar(int idCliente) {
        Connection con = null;
        PreparedStatement query = null;
        String sql = "delete from cliente where idCliente=?";
        try {
            con=ConectorBaseDeDatos.conectar();
            query=con.prepareStatement(sql);
            query.setInt(1, idCliente);
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
