package com.ihm.effective.rutine;

import android.app.ListActivity;
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
import com.ihm.providers.CatalogoAlimentoProvider;

public class CatalogoComidasHistorial extends ListActivity{

	private ListView miLista;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalogo_comidas2);
        
       
        // La URI content://contacts/people consulta todos los contactos
 		Uri uriContacts = CatalogoAlimentoProvider.CONTENT_URI;
 		
		//Cursor c = managedQuery(uriContacts, null, null, null, null);
 		      
 		DatabaseHelper usdbh =
 	            new DatabaseHelper(this, "effective_rutine.db", null, 1);
 	 
 	        SQLiteDatabase db = usdbh.getWritableDatabase();
 		
		Cursor c =  db.rawQuery( "select id_catalogo as _id,* from catalogo_alimento c where c.id_catalogo_padre is null", null);

 		String[] columns = new String[] {
 				
 				CatalogoAlimentoProvider.NOMBRE,
 				CatalogoAlimentoProvider.DESCRIPCION,
 				CatalogoAlimentoProvider.ID_CATALOGO_PADRE,
 				 				
 				CatalogoAlimentoProvider._ID
 				
 				 };
 		int[] views = new int[] {R.id.contactID };

 		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.row1, c, columns, views);
 		miLista=this.getListView();
 		
 		//this.getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
 		miLista.setAdapter(adapter);
 		usdbh.close();
    }
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
	   // TODO Auto-generated method stub
	   super.onListItemClick(l, v, position, id);

	  Cursor c=(Cursor)miLista.getItemAtPosition(position);
   	  
	  String codigo=null;
	  String regimen=null;
	  String fecha=null;
      Bundle extras = getIntent().getExtras();
      if(extras!=null){
          codigo = extras.getString("codigo");
          regimen = extras.getString("regimen");
          fecha=extras.getString("fecha");
      }
	  //Toast.makeText(this,"Item Clicked "+c.getString(1), Toast.LENGTH_SHORT).show();
	  Intent i = new Intent(this, ListaComidasHistorial.class);
	  i.putExtra("categoria", c.getString(1));
	  i.putExtra("codigo", codigo);
	  i.putExtra("regimen", regimen);
	  Log.d("my tag2" ,"feha CCH: "+fecha);
	  i.putExtra("fecha", fecha);
	  startActivity(i);
	  
	  }
	
	public void verAyuda(View view){
        Intent i = new Intent(this, Ayuda.class);
     startActivity(i);
    }
	
	public void cambiarPeso(View view){
        Intent i = new Intent(this, CambiarPeso.class);
     startActivity(i);
    }
	
	 public void verHome(View view){
	        Intent i = new Intent(this, MainActivity.class);
	     startActivity(i);
	    }
}
