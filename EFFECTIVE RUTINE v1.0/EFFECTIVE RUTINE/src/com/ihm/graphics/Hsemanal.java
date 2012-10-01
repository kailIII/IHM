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
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class Hsemanal extends Activity{
	
private GraphicalView mChartView;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
        setContentView(R.layout.historial_mensual);
    }
	
	protected void onResume() {
	    super.onResume();
	    if (mChartView == null) {
		    LinearLayout layout = (LinearLayout) findViewById(R.id.chart); 
	        
		    //SELECT sum (a.total_calorias_kcal) as sum, a.tiempo_inicio, p.total_calorias_kcal  FROM rutina_alimento a ,rutina_actividad p where a.tiempo_inicio=p.tiempo_inicio GROUP BY a.tiempo_inicio ORDER BY a.tiempo_inicio   desc limit 3
		    DatabaseHelper usdbh =
	 	            new DatabaseHelper(this, "effective_rutine.db", null, 1);
	 	 
	 	    SQLiteDatabase db = usdbh.getWritableDatabase();
	 		
			Cursor c =  db.rawQuery("SELECT sum (a.total_calorias_kcal) as sum, a.tiempo_inicio, p.total_calorias_kcal  FROM rutina_alimento a ,rutina_actividad p where a.tiempo_inicio=p.tiempo_inicio GROUP BY a.tiempo_inicio ORDER BY a.tiempo_inicio",null);
			
			int tamanio=0;
			if ( c.moveToFirst() ) {
				tamanio=c.getCount();
				if(tamanio>7){
					tamanio=7;
				}
			}
			if(tamanio!=0){
				Cursor c2 =  db.rawQuery("SELECT sum (a.total_calorias_kcal) as sum, a.tiempo_inicio, p.total_calorias_kcal  FROM rutina_alimento a ,rutina_actividad p where a.tiempo_inicio=p.tiempo_inicio GROUP BY a.tiempo_inicio ORDER BY a.tiempo_inicio   desc limit "+tamanio,null);
				int y[];
				String dias[];
		        y=new int[tamanio+1];
		        dias=new String[tamanio+1];
		        y[0]=0;
		        dias[0]="0";
		        Log.d("algo", "bueno2");
				for(int i=0;i<tamanio;i++){
					c2.moveToPosition(i);
					double ganadas=Double.parseDouble(c2.getString(0));
					double perdidas=Double.parseDouble(c2.getString(2));
					double netas=ganadas-perdidas;
					y[i+1]=(int) netas;
					dias[i+1]=c2.getString(1);
					Log.d("algo", "bueno");
				}
				
			
			
			
			
		    
			
		    
		        //int[] y = { 12, 13, 10, 14, 10, 12, 13};
			    /*int y[];
		        y=new int[7];
		        y[0]=12;
		        y[1]=13;
		        y[2]=10;
		        y[3]=14;
		        y[4]=10;
		        y[5]=12;
		        y[6]=13;*/
		       
				CategorySeries series = new CategorySeries("Dias de la semana");
				for (int i = 0; i < y.length; i++) {
					series.add("Bar " + (i+1), y[i]);
					//renderer.addTextLabel(i+1, dias[i]);
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
				mRenderer.setChartTitle("Historial semanal");
				mRenderer.setXTitle("Dias");
				mRenderer.setYTitle("Calorias netas");
				mRenderer.setAxesColor(Color.BLACK);
			    mRenderer.setLabelsColor(Color.BLACK);
			    // Customize bar 1
				XYSeriesRenderer renderer = new XYSeriesRenderer();
			    renderer.setDisplayChartValues(true);
			    renderer.setColor(Color.CYAN);
			    renderer.setChartValuesSpacing((float) 0.5);
			    renderer.setChartValuesTextSize(20);
			    
			    
			    //mRenderer.setYAxisMin(50);
			    //mRenderer.setYAxisMax(200);
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
					mRenderer.addTextLabel(i+1, dias[i]);
				}
			    
			    mRenderer.setXLabelsAlign(Align.RIGHT);
			    mRenderer.setXLabels(0);
			    mRenderer.setLabelsTextSize(20);
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
