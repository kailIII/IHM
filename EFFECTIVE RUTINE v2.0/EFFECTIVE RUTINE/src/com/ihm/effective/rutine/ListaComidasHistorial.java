package com.ihm.effective.rutine;

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
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.ihm.bd.DatabaseHelper;
import com.ihm.graphics.Hmensual;
import com.ihm.graphics.Hsemanal;
import com.ihm.providers.AlimentoProvider;
import com.ihm.providers.DetalleRutinaAlimentoProvider;
import com.ihm.providers.RutinaAlimentoProvider;



public class ListaComidasHistorial extends ListActivity {
	
	private ListView miLista;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_comidas2);
        
        String categoria=null;       
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            categoria = extras.getString("categoria");
        }
        
       
        // La URI content://contacts/people consulta todos los contactos
 		Uri uriContacts = AlimentoProvider.CONTENT_URI;
 		
		//Cursor c = managedQuery(uriContacts, null, null, null, null);
 		      
 		DatabaseHelper usdbh =
 	            new DatabaseHelper(this, "effective_rutine.db", null, 1);
 	 
 	        SQLiteDatabase db = usdbh.getWritableDatabase();
 		
 	       Cursor c;
 	        if(categoria.equals(null)){
 	        	 c =  db.rawQuery( "select id_alimento as _id,* from catalogo_alimento c, alimento a  where c.id_catalogo=a.id_catalogo", null);
 	        }else{
 	        	 c =  db.rawQuery( "select id_alimento as _id,* from catalogo_alimento c, alimento a  where c.id_catalogo=a.id_catalogo and c.id_catalogo_padre='"+categoria+"'", null);
 	        }
 		String[] columns = new String[] {
 				
 				AlimentoProvider.NOMBRE,
 				AlimentoProvider.CALORIAS_KCAL,
 				AlimentoProvider.DESCRIPCION,
 				AlimentoProvider.CANTIDAD_G,
 				AlimentoProvider.ID_CATALOGO,
 				AlimentoProvider.GRASAS_G,
 				AlimentoProvider.PROTEINAS_G,
 				AlimentoProvider.CARBOHIDRATOS_G,
 				AlimentoProvider._ID
 				
 				 };
 		int[] views = new int[] {R.id.nombre, R.id.tcalorias,R.id.porcion };

 		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.row2, c, columns, views);
 		miLista=this.getListView();
 		miLista.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
 		//this.getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
 		miLista.setAdapter(adapter);
 		usdbh.close();
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
    
    public void guardarComidas(View view) {
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
            String codigo=null;
            for (int i=0; i<size; i++) {
                //Si valueAt(i) es true, es que estaba seleccionado
                if (seleccionados.valueAt(i)) {
                    //en keyAt(i) obtengo su posición
                	Cursor c=(Cursor)miLista.getItemAtPosition(seleccionados.keyAt(i));
                	
                    //resultado.append("El elemento "+c.getString(2)+" estaba seleccionado\n");
                    
                	codigo=null;
                    Bundle extras = getIntent().getExtras();
                    if(extras!=null){
                        codigo=extras.getString("codigo");
                    }
                			
                	
                    ContentValues values = new ContentValues();
                    
                    values.put(
    						DetalleRutinaAlimentoProvider.ID_RUTINA_ALIMENTO,
    						codigo);
                    values.put(
    						DetalleRutinaAlimentoProvider.ID_ALIMENTO,
    						c.getString(0));//5
                    values.put(
    						DetalleRutinaAlimentoProvider.CANTIDAD_INGERIDA_G,
    						"1");
    				values.put(
    						DetalleRutinaAlimentoProvider.CALORIAS_KCAL,
    						c.getString(9));
    				values.put(
    						DetalleRutinaAlimentoProvider.GRASAS_G,
    						c.getString(10));
    				values.put(
    						DetalleRutinaAlimentoProvider.PROTEINAS_G,
    						c.getString(11));
    				values.put(
    						DetalleRutinaAlimentoProvider.CARBOHIDRATOS_G,
    						c.getString(12));
    				   				
    				
    				Uri uriNuew = getContentResolver().insert(DetalleRutinaAlimentoProvider.CONTENT_URI, values);
    				
    				//IO implemento
    				Cursor c2 = managedQuery(uriNuew, null, null, null, null);		
    				
    				
                }
            }
            
            String regimen=null;
            String fecha=null;
            Bundle extras = getIntent().getExtras();
            if(extras!=null){
                regimen = extras.getString("regimen");
                codigo=extras.getString("codigo");
                fecha=extras.getString("fecha");
            }
            
            //updateRutina(getDatePhone());
            Intent i = new Intent(this, ListaComidasSeleccionadasHistorial.class);
            i.putExtra("regimen", regimen);
            i.putExtra("codigo", codigo);
            i.putExtra("fecha", fecha);
            Log.d("my tag2" ,"feha LCH: "+fecha);
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
    
    public void updateRutina(String hoy){
    	String sum="0";
        //select sum (d.calorias_kcal) as sum from detalle_rutina_alimento d,alimento a,rutina_alimento r where d.id_alimento=a.id_alimento and d.id_rutina_alimento=r.id_rutina_alimento and r.id_regimen=2
        DatabaseHelper usdbh =
 	            new DatabaseHelper(this, "effective_rutine.db", null, 1);
 	 
 	        SQLiteDatabase db = usdbh.getWritableDatabase();
 		
 	       String regimen=null;
 	      String codigo=null;
           Bundle extras = getIntent().getExtras();
           if(extras!=null){
               regimen = extras.getString("regimen");
               codigo=extras.getString("codigo");
           }    
 	        
		Cursor c =  db.rawQuery("select sum (d.calorias_kcal) as sum from detalle_rutina_alimento d,alimento a,rutina_alimento r where d.id_alimento=a.id_alimento and d.id_rutina_alimento=r.id_rutina_alimento and r.id_regimen="+regimen+" and r.tiempo_inicio='"+hoy+"'" ,null);
		
		if ( c.moveToFirst() ) {
			sum=c.getString(c.getColumnIndex("sum"));
			Log.d("my tag2" ,"yo sumo ahora "+sum);
		}
		if(sum==null){
			sum="0";
			Log.d("my tag2" ,"no sumo nada por eso soy un "+sum);
		}
		ContentValues values = new ContentValues();
		values.put(
				RutinaAlimentoProvider.TOTAL_CALORIAS_KCAL,sum);
		
		
		String uriString="content://"+RutinaAlimentoProvider.PROVIDER_NAME+"/"+RutinaAlimentoProvider.ENTIDAD+"/"+codigo;
		Uri CONTENT_URI = Uri.parse(uriString);
		getContentResolver().update(CONTENT_URI, values,null,null);
		
		usdbh.close();
		
    }

}
