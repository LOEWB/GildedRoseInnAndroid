package fr.iutvalence.info.m4104.gildedroseinn;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

public class ListAdapterInventory implements ListAdapter
{
    private ModelApplication appli = new ModelApplication();
    private Context context;

    public ListAdapterInventory(Context context)
    {
        this.context = context;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return this.appli.getInventorySize();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //initialisation de la view
        LayoutInflater li = LayoutInflater.from(this.context);
        View vue = li.inflate(R.layout.item_layout_shop, parent, false);
        TextView txt = (TextView) vue.findViewById(R.id.item_text);
        TextView sellin = (TextView) vue.findViewById(R.id.item_sellin);
        TextView quality = (TextView) vue.findViewById(R.id.item_quality);
        TextView price =(TextView) vue.findViewById(R.id.price_text);
        TextView button=(TextView) vue.findViewById(R.id.button);
        txt.setText(this.appli.getItemInventory(position).getName());
        sellin.setText(String.valueOf(this.appli.getItemInventory(position).getSellIn()));
        quality.setText(String.valueOf(this.appli.getItemInventory(position).getQuality()));
        price.setText(String.valueOf(this.appli.getItemInventory(position).getPrice()));
        button.setText("REMOVE ");

        //implémentation du bouton de suppression d'item, on fais passer la position courante à travers son tag
        final Button b=(Button) vue.findViewById(R.id.button);
        b.setTag(position);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //On ouvre l'activité de pop up en lui envoyant la position courante et le type de listView ici l'inventaire en extra
                Intent intent=new Intent(context,Pop.class);
                Bundle extra=new Bundle();
                extra.putString("type", "remove");
                extra.putInt("position", b.getTag().hashCode());
                intent.putExtra("bundle", extra);
                context.startActivity(intent);
            }
        });
        return vue;

    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public boolean isEmpty() {
        return getCount()==0;
    }

}
