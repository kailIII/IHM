package com.ihm.bd;

import java.util.Date;

/**
*
* @author Edward J. Holguín Holguín
*/
public class RutinaAlimento {
   private int id_rutina_alimento;
   private int id_regimen;
   private Date tiempo_inicio;
   private String comentarios;
   private double total_calorias_kcal;
   private double total_grasas_g;
   private double total_proteinas_g;
   private double total_carbohidratos_g;

   public RutinaAlimento() {
   }
   
   public RutinaAlimento(int id_rutina_alimento, int id_regimen, Date tiempo_inicio, String comentarios) {
       this.id_rutina_alimento = id_rutina_alimento;
       this.id_regimen = id_regimen;
       this.tiempo_inicio = tiempo_inicio;
       this.comentarios = comentarios;
   }

   public String getComentarios() {
       return comentarios;
   }

   public void setComentarios(String comentarios) {
       this.comentarios = comentarios;
   }

   public int getId_regimen() {
       return id_regimen;
   }

   public void setId_regimen(int id_regimen) {
       this.id_regimen = id_regimen;
   }

   public int getId_rutina_alimento() {
       return id_rutina_alimento;
   }

   public void setId_rutina_alimento(int id_rutina_alimento) {
       this.id_rutina_alimento = id_rutina_alimento;
   }

   public Date getTiempo_inicio() {
       return tiempo_inicio;
   }

   public void setTiempo_inicio(Date tiempo_inicio) {
       this.tiempo_inicio = tiempo_inicio;
   }

   public double getTotal_calorias_kcal() {
       return total_calorias_kcal;
   }

   public double getTotal_carbohidratos_g() {
       return total_carbohidratos_g;
   }

   public double getTotal_grasas_g() {
       return total_grasas_g;
   }

   public double getTotal_proteinas_g() {
       return total_proteinas_g;
   }
   
}
