package app;

import model.EstadoPedido;
import model.Pedido;
import model.Repartidor;
import model.ZonaDeCarga;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal que inicia la simulación concurrente de SpeedFast.
 */
public class Main {

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args argumentos recibidos desde la consola
     * @throws InterruptedException si un hilo es interrumpido durante
     *                              la espera de finalización
     */
    public static void main(String[] args)
            throws InterruptedException {

        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();
        List<Pedido> pedidos = new ArrayList<>();

        pedidos.add(new Pedido(
                101,
                "Av. Argentina 1234",
                EstadoPedido.PENDIENTE
        ));

        pedidos.add(new Pedido(
                102,
                "Av. Brasil 567",
                EstadoPedido.PENDIENTE
        ));

        pedidos.add(new Pedido(
                103,
                "Pasaje Las Palmeras 890",
                EstadoPedido.PENDIENTE
        ));

        pedidos.add(new Pedido(
                104,
                "Calle Colón 321",
                EstadoPedido.PENDIENTE
        ));

        pedidos.add(new Pedido(
                105,
                "Av. Balmaceda 456",
                EstadoPedido.PENDIENTE
        ));

        for (Pedido pedido : pedidos) {
            zonaDeCarga.agregarPedido(pedido);
        }

        Thread pedro = new Thread(
                new Repartidor("Pedro", zonaDeCarga)
        );

        Thread maria = new Thread(
                new Repartidor("María", zonaDeCarga)
        );

        Thread carlos = new Thread(
                new Repartidor("Carlos", zonaDeCarga)
        );

        pedro.start();
        maria.start();
        carlos.start();

        pedro.join();
        maria.join();
        carlos.join();

        boolean todosEntregados = pedidos.stream()
                .allMatch(
                        pedido -> pedido.getEstado()
                                == EstadoPedido.ENTREGADO
                );

        if (todosEntregados) {
            System.out.println(
                    "Todos los pedidos han sido entregados correctamente"
            );
        } else {
            System.out.println(
                    "El proceso terminó, pero hay pedidos pendientes "
                            + "de entrega."
            );
        }
    }
}
