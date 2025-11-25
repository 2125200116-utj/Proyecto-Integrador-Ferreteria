
package dao;

import bd.ConectorBaseDeDatos;
import dto.DetallePedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAODetallePedido {
    
    public boolean insertar(DetallePedido dp) throws Exception{
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
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
    }

    public ArrayList<DetallePedido> seleccionarTodos() throws Exception{
        ArrayList<DetallePedido> registros = new ArrayList<>();
        String sql = "select * from detallePedido join pedido "
                + "on detallePedido.idPedido = pedido.idPedido "
                + "where estado=?";
        Connection con = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, "ACTIVO");
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
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registros;
    }

    public ArrayList<DetallePedido> seleccionarId(int idPedido) throws Exception{
        ArrayList<DetallePedido> registros = new ArrayList<>();
        String sql = "select * from detallePedido join pedido "
                + "on detallePedido.idPedido = pedido.idPedido "
                + "where idPedido=? and estado=?";
        Connection con = null;
        DetallePedido registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, idPedido);
            query.setString(2, "ACTIVO");
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
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registros;
    }
}
