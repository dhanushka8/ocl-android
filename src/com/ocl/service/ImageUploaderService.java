package com.ocl.service;

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
import java.util.List;

import android.util.Log;

public class ImageUploaderService {

	
	public static boolean doFileUpload(List<String> fileList){

		boolean isSuccesfull=false;
		HttpURLConnection conn = null;
	    DataOutputStream dos = null;
	    DataInputStream inStream = null; 

	    String exsistingFileName = "/mnt/sdcard/Pictures/Monkey_sad.jpg";
	    // Is this the place are you doing something wrong.
	    String lineEnd = "\r\n";
	    String twoHyphens = "--";
	    String boundary =  "*****";
	    int bytesRead, bytesAvailable, bufferSize;
	    byte[] buffer;
	    int maxBufferSize = 1*1024*1024;
	    String urlString = "http://10.0.2.2:8084/Uploader/FileUploadHandler";
	    try
	    {
	        Log.i("MediaPlayer","Inside second Method");
	        FileInputStream fileInputStream = new FileInputStream(new File(exsistingFileName) );
	        URL url = new URL(urlString);
	        conn = (HttpURLConnection) url.openConnection();
	        conn.setDoInput(true);
	        // Allow Outputs
	        conn.setDoOutput(true);
	        // Don't use a cached copy.
	        conn.setUseCaches(false);
	        // Use a post method.
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Connection", "Keep-Alive");
	        conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);
	        
	        dos = new DataOutputStream( conn.getOutputStream() );
	        dos.writeBytes(twoHyphens + boundary + lineEnd);
	        dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + exsistingFileName +"\"" + lineEnd);
	        dos.writeBytes(lineEnd);
	        Log.e("MediaPlayer","Headers are written");
	        bytesAvailable = fileInputStream.available();
	        bufferSize = Math.min(bytesAvailable, maxBufferSize);
	        buffer = new byte[bufferSize];
	        bytesRead = fileInputStream.read(buffer, 0, bufferSize);
	        while (bytesRead > 0)
	        {
	            dos.write(buffer, 0, bufferSize);
	            bytesAvailable = fileInputStream.available();
	            bufferSize = Math.min(bytesAvailable, maxBufferSize);
	            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
	        }
	        dos.writeBytes(lineEnd);
	        dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
	        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String inputLine;
	        StringBuffer tv=new StringBuffer();
	        while ((inputLine = in.readLine()) != null) 
	            tv.append(inputLine);
	        // close streams
	        Log.e("MediaPlayer","File is written");
	        
	        Log.i("MediaPlayer",tv.toString());
	        fileInputStream.close();
	        dos.flush();
	        dos.close();
	    }
	    catch (MalformedURLException ex)
	    {
	        Log.e("MediaPlayer", "error: " + ex.toString(), ex);
	    }
	    catch (IOException ioe)
	    {
	        Log.e("MediaPlayer", "error: " + ioe.toString(), ioe);
	    }

	    //------------------ read the SERVER RESPONSE
	    try {
	        inStream = new DataInputStream ( conn.getInputStream() );
	        String str;            
	        while (( str = inStream.readLine()) != null)
	        {
	            Log.e("MediaPlayer","Server Response"+str);
	        }
	        /*while((str = inStream.readLine()) !=null ){

	        }*/
	        inStream.close();
	    }
	    catch (IOException ioex){
	        Log.e("MediaPlayer", "error: " + ioex.toString(), ioex);
	    }
	    return isSuccesfull;
	}
	
}
