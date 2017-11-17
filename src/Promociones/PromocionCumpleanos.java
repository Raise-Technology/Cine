/*
    En esta clase se definen los metodos para aplicar una promocion a un cumpleañero
    Fecha de creacion: 19/7/2017
    Fecha de ultima modificaión: 19/7/2017
    Autor: Cesar Navarro
 */
package Promociones;

import javax.swing.JOptionPane;


public class PromocionCumpleanos extends Promocion{
    private int numeroClientes;
    
    public PromocionCumpleanos()
    {
        nombre = "Promocion Cumpleaños";
        tipoTarjeta = "Azul";
        numeroClientes = 10;
    }
    
    @Override
    public boolean validarTarjeta(String tipoTarjeta) {
        boolean validado = false;
        if(tipoTarjeta.equals("Azul")||tipoTarjeta.equals("Dorada")||tipoTarjeta.equals("Negra"))
        {
            validado = true;
        }
        return validado;
    }

    @Override
    public boolean aplicarRestricciones() {
        boolean verificado = false;
        if(numeroClientes>=1)
        {
            numeroClientes--;
            verificado = true;
        }
        return verificado;
    }

    @Override
    public double aplicarPromocion() {
        double descuento = 0.7;
        JOptionPane.showMessageDialog(null, "Se aplico la promocion "+nombre+" con un descuento de 30%");
        return descuento;
    }
    
}
