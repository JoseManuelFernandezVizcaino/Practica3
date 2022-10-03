package Vista;

import Modelo.*;
import Controlador.*;
import Vista.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Frame extends javax.swing.JFrame {

    public Frame() {
        
        Lista <Cuenta> lista = new Lista();
        
        lista.insertar(new Cuenta(5000,"Jose Manuel",10, 02, 2022));
        lista.insertar(new Cuenta(10000,"Rosa",16, 05, 2012));
        lista.insertar(new Cuenta(100,"Felipe",26, 06, 2022));
        lista.insertar(new Cuenta(1000,"Julio",19, 01, 2018));
        lista.insertar(new Cuenta(300,"Marcos",21, 10, 2020));
        
        lista.setActual(lista.getInicio());
        
        Cuenta cuentaActual = (Cuenta) lista.getActual().getDato();
        
        initComponents();
        
        numCuenta.setText(""+cuentaActual.getNumero());
        diaF.setText("" + cuentaActual.getFecha().get(Calendar.DATE));
        mesF.setText("" + cuentaActual.getFecha().get(Calendar.MONTH));
        anioF.setText("" + cuentaActual.getFecha().get(Calendar.YEAR));
        saldoCuenta.setText(""+cuentaActual.getSaldo());
        propCuenta.setText(""+cuentaActual.getPropietario());
        
        botonAceptar.setVisible(false);
        botonCancelar.setVisible(false);
        
        botonAnterior.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                if(lista.getActual().getAnterior() != null){
                    Cuenta C = (Cuenta) lista.getActual().getAnterior().getDato();
                    lista.setActual(lista.getActual().getAnterior());
                    
                    numCuenta.setText("" + C.getNumero());
                    diaF.setText("" + C.getFecha().get(Calendar.DATE));
                    mesF.setText("" + C.getFecha().get(Calendar.MONTH));
                    anioF.setText("" + C.getFecha().get(Calendar.YEAR));
                    saldoCuenta.setText("" + C.getSaldo());
                    propCuenta.setText("" + C.getPropietario());
                }

            }
        });
        
        botonSiguiente.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                if(lista.getActual().getSiguiente() != null){
                    Cuenta C = (Cuenta) lista.getActual().getSiguiente().getDato();
                    lista.setActual(lista.getActual().getSiguiente());
                    
                    numCuenta.setText("" + C.getNumero());
                    diaF.setText(""+C.getFecha().get(Calendar.DATE));
                    mesF.setText(""+C.getFecha().get(Calendar.MONTH));
                    anioF.setText(""+C.getFecha().get(Calendar.YEAR));
                    saldoCuenta.setText("" + C.getSaldo());
                    propCuenta.setText("" + C.getPropietario());
                }

            }
        });
        
        botonCrear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                GregorianCalendar fechaActual = new GregorianCalendar(new Locale("es", "ES"));
                numCuenta.setText("Autonumerico");
                numCuenta.setBackground(Color.GRAY);
                diaF.setText("dd");
                mesF.setText("mm");
                anioF.setText("yyyy");
                saldoCuenta.setText("");
                propCuenta.setText("");
                
                propCuenta.setEditable(true);
                saldoCuenta.setEditable(true);
                
                botonCrear.setVisible(false);
                botonAnterior.setVisible(false);
                botonSiguiente.setVisible(false);
                botonAceptar.setVisible(true);
                botonCancelar.setVisible(true);
                
                botonAceptar.addActionListener(new ActionListener(){
                     public void actionPerformed(ActionEvent e){
                        GregorianCalendar fecha = new GregorianCalendar(new Locale("es", "ES"));
                        String dia = diaF.getText();
                        String mes = mesF.getText();
                        String anio = anioF.getText();
                        String propietario = propCuenta.getText();
                        float saldo = Float.parseFloat(saldoCuenta.getText());
                        //GregorianCalendar fecha = new GregorianCalendar(new Locale("es", "ES"));
                        
                        if(dia.equalsIgnoreCase("dd") || dia.equalsIgnoreCase("") || mes.equalsIgnoreCase("mm") || mes.equalsIgnoreCase("")
                                || anio.equalsIgnoreCase("yyyy") || anio.equalsIgnoreCase("")){
                            lista.insertar(new Cuenta(saldo, propietario ,fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), fecha.get(Calendar.DATE)));
                        }
                        else
                        {
                            int dia1 = Integer.parseInt(dia);
                            int mes1 = Integer.parseInt(mes);
                            int anio1 = Integer.parseInt(anio);
                            lista.insertar(new Cuenta(saldo, propietario, anio1, mes1, dia1));
                        }
                        
                        propCuenta.setEditable(false);
                        saldoCuenta.setEditable(false);
                        diaF.setEditable(false);
                        mesF.setEditable(false);
                        anioF.setEditable(false);
                        numCuenta.setBackground(null);
                        
                        botonSiguiente.setVisible(true);
                        botonAnterior.setVisible(true);
                        botonAceptar.setVisible(false);
                        botonCancelar.setVisible(false);
                        botonCrear.setVisible(true);
                        
                        Cuenta C = (Cuenta) lista.getActual().getDato();
                        refrescarCuenta(C);
                    }
                });
                
                botonCancelar.addActionListener(new ActionListener(){
                     public void actionPerformed(ActionEvent e){
                        botonSiguiente.setVisible(true);
                        botonAnterior.setVisible(true);
                        botonAceptar.setVisible(false);
                        botonCancelar.setVisible(false);
                        botonCrear.setVisible(true);
                        
                        numCuenta.setBackground(null);
                        
                        Cuenta C = (Cuenta) lista.getActual().getDato();
                        refrescarCuenta(C);
                    }
                });
            }
        });
    }
    
    public void refrescarCuenta(Cuenta cuenta){
        numCuenta.setText(""+cuenta.getNumero());
        diaF.setText(""+cuenta.getFecha().get(Calendar.DATE));
        mesF.setText(""+cuenta.getFecha().get(Calendar.MONTH));
        anioF.setText(""+cuenta.getFecha().get(Calendar.YEAR));
        saldoCuenta.setText(""+cuenta.getSaldo());
        propCuenta.setText(""+cuenta.getPropietario());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        numCuenta = new javax.swing.JTextField();
        propCuenta = new javax.swing.JTextField();
        diaF = new javax.swing.JTextField();
        mesF = new javax.swing.JTextField();
        anioF = new javax.swing.JTextField();
        saldoCuenta = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        botonAceptar = new javax.swing.JButton();
        botonCrear = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        botonAnterior = new javax.swing.JButton();
        botonSiguiente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Numero Cuenta");

        jLabel2.setText("Propietario");

        jLabel3.setText("Fecha");

        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 46, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(45, 45, 45)
                .addComponent(jLabel3)
                .addGap(30, 30, 30)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        numCuenta.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(saldoCuenta)
                    .addComponent(numCuenta)
                    .addComponent(propCuenta)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(diaF, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mesF, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(anioF)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(numCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(propCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(diaF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mesF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(anioF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(saldoCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
        );

        botonAceptar.setText("Aceptar");

        botonCrear.setText("Crear Cuenta");

        botonCancelar.setText("Cancelar");

        botonAnterior.setText("Anterior");

        botonSiguiente.setText("Siguiente");
        botonSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSiguienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSiguienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonSiguienteActionPerformed

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
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField anioF;
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonAnterior;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonCrear;
    private javax.swing.JButton botonSiguiente;
    private javax.swing.JTextField diaF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField mesF;
    private javax.swing.JTextField numCuenta;
    private javax.swing.JTextField propCuenta;
    private javax.swing.JTextField saldoCuenta;
    // End of variables declaration//GEN-END:variables
}
