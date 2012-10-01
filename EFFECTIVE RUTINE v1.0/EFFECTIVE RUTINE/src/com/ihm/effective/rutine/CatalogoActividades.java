package com.ihm.effective.rutine;

/**
*
* @author Ricardo X. Campozano 
*/

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.ihm.bd.DatabaseHelper;
import com.ihm.providers.CatalogoActividadProvider;


public class CatalogoActividades extends ListActivity{

	private ListView miLista;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalogo_actividades);
        
       
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
}
