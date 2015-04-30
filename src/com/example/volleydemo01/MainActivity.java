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
				//使用ImageRequest来加载网络图片的步骤如下：
				//第一步：获取RequestQueue对象实例
				//第二步：获取ImageRequest对象实例
				/*ImageRequest的构造函数中有6个参数，
				 * 第一个参数就是图片的URL地址。
				 * 第二个参数是图片请求成功的回调，这里我们把返回的Bitmap参数设置到ImageView中。
				 * 第三第四个参数分别用于指定允许图片最大的宽度和高度，如果指定的网络图片的宽度或高度大于这里的最大值，则会对图片进行压缩，指定成0的话就表示不管图片有多大，都不会进行压缩。
				 * 第五个参数用于指定图片的颜色属性，Bitmap.Config下的几个常量都可以在这里使用，其中ARGB_8888可以展示最好的颜色属性，每个图片像素占据4个字节的大小，而RGB_565则表示每个图片像素占据2个字节大小。
				 * 第六个参数是图片请求失败的回调，这里我们当请求失败时在ImageView中显示一张默认图片。
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
				//使用ImageLoader加载图片的步骤如下
				//第一步：实例化一个RequestQueue对象
				mQueue=Volley.newRequestQueue(MainActivity.this);
				//第二步：实例化一个ImageLoader对象，接受两个参数
				/*
				 * 第一个参数为：RequestQueue对象，第二个参数为ImageCache对象，这里是一个空对象
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
				
				imageLoaderButton.setText("正在申请数据");
				//第三步：实例化一个ImageListener对象
				/*
				 * 接受三个参数：第一个参数为要显示图片的ImageView控件实例，
				 * 第二个参数:指定加载图片的过程中显示的图片
				 * 第三个参数:指定加载图片失败的情况下显示的图片
				 * 
				 * */
				ImageListener imageListener=ImageLoader.getImageListener(imageView, R.drawable.ic_launcher, R.drawable.ic_launcher);
				//第四步：调用ImageLoader的get()方法来加载图片
				imageLoader.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", imageListener);
				imageLoaderButton.setText("申请数据完毕");
			}
		});
		
	}


}
