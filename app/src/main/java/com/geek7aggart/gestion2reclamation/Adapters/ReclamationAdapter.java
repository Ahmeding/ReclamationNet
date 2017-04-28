

package com.geek7aggart.gestion2reclamation.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.geek7aggart.gestion2reclamation.Model.Reclamation;
import com.geek7aggart.gestion2reclamation.R;

import java.util.List;

/**
 * Created by BARA' on 08/04/2016.
 */
public class ReclamationAdapter extends ArrayAdapter<Reclamation>{
   Context context;
    int resource;
    public ReclamationAdapter(Context context, int resource,List<Reclamation> reclamations) {
        super(context, resource,reclamations);
        this.context=context;
        this.resource=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
View view=  convertView;
        ReclamationHolder reclamationHolder= new ReclamationHolder();
        if(view==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, parent, false);
            reclamationHolder.tv_id = (TextView) view.findViewById(R.id.tvID);
            reclamationHolder.tv_titre = (TextView) view.findViewById(R.id.tvTitre);
            reclamationHolder.tv_etat = (TextView) view.findViewById(R.id.tvEtat);
            reclamationHolder.tv_date = (TextView) view.findViewById(R.id.tvDate);
            view.setTag(reclamationHolder);
        }
        else
        {
            reclamationHolder= (ReclamationHolder) view.getTag();
        }
        reclamationHolder.tv_id.setText(String.valueOf(getItem(position).getIdReclamation()));
        reclamationHolder.tv_titre.setText(getItem(position).getTitre());
        reclamationHolder.tv_etat.setText(getItem(position).getEtat());
       reclamationHolder.tv_date.setText(getItem(position).getDateRec());


        return view;
    }
    class ReclamationHolder{
        TextView tv_id;
        TextView tv_titre;
        TextView tv_etat;
        TextView tv_date;
    }
}
