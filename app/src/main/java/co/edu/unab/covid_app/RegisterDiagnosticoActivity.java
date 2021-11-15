package co.edu.unab.covid_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.FrameMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class RegisterDiagnosticoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_diagnostico);

        String[] datosPreguntaUno = {"Si","No"};
        String[] datosPreguntaDos = {"Fiebre de 38°C o más","Dificultad para respirar","Dolor de garganta",
                "Disminución del sentido del olfato","Tos","Fatiga o decaimiento","Disminución del sentido del gusto","Ninguno"};
        ArrayAdapter<String> adapterUno = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_single_choice,datosPreguntaUno);
        ListView opcionespruno = findViewById(R.id.pregunta_uno);
        opcionespruno.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        opcionespruno.setAdapter(adapterUno);

        ArrayAdapter<String> adapterDos = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_multiple_choice,datosPreguntaDos);
        ListView opcionesprdos = findViewById(R.id.pregunta_dos);
        opcionesprdos.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        opcionesprdos.setAdapter(adapterDos);
    }

    public void register(View view) {
        ListView opcionesprdos = findViewById(R.id.pregunta_dos);
        SparseBooleanArray valoresDos = opcionesprdos.getCheckedItemPositions();
        ListView opcionespruno = findViewById(R.id.pregunta_uno);
        SparseBooleanArray valoresUno = opcionespruno.getCheckedItemPositions();
        //TO DO ACCIÓN DE REGISTRAR EN BACKEND
        Toast.makeText(getApplicationContext(), valoresUno.size()+""+valoresDos.size(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterDiagnosticoActivity.this, NavegacionMenuActivity.class);
        startActivity(intent);
    }

    public void cancelar(View view){
        Intent intent = new Intent(RegisterDiagnosticoActivity.this, NavegacionMenuActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void finish() {
        //super.finish();
    }

}