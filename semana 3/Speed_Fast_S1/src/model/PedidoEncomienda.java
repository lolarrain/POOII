package model;

/**
 * Representa un pedido de encomienda.
 */
public class PedidoEncomienda extends Pedido {

    /**
     * Constructor de PedidoEncomienda.
     *
     * @param idPedido identificador del pedido
     * @param direccionEntrega dirección de entrega
     * @param distanciaKm distancia en kilómetros
     */
    public PedidoEncomienda(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    /**
     * Asigna automáticamente un repartidor con vehículo
     * adecuado para transportar encomiendas.
     */
    @Override
    public void asignarRepartidor() {
        setRepartidorAsignado("Repartidor con vehículo de carga");
    }

    /**
     * Calcula el tiempo de entrega de una encomienda.
     * Se consideran 20 minutos base más 1.5 minutos por kilómetro.
     *
     * @return tiempo estimado en minutos
     */
    @Override
    public int calcularTiempoEntrega() {
        return (int) Math.round(20 + (1.5 * getDistanciaKm()));
    }
}