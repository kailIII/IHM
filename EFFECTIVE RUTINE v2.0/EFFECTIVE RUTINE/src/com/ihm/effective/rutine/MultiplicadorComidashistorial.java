package com.ihm.effective.rutine;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.ihm.bd.DatabaseHelper;
import com.ihm.graphics.Hmensual;
import com.ihm.graphics.Hsemanal;
import com.ihm.providers.CatalogoAlimentoProvider;
import com.ihm.providers.DetalleRutinaAlimentoProvider;

public class MultiplicadorComidashistorial extends Activity{

	String id;
	String calorias;
	String grasas;
	String proteinas;
	String carbohidratos;
	String porciones;
	String nombre;
	
	String regimen;
	String codigo;
	String fecha;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
               
        setContentView(R.layout.multiplicador_comidas2);
        
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
        	calorias = extras.getString("calorias");
        	grasas = extras.getString("grasas");
        	proteinas = extras.getString("proteinas");
        	carbohidratos = extras.getString("carbohidratos");
        	porciones = extras.getString("porciones");
        	id = extras.getString("id");
        	nombre=extras.getString("nombre");
            Log.d("my tag2" ,"kcal "+calorias+" g "+grasas+" p "+proteinas+" c "+carbohidratos+" p "+porciones+" y soy "+id);
            regimen=extras.getString("regimen");
            codigo=extras.getString("codigo");
            fecha=extras.getString("fecha");
        }
        
        TextView tcal = (TextView) findViewById(R.id.icalorias);
        TextView tgrasas = (TextView) findViewById(R.id.igrasas);
        TextView tpro = (TextView) findViewById(R.id.iproteinas);
        TextView tcar = (TextView) findViewById(R.id.icarbohidratos);
        TextView inombre = (TextView) findViewById(R.id.inombre);
       
        if(calorias==null){
        	 Log.d("my tag2" ,"mori aqui y no tengo calorias");
        	calorias="0";
        }
        //Log.d("my tag2" ,"mori aqui y tengo calorias "+calorias);
        tcal.setText(calorias);
        tgrasas.setText(grasas);
        tpro.setText(proteinas);
        tcar.setText(carbohidratos);
        inombre.setText(nombre);

        
        String array_spinner[];
        array_spinner=new String[10];
        array_spinner[0]="1";
        array_spinner[1]="2";
        array_spinner[2]="3";
        array_spinner[3]="4";
        array_spinner[4]="5";
        array_spinner[5]="6";
        array_spinner[6]="7";
        array_spinner[7]="8";
        array_spinner[8]="9";
        array_spinner[9]="10";
        Spinner s = (Spinner) findViewById(R.id.spinner1);
        
       /* String myString = "some value"; //the value you want the position for

        ArrayAdapter myAdap = (ArrayAdapter) mySpinner.getAdapter(); //cast to an ArrayAdapter

        int spinnerPosition = myAdap.getPosition(myString);

        //set the default according to value
        mySpinner.setSelection(spinnerPosition);*/
        
        
        ArrayAdapter adapter = new ArrayAdapter(this,
        android.R.layout.simple_spinner_item, array_spinner);
        s.setAdapter(adapter);
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
    
    public void verHsemanal(View view){
        Intent i = new Intent(this, Hsemanal.class);
     startActivity(i);
    }
    
    public void verHmensual(View view){
        Intent i = new Intent(this, Hmensual.class);
     startActivity(i);
    }
    
    public void actualizarPorcion(View view){
        double caloria=Double.parseDouble(calorias);
        double gras=Double.parseDouble(grasas);
        double prot=Double.parseDouble(proteinas);
        double carb=Double.parseDouble(carbohidratos);
        
        String p=((Spinner) findViewById(R.id.spinner1)).getSelectedItem().toString();
        int por=Integer.parseInt(p);
        Log.d("my tag2" ,"aqui "+p+" y yo soy "+id);
        Log.d("my tag2" ,"c "+caloria+" g "+gras+" p "+prot+" car "+carb);
        
        caloria=caloria*por;
        gras=gras*por;
        prot=prot*por;
        carb=carb*por;
        
        Log.d("my tag2" ,"multiplico c "+caloria+" g "+gras+" p "+prot+" car "+carb);
        
        ContentValues values = new ContentValues();
		
		values.put(
			    DetalleRutinaAlimentoProvider.CALORIAS_KCAL,caloria);
		values.put(
				DetalleRutinaAlimentoProvider.GRASAS_G,gras);
		values.put(
				DetalleRutinaAlimentoProvider.PROTEINAS_G,prot);
		values.put(
				DetalleRutinaAlimentoProvider.CARBOHIDRATOS_G,carb);
		values.put(
				DetalleRutinaAlimentoProvider.CANTIDAD_INGERIDA_G,por);
		
		String uriString="content://"+DetalleRutinaAlimentoProvider.PROVIDER_NAME+"/"+DetalleRutinaAlimentoProvider.ENTIDAD+"/"+id;
		Uri CONTENT_URI = Uri.parse(uriString);
		getContentResolver().update(CONTENT_URI, values,null,null);
				
		Intent i = new Intent(this, ListaComidasSeleccionadasHistorial.class);
		i.putExtra("regimen", regimen);
		i.putExtra("codigo", codigo);
		i.putExtra("fecha", fecha);
	     startActivity(i);
        
        
        
        
        
        
        
        
    }
    
    public void verAyuda(View view){
        Intent i = new Intent(this, Ayuda.class);
     startActivity(i);
    }
}