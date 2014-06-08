package us.wiinom.zpo.trainer.app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;

import serwisy.FileReadingService;
import serwisy.TrainingService;

public class MainActivity extends ActionBarActivity {
    TrainingService ts;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ts = new TrainingService(this.getApplicationContext());
        intent = new Intent(getApplicationContext(), TreningActivity.class);

        inicjalizacjaListView();
    }

    private void inicjalizacjaListView() {
        ListView lvTreningi = (ListView)findViewById(R.id.listView);

        lvTreningi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                intent.putExtra("SelectedTrainingFile", adapterView.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });

        ArrayAdapter<String> treningiAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, ts.getTrainings());
        lvTreningi.setAdapter(treningiAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
