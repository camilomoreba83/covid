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

import co.edu.unab.covid_app.entities.DiagnosticoUser;
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

    private ArrayList<DiagnosticoUser> getArrayItems(){
        ArrayList<DiagnosticoUser> listItems = new ArrayList<>();
        listItems.add(new DiagnosticoUser(1,"Carlos Andres","Sin Diagnostico","noi_mage"));
        listItems.add(new DiagnosticoUser(2,"Sara Estrella","Sin Diagnostico","noi_mage"));
        listItems.add(new DiagnosticoUser(3,"Ximena Hernandez","No habilitado","noi_mage"));
        listItems.add(new DiagnosticoUser(4,"Santiago Salcedo","No habilitado","noi_mage"));
        listItems.add(new DiagnosticoUser(5,"Paula Muñoz","Habilitado","noi_mage"));
        listItems.add(new DiagnosticoUser(6,"Daniel Rivas","Habilitado","noi_mage"));
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