package eng.asu.coursesmanagementsystem.activities;

import androidx.appcompat.app.AppCompatActivity;
import eng.asu.coursesmanagementsystem.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.*;

public class RegisterActivity extends AppCompatActivity {
    private EditText email ,password,confirmPassword,phone,gpa,name;
    private Button btnRegister;
    private String emailTxt,passwordtxt,confirmPasswordtxt,nametxt;
    int gpaTxt,phoneTxt;
    boolean isInvalid = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        confirmPassword=findViewById(R.id.confirmPassword);
        phone=findViewById(R.id.phone);
        gpa=findViewById(R.id.gpa);
        name=findViewById(R.id.name);
        btnRegister=findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailTxt = email.getText().toString().trim();
                passwordtxt = password.getText().toString();
                confirmPasswordtxt = confirmPassword.getText().toString();
                nametxt = name.getText().toString();
                if(phone.getText().toString().isEmpty()){
                    phone.setError("Please insert phone");
                    isInvalid = true;
                }
                else{
                    try{
                        phoneTxt = Integer.parseInt(phone.getText().toString());
                    }
                    catch(Exception e) {
                        phone.setError("Phone must be a number");
                        isInvalid = true;
                    }
                }
                if(gpa.getText().toString().isEmpty()){
                    gpa.setError("Please insert gpa");
                    isInvalid = true;
                }
                else{
                    try {
                        gpaTxt = Integer.parseInt(gpa.getText().toString());
                    }
                    catch (Exception e){
                        gpa.setError("Gpa must be a number");
                        isInvalid = true;
                    }
                }
                if(emailTxt.isEmpty()){
                    email.setError("Please insert email");
                    isInvalid = true;
                }
                if(passwordtxt.isEmpty()){
                    password.setError("Please insert password");
                    isInvalid = true;
                }
                if(nametxt.isEmpty()){
                    name.setError("Please insert name");
                    isInvalid = true;

                }
                if(confirmPasswordtxt.isEmpty()){
                    confirmPassword.setError("Please confirm password");
                    isInvalid = true;
                }
                if(!validateEmail(emailTxt)){
                    email.setError("Invalid email");
                    isInvalid = true;
                }
                if(!validateGpa(gpaTxt)){
                    gpa.setError("Invalid gpa");
                    isInvalid = true;
                }
                if(!matchPassword(passwordtxt,confirmPasswordtxt)){
                    confirmPassword.setError("Passwords don't match");
                    isInvalid = true;
                }
                if(!validateName(nametxt)){
                    name.setError("Name must contain characters only");
                    isInvalid = true;
                }
                if(!isInvalid){
                    Toast.makeText(getApplicationContext()," Registered successfully",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });

    }

    public boolean validateEmail(String email){
        String emailRegex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";;
        Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPat.matcher(email);
        return  matcher.find();
    }
    public boolean validateGpa(int gpa) {
        return (gpa > 0 && gpa <= 4);
    }
    public boolean validateName(String name){
        for(int i=0; i < name.length(); i++){
            if(!(name.charAt(i)>='a' && name.charAt(i)<='z' || name.charAt(i)>='A' && name.charAt(i)<='Z'))
                return false;
        }
        return true;
    }
    public boolean matchPassword(String password, String confirm){
        return (password.equals(confirm));
    }
}
