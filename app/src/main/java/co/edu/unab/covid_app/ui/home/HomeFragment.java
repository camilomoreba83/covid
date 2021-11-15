package co.edu.unab.covid_app.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import co.edu.unab.covid_app.R;
import co.edu.unab.covid_app.RegisterDiagnosticoActivity;
import co.edu.unab.covid_app.databinding.FragmentHomeBinding;
import co.edu.unab.covid_app.entities.Diagnostico;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    View vista;
    Button btnRegistrarDiagnostico;
    Diagnostico userDiagnostico;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();*/

        vista = inflater.inflate(R.layout.fragment_home,container,false);

        this.userDiagnostico = new Diagnostico(1,"Walter Giovanny","Cuadros Rincón"
                ,"Ingeniería","1234","user@email.com",
                "https://cdn-icons-png.flaticon.com/512/147/147144.png","7-11-2021","No hay registro");
        TextView userName = vista.findViewById(R.id.userName);
        TextView userProgram = vista.findViewById(R.id.userProgram);
        TextView userEstado = vista.findViewById(R.id.userEstado);
        ImageView imagenUser = vista.findViewById(R.id.imageUser);
        ImageView imagenEstado = vista.findViewById(R.id.imageEstado);
        Button btnRegistrarDiagnostico = vista.findViewById(R.id.btnRegistrarDiagnostico);
        userName.setText(userDiagnostico.getNombre()+" "+userDiagnostico.getApellido());
        userProgram.setText(userDiagnostico.getPrograma());
        switch (userDiagnostico.getEstado()){
            case "Habilitado":
                userEstado.setText(userDiagnostico.getEstado());
                Picasso.get().load(R.drawable.engineer).into(imagenUser);
                Picasso.get().load(R.drawable.checkbox).into(imagenEstado);
                btnRegistrarDiagnostico.setVisibility(View.GONE);
                break;
            case "No habilitado":
                userEstado.setText(userDiagnostico.getEstado());
                Picasso.get().load(R.drawable.engineer).into(imagenUser);
                Picasso.get().load(R.drawable.cancel).into(imagenEstado);
                btnRegistrarDiagnostico.setVisibility(View.GONE);
                break;
            default:
                userEstado.setText(userDiagnostico.getEstado());
                Picasso.get().load(R.drawable.engineer).into(imagenUser);
                Picasso.get().load(R.drawable.event).into(imagenEstado);
                btnRegistrarDiagnostico.setVisibility(View.VISIBLE);
                btnRegistrarDiagnostico.setOnClickListener(eventoClick);
                break;
        }

        return vista;
    }

    View.OnClickListener eventoClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId()==R.id.btnRegistrarDiagnostico) {
                Intent intent = new Intent(HomeFragment.this.getContext(), RegisterDiagnosticoActivity.class);
                intent.putExtra("idUser", userDiagnostico.getId());
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