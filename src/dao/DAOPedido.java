
package dao;

import bd.ConectorBaseDeDatos;
import dto.Pedido;
import java.sql.*;
import java.util.ArrayList;

public class DAOPedido implements DAO<Pedido>{
    
    @Override
    public boolean insertar(Pedido pedido) {
        Connection con = null;
        PreparedStatement query = null;
        String sql = " insert into pedido (idProveedor,impuesto,fecha,total,estado)"
                + "values(?, ?, ?, ?, ?)";
        try {
            con = ConectorBaseDeDatos.conectar();
            query = con.prepareStatement(sql);
            query.setInt(1, pedido.getIdProveedor());
            query.setDouble(2, pedido.getImpuesto());
            query.setString(3, pedido.getFecha());
            query.setDouble(4, pedido.getTotal());
            query.setString(5, pedido.getEstado());
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
    public ArrayList<Pedido> seleccionarTodos() {
        ArrayList<Pedido> registros = new ArrayList<>();
        String sql = " select * from pedido where estado=?";
        Connection con = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, "ACTIVO");
            ResultSet respuestaSQL = query.executeQuery();
            while (respuestaSQL.next()) {
                Pedido registro = new Pedido();
                registro.setIdPedido(respuestaSQL.getInt("idPedido"));
                registro.setIdProveedor(respuestaSQL.getInt("idProveedor"));
                registro.setImpuesto(respuestaSQL.getDouble("impuesto"));
                registro.setFecha(respuestaSQL.getString("fecha"));
                registro.setTotal(respuestaSQL.getDouble("total"));
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
    public ArrayList<Pedido> seleccionarAlgunos(String fecha){
    ArrayList<Pedido> registros = new ArrayList<>();
        String sql = " select * from pedido where fecha=? and estado=?";
        Connection con = null;
        Pedido registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, fecha);
            query.setString(2, "ACTIVO");
            ResultSet respuestaSQL = query.executeQuery();
            while (respuestaSQL.next()) {
                registro = new Pedido();
                registro.setIdPedido(respuestaSQL.getInt("idPedido"));
                registro.setIdProveedor(respuestaSQL.getInt("idProveedor"));
                registro.setImpuesto(respuestaSQL.getDouble("impuesto"));
                registro.setFecha(respuestaSQL.getString("fecha"));
                registro.setTotal(respuestaSQL.getDouble("total"));
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
    public Pedido seleccionarId(int idPedido){
    ArrayList<Pedido> registros = new ArrayList<>();
        String sql = " select * from venta where idPedido=? and estado=?";
        Connection con = null;
        Pedido registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, idPedido);
            query.setString(2, "ACTIVO");
            ResultSet respuestaSQL = query.executeQuery();
            while (respuestaSQL.next()) {
                registro = new Pedido();
                registro.setIdPedido(respuestaSQL.getInt("idPedido"));
                registro.setIdProveedor(respuestaSQL.getInt("idProveedor"));
                registro.setImpuesto(respuestaSQL.getDouble("impuesto"));
                registro.setFecha(respuestaSQL.getString("fecha"));
                registro.setTotal(respuestaSQL.getDouble("total"));
                registro.setEstado(respuestaSQL.getString("estado"));
                registros.add(registro);
            }
        } catch (Exception e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registro;    
    }
    
    @Override
    public boolean actualizar(int idPedido, Pedido pedido){
        Connection con = null;
        PreparedStatement query = null;
        String sql = "update venta set idProveedor=?, impuesto=?, "
                + "fecha=?, total=?, estado=? where idPedido=? and estado=?";
        try {
            con=ConectorBaseDeDatos.conectar();
            query = con.prepareStatement(sql);
            query.setInt(1, pedido.getIdProveedor());
            query.setDouble(2, pedido.getImpuesto());
            query.setString(3, pedido.getFecha());
            query.setDouble(4, pedido.getTotal());
            query.setString(5, pedido.getEstado());
            query.setInt(5, idPedido);
            query.setString(6, "ACTIVO");
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
    public boolean borrar(int idPedido){
        Connection con = null;
        PreparedStatement query = null;
        String sql = "update pedido set estado=? where idPedido=?";
        try {
            con=ConectorBaseDeDatos.conectar();
            query=con.prepareStatement(sql);
            query.setString(1, "INACTIVO");
            query.setInt(2, idPedido);
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
