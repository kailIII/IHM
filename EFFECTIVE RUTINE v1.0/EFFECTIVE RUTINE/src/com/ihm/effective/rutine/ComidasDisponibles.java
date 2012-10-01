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

import com.ihm.providers.AlimentoProvider;
import com.ihm.providers.DatosProvider;
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



public class ComidasDisponibles extends Activity {

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comidas_disponibles);
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
    
    public void verHome(View view){
        Intent i = new Intent(this, MainActivity.class);
     startActivity(i);
    }
    
    public void verLCSeleccionadas(View view, int codigo){
    	
    	String fecha=getDatePhone(); 
    	String existo=existeId(fecha,codigo);
    	if(existo.equals("no")){
    		ContentValues values2 = new ContentValues();
      		values2.put(RutinaAlimentoProvider.ID_REGIMEN,codigo);
    		values2.put(RutinaAlimentoProvider.TIEMPO_INICIO,fecha);
    		values2.put(RutinaAlimentoProvider.COMENTARIOS,0);
    		values2.put(RutinaAlimentoProvider.TOTAL_CALORIAS_KCAL,0);
    		values2.put(RutinaAlimentoProvider.TOTAL_GRASAS_G,0);
    		values2.put(RutinaAlimentoProvider.TOTAL_PROTEINAS_G,0);
    		values2.put(RutinaAlimentoProvider.TOTAL_CARBOHIDRATOS_G,0);
    		
    		Uri uriNueva = getContentResolver().insert(RutinaAlimentoProvider.CONTENT_URI, values2);
    		
    		//IO implemento
    		managedQuery(uriNueva, null, null, null, null);	
    		
    		existo=existeId(fecha,codigo); 
    		Log.d("my tag2" ,"yo soy el numero "+existo);
    		Intent i = new Intent(this, ListaComidasSeleccionadas.class);
    		i.putExtra("codigo", existo);
    		i.putExtra("regimen", codigo+"");
            startActivity(i);
    	}
    	else{
    		
            Intent i = new Intent(this, ListaComidasSeleccionadas.class);
            i.putExtra("codigo", existo);
            i.putExtra("regimen", codigo+"");
            Log.d("my tag2" ,"enviando "+codigo);
            startActivity(i);
    	}
    	
    }
    
    public void sDesayuno(View view){
    	int codigo=1;
    	verLCSeleccionadas(view, codigo);
    }
    
    public void sAlmuerzo(View view){
    	int codigo=2;
    	verLCSeleccionadas(view, codigo);
    }
    public void sMerienda(View view){
    	int codigo=3;
    	verLCSeleccionadas(view, codigo);
    }
    public void sCena(View view){
    	int codigo=4;
    	verLCSeleccionadas(view, codigo);
    }
    public void sAperitivos(View view){
    	int codigo=5;
    	verLCSeleccionadas(view, codigo);
    }
	
    
    private String getDatePhone() 
    { 
        Calendar cal = new GregorianCalendar(); 
        Date date = cal.getTime(); 
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
        String formatteDate = df.format(date); 
        return formatteDate; 
    } 
    

    private String existeId(String fecha, int codigo) 
    { 
    	DatabaseHelper usdbh =  new DatabaseHelper(this, "effective_rutine.db", null, 1);
   	  
    	SQLiteDatabase db = usdbh.getWritableDatabase();
    	Cursor c =  db.rawQuery( "select * from rutina_alimento where tiempo_inicio='"+fecha+"' and id_regimen="+codigo+"", null);
      	//String g=c.getString(c.getColumnIndex("id_rutina_alimento"));
    	if ( c.moveToFirst() ) {
    		String g=c.getString(c.getColumnIndex("id_rutina_alimento"));
    		Log.d("my tag2" ,"la rutina si existe");
    		usdbh.close();
    		return g;
    	} else {
    		String g="no";
    		Log.d("my tag2" ,"la rutina no existe");
    		usdbh.close();
    		return g;
    	}
    } 
    
    public void verAyuda(View view){
        Intent i = new Intent(this, Ayuda.class);
     startActivity(i);
    }
}
