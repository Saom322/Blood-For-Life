
package com.example.bloodforlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
/**
 * A Class Representing SignUp Form on the Screen
 */
public class MainActivity extends AppCompatActivity {
    /**
     * The Name of a Donor
     */
    EditText etName;
    /**
     * The BloodGroup of a Donor
     */
    EditText etBloodGroup;
    /**
     * The Email of a Donor
     */
    EditText etEmail;
    /**
     * The Password of a Donor
     */
    EditText etPassword;
    /**
     * The Weight of a Donor
     */
    EditText etWeight;
    /**
     * The ContactNumber of a Donor
     */
    EditText etPhone;
    /**
     * Button For Confirming SignUp
     */
    Button btnRegister;
    /**
     * Button For Activating Login Page
     */
    Button btnLogin;
    /**
     * Field for getting instance of a Firebase Database
     */
    FirebaseDatabase rootNode;
    /**
     * Field for selecting Table to save Donor data in Firebase DB
     */
    DatabaseReference reference;

    /**
     * Saves the given data by a Donor in DB
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.activity_main_et_name);
        etBloodGroup = findViewById(R.id.activity_main_et_bloodgroup);
        etWeight = findViewById(R.id.activity_main_et_weight);
        etEmail = findViewById(R.id.activity_main_et_email);
        etPassword = findViewById(R.id.activity_main_et_password);
        etPhone = findViewById(R.id.activity_main_et_phonenumber);
        btnLogin = findViewById(R.id.activity_main_btn_login);
        btnRegister = findViewById(R.id.activity_main_btn_register);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Donors");
                String name = etName.getText().toString();
                String bloodGroup = etBloodGroup.getText().toString();
                String weight = etWeight.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String phone = etPhone.getText().toString();
                Integer weightInteger = Integer.valueOf(weight);

                if (weightInteger > 50) {
                    if (name.length() != 0 && password.length() != 0 && bloodGroup.length() != 0 && email.length() != 0) {
                        Profile profile = new Profile(name, email, bloodGroup, weight, password, phone);
                        reference.child(phone).setValue(profile);
                        Toast.makeText(MainActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Some Fields are Empty", Toast.LENGTH_SHORT).show();
                    }
                }
                else

            {
                Toast.makeText(MainActivity.this, "Weight Must be Above 50 KG", Toast.LENGTH_SHORT).show();
            }


        }
    });

        btnLogin.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
        startActivity(new Intent(MainActivity.this, Login.class));
    }
    });


}
}