/*
    Interface que define nuestro servicio de guardarropa
    Fecha de creacion: 19/7/2017
    Fecha de ultima modificaión: 19/7/2017
    Autor: Cesar Navarro
 */
package Servicios;

public interface Guardarropa {
    
    public String registrarArticulo(String codigoBoleto);
    public String retirarArticulo(String numeroRegistro);
    
}
