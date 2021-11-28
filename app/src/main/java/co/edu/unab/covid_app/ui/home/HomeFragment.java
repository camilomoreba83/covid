package co.edu.unab.covid_app.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import co.edu.unab.covid_app.R;
import co.edu.unab.covid_app.RegisterDiagnosticoActivity;
import co.edu.unab.covid_app.databinding.FragmentHomeBinding;
import co.edu.unab.covid_app.entities.Diagnostico;
import co.edu.unab.covid_app.entities.DiagnosticoB;
import co.edu.unab.covid_app.http.AbcUniversitySingleton;
import co.edu.unab.covid_app.http.Config;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private String token = Config.usuario.getToken();

    View vista;
    Button btnRegistrarDiagnostico;
    DiagnosticoB userDiagnostico;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //queue = Volley.newRequestQueue(getActivity());
        Log.i("Token", "onCreateView: "+token);
        vista = inflater.inflate(R.layout.fragment_home,container,false);
        if(Objects.isNull(Config.Diagnostico)) {
            StringRequest solicitud = new StringRequest(
                    Request.Method.GET,
                    Config.URL_Report + "1",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject obj = new JSONObject(response);

                                JSONObject data = obj.getJSONObject("data");
                                Gson gson = new Gson();
                                Type tipo = new TypeToken<DiagnosticoB>() {
                                }.getType();
                                Config.Diagnostico = gson.fromJson(String.valueOf(data), tipo);
                                Log.d("respuesta", "" + Config.Diagnostico.getName());
                                cargarVista(Config.Diagnostico);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("error", error.getMessage());
                }
            }
            ) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Authorization", token);
                    return headers;
                }
            };
            //queue.add(solicitud);
            AbcUniversitySingleton.getInstance(getActivity()).addQueue(solicitud);
        }else{
            Log.i("if_sentece", "onCreateView: entrooo al else");
            cargarVista(Config.Diagnostico);
        }
        return vista;
    }
    public void cargarVista(DiagnosticoB userDiagnosticoVista){
        TextView userName = vista.findViewById(R.id.userName);
        TextView userProgram = vista.findViewById(R.id.userProgram);
        TextView userEstado = vista.findViewById(R.id.userEstado);
        ImageView imagenUser = vista.findViewById(R.id.imageUser);
        ImageView imagenEstado = vista.findViewById(R.id.imageEstado);
        Button btnRegistrarDiagnostico = vista.findViewById(R.id.btnRegistrarDiagnostico);
        Button btnEditarReporte = vista.findViewById(R.id.btn_editar);
        userName.setText(userDiagnosticoVista.getName()+" "+userDiagnosticoVista.getSurname());
        userProgram.setText(userDiagnosticoVista.getPrograma());
        switch (userDiagnosticoVista.getState()){
            case "Habilitado":
                userEstado.setText(userDiagnosticoVista.getState());
                Picasso.get().load(R.drawable.engineer).into(imagenUser);
                Picasso.get().load(R.drawable.checkbox).into(imagenEstado);
                btnRegistrarDiagnostico.setVisibility(View.GONE);
                btnEditarReporte.setVisibility(View.GONE);
                break;
            case "NO HABILITADO":
                userEstado.setText(userDiagnosticoVista.getState());
                Picasso.get().load(R.drawable.engineer).into(imagenUser);
                Picasso.get().load(R.drawable.cancel).into(imagenEstado);
                btnRegistrarDiagnostico.setVisibility(View.GONE);
                btnEditarReporte.setVisibility(View.VISIBLE);
                break;
            default:
                userEstado.setText(userDiagnosticoVista.getState());
                Picasso.get().load(R.drawable.engineer).into(imagenUser);
                Picasso.get().load(R.drawable.event).into(imagenEstado);
                btnRegistrarDiagnostico.setVisibility(View.VISIBLE);
                btnRegistrarDiagnostico.setOnClickListener(eventoClick);
                btnEditarReporte.setVisibility(View.GONE);
                break;
        }

    }
    View.OnClickListener eventoClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId()==R.id.btnRegistrarDiagnostico) {
                Intent intent = new Intent(HomeFragment.this.getContext(), RegisterDiagnosticoActivity.class);
                intent.putExtra("idUser", Config.Diagnostico.getId_user());
                startActivity(intent);
            }
            if(view.getId()==R.id.btn_editar){
                Intent intent = new Intent(HomeFragment.this.getContext(), RegisterDiagnosticoActivity.class);
                intent.putExtra("idUser", Config.Diagnostico.getId_user());
                intent.putExtra("editarReporte",true);
                startActivity(intent);
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}