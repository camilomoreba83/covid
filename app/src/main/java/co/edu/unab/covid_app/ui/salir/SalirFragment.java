package co.edu.unab.covid_app.ui.salir;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import co.edu.unab.covid_app.MainActivity;
import co.edu.unab.covid_app.RegisterDiagnosticoActivity;
import co.edu.unab.covid_app.databinding.FragmentSalirBinding;
import co.edu.unab.covid_app.ui.home.HomeFragment;

public class SalirFragment extends Fragment {

    private FragmentSalirBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //System.exit(0);

        Intent intent = new Intent(SalirFragment.this.getContext(), MainActivity.class);
        startActivity(intent);


        SalirViewModel salirViewModel =
                new ViewModelProvider(this).get(SalirViewModel.class);

        binding = FragmentSalirBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}