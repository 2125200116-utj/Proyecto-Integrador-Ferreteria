package dao;

import bd.ConectorBaseDeDatos;
import dto.DetalleVenta;
import java.sql.*;
import java.util.ArrayList;

public class DAODetalleVenta {

    public boolean insertar(DetalleVenta dv) throws Exception{
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
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
    }

    public ArrayList<DetalleVenta> seleccionarTodos() throws Exception{
        ArrayList<DetalleVenta> registros = new ArrayList<>();
        String sql = "select * from detalleVenta join venta "
                + "on detalleVenta.idVenta = venta.idVenta "
                + "where estado=?";
        Connection con = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, "ACTIVO");
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
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registros;
    }

    public ArrayList<DetalleVenta> seleccionarId(int idVenta) throws Exception{
        ArrayList<DetalleVenta> registros = new ArrayList<>();
        String sql = "select * from detalleVenta join venta "
                + "on detalleVenta.idVenta = venta.idVenta "
                + "where idVenta=? and estado=?";
        Connection con = null;
        DetalleVenta registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, idVenta);
            query.setString(2, "ACTIVO");
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
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registros;
    }
}
