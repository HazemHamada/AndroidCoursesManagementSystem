package eng.asu.coursesmanagementsystem.activities;

import androidx.appcompat.app.AppCompatActivity;
import eng.asu.coursesmanagementsystem.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class RegisterActivity extends AppCompatActivity {
    private EditText email ,password,confirmPassword,phone,gpa,firstName,lastName;
    private Button btnRegister;
    String emailTxt,passwordtxt,confirmPasswordtxt,phoneTxt,gpaTxt,firstNametxt,lastNametxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        confirmPassword=findViewById(R.id.confirmPassword);
        phone=findViewById(R.id.phone);
        gpa=findViewById(R.id.gpa);
        firstName=findViewById(R.id.firstName);
        lastName=findViewById(R.id.lastName);
        btnRegister=findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailTxt = email.getText().toString();
                passwordtxt = password.getText().toString();
                confirmPasswordtxt = confirmPassword.getText().toString();
                emailTxt = email.getText().toString();
                phoneTxt = phone.getText().toString();
                gpaTxt = gpa.getText().toString();
                firstNametxt = firstName.getText().toString();
                lastNametxt = lastName.getText().toString();

            }
        });

    }
}
