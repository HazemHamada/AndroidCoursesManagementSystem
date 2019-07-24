package eng.asu.coursesmanagementsystem.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import eng.asu.coursesmanagementsystem.R;
import eng.asu.coursesmanagementsystem.utils.SharedCache;

public class loginActivity extends AppCompatActivity {
    private EditText emailEt , passwordEt;
    private Button btn_login;
    private TextView register;
    private ProgressBar loading;

    private SharedCache sharedCache;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEt=findViewById(R.id.email);
        passwordEt=findViewById(R.id.password);
        btn_login=findViewById(R.id.btn_login);
        loading=findViewById(R.id.loading);
        register=findViewById(R.id.register);

        sharedCache=new SharedCache(this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email =emailEt.getText().toString().trim();
                String pass=passwordEt.getText().toString().trim();

                if(email.isEmpty())
                    emailEt.setError("Please insert email");
                else if(pass.isEmpty())
                    passwordEt.setError("Please insert password");
                else {
                    login(email);
                    show();
                }
            }

        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




    }

    private void login(String email) {
        loading.setVisibility(View.VISIBLE);
        btn_login.setVisibility(View.GONE);
        sharedCache.saveDate(email);
        loading.setVisibility(View.GONE);
        Toast.makeText(this,"Data saved",Toast.LENGTH_SHORT).show();
    }
    private void show(){
        if(sharedCache.loadData() != "")
            Toast.makeText(this,sharedCache.loadData()+" Logged in successfully",Toast.LENGTH_SHORT).show();
    }
}
