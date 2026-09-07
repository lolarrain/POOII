# SpeedFast S4

Proyecto desarrollado en Java que representa un sistema de gestión de pedidos para una empresa de reparto a domicilio.

Esta versión reutiliza la estructura orientada a objetos desarrollada previamente e incorpora programación concurrente, permitiendo simular el trabajo simultáneo de varios repartidores. Cada repartidor funciona como una tarea independiente, procesa secuencialmente los pedidos que tiene asignados y simula el tiempo de entrega mediante pausas aleatorias.

## Conceptos aplicados

- Abstracción
- Encapsulamiento
- Herencia
- Polimorfismo
- Sobrescritura de métodos
- Sobrecarga de métodos
- Interfaces
- Colecciones genéricas
- Programación concurrente
- `Runnable`
- `Thread.sleep()`
- `ExecutorService`
- Manejo de interrupciones
- Desacoplamiento de responsabilidades

La clase abstracta `Pedido` contiene los atributos y comportamientos comunes a todos los pedidos, incluyendo `idPedido`, `direccionEntrega`, `distanciaKm`, `estado` y `repartidorAsignado`.

Las clases `PedidoComida`, `PedidoEncomienda` y `PedidoExpress` heredan de `Pedido` e implementan sus propias reglas para calcular el tiempo estimado de entrega y asignar automáticamente un tipo de repartidor.

La clase `ControladorDeEnvios` gestiona las operaciones funcionales de los pedidos mediante las interfaces `Despachable`, `Cancelable` y `Rastreable`, permitiendo reservar, despachar, cancelar y consultar el historial de un envío.

# SpeedFast S4

Proyecto desarrollado en Java para simular la gestión concurrente de pedidos y repartidores.

Esta versión reutiliza la estructura de la semana anterior e incorpora concurrencia mediante `Runnable`, `Thread.sleep()` y `ExecutorService`.

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