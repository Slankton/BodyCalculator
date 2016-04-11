package com.example.slanktonen.bodycalculator;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class BMI extends Activity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        calculate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void calculate(){

        Button berechne = (Button) findViewById(R.id.buttonBerechne);
        berechne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String groesse = ((EditText) findViewById(R.id.groesseInput)).getText().toString();
                String gewicht = ((EditText) findViewById(R.id.gewichtInput)).getText().toString();
                if (!isNull(groesse, gewicht)) {
                    if (groesse.length() == 3 && !groesse.contains(".")) {
                        groesse = groesse.substring(0, 1) + "." + groesse.substring(1, 3);
                    }
                    Float groesseF = Float.parseFloat(groesse);
                    Float gewichtF = Float.parseFloat(gewicht);

                    if (checkData(groesseF, gewichtF)) {
                        float ergebnis = gewichtF / (groesseF * groesseF);
                        TextView ergebnisOutput = (TextView) findViewById(R.id.ergebnisOutput);
                        String auswertung = auswertenBMI(ergebnis);
                        ergebnisOutput.setText(String.valueOf(ergebnis).substring(0,5) + "\n" + auswertung);
                    }
                }
            }
        });

    }

    //Art des Übergewichts
    String auswertenBMI(float ergebnis) {
        boolean weiblich = ((RadioButton) findViewById(R.id.weiblichRadio)).isChecked();
        if ((ergebnis < 19 && weiblich) || (ergebnis < 20 && !weiblich))
            return getString(R.string.untergewicht);
        else if ((ergebnis >= 19 && ergebnis <= 24 && weiblich) || (ergebnis >= 20 && ergebnis <= 25 && !weiblich))
            return getString(R.string.normalgewicht);
        else if ((ergebnis > 24 && ergebnis <= 30 && weiblich) || (ergebnis > 25 && ergebnis <= 30 && !weiblich))
            return getString(R.string.uebergewicht);
        else if (ergebnis > 30 && ergebnis <= 40)
            return getString(R.string.adipositas);
        else
            return getString(R.string.massiveAdipositas);
    }

    //Sind die Daten realistisch?
    boolean checkData(float groesse, float gewicht) {
        boolean ret = true;
        if (groesse < 1.00 || groesse > 2.50) {
            findViewById(R.id.groesseAlert).setVisibility(View.VISIBLE);
            ret = false;
        }else{
            findViewById(R.id.groesseAlert).setVisibility(View.GONE);
        }
        if (gewicht < 31 || gewicht > 249) {
            findViewById(R.id.gewichtAlert).setVisibility(View.VISIBLE);
            ret = false;
        }else{
            findViewById(R.id.gewichtAlert).setVisibility(View.GONE);
        }

        return ret;
    }

    //Wurde etwas eingegeben
    boolean isNull(String groesse, String gewicht) {
        boolean ret = false;
        if (groesse.isEmpty()) {
            findViewById(R.id.groesseAlert).setVisibility(View.VISIBLE);
            ret = true;
        }
        else{
            findViewById(R.id.groesseAlert).setVisibility(View.GONE);
        }
        if (gewicht.isEmpty()) {
            findViewById(R.id.gewichtAlert).setVisibility(View.VISIBLE);
            ret = true;
        }
        else{
            findViewById(R.id.gewichtAlert).setVisibility(View.GONE);
        }

        return ret;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "BMI Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.slanktonen.bodycalculator/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "BMI Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.slanktonen.bodycalculator/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}