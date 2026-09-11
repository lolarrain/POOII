package app;

import model.EstadoPedido;
import model.Pedido;
import model.Repartidor;
import model.ZonaDeCarga;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Clase principal que inicia la simulación concurrente de SpeedFast.
 */
public class Main {

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args argumentos recibidos desde la consola
     * @throws InterruptedException si el hilo principal es interrumpido
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

        ExecutorService executor =
                Executors.newFixedThreadPool(3);

        executor.execute(
                new Repartidor("Pedro", zonaDeCarga)
        );

        executor.execute(
                new Repartidor("María", zonaDeCarga)
        );

        executor.execute(
                new Repartidor("Carlos", zonaDeCarga)
        );

        executor.shutdown();

        executor.awaitTermination(
                1,
                TimeUnit.MINUTES
        );

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
