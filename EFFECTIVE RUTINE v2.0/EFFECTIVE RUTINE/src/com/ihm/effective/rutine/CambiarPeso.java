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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class CambiarPeso extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_usuarios2);
        
        int genero=mostrarDatos();
        String array_spinner[];
        array_spinner=new String[2];
        if(genero==0){
	        array_spinner[0]="Masculino";
	        array_spinner[1]="Femenino";
        }else{
        	array_spinner[0]="Femenino";
	        array_spinner[1]="Masculino";
        }
        Spinner s = (Spinner) findViewById(R.id.sexo);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_spinner);
                s.setAdapter(adapter);
                
                TextView imc = (TextView) findViewById(R.id.imc);
                TextView clas = (TextView) findViewById(R.id.clas);
                TextView mens = (TextView) findViewById(R.id.mensaje);
                
                DatabaseHelper usdbh2 =  new DatabaseHelper(this, "effective_rutine.db", null, 1);
           	  
            	SQLiteDatabase db2 = usdbh2.getWritableDatabase();
            	Cursor c2 =  db2.rawQuery( "select peso_kg,altura_cm from datos", null);
            	//imc=(masa_g)/(talla cm2)
            	String pesokg="0";
            	String tallam="0";
            	if ( c2.moveToFirst() ) {
    	        	 pesokg=c2.getString(0);
    	        	 tallam=c2.getString(1);
            	}
            	Log.d("my tag" ,"yo peso "+pesokg+" y mi talla es "+tallam);
            	
            	Double pkg=Double.parseDouble(pesokg);
            	Double tm=Double.parseDouble(tallam);
            	
            	
            	double imc2;
            	if(tm==0){
            		imc2=0;
            	}else{
            		imc2 = pkg/(tm*tm);
                	imc2 = Math.rint(imc2*100)/100;
            	}
            	
            	
            	Log.d("my tag" ,"ahora si yo peso "+pkg+" y mi talla es "+tm+" y mi imc es "+imc2);
            	
            	usdbh2.close();
                imc.setText(imc2+"");
                clas.setText(clasificacion(imc2));
                //clasificacion(imc2);
                Log.d("my tag" , "Usted se encuentra en la clasificación de:" + clasificacion(imc2));
                mens.setText(clasificacion(imc2));
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
    

	public void guardarOpciones(View view){
    	
				// TODO Auto-generated method stub
				
				String altura=((EditText) findViewById(R.id.talla)).getText().toString();
				String peso=((EditText) findViewById(R.id.peso)).getText().toString();
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
				
				String uriString="content://"+DatosProvider.PROVIDER_NAME+"/"+DatosProvider.ENTIDAD+"/1";
				Uri CONTENT_URI = Uri.parse(uriString);
				getContentResolver().update(CONTENT_URI, values,null,null);
						
				Intent i = new Intent(this, CambiarPeso.class);
			     startActivity(i);
				}
     }
	
	public void verAyuda(View view){
        Intent i = new Intent(this, Ayuda.class);
     startActivity(i);
    }
	
	public int mostrarDatos(){
		int g=0;
		DatabaseHelper usdbh =
 	            new DatabaseHelper(this, "effective_rutine.db", null, 1);
 	 
 	        SQLiteDatabase db = usdbh.getWritableDatabase();
 		
		Cursor c =  db.rawQuery( "select * from datos",null);
		//String user="";
		String sexo="";
		String peso="";
		String talla="";
		String edad="";
		if ( c.moveToFirst() ) {
		//	user="user";
			sexo=c.getString(1);
			peso=c.getString(2);
			talla=c.getString(3);
			edad=c.getString(4);
		}
		
		if(sexo.equals("Femenino")){
			g=1;
		}
		
		//EditText u = (EditText) findViewById(R.id.user); 
		EditText p = (EditText) findViewById(R.id.peso); 
		EditText t = (EditText) findViewById(R.id.talla); 
		EditText e = (EditText) findViewById(R.id.edad); 
		
		Spinner s=(Spinner)findViewById(R.id.sexo);
		s.setEnabled(false);
		
		//u.setText(user);
		//u.setEnabled(false);
		
		p.setText(peso);
		p.setEnabled(false);
		
		t.setText(talla);
		t.setEnabled(false);
		
		e.setText(edad);
		e.setEnabled(false);
		
		Button guardar=(Button)findViewById(R.id.guardar);
		guardar.setEnabled(false);
		usdbh.close();
		return g;
	}
	
	public void editar(View view){
		//EditText u = (EditText) findViewById(R.id.user); 
		EditText p = (EditText) findViewById(R.id.peso); 
		EditText t = (EditText) findViewById(R.id.talla); 
		EditText e = (EditText) findViewById(R.id.edad); 
		
		Spinner s=(Spinner)findViewById(R.id.sexo);
		s.setFocusable(true);
		s.setEnabled(true);
		
		//u.setEnabled(true);
		
		p.setEnabled(true);
		
		t.setEnabled(true);
		
		e.setEnabled(true);
		
		Button guardar=(Button)findViewById(R.id.guardar);
		guardar.setEnabled(true);
	
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