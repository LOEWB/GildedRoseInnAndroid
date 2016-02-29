package fr.iutvalence.info.m4104.gildedroseinn;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;


public class ShopActivity extends ActionBarActivity {

    private ListView liste;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_layout);
        liste=(ListView) findViewById(R.id.listViewShop);
        liste.setAdapter(new ListAdapterShop(this));
    }
}
