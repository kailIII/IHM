package com.ihm.effective.rutine;

/**
*
* @author Ricardo X. Campozano 
*/



import com.ihm.bd.DatabaseHelper;
import com.ihm.graphics.Hmensual;
import com.ihm.graphics.Hsemanal;
import com.ihm.providers.DatosProvider;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	DatabaseHelper usdbh =  new DatabaseHelper(this, "effective_rutine.db", null, 1);
 	  
    	SQLiteDatabase db = usdbh.getWritableDatabase();
    	Cursor c =  db.rawQuery( "select id_dato from datos", null);
    	super.onCreate(savedInstanceState);
    	boolean aqui=false;
    	if ( c.moveToFirst() ) {
    		Log.d("my tag" ,"si tengo");// start activity a
    		usdbh.close();
    		aqui=true;
    		setContentView(R.layout.activity_main);
    	} else {
    		Log.d("my tag" ,"no tengo");// start activity b
    		usdbh.close();
    		
    		setContentView(R.layout.cambiar_peso);
    		String array_spinner[];
            array_spinner=new String[2];
            array_spinner[0]="Masculino";
            array_spinner[1]="Femenino";
            Spinner s = (Spinner) findViewById(R.id.sexo);
            ArrayAdapter adapter = new ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, array_spinner);
                    s.setAdapter(adapter);
    	}
    	
    	if(aqui==true){
    		TextView imc = (TextView) findViewById(R.id.imc);
            TextView clas = (TextView) findViewById(R.id.clas);
            
            DatabaseHelper usdbh2 =  new DatabaseHelper(this, "effective_rutine.db", null, 1);
       	  
        	SQLiteDatabase db2 = usdbh2.getWritableDatabase();
        	Cursor c2 =  db2.rawQuery( "select peso_kg,altura_cm from datos", null);
        	//imc=(masa_g)/(talla cm2)
        	String pesokg=null;
        	String tallam=null;
        	if ( c2.moveToFirst() ) {
	        	 pesokg=c2.getString(0);
	        	 tallam=c2.getString(1);
        	}
        	Log.d("my tag" ,"yo peso "+pesokg+" y mi talla es "+tallam);
        	
        	Double pkg=Double.parseDouble(pesokg);
        	Double tm=Double.parseDouble(tallam);
        	
        	
        	double imc2;
        	imc2 = pkg/(tm*tm);
        	imc2 = Math.rint(imc2*100)/100;
        	
        	Log.d("my tag" ,"ahora si yo peso "+pkg+" y mi talla es "+tm+" y mi imc es "+imc2);
        	
        	usdbh2.close();
            imc.setText(imc2+"");
            //clasificacion(imc2);
            Log.d("my tag" , "Usted se encuentra en la clasificación de:" + clasificacion(imc2));
            clas.setText(clasificacion(imc2));
    	}
    	
        
                
    }
    
    public void guardarOpciones(View view){
    	
		// TODO Auto-generated method stub
		
		String altura=((EditText) findViewById(R.id.talla)).getText().toString();
		String peso=((EditText) findViewById(R.id.peso)).getText().toString();
		//String sexo=((EditText) findViewById(R.id.sexo)).getText().toString();
		String sexo=((Spinner) findViewById(R.id.sexo)).getSelectedItem().toString();
		String edad=((EditText) findViewById(R.id.edad)).getText().toString();
		if(altura.trim().isEmpty()||peso.trim().isEmpty()||sexo.trim().isEmpty()||edad.trim().isEmpty()){
			Toast.makeText(this,"Faltan campos por completar", Toast.LENGTH_SHORT).show();
		}
		else{
		
		ContentValues values = new ContentValues();
				
		values.put(
				DatosProvider.ALTURA_CM,altura);
		values.put(
				DatosProvider.PESO_KG,peso);
		values.put(
				DatosProvider.SEXO,sexo);
		values.put(
				DatosProvider.EDAD,edad);
		Uri uriNuew = getContentResolver().insert(DatosProvider.CONTENT_URI, values);
		
		//IO implemento
		managedQuery(uriNuew, null, null, null, null);		
		
		Intent i = new Intent(this, MainActivity.class);
	     startActivity(i);
		}
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
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
    
    public String clasificacion(Double peso){
    	String clasificacion=null;
    	if(peso>=0 && peso<=18.5){
    		clasificacion = "DEFICIENCIA NUTRICIONAL";
    	}
    	else if(peso>18.5 && peso<=20){
    		clasificacion = "BAJO DE PESO";
    	}
    	else if(peso>20 && peso<=25){
    		clasificacion = "NORMAL";
    	}
    	else if(peso>25 && peso<=30){
    		clasificacion = "SOBREPESO";
    	}
    	else if(peso>30 && peso<=40){
    		clasificacion = "OBESO";
    	}
    	else if(peso>40){
    		clasificacion = "OBESIDA MORBIA";
    	}
    	else{
    		clasificacion = "";
    	}
    	return clasificacion;
    }
    
    
   
    
}
