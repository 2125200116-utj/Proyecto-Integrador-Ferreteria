package adminDao;

import bd.ConectorBaseDeDatos;
import dto.Venta;
import java.sql.*;
import java.util.ArrayList;

public class adminDAOVenta implements adminDAO<Venta>{
    
    @Override
    public boolean insertar(Venta venta) {
        Connection con = null;
        PreparedStatement query = null;
        String sql = " insert into venta (total,impuesto,estado)"
                + "values(?, ?, ?)";
        try {
            con = ConectorBaseDeDatos.conectar();
            query = con.prepareStatement(sql);
            query.setDouble(1, venta.getTotal());
            query.setDouble(2, venta.getImpuesto());
            query.setString(3, venta.getEstado());
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
    public ArrayList<Venta> seleccionarAlgunos(String idVenta){
    ArrayList<Venta> registros = new ArrayList<>();
        String sql = " select * from venta where id=?";
        Connection con = null;
        Venta registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, idVenta);
            ResultSet respuestaSQL = query.executeQuery();
            while (respuestaSQL.next()) {
                registro = new Venta();
                registro.setIdVenta(respuestaSQL.getInt("idVenta"));
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
        
    }
    
    @Override
    public boolean actualizar(int idVenta, Venta venta){
        
    }
    
    @Override
    public boolean borrar(int idVenta){
        
    }
    
}
