package lists;

import java.io.Serial;
import java.io.Serializable;

public class Nodo<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Nodo<T> siguienteNodo;
    private T valorNodo;


    /**
     * Constructor de la clase Listas.Nodo
     * dato Elemento que se guarda en el Listas.Nodo
     */
    public Nodo(T valorNodo) {
        this.valorNodo = valorNodo;
    }


    /**
     * Constructor de la clase Listas.Nodo
     * @param dato Elemento que se guarda en el Listas.Nodo
     * @param siguiente Enlace al siguiente Listas.Nodo
     */
    public Nodo(T dato, Nodo<T> siguiente) {
        super();
        this.valorNodo = dato;
        this.siguienteNodo = siguiente;
    }


    //Metodos get y set de la clase Listas.Nodo

    public Nodo<T> getSiguienteNodo() {
        return siguienteNodo;
    }


    public void setSiguienteNodo(Nodo<T> siguienteNodo) {
        this.siguienteNodo = siguienteNodo;
    }


    public T getNodeValue() {
        return valorNodo;
    }


    public void setValorNodo(T valorNodo) {
        this.valorNodo = valorNodo;
    }
}
