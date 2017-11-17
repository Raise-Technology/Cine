/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cine;

import java.io.*;
import Promociones.PromocionCumpleanos;
import Promociones.Promocion2x1;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Cesar Programacion
 */
public class VentanaCine extends javax.swing.JFrame {

    /**
     * Creates new form VentanaCine
     */
    public VentanaCine() {
        initComponents();
        setLocationRelativeTo(null);
        cine = new Cine();
        promoCumple = new PromocionCumpleanos();
        promo2x1 = new Promocion2x1();
        Date hora = new Date();
        SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss");
        lblFecha.setText(formatoHora.format(hora));
    }
    
    public String guardarComo()
    {
        guardarComo.showSaveDialog(this);
        String nombreArchivo = guardarComo.getSelectedFile().getPath();
        JOptionPane.showMessageDialog(this, nombreArchivo);
        return nombreArchivo;
    }
    
    public String abrirDesde()
    {
        guardarComo.showOpenDialog(this);
        String nombreArchivo = guardarComo.getSelectedFile().getPath();
        return nombreArchivo;
    }

    public void guardarTexto() {
        String direccion = "C:\\Users\\Cesar Programacion\\Documents\\NetBeansProjects\\ProyectoCine\\src\\archivoTexto.txt";
        FileWriter escritorDeArchivo = null;
        BufferedWriter escritorMemoriaReservada = null;
        PrintWriter escritorDeTexto = null;

        try {
            escritorDeArchivo = new FileWriter(direccion);
            escritorMemoriaReservada = new BufferedWriter(escritorDeArchivo);
            escritorDeTexto = new PrintWriter(escritorMemoriaReservada);
            escritorDeTexto.println(cine.getTicket());

            escritorDeTexto.flush();
            escritorMemoriaReservada.flush();
            escritorDeArchivo.flush();

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al escribir");
        } finally {
            try {
                if (escritorDeTexto != null) {
                    escritorDeTexto.close();
                }
                if (escritorMemoriaReservada != null) {
                    escritorMemoriaReservada.close();
                }
                if (escritorDeArchivo != null) {
                    escritorDeArchivo.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al cerrar");
            }//catch cerrar escritores
        }//finally
    }

    public void leerTexto() {
        //Para leer desde un archivo de texto necesitamos
        //Primeramente un lector de archivos
        //Segundo un lector para el contenido del archivo
        FileReader lectorDeArchivo = null;
        BufferedReader lectorDeContenido = null;
        String direccion = "C:\\Users\\Cesar Programacion\\Documents\\NetBeansProjects\\ProyectoCine\\src\\archivoTexto.txt";
        //La lectura de archivos de texto se hace linea por linea y no por texto completo
        //Ocuparemos 2 variables 1 para guardar la lectura de la linea
        //La segunda para guardar la lectura de todas las lineas
        String linea;
        String textoDeArchivo = "";

        try {
            lectorDeArchivo = new FileReader(direccion);
            lectorDeContenido = new BufferedReader(lectorDeArchivo);

            while ((linea = lectorDeContenido.readLine()) != null) {
                textoDeArchivo += linea + "\n";
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } finally {
            try {
                if (lectorDeContenido != null) {
                    lectorDeContenido.close();
                }
                if (lectorDeArchivo != null) {
                    lectorDeArchivo.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }   
        }//Finally
        JOptionPane.showMessageDialog(this, textoDeArchivo);
    }

    public void guardarObjeto() {
        //Para guardar un objeto en un archivo. Son necesarias 5 cosas.
        //La primera tener un archivo en el cual guardar.
        //La segunda tener una ruta del archivo.
        String direccion = "C:\\Users\\Cesar Programacion\\Documents\\NetBeansProjects\\ProyectoCine\\src\\archivoObjeto.txt";
        //La tercera son los flujos de salida. Son los encargados de crear los archivos en la pc.
        FileOutputStream creadorDeArchivo = null;
        ObjectOutputStream creadorDeObjeto = null;

        try {
            //Se crea el archivo en la pc
            creadorDeArchivo = new FileOutputStream(direccion);
            //Se prepara al objeto para que pueda recibir archivos dentro de el
            creadorDeObjeto = new ObjectOutputStream(creadorDeArchivo);
            //Se guarda el objeto dentro del archivo
            creadorDeObjeto.writeObject(cine);
            creadorDeObjeto.flush();

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } finally {
            try {
                if (creadorDeArchivo != null) {
                    creadorDeArchivo.close();
                }
                if (creadorDeObjeto != null) {
                    creadorDeObjeto.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }

        }

    }

    public void leerObjeto() {
        //Para leer un objeto se necesitan 4 cosas
        //La primera un archivo del cual leer el objeto
        //La segunda los flujos de lectura de objeto y lectura de archivo
        FileInputStream lectorDeArchivos = null;
        ObjectInputStream lectorDeObjetos = null;
        String direccion = "C:\\Users\\Cesar Programacion\\Documents\\NetBeansProjects\\ProyectoCine\\src\\archivoObjeto.txt";
        //La tercera el lugar en donde voy a guardar el objeto proveniente del archivo
        Cine objetoDeArchivo = new Cine();

        try {

            lectorDeArchivos = new FileInputStream(direccion);
            lectorDeObjetos = new ObjectInputStream(lectorDeArchivos);
            objetoDeArchivo = (Cine) lectorDeObjetos.readObject();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }//catch leer objetos
        finally {
            try {
                if (lectorDeArchivos != null) {
                    lectorDeArchivos.close();
                }
                if (lectorDeObjetos != null) {
                    lectorDeObjetos.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }//catch cerrar lectores
        }//finally
        JOptionPane.showMessageDialog(this, objetoDeArchivo.getTicket());
    }

    public void guardarBytes() {

        //Para guardar son necesarias 3 cosas
        //1 El archivo sobre el cual vamos a guardar la informacion
        
        //Un escritor un flujo de salida
        FileOutputStream fw = null;
        try {
            
                fw = new FileOutputStream(ruta);
                JOptionPane.showMessageDialog(null, "Se ha creado correctamente el archivo");
                byte bytes[] = new byte[cine.getTicket().length()];
                bytes = cine.getTicket().getBytes();
                fw.write(bytes);
                fw.flush();
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }

    public void leerBytes() {
        try {
            FileReader fr = new FileReader(ruta);
            String cad = "";
            int c;
            while ((c = fr.read()) != -1) {
                cad = cad + (char) c;
            }
            JOptionPane.showMessageDialog(null, cad);

        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void leerBytesDos()
    {
        try {
           
            FileInputStream fis = new FileInputStream(ruta);
             
            String cad = "";
            int c;
            while ((c = fis.read()) != -1) {
                System.out.println(c);
                cad = cad + (char) c;
            }
            JOptionPane.showMessageDialog(null, cad);

        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombreCine = new javax.swing.JLabel();
        lblFuncion = new javax.swing.JLabel();
        cbxFunciones = new javax.swing.JComboBox<>();
        lblNumeroBoletos = new javax.swing.JLabel();
        txtNumeroBoletos = new javax.swing.JTextField();
        lblPrecioBoleto = new javax.swing.JLabel();
        txtPrecioBoleto = new javax.swing.JTextField();
        lblCombo = new javax.swing.JLabel();
        cbxCombo = new javax.swing.JComboBox<>();
        btnGenerarTicket = new javax.swing.JButton();
        lblPromo = new javax.swing.JLabel();
        cbxPromo = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lblFecha = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        subMenuObjetos = new javax.swing.JMenu();
        opcionGuardarObjeto = new javax.swing.JMenuItem();
        opcionLeerObjeto = new javax.swing.JMenuItem();
        subMenuBytes = new javax.swing.JMenu();
        opcionGuardarBytes = new javax.swing.JMenuItem();
        opcionLeerBytes = new javax.swing.JMenuItem();
        subMenuTexto = new javax.swing.JMenu();
        opcionGuardarTexto = new javax.swing.JMenuItem();
        opcionLeerTexto = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblNombreCine.setText("CINEMAS TEPIC");

        lblFuncion.setText("Función:");

        cbxFunciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una funcion", "Spider Man 4:00 pm", "Cars 3 5:00 pm", "Mi villano favorito 3 6:00 pm", "Transformers 7:00 pm" }));

        lblNumeroBoletos.setText("Número de Boletos:");

        lblPrecioBoleto.setText("Precio Boleto:");

        txtPrecioBoleto.setEditable(false);
        txtPrecioBoleto.setText("100");
        txtPrecioBoleto.setToolTipText("");

        lblCombo.setText("Combo:");

        cbxCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un combo", "Combo nachos $200", "Combo HotDog $220", "Combo Crepas $250" }));

        btnGenerarTicket.setText("Generar Ticket");
        btnGenerarTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarTicketActionPerformed(evt);
            }
        });

        lblPromo.setText("Promocion:");

        cbxPromo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Una Promocion", "Cumpleanos", "2x1" }));

        jButton1.setText("Guardar Como");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Leer Desde");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lblFecha.setText("jLabel1");

        menuArchivo.setText("Archivo");

        subMenuObjetos.setText("Objetos");

        opcionGuardarObjeto.setText("Guardar");
        opcionGuardarObjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionGuardarObjetoActionPerformed(evt);
            }
        });
        subMenuObjetos.add(opcionGuardarObjeto);

        opcionLeerObjeto.setText("Leer");
        opcionLeerObjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionLeerObjetoActionPerformed(evt);
            }
        });
        subMenuObjetos.add(opcionLeerObjeto);

        menuArchivo.add(subMenuObjetos);

        subMenuBytes.setText("Bytes");

        opcionGuardarBytes.setText("Guardar");
        opcionGuardarBytes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionGuardarBytesActionPerformed(evt);
            }
        });
        subMenuBytes.add(opcionGuardarBytes);

        opcionLeerBytes.setText("Leer");
        opcionLeerBytes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionLeerBytesActionPerformed(evt);
            }
        });
        subMenuBytes.add(opcionLeerBytes);

        menuArchivo.add(subMenuBytes);

        subMenuTexto.setText("Texto");

        opcionGuardarTexto.setText("Guardar");
        opcionGuardarTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionGuardarTextoActionPerformed(evt);
            }
        });
        subMenuTexto.add(opcionGuardarTexto);

        opcionLeerTexto.setText("Leer");
        opcionLeerTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionLeerTextoActionPerformed(evt);
            }
        });
        subMenuTexto.add(opcionLeerTexto);

        menuArchivo.add(subMenuTexto);

        jMenuBar1.add(menuArchivo);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblNombreCine)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblFuncion)
                                    .addGap(18, 18, 18)
                                    .addComponent(cbxFunciones, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNumeroBoletos)
                                    .addComponent(lblPrecioBoleto))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNumeroBoletos)
                                    .addComponent(txtPrecioBoleto)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblPromo)
                                    .addComponent(lblCombo))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxPromo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2)
                            .addComponent(lblFecha)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGenerarTicket)
                        .addGap(60, 60, 60)
                        .addComponent(jButton1)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNombreCine)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFuncion)
                            .addComponent(cbxFunciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblFecha)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroBoletos)
                    .addComponent(txtNumeroBoletos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecioBoleto)
                    .addComponent(txtPrecioBoleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCombo)
                            .addComponent(cbxCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPromo)
                            .addComponent(cbxPromo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGenerarTicket)
                            .addComponent(jButton1))
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(90, 90, 90))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarTicketActionPerformed
        cine.setNombreCine(lblNombreCine.getText());
        cine.setNumeroBoletos(Integer.parseInt(txtNumeroBoletos.getText()));
        cine.setFuncion(String.valueOf(cbxFunciones.getSelectedItem()));
        cine.setCombo(String.valueOf(cbxCombo.getSelectedItem()));
        cine.setPrecioBoletos(Integer.parseInt(txtPrecioBoleto.getText()));

        if (cbxPromo.getSelectedIndex() == 1) {
            cine.calcularPago(promoCumple);
        }
        if (cbxPromo.getSelectedIndex() == 2) {
            try {
                promo2x1.setPuntos(Integer.parseInt(JOptionPane.showInputDialog("Ingresa los puntos de la tarjeta")));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
                return;
            }
            if (promo2x1.validarTarjeta(JOptionPane.showInputDialog("Escriba el color de la tarjeta"))) {
                cine.calcularPago(promo2x1);
            }
        }
        if (cbxPromo.getSelectedIndex() == 0) {
            cine.calcularPago();
        }

        //cine.calcularPago();
        cine.mostrarDatosVenta();
        guardarBytes();

    }//GEN-LAST:event_btnGenerarTicketActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        guardarComo();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void opcionGuardarObjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionGuardarObjetoActionPerformed
        guardarObjeto();
    }//GEN-LAST:event_opcionGuardarObjetoActionPerformed

    private void opcionLeerObjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionLeerObjetoActionPerformed
        leerObjeto();
    }//GEN-LAST:event_opcionLeerObjetoActionPerformed

    private void opcionGuardarTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionGuardarTextoActionPerformed
        guardarTexto();
    }//GEN-LAST:event_opcionGuardarTextoActionPerformed

    private void opcionLeerTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionLeerTextoActionPerformed
        leerTexto();
    }//GEN-LAST:event_opcionLeerTextoActionPerformed

    private void opcionGuardarBytesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionGuardarBytesActionPerformed
        guardarBytes();
    }//GEN-LAST:event_opcionGuardarBytesActionPerformed

    private void opcionLeerBytesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionLeerBytesActionPerformed
        leerBytesDos();                
    }//GEN-LAST:event_opcionLeerBytesActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        abrirDesde();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaCine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaCine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaCine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaCine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaCine().setVisible(true);
            }
        });
    }
    
    JFileChooser guardarComo = new javax.swing.JFileChooser();
    PromocionCumpleanos promoCumple;
    Promocion2x1 promo2x1;
    Cine cine;
    String ruta = "C:\\Users\\IBM_ADMIN\\Documents\\NetBeansProjects\\ProyectoCine\\src\\archivo.txt";
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarTicket;
    private javax.swing.JComboBox<String> cbxCombo;
    private javax.swing.JComboBox<String> cbxFunciones;
    private javax.swing.JComboBox<String> cbxPromo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblCombo;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFuncion;
    private javax.swing.JLabel lblNombreCine;
    private javax.swing.JLabel lblNumeroBoletos;
    private javax.swing.JLabel lblPrecioBoleto;
    private javax.swing.JLabel lblPromo;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenuItem opcionGuardarBytes;
    private javax.swing.JMenuItem opcionGuardarObjeto;
    private javax.swing.JMenuItem opcionGuardarTexto;
    private javax.swing.JMenuItem opcionLeerBytes;
    private javax.swing.JMenuItem opcionLeerObjeto;
    private javax.swing.JMenuItem opcionLeerTexto;
    private javax.swing.JMenu subMenuBytes;
    private javax.swing.JMenu subMenuObjetos;
    private javax.swing.JMenu subMenuTexto;
    private javax.swing.JTextField txtNumeroBoletos;
    private javax.swing.JTextField txtPrecioBoleto;
    // End of variables declaration//GEN-END:variables
}
