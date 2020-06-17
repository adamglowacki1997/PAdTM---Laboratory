package pl.edu.zut.wi.app.zut6_padtm_lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class AddRecord extends AppCompatActivity {
    private int modify_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        ArrayAdapter gatunki = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[] {"Pies", "Kot", "Rybki"});
        Spinner gatunek = (Spinner) findViewById
                (R.id.inputGatunek);
        gatunek.setAdapter(gatunki);

        Bundle extras = getIntent().getExtras();
        try {
            if(extras.getSerializable("element") != null) {
                Animal zwierz = (Animal) extras.getSerializable("element");

                Spinner formGatunek = (Spinner) findViewById(R.id.inputGatunek);
                EditText kolor = (EditText) findViewById(R.id.inputKolor);
                EditText wielkosc = (EditText) findViewById(R.id.inputWielkosc);
                EditText opis = (EditText) findViewById(R.id.inputOpis);

                kolor.setText(zwierz.getKolor());
                wielkosc.setText(Float.toString(zwierz.getWielkosc()));
                opis.setText(zwierz.getOpis());

                int i;
                switch (zwierz.getGatunek()) {
                    case "Pies": i = 0; break;
                    case "Kot": i = 1; break;
                    case "Rybki": i = 2;break;
                    default: i = -1;
                }
                formGatunek.setSelection(i);

                this.modify_id = zwierz.getId();
            }
        }catch(Exception ex) {
            this.modify_id=0;
        }


    }

    public void send(View view) {
        EditText kolor = (EditText) findViewById(R.id.inputKolor);
        EditText wielkosc = (EditText) findViewById(R.id.inputWielkosc);
        EditText opis = (EditText) findViewById(R.id.inputOpis);
        Spinner gatunek = (Spinner) findViewById(R.id.inputGatunek);

        Animal zwierze = new Animal(
                gatunek.getSelectedItem().toString(),
                kolor.getText().toString(),
                Float.valueOf(wielkosc.getText().toString()),
                opis.getText().toString()
        );

        zwierze.setId(this.modify_id);

        Intent intencja = new Intent();
        intencja.putExtra("nowy", zwierze);
        setResult(RESULT_OK, intencja);
        finish();

    }
}
