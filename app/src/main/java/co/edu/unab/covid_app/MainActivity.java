package co.edu.unab.covid_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                if (pass.isEmpty() || email.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Debes ingresar tu email y contraseña", Toast.LENGTH_SHORT).show();
                }else if ((email.contentEquals(email1))&&(pass.contentEquals(pass1))){
                    Toast.makeText(getApplicationContext(), "Bienvenid@s!!!", Toast.LENGTH_SHORT).show();

                    //Setear los inputs
                    txtContraseña.setText(null);
                    txtEmail.setText(null);

                    Intent intent= new Intent(MainActivity.this, NavegacionMenuActivity.class);
                    intent.putExtra("email",email);
                    startActivity(intent); //Aqui enviar a la actividad HOME
                }else{
                    Toast.makeText(getApplicationContext(), "Email o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
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