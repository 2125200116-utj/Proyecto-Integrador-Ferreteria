package adminDao;

import bd.ConectorBaseDeDatos;
import dto.Venta;
import java.sql.*;
import java.util.ArrayList;

public class adminDAOVenta{
    

    public boolean insertar(Venta venta) throws Exception{
        Connection con = null;
        PreparedStatement query = null;
        String sql = " insert into venta (idCliente,idEmpleado,fecha,total,precio,impuesto,estado)"
                + "values(?, ?, ?, ?, ?, ?, ?)";
        try {
            con = ConectorBaseDeDatos.conectar();
            query = con.prepareStatement(sql);
            query.setInt(1, venta.getIdCliente());
            query.setInt(2, venta.getIdEmpleado());
            query.setString(3, venta.getFecha());
            query.setDouble(4, venta.getTotal());
            query.setDouble(4, venta.getPrecio());
            query.setDouble(5, venta.getImpuesto());
            query.setString(6, venta.getEstado());
            int res = query.executeUpdate();
            return (res > 0);
        } catch (Exception e) {
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
    }
    

    public ArrayList<Venta> seleccionarTodos() throws Exception{
        ArrayList<Venta> registros = new ArrayList<>();
        String sql = " select * from venta";
        Connection con = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            ResultSet respuestaSQL = query.executeQuery();
            while (respuestaSQL.next()) {
                Venta registro = new Venta();
                registro.setIdVenta(respuestaSQL.getInt("idVenta"));
                registro.setIdCliente(respuestaSQL.getInt("idCliente"));
                registro.setIdEmpleado(respuestaSQL.getInt("idEmpleado"));
                registro.setFecha(respuestaSQL.getString("fecha"));
                registro.setTotal(respuestaSQL.getDouble("total"));
                registro.setPrecio(respuestaSQL.getDouble("precio"));
                registro.setImpuesto(respuestaSQL.getDouble("impuesto"));
                registro.setEstado(respuestaSQL.getString("estado"));
                registros.add(registro);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registros;
    }
    
 
    public ArrayList<Venta> seleccionarAlgunos(int idCliente) throws Exception{
    ArrayList<Venta> registros = new ArrayList<>();
        String sql = " select * from venta where idCliente = ?";
        Connection con = null;
        Venta registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, idCliente);
            ResultSet respuestaSQL = query.executeQuery();
            while (respuestaSQL.next()) {
                registro = new Venta();
                registro.setIdVenta(respuestaSQL.getInt("idVenta"));
                registro.setIdCliente(respuestaSQL.getInt("idCliente"));
                registro.setIdEmpleado(respuestaSQL.getInt("idEmpleado"));
                registro.setFecha(respuestaSQL.getString("fecha"));
                registro.setTotal(respuestaSQL.getDouble("total"));
                registro.setPrecio(respuestaSQL.getDouble("precio"));
                registro.setImpuesto(respuestaSQL.getDouble("impuesto"));
                registro.setEstado(respuestaSQL.getString("estado"));
                registros.add(registro);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registros;
    }
    

    public Venta seleccionarId(int idVenta) throws Exception{
    ArrayList<Venta> registros = new ArrayList<>();
        String sql = " select * from venta where idVenta=?";
        Connection con = null;
        Venta registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, idVenta);
            ResultSet respuestaSQL = query.executeQuery();
            while (respuestaSQL.next()) {
                registro = new Venta();
                registro.setIdVenta(respuestaSQL.getInt("idVenta"));
                registro.setIdCliente(respuestaSQL.getInt("idCliente"));
                registro.setIdEmpleado(respuestaSQL.getInt("idEmpleado"));
                registro.setFecha(respuestaSQL.getString("fecha"));
                registro.setTotal(respuestaSQL.getDouble("total"));
                registro.setPrecio(respuestaSQL.getDouble("precio"));
                registro.setImpuesto(respuestaSQL.getDouble("impuesto"));
                registro.setEstado(respuestaSQL.getString("estado"));
                registros.add(registro);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registro;    
    }
    

    public boolean actualizar(int idVenta, Venta venta) throws Exception{
        Connection con = null;
        PreparedStatement query = null;
        String sql = "update venta set idCliente=?, idEmpleado=?, "
                + "fecha=?, total=?, precio=?, impuesto=?, estado=? where idVenta=?";
        try {
            con=ConectorBaseDeDatos.conectar();
            query = con.prepareStatement(sql);
            query.setInt(1, venta.getIdCliente());
            query.setInt(2, venta.getIdEmpleado());
            query.setString(3, venta.getFecha());
            query.setDouble(4, venta.getTotal());
            query.setDouble(5, venta.getPrecio());
            query.setDouble(6, venta.getImpuesto());
            query.setString(7, venta.getEstado());
            query.setInt(8, idVenta);
            int res=query.executeUpdate();
            return (res>0);
        } catch (Exception e) {
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
    }
    

    public boolean borrar(int idVenta) throws Exception{
        Connection con = null;
        PreparedStatement query = null;
        String sql = "delete from venta where idVenta=?";
        try {
            con=ConectorBaseDeDatos.conectar();
            query=con.prepareStatement(sql);
            query.setInt(1, idVenta);
            int res=query.executeUpdate();
            return (res>0);
        } catch (Exception e) {
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
    }
    
    public int seleccionarUltimoId() throws Exception{
        int id = 0;
        String sql="select last_insert_id(idVenta) as id from venta";
        Connection con=null;
        Venta registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            ResultSet respuestaSQL = query.executeQuery();
            
            while(respuestaSQL.next()){
                id = respuestaSQL.getInt("id");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return id;
    }
}

