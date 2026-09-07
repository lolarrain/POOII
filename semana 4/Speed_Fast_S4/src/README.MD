# SpeedFast S4

Proyecto desarrollado en Java para simular la gestión concurrente de pedidos y repartidores.

Esta versión reutiliza la estructura de la semana 3 e incorpora concurrencia mediante `Runnable`, `Thread.sleep()` y `ExecutorService`.

## Conceptos aplicados

- Abstracción, herencia y polimorfismo.
- Interfaces (`Despachable`, `Cancelable`, `Rastreable`).
- Colecciones genéricas.
- Programación concurrente.
- `Runnable`.
- `ExecutorService`.

## Funcionamiento

La clase abstracta `Pedido` es reutilizada junto a sus subclases:

- `PedidoComida`
- `PedidoEncomienda`
- `PedidoExpress`

La clase `Repartidor` implementa `Runnable` y procesa secuencialmente los pedidos que tiene asignados.

En `Main` se crean tres repartidores con dos pedidos cada uno. Mediante un `ExecutorService` los tres ejecutan sus rutas de forma concurrente.

También se reutiliza `ControladorDeEnvios` para reservar, despachar, cancelar y consultar el historial de los pedidos.

## Estructura

```text
src
├── app
│   └── Main.java
├── interfaces
│   ├── Cancelable.java
│   ├── Despachable.java
│   └── Rastreable.java
└── model
    ├── ControladorDeEnvios.java
    ├── Pedido.java
    ├── PedidoComida.java
    ├── PedidoEncomienda.java
    ├── PedidoExpress.java
    └── Repartidor.java
```
