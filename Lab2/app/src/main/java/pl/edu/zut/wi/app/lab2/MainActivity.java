package pl.edu.zut.wi.app.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("DEBUG", "Create");
        ShowToast("onCreate()");
    }

    @Override
    protected void onStart() { super.onStart();
        Log.d("DEBUG", "onStart");
        ShowToast("onStart()");
    }

    @Override
    protected void onResume() { super.onResume();
        Log.d("DEBUG", "onResume");
        ShowToast("onResume()");
    }

    @Override
    protected void onPause() { super.onPause();
        Log.d("DEBUG", "onPause");
        ShowToast("onPause()");
    }

    @Override
    protected void onStop() { super.onStop();
        Log.d("DEBUG", "onStop");
        ShowToast("onStop()");
    }

    @Override
    protected void onRestart() { super.onRestart();
        Log.d("DEBUG", "onRestart");
        ShowToast("onRestart()");
    }

    @Override
    protected  void onDestroy() {super.onDestroy();
        Log.d("DEBUG", "onDestroy");
        ShowToast("onDestroy()");
    }

    private void ShowToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void buttonOnClick(View view) {
        Intent intent = new Intent(this, SideActivity.class);
        startActivity(intent);
    }
}
