package com.gsdp.opsecurity;

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
	public SurfaceHolder mHolder; 
	private Camera mCamera;
	
	public CameraPreview(Context context, Camera camera) {
		super(context);
		mCamera = camera;
		
		// Install a SurfaceHolder.Callback so we get notified when the 
		// underlying surface is created and destroyed.
		mHolder = getHolder();        
		mHolder.addCallback(this);
		// deprecated setting, but required on Android versions prior to 3.0        
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		
	}
	
	public void surfaceCreated(SurfaceHolder holder) {
		// The Surface has been created, now tell the camera where to draw the preview.
		try {
			mCamera.setPreviewDisplay(holder);
			mCamera.startPreview();
			mCamera.setDisplayOrientation(90);
			Log.d("Henry", "Surface Created");
		} catch (IOException e) {
			Log.d("IO Error", "Error setting camera preview: " + e.getMessage());
		}
	}
	
	public void surfaceDestroyed(SurfaceHolder holder) {
		//mCamera.stopPreview();
		Log.d("Henry", "Suface Destroyed");
		// empty. Take care of releasing the Camera preview in your activity.
	}
	
	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		// If your preview can change or rotate, take care of those events here.
		// Make sure to stop the preview before resizing or reformatting it.
		
		Log.d("Henry", "Suface Changed");
		if (mHolder.getSurface() == null){
			// preview surface does not exist
		}
		
		// stop preview before making changes        
		try {
			mCamera.stopPreview();
		} catch (Exception e) {
			// ignore: tried to stop a non-existent preview
		}
		
		// set preview size and make any resize, rotate or        
		// reformatting changes here
		
		// start preview with new settings
		
		try {
			mCamera.setPreviewDisplay(mHolder);            
			mCamera.startPreview();
		} catch (Exception e) {
			Log.d("Eception e", "Error starting camera preview: " + e.getMessage());
		}
	}
	
	
} // < ------ end of class 