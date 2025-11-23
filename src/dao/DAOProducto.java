package dao;

import bd.ConectorBaseDeDatos;
import dto.Producto;
import java.util.ArrayList;
import java.sql.*;

public class DAOProducto implements DAO<Producto> {

    @Override
    public boolean insertar(Producto prod) {
        Connection con = null;
        PreparedStatement query = null;
        String sql = " insert into producto (nombre, descripcion, precio, imagen1, imagen2, imagen3, existencia, estado, idProveedor)"
                + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            con = ConectorBaseDeDatos.conectar();
            query = con.prepareStatement(sql);
            query.setString(1, prod.getNombre());
            query.setString(2, prod.getDescripcion());
            query.setDouble(3, prod.getPrecio());
            query.setBytes(4, prod.getImagen1());
            query.setBytes(5, prod.getImagen2());
            query.setBytes(6, prod.getImagen3());
            query.setInt(7, prod.getExistencia());
            query.setString(8, prod.getEstado());
            query.setInt(9, prod.getIdProveedor());
            int res = query.executeUpdate();
            return (res > 0);
        } catch (Exception e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return false;
    }

    @Override
    public ArrayList<Producto> seleccionarTodos() {
        ArrayList<Producto> registros = new ArrayList<>();
        String sql = " select * from producto where estado=?";
        Connection con = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, "ACTIVO");
            ResultSet respuestaSQL = query.executeQuery();
            while (respuestaSQL.next()) {
                Producto registro = new Producto();
                registro.setIdProducto(respuestaSQL.getInt("idProducto"));
                registro.setNombre(respuestaSQL.getString("nombre"));
                registro.setDescripcion(respuestaSQL.getString("descripcion"));
                registro.setPrecio(respuestaSQL.getDouble("precio"));
                registro.setImagen1(respuestaSQL.getBytes("imagen1"));
                registro.setImagen2(respuestaSQL.getBytes("imagen2"));
                registro.setImagen3(respuestaSQL.getBytes("imagen3"));
                registro.setExistencia(respuestaSQL.getInt("existencia"));
                registro.setEstado(respuestaSQL.getString("estado"));
                registro.setIdProveedor(respuestaSQL.getInt("idProveedor"));
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
    public ArrayList<Producto> seleccionarAlgunos(String nombre) {
        ArrayList<Producto> registros = new ArrayList<>();
        String sql = " select * from producto where nombre = ? and estado=?";
        Connection con = null;
        Producto registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, nombre);
            query.setString(2, "ACTIVO");
            ResultSet respuestaSQL = query.executeQuery();
            while (respuestaSQL.next()) {
                registro = new Producto();
                registro.setIdProducto(respuestaSQL.getInt("idProducto"));
                registro.setNombre(respuestaSQL.getString("nombre"));
                registro.setDescripcion(respuestaSQL.getString("descripcion"));
                registro.setPrecio(respuestaSQL.getDouble("precio"));
                registro.setImagen1(respuestaSQL.getBytes("imagen1"));
                registro.setImagen2(respuestaSQL.getBytes("imagen2"));
                registro.setImagen3(respuestaSQL.getBytes("imagen3"));
                registro.setExistencia(respuestaSQL.getInt("existencia"));
                registro.setEstado(respuestaSQL.getString("estado"));
                registro.setIdProveedor(respuestaSQL.getInt("idProveedor"));
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
    public Producto seleccionarId(int idProducto) {
        String sql = " select * from producto where idProducto = ? and estado=?";
        Connection con = null;
        Producto registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, idProducto);
            query.setString(2, "ACTIVO");
            ResultSet respuestaSQL = query.executeQuery();
            while (respuestaSQL.next()) {
                registro = new Producto();
                registro.setIdProducto(respuestaSQL.getInt("idProducto"));
                registro.setNombre(respuestaSQL.getString("nombre"));
                registro.setDescripcion(respuestaSQL.getString("descripcion"));
                registro.setPrecio(respuestaSQL.getDouble("precio"));
                registro.setImagen1(respuestaSQL.getBytes("imagen1"));
                registro.setImagen2(respuestaSQL.getBytes("imagen2"));
                registro.setImagen3(respuestaSQL.getBytes("imagen3"));
                registro.setExistencia(respuestaSQL.getInt("existencia"));
                registro.setEstado(respuestaSQL.getString("estado"));
                registro.setIdProveedor(respuestaSQL.getInt("idProveedor"));
            }
        } catch (Exception e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registro;
    }

    @Override
    public boolean actualizar(int idProducto, Producto prod) {
        Connection con = null;
        PreparedStatement query = null;
        String sql = " update producto set "
                + "nombre=?, descripcion=?, "
                + "precio=?, imagen1=?, "
                + "imagen2=?, imagen3=?, "
                + "existencia=?, idProveedor=?"
                + "where idProducto=? and estado=?";
        try {
            con = ConectorBaseDeDatos.conectar();
            query = con.prepareStatement(sql);
            query.setString(1, prod.getNombre());
            query.setString(2, prod.getDescripcion());
            query.setDouble(3, prod.getPrecio());
            query.setBytes(4, prod.getImagen1());
            query.setBytes(5, prod.getImagen2());
            query.setBytes(6, prod.getImagen3());
            query.setInt(7, prod.getExistencia());
            query.setInt(8, prod.getIdProveedor());
            query.setInt(9, idProducto);
            query.setString(10, "ACTIVO");
            int res = query.executeUpdate();
            return (res > 0);
        } catch (Exception e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return false;
    }

    @Override
    public boolean borrar(int idProducto) {
        Connection con = null;
        PreparedStatement query = null;
        String sql = " update producto set "
                + "estado=? where idProducto=?";
        try {
            con = ConectorBaseDeDatos.conectar();
            query = con.prepareStatement(sql);
            query.setString(1, "INACTIVO");
            query.setInt(2, idProducto);
            int res = query.executeUpdate();
            return (res > 0);
        } catch (Exception e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return false;
    }

}
