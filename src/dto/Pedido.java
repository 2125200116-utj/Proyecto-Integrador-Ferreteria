
package dto;

public class Pedido {
    private int idPedido;
    private double impuesto;
    private String fecha;
    private double total;
    private String estado;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
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
        int hash = 5;
        hash = 53 * hash + this.idPedido;
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
        return "\nPedido{" + "\nid=" + idPedido + ", \nimpuesto=" + impuesto + 
                ", \nfecha=" + fecha + ", \ntotal=" + total + ",\n estado=" 
                + estado + "}\n";
    }
    
    
}
