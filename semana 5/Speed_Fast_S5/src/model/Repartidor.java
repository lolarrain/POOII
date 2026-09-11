package model;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Representa a un repartidor que puede ejecutarse
 * en un hilo independiente mediante Runnable.
 *
 * Las acciones del repartidor se desarrollan
 * en el método run().
 */
public class Repartidor implements Runnable {

    private final String nombre;
    private final ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    /**
     * Imprime juntos los mensajes correspondientes al retiro
     * de un pedido.
     */
    private static synchronized void mostrarRetiro(
            String identificador,
            Pedido pedido
    ) {
        System.out.println();

        System.out.println(
                identificador
                        + " Retirando pedido #"
                        + pedido.getId()
                        + "..."
        );

        System.out.println(
                identificador
                        + " Estado: "
                        + pedido.getEstado()
        );
    }

    /**
     * Imprime juntos los mensajes correspondientes a la entrega
     * de un pedido.
     */
    private static synchronized void mostrarEntrega(
            String identificador,
            Pedido pedido
    ) {
        System.out.println();

        System.out.println(
                identificador
                        + " Entregando pedido #"
                        + pedido.getId()
                        + "..."
        );

        System.out.println(
                identificador
                        + " Estado: "
                        + pedido.getEstado()
        );
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

            Pedido pedido = zonaDeCarga.retirarPedido();

            if (pedido == null) {
                return;
            }

            String identificador =
                    "[Repartidor - " + nombre + "]";

            /*
             * Este bloque muestra el retiro completo
             * sin mezclarse con otro repartidor.
             */
            mostrarRetiro(identificador, pedido);

            try {
                int tiempoEntrega =
                        ThreadLocalRandom.current().nextInt(
                                1000,
                                3001
                        );

                Thread.sleep(tiempoEntrega);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

                System.out.println(
                        identificador
                                + " fue interrumpido mientras entregaba "
                                + "el pedido #"
                                + pedido.getId()
                                + "."
                );

                return;
            }

            /*
             * El pedido se marca como entregado antes de mostrar
             * el bloque final.
             */
            pedido.setEstado(EstadoPedido.ENTREGADO);

            mostrarEntrega(identificador, pedido);
        }
    }
}