package model;

/**
 * Representa un pedido de comida.
 */
public class PedidoComida extends Pedido {

    /**
     * Constructor de PedidoComida.
     *
     * @param idPedido identificador del pedido
     * @param direccionEntrega dirección de entrega
     * @param distanciaKm distancia en kilómetros
     */
    public PedidoComida(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    /**
     * Asigna automáticamente un repartidor especializado
     * en entregas de comida.
     */
    @Override
    public void asignarRepartidor() {
        setRepartidorAsignado("Repartidor en motocicleta");
    }

    /**
     * Calcula el tiempo de entrega de un pedido de comida.
     * Se consideran 15 minutos base más 2 minutos por kilómetro.
     *
     * @return tiempo estimado en minutos
     */
    @Override
    public int calcularTiempoEntrega() {
        return (int) Math.round(15 + (2 * getDistanciaKm()));
    }
}
