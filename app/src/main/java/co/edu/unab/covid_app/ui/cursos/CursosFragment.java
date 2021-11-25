package co.edu.unab.covid_app.ui.cursos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import co.edu.unab.covid_app.R;
import co.edu.unab.covid_app.ReportesActivity;
import co.edu.unab.covid_app.databinding.FragmentCursosBinding;
import co.edu.unab.covid_app.entities.Curso;
import co.edu.unab.covid_app.helpers.CursosAdapter;

public class CursosFragment extends Fragment implements CursosAdapter.CursoAdapterListener{

    private FragmentCursosBinding binding;

    private List<Curso> cursos;
    private RecyclerView cursoRecycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private View vista;
    public View.OnClickListener listener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*CursosViewModel cursosViewModel =
                new ViewModelProvider(this).get(CursosViewModel.class);

        binding = FragmentCursosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();*/

        vista = inflater.inflate(R.layout.fragment_cursos,container,false);

        cursoRecycler = vista.findViewById(R.id.cursoRecycler);

        cursos = getArrayItems();
        adapter = new CursosAdapter(cursos, CursosFragment.this);
        cursoRecycler.setAdapter(adapter);


        cursoRecycler.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(),3);
        cursoRecycler.setLayoutManager(layoutManager);

        return vista;
    }

    private ArrayList<Curso> getArrayItems(){
        ArrayList<Curso> listItems = new ArrayList<>();
        listItems.add(new Curso(1,"Curso 1","noimage"));
        listItems.add(new Curso(2,"Curso 2","noimage"));
        listItems.add(new Curso(3,"Curso 3","noimage"));
        listItems.add(new Curso(4,"Curso 4","noimage"));
        listItems.add(new Curso(5,"Curso 5","noimage"));
        listItems.add(new Curso(6,"Curso 6","noimage"));
        return listItems;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void OnItemClicked(int id) {
        Intent i = new Intent(CursosFragment.this.getContext(), ReportesActivity.class);
        i.putExtra("id",id);
        startActivity(i);
        Log.d("ID","->"+id);
    }
}