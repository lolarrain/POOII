package model;

/**
 * Representa un pedido de compra express.
 */
public class PedidoExpress extends Pedido {

    /**
     * Constructor de PedidoExpress.
     *
     * @param idPedido identificador del pedido
     * @param direccionEntrega dirección de entrega
     * @param distanciaKm distancia en kilómetros
     */
    public PedidoExpress(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    /**
     * Asigna automáticamente un repartidor con prioridad
     * para entregas express.
     */
    @Override
    public void asignarRepartidor() {
        setRepartidorAsignado("Repartidor express prioritario");
    }

    /**
     * Calcula el tiempo de entrega de una compra express.
     * El tiempo base es de 10 minutos.
     * Si la distancia supera los 5 km, se agregan 5 minutos.
     *
     * @return tiempo estimado en minutos
     */
    @Override
    public int calcularTiempoEntrega() {

        int tiempo = 10;

        if (getDistanciaKm() > 5) {
            tiempo += 5;
        }

        return tiempo;
    }
}