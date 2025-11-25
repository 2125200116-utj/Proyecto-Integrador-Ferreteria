    package service;

import adminDao.adminDAOCliente;
import adminDao.adminDAODetalleVenta;
import adminDao.adminDAOProducto;
import adminDao.adminDAOVenta;
import bd.ConectorBaseDeDatos;
import dto.Cliente;
import dto.DetalleVenta;
import dto.Venta;
import dto.VentaConDetalle;
import java.util.ArrayList;
import java.sql.*;
import java.sql.SQLException;

public class adminVentaService {

    private adminDAOVenta adminDaoVenta = new adminDAOVenta();
    private adminDAODetalleVenta adminDaoDetalleVenta = new adminDAODetalleVenta();
    private adminDAOCliente adminDaoCliente = new adminDAOCliente();
    private adminDAOProducto adminDaoProducto = new adminDAOProducto();

    public void registrarVenta(Venta venta, ArrayList<DetalleVenta> detalleVenta) throws Exception {
       Connection con = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            con.setAutoCommit(false);

            //insertar venta y obtener id
            adminDaoVenta.insertar(venta);
            int idVenta = adminDaoVenta.seleccionarUltimoId();

            //insertar detalle
            for (DetalleVenta dv : venta.getDetalleVenta()) {
                dv.setIdVenta(idVenta);
                adminDaoDetalleVenta.insertar(dv);

                //restar existencia de producto
                int existencia = adminDaoProducto.obtenerStock(dv.getIdProducto());
                if (existencia < dv.getCantidad()) {
                    throw new Exception("Stock insuficiente para el producto" + dv.getIdProducto());
                } else {
                    adminDaoProducto.restarExistencia(dv.getIdProducto(), dv.getCantidad());
                }
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

    //obtener venta con detalle por medio de id de venta
    public VentaConDetalle obtenerVenta(int idVenta) throws Exception {
        Venta venta = adminDaoVenta.seleccionarId(idVenta);
        ArrayList<DetalleVenta> detalleVenta = adminDaoDetalleVenta.seleccionarId(idVenta);

        return new VentaConDetalle(venta, detalleVenta);
    }

    //obtener venta con detalle por medio de nombre de cliente
    public ArrayList<VentaConDetalle> buscarVentaPorNombreCliente(String nombre) throws Exception {
        ArrayList<VentaConDetalle> resultado = new ArrayList<>();

        ArrayList<Cliente> clientes = adminDaoCliente.seleccionarAlgunos(nombre);

        for (Cliente c : clientes) {
            ArrayList<Venta> ventas = adminDaoVenta.seleccionarAlgunos(c.getIdCliente());

            for (Venta v : ventas) {
                ArrayList<DetalleVenta> detalle = adminDaoDetalleVenta.seleccionarId(v.getIdVenta());

                VentaConDetalle vcd = new VentaConDetalle(v, detalle);

                resultado.add(vcd);
            }
        }
        return resultado;
    }
    
}
