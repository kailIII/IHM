package com.ihm.bd;

/**
*
* @author Edward J. Holguín Holguín
*/
public class CatalogoAlimento {
   private int id_catalogo;
   private String nombre;
   private String descripcion;
   private int id_catalogo_padre;

   public CatalogoAlimento() {
   }

   public CatalogoAlimento(int id_catalogo, String nombre, String descripcion, int id_catalogo_padre) {
       this.id_catalogo = id_catalogo;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.id_catalogo_padre = id_catalogo_padre;
   }

   public String getDescripcion() {
       return descripcion;
   }

   public void setDescripcion(String descripcion) {
       this.descripcion = descripcion;
   }

   public int getId_catalogo() {
       return id_catalogo;
   }

   public void setId_catalogo(int id_catalogo) {
       this.id_catalogo = id_catalogo;
   }

   public int getId_catalogo_padre() {
       return id_catalogo_padre;
   }

   public void setId_catalogo_padre(int id_catalogo_padre) {
       this.id_catalogo_padre = id_catalogo_padre;
   }

   public String getNombre() {
       return nombre;
   }

   public void setNombre(String nombre) {
       this.nombre = nombre;
   }
   
   
}
