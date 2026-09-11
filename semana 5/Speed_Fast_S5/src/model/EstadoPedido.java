package model;
/**
 * Representa los estados posibles de un pedido dentro del sistema SpeedFast.
 */
public enum EstadoPedido {

    /**
     * El pedido fue creado, pero todavía no ha sido retirado.
     */
    PENDIENTE,

    /**
     * El pedido fue asignado a un repartidor y está siendo transportado.
     */
    EN_REPARTO,

    /**
     * El pedido fue entregado al destinatario.
     */
    ENTREGADO
}