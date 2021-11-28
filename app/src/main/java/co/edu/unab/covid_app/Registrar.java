package co.edu.unab.covid_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import co.edu.unab.covid_app.entities.Usuario;
import co.edu.unab.covid_app.http.AbcUniversitySingleton;
import co.edu.unab.covid_app.http.Config;

public class Registrar extends AppCompatActivity implements View.OnClickListener{

    View.OnClickListener eventoRegistro = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            EditText txtNombres = findViewById(R.id.txtNombres);
            EditText txtApellidos = findViewById(R.id.txtApellidos);
            EditText txtEmailReg = findViewById(R.id.txtEmailReg);
            EditText txtDocIdentidad = findViewById(R.id.txtDocIdentidad);
            EditText txtContraseñaReg = findViewById(R.id.txtContraseñaReg);
            EditText txtContraseñaReg1 = findViewById(R.id.txtContraseñaReg1);
            String nombres = txtNombres.getText().toString();
            String apellidos = txtApellidos.getText().toString();
            String emailReg = txtEmailReg.getText().toString();
            String documento = txtDocIdentidad.getText().toString();
            String contraseñaReg = txtContraseñaReg.getText().toString();
            String contraseñaReg1 = txtContraseñaReg1.getText().toString();
            RadioButton rdbProfesor = findViewById(R.id.rdbProfesor);
            RadioButton rdbEstudiante = findViewById(R.id.rdbEstudiante);
            RadioGroup roles = findViewById(R.id.radioGroup);
            Spinner comboBox = findViewById(R.id.spinner);
            int id_rol = 0;
            int id_program = 0;

            if(view.getId()==R.id.btnRegistro){
                if (nombres.isEmpty()||apellidos.isEmpty()||emailReg.isEmpty()||contraseñaReg.isEmpty()||contraseñaReg1.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Debes ingresar todos los campos", Toast.LENGTH_SHORT).show();
                }else{
                    if (contraseñaReg.contentEquals(contraseñaReg1)){
                        if(comboBox.getSelectedItemPosition()!=0 && (rdbEstudiante.isChecked()||rdbProfesor.isChecked())){
                            Toast.makeText(getApplicationContext(), "Registro exitoso!!!", Toast.LENGTH_SHORT).show();
                            //id_program int(255) e id_rol int(255),
                            //{"name":"Pedro Pablo","surname":"Perez Prieto",
                            // "email":"pppp@email.com","nit":"12345","imageUrl":"imagen.png",
                            // "idProgram":"1","idRol":"1","password":"123"}
                            ///////rol
                            String contraseña_verificada = contraseñaReg1;
                            if (rdbEstudiante.isChecked()==true){
                                id_rol = 1;
                            }else if (rdbProfesor.isChecked()==true){
                                id_rol = 2;
                            }
                            ////////programa
                            id_program = comboBox.getSelectedItemPosition();

                            JSONObject json=new JSONObject();
                            try {
                                json.put("name",nombres);
                                json.put("surname",apellidos);
                                json.put("email",emailReg);
                                json.put("nit",documento);
                                json.put("imageUrl","null");
                                json.put("idProgram",id_program);
                                json.put("idRol",id_rol);
                                json.put("password",contraseña_verificada);
                                Log.d("json= ", String.valueOf(json));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            StringRequest solicitud = new StringRequest(
                                    Request.Method.POST, Config.URL_REGISTER,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Log.d("respuesta", response);
                                            Gson gson = new Gson();
                                            Type tipo = new TypeToken<Usuario>(){}.getType();
                                            Config.usuario = gson.fromJson(response, tipo);
                                            Log.d("Usuario=", Config.usuario.toString());
                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.e("error", error.getMessage());
                                }
                            }
                            ){
                                @Nullable
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String, String> values = new HashMap<>();
                                    values.put("json", String.valueOf(json));
                                    return values;
                                }
                            };

                            AbcUniversitySingleton.getInstance(getApplicationContext()).addQueue(solicitud);

                            Intent intent = new Intent(Registrar.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(), "Elige quién eres y/o programa al que perteneces", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "La contraseña no coincide", Toast.LENGTH_SHORT).show();
                    }
                }
            }else if (view.getId()==R.id.txtIniciarSesionReg){
                Toast.makeText(getApplicationContext(), "Hola nuevamente!!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Registrar.this, MainActivity.class);
                startActivity(intent);
                finish();
            }/*else if (view.getId()==R.id.button1){
                String text2="";
                if (rdbEstudiante.isChecked()==true){
                    text2="Estudiante";
                }else if (rdbProfesor.isChecked()==true){
                    text2="Profesor";
                }
                String text = (String) comboBox.getSelectedItem(); //adapterSpinner.getSelectedItem().toString();
                TextView lbMsg = findViewById(R.id.lbMsg);
                lbMsg.setText(text2+" "+text);
            }*/
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        Button btnRegistro = findViewById(R.id.btnRegistro);
        TextView txtIniciarSesionReg = findViewById(R.id.txtIniciarSesionReg);
        txtIniciarSesionReg.setOnClickListener(eventoRegistro);
        btnRegistro.setOnClickListener(eventoRegistro);

        String [] datos= {"Selecciona uno","Ingeniería de Software", "Ingeniería Mecánica", "Ingeniería Electrónica",
                "Licenciatura en Matemáticas","Psicología"};
        Spinner comboBox = findViewById(R.id.spinner);
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, datos);
        comboBox.setAdapter(adapterSpinner);
        //comboBox.setOnClickListener(eventoRegistro);

        String user = getIntent().getStringExtra("email");
        TextView lbMsg1 = findViewById(R.id.txtEmailReg);
        lbMsg1.setText(user);



        /*Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(eventoRegistro);*/
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void finish() {
        //super.finish();
    }
}