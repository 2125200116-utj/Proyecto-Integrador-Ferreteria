
package service;

import adminDao.adminDAODetallePedido;
import adminDao.adminDAOPedido;
import adminDao.adminDAOProducto;
import adminDao.adminDAOProveedor;
import bd.ConectorBaseDeDatos;
import dto.DetallePedido;
import dto.Pedido;
import dto.PedidoConDetalle;
import dto.Proveedor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

public class adminPedidoService {

    private adminDAOPedido adminDaoPedido = new adminDAOPedido();
    private adminDAODetallePedido adminDaoDetallePedido = new adminDAODetallePedido();
    private adminDAOProveedor adminDaoProveedor = new adminDAOProveedor();
    private adminDAOProducto adminDaoProducto = new adminDAOProducto();

    public void registrarPedido(Pedido pedido, ArrayList<DetallePedido> detallePedido) throws Exception {
        Connection con = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            con.setAutoCommit(false);
            
        //insertar pedido y obtener id
        adminDaoPedido.insertar(pedido);
        int idPedido = adminDaoPedido.seleccionarUltimoId();
        System.out.println("el id del pedido es: " + idPedido);
        System.out.println("adminDaoPedido pasado");

        //insertar detalle
        for (DetallePedido dp : pedido.getDetallePedido()) {
            dp.setIdPedido(idPedido);
            adminDaoDetallePedido.insertar(dp);
            System.out.println("adminDaoDetallePedido pasado");
            
            //sumar existencia de producto
            adminDaoProducto.sumarExistencia(dp.getIdProducto(), dp.getCantidad());
            System.out.println("adminDaoProducto pasado");
        }
        con.commit();
            con.setAutoCommit(true);
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw ex;
            }
            e.printStackTrace();
        }
        ConectorBaseDeDatos.desconectar(con);
    }

    //obtener pedido con detalle por medio de id de pedido
    public PedidoConDetalle obtenerPedido(int idPedido) throws Exception {
        Pedido pedido = adminDaoPedido.seleccionarId(idPedido);
        ArrayList<DetallePedido> detallePedido = adminDaoDetallePedido.seleccionarId(idPedido);

        return new PedidoConDetalle(pedido, detallePedido);
    }

    //obtener pedido con detalle por medio de nombre de proveedor
    public ArrayList<PedidoConDetalle> buscarPedidoPorNombreProv(String nombre) throws Exception {
        ArrayList<PedidoConDetalle> resultado = new ArrayList<>();
        
        ArrayList<Proveedor> proveedores = adminDaoProveedor.seleccionarAlgunos(nombre);
        
        for (Proveedor pr : proveedores) {
            ArrayList<Pedido> pedidos = adminDaoPedido.seleccionarAlgunos(pr.getIdProveedor());
            
            for (Pedido pe : pedidos){
                ArrayList<DetallePedido> detalle = adminDaoDetallePedido.seleccionarId(pe.getIdPedido());
                
                PedidoConDetalle pcd = new PedidoConDetalle(pe,detalle);
                
                resultado.add(pcd);
            }
        }
        return resultado;
    }
}
