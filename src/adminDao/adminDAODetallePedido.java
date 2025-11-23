
package adminDao;

import bd.ConectorBaseDeDatos;
import dto.DetallePedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class adminDAODetallePedido{
    public boolean insertar(DetallePedido dp) {
        Connection con = null;
        PreparedStatement query = null;
        String sql = " insert into venta (idPedido,idProducto,cantidad,precio,descuento)"
                + "values(?, ?, ?, ?, ?)";
        try {
            con = ConectorBaseDeDatos.conectar();
            query = con.prepareStatement(sql);
            query.setInt(1, dp.getIdPedido());
            query.setInt(2, dp.getIdProducto());
            query.setInt(3, dp.getCantidad());
            query.setDouble(4, dp.getPrecio());
            query.setDouble(5, dp.getDescuento());
            int res = query.executeUpdate();
            return (res > 0);
        } catch (Exception e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return false;
    }
    
    
    public ArrayList<DetallePedido> seleccionarTodos() {
        ArrayList<DetallePedido> registros = new ArrayList<>();
        String sql = " select * from detallePedido";
        Connection con = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            ResultSet respuestaSQL = query.executeQuery();
            while (respuestaSQL.next()) {
                DetallePedido registro = new DetallePedido();
                registro.setIdDetallePedido(respuestaSQL.getInt("idDetallePedido"));
                registro.setIdPedido(respuestaSQL.getInt("idPedido"));
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
    
    
    public DetallePedido seleccionarId(int idPedido){
    ArrayList<DetallePedido> registros = new ArrayList<>();
        String sql = " select * from detallePedido where idPedido=?";
        Connection con = null;
        DetallePedido registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, idPedido);
            ResultSet respuestaSQL = query.executeQuery();
            while (respuestaSQL.next()) {
                registro = new DetallePedido();
                registro.setIdDetallePedido(respuestaSQL.getInt("idDetallePedido"));
                registro.setIdPedido(respuestaSQL.getInt("idPedido"));
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
    
    public boolean actualizar(int idPedido, DetallePedido dp){
        Connection con = null;
        PreparedStatement query = null;
        String sql = "update detallePedido set idVenta=?, idProducto=?, "
                + "cantidad=?, precio=?, descuento=? where idPedido=?";
        try {
            con=ConectorBaseDeDatos.conectar();
            query = con.prepareStatement(sql);
            query.setInt(1, dp.getIdPedido());
            query.setInt(2, dp.getIdProducto());
            query.setInt(3, dp.getCantidad());
            query.setDouble(4, dp.getPrecio());
            query.setDouble(5, dp.getDescuento());
            query.setInt(7, idPedido);
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
