# SpeedFast S5

Este proyecto simula el funcionamiento de una empresa de reparto llamada SpeedFast. Varios repartidores trabajan simultáneamente y retiran pedidos desde una zona de carga compartida.

El objetivo principal es aplicar conceptos de Programación Orientada a Objetos y mecanismos de sincronización para garantizar que cada pedido sea retirado y entregado por un único repartidor.

## Conceptos aplicados

- **Encapsulamiento:** los atributos son privados y se gestionan mediante métodos públicos.
- **Abstracción:** cada clase concentra una responsabilidad específica del sistema.
- **Interfaces:** `Repartidor` implementa `Runnable` para ejecutarse mediante hilos.
- **Enumeraciones:** `EstadoPedido` restringe los estados válidos de un pedido.
- **Concurrencia:** varios repartidores ejecutan sus tareas en hilos independientes.
- **Sincronización:** los métodos de `ZonaDeCarga` utilizan `synchronized` para impedir que dos repartidores retiren el mismo pedido.

## Funcionamiento

Los pedidos comienzan en estado `PENDIENTE`. Cada repartidor solicita un pedido a la zona de carga. El método sincronizado lo marca como `EN_REPARTO` y lo retira de la lista, impidiendo que otro hilo pueda tomarlo.

Después de simular el tiempo de entrega, el pedido cambia a estado `ENTREGADO`. Finalmente, el programa verifica que todos los pedidos hayan sido entregados correctamente.



## Estructura 

La solución está organizada en cinco componentes principales: un `enum`, tres clases de dominio y una clase principal de ejecución.

```text
src
├── app
│   └── Main.java
└── model
    ├── EstadoPedido.java
    ├── Pedido.java
    ├── Repartidor.java
    └── ZonaDeCarga.java
```