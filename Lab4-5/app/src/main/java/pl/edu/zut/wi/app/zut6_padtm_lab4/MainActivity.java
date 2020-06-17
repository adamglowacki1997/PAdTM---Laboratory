package pl.edu.zut.wi.app.zut6_padtm_lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private int rap = 0;
    private SimpleCursorAdapter adapter;
    private MySQLite db = new MySQLite(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                this.db.lista(),
                new String[] {"_id", "gatunek" },
                new int[] {android.R.id.text1, android.R.id.text2},
                SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);

        ListView listView = (ListView) findViewById(R.id.mainActivityListView);
        listView.setAdapter(this.adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos, long id)
            {
                TextView idz = (TextView) view.findViewById(android.R.id.text1);
                Animal zwierz = db.pobierz(Integer.parseInt(idz.getText().toString()));
                Intent intencja = new
                        Intent(getApplicationContext(),
                        AddRecord.class);

                intencja.putExtra("element", zwierz);
                startActivityForResult(intencja, 2);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean
            onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView idz = (TextView) view.findViewById(android.R.id.text1);
                db.usun(idz.getText().toString());
                adapter.changeCursor(db.lista());
                adapter.notifyDataSetChanged();
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public void newRecord(MenuItem mi) {
        Intent intent = new Intent(this, AddRecord.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == 1) && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Animal nowy = (Animal)extras.getSerializable("nowy");

                this.db.dodaj(nowy);

            adapter.changeCursor(this.db.lista());
            adapter.notifyDataSetChanged();
        }

        if ((requestCode == 2) && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Animal nowy = (Animal)extras.getSerializable("nowy");

                this.db.aktualizuj(nowy);

            adapter.changeCursor(this.db.lista());
            adapter.notifyDataSetChanged();
        }
    }
}
