package co.edu.unab.covid_app.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import co.edu.unab.covid_app.R;
import co.edu.unab.covid_app.entities.Diagnostico;

public class DiagnosticoAdapter extends BaseAdapter {
    private ArrayList<Diagnostico> listaItems;
    private Context context;

    public DiagnosticoAdapter(ArrayList<Diagnostico> listaItems, Context context) {
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
        Diagnostico Item = (Diagnostico) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item_diagnostico,null);
        ImageView imgUser = convertView.findViewById(R.id.imgUser);
        TextView lblName = convertView.findViewById(R.id.lblNameList);
        TextView lblDiagnostico = convertView.findViewById(R.id.lblDiagnostico);

        Picasso.get().load(Item.getImage())
                .placeholder(R.drawable.ic_avatar)
                .error(R.drawable.ic_avatar)
                //.resize(20,20)
                .into(imgUser);
        String full_name=Item.getName()+" "+Item.getSurname();
        lblName.setText(full_name);
        lblDiagnostico.setText(Item.getEstado());

        return convertView;
    }
}
