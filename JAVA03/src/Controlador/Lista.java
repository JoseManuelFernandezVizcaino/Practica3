
package Controlador;

import Modelo.*;

public class Lista <T> {
    
    private static Nodo inicio;
    private Nodo actual;
    
    public static Nodo getInicio() {
        return inicio;
    }

    public static void setInicio(Nodo aInicio) {
        inicio = aInicio;
    }

    public Nodo getActual() {
        return actual;
    }

    public void setActual(Nodo actual) {
        this.actual = actual;
    }
    
    public Lista(){
        inicio = null;
        this.actual = null;
    }
    
    public void insertar(T nd){
        Nodo n = new Nodo(nd);
        
        if(getInicio() == null){
            setInicio(n);
        }
        else{
            Nodo aux = getInicio();
            while(aux.getSiguiente() != null){
                aux = aux.getSiguiente();
            }
            n.setAnterior(aux);
            aux.setSiguiente(n);
        }
    }
}
