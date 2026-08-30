package app;

import model.ControladorDeEnvios;
import model.Pedido;
import model.PedidoComida;
import model.PedidoEncomienda;
import model.PedidoExpress;

import java.util.List;

/**
 * Clase principal del sistema SpeedFast.
 */
public class Main {

    /**
     * Método principal para probar los distintos tipos de pedidos
     * mediante abstracción, polimorfismo e interfaces.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {

        Pedido pedidoComida = new PedidoComida(
                101,
                "Av. Argentina 1234",
                4.0
        );

        Pedido pedidoEncomienda = new PedidoEncomienda(
                102,
                "Prat 850",
                5.0
        );

        Pedido pedidoExpress = new PedidoExpress(
                103,
                "Av. Brasil 450",
                7.0
        );

        List<Pedido> pedidos = List.of(
                pedidoComida,
                pedidoEncomienda,
                pedidoExpress
        );

        System.out.println("===== SPEEDFAST =====");

        /**
         * Demostración de sobrescritura.
         * Cada tipo de pedido ejecuta su propia versión
         * de asignarRepartidor() y calcularTiempoEntrega().
         */
        for (Pedido pedido : pedidos) {

            System.out.println("\n----------------------------");

            pedido.asignarRepartidor();
            pedido.mostrarResumen();

            System.out.println(
                    "Tiempo estimado de entrega: "
                            + pedido.calcularTiempoEntrega()
                            + " minutos"
            );
        }

        /**
         * Demostración de sobrecarga.
         * Se utiliza asignarRepartidor(String nombre)
         * para realizar una asignación manual.
         */
        System.out.println("\n===== ASIGNACIÓN MANUAL =====");

        pedidoExpress.asignarRepartidor("Pedro Rojas");
        pedidoExpress.mostrarResumen();

        /**
         * Demostración de las operaciones implementadas
         * mediante interfaces.
         */
        System.out.println("\n===== GESTIÓN DE ENVÍOS =====");

        ControladorDeEnvios controladorComida =
                new ControladorDeEnvios(pedidoComida);

        controladorComida.reservarPedido();
        controladorComida.despachar();
        controladorComida.verHistorial();

        System.out.println("\n----------------------------");

        ControladorDeEnvios controladorEncomienda =
                new ControladorDeEnvios(pedidoEncomienda);

        controladorEncomienda.reservarPedido();
        controladorEncomienda.cancelar();
        controladorEncomienda.verHistorial();
    }
}