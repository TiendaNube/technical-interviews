# Autorent

La empresa de alquiler de autos​ "Autorent" ​requiere de nuestros servicios para administrar mejor su negocio. El objetivo es diseñar un sistema que calcule las tarifas de alquiler de los autos de la empresa.

Cada vez que un auto es devuelto por un cliente, se realizan las siguientes verificaciones:
 * Nivel de combustible, deberá estar lleno, caso contrario se cobra una multa en función del nivel con el que se devolvió el mismo.
 * Kilometraje, se coteja la cantidad de km que tenía el auto antes del alquiler y luego del mismo, y se verifica que esté dentro del rango acordado. Si se supera el mismo se cobra un multa por cada uno de los km sobrepasados. El kilometraje por día permitido es de 400 kilómetros diarios para todos los modelos. 
 * Fecha de devolución, el precio de alquiler se coteja por día. Si se alquiló a las 10 h del día 12/04/2015, se deberá devolver a lo sumo a las 10 h del día 13/04/2015, caso contrario se cobrará un dia mas de alquiler.

Los Sábados y Domingos el día de alquiler tiene un recargo del 15% sobre el costo normal.

"Autorent" nos ha facilitado las siguientes tablas de datos:

| Modelo         | Alquiler ($/dia) | $/km excedido | Capacidad de tanque (l) |
| -------------- | ---------------: | ------------: | ----------------------: |
| Renault Clio   | 400              | 10            | 45                      |
| Ford Ka        | 430              | 10            | 40                      |
| Renault Logan  | 500              | 12            | 50                      |
| Renault Duster | 700              | 15            | 70                      | 

La tabla siguiente indica las multas de aquellos tanques que no estén llenos:

| Nivel del tanque | Multa ($) |
| ---------------- | --------: |
| Vacio - 1/4      |       400 |
| 1/4 - 1/2        |       300 |
| 1/2 - 3/4        |       200 |
| 3/4 en adelante  |       100 |

## User stories

 * Como cliente al retornar un auto en tiempo y forma sólo deberé pagar el precio normal de ese modelo multiplicado por los días que lo alquilé.
 * Como cliente al retornar un auto 3 h más tarde de lo estipulado deberá pagar un día de más.
 * Como cliente al retornar un auto con menos de 1/4 de tanque deberé pagar la multa adecuada.
 * Como cliente al retornar un auto con más kilómetros de los permitidos por día deberé pagar una multa adecuada por cada kilómetro excedido.
 * Como cliente al retornar un auto con menos de 1/4 de tanque y 3 h más tarde deberé pagar la multa adecuada.


## Run tests

`python -m unittest test/autorent_acceptance_test.py`
