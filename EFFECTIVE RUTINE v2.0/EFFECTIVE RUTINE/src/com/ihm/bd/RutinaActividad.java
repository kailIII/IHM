package com.ihm.bd;

/**
*
* @author Edward J. Holguín Holguín
*/
public class RutinaActividad {
   private int id_detalle;
   private int id_rutina_actividad;
   private int id_actividad;
   private int duracion_s;
   private double calorias_kcal;

   public RutinaActividad() {
   }

   public RutinaActividad(int id_detalle, int id_rutina_actividad, int id_actividad, int duracion_s) {
       this.id_detalle = id_detalle;
       this.id_rutina_actividad = id_rutina_actividad;
       this.id_actividad = id_actividad;
       this.duracion_s = duracion_s;
   }

   public int getDuracion_s() {
       return duracion_s;
   }

   public void setDuracion_s(int duracion_s) {
       this.duracion_s = duracion_s;
   }

   public int getId_actividad() {
       return id_actividad;
   }

   public void setId_actividad(int id_actividad) {
       this.id_actividad = id_actividad;
   }

   public int getId_detalle() {
       return id_detalle;
   }

   public void setId_detalle(int id_detalle) {
       this.id_detalle = id_detalle;
   }

   public int getId_rutina_actividad() {
       return id_rutina_actividad;
   }

   public void setId_rutina_actividad(int id_rutina_actividad) {
       this.id_rutina_actividad = id_rutina_actividad;
   }

   public double getCalorias_kcal() {
       return calorias_kcal;
   }
   
}
