package dao;

import bd.ConectorBaseDeDatos;
import dto.Empleado;
import java.util.ArrayList;
import java.sql.*;

public class DAOEmpleado implements DAO<Empleado>{

    @Override
    public boolean insertar(Empleado empleado) {
        Connection con = null;
        PreparedStatement query = null;
        String sql = "insert into empleado (usuario, contrasena, nombre, apellido, telefono, telefono, rol, imagen, estado)"
                + "values (?, ?, ?, ?, ?, ?, ?)";
        try {
            con=ConectorBaseDeDatos.conectar();
            query=con.prepareStatement(sql);
            query.setString(1, empleado.getUsuario());
            query.setString(2, empleado.getContrasena());
            query.setString(3, empleado.getNombre());
            query.setString(4, empleado.getApellido());
            query.setString(5, empleado.getTelefono());
            query.setString(6, empleado.getRol());
            query.setBytes(7, empleado.getImagen());
            int res=query.executeUpdate();
            return (res>0);
        } catch (Exception e) {
            System.out.println("Error de SQL: "+ e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return false;
    }

    @Override
    public ArrayList<Empleado> seleccionarTodos() {
        ArrayList<Empleado> registros= new ArrayList<>();
        String sql="select * from empleado";
        Connection con=null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            ResultSet respuestaSQL = query.executeQuery();
            while(respuestaSQL.next()){
                Empleado registro = new Empleado();
                registro.setId(respuestaSQL.getInt("id"));
                registro.setUsuario(respuestaSQL.getString("usuario"));
                registro.setContrasena(respuestaSQL.getString("contrasena"));
                registro.setNombre(respuestaSQL.getString("nombre"));
                registro.setApellido(respuestaSQL.getString("apellido"));
                registro.setTelefono(respuestaSQL.getString("telefono"));
                registro.setRol(respuestaSQL.getString("rol"));
                registro.setImagen(respuestaSQL.getBytes("imagen"));
                registro.setEstado(respuestaSQL.getString("estado"));
                registros.add(registro);
            }
        } catch (Exception e) {
            System.out.println("Error de SQL: "+ e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registros;
    }

    @Override
    public ArrayList<Empleado> seleccionarAlgunos(String nombre) {
        ArrayList<Empleado> registros= new ArrayList<>();
        String sql="select * from empleado where nombre=?";
        Connection con=null;
        Empleado registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, nombre);
            ResultSet respuestaSQL = query.executeQuery();
            
            while(respuestaSQL.next()){
                registro = new Empleado();
                registro.setId(respuestaSQL.getInt("id"));
                registro.setUsuario(respuestaSQL.getString("usuario"));
                registro.setContrasena(respuestaSQL.getString("contrasena"));
                registro.setNombre(respuestaSQL.getString("nombre"));
                registro.setApellido(respuestaSQL.getString("apellido"));
                registro.setTelefono(respuestaSQL.getString("telefono"));
                registro.setRol(respuestaSQL.getString("rol"));
                registro.setImagen(respuestaSQL.getBytes("imagen"));
                registro.setEstado(respuestaSQL.getString("estado"));
                registros.add(registro);
            }
        } catch (Exception e) {
            System.out.println("Error de SQL: "+ e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registros;
    }

    @Override
    public Empleado seleccionarId(int id) {
        ArrayList<Empleado> registros= new ArrayList<>();
        String sql="select * from empleado where id=?";
        Connection con=null;
        Empleado registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, id);
            ResultSet respuestaSQL = query.executeQuery();
            
            while(respuestaSQL.next()){
                registro = new Empleado();
                registro.setId(respuestaSQL.getInt("id"));
                registro.setUsuario(respuestaSQL.getString("usuario"));
                registro.setContrasena(respuestaSQL.getString("contrasena"));
                registro.setNombre(respuestaSQL.getString("nombre"));
                registro.setApellido(respuestaSQL.getString("apellido"));
                registro.setTelefono(respuestaSQL.getString("telefono"));
                registro.setRol(respuestaSQL.getString("rol"));
                registro.setImagen(respuestaSQL.getBytes("imagen"));
                registro.setEstado(respuestaSQL.getString("estado"));
                registros.add(registro);
            }
        } catch (Exception e) {
            System.out.println("Error de SQL: "+ e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registro;
    }

    @Override
    public boolean actualizar(int id, Empleado usuario) {
        Connection con = null;
        PreparedStatement query = null;
        String sql = "update empleado set contrasena=?, nombre=?, "
                + "apellido=?, telefono=?, rol=?, imagen=? where id=?";
        try {
            con=ConectorBaseDeDatos.conectar();
            query=con.prepareStatement(sql);
            query.setString(1, usuario.getContrasena());
            query.setString(2, usuario.getNombre());
            query.setString(3, usuario.getApellido());
            query.setString(4, usuario.getTelefono());
            query.setString(5, usuario.getRol());
            query.setBytes(6, usuario.getImagen());
            query.setInt(7, usuario.getId());
            int res=query.executeUpdate();
            return (res>0);
        } catch (Exception e) {
            System.out.println("Error de SQL: "+ e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return false;
    }

    @Override
    public boolean borrar(int id) {
        Connection con = null;
        PreparedStatement query = null;
        String sql = "update empleado set estado=? where id=?";
        try {
            con=ConectorBaseDeDatos.conectar();
            query=con.prepareStatement(sql);
            query.setString(1, "INACTIVO");
            query.setInt(2, id);
            int res=query.executeUpdate();
            return (res>0);
        } catch (Exception e) {
            System.out.println("Error de SQL: "+ e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return false;
    }
    
}
