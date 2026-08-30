# SpeedFast S3

Proyecto desarrollado en Java que representa un sistema de gestión de pedidos para una empresa de reparto a domicilio.

Esta versión integra abstracción, polimorfismo e interfaces, permitiendo gestionar distintos tipos de pedidos, asignar repartidores, calcular tiempos de entrega y realizar operaciones como reservar, despachar, cancelar y consultar el historial de un envío.

## Conceptos aplicados

-Abstracción
-Encapsulamiento
-Herencia
-Polimorfismo
-Sobrescritura de métodos
-Sobrecarga de métodos
-Interfaces
-Colecciones genéricas
-Desacoplamiento de responsabilidades

La clase abstracta `Pedido` contiene los atributos y comportamientos comunes a todos los pedidos.

Las clases `PedidoComida`, `PedidoEncomienda` y `PedidoExpress` heredan de Pedido e implementan sus propias reglas para:

-Asignar automáticamente un repartidor.

-Calcular el tiempo estimado de entrega.

El método `asignarRepartidor()` es sobrescrito por cada subclase, mientras que `asignarRepartidor(String nombre)` permite realizar una asignación manual mediante sobrecarga.

La clase `ControladorDeEnvios` gestiona las operaciones funcionales de los pedidos mediante las interfaces `Despachable`, `Cancelable` y `Rastreable`.

## Estructura del proyecto

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
    └── PedidoExpress.java
```

## Escalabilidad, reutilización y mantenibilidad

La incorporación de una clase abstracta, polimorfismo e interfaces permite que el sistema pueda crecer sin modificar su estructura principal. Nuevos tipos de pedido pueden heredar de Pedido e implementar sus propias reglas, reutilizando los atributos y métodos comunes ya definidos.

Además, las interfaces separan responsabilidades como despacho, cancelación y rastreo, evitando concentrar toda la lógica en una sola clase. Esto facilita modificar, ampliar o reutilizar funcionalidades específicas sin afectar innecesariamente al resto del sistema.
