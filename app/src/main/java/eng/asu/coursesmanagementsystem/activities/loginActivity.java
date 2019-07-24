package eng.asu.coursesmanagementsystem.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import eng.asu.coursesmanagementsystem.R;

public class loginActivity extends AppCompatActivity {
    private EditText email , password;
    private Button btn_login;
    private TextView register;
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        btn_login=findViewById(R.id.btn_login);
        loading=findViewById(R.id.loading);
        register=findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
