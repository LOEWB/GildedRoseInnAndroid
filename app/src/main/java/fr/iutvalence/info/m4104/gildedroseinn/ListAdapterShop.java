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
import android.widget.Toast;

import org.w3c.dom.Text;

public class ListAdapterShop implements ListAdapter
{
    private ModelApplication appli = new ModelApplication();
    private Context context;

    public ListAdapterShop(Context context)
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
        return this.appli.getItemsSize();
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
        LayoutInflater li = LayoutInflater.from(this.context);
        View vue = li.inflate(R.layout.item_layout_shop, parent, false);
        TextView txt = (TextView) vue.findViewById(R.id.item_text);
        TextView sellin = (TextView) vue.findViewById(R.id.item_sellin);
        TextView quality = (TextView) vue.findViewById(R.id.item_quality);
        TextView price =(TextView) vue.findViewById(R.id.price_text);
        txt.setText(this.appli.getItem(position).getName());
        sellin.setText(String.valueOf(this.appli.getItem(position).getSellIn()));
        quality.setText(String.valueOf(this.appli.getItem(position).getQuality()));
        price.setText(String.valueOf(this.appli.getItem(position).getPrice()));

        final Button b=(Button) vue.findViewById(R.id.button);
        b.setTag(position);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelApplication app=new ModelApplication();
                //pas assez de thunes
                if(ModelApplication.getWallet()<app.getItem(b.getTag().hashCode()).getPrice())
                {
                    Toast toast=Toast.makeText(context,"Not enought money. Go get some dumbass !",Toast.LENGTH_SHORT);
                    toast.show();
                }
                //assez de thunes
                else
                {
                    Intent intent = new Intent(context, Pop.class);
                    Bundle extra = new Bundle();
                    extra.putInt("position", b.getTag().hashCode());
                    extra.putString("type", "add");
                    intent.putExtra("bundle", extra);
                    context.startActivity(intent);
                }
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
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return getCount()==0;
    }


}
