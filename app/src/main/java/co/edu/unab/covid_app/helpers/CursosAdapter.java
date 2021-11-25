package co.edu.unab.covid_app.helpers;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


import java.util.List;

import co.edu.unab.covid_app.R;
import co.edu.unab.covid_app.entities.Curso;

public class CursosAdapter extends RecyclerView.Adapter<CursosAdapter.ViewHolder>{

    private List<Curso> cursos;
    private CursoAdapterListener listener;

    public CursosAdapter(List<Curso> cursos, CursoAdapterListener listener) {
        this.cursos = cursos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cursos,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Curso curso = cursos.get(position);

        holder.lblNameCurso1.setText(curso.getNameCurso());
        Picasso.get().load(curso.getImage())
                .placeholder(R.drawable.ic_curso)
                .error(R.drawable.ic_curso)
                //.resize(20,20)
                .into(holder.imgCurso1);
    }

    @Override
    public int getItemCount() {
        int i=0;
        if (this.cursos.size()>0) i = this.cursos.size();
        return i;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgCurso1;
        TextView lblNameCurso1;


        public ViewHolder(View view){
            super(view);
            imgCurso1 = view.findViewById(R.id.imgCurso1);
            lblNameCurso1 = view.findViewById(R.id.lblNameCurso1);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.OnItemClicked(cursos.get(getAdapterPosition()).getId());
        }
    }

    public interface CursoAdapterListener{
        void OnItemClicked(int id);
    }
}
