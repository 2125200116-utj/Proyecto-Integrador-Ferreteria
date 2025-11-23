package adminDao;

import bd.ConectorBaseDeDatos;
import dto.Empleado;
import java.util.ArrayList;
import java.sql.*;

public class adminDAOEmpleado implements adminDAO<Empleado>{

    @Override
    public boolean insertar(Empleado empleado) {
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
            System.out.println("Error de SQL: "+ e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registros;
    }

    @Override
    public Empleado seleccionarId(int idEmpleado) {
        ArrayList<Empleado> registros= new ArrayList<>();
        String sql="select * from empleado where idEmpleado=?";
        Connection con=null;
        Empleado registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, idEmpleado);
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
            System.out.println("Error de SQL: "+ e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registro;
    }

    @Override
    public boolean actualizar(int idEmpleado, Empleado usuario) {
        Connection con = null;
        PreparedStatement query = null;
        String sql = "update empleado set contrasena=?, nombre=?, "
                + "apellido=?, telefono=?, rol=?, imagen=?, estado=? where idEmpleado=?";
        try {
            con=ConectorBaseDeDatos.conectar();
            query=con.prepareStatement(sql);
            query.setString(1, usuario.getContrasena());
            query.setString(2, usuario.getNombre());
            query.setString(3, usuario.getApellido());
            query.setString(4, usuario.getTelefono());
            query.setString(5, usuario.getRol());
            query.setBytes(6, usuario.getImagen());
            query.setString(7, usuario.getEstado());
            query.setInt(8, idEmpleado);
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
    public boolean borrar(int idEmpleado) {
        Connection con = null;
        PreparedStatement query = null;
        String sql = "delete from empleado where idEmpleado=?";
        try {
            con=ConectorBaseDeDatos.conectar();
            query=con.prepareStatement(sql);
            query.setInt(1, idEmpleado);
            int res=query.executeUpdate();
            return (res>0);
        } catch (Exception e) {
            System.out.println("Error de SQL: "+ e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return false;
    }
    
    public int seleccionarUltimoId() {
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
            System.out.println("Error de SQL: "+ e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return id;
    }
}
