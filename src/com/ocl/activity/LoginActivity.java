package com.ocl.activity;

import com.ocl.R;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	
	private EditText userNameField;
	private EditText passwordField;
	private Button loginButton;
	private Button registerButton;
	private Button exitButton;
	
	final static String TAG="Home Page";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("oncreate called");
        Log.i(TAG, "On create function called");
        
        initFormElements();
        
        initFormFunction();
        Log.d(TAG, "all method calls done.");
        
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }
    
    private void initFormElements(){
    	setContentView(R.layout.activity_login_page);
    	
    	Log.i(TAG,"Init form ele called");
    	
    	userNameField=(EditText)findViewById(R.id.userName_textField);
    	passwordField=(EditText)findViewById(R.id.password_textField);
    	
    	loginButton=(Button)findViewById(R.id.login_button);
    	registerButton=(Button)findViewById(R.id.register_button);
    	exitButton=(Button)findViewById(R.id.exit_button);
    	
    	Log.i(TAG,"Init form ele called done");
    	
      }



	private void initFormFunction() {
		
		Log.i(TAG,"Init form function called done"); 
		
		loginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				autherizeUserLogin();
					Log.d(TAG,"login button calling");
				
			}
		});
		
		
		registerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Log.d(TAG, "Register Button Calling");
			}
		});
		
		exitButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				System.out.println(" exit button alle");
				Log.d(TAG, "Exit Application Calling");
				
			}
		});
		
		
	}


	protected void autherizeUserLogin() {
		
		String userName=userNameField.getText().toString();
		String password=passwordField.getText().toString();
		
		Log.i(TAG,"User typed useN: "+userName+" passowrd : "+password);
				
		startActivity(new Intent(LoginActivity.this,HomePageActivity.class));
		LoginActivity.this.finish();
		
	}
 
    
    
}
