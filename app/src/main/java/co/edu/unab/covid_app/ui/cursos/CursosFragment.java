package co.edu.unab.covid_app.ui.cursos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import co.edu.unab.covid_app.R;
import co.edu.unab.covid_app.ReportesActivity;
import co.edu.unab.covid_app.databinding.FragmentCursosBinding;
import co.edu.unab.covid_app.entities.Curso;
import co.edu.unab.covid_app.helpers.CursosAdapter;
import co.edu.unab.covid_app.helpers.ItemCursos;

public class CursosFragment extends Fragment {

    private FragmentCursosBinding binding;
    private View vista;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*CursosViewModel cursosViewModel =
                new ViewModelProvider(this).get(CursosViewModel.class);

        binding = FragmentCursosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();*/

        vista = inflater.inflate(R.layout.fragment_cursos,container,false);
        ListView listViewItems = vista.findViewById(R.id.lvCursos);
        CursosAdapter adaptador = new CursosAdapter(getArrayItems(), getContext());
        listViewItems.setAdapter(adaptador);
        listViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*int tag = (int) view.getTag();
                Toast.makeText(getActivity(),"item: "+position+" tag-->"+tag,Toast.LENGTH_LONG).show();*/
                Intent intent = new Intent(CursosFragment.this.getContext(), ReportesActivity.class);
                startActivity(intent);

            }
        });
        return vista;
    }

    private ArrayList<ItemCursos> getArrayItems(){
        ArrayList<ItemCursos> listItems = new ArrayList<>();
        listItems.add(
                new ItemCursos(
                        new Curso(1,"Curso 1","noimage"),
                        new Curso(2,"Curso 2","noimage"),
                        new Curso(3,"Curso 3","noimage")
                )
        );

        listItems.add(
                new ItemCursos(
                        new Curso(4,"Curso 4","noimage"),
                        new Curso(5,"Curso 5","noimage"),
                        new Curso(6,"Curso 6","noimage")
                )
        );
        return listItems;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}