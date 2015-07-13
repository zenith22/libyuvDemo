package com.demo.libyuvdemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	static {
		System.loadLibrary("stlport_shared");
		System.loadLibrary("yuv_shared");
		System.loadLibrary("YUVDemo");
	}

	private native String stringFromJNI();

	private native byte[] ConvertToI420(byte[] nv21, int w, int h, int type);
	
	private native byte[] ConvertToI420NegativeStride(byte[] nv21, int w, int h, int type);

	private final String sdCardPath = Environment.getExternalStorageDirectory()
			.getAbsolutePath();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void ConvertToI420One(View view) {

		try {
			int w = 176, h = 144;

			File nv21File = new File(sdCardPath + "/libyuvDemo/nv21.yuv");
			File I420File = new File(sdCardPath
					+ "/libyuvDemo/nv21-I420-One.yuv");

			FileInputStream fileInputStream = new FileInputStream(nv21File);

			byte[] nv21 = new byte[(int) (w * h * 1.5)];

			fileInputStream.read(nv21);

			fileInputStream.close();

			byte[] I420 = ConvertToI420(nv21, w, h, 1);

			if (I420File.exists() == false) {
				I420File.createNewFile();
			}

			FileOutputStream fileOutputStream = new FileOutputStream(I420File);
			fileOutputStream.write(I420);
			fileOutputStream.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void ConvertToI420Two(View view) {

		try {
			int w = 176, h = 144;

			File nv21File = new File(sdCardPath + "/libyuvDemo/nv21.yuv");
			File I420File = new File(sdCardPath
					+ "/libyuvDemo/nv21-I420-Two.yuv");

			FileInputStream fileInputStream = new FileInputStream(nv21File);

			byte[] nv21 = new byte[(int) (w * h * 1.5)];

			fileInputStream.read(nv21);

			fileInputStream.close();

			byte[] I420 = ConvertToI420(nv21, w, h,2);

			if (I420File.exists() == false) {
				I420File.createNewFile();
			}

			FileOutputStream fileOutputStream = new FileOutputStream(I420File);
			fileOutputStream.write(I420);
			fileOutputStream.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void ConvertToI420Three(View view) {

		try {
			int w = 176, h = 144;

			File nv21File = new File(sdCardPath + "/libyuvDemo/nv21.yuv");
			File I420File = new File(sdCardPath
					+ "/libyuvDemo/nv21-I420-Three.yuv");

			FileInputStream fileInputStream = new FileInputStream(nv21File);

			byte[] nv21 = new byte[(int) (w * h * 1.5)];

			fileInputStream.read(nv21);

			fileInputStream.close();

			byte[] I420 = ConvertToI420(nv21, w, h,3);

			if (I420File.exists() == false) {
				I420File.createNewFile();
			}

			FileOutputStream fileOutputStream = new FileOutputStream(I420File);
			fileOutputStream.write(I420);
			fileOutputStream.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void ConvertToI420Four(View view) {

		try {
			int w = 176, h = 144;

			File nv21File = new File(sdCardPath + "/libyuvDemo/nv21.yuv");
			File I420File = new File(sdCardPath
					+ "/libyuvDemo/nv21-I420-Four.yuv");

			FileInputStream fileInputStream = new FileInputStream(nv21File);

			byte[] nv21 = new byte[(int) (w * h * 1.5)];

			fileInputStream.read(nv21);

			fileInputStream.close();

			byte[] I420 = ConvertToI420(nv21, w, h,4);

			if (I420File.exists() == false) {
				I420File.createNewFile();
			}

			FileOutputStream fileOutputStream = new FileOutputStream(I420File);
			fileOutputStream.write(I420);
			fileOutputStream.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void ConvertToI420NegativeStride(View view){


		try {
			int w = 176, h = 144;

			File nv21File = new File(sdCardPath + "/libyuvDemo/nv21.yuv");
			File I420File = new File(sdCardPath
					+ "/libyuvDemo/nv21-I420-NegativeStride.yuv");

			FileInputStream fileInputStream = new FileInputStream(nv21File);

			byte[] nv21 = new byte[(int) (w * h * 1.5)];

			fileInputStream.read(nv21);

			fileInputStream.close();

			byte[] I420 = ConvertToI420NegativeStride(nv21, w, h,4);

			if (I420File.exists() == false) {
				I420File.createNewFile();
			}

			FileOutputStream fileOutputStream = new FileOutputStream(I420File);
			fileOutputStream.write(I420);
			fileOutputStream.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
