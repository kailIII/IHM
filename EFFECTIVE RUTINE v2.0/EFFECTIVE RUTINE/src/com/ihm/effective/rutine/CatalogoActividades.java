package com.ihm.effective.rutine;

/**
*
* @author Ricardo X. Campozano 
*/

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.ihm.bd.DatabaseHelper;
import com.ihm.providers.CatalogoActividadProvider;
import com.ihm.providers.RutinaActividadProvider;


public class CatalogoActividades extends ListActivity{

	private ListView miLista;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalogo_actividades2);
        
       
        // La URI content://contacts/people consulta todos los contactos
 		Uri uriContacts = CatalogoActividadProvider.CONTENT_URI;
 		
		//Cursor c = managedQuery(uriContacts, null, null, null, null);
 		      
 		DatabaseHelper usdbh =
 	            new DatabaseHelper(this, "effective_rutine.db", null, 1);
 	 
 	        SQLiteDatabase db = usdbh.getWritableDatabase();
 		
		Cursor c =  db.rawQuery( "select id_catalogo as _id,* from catalogo_actividad", null);

 		String[] columns = new String[] {
 				
 				CatalogoActividadProvider.NOMBRE,
 				CatalogoActividadProvider.DESCRIPCION,
 				
 				 				
 				CatalogoActividadProvider._ID
 				
 				 };
 		int[] views = new int[] {R.id.contactID };

 		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.row1, c, columns, views);
 		miLista=this.getListView();
 		
 		//this.getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
 		miLista.setAdapter(adapter);
    }
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
	   // TODO Auto-generated method stub
	   super.onListItemClick(l, v, position, id);

	  Cursor c=(Cursor)miLista.getItemAtPosition(position);
	  
	  String codigo=null;
      Bundle extras = getIntent().getExtras();
      if(extras!=null){
          codigo = extras.getString("codigo");
      }	
	  //Toast.makeText(this,"Item Clicked "+c.getString(1), Toast.LENGTH_SHORT).show();
	  Intent i = new Intent(this, ListaActividades.class);
	  i.putExtra("categoria", c.getString(1));
	  i.putExtra("codigo", codigo);
	  startActivity(i);
	  
	  }
	
	public void verAyuda(View view){
        Intent i = new Intent(this, Ayuda.class);
     startActivity(i);
    }
	
	public void verHome(View view){
        Intent i = new Intent(this, MainActivity.class);
     startActivity(i);
    }
	
	public void cambiarPeso(View view){
        Intent i = new Intent(this, CambiarPeso.class);
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
	
	public void verComidas(View view){
        Intent i = new Intent(this, ComidasDisponibles.class);
     startActivity(i);
    }
    
	 public void verHConsulta(View view){
	        Intent i = new Intent(this, HistorialConsulta.class);
	     startActivity(i);
	    }  
}
