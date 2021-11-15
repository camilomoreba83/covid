package co.edu.unab.covid_app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

import co.edu.unab.covid_app.entities.Diagnostico;
import co.edu.unab.covid_app.helpers.DiagnosticoAdapter;

public class ReportesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);

        ListView listViewItems = findViewById(R.id.lvItems);
        DiagnosticoAdapter adaptador = new DiagnosticoAdapter(getArrayItems(), getApplicationContext());
        listViewItems.setAdapter(adaptador);

        PieChart pieChartH;
        PieChart pieChartNH;
        PieChart pieChartSD;

        pieChartH=findViewById(R.id.pieChartH);
        pieChartNH=findViewById(R.id.pieChartNH);
        pieChartSD=findViewById(R.id.pieChartSD);

        configPieChart(
                new int[]{Color.rgb(127,225,173),Color.LTGRAY,Color.LTGRAY},
                dataValues(60,30,10),
                "60%",
                pieChartH
        );
        configPieChart(
                new int[]{Color.rgb(248,95,106),Color.LTGRAY,Color.LTGRAY},
                dataValues(30,10,60),
                "30%",
                pieChartNH);
        configPieChart(
                new int[]{Color.rgb(95,106,248),Color.LTGRAY,Color.LTGRAY},
                dataValues(10,60,30),
                "10%",
                pieChartSD
        );
    }

    private ArrayList<Diagnostico> getArrayItems(){
        ArrayList<Diagnostico> listItems = new ArrayList<>();

        listItems.add(new Diagnostico(1,"Carlos Andres","Hernandez", "Ingenieria", "1234567890","carlos@andres.com","noi_mage","15/11/2021","Sin Diagnostico"));
        listItems.add(new Diagnostico(2,"Maria Camila","Molina", "Ingenieria", "1234567890","maria@camila.com","noi_mage","15/11/2021","Sin Diagnostico"));
        listItems.add(new Diagnostico(3,"Paula Muñoz","Sanchez", "Ingenieria", "1234567890","paula@muñoz.com","noi_mage","15/11/2021","No habilitado"));
        listItems.add(new Diagnostico(4,"Mario Andres","Solarte", "Ingenieria", "1234567890","mario@andres.com","noi_mage","15/11/2021","No habilitado"));
        listItems.add(new Diagnostico(3,"Cesar Armando","Belarcazar", "Ingenieria", "1234567890","cesar@armando.com","noi_mage","15/11/2021","Habilitado"));
        listItems.add(new Diagnostico(4,"Sara Elizabeth","Angulo", "Ingenieria", "1234567890","sara@angulo.com","noi_mage","15/11/2021","Habilitado"));

        return listItems;
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
}