package tema5.implementacion;

public class ABBString extends ABB<String> {
    public int palabrasQueEmpiezan(String prefijo){
        return palabrasQueEmpiezan(this.raiz, prefijo);
    }

    protected int palabrasQueEmpiezan(NodoABB<String> actual, String prefijo){
        if (actual == null)
            return 0;
        if(actual.dato.startsWith(prefijo)){
            return 1 + palabrasQueEmpiezan(actual.izq, prefijo)
                    + palabrasQueEmpiezan(actual.der, prefijo);
        } else {
            if(prefijo.compareTo(actual.dato) < 0){
                return palabrasQueEmpiezan(actual.izq, prefijo);
            } else {
                return palabrasQueEmpiezan(actual.der, prefijo);
            }
        }
    }
}