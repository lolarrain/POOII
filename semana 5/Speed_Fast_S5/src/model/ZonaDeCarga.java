package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ZonaDeCarga {

    private final List<Pedido> pedidos = new ArrayList<>();

    public ZonaDeCarga() {
        System.out.println("[Zona de carga inicializada]");
        System.out.println();
    }

    public synchronized void agregarPedido(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException(
                    "No se puede agregar un pedido nulo."
            );
        }

        if (pedido.getEstado() != EstadoPedido.PENDIENTE) {
            throw new IllegalArgumentException(
                    "Solo se pueden agregar pedidos con estado PENDIENTE."
            );
        }

        pedidos.add(pedido);

        System.out.println(
                "Pedido #" + pedido.getId()
                        + " agregado. Destino: "
                        + pedido.getDireccionEntrega()
        );
    }

    public synchronized Pedido retirarPedido() {
        Iterator<Pedido> iterator = pedidos.iterator();

        while (iterator.hasNext()) {
            Pedido pedido = iterator.next();

            if (pedido.getEstado() == EstadoPedido.PENDIENTE) {
                pedido.setEstado(EstadoPedido.EN_REPARTO);
                iterator.remove();

                return pedido;
            }
        }

        return null;
    }
}