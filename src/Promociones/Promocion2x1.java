/*
    En esta clase se define la promocion 2x1 de nuestro cine
    Fecha de creacion: 19/7/2017
    Fecha de ultima modificaión: 19/7/2017
    Autor: Cesar Navarro
 */
package Promociones;

import javax.swing.JOptionPane;

public class Promocion2x1 extends Promocion{
    private int puntos;
    
    public Promocion2x1()
    {
        nombre = "Promocion Limitada 2x1";
        tipoTarjeta = "Negra";        
    }
    
    public void setPuntos(int puntos)
    {
        this.puntos = puntos;
    }
    
    @Override
    public boolean validarTarjeta(String tipoTarjeta) {
        boolean validado = false;
        if(tipoTarjeta.equals("Negra"))
        {
            validado = true;
        }
        return validado;
    }

    @Override
    public boolean aplicarRestricciones() {
        boolean verificado = false;
        if(puntos >= 5000)
        {
            verificado = true;
        }
        return verificado;
    }

    @Override
    public double aplicarPromocion() {
        double descuento = 0.5;
        JOptionPane.showMessageDialog(null, "La se aplico promocion "+nombre+" con un descuento de 50%");
        return descuento;
    } 
    
}
