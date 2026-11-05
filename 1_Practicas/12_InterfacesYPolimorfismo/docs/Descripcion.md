# Laboratorio 12: interfaces y polimorfismo

Sube tu código java

Las reseñas de productos se publican solo si pasan varios filtros automáticos. Después, los productos se ordenan por calificación.
Un producto con una sola reseña de 5 estrellas no debería quedar por encima de uno con 20 reseñas que promedian 4.6 en estos casos hay que poner un limite de reseñas para que los tome en cuenta (como filtro).

Define las siguientes clases e interfaces:
Clase Reseña: contiene un identificador único ejemplo VX@123, para referirse al producto que esta haciendo reseña, el usuario que dijo la reseña, la calificación (de 1 a 5 estrellas ) verificada al crearse de que no se pase de 5, la reseña en forma de texto y si la compra esta verificada.
Enum Veredicto, contiene valores de { APROBADA, REVISION_MANUAL, RECHAZADA }.
Interfaz Filtro, con métodos de getNombre():String y revisar(Reseña): Veredicto
Clase FiltroGroserias este filtro si encuentra en el texto alguna palabra de una lista de palabras groseras, en el caso de que encuentre veredicto->RECHAZADA
Clase FiltroLongitud este filtro verifica si el texto contiene con un mínimo de 5 palabras, de lo contrario veredicto->RECHAZADA y un máximo de 255 palabras.
Clase FiltroSpam este filtro revisa que si el texto contiene más del 65% en mayusculas lo considere spam, por lo que el veredicto->REVISION_MANUAL
clase Moderador esta clase ejecuta todos los filtros que se le desea aplicar a las reseñas en un método llamado moderar(Reseña): veredicto aparte se encarga de decir cual de los filtros fue el más severo y lo registra como el filtro que hizo que se rechazará la reseña. Recibe un arreglo de filtros
intefaz CalcularCalificacion esta interfaz se encarga de promediar la calificación de las reseñas aprobadas con un método calcular(Reseña[] aprobadas, SKU item) : double
clase PromedioSimple, Sumatoria de calificación entre cantidad de reseñas : ∑ c/n
clase PromedioBayesiano, Sumatoria de calificación * confianza de reseña (un numero float de 0 a 1) entre la cantidad de reseñas : ∑ (c*p)/n, C es más cercano a 1.0 entre más palabras tenga la reseña
Haz un programa donde califiques 3 productos
producto 1, con 3 reseña (5 estrellas), se rechazan 2
producto 2, con 10 reseñas ( promedio de 3.8 estrellas) , se rechazan 5
producto 3, con 13 reseñas (promedio de 4 estrellas), se rechazan 3
mostrar que filtros se ejecutaron y cual fue el filtro que hizo que se rechazará la reseña
después calcular la puntuación de estrellas
y finalmente mostrar los productos en orden descendente de estrellas según las reseñas. (mostrar ambos promedios)
