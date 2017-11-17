/*
 *  Esto es un sistema para manejar las entradas de un cine
    Fecha de creacion: 27/5/2017
    Fecha de ultima modificaión: 19/7/2017
    Autor: Cesar Navarro
 */
package Cine;

import Promociones.Promocion;
import Promociones.PromocionCumpleanos;
import Promociones.Promocion2x1;
import Servicios.Guardarropa;
import Servicios.Videojuegos;
import java.io.Serializable;
import javax.swing.JOptionPane;

public class Cine implements Guardarropa, Videojuegos, Serializable{
    //Se declaran las propiedades del programa
    
    
    private String nombreCine;
    private String funcion;
    private int numeroBoletos;
    private double precioBoletos;
    private double pagoBoletos;
    private double precioCombo;
    private String combo;
    private double totalPagar;
    private String miercoles;
    private String funciones[] = new String[]{"Spider Man 4:00 pm", "Mi villano favorito 5:00 pm", "Car 3 6:00 pm"};
    private String combos[] = new String[]{"Combo nachos $200", "Combo HotDog $220", "Combo Crepas $250"};
    private String ticket;

    //Getters y Setters
    public String getNombreCine() {
        return nombreCine;
    }

    public void setNombreCine(String nombreCine) {
        this.nombreCine = nombreCine;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public int getNumeroBoletos() {
        return numeroBoletos;
    }

    public void setNumeroBoletos(int numeroBoletos) {
        this.numeroBoletos = numeroBoletos;
    }

    public double getPrecioBoletos() {
        return precioBoletos;
    }

    public void setPrecioBoletos(double precioBoletos) {
        this.precioBoletos = precioBoletos;
    }

    public double getPagoBoletos() {
        return pagoBoletos;
    }

    public void setPagoBoletos(double pagoBoletos) {
        this.pagoBoletos = pagoBoletos;
    }

    public double getPrecioCombo() {
        return precioCombo;
    }

    public void setPrecioCombo(double precioCombo) {
        this.precioCombo = precioCombo;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public String getMiercoles() {
        return miercoles;
    }

    public void setMiercoles(String miercoles) {
        this.miercoles = miercoles;
    }

    public String[] getFunciones() {
        return funciones;
    }

    public void setFunciones(String[] funciones) {
        this.funciones = funciones;
    }

    public String[] getCombos() {
        return combos;
    }

    public void setCombos(String[] combos) {
        this.combos = combos;
    }
    

    public void setCombo(String combo)
    {
        this.combo = combo;
    }
    
    public String getTicket()
    {
        return ticket;
    }
    
    public void obtenerDatos() {
        nombreCine = JOptionPane.showInputDialog("Ingresa el nombre del cine");
        funcion = JOptionPane.showInputDialog(null,
                "Selecciona la funcion",
                "Funcion",
                JOptionPane.DEFAULT_OPTION,
                null,
                funciones,
                null
        ).toString();
        numeroBoletos = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el numero de boletos"));
        precioBoletos = Double.parseDouble(JOptionPane.showInputDialog("Ingresa el precio del boleto"));
        combo = JOptionPane.showInputDialog(null,
                "Selecciona el combo",
                "Combo",
                JOptionPane.DEFAULT_OPTION,
                null,
                combos,
                null
        ).toString();

    }
    
    public void calcularPagoCombo()
    {
        switch (combo) {
            case "Combo nachos $200":
                precioCombo = 200;
                break;
            case "Combo HotDog $220":
                precioCombo = 220;
                break;
            case "Combo Crepas $250":
                precioCombo = 250;
                break;
            default:
                precioCombo = 0;
        }
    }

    public void calcularPago() {
        
        pagoBoletos = (numeroBoletos * precioBoletos);
        calcularPagoCombo();
        totalPagar = pagoBoletos + precioCombo;

    }
    
    public void calcularPago(Promocion promo)
    {
         if(promo.aplicarRestricciones())
            {
                pagoBoletos = (numeroBoletos * precioBoletos) * promo.aplicarPromocion();
                calcularPagoCombo();
                totalPagar = pagoBoletos + precioCombo;
            }
            else
            {
                calcularPago();
            }        
    }


public void  mostrarDatosVenta()
        {
            //Creamos el ticket con los datos de la venta
       ticket = "---------" + nombreCine + "---------\n"
                + "Funcion: " + funcion + "\n"                
                + "Numero de Boletos: " + numeroBoletos + "\n"
                + "Precio Boleto: " + precioBoletos + "\n"
                + "Combo: " + combo + "\n"                
                + "Total a pagar: " + totalPagar;

            System.out.println(ticket);

            JOptionPane.showMessageDialog(null, ticket);
            
        }
    

    public static void main(String[] args) {
       
        PromocionCumpleanos promoCumpleanos = new PromocionCumpleanos();
        Promocion2x1 promo2x1 = new Promocion2x1();
        Cine cine = new Cine();
        
        cine.obtenerDatos();
        promo2x1.setPuntos(10000);
        cine.calcularPago(promo2x1);
        cine.mostrarDatosVenta();
        String registroArticulo =  cine.registrarArticulo("ABC1234");
        cine.retirarArticulo(registroArticulo);
        String tarjetaVideoJuegos = cine.venderTarjetaVideoJuegos("LKJH3210");
        cine.recargarTarjetaVideoJuegos(500, tarjetaVideoJuegos);

        

    }
    //Servicio de Guardarropa
    @Override
    public String registrarArticulo(String codigoBoleto) {
        String numeroRegistro = codigoBoleto+nombreCine+"19-7-2017-LPR";
        JOptionPane.showMessageDialog(null, "Se ha registrado correctamente un articulo en guardarropa con codigo "+numeroRegistro);
        return numeroRegistro;
    }

    @Override
    public String retirarArticulo(String numeroRegistro) {
        String mensaje = "Se ha retirado el articulo: "+numeroRegistro;
        JOptionPane.showMessageDialog(null, mensaje);
        return mensaje;
    }
    
    //Servicio de videojuegos
    @Override
    public String venderTarjetaVideoJuegos(String numeroTarjetaSocio) {
        String numeroTarjetaVideojuegos = numeroTarjetaSocio+nombreCine+"10001";
        JOptionPane.showMessageDialog(null, "Se ha generado exitosamente la tarjeta con el codigo "+numeroTarjetaVideojuegos);
        return numeroTarjetaVideojuegos;
    }

    @Override
    public void recargarTarjetaVideoJuegos(double dinero, String numeroTarjetaVideojuegos) {
        JOptionPane.showMessageDialog(null, "Se ha agregado correctamente $"+dinero+" a la tarjeta "+numeroTarjetaVideojuegos);
    }

}
