package tema5.modelos;

import tema5.implementacion.EntradaMap;
import tema3.modelos.Map;

public interface MapOrdenado<C extends Comparable<C>, V> extends Map<C, V> {

    EntradaMap<C, V> recuperarEntradaMin(); // Entrada de clave mínima
    C recuperarMin(); // Clave mínima

    EntradaMap<C, V> recuperarEntradaMax(); // Entrada de clave máxima
    C recuperarMax(); // Clave máxima

    EntradaMap<C, V> sucesorEntrada(C c); // Siguiente Entrada a c en orden
    C sucesor(C c); // Siguiente clave a c en el orden

    EntradaMap<C, V> predecesorEntrada(C c); // Entrada anterior a c en orden
    C predecesor(C c); // Clave anterior a c en el orden
}
