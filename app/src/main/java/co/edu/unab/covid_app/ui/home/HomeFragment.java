package co.edu.unab.covid_app.ui.home;

import android.content.Intent;
import android.os.Bundle;
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

import co.edu.unab.covid_app.R;
import co.edu.unab.covid_app.RegisterDiagnosticoActivity;
import co.edu.unab.covid_app.databinding.FragmentHomeBinding;
import co.edu.unab.covid_app.entities.Diagnostico;
import co.edu.unab.covid_app.entities.DiagnosticoB;
import co.edu.unab.covid_app.http.AbcUniversitySingleton;
import co.edu.unab.covid_app.http.Config;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjEsImVtYWlsIjoid2FsdGVyQG1pc2lvbnRpYy5jb20iLCJuYW1lIjoiV2FsdGVyIEdpb3Zhbm55Iiwic3VybmFtZSI6IkN1YWRyb3MgUmluY1x1MDBmM24iLCJpYXQiOjE2Mzc3MDYzODQsImV4cCI6MTYzODMxMTE4NH0.LxtGhZoftRY0tuVy85IzfYE9Vu8Oo4c5KoYXsWU6RuY";
   // private RequestQueue queue;
    /*HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();*/


    View vista;
    Button btnRegistrarDiagnostico;
    DiagnosticoB userDiagnostico;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //queue = Volley.newRequestQueue(getActivity());
        vista = inflater.inflate(R.layout.fragment_home,container,false);
        StringRequest solicitud = new StringRequest(
                Request.Method.GET,
                Config.URL_ReportByIdUser+"1",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);

                            JSONObject data = obj.getJSONObject("data");
                            Gson gson = new Gson();
                            Type tipo = new TypeToken<DiagnosticoB>() {}.getType();
                            userDiagnostico = gson.fromJson(String.valueOf(data),tipo);
                            cargarVista(userDiagnostico);
                            Log.d("respuesta", ""+userDiagnostico.getName());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error",error.getMessage());
            }
        }
        )/*{
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> values = new HashMap<>();
                values.put("variable","valor");
                return super.getParams();
            }
        };*/
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<>();
                headers.put("Authorization",token);
                return headers;
            }
        };
        //queue.add(solicitud);
        AbcUniversitySingleton.getInstance(getActivity()).addQueue(solicitud);

        return vista;
    }
    public void cargarVista(DiagnosticoB userDiagnosticoVista){
        TextView userName = vista.findViewById(R.id.userName);
        TextView userProgram = vista.findViewById(R.id.userProgram);
        TextView userEstado = vista.findViewById(R.id.userEstado);
        ImageView imagenUser = vista.findViewById(R.id.imageUser);
        ImageView imagenEstado = vista.findViewById(R.id.imageEstado);
        Button btnRegistrarDiagnostico = vista.findViewById(R.id.btnRegistrarDiagnostico);
        userName.setText(userDiagnosticoVista.getName()+" "+userDiagnosticoVista.getSurname());
        userProgram.setText(this.userDiagnostico.getPrograma());
        switch (this.userDiagnostico.getState()){
            case "Habilitado":
                userEstado.setText(userDiagnosticoVista.getState());
                Picasso.get().load(R.drawable.engineer).into(imagenUser);
                Picasso.get().load(R.drawable.checkbox).into(imagenEstado);
                btnRegistrarDiagnostico.setVisibility(View.GONE);
                break;
            case "NO HABILITADO":
                userEstado.setText(userDiagnosticoVista.getState());
                Picasso.get().load(R.drawable.engineer).into(imagenUser);
                Picasso.get().load(R.drawable.cancel).into(imagenEstado);
                btnRegistrarDiagnostico.setVisibility(View.GONE);
                break;
            default:
                userEstado.setText(userDiagnosticoVista.getState());
                Picasso.get().load(R.drawable.engineer).into(imagenUser);
                Picasso.get().load(R.drawable.event).into(imagenEstado);
                btnRegistrarDiagnostico.setVisibility(View.VISIBLE);
                btnRegistrarDiagnostico.setOnClickListener(eventoClick);
                break;
        }

    }
    View.OnClickListener eventoClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId()==R.id.btnRegistrarDiagnostico) {
                Intent intent = new Intent(HomeFragment.this.getContext(), RegisterDiagnosticoActivity.class);
                intent.putExtra("idUser", userDiagnostico.getId_user());
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