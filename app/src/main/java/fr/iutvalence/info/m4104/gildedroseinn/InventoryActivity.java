package fr.iutvalence.info.m4104.gildedroseinn;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import java.util.Timer;
import java.util.TimerTask;


public class InventoryActivity extends ActionBarActivity {
    private ListView liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_layout);
        liste=(ListView) findViewById(R.id.listViewInventory);
        ListAdapterInventory adapt=new ListAdapterInventory(this);
        liste.setAdapter(adapt);

    }

    /*Lorsque l'activité Pop est fermée, donc qu'un item a bien été retiré,
    * on revient sur cette activité à traver onResume(),on actualise alors l'affichage de l'inventaire*/
    @Override
    protected void onResume(){
        super.onResume();
        ListAdapterInventory adapt=new ListAdapterInventory(this);
        liste.setAdapter(adapt);
    }
}
