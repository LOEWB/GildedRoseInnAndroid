package fr.iutvalence.info.m4104.gildedroseinn;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.AvoidXfermode;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.SocketHandler;


public class HomeActivity extends Activity {

    public static final String PREFS_NAME ="PrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //récupération du porte-monnaie
        SharedPreferences wallet = getSharedPreferences(PREFS_NAME,0);
        float cash=wallet.getFloat("wallet",0);
        ModelApplication.setWallet(cash);

        setContentView(R.layout.home_layout);
        initViews();
        //Incrémente le jour toutes les 10s
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                ModelApplication.incrDayNb();
            }
        }, 0, 10000);


    }

    @Override
    protected  void onResume()
    {
        super.onResume();
        initViews();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        //enregistrement de la valeur du porte-monnaie dans les préférences
        SharedPreferences wallet = getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor = wallet.edit();
        editor.putFloat("wallet",ModelApplication.getWallet());
        editor.commit();
    }
    public void homeActivityClickListener(View view)
    {
        switch (view.getId())
        {
            case R.id.shop_button :
                startShopActivity();
                break;
            case R.id.inventory_button :
                startInventoryActivity();
                break;
            case R.id.next_button :
                nextDay();
                break;
            case R.id.cash_button :
                getCash();
            default :
        }
    }

    private void getCash() {
        ModelApplication.incrWallet();
        initViews();
    }

    private void nextDay()
    {
        ModelApplication.incrDayNb();
        ModelApplication app=new ModelApplication();
        app.updateAllItems();
        initViews();
    }

    private void startInventoryActivity()
    {
        this.startActivity(new Intent(this, InventoryActivity.class));
    }

    private void startShopActivity()
    {
        this.startActivity( new Intent(this, ShopActivity.class));
    }

    private void initViews()
    {
        TextView dayTxt=(TextView)this.findViewById(R.id.day_text);
        dayTxt.setText(("" + ModelApplication.getDayNb()));
        TextView cashTxt=(TextView) this.findViewById(R.id.wallet_text);
        cashTxt.setText(""+ModelApplication.getWallet());
    }

}
