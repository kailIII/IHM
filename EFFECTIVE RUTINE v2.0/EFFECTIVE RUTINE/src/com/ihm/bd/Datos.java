package com.ihm.bd;

/**
*
* @author Edward J. Holguín Holguín
*/
public class Datos {
   private int id_dato;
   private String sexo;
   private double peso_kg;
   private double altura_cm;
   private int edad;

   public Datos(int id_dato, String sexo, double peso_kg, double altura_cm, int edad) {
       this.id_dato = id_dato;
       this.sexo = sexo;
       this.peso_kg = peso_kg;
       this.altura_cm = altura_cm;
       this.edad = edad;
   }

   public double getAltura_cm() {
       return altura_cm;
   }

   public void setAltura_cm(double altura_cm) {
       this.altura_cm = altura_cm;
   }

   public int getEdad() {
       return edad;
   }

   public void setEdad(int edad) {
       this.edad = edad;
   }

   public int getId_dato() {
       return id_dato;
   }

   public void setId_dato(int id_dato) {
       this.id_dato = id_dato;
   }

   public double getPeso_kg() {
       return peso_kg;
   }

   public void setPeso_kg(double peso_kg) {
       this.peso_kg = peso_kg;
   }

   public String getSexo() {
       return sexo;
   }

   public void setSexo(String sexo) {
       this.sexo = sexo;
   }
   
}
