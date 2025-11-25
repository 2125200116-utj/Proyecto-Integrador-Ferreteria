
package dto;

import java.util.ArrayList;

public class PedidoConDetalle {
    
    private Pedido pedido;
    private ArrayList<DetallePedido> detallePedido;

    public PedidoConDetalle(Pedido pedido, ArrayList<DetallePedido> detallePedido) {
        this.pedido = pedido;
        this.detallePedido = detallePedido;
        
        
    }
}
