package com.ihm.effective.rutine;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.ihm.graphics.Hmensual;
import com.ihm.graphics.Hsemanal;
import com.ihm.providers.DetalleRutinaActividadProvider;
import com.ihm.providers.DetalleRutinaAlimentoProvider;

public class MultiplicadorActividades extends Activity {

	String id;
	String km;
	String tk;
	
	String nombre;
	
	String codigo;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
               
        setContentView(R.layout.multiplicador_actividades);
        
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
        	id = extras.getString("id");
        	km = extras.getString("km");
        	tk = extras.getString("tk");
        	
        	nombre = extras.getString("nombre");
        	codigo=extras.getString("codigo");
            Log.d("my tag2" ,"mi id "+id+" k minuto: "+km+" k total: "+tk+" me llamo "+nombre+" y soy "+codigo);
            
            
        }
        
        TextView tcal = (TextView) findViewById(R.id.ical);     
        TextView inombre = (TextView) findViewById(R.id.inombre);
       
        tcal.setText(km);
        inombre.setText(nombre);
                
        String array_spinner[];
        array_spinner=new String[10];
        array_spinner[0]="5";
        array_spinner[1]="10";
        array_spinner[2]="15";
        array_spinner[3]="20";
        array_spinner[4]="30";
        array_spinner[5]="45";
        array_spinner[6]="60";
        array_spinner[7]="90";
        array_spinner[8]="120";
        array_spinner[9]="180";
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
    
    public void actualizarMinutos(View view){
        double kminutos=Double.parseDouble(km);
        double tcalorias=Double.parseDouble(tk);
        
        
        String p=((Spinner) findViewById(R.id.spinner1)).getSelectedItem().toString();
        int m=Integer.parseInt(p);
        Log.d("my tag2" ,"aqui "+p+" y yo soy "+id);
        Log.d("my tag2" ,"km "+kminutos+" tc "+tcalorias+" m "+m);
        
        tcalorias=kminutos*m;
        
        
        Log.d("my tag2" ,"multiplico tc "+tcalorias);
        
        ContentValues values = new ContentValues();
		
		values.put(
			    DetalleRutinaActividadProvider.CALORIAS_KCAL,tcalorias+"");
		values.put(
				DetalleRutinaActividadProvider.DURACION_S,m);
		
		
		String uriString="content://"+DetalleRutinaActividadProvider.PROVIDER_NAME+"/"+DetalleRutinaActividadProvider.ENTIDAD+"/"+id;
		Uri CONTENT_URI = Uri.parse(uriString);
		getContentResolver().update(CONTENT_URI, values,null,null);
				
		Intent i = new Intent(this, ListaActividadesSeleccionadas.class);
		
		i.putExtra("codigo", codigo);
	     startActivity(i);
        
        
        
        
        
        
        
        
    }
    
    public void verAyuda(View view){
        Intent i = new Intent(this, Ayuda.class);
     startActivity(i);
    }
}

