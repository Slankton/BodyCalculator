package com.example.slanktonen.bodycalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class WaistHip extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waist_hip);

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
                String taille = ((EditText) findViewById(R.id.tailleInput)).getText().toString();
                String huefte = ((EditText) findViewById(R.id.huefteInput)).getText().toString();
                if (!isNull(taille, huefte)) {

                    Float tailleF = Float.parseFloat(taille);
                    Float huefteF = Float.parseFloat(huefte);

                    if (checkData(tailleF, huefteF)) {
                        float ergebnis = tailleF / huefteF;
                        TextView ergebnisOutput = (TextView) findViewById(R.id.ergebnisOutput);
                        String auswertung = auswertenWaistHip(ergebnis);
                        ergebnisOutput.setText(String.valueOf(ergebnis) + "\n" + auswertung);
                    }
                }
            }
        });
    }

    String auswertenWaistHip(float ergebnis) {
        boolean weiblich = ((RadioButton) findViewById(R.id.weiblichRadio)).isChecked();
        if ((ergebnis < 0.8 && weiblich) || (ergebnis < 0.9 && !weiblich))
            return getString(R.string.normalgewicht);
        else if ((ergebnis >= 0.8 && ergebnis <= 0.84 && weiblich) || (ergebnis >= 0.9 && ergebnis <= 0.99 && !weiblich))
            return getString(R.string.uebergewicht);
        else {
            return getString(R.string.adipositas);
        }
    }

    boolean checkData(float taille, float huefte) {
        boolean ret = true;

        return ret;
    }

    Boolean isNull(String taille, String huefte) {
        boolean ret = false;
        if (taille.isEmpty()) {
            findViewById(R.id.tailleAlert).setVisibility(View.VISIBLE);
            ret = true;
        }
        else{
            findViewById(R.id.tailleAlert).setVisibility(View.GONE);
        }
        if (huefte.isEmpty()) {
            findViewById(R.id.huefteAlert).setVisibility(View.VISIBLE);
            ret = true;
        }
        else{
            findViewById(R.id.huefteAlert).setVisibility(View.GONE);
        }

        return ret;
    }
}
