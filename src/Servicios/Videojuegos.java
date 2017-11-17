/*
    Interface que define el servicio de videojuegos de nuestra cadena de cines
    Fecha de creacion: 19/7/2017
    Fecha de ultima modificaión: 19/7/2017
    Autor: Cesar Navarro
 */
package Servicios;

public interface Videojuegos {
    
    public String venderTarjetaVideoJuegos(String numeroTarjetaSocio);
    public void recargarTarjetaVideoJuegos(double dinero, String numeroTarjetaVideojuegos);
    
}
