package model;
/**
 * Representa un pedido que debe ser gestionado y entregado por SpeedFast.
 */
public class Pedido {

    private int id;
    private String direccionEntrega;
    private EstadoPedido estado;

    /**
     * Construye un nuevo pedido.
     *
     * @param id identificador único del pedido
     * @param direccionEntrega dirección donde debe entregarse el pedido
     * @param estado estado inicial del pedido
     */
    public Pedido(
            int id,
            String direccionEntrega,
            EstadoPedido estado
    ) {
        this.id = id;
        this.direccionEntrega = direccionEntrega;
        this.estado = estado;
    }

    /**
     * Obtiene el identificador del pedido.
     *
     * @return identificador del pedido
     */
    public int getId() {
        return id;
    }

    /**
     * Modifica el identificador del pedido.
     *
     * @param id nuevo identificador
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la dirección de entrega.
     *
     * @return dirección de entrega
     */
    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    /**
     * Modifica la dirección de entrega.
     *
     * @param direccionEntrega nueva dirección de entrega
     */
    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    /**
     * Obtiene el estado actual del pedido.
     *
     * @return estado actual del pedido
     */
    public EstadoPedido getEstado() {
        return estado;
    }

    /**
     * Actualiza el estado del pedido utilizando un enum.
     *
     * @param nuevoEstado nuevo estado del pedido
     * @throws IllegalArgumentException si el estado es nulo
     */
    public void setEstado(EstadoPedido nuevoEstado) {
        if (nuevoEstado == null) {
            throw new IllegalArgumentException(
                    "El estado no puede ser nulo."
            );
        }

        this.estado = nuevoEstado;
    }

    /**
     * Actualiza el estado del pedido utilizando texto.
     *
     * @param nuevoEstado nombre del nuevo estado
     * @throws IllegalArgumentException si el estado no es válido
     */
    public void setEstado(String nuevoEstado) {
        if (nuevoEstado == null || nuevoEstado.isBlank()) {
            throw new IllegalArgumentException(
                    "El estado no puede estar vacío."
            );
        }

        try {
            setEstado(
                    EstadoPedido.valueOf(
                            nuevoEstado.trim().toUpperCase()
                    )
            );
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Estado no válido: " + nuevoEstado,
                    e
            );
        }
    }

    /**
     * Devuelve una representación textual del pedido.
     *
     * @return información del pedido
     */
    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", direccionEntrega='" + direccionEntrega + '\'' +
                ", estado=" + estado +
                '}';
    }
}