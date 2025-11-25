
package adminDao;

import bd.ConectorBaseDeDatos;
import dto.Pedido;
import java.sql.*;
import java.util.ArrayList;

public class adminDAOPedido{
    

    public boolean insertar(Pedido pedido) throws Exception{
        Connection con = null;
        PreparedStatement query = null;
        String sql = " insert into pedido (idProveedor,precio,impuesto,fecha,total,estado)"
                + "values(?, ?, ?, ?, ?, ?)";
        try {
            con = ConectorBaseDeDatos.conectar();
            query = con.prepareStatement(sql);
            query.setInt(1, pedido.getIdProveedor());
            query.setDouble(2, pedido.getPrecio());
            query.setDouble(3, pedido.getImpuesto());
            query.setString(4, pedido.getFecha());
            query.setDouble(5, pedido.getTotal());
            query.setString(6, pedido.getEstado());
            int res = query.executeUpdate();
            return (res > 0);
        } catch (Exception e) {
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
    }
    

    public ArrayList<Pedido> seleccionarTodos() throws Exception{
        ArrayList<Pedido> registros = new ArrayList<>();
        String sql = " select * from pedido";
        Connection con = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            ResultSet respuestaSQL = query.executeQuery();
            while (respuestaSQL.next()) {
                Pedido registro = new Pedido();
                registro.setIdPedido(respuestaSQL.getInt("idPedido"));
                registro.setIdProveedor(respuestaSQL.getInt("idProveedor"));
                registro.setPrecio(respuestaSQL.getDouble("precio"));
                registro.setImpuesto(respuestaSQL.getDouble("impuesto"));
                registro.setFecha(respuestaSQL.getString("fecha"));
                registro.setTotal(respuestaSQL.getDouble("total"));
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
    

    public ArrayList<Pedido> seleccionarAlgunos(int idProveedor) throws Exception{
    ArrayList<Pedido> registros = new ArrayList<>();
        String sql = " select * from pedido where idProveedor = ?";
        Connection con = null;
        Pedido registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, idProveedor);
            ResultSet respuestaSQL = query.executeQuery();
            while (respuestaSQL.next()) {
                registro = new Pedido();
                registro.setIdPedido(respuestaSQL.getInt("idPedido"));
                registro.setIdProveedor(respuestaSQL.getInt("idProveedor"));
                registro.setPrecio(respuestaSQL.getDouble("precio"));
                registro.setImpuesto(respuestaSQL.getDouble("impuesto"));
                registro.setFecha(respuestaSQL.getString("fecha"));
                registro.setTotal(respuestaSQL.getDouble("total"));
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
    

    public Pedido seleccionarId(int idPedido) throws Exception{
    ArrayList<Pedido> registros = new ArrayList<>();
        String sql = " select * from pedido where idPedido=?";
        Connection con = null;
        Pedido registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, idPedido);
            ResultSet respuestaSQL = query.executeQuery();
            while (respuestaSQL.next()) {
                registro = new Pedido();
                registro.setIdPedido(respuestaSQL.getInt("idPedido"));
                registro.setIdProveedor(respuestaSQL.getInt("idProveedor"));
                registro.setPrecio(respuestaSQL.getDouble("precio"));
                registro.setImpuesto(respuestaSQL.getDouble("impuesto"));
                registro.setFecha(respuestaSQL.getString("fecha"));
                registro.setTotal(respuestaSQL.getDouble("total"));
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
    

    public boolean actualizar(int idPedido, Pedido pedido) throws Exception{
        Connection con = null;
        PreparedStatement query = null;
        String sql = "update pedido set idProveedor=?, precio=?, impuesto=?, "
                + "fecha=?, total=?, estado=? where idPedido=?";
        try {
            con=ConectorBaseDeDatos.conectar();
            query = con.prepareStatement(sql);
            query.setInt(1, pedido.getIdProveedor());
            query.setDouble(2, pedido.getPrecio());
            query.setDouble(3, pedido.getImpuesto());
            query.setString(4, pedido.getFecha());
            query.setDouble(5, pedido.getTotal());
            query.setString(6, pedido.getEstado());
            query.setInt(7, idPedido);
            int res=query.executeUpdate();
            return (res>0);
        } catch (Exception e) {
            throw e;
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
    }
    

    public boolean borrar(int idPedido) throws Exception{
        Connection con = null;
        PreparedStatement query = null;
        String sql = "delete from pedido where idPedido=?";
        try {
            con=ConectorBaseDeDatos.conectar();
            query=con.prepareStatement(sql);
            query.setInt(1, idPedido);
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
        String sql="select last_insert_id(idPedido) as id from pedido";
        Connection con=null;
        Pedido registro = null;
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
