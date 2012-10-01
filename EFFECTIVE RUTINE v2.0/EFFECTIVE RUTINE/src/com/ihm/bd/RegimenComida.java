package com.ihm.bd;

/**
*
* @author Edward J. Holguín Holguín
*/
public class RegimenComida {
   private int id_regimen;
   private String nombre;
   private String recomendacion;

   public RegimenComida() {
   }

   public RegimenComida(int id_regimen, String nombre, String recomendacion) {
       this.id_regimen = id_regimen;
       this.nombre = nombre;
       this.recomendacion = recomendacion;
   }

   public int getId_regimen() {
       return id_regimen;
   }

   public void setId_regimen(int id_regimen) {
       this.id_regimen = id_regimen;
   }

   public String getNombre() {
       return nombre;
   }

   public void setNombre(String nombre) {
       this.nombre = nombre;
   }

   public String getRecomendacion() {
       return recomendacion;
   }

   public void setRecomendacion(String recomendacion) {
       this.recomendacion = recomendacion;
   }
   
}
