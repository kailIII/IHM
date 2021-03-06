package com.ihm.effective.rutine;

/**
*
* @author Ricardo X. Campozano 
*/

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.ihm.bd.DatabaseHelper;
import com.ihm.graphics.Hmensual;
import com.ihm.graphics.Hsemanal;
import com.ihm.providers.RutinaActividadProvider;
import com.ihm.providers.RutinaAlimentoProvider;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

public class Rutina extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rutina);
    }
	
	public void cambiarPeso(View view){
        Intent i = new Intent(this, CambiarPeso.class);
     startActivity(i);
    }
    
    public void verComidas(View view){
        Intent i = new Intent(this, ComidasDisponibles.class);
     startActivity(i);
    }
    
    public void verHConsulta(View view){
        Intent i = new Intent(this, HistorialConsulta.class);
     startActivity(i);
    }
    
    public void verRutina(View view){
        Intent i = new Intent(this, Rutina.class);
     startActivity(i);
    }
    
    public void verHMensual(View view){
        Intent i = new Intent(this, Hmensual.class);
     startActivity(i);
    }
    
    public void verHSemanal(View view){
        Intent i = new Intent(this, Hsemanal.class);
     startActivity(i);
    }
    
    public void verLActividades(View view){
        Intent i = new Intent(this, ListaActividades.class);
     startActivity(i);
    }
    
    public void verLComidas(View view){
        Intent i = new Intent(this, ListaComidas.class);
     startActivity(i);
    }
    
    public void verLASeleccionadas(View view){
    	
    	String fecha=getDatePhone(); 
    	String existo=existeId(fecha);
    	if(existo.equals("no")){
    		ContentValues values2 = new ContentValues();
      		
    		values2.put(RutinaActividadProvider.TIEMPO_INICIO,fecha);
    		values2.put(RutinaActividadProvider.TIEMPO_TOTAL_S,0);
    		values2.put(RutinaActividadProvider.TOTAL_CALORIAS_KCAL,0);
    		    		
    		Uri uriNueva = getContentResolver().insert(RutinaActividadProvider.CONTENT_URI, values2);
    		
    		//IO implemento
    		managedQuery(uriNueva, null, null, null, null);	
    		
    		existo=existeId(fecha); 
    		Log.d("my tag2" ,"yo soy la rutina_actividad "+existo);
    		Intent i = new Intent(this, ListaActividadesSeleccionadas.class);
    		i.putExtra("codigo", existo);
    		
            startActivity(i);
    	}
    	else{
    		
            Intent i = new Intent(this, ListaActividadesSeleccionadas.class);
            i.putExtra("codigo", existo);
           
            Log.d("my tag2" ,"enviando "+existo);
            startActivity(i);
    	}
        
    }
    
    private String getDatePhone() 
    { 
        Calendar cal = new GregorianCalendar(); 
        Date date = cal.getTime(); 
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
        String formatteDate = df.format(date); 
        return formatteDate; 
    } 
    
    private String existeId(String fecha) 
    { 
    	DatabaseHelper usdbh =  new DatabaseHelper(this, "effective_rutine.db", null, 1);
   	  
    	SQLiteDatabase db = usdbh.getWritableDatabase();
    	Cursor c =  db.rawQuery( "select * from rutina_actividad where tiempo_inicio='"+fecha+"'", null);
      	//String g=c.getString(c.getColumnIndex("id_rutina_alimento"));
    	if ( c.moveToFirst() ) {
    		String g=c.getString(c.getColumnIndex("id_rutina_actividad"));
    		Log.d("my tag2" ,"la actividad si existe");
    		usdbh.close();
    		return g;
    	} else {
    		String g="no";
    		Log.d("my tag2" ,"la actividad no existe");
    		usdbh.close();
    		return g;
    	}
    } 
    
    public void verAyuda(View view){
        Intent i = new Intent(this, Ayuda.class);
     startActivity(i);
    }
    
    public void verHome(View view){
        Intent i = new Intent(this, MainActivity.class);
     startActivity(i);
    }

}
