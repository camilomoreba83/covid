package co.edu.unab.covid_app.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import co.edu.unab.covid_app.R;

public class CursosAdapter extends BaseAdapter {
    private ArrayList<ItemCursos> listaItems;
    private Context context;

    public CursosAdapter(ArrayList<ItemCursos> listaItems, Context context) {
        this.listaItems = listaItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listaItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemCursos Item = (ItemCursos) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item_cursos,null);

        ImageView imgCurso1 = convertView.findViewById(R.id.imgCurso1);
        TextView lblNameCurso1 = convertView.findViewById(R.id.lblNameCurso1);
        lblNameCurso1.setText(Item.getCurso1().getNameCurso());
        Picasso.get().load(Item.getCurso1().getImage())
                .placeholder(R.drawable.ic_curso)
                .error(R.drawable.ic_curso)
                //.resize(20,20)
                .into(imgCurso1);

        ImageView imgCurso2 = convertView.findViewById(R.id.imgCurso2);
        TextView lblNameCurso2 = convertView.findViewById(R.id.lblNameCurso2);
        lblNameCurso2.setText(Item.getCurso2().getNameCurso());
        Picasso.get().load(Item.getCurso2().getImage())
                .placeholder(R.drawable.ic_curso)
                .error(R.drawable.ic_curso)
                //.resize(20,20)
                .into(imgCurso2);


        ImageView imgCurso3 = convertView.findViewById(R.id.imgCurso3);
        TextView lblNameCurso3 = convertView.findViewById(R.id.lblNameCurso3);
        lblNameCurso3.setText(Item.getCurso3().getNameCurso());
        Picasso.get().load(Item.getCurso3().getImage())
                .placeholder(R.drawable.ic_curso)
                .error(R.drawable.ic_curso)
                //.resize(20,20)
                .into(imgCurso3);

        imgCurso1.setTag(Item.getCurso1().getId());
        imgCurso2.setTag(Item.getCurso2().getId());
        imgCurso3.setTag(Item.getCurso3().getId());

        imgCurso1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((ListView) parent).performItemClick(view, position, 0);
            }
        });

        imgCurso2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((ListView) parent).performItemClick(view, position, 0);
            }
        });

        imgCurso3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(context,"presionó elegir",Toast.LENGTH_LONG);
                ((ListView) parent).performItemClick(view, position, 0);
            }
        });

        return convertView;
    }
}
