package com.ihm.bd;

/**
*
* @author Edward J. Holguín Holguín
*/
public class DetalleRutinaAlimento {
   private int id_detalle;
   private int id_rutina_alimento;
   private int id_alimento;
   private double cantidad_ingerida_g;
   private double calorias_kcal;
   private double grasas_g;
   private double proteinas_g;
   private double carbohidratos_g;

   public DetalleRutinaAlimento(int id_detalle, int id_rutina_alimento, int id_alimento, double cantidad_ingerida_g) {
       this.id_detalle = id_detalle;
       this.id_rutina_alimento = id_rutina_alimento;
       this.id_alimento = id_alimento;
       this.cantidad_ingerida_g = cantidad_ingerida_g;
   }

   public double getCantidad_ingerida_g() {
       return cantidad_ingerida_g;
   }

   public void setCantidad_ingerida_g(double cantidad_ingerida_g) {
       this.cantidad_ingerida_g = cantidad_ingerida_g;
   }

   public int getId_alimento() {
       return id_alimento;
   }

   public void setId_alimento(int id_alimento) {
       this.id_alimento = id_alimento;
   }

   public int getId_detalle() {
       return id_detalle;
   }

   public void setId_detalle(int id_detalle) {
       this.id_detalle = id_detalle;
   }

   public int getId_rutina_alimento() {
       return id_rutina_alimento;
   }

   public void setId_rutina_alimento(int id_rutina_alimento) {
       this.id_rutina_alimento = id_rutina_alimento;
   }

   public double getCalorias_kcal() {
       return calorias_kcal;
   }

   public double getCarbohidratos_g() {
       return carbohidratos_g;
   }

   public double getGrasas_g() {
       return grasas_g;
   }

   public double getProteinas_g() {
       return proteinas_g;
   }
   
}
