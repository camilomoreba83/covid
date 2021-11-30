package co.edu.unab.covid_app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import co.edu.unab.covid_app.entities.Diagnostico;
import co.edu.unab.covid_app.helpers.DiagnosticoAdapter;
import co.edu.unab.covid_app.http.AbcUniversitySingleton;
import co.edu.unab.covid_app.http.Config;


public class ReportesActivity extends AppCompatActivity {

    private String token;
    private ListView listViewItems;
    private PieChart pieChartH;
    private PieChart pieChartNH;
    private PieChart pieChartSD;
    private int nh=0, hb=0, sd=0;
    private String pnh, phb, psd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);

        token = Config.usuario.getToken();
        //idCurso = getIntent().getIntExtra("id_curso",0);
        ArrayList<Diagnostico> data = getIntent().getParcelableArrayListExtra("data");
        listViewItems = findViewById(R.id.lvItems);
        pieChartH=findViewById(R.id.pieChartH);
        pieChartNH=findViewById(R.id.pieChartNH);
        pieChartSD=findViewById(R.id.pieChartSD);

        DiagnosticoAdapter adaptador = new DiagnosticoAdapter(data, getApplicationContext());
        listViewItems.setAdapter(adaptador);

        nh = (int) data.stream().filter(diagnostico -> diagnostico.getEstado().equals("NO HABILITADO")).count();
        hb = (int) data.stream().filter(diagnostico -> diagnostico.getEstado().equals("HABILITADO")).count();
        sd = (int) data.stream().filter(diagnostico -> diagnostico.getEstado().equals("SIN REGISTRO")).count();

        setPorcentajes();
        graficar();
    }

    private ArrayList<PieEntry> dataValues(int principal, int val1, int val2){
        ArrayList<PieEntry> dataVals = new ArrayList<>();

        dataVals.add(new PieEntry(principal,""));
        dataVals.add(new PieEntry(val1,""));
        dataVals.add(new PieEntry(val2,""));
        return dataVals;
    }

    private void configPieChart(int[] colors, ArrayList<PieEntry> dataValues, String porcentaje, PieChart pieChart){
        PieDataSet pieDataSet = new PieDataSet(dataValues,"");
        pieDataSet.setColors(colors);
        pieDataSet.setDrawValues(false);
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);

        pieChart.setDrawEntryLabels(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText(porcentaje);
        pieChart.setCenterTextSize(20);
        pieChart.setCenterTextColor(colors[0]);
        pieChart.setHoleRadius(90);
    }

    private void graficar(){

        configPieChart(
                new int[]{Color.rgb(127,225,173),Color.LTGRAY,Color.LTGRAY},
                dataValues(hb,nh,sd),
                phb+"%",
                pieChartH
        );
        configPieChart(
                new int[]{Color.rgb(248,95,106),Color.LTGRAY,Color.LTGRAY},
                dataValues(nh,sd,hb),
                pnh+"%",
                pieChartNH);
        configPieChart(
                new int[]{Color.rgb(95,106,248),Color.LTGRAY,Color.LTGRAY},
                dataValues(sd,hb,nh),
                psd+"%",
                pieChartSD
        );
    }

    private void setPorcentajes(){
        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(2); //Define 2 decimales.

        float total = sd+nh+hb;
        phb = format.format((hb/total)*100);
        pnh = format.format((nh/total)*100);
        psd = format.format((sd/total)*100);
    }
}