package dao;

import bd.ConectorBaseDeDatos;
import dto.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DAOVenta {
    
    public boolean insertar(Venta venta) {
        Connection con = null;
        PreparedStatement query = null;
        String sql = " insert into producto (total,impuesto,estado)"
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
}
