package com.ihm.graphics;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import com.ihm.bd.DatabaseHelper;
import com.ihm.effective.rutine.CambiarPeso;
import com.ihm.effective.rutine.ComidasDisponibles;
import com.ihm.effective.rutine.HistorialConsulta;
import com.ihm.effective.rutine.ListaActividades;
import com.ihm.effective.rutine.ListaComidas;
import com.ihm.effective.rutine.MainActivity;
import com.ihm.effective.rutine.R;
import com.ihm.effective.rutine.Rutina;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class Hmensual extends Activity {
	
	private GraphicalView mChartView;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
        setContentView(R.layout.historial_mensual);
    }
	
	protected void onResume() {
	    super.onResume();
	    if (mChartView == null) {
		    LinearLayout layout = (LinearLayout) findViewById(R.id.chart); 
	        
		    
		    
		    DatabaseHelper usdbh =
	 	            new DatabaseHelper(this, "effective_rutine.db", null, 1);
	 	 
	 	    SQLiteDatabase db = usdbh.getWritableDatabase();
	 		
			Cursor c =  db.rawQuery("SELECT sum (a.total_calorias_kcal) as sum, a.tiempo_inicio, p.total_calorias_kcal  FROM rutina_alimento a ,rutina_actividad p where a.tiempo_inicio=p.tiempo_inicio GROUP BY a.tiempo_inicio ORDER BY a.tiempo_inicio desc",null);
			
			int tamanio=0;
			int semanas=0;
			if ( c.moveToFirst() ) {
				tamanio=c.getCount();
				semanas=tamanio/7;
			}
			if(tamanio!=0){
				//Cursor c2 =  db.rawQuery("SELECT sum (a.total_calorias_kcal) as sum, a.tiempo_inicio, p.total_calorias_kcal  FROM rutina_alimento a ,rutina_actividad p where a.tiempo_inicio=p.tiempo_inicio GROUP BY a.tiempo_inicio ORDER BY a.tiempo_inicio   desc limit "+tamanio,null);
				int y[];
				String sem[];
		        y=new int[semanas+1];
		        sem=new String[semanas+1];
		        Log.d("algo", "bueno2");
				for(int i=0;i<tamanio;i++){
					c.moveToPosition(i);
					double ganadas=Double.parseDouble(c.getString(0));
					double perdidas=Double.parseDouble(c.getString(2));
					double netas=ganadas-perdidas;
					if(i==0 || i==7 || i==14 || i==21){
						y[i/7]=0;
						String label="";
						switch(i){
							case 0:label="Semana actual";break;
							case 7:label="Semana pasada";break;
							case 14: label="hace 3 semanas";break;
							case 21:label="hace 1 mes";break;
						}
						sem[i/7]=label;
					}
					
					y[i/7]=y[i/7]+(int) netas;
					Log.d("algo", "bueno");
				}
				
				
				CategorySeries series = new CategorySeries("Dias de la semana");
				for (int i = 0; i < y.length; i++) {
					series.add("Bar " + (i+1), y[i]);
					
				}
				
				/*// Bar 2
				int[] y2 = { 224, 235, 243, 256, 234, 223, 242, 234, 223, 243, 234, 274 };
				CategorySeries series2 = new CategorySeries("Demo Bar Graph 2");
				for (int i = 0; i < y.length; i++) {
					series2.add("Bar " + (i+1), y2[i]);
				}*/
				
				XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
				dataset.addSeries(series.toXYSeries());
				//dataset.addSeries(series2.toXYSeries());
		
				// This is how the "Graph" itself will look like
				XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
				mRenderer.setChartTitle("Historial mensual");
				mRenderer.setXTitle("Semanas");
				mRenderer.setYTitle("Calorias netas");
				mRenderer.setAxesColor(Color.BLACK);
			    mRenderer.setLabelsColor(Color.BLACK);
			    // Customize bar 1
			    XYSeriesRenderer renderer = new XYSeriesRenderer();
			    renderer.setDisplayChartValues(true);
			    renderer.setColor(Color.CYAN);
			    renderer.setChartValuesSpacing((float) 0.5);
			    
			    
			    mRenderer.setYAxisMin(0);
			    mRenderer.setYAxisMax(50);
			    mRenderer.addSeriesRenderer(renderer);
			    /*// Customize bar 2
			    XYSeriesRenderer renderer2 = new XYSeriesRenderer();
			    renderer.setColor(Color.CYAN);
			    renderer.setDisplayChartValues(true);
			    renderer.setChartValuesSpacing((float) 0.5);
			    mRenderer.addSeriesRenderer(renderer2);*/
			    
			  
			    mRenderer.setApplyBackgroundColor(true);
			    mRenderer.setBackgroundColor(Color.WHITE);
			    mRenderer.setMarginsColor(Color.WHITE);
			    
			    for (int i = 0; i < y.length; i++) {
					//series.add("Bar " + (i+1), y[i]);
					mRenderer.addTextLabel(i+1, sem[i]);
				}
			    
			    mRenderer.setXLabelsAlign(Align.CENTER);
			    mRenderer.setXLabels(0);
			    mRenderer.setLabelsTextSize(15);
			    mRenderer.setXLabelsColor(Color.BLACK);
			    
				mChartView= ChartFactory.getBarChartView(this, dataset,mRenderer, Type.DEFAULT);
				layout.addView(mChartView);
			}
			usdbh.close();
		    }
	    
	    else{
	        mChartView.repaint(); 

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
	

}
