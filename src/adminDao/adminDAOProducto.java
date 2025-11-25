package adminDao;

import bd.ConectorBaseDeDatos;
import dto.Producto;
import java.util.ArrayList;
import java.sql.*;

public class adminDAOProducto implements adminDAO<Producto> {

    @Override
    public boolean insertar(Producto prod) throws Exception{
        Connection con = null;
        PreparedStatement query = null;
        String sql = " insert into producto (nombre, descripcion, precioVenta, precioCompra, imagen1, imagen2, imagen3, existencia, estado, idProveedor)"
                + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            con = ConectorBaseDeDatos.conectar();
            query = con.prepareStatement(sql);
            query.setString(1, prod.getNombre());
            query.setString(2, prod.getDescripcion());
            query.setDouble(3, prod.getPrecioVenta());
            query.setDouble(4, prod.getPrecioCompra());
            query.setBytes(5, prod.getImagen1());
            query.setBytes(6, prod.getImagen2());
            query.setBytes(7, prod.getImagen3());
            query.setInt(8, prod.getExistencia());
            query.setString(9, prod.getEstado());  
            query.setInt(10, prod.getIdProveedor());
            int res = query.executeUpdate();
            return (res > 0);
        } catch (Exception e) {
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
    }

    @Override
    public ArrayList<Producto> seleccionarTodos() throws Exception{
        ArrayList<Producto> registros = new ArrayList<>();
        String sql = " select * from producto ";
        Connection con = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            ResultSet respuestaSQL = query.executeQuery();
            while (respuestaSQL.next()) {
                Producto registro = new Producto();
                registro.setIdProducto(respuestaSQL.getInt("idProducto"));
                registro.setNombre(respuestaSQL.getString("nombre"));
                registro.setDescripcion(respuestaSQL.getString("descripcion"));
                registro.setPrecioVenta(respuestaSQL.getDouble("precioVenta"));
                registro.setPrecioCompra(respuestaSQL.getDouble("precioCompra"));
                registro.setImagen1(respuestaSQL.getBytes("imagen1"));
                registro.setImagen2(respuestaSQL.getBytes("imagen2"));
                registro.setImagen3(respuestaSQL.getBytes("imagen3"));
                registro.setExistencia(respuestaSQL.getInt("existencia"));
                registro.setEstado(respuestaSQL.getString("estado"));
                registro.setIdProveedor(respuestaSQL.getInt("idProveedor"));
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
    public ArrayList<Producto> seleccionarAlgunos(String nombre) throws Exception{
        ArrayList<Producto> registros = new ArrayList<>();
        String sql = " select * from producto where nombre like ?";
        Connection con = null;
        Producto registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, "%" +nombre+"%");
            ResultSet respuestaSQL = query.executeQuery();
            while (respuestaSQL.next()) {
                registro = new Producto();
                registro.setIdProducto(respuestaSQL.getInt("idProducto"));
                registro.setNombre(respuestaSQL.getString("nombre"));
                registro.setDescripcion(respuestaSQL.getString("descripcion"));
                registro.setPrecioVenta(respuestaSQL.getDouble("precioVenta"));
                registro.setPrecioCompra(respuestaSQL.getDouble("precioCompra"));
                registro.setImagen1(respuestaSQL.getBytes("imagen1"));
                registro.setImagen2(respuestaSQL.getBytes("imagen2"));
                registro.setImagen3(respuestaSQL.getBytes("imagen3"));
                registro.setExistencia(respuestaSQL.getInt("existencia"));
                registro.setEstado(respuestaSQL.getString("estado"));
                registro.setIdProveedor(respuestaSQL.getInt("idProveedor"));
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
    public Producto seleccionarId(int idProducto) throws Exception{
        String sql = " select * from producto where idProducto = ?";
        Connection con = null;
        Producto registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, idProducto);
            ResultSet respuestaSQL = query.executeQuery();
            while (respuestaSQL.next()) {
                registro = new Producto();
                registro.setIdProducto(respuestaSQL.getInt("idProducto"));
                registro.setNombre(respuestaSQL.getString("nombre"));
                registro.setDescripcion(respuestaSQL.getString("descripcion"));
                registro.setPrecioVenta(respuestaSQL.getDouble("precioVenta"));
                registro.setPrecioCompra(respuestaSQL.getDouble("precioCompra"));
                registro.setImagen1(respuestaSQL.getBytes("imagen1"));
                registro.setImagen2(respuestaSQL.getBytes("imagen2"));
                registro.setImagen3(respuestaSQL.getBytes("imagen3"));
                registro.setExistencia(respuestaSQL.getInt("existencia"));
                registro.setEstado(respuestaSQL.getString("estado"));
                registro.setIdProveedor(respuestaSQL.getInt("idProveedor"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registro;
    }

    @Override
    public boolean actualizar(int idProducto, Producto prod) throws Exception{
        Connection con = null;
        PreparedStatement query = null;
        String sql = " update producto set "
                + "nombre=?, descripcion=?, "
                + "precioVenta=?, precioCompra=?, imagen1=?, "
                + "imagen2=?, imagen3=?, "
                + "existencia=?, estado=?, idProveedor=? "
                + "where idProducto=?";
        try {
            con = ConectorBaseDeDatos.conectar();
            query = con.prepareStatement(sql);
            query.setString(1, prod.getNombre());
            query.setString(2, prod.getDescripcion());
            query.setDouble(3, prod.getPrecioVenta());
            query.setDouble(4, prod.getPrecioCompra());
            query.setBytes(5, prod.getImagen1());
            query.setBytes(6, prod.getImagen2());
            query.setBytes(7, prod.getImagen3());
            query.setInt(8, prod.getExistencia());
            query.setString(9, prod.getEstado());
            query.setInt(10, prod.getIdProveedor());
            query.setInt(11, idProducto);
            int res = query.executeUpdate();
            return (res > 0);
        } catch (Exception e) {
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
    }

    @Override
    public boolean borrar(int idProducto) throws Exception{
        Connection con = null;
        PreparedStatement query = null;
        String sql = "delete from producto where idProducto=?";
        try {
            con = ConectorBaseDeDatos.conectar();
            query = con.prepareStatement(sql);
            query.setInt(1, idProducto);
            int res = query.executeUpdate();
            return (res > 0);
        } catch (Exception e) {
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
    }

    public void restarExistencia(int idProducto, int cantidad) throws Exception{
        Connection con = null;
        String sql = "update producto set existencia = existencia - ? where idProducto = ?";
        try{
        con = ConectorBaseDeDatos.conectar();
        PreparedStatement query = con.prepareStatement(sql);
        query.setInt(1, cantidad);
        query.setInt(2, idProducto);
        }catch(Exception e){
            throw e;
        }  
    }
    
    public void sumarExistencia(int idProducto, int cantidad) throws Exception{
        Connection con = null;
        String sql = "update producto set existencia = existencia + ? where idProducto = ?";
        try{
        con = ConectorBaseDeDatos.conectar();
        PreparedStatement query = con.prepareStatement(sql);
        query.setInt(1, cantidad);
        query.setInt(2, idProducto);
        }catch(Exception e){
            throw e;
        }  
    }
    
    public int obtenerStock(int idProducto) throws Exception {
        int existencia = 0;
        Connection con = null;
        String sql = "select existencia from producto where idProducto = ?";
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, idProducto);
            ResultSet respuestaSQL = query.executeQuery();

            while (respuestaSQL.next()) {
                existencia = respuestaSQL.getInt("existencia");
            }
        } catch (Exception e) {
            throw e;
        }
        return existencia;
    }
    
    public double obtenerPrecio(int idProducto) throws Exception {
        double precio = 0;
        Connection con = null;
        String sql = "select precioCompra from producto where idProducto = ?";
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, idProducto);
            ResultSet respuestaSQL = query.executeQuery();

            while (respuestaSQL.next()) {
                precio = respuestaSQL.getDouble("precioCompra");
            }
        } catch (Exception e) {
            throw e;
        }
        return precio;
    }
}
