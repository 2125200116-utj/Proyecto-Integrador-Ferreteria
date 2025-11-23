
package adminDao;

import bd.ConectorBaseDeDatos;
import dto.DetalleVenta;
import java.sql.*;
import java.util.ArrayList;

public class adminDAODetalleVenta{
    
    public boolean insertar(DetalleVenta dv) {
        Connection con = null;
        PreparedStatement query = null;
        String sql = " insert into venta (idVenta,idProducto,cantidad,precio,descuento)"
                + "values(?, ?, ?, ?, ?)";
        try {
            con = ConectorBaseDeDatos.conectar();
            query = con.prepareStatement(sql);
            query.setInt(1, dv.getIdVenta());
            query.setInt(2, dv.getIdProducto());
            query.setInt(3, dv.getCantidad());
            query.setDouble(4, dv.getPrecio());
            query.setDouble(5, dv.getDescuento());
            int res = query.executeUpdate();
            return (res > 0);
        } catch (Exception e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return false;
    }
    
    
    public ArrayList<DetalleVenta> seleccionarTodos() {
        ArrayList<DetalleVenta> registros = new ArrayList<>();
        String sql = " select * from detalleVenta";
        Connection con = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            ResultSet respuestaSQL = query.executeQuery();
            while (respuestaSQL.next()) {
                DetalleVenta registro = new DetalleVenta();
                registro.setIdDetalleVenta(respuestaSQL.getInt("idDetalleVenta"));
                registro.setIdVenta(respuestaSQL.getInt("idVenta"));
                registro.setIdProducto(respuestaSQL.getInt("idProducto"));
                registro.setCantidad(respuestaSQL.getInt("cantidad"));
                registro.setPrecio(respuestaSQL.getDouble("precio"));
                registro.setDescuento(respuestaSQL.getDouble("descuento"));
                registros.add(registro);
            }
        } catch (Exception e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registros;
    }
    
    
    public DetalleVenta seleccionarId(int idVenta){
    ArrayList<DetalleVenta> registros = new ArrayList<>();
        String sql = " select * from detalleVenta where idVenta=?";
        Connection con = null;
        DetalleVenta registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, idVenta);
            ResultSet respuestaSQL = query.executeQuery();
            while (respuestaSQL.next()) {
                registro = new DetalleVenta();
                registro.setIdDetalleVenta(respuestaSQL.getInt("idDetalleVenta"));
                registro.setIdVenta(respuestaSQL.getInt("idVenta"));
                registro.setIdProducto(respuestaSQL.getInt("idProducto"));
                registro.setCantidad(respuestaSQL.getInt("cantidad"));
                registro.setPrecio(respuestaSQL.getDouble("precio"));
                registro.setDescuento(respuestaSQL.getDouble("descuento"));
                registros.add(registro);
            }
        } catch (Exception e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registro;    
    }
    
    public boolean actualizar(int idVenta, DetalleVenta dv){
        Connection con = null;
        PreparedStatement query = null;
        String sql = "update detalleVenta set idVenta=?, idProducto=?, "
                + "cantidad=?, precio=?, descuento=? where idVenta=?";
        try {
            con=ConectorBaseDeDatos.conectar();
            query = con.prepareStatement(sql);
            query.setInt(1, dv.getIdVenta());
            query.setInt(2, dv.getIdProducto());
            query.setInt(3, dv.getCantidad());
            query.setDouble(4, dv.getPrecio());
            query.setDouble(5, dv.getDescuento());
            query.setInt(7, idVenta);
            int res=query.executeUpdate();
            return (res>0);
        } catch (Exception e) {
            System.out.println("Error de SQL: "+ e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return false;
    }
    

    public boolean borrar(int idDetalleVenta){
        Connection con = null;
        PreparedStatement query = null;
        String sql = "delete from detalleVenta where idVenta=?";
        try {
            con=ConectorBaseDeDatos.conectar();
            query=con.prepareStatement(sql);
            query.setInt(1, idDetalleVenta);
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
