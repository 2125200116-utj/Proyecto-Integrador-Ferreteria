package dao;

import bd.ConectorBaseDeDatos;
import dto.Empleado;
import java.util.ArrayList;
import java.sql.*;

public class DAOEmpleado implements DAO<Empleado>{

    @Override
    public boolean insertar(Empleado empleado) throws Exception{
        Connection con = null;
        PreparedStatement query = null;
        String sql = "insert into empleado (usuario, contrasena, nombre, apellido, telefono, rol, imagen, estado)"
                + "values (?, ?, ?, ?, ?, ?, ?, ?)";
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
            query.setString(8, empleado.getEstado());
            int res=query.executeUpdate();
            return (res>0);
        } catch (Exception e) {
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
    }

    @Override
    public ArrayList<Empleado> seleccionarTodos() throws Exception{
        ArrayList<Empleado> registros= new ArrayList<>();
        String sql="select * from empleado where estado=?";
        Connection con=null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, "ACTIVO");
            ResultSet respuestaSQL = query.executeQuery();
            while(respuestaSQL.next()){
                Empleado registro = new Empleado();
                registro.setIdEmpleado(respuestaSQL.getInt("idEmpleado"));
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
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registros;
    }

    @Override
    public ArrayList<Empleado> seleccionarAlgunos(String nombre) throws Exception{
        ArrayList<Empleado> registros= new ArrayList<>();
        String sql="select * from empleado where nombre like ? and estado=?";
        Connection con=null;
        Empleado registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, "%" +nombre+"%");
            query.setString(2, "ACTIVO");
            ResultSet respuestaSQL = query.executeQuery();
            
            while(respuestaSQL.next()){
                registro = new Empleado();
                registro.setIdEmpleado(respuestaSQL.getInt("idEmpleado"));
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
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registros;
    }

    @Override
    public Empleado seleccionarId(int idEmpleado) throws Exception{
        ArrayList<Empleado> registros= new ArrayList<>();
        String sql="select * from empleado where idEmpleado=? and estado=?";
        Connection con=null;
        Empleado registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, idEmpleado);
            query.setString(2, "ACTIVO");
            ResultSet respuestaSQL = query.executeQuery();
            
            while(respuestaSQL.next()){
                registro = new Empleado();
                registro.setIdEmpleado(respuestaSQL.getInt("idEmpleado"));
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
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registro;
    }

    @Override
    public boolean actualizar(int idEmpleado, Empleado usuario) throws Exception{
        Connection con = null;
        PreparedStatement query = null;
        String sql = "update empleado set contrasena=?, nombre=?, "
                + "apellido=?, telefono=?, rol=?, imagen=? where idEmpleado=? and estado=?";
        try {
            con=ConectorBaseDeDatos.conectar();
            query=con.prepareStatement(sql);
            query.setString(1, usuario.getContrasena());
            query.setString(2, usuario.getNombre());
            query.setString(3, usuario.getApellido());
            query.setString(4, usuario.getTelefono());
            query.setString(5, usuario.getRol());
            query.setBytes(6, usuario.getImagen());
            query.setInt(7, idEmpleado);
            query.setString(8, "ACTIVO");
            int res=query.executeUpdate();
            return (res>0);
        } catch (Exception e) {
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
    }

    @Override
    public boolean borrar(int idEmpleado) throws Exception{
        Connection con = null;
        PreparedStatement query = null;
        String sql = "update empleado set estado=? where idEmpleado=?";
        try {
            con=ConectorBaseDeDatos.conectar();
            query=con.prepareStatement(sql);
            query.setString(1, "INACTIVO");
            query.setInt(2, idEmpleado);
            int res=query.executeUpdate();
            return (res>0);
        } catch (Exception e) {
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
    }
    
    
    public int seleccionarUltimoId() throws Exception{
        int id = 0;
        String sql="select last_insert_id(idEmpleado) as id from empleado";
        Connection con=null;
        Empleado registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            ResultSet respuestaSQL = query.executeQuery();
            
            while(respuestaSQL.next()){
                id = respuestaSQL.getInt("id");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return id;
    }
}
