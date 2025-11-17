
package adminDao;

import bd.ConectorBaseDeDatos;
import dto.Pedido;
import java.sql.*;
import java.util.ArrayList;

public class adminDAOPedido implements adminDAO<Pedido>{
    
    @Override
    public boolean insertar(Pedido pedido) {
        Connection con = null;
        PreparedStatement query = null;
        String sql = " insert into pedido (total,impuesto,estado)"
                + "values(?, ?, ?)";
        try {
            con = ConectorBaseDeDatos.conectar();
            query = con.prepareStatement(sql);
            query.setDouble(1, pedido.getTotal());
            query.setDouble(2, pedido.getImpuesto());
            query.setString(3, pedido.getEstado());
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
        
    }
    
    @Override
    public ArrayList<Pedido> seleccionarAlgunos(String dato){
        
    }
    
    @Override
    public Pedido seleccionarId(int idPedido){
        
    }
    
    @Override
    public boolean actualizar(int idPedido, Pedido pedido){
        
    }
    
    @Override
    public boolean borrar(int idPedido){
        
    }
    
}
