package dao;

import bd.ConectorBaseDeDatos;
import dto.Venta;
import java.sql.*;
import java.util.ArrayList;

public class DAOVenta implements DAO<Venta>{
    
    @Override
    public boolean insertar(Venta venta) {
        Connection con = null;
        PreparedStatement query = null;
        String sql = " insert into venta (idCliente,idEmpleado,fecha,total,impuesto,estado)"
                + "values(?, ?, ?, ?, ?, ?)";
        try {
            con = ConectorBaseDeDatos.conectar();
            query = con.prepareStatement(sql);
            query.setInt(1, venta.getIdCliente());
            query.setInt(2, venta.getIdEmpleado());
            query.setString(3, venta.getFecha());
            query.setDouble(4, venta.getTotal());
            query.setDouble(5, venta.getImpuesto());
            query.setString(6, venta.getEstado());
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
    public ArrayList<Venta> seleccionarTodos() {
        ArrayList<Venta> registros = new ArrayList<>();
        String sql = " select * from venta where estado=?";
        Connection con = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, "ACTIVO");
            ResultSet respuestaSQL = query.executeQuery();
            while (respuestaSQL.next()) {
                Venta registro = new Venta();
                registro.setIdVenta(respuestaSQL.getInt("idVenta"));
                registro.setIdCliente(respuestaSQL.getInt("idCliente"));
                registro.setIdEmpleado(respuestaSQL.getInt("idEmpleado"));
                registro.setFecha(respuestaSQL.getString("fecha"));
                registro.setTotal(respuestaSQL.getDouble("total"));
                registro.setImpuesto(respuestaSQL.getDouble("impuesto"));
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
    public ArrayList<Venta> seleccionarAlgunos(String fecha){
    ArrayList<Venta> registros = new ArrayList<>();
        String sql = " select * from venta where fecha=? and estado=?";
        Connection con = null;
        Venta registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, fecha);
            query.setString(2, "ACTIVO");
            ResultSet respuestaSQL = query.executeQuery();
            while (respuestaSQL.next()) {
                registro = new Venta();
                registro.setIdVenta(respuestaSQL.getInt("idVenta"));
                registro.setIdCliente(respuestaSQL.getInt("idCliente"));
                registro.setIdEmpleado(respuestaSQL.getInt("idEmpleado"));
                registro.setFecha(respuestaSQL.getString("fecha"));
                registro.setTotal(respuestaSQL.getDouble("total"));
                registro.setImpuesto(respuestaSQL.getDouble("impuesto"));
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
    public Venta seleccionarId(int idVenta){
    ArrayList<Venta> registros = new ArrayList<>();
        String sql = " select * from venta where idVenta=? and estado=?";
        Connection con = null;
        Venta registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, idVenta);
            query.setString(2, "ACTIVO");
            ResultSet respuestaSQL = query.executeQuery();
            while (respuestaSQL.next()) {
                registro = new Venta();
                registro.setIdVenta(respuestaSQL.getInt("idVenta"));
                registro.setIdCliente(respuestaSQL.getInt("idCliente"));
                registro.setIdEmpleado(respuestaSQL.getInt("idEmpleado"));
                registro.setFecha(respuestaSQL.getString("fecha"));
                registro.setTotal(respuestaSQL.getDouble("total"));
                registro.setImpuesto(respuestaSQL.getDouble("impuesto"));
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
    public boolean actualizar(int idVenta, Venta venta){
        Connection con = null;
        PreparedStatement query = null;
        String sql = "update venta set idCliente=?, idEmpleado=?, "
                + "fecha=?, total=?, impuesto=?, estado=? where idVenta=? and estado=?";
        try {
            con=ConectorBaseDeDatos.conectar();
            query = con.prepareStatement(sql);
            query.setInt(1, venta.getIdCliente());
            query.setInt(2, venta.getIdEmpleado());
            query.setString(3, venta.getFecha());
            query.setDouble(4, venta.getTotal());
            query.setDouble(5, venta.getImpuesto());
            query.setString(6, venta.getEstado());
            query.setInt(7, idVenta);
            query.setString(8, "ACTIVO");
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
    public boolean borrar(int idVenta){
        Connection con = null;
        PreparedStatement query = null;
        String sql = "update venta set estado=? where idVenta=?";
        try {
            con=ConectorBaseDeDatos.conectar();
            query=con.prepareStatement(sql);
            query.setString(1, "INACTIVO");
            query.setInt(2, idVenta);
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
