package fr.iutvalence.info.m4104.gildedroseinn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by BNY on 25/02/2016.
 */
public class Pop extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_layout);

        //on réduit la taille de la fenetre du pop up
        DisplayMetrics d = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(d);

        getWindow().setLayout((int) (d.widthPixels * .9), (int) (d.heightPixels * .2));

        ModelApplication app=new ModelApplication();
        Intent intent=getIntent();
        Bundle extra=intent.getBundleExtra("bundle");
        TextView txt = (TextView) findViewById(R.id.popText);
        TextView price=(TextView) findViewById(R.id.price_text);

        //ShopActivity, on vient d'acheter un item
        if (extra.getString("type").equals("add"))
        {
            app.addItemToInventory(app.getItem(extra.getInt("position")));
            ModelApplication.substracWallet(app.getItem(extra.getInt("position")).getPrice());
            txt.setText(app.getItem(extra.getInt("position")).getName());
            price.setText(String.valueOf(app.getItem(extra.getInt("position")).getPrice()));
        }

        //InventoryActivity, on vient de retirer un item
        if(extra.getString("type").equals("remove")) {
            txt.setText(app.getItemInventory(extra.getInt("position")).getName());
            price.setText(String.valueOf(app.getItemInventory(extra.getInt("position")).getPrice()));
            TextView stateTxt = (TextView) findViewById(R.id.textView4);
            stateTxt.setText("removed !");
            TextView loss=(TextView) findViewById(R.id.textView6);
            loss.setText("loss of ");
            app.removeFromInventory((extra.getInt("position")));
        }
    }
}
