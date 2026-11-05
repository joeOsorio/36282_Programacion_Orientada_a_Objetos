# Practica 8

Haz por lo menos 3 clases para el siguiente problema.

Haz un programa donde simulemos la mecánica principal de los juegos de cartas (revolver cartas, repartir cartas, eliminar cartas de mano), para este programa imitaremos como si estuviéramos jugando go fish pero solo las mecánicas principal (sin reglas)

para usar random en java

```

import java.util.Random;

public class Main {
public static void main(String[] args) {
Random random = new Random();

        // 1. Random integer across the entire int range (-2B to +2B)
        int anyInt = random.nextInt();

        // 2. Random integer from 0 (inclusive) to 10 (exclusive) -> Range: 0 to 9
        int boundedInt = random.nextInt(10);

        // 3. Random integer within a specific range (e.g., 5 to 15)
        // Formula: random.nextInt(max - min + 1) + min
        int rangeInt = random.nextInt(15 - 5 + 1) + 5;

        // 4. Other data types
        double randDouble = random.nextDouble(); // Between 0.0 and 1.0
        boolean randBoolean = random.nextBoolean(); // true or false
    }

}
```

1.- haz una clase que funcione como las cartas

2.- haz una clase. que funcione como la mano

3.- haz una clase que funcione como el dealer o el que lleva el control del juego (no es el main)

4.- haz el main.

Las cartas son el estándar de A, 2, 3, 4, 5, 6, 7, 8, 9, J, Q K, de 4 tipos trébol, espadas, diamantes, corazón, al instanciarse deberán de tomar un valor que no este en el mazo de los posibles valores.

La mano contiene mínimo 5 cartas al inicio, pero puede crecer hasta el máximo de cartas (48), la mano tiene la habilidad de agarrar cartas (las agrega a la mano), de tirar carta (las elimina de la mano), transferir cartas (la pasa de una mano a otra de otro jugador, puede ser 1 o muchas), también puede mostrar cartas al dealer/juego

El dealer es el que lleva el control del juego, revuelve el mazo, reparte las cartas a los jugadores (5 al inicio), dictamina a quien le toca (los turnos), dictamina quien gana el juego, etc.

para este programa, solo se quiere probar unas partes del juego go fish

- revolver mazo
- repartir cartas a las manos
- agarrar cartas del mazo a la mano
- pasar cartas de una mano a otra (de un jugador a otro)
- mostrar mano a dealer
- contar cuantas cartas tiene del mismo valor ( A de trébol con A de espadas con A de diamantes con A de corazones)

Sube tu códigos java
