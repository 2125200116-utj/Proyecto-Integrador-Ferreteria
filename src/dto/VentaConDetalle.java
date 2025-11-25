
package dto;

import java.util.ArrayList;

public class VentaConDetalle {
    private Venta venta;
    private ArrayList<DetalleVenta> detalleVenta;

    public VentaConDetalle(Venta venta, ArrayList<DetalleVenta> detalleVenta) {
       this.venta = venta;
       this.detalleVenta = detalleVenta;
    }

}
