package model;

import java.util.List;
import java.util.Random;

/**
 * Representa a un repartidor de SpeedFast.
 * Cada repartidor funciona como una tarea independiente
 * que procesa secuencialmente los pedidos que tiene asignados.
 */
public class Repartidor implements Runnable {

    private String nombre;
    private List<Pedido> pedidosAsignados;
    private Random random;

    /**
     * Constructor de Repartidor.
     *
     * @param nombre nombre del repartidor
     * @param pedidosAsignados lista de pedidos que debe entregar
     */
    public Repartidor(
            String nombre,
            List<Pedido> pedidosAsignados
    ) {
        this.nombre = nombre;
        this.pedidosAsignados = pedidosAsignados;
        this.random = new Random();
    }

    /**
     * Ejecuta secuencialmente las entregas asignadas
     * al repartidor.
     */
    @Override
    public void run() {

        System.out.println(
                "Repartidor " + nombre + " inicia su ruta."
        );

        for (Pedido pedido : pedidosAsignados) {
            procesarPedido(pedido);
        }

        System.out.println(
                "Repartidor " + nombre + " finalizó su ruta."
        );
    }

    /**
     * Procesa un pedido individual.
     *
     * @param pedido pedido que será entregado
     */
    private void procesarPedido(Pedido pedido) {

        // Si el pedido fue cancelado, se omite la entrega.
        if (pedido.getEstado().equals("Cancelado")) {

            System.out.println(
                    "\n[" + nombre + "] Pedido "
                            + pedido.getIdPedido()
                            + " cancelado. Se omite la entrega."
            );

            System.out.println("----------------------------");
            return;
        }

        System.out.println(
                "\n[" + nombre + "] Iniciando pedido "
                        + pedido.getIdPedido()
        );

        // Asigna el repartidor concreto al pedido.
        pedido.asignarRepartidor(nombre);

        // Reutiliza la lógica de gestión desarrollada previamente.
        GestorEnvios controlador =
                new GestorEnvios(pedido);

        controlador.reservarPedido();
        controlador.despachar();

        // Muestra la información actual del pedido.
        pedido.mostrarResumen();

        System.out.println(
                "[" + nombre + "] Tiempo estimado: "
                        + pedido.calcularTiempoEntrega()
                        + " minutos."
        );

        try {

            // Retardo aleatorio entre 1 y 3 segundos.
            int pausa = 1000 + random.nextInt(2000);

            System.out.println(
                    "[" + nombre + "] Realizando entrega del pedido "
                            + pedido.getIdPedido()
            );

            Thread.sleep(pausa);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            System.out.println(
                    "[" + nombre + "] Ruta interrumpida."
            );

            return;
        }

        System.out.println(
                "[" + nombre + "] Pedido "
                        + pedido.getIdPedido()
                        + " entregado."
        );

        controlador.verHistorial();

        System.out.println("----------------------------");
    }
}