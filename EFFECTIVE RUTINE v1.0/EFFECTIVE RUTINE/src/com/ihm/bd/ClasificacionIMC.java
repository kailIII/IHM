package com.ihm.bd;

/**
*
* @author Edward J. Holguín Holguín
*/
public class ClasificacionIMC {
   private int id_clasificacion;
   private String clasificacion;
   private double valor_minimo;
   private double valor_maximo;

   public ClasificacionIMC(int id_clasificacion, String clasificacion, double valor_minimo, double valor_maximo) {
       this.id_clasificacion = id_clasificacion;
       this.clasificacion = clasificacion;
       this.valor_minimo = valor_minimo;
       this.valor_maximo = valor_maximo;
   }

   public String getClasificacion() {
       return clasificacion;
   }

   public int getId_clasificacion() {
       return id_clasificacion;
   }

   public double getValor_maximo() {
       return valor_maximo;
   }

   public double getValor_minimo() {
       return valor_minimo;
   }
   
}
