package com.ihm.bd;

/**
*
* @author Edward Holguín Holguín
*/
public class DetalleRutinaActividad {
   private int id_detalle;
   private int id_rutina_actividad;
   private int id_actividad;
   private int duracion;
   private int calorias_kcal;

   public DetalleRutinaActividad(int id_detalle, int id_rutina_actividad, int id_actividad, int duracion, int calorias_kcal) {
       this.id_detalle = id_detalle;
       this.id_rutina_actividad = id_rutina_actividad;
       this.id_actividad = id_actividad;
       this.duracion = duracion;
       this.calorias_kcal = calorias_kcal;
   }

   public int getCalorias_kcal() {
       return calorias_kcal;
   }

   public void setCalorias_kcal(int calorias_kcal) {
       this.calorias_kcal = calorias_kcal;
   }

   public int getDuracion() {
       return duracion;
   }

   public void setDuracion(int duracion) {
       this.duracion = duracion;
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
   
   
}
