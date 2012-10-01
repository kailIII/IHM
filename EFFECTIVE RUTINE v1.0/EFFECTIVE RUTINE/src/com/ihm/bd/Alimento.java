package com.ihm.bd;

/**
*
* @author Edward J. Holguín Holguín
*/
public class Alimento {
   private int id_alimento;
   private String nombre;
   private int descipcion;
   private double cantidad_g;
   private double calorias_kcal;
   private double grasas_g;
   private double proteinas_g;
   private double carbohidratos_g;
   private double id_catalogo;

   public Alimento() {
   }

   public Alimento(int id_alimento, String nombre, int descipcion, double cantidad_g, double calorias_kcal, double id_catalogo) {
       this.id_alimento = id_alimento;
       this.nombre = nombre;
       this.descipcion = descipcion;
       this.cantidad_g = cantidad_g;
       this.calorias_kcal = calorias_kcal;
       this.id_catalogo = id_catalogo;
   }

   public Alimento(int id_alimento, String nombre, int descipcion, double cantidad_g, double calorias_kcal, double grasas_g, double proteinas_g, double carbohidratos_g, double id_catalogo) {
       this.id_alimento = id_alimento;
       this.nombre = nombre;
       this.descipcion = descipcion;
       this.cantidad_g = cantidad_g;
       this.calorias_kcal = calorias_kcal;
       this.grasas_g = grasas_g;
       this.proteinas_g = proteinas_g;
       this.carbohidratos_g = carbohidratos_g;
       this.id_catalogo = id_catalogo;
   }

   public double getCalorias_kcal() {
       return calorias_kcal;
   }

   public void setCalorias_kcal(double calorias_kcal) {
       this.calorias_kcal = calorias_kcal;
   }

   public double getCantidad_g() {
       return cantidad_g;
   }

   public void setCantidad_g(double cantidad_g) {
       this.cantidad_g = cantidad_g;
   }

   public double getCarbohidratos_g() {
       return carbohidratos_g;
   }

   public void setCarbohidratos_g(double carbohidratos_g) {
       this.carbohidratos_g = carbohidratos_g;
   }

   public int getDescipcion() {
       return descipcion;
   }

   public void setDescipcion(int descipcion) {
       this.descipcion = descipcion;
   }

   public double getGrasas_g() {
       return grasas_g;
   }

   public void setGrasas_g(double grasas_g) {
       this.grasas_g = grasas_g;
   }

   public int getId_alimento() {
       return id_alimento;
   }

   public void setId_alimento(int id_alimento) {
       this.id_alimento = id_alimento;
   }

   public double getId_catalogo() {
       return id_catalogo;
   }

   public void setId_catalogo(double id_catalogo) {
       this.id_catalogo = id_catalogo;
   }

   public String getNombre() {
       return nombre;
   }

   public void setNombre(String nombre) {
       this.nombre = nombre;
   }

   public double getProteinas_g() {
       return proteinas_g;
   }

   public void setProteinas_g(double proteinas_g) {
       this.proteinas_g = proteinas_g;
   }
   
   
}
