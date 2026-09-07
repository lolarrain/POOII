package model;

/**
 * Clase abstracta que representa un pedido genérico de SpeedFast.
 * Contiene los atributos y comportamientos comunes a todos los tipos de pedido.
 */
public abstract class Pedido {

    private int idPedido;
    private String direccionEntrega;
    private double distanciaKm;
    private String estado;
    private String repartidorAsignado;

    /**
     * Constructor de la clase Pedido.
     *
     * @param idPedido identificador único del pedido
     * @param direccionEntrega dirección donde se realizará la entrega
     * @param distanciaKm distancia de la entrega en kilómetros
     */
    public Pedido(int idPedido, String direccionEntrega, double distanciaKm) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
        this.estado = "Pendiente";
        this.repartidorAsignado = "Sin asignar";
    }

    /**
     * Muestra los datos principales del pedido.
     */
    public void mostrarResumen() {
        System.out.println("ID Pedido: " + idPedido);
        System.out.println("Dirección de entrega: " + direccionEntrega);
        System.out.println("Distancia: " + distanciaKm + " km");
        System.out.println("Estado: " + estado);
        System.out.println("Repartidor: " + repartidorAsignado);
    }

    /**
     * Asigna automáticamente un repartidor.
     * Cada tipo de pedido debe implementar su propia lógica.
     */
    public abstract void asignarRepartidor();

    /**
     * Asigna manualmente un repartidor mediante su nombre.
     * Este método corresponde a una sobrecarga de asignarRepartidor().
     *
     * @param nombre nombre del repartidor
     */
    public void asignarRepartidor(String nombre) {
        repartidorAsignado = nombre;
    }

    /**
     * Calcula el tiempo estimado de entrega.
     * Cada tipo de pedido debe implementar su propia lógica.
     *
     * @return tiempo estimado de entrega en minutos
     */
    public abstract int calcularTiempoEntrega();

    public int getIdPedido() {
        return idPedido;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRepartidorAsignado() {
        return repartidorAsignado;
    }

    /**
     * Permite que las clases derivadas asignen un repartidor
     * manteniendo el atributo encapsulado.
     *
     * @param repartidorAsignado descripción o nombre del repartidor
     */
    protected void setRepartidorAsignado(String repartidorAsignado) {
        this.repartidorAsignado = repartidorAsignado;
    }
}