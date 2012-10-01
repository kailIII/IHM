package com.ihm.effective.rutine;

/**
*
* @author Ricardo X. Campozano 
*/


import com.ihm.providers.ActividadProvider;
import com.ihm.providers.AlimentoProvider;
import com.ihm.providers.CatalogoActividadProvider;
import com.ihm.providers.DetalleRutinaActividadProvider;
import com.ihm.providers.DetalleRutinaAlimentoProvider;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.ihm.bd.DatabaseHelper;
import com.ihm.effective.rutine.R;
import com.ihm.graphics.Hmensual;
import com.ihm.graphics.Hsemanal;


public class ListaActividades extends ListActivity{
	private ListView miLista;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_actividades2);
        
        String categoria=null;
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            categoria = extras.getString("categoria");
        }
       
        // La URI content://contacts/people consulta todos los contactos
 		Uri uriContacts = ActividadProvider.CONTENT_URI;
 		
		//Cursor c = managedQuery(uriContacts, null, null, null, null);
 		      
 		DatabaseHelper usdbh =
 	            new DatabaseHelper(this, "effective_rutine.db", null, 1);
 	 
 	        SQLiteDatabase db = usdbh.getWritableDatabase();
 		
 	       Cursor c;
 	        if(categoria.equals(null)){
 	        	 c =  db.rawQuery( "select id_actividad as _id,* from catalogo_actividad c, actividad a  where c.id_catalogo=a.id_catalogo", null);
 	        }else{
 	        	 c =  db.rawQuery( "select id_actividad as _id,* from catalogo_actividad c, actividad a  where c.id_catalogo=a.id_catalogo and c.id_catalogo='"+categoria+"'", null);
 	        }
 		String[] columns = new String[] {
 				
 				ActividadProvider.NOMBRE,
 				ActividadProvider.KCAL,
 				ActividadProvider.DESCRIPCION,
 				ActividadProvider.TIEMPO_S,
 				ActividadProvider.ID_CATALOGO,
 				
 				ActividadProvider._ID
 				
 				 };
 		int[] views = new int[] {R.id.nombre, R.id.tcalorias };

 		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.row3, c, columns, views);
 		miLista=this.getListView();
 		miLista.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
 		//this.getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
 		miLista.setAdapter(adapter);
    }
	
	/*protected void onListItemClick(ListView l, View v, int position, long id) {
		   // TODO Auto-generated method stub
		   super.onListItemClick(l, v, position, id);

		   Toast.makeText(this,"Item Clicked", Toast.LENGTH_SHORT).show();
		  
		  }*/
	
	/**
     * Este método se llama al pulsar en el botón de borrar
     * como se definió en el layout res/layout/main.xml
     */
    public void deleteSelected(View view) {
        //Obtengo los elementos seleccionados de mi lista
        SparseBooleanArray seleccionados = miLista.getCheckedItemPositions();
 
        if(seleccionados==null || seleccionados.size()==0){
            //Si no había elementos seleccionados...
            Toast.makeText(this,"No hay elementos seleccionados",Toast.LENGTH_SHORT).show();
        }else{
            //si los había, miro sus valores
 
            //Esto es para ir creando un mensaje largo que mostraré al final
            StringBuilder resultado=new StringBuilder();
            resultado.append("Se eliminarán los siguientes elementos:\n");
 
            //Recorro my "array" de elementos seleccionados
            final int size=seleccionados.size();
            for (int i=0; i<size; i++) {
                //Si valueAt(i) es true, es que estaba seleccionado
                if (seleccionados.valueAt(i)) {
                    //en keyAt(i) obtengo su posición
                	Cursor c=(Cursor)miLista.getItemAtPosition(seleccionados.keyAt(i));
                	
                    resultado.append("El elemento "+c.getString(2)+" estaba seleccionado\n");
                }
            }
            Toast.makeText(this,resultado.toString(),Toast.LENGTH_LONG).show();
        }
    }
    
    public void guardarActividades(View view) {
        //Obtengo los elementos seleccionados de mi lista
        SparseBooleanArray seleccionados = miLista.getCheckedItemPositions();
 
        if(seleccionados==null || seleccionados.size()==0){
            //Si no había elementos seleccionados...
            Toast.makeText(this,"No hay elementos seleccionados",Toast.LENGTH_SHORT).show();
        }else{
            //si los había, miro sus valores
 
            //Esto es para ir creando un mensaje largo que mostraré al final
            //StringBuilder resultado=new StringBuilder();
            
 
            //Recorro my "array" de elementos seleccionados
            final int size=seleccionados.size();
            for (int i=0; i<size; i++) {
                //Si valueAt(i) es true, es que estaba seleccionado
                if (seleccionados.valueAt(i)) {
                    //en keyAt(i) obtengo su posición
                	Cursor c=(Cursor)miLista.getItemAtPosition(seleccionados.keyAt(i));
                	
                    //resultado.append("El elemento "+c.getString(2)+" estaba seleccionado\n");
                    
                	String codigo=null;
                    Bundle extras = getIntent().getExtras();
                    if(extras!=null){
                        codigo=extras.getString("codigo");
                    }               			
                	
                    ContentValues values = new ContentValues();
                    
                    values.put(
    						DetalleRutinaActividadProvider.ID_RUTINA_ACTIVIDAD,
    						codigo);
                    values.put(
    						DetalleRutinaActividadProvider.ID_ACTIVIDAD,
    						c.getString(0));
                    values.put(
    						DetalleRutinaActividadProvider.DURACION_S,
    						"30");
    				values.put(
    						DetalleRutinaActividadProvider.CALORIAS_KCAL,
    						c.getString(c.getColumnIndex("kcal")));
    				 				
    				
    				
    				Uri uriNuew = getContentResolver().insert(DetalleRutinaActividadProvider.CONTENT_URI, values);
    				
    				//IO implemento
    				Cursor c2 = managedQuery(uriNuew, null, null, null, null);		
    				
    				
                }
            }
            String codigo=null;
            Bundle extras = getIntent().getExtras();
            if(extras!=null){
                codigo=extras.getString("codigo");
            }
            Intent i = new Intent(this, ListaActividadesSeleccionadas.class);
            i.putExtra("codigo", codigo);
		     startActivity(i);
        }
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
    
    public void verAyuda(View view){
        Intent i = new Intent(this, Ayuda.class);
     startActivity(i);
    }
}
