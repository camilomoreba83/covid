package co.edu.unab.covid_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.FrameMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import co.edu.unab.covid_app.entities.DiagnosticoB;
import co.edu.unab.covid_app.entities.ReportRegister;
import co.edu.unab.covid_app.http.AbcUniversitySingleton;
import co.edu.unab.covid_app.http.Config;

public class RegisterDiagnosticoActivity extends AppCompatActivity {
    private String token = Config.usuario.getToken();

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
        String pruno = "";
        for(int i = 0; i < valoresUno.size(); i++) {
            if(valoresUno.valueAt(i)) {
            int key = valoresUno.keyAt(i);
            boolean value = valoresUno.get(key);
            if (value) {
                pruno = (valoresUno.keyAt(i)==0)?"a":"b";
            } }}
        String prdos = "";
        String SEPARADOR = "";
        StringBuilder cadena= new StringBuilder();
        String opcionesLetras[]= {"a","b","c","d","e","f","g","h"};
        for(int i = 0; i < valoresDos.size(); i++) {
            if(valoresDos.valueAt(i)) {
                int key = valoresDos.keyAt(i);
                boolean value = valoresDos.get(key);
                if (value) {
                    cadena.append(SEPARADOR);
                    prdos = opcionesLetras[valoresDos.keyAt(i)];
                    cadena.append(prdos);
                    SEPARADOR = ",";
                } }}
        if(pruno == "" && prdos == ""){
            Intent intent = new Intent(RegisterDiagnosticoActivity.this, NavegacionMenuActivity.class);
            startActivity(intent);
        }
        ReportRegister report = new ReportRegister(pruno,prdos);
        StringRequest solicitud = new StringRequest(
                Request.Method.POST,
                Config.URL_Report_Post,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(obj.getString("status").equals("ok")){
                                Log.d("respuesta", "entro al if");
                                Intent intent = new Intent(RegisterDiagnosticoActivity.this, NavegacionMenuActivity.class);
                                startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Intent intent = new Intent(RegisterDiagnosticoActivity.this, NavegacionMenuActivity.class);
                startActivity(intent);
                finish();
            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> values = new HashMap<>();
                Gson gson = new Gson();
                String json = gson.toJson(report);
                Log.i("VARIABLES", "getParams: "+json);
                values.put("json",json);
                return values;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<>();
                headers.put("Authorization",token);
                return headers;
            }
        };

        AbcUniversitySingleton.getInstance(this).addQueue(solicitud);



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