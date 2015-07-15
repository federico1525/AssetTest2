package com.fede.assettest2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

import com.leanplum.Leanplum;
import com.leanplum.Var;
import com.leanplum.activities.LeanplumActionBarActivity;
import com.leanplum.activities.LeanplumActivity;
import com.leanplum.callbacks.VariableCallback;
import com.leanplum.callbacks.VariablesChangedCallback;


public class MainActivity extends LeanplumActionBarActivity {

    ImageView mImage;
    Var<String> stella = Var.defineAsset("Star", "star.png");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // We've inserted your FedeApp API keys here for you :)
        if (BuildConfig.DEBUG) {
            Leanplum.setAppIdForDevelopmentMode("app_KBA1FumxGvuftZQQanpt8qNv1eLJcLHffBF38qwVNcw", "dev_rXc2Io45NCYWFC01MhhurVhd1caBKrwKXWQXOmzzdqA");
        } else {
            Leanplum.setAppIdForProductionMode("app_KBA1FumxGvuftZQQanpt8qNv1eLJcLHffBF38qwVNcw", "prod_JJ7oaM1zXgv0QKxIxNtauBeSD3Wa7ghdaAXWTgYp32o");
        }

        mImage = (ImageView) findViewById(R.id.image);


        Leanplum.addVariablesChangedAndNoDownloadsPendingHandler(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {
                Log.i("####", "variablesChanged");
                mImage.setImageBitmap(BitmapFactory.decodeStream(stella.stream()));
            }
        });


        Leanplum.enableVerboseLoggingInDevelopmentMode();
        Leanplum.start(this);

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
