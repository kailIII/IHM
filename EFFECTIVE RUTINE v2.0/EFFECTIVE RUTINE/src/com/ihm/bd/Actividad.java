package com.ihm.bd;

/**
*
* @author Edward J. Holguín Holguín
*/
public class Actividad {
   private int id_actividad;
   private String nombre;
   private String descripcion;
   private double kcal;
   private int tiempo_s;
   private int id_catalogo;

   public Actividad() {
   }

   public Actividad(int id_actividad, String nombre, String descripcion, double kcal, int tiempo_s, int id_catalogo) {
       this.id_actividad = id_actividad;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.kcal = kcal;
       this.tiempo_s = tiempo_s;
       this.id_catalogo = id_catalogo;
   }

   public String getDescripcion() {
       return descripcion;
   }

   public void setDescripcion(String descripcion) {
       this.descripcion = descripcion;
   }

   public int getId_actividad() {
       return id_actividad;
   }

   public void setId_actividad(int id_actividad) {
       this.id_actividad = id_actividad;
   }

   public int getId_catalogo() {
       return id_catalogo;
   }

   public void setId_catalogo(int id_catalogo) {
       this.id_catalogo = id_catalogo;
   }

   public double getKcal() {
       return kcal;
   }

   public void setKcal(double kcal) {
       this.kcal = kcal;
   }

   public String getNombre() {
       return nombre;
   }

   public void setNombre(String nombre) {
       this.nombre = nombre;
   }

   public int getTiempo_s() {
       return tiempo_s;
   }

   public void setTiempo_s(int tiempo_s) {
       this.tiempo_s = tiempo_s;
   }
   

}
