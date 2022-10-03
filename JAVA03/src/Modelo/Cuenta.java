package Modelo;

import java.util.*;
import Controlador.*;

public class Cuenta {
    
    private static int numeroEstatico = 1;
    private boolean primeraCuenta = true;
    
    private int numero;
    private GregorianCalendar fecha; 
    private float saldo;
    private String propietario;
    
    public Cuenta()
    {
        this.numero = 0;
        this.fecha = new GregorianCalendar();
        this.saldo = 0;
        this.propietario = "";
    }
    
    public Cuenta(float saldo, String propietario)
    {
        this();
        this.propietario = propietario;
        this.saldo = saldo;
        this.fecha = new GregorianCalendar();
        numeroEstatico++;
    }
    
    public Cuenta(float saldo, String propietario,int dia, int mes, int anio)
    {
        this(saldo, propietario);
        setFecha(dia,mes,anio);
        setNumero(numeroEstatico);
    }

    public static int getNumeroEstatico() {
        return numeroEstatico;
    }

    public static void setNumeroEstatico(int aNumeroEstatico) {
        numeroEstatico = aNumeroEstatico;
    }

    public boolean isPrimeraCuenta() {
        return primeraCuenta;
    }

    public void setPrimeraCuenta(boolean primeraCuenta) {
        this.primeraCuenta = primeraCuenta;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public GregorianCalendar getFecha() {
        return fecha;
    }

    public void setFecha(int dia, int mes, int anio) {
        this.fecha = new GregorianCalendar(dia,mes,anio);
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }
    
    public void nuevaCuenta(){
        new Cuenta();
    }
    
    public String toString() {
      String imprime = "   Cuenta: ";
      imprime += getNumero();
      imprime += getFecha();
      imprime += "\n\tSaldo: " + getSaldo();
      imprime += "\n\tPropietario: " + getPropietario();
      return imprime;
   }
}