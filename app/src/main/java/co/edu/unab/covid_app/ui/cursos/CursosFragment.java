package co.edu.unab.covid_app.ui.cursos;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import co.edu.unab.covid_app.R;
import co.edu.unab.covid_app.ReportesActivity;
import co.edu.unab.covid_app.databinding.FragmentCursosBinding;
import co.edu.unab.covid_app.entities.Curso;
import co.edu.unab.covid_app.entities.Diagnostico;
import co.edu.unab.covid_app.helpers.CursosAdapter;
import co.edu.unab.covid_app.http.AbcUniversitySingleton;
import co.edu.unab.covid_app.http.Config;

public class CursosFragment extends Fragment implements CursosAdapter.CursoAdapterListener{

    private FragmentCursosBinding binding;

    private RecyclerView cursoRecycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String token;
    private View vista;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*CursosViewModel cursosViewModel =
                new ViewModelProvider(this).get(CursosViewModel.class);

        binding = FragmentCursosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();*/

        vista = inflater.inflate(R.layout.fragment_cursos,container,false);
        token = Config.usuario.getToken();
        cursoRecycler = vista.findViewById(R.id.cursoRecycler);
        getCursos();

        return vista;
    }

    private void getCursos(){

        StringRequest getCursos = new StringRequest(
                Request.Method.GET,
                Config.URL_PROGRAMS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject objResponse = new JSONObject(response);

                            if(objResponse.getString("status").equals("ok")) {
                                JSONArray programs = objResponse.getJSONArray("programs");

                                Gson gson = new Gson();
                                Type tipo = new TypeToken<ArrayList<Curso>>() {}.getType();

                                ArrayList<Curso> cursos = gson.fromJson(programs.toString(), tipo);

                                adapter = new CursosAdapter(cursos, CursosFragment.this);
                                cursoRecycler.setAdapter(adapter);

                                cursoRecycler.setHasFixedSize(true);
                                layoutManager = new GridLayoutManager(getContext(), 3);
                                cursoRecycler.setLayoutManager(layoutManager);
                            }
                        }catch ( JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CursosFragment.this.getContext().getApplicationContext(),"Error al cargar listado de cursos",Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization",token);
                return headers;
            }
        };
        AbcUniversitySingleton.getInstance(getActivity()).addQueue(getCursos);
    }

    private void getDiagnosticoByProgram(int idCurso){
        StringRequest diagnosticos = new StringRequest(
                Request.Method.GET,
                Config.URL_DIAGNOSTICOS_PROGRAM+idCurso,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject objResponse = new JSONObject(response);

                            if(objResponse.getString("status").equals("ok")) {
                                JSONArray diagnosticos = objResponse.getJSONArray("estudents");
                                Gson gson = new Gson();
                                Type tipo = new TypeToken<ArrayList<Diagnostico>>() {}.getType();

                                ArrayList<Diagnostico>  data = gson.fromJson(diagnosticos.toString(), tipo);

                                Intent i = new Intent(CursosFragment.this.getContext(), ReportesActivity.class);
                                i.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) data);
                                startActivity(i);
                            }
                        }catch ( JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),"Error al cargar listado de diagnosticos",Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization",token);
                return headers;
            }
        };
        AbcUniversitySingleton.getInstance(getActivity()).addQueue(diagnosticos);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void OnItemClicked(int id) {
        getDiagnosticoByProgram(id);
        /*Intent i = new Intent(CursosFragment.this.getContext(), ReportesActivity.class);
        i.putExtra("id_curso",id);
        startActivity(i);
        Log.d("id_curso","->"+id);*/
    }
}