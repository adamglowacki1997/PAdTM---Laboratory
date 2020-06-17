package pl.edu.zut.wi.app.lab3;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

import pl.edu.zut.wi.app.lab3.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Klikniete", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                showToast("settings");
                break;
            case R.id.action_one:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                break;
            case R.id.action_two:
                showToast("two");
                break;
        }

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonOnClick(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void buttonOnClick2(View view) {
        Random random = new Random();
        int index = random.nextInt() % 5;

        ImageView imageView = findViewById(R.id.imageView);

        switch (index) {
        case 0:
            imageView.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_menu_add, getTheme()));
            break;

        case 1:
            imageView.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_menu_help, getTheme()));
            break;

        case 2:
            imageView.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_delete, getTheme()));
            break;

            case 3:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.a, getTheme()));
                break;

            case 4:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.b, getTheme()));
                break;
        }

    }

    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT)
                .show();
    }
}
