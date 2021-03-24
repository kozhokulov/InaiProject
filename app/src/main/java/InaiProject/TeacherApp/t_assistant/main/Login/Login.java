package InaiProject.TeacherApp.t_assistant.main.Login;

import android.os.Bundle;

import InaiProject.TeacherApp.t_assistant.R;
import InaiProject.TeacherApp.t_assistant.main.AppBase;
import InaiProject.TeacherApp.t_assistant.main.database.LoginDataBaseAdapter;
import InaiProject.TeacherApp.t_assistant.main.database.Register;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

    public class Login extends Activity
    {
        Button btnSignIn,btnSignUp;
        LoginDataBaseAdapter loginDataBaseAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

// create a instance of SQLite Database
            loginDataBaseAdapter=new LoginDataBaseAdapter(this);
            loginDataBaseAdapter=loginDataBaseAdapter.open();

// Get The Refference Of Buttons
            btnSignIn=(Button)findViewById(R.id.buttonSignIn);
            btnSignUp=(Button)findViewById(R.id.buttonSignUp);

// Set OnClick Listener on SignUp button
            btnSignUp.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
// TODO Auto-generated method stub

/// Create Intent for SignUpActivity abd Start The Activity
                    Intent intentSignUP=new Intent(getApplicationContext(), Register.class);
                    startActivity(intentSignUP);
                }
            });

// get the Refferences of views
            final EditText editTextUserName=(EditText)findViewById(R.id.editTextUserNameToLogin);
            final EditText editTextPassword=(EditText)findViewById(R.id.editTextPasswordToLogin);

// Set On ClickListener
            btnSignIn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
// get The User name and Password
                    String userName=editTextUserName.getText().toString();
                    String password=editTextPassword.getText().toString();

// fetch the Password form database for respective user name
                    String storedPassword=loginDataBaseAdapter.getSinlgeEntry(password);

// check if the Stored password matches with Password entered by user
                    if(password.equals(storedPassword))
                    {
                        Toast.makeText(Login.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                        Intent intentSignUP=new Intent(getApplicationContext(), AppBase.class);
                        startActivity(intentSignUP);
                    }
                    else
                    {
                        Toast.makeText(Login.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
// Close The Database
            loginDataBaseAdapter.close();
        }
    }