package com.ihm.bd;

/**
*
* @author Edward J. Holguín Holguín
*/
public class CatalogoActividad {
   private int id_catalogo;
   private String nombre;
   private String decripcion;

   public CatalogoActividad() {
   }

   public CatalogoActividad(int id_catalogo, String nombre, String decripcion) {
       this.id_catalogo = id_catalogo;
       this.nombre = nombre;
       this.decripcion = decripcion;
   }

   public String getDecripcion() {
       return decripcion;
   }

   public void setDecripcion(String decripcion) {
       this.decripcion = decripcion;
   }

   public int getId_catalogo() {
       return id_catalogo;
   }

   public void setId_catalogo(int id_catalogo) {
       this.id_catalogo = id_catalogo;
   }

   public String getNombre() {
       return nombre;
   }

   public void setNombre(String nombre) {
       this.nombre = nombre;
   }
   
}
