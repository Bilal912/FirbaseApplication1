package com.example.firbaseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.firbaseapplication.Upload_Flies.Upload_Flie;
import com.example.firbaseapplication.firestore_CRUD.AddValuesActicity;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

      /** try {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.firbaseapplication", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.i("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        }**/
      /**catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }**/
      /** catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } **/
    }


    public void Register(View view) {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    public void Authentication(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void view(View view) {
        Intent intent = new Intent(this, ViewActivity.class);
        startActivity(intent);
    }

    public void pattern(View view) {
        Intent intent = new Intent(this, PatternActivity.class);
        startActivity(intent);
    }

    public void inputfield(View view) {
        Intent intent = new Intent(this, TextInputFieldActivity.class);
        startActivity(intent);
    }

    public void LoginActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void googlesignin(View view) {
        Intent intent = new Intent(this, GoogleActivity.class);
        startActivity(intent);
    }

    public void facebookSignIn(View view) {
        Intent intent = new Intent(this, FacebookActivity.class);
        startActivity(intent);
    }

    public void phoneAuthentication(View view) {
        Intent intent = new Intent(this,  PhoneAuthenticationActivity.class);
        startActivity(intent);
    }

    public void fragmentPage(View view) {
        Intent intent = new Intent(this,  FragmentActivity.class);
        startActivity(intent);
    }

    public void locationPage(View view) {
        Intent intent = new Intent(this,  SnackBarActivity.class);
        startActivity(intent);
    }

    public void citylocationPage(View view) {
        Intent intent = new Intent(this,  CityLocationActivity.class);
        startActivity(intent);
    }

    public void fileUpload(View view) {
        Intent intent = new Intent(this,  Upload_Flie.class);
        startActivity(intent);
    }

    public void fireStore_Add(View view) {
        Intent intent = new Intent(this,  AddValuesActicity.class);
        startActivity(intent);
    }
}
