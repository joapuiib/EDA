package tema3.modelos;


import tema1.modelos.ListaConPI;

public interface Map<C, V> {
    // Añade la entrada (c,v) y devuelve el antiguo valor que
    // tenía dicha clave (o null si no tenía ningún valor asociado)
    V insertar(C c, V v);

    // Elimina la entrada con clave c y devuelve su valor asociado
    // (o null si no hay ninguna entrada con dicha clave)
    V eliminar(C c);

    // Busca la clave c y devuelve su informacion asociada
    // o null si no hay una entrada con dicha clave
    V recuperar(C c);

    // Devuelve true si el Map está vacío
    boolean esVacio();

    // Devuelve el número de entradas que contiene el Map
    int talla();

    // Devuelve una lista con las claves de todas las entradas del Map
    ListaConPI<C> claves();
}