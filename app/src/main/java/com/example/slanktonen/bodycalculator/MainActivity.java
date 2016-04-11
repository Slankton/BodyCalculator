package com.example.slanktonen.bodycalculator;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = getListView();
        String[] listItems = new String[] {getString(R.string.bmi), getString(R.string.waistHipRatio), "Elem2", "Elem3", "Elem4"};

        //Jedes Elemet der Liste wird zu einem Object pro Zeile gemapped
       this.setListAdapter(new ArrayAdapter<>(this, R.layout.listview_layout, R.id.Itemname, listItems));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent intent;
                switch(position) {
                    case 0:
                        intent = new Intent(getApplicationContext(), BMI.class);
                        break;
                    case 1:
                        intent = new Intent(getApplicationContext(), WaistHip.class);
                        break;
                    default:
                        intent = new Intent(getApplicationContext(), BMI.class);
                        break;
                }
                startActivity(intent);

            }

        });
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
}
