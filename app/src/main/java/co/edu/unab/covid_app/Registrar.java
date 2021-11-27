package co.edu.unab.covid_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static co.edu.unab.covid_app.Config.usuario;

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
            RadioGroup rol = findViewById(R.id.radioGroup);
            Spinner comboBox = findViewById(R.id.spinner);


            if(view.getId()==R.id.btnRegistro){
                if (nombres.isEmpty()||apellidos.isEmpty()||emailReg.isEmpty()||contraseñaReg.isEmpty()||contraseñaReg1.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Debes ingresar todos los campos", Toast.LENGTH_SHORT).show();
                }else{
                    if (contraseñaReg.contentEquals(contraseñaReg1)){
                        Toast.makeText(getApplicationContext(), "Registro exitoso!!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Registrar.this, MainActivity.class);
                        startActivity(intent);
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

        String [] datos= {"Selecciona uno","Ingenieria", "Medicina", "Arquitectura","Derecho"};
        Spinner comboBox = findViewById(R.id.spinner);
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, datos);
        comboBox.setAdapter(adapterSpinner);
        //comboBox.setOnClickListener(eventoRegistro);

        //String email_1 = getIntent().getStringExtra(String.);
        ///////////para recuperar datos de identify users///////////////////////////
        /*
        TextView lbMsg1 = findViewById(R.id.txtEmailReg);
        lbMsg1.setText(String.valueOf(Config.usuario.getIdentify().get("email")).replace("\"", ""));
        TextView lbMsg2 = findViewById(R.id.txtNombres);
        lbMsg2.setText(String.valueOf(Config.usuario.getIdentify().get("name")).replace("\"", ""));//.replace("\"", "") para quitar comillas
        TextView lbMsg3 = findViewById(R.id.txtApellidos);
        lbMsg3.setText(String.valueOf(Config.usuario.getIdentify().get("surname")).replace("\"", ""));
        TextView lbMsg4 = findViewById(R.id.txtDocIdentidad);
        lbMsg4.setText(String.valueOf(Config.usuario.getIdentify().get("sub")));*/
        ///////////////////////////////////////////////////////////////////////////////
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