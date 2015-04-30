package com.example.volleydemo01;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends ActionBarActivity {

	private Button imageRequestButton,imageLoaderButton;
	private ImageView imageView;
	private RequestQueue mQueue;
	private ImageRequest imageRequest;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageRequestButton=(Button)findViewById(R.id.buttonId);
		imageLoaderButton=(Button)findViewById(R.id.imageLoader);
		imageView=(ImageView)findViewById(R.id.imageViewId);
		
		imageRequestButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//ʹ��ImageRequest����������ͼƬ�Ĳ������£�
				//��һ������ȡRequestQueue����ʵ��
				//�ڶ�������ȡImageRequest����ʵ��
				/*ImageRequest�Ĺ��캯������6��������
				 * ��һ����������ͼƬ��URL��ַ��
				 * �ڶ���������ͼƬ����ɹ��Ļص����������ǰѷ��ص�Bitmap�������õ�ImageView�С�
				 * �������ĸ������ֱ�����ָ������ͼƬ���Ŀ�Ⱥ͸߶ȣ����ָ��������ͼƬ�Ŀ�Ȼ�߶ȴ�����������ֵ������ͼƬ����ѹ����ָ����0�Ļ��ͱ�ʾ����ͼƬ�ж�󣬶��������ѹ����
				 * �������������ָ��ͼƬ����ɫ���ԣ�Bitmap.Config�µļ�������������������ʹ�ã�����ARGB_8888����չʾ��õ���ɫ���ԣ�ÿ��ͼƬ����ռ��4���ֽڵĴ�С����RGB_565���ʾÿ��ͼƬ����ռ��2���ֽڴ�С��
				 * ������������ͼƬ����ʧ�ܵĻص����������ǵ�����ʧ��ʱ��ImageView����ʾһ��Ĭ��ͼƬ��
				 * 
				 * */
				mQueue=Volley.newRequestQueue(MainActivity.this);
				imageRequest=new ImageRequest("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", new Response.Listener<Bitmap>() {

					@Override
					public void onResponse(Bitmap response) {
						// TODO Auto-generated method stub
						imageView.setImageBitmap(response);
						
					}
				}, 0, 0,Config.RGB_565 ,new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						imageRequestButton.setText("error");
						imageView.setImageResource(R.drawable.ic_launcher);
						
					}
				});
				mQueue.add(imageRequest);
				
				
				
			}
		});
		imageLoaderButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//ʹ��ImageLoader����ͼƬ�Ĳ�������
				//��һ����ʵ����һ��RequestQueue����
				mQueue=Volley.newRequestQueue(MainActivity.this);
				//�ڶ�����ʵ����һ��ImageLoader���󣬽�����������
				/*
				 * ��һ������Ϊ��RequestQueue���󣬵ڶ�������ΪImageCache����������һ���ն���
				 * 
				 * */
				ImageLoader imageLoader=new ImageLoader(mQueue, new ImageCache() {
					
					@Override
					public void putBitmap(String url, Bitmap bitmap) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public Bitmap getBitmap(String url) {
						// TODO Auto-generated method stub
						return null;
					}
				});
				
				imageLoaderButton.setText("������������");
				//��������ʵ����һ��ImageListener����
				/*
				 * ����������������һ������ΪҪ��ʾͼƬ��ImageView�ؼ�ʵ����
				 * �ڶ�������:ָ������ͼƬ�Ĺ�������ʾ��ͼƬ
				 * ����������:ָ������ͼƬʧ�ܵ��������ʾ��ͼƬ
				 * 
				 * */
				ImageListener imageListener=ImageLoader.getImageListener(imageView, R.drawable.ic_launcher, R.drawable.ic_launcher);
				//���Ĳ�������ImageLoader��get()����������ͼƬ
				imageLoader.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", imageListener);
				imageLoaderButton.setText("�����������");
			}
		});
		
	}


}
