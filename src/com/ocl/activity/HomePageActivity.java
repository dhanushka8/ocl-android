package com.ocl.activity;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ocl.ProfileActivity;
import com.ocl.R;
import com.ocl.service.ImageUploaderService;

public class HomePageActivity extends Activity {

	private Button uploadOptionButton;
	private Button viewProfileButton;
	final int PICK_REQUEST_CODE =8;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);
		
		initFormElements();
		initFormFunctions();
		
	}

	private void initFormElements() {

		uploadOptionButton=(Button) findViewById(R.id.uploaddImagesButton);
		viewProfileButton=(Button)findViewById(R.id.viewProfileButton);
		
		
	}
	private static final int PICK_IMAGE = 1;
	private void initFormFunctions() {

		uploadOptionButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				
				startActivity(new Intent(HomePageActivity.this,ImageUploadActivity.class));
				HomePageActivity.this.finish();
				
				/*
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
				
				
				new Thread(new Runnable() {
	                    public void run() {
	                         runOnUiThread(new Runnable() {
	                                public void run() {
	                                    
	                                }
	                            });                      
	                      ImageUploaderService.doFileUpload(null);
	                    }
	                  }).start();
				
				*/
				
				/*
				 File pictures=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
				String [] listOfPictures=pictures.list();
				Uri uri=null;
				
				//ArrayList<Uri> arrayList=new ArrayList<Uri>();
				for(String picture:listOfPictures){
					//uri=Uri.parse("file://"+pictures.toString()+"/"+picture);
					Log.i("Images : ","picture name  "+picture+" path : "+pictures.toString());
				}
				*/
			}
		});
		
		viewProfileButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(HomePageActivity.this,ProfileActivity.class));
				HomePageActivity.this.finish();				
			}
		});
		
		
	}
		
		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		    if(requestCode == PICK_IMAGE && data != null && data.getData() != null) {
		        Uri _uri = data.getData();

		        //User had pick an image.
		        Cursor cursor = getContentResolver().query(_uri, new String[] { android.provider.MediaStore.Images.ImageColumns.DATA }, null, null, null);
		        cursor.moveToFirst();

		        //Link to the image
		        final String imageFilePath = cursor.getString(0);
		        Log.i("Image FOUND "," PATH : "+imageFilePath      );
		        cursor.close();
		    }
		    super.onActivityResult(requestCode, resultCode, data);
		}
		
	 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_page, menu);
		return true;
	}

 
	
}
