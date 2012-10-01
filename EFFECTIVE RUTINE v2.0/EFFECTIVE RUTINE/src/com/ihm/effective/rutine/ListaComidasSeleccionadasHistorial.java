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
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ihm.effective.rutine.R;
import com.ihm.graphics.Hmensual;
import com.ihm.graphics.Hsemanal;
import com.ihm.bd.DatabaseHelper;
import com.ihm.providers.AlimentoProvider;
import com.ihm.providers.CatalogoAlimentoProvider;
import com.ihm.providers.DatosProvider;
import com.ihm.providers.DetalleRutinaActividadProvider;
import com.ihm.providers.DetalleRutinaAlimentoProvider;
import com.ihm.providers.RutinaAlimentoProvider;

public class ListaComidasSeleccionadasHistorial extends ListActivity{

	private ListView miLista;
	 String regimen=null;
     String codigo=null;
     String fecha=null;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comidas_seleccionadas2);
        
       
       
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            regimen = extras.getString("regimen");
            codigo = extras.getString("codigo");
            fecha=extras.getString("fecha");
            Log.d("my tag2" ,"yo soy el regimen "+regimen+" con codigo "+codigo+" con feha "+fecha);
            cambiarTitulo(regimen,fecha);
        }
        //String hoy=getDatePhone();
        updateRutina(fecha);
        // La URI content://contacts/people consulta todos los contactos
 		Uri uriContacts = DetalleRutinaAlimentoProvider.CONTENT_URI;
 		
		//Cursor c = managedQuery(uriContacts, null, null, null, null);
 		      
 		DatabaseHelper usdbh =
 	            new DatabaseHelper(this, "effective_rutine.db", null, 1);
 	 
 	        SQLiteDatabase db = usdbh.getWritableDatabase();
 		
		Cursor c =  db.rawQuery( "select id_detalle as _id, d.calorias_kcal as tcal,* from detalle_rutina_alimento d,alimento a,rutina_alimento r where d.id_alimento=a.id_alimento and d.id_rutina_alimento=r.id_rutina_alimento and r.id_regimen="+regimen+" and r.tiempo_inicio='"+fecha+"'", null);
//select id_detalle as _id,* from detalle_rutina_alimento d,alimento a,rutina_alimento r where d.id_alimento=a.id_alimento and d.id_rutina_alimento=r.id_rutina_alimento
//and r.tiempo_inicio='2012-09-08'		
 		String[] columns = new String[] {
 				
 				AlimentoProvider.NOMBRE,
 				AlimentoProvider.DESCRIPCION,
 				//AlimentoProvider.CALORIAS_KCAL,
 				
 				"tcal",
 				DetalleRutinaAlimentoProvider.CANTIDAD_INGERIDA_G,
 				
 				DetalleRutinaAlimentoProvider._ID
 				
 				 };
 		int[] views = new int[] {R.id.nombre, R.id.porcion,  R.id.tcalorias, R.id.nporciones };

 		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.row_c_seleccionadas, c, columns, views);
 		miLista=this.getListView();
 		miLista.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
 		miLista.setLongClickable(true);
 		miLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
 		    public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
 		        return onLongListItemClick(v,pos,id);
 		    }
 		});
 		//this.getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
 		miLista.setAdapter(adapter);
 		usdbh.close();
    }
	
	/*protected void onListItemClick(ListView l, View v, int position, long id) {
		   // TODO Auto-generated method stub
		   super.onListItemClick(l, v, position, id);

		   Toast.makeText(this,"Item Clicked", Toast.LENGTH_SHORT).show();
		  
		  }*/
	
	
	
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
    
    public void verCAlimentos(View view){
    	
        Intent i = new Intent(this, CatalogoComidasHistorial.class);
        i.putExtra("codigo", codigo);
        i.putExtra("regimen", regimen);
        i.putExtra("fecha", fecha);
     startActivity(i);
    }
    
    public void deleteSelected(View view) {
        //Obtengo los elementos seleccionados de mi lista
        SparseBooleanArray seleccionados = miLista.getCheckedItemPositions();
 
        if(seleccionados==null || seleccionados.size()==0){
            //Si no había elementos seleccionados...
            Toast.makeText(this,"No hay elementos seleccionados",Toast.LENGTH_SHORT).show();
        }else{
            //si los había, miro sus valores
 
            
           
 
            //Recorro my "array" de elementos seleccionados
            final int size=seleccionados.size();
            for (int i=0; i<size; i++) {
                //Si valueAt(i) es true, es que estaba seleccionado
                if (seleccionados.valueAt(i)) {
                    //en keyAt(i) obtengo su posición
                	Cursor c=(Cursor)miLista.getItemAtPosition(seleccionados.keyAt(i));
                	
                    //resultado.append("El elemento "+c.getString(1)+" estaba seleccionado\n");
                    getContentResolver().delete(Uri.parse("content://effectiveRutine.proveedor.DetalleRutinaAlimento/detalle_rutina_alimento/"+c.getString(2)),  null, null);
                    //miLista.refreshDrawableState();
                    
                    Intent in = new Intent(this, ListaComidasSeleccionadasHistorial.class);
                    in.putExtra("codigo", codigo);
                    in.putExtra("regimen", regimen);
                    in.putExtra("fecha", fecha);
                 startActivity(in);
                    
                }
            }
            
        }
    }
    
    public void updateRutina(String hoy){
    	String sum="0";
    	
    	 Log.d("my tag2" ,"feha: "+hoy);
    	 
    	 Bundle extras = getIntent().getExtras();
         if(extras!=null){
             hoy = extras.getString("fecha");
             }
         Log.d("my tag2" ,"feha hoy2: "+hoy+" regimen "+regimen);
         
        //select sum (d.calorias_kcal) as sum from detalle_rutina_alimento d,alimento a,rutina_alimento r where d.id_alimento=a.id_alimento and d.id_rutina_alimento=r.id_rutina_alimento and r.id_regimen=2
        DatabaseHelper usdbh =
 	            new DatabaseHelper(this, "effective_rutine.db", null, 1);
 	 
 	        SQLiteDatabase db = usdbh.getWritableDatabase();
 		
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
		
		TextView tcal = (TextView) findViewById(R.id.tcaloriasg);
		tcal.setText(sum);
		String uriString="content://"+RutinaAlimentoProvider.PROVIDER_NAME+"/"+RutinaAlimentoProvider.ENTIDAD+"/"+codigo;
		Uri CONTENT_URI = Uri.parse(uriString);
		getContentResolver().update(CONTENT_URI, values,null,null);
		
		usdbh.close();
		
    } 
    
    private String getDatePhone() 
    { 
        Calendar cal = new GregorianCalendar(); 
        Date date = cal.getTime(); 
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
        String formatteDate = df.format(date); 
        return formatteDate; 
    } 
    
    protected boolean onLongListItemClick(View v, int pos, long id) {
        
        Cursor c=(Cursor)miLista.getItemAtPosition(pos);
        Log.d("TAG", "onLongListItemClick");
        Intent i = new Intent(this, MultiplicadorComidashistorial.class);
        i.putExtra("id", c.getString(2));
        i.putExtra("calorias", c.getString(14));
        i.putExtra("grasas", c.getString(15));
        i.putExtra("proteinas", c.getString(16));
        i.putExtra("carbohidratos", c.getString(17));
        i.putExtra("porciones", c.getString(5));
        i.putExtra("nombre", c.getString(11));
        i.putExtra("regimen", regimen);
        i.putExtra("codigo", codigo);
        i.putExtra("fecha", fecha);
        
        startActivity(i);
        return true;
    }
    
    public void verAyuda(View view){
        Intent i = new Intent(this, Ayuda.class);
     startActivity(i);
    }
    
    public void cambiarTitulo(String r,String fecha){
    	int re=0;
    	if(r==null){
    		re=0;
    	}else{
    		re=Integer.parseInt(r);
    	}
    	String titulo="Comidas seleccionadas";
    	switch(re){
    		case 0:titulo="Comidas seleccionadas";break;
    		case 1:titulo="Desayuno "+fecha;break;
    		case 2:titulo="Almuerzo "+fecha;break;
    		case 3:titulo="Merienda "+fecha;break;
    	}
    	
    	TextView t = (TextView) findViewById(R.id.titulo); 
    	t.setText(titulo);
    }
    
    

    
    
}
