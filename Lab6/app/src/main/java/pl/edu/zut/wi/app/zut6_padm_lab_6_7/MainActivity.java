package pl.edu.zut.wi.app.zut6_padm_lab_6_7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gameList(View v) {
        Intent intencja = new Intent(getApplicationContext(), GamesList.class);
        // "gra" field indicates which game was chosen
        intencja.putExtra("gra", v.getId());
        startActivity(intencja);
    }
}
