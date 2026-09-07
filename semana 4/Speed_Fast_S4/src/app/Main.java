package app;

import interfaces.Cancelable;
import model.GestorEnvios;
import model.Pedido;
import model.PedidoComida;
import model.PedidoEncomienda;
import model.PedidoExpress;
import model.Repartidor;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        System.out.println(
                "===== SPEEDFAST - SIMULACIÓN CONCURRENTE ====="
        );

        // Pedidos de Pedro
        Pedido pedido101 = new PedidoComida(
                101,
                "Av. Argentina 1234",
                4.0
        );

        Pedido pedido102 = new PedidoEncomienda(
                102,
                "Prat 850",
                5.0
        );

        // Pedidos de María
        Pedido pedido103 = new PedidoExpress(
                103,
                "Av. Brasil 450",
                7.0
        );

        Pedido pedido104 = new PedidoComida(
                104,
                "Matta 920",
                3.5
        );

        // Pedidos de Carlos
        Pedido pedido105 = new PedidoEncomienda(
                105,
                "Condell 710",
                6.0
        );

        Pedido pedido106 = new PedidoExpress(
                106,
                "Av. Angamos 1550",
                4.5
        );

        /*
         * Demostración de la interfaz Cancelable.
         * El pedido 104 se cancela antes de iniciar las rutas.
         */
        GestorEnvios controladorCancelacion =
                new GestorEnvios(pedido104);

        Cancelable cancelable = controladorCancelacion;
        cancelable.cancelar();

        // Pedidos asignados a cada repartidor
        List<Pedido> pedidosPedro = List.of(
                pedido101,
                pedido102
        );

        List<Pedido> pedidosMaria = List.of(
                pedido103,
                pedido104
        );

        List<Pedido> pedidosCarlos = List.of(
                pedido105,
                pedido106
        );

        // Creación de los repartidores
        Repartidor pedro = new Repartidor(
                "Pedro",
                pedidosPedro
        );

        Repartidor maria = new Repartidor(
                "María",
                pedidosMaria
        );

        Repartidor carlos = new Repartidor(
                "Carlos",
                pedidosCarlos
        );

        /*
         * Pool de tres hilos.
         * Cada repartidor puede ejecutarse de forma independiente.
         */
        ExecutorService executor =
                Executors.newFixedThreadPool(3);

        // Ejecución concurrente de los repartidores
        executor.execute(pedro);
        executor.execute(maria);
        executor.execute(carlos);

        /*
         * Se dejan de aceptar nuevas tareas.
         * Las tareas ya enviadas continúan hasta terminar.
         */
        executor.shutdown();

        /*
         * Main espera a que todos los repartidores
         * hayan terminado sus rutas.
         */
        try {

            if (!executor.awaitTermination(
                    1,
                    TimeUnit.MINUTES
            )) {

                System.out.println(
                        "Los repartidores no finalizaron "
                                + "dentro del tiempo esperado."
                );

                executor.shutdownNow();
            }

        } catch (InterruptedException e) {

            executor.shutdownNow();
            Thread.currentThread().interrupt();

            System.out.println(
                    "La simulación fue interrumpida."
            );
        }

        System.out.println(
                "\nTodas las rutas han finalizado."
        );
    }
}