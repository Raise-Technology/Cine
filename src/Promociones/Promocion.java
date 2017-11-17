/*
    Esta es la clase base para definir las promociones de nuestro cine
    Fecha de creacion: 19/7/2017
    Fecha de ultima modificaión: 19/7/2017
    Autor: Cesar Navarro
 */
package Promociones;

public abstract class Promocion {
    
    protected String nombre;
    protected String tipoTarjeta;
    
    public abstract boolean validarTarjeta(String tipoTarjeta);
    public abstract boolean aplicarRestricciones();
    public abstract double aplicarPromocion();   
    
}
