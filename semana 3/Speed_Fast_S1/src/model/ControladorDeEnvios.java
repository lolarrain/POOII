package model;

import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de gestionar las operaciones asociadas
 * al envío de un pedido.
 */
public class ControladorDeEnvios
        implements Despachable, Cancelable, Rastreable {

    private Pedido pedido;
    private List<String> historial;

    /**
     * Constructor de ControladorDeEnvios.
     *
     * @param pedido pedido que será gestionado
     */
    public ControladorDeEnvios(Pedido pedido) {
        this.pedido = pedido;
        this.historial = new ArrayList<>();

        historial.add("Pedido creado");
    }

    /**
     * Reserva el pedido para iniciar su proceso de entrega.
     */
    public void reservarPedido() {

        if (pedido.getEstado().equals("Cancelado")) {
            System.out.println("No se puede reservar un pedido cancelado.");
            return;
        }

        pedido.setEstado("Reservado");

        historial.add("Pedido reservado");

        System.out.println(
                "Pedido " + pedido.getIdPedido() + " reservado."
        );
    }

    /**
     * Despacha el pedido.
     */
    @Override
    public void despachar() {

        if (pedido.getEstado().equals("Cancelado")) {
            System.out.println(
                    "No se puede despachar un pedido cancelado."
            );
            return;
        }

        if (pedido.getRepartidorAsignado().equals("Sin asignar")) {
            pedido.asignarRepartidor();
        }

        pedido.setEstado("Despachado");

        historial.add(
                "Pedido despachado con "
                        + pedido.getRepartidorAsignado()
        );

        System.out.println(
                "Pedido " + pedido.getIdPedido() + " despachado."
        );
    }

    /**
     * Cancela el pedido.
     */
    @Override
    public void cancelar() {

        if (pedido.getEstado().equals("Despachado")) {
            System.out.println(
                    "No se puede cancelar un pedido ya despachado."
            );
            return;
        }

        pedido.setEstado("Cancelado");

        historial.add("Pedido cancelado");

        System.out.println(
                "Pedido " + pedido.getIdPedido() + " cancelado."
        );
    }

    /**
     * Muestra el historial de operaciones del pedido.
     */
    @Override
    public void verHistorial() {

        System.out.println(
                "Historial del pedido " + pedido.getIdPedido() + ":"
        );

        for (String evento : historial) {
            System.out.println("- " + evento);
        }
    }
}