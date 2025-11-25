
package dto;

import java.util.ArrayList;

public class Pedido {
    private int idPedido;
    private int idProveedor;
    private double impuesto;
    private double precio;
    private String fecha;
    private double total;
    private String estado;

    private ArrayList<DetallePedido> detallePedido = new ArrayList<>();
    
    public ArrayList<DetallePedido> getDetallePedido(){
        return detallePedido;
    }
    
    public void setDetallePedido(ArrayList<DetallePedido> detallePedido) {
        this.detallePedido = detallePedido;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.idPedido;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pedido other = (Pedido) obj;
        return this.idPedido == other.idPedido;
    }

    @Override
    public String toString() {
        return "\nPedido{" + "\nidPedido=" + idPedido + ", \nidProveedor=" + idProveedor + ",\n impuesto=" + 
                impuesto + ", \nprecio=" + precio + ", \nfecha=" + fecha + ", \ntotal=" + total + ", \nestado=" + estado + 
                ", \ndetallePedido=" + detallePedido + "}\n";
    }
    
    
}
