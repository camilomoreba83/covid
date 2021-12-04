package co.edu.unab.covid_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    View.OnClickListener eventoMain = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            EditText txtContraseña = findViewById(R.id.txtContraseña);
            EditText txtEmail = findViewById(R.id.txtEmail);
            String email = txtEmail.getText().toString();
            String pass = txtContraseña.getText().toString();
            String pass1 = "1234";
            String email1 = "covid@gmail.com";
            if(view.getId()==R.id.btnIniciarSesion){
                JSONObject json=new JSONObject();
                try {
                    json.put("email",email);
                    json.put("password",pass);
                    json.put("getToken","false");
                    Log.d("json= ", String.valueOf(json));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                StringRequest solicitud = new StringRequest(
                        Request.Method.POST, Config.URL_LOGIN,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("respuesta", response);
                                Gson gson = new Gson();
                                Type tipo = new TypeToken<Usuario>(){}.getType();
                                Config.usuario = gson.fromJson(response, tipo);
                                Log.d("Usuario=", Config.usuario.toString());

                                if (pass.isEmpty() || email.isEmpty()){
                                    Toast.makeText(getApplicationContext(), "Debes ingresar tu email y contraseña", Toast.LENGTH_SHORT).show();
                                }else if ("ok".equals(Config.usuario.getStatus().toString())){
                                    JsonObject datos = Config.usuario.getIdentify().getAsJsonObject();
                                    String nombre = String.valueOf(Config.usuario.getIdentify().get("name")).replace("\"", "");
                                    Toast.makeText(getApplicationContext(), "Bienvenid@ "+nombre, Toast.LENGTH_SHORT).show();
                                    txtContraseña.setText(null);
                                    txtEmail.setText(null);
                                    Intent intent= new Intent(MainActivity.this, NavegacionMenuActivity.class);//NavegacionMenuActivity.class);
                                    startActivity(intent); //Aqui enviar a la actividad HOME
                                }else{
                                    Toast.makeText(getApplicationContext(), "Email o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Log.e("error", error.getMessage());
                        Log.e("error", "error de conexión");
                    }
                }
                ){
                    ///se envia al servidor el json seguir viendo semana 5 19 de nov min 40
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> values = new HashMap<>();
                        values.put("json", String.valueOf(json));
                        return values;
                    }
                };

                AbcUniversitySingleton.getInstance(getApplicationContext()).addQueue(solicitud);
            }else if (view.getId()==R.id.txtRegistrarse){
                Toast.makeText(getApplicationContext(), "Ingresa tus datos", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(MainActivity.this, Registrar.class);
                startActivity(intent);
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText txtContraseña = findViewById(R.id.txtContraseña);
        EditText txtEmail = findViewById(R.id.txtEmail);

        Button btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        TextView txtRegistrarse = findViewById(R.id.txtRegistrarse);

        txtRegistrarse.setOnClickListener(eventoMain);
        btnIniciarSesion.setOnClickListener(eventoMain);

    }


    @Override
    public void onClick(View view) {

        //Toast.makeText(getApplicationContext(), "Bienvenid@s todos", Toast.LENGTH_SHORT).show();
        //Intent intent= new Intent(MainActivity.this, Registro.class);
        //startActivity(intent);
    }

    @Override
    public void finish() {
        //super.finish();
    }
}