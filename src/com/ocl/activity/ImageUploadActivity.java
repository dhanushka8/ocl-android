package com.ocl.activity;

import com.ocl.R;
import com.ocl.R.layout;
import com.ocl.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ImageUploadActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_upload);
		
 		initFormElements();
 		initFormFunctions();
		
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_upload, menu);
		return true;
	}
	
	private void initFormFunctions() {
		// TODO Auto-generated method stub
		
	}

	private void initFormElements() {
		// TODO Auto-generated method stub
		
	}

}
