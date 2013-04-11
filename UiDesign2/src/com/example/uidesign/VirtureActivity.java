// ������ȭ ȭ��, Ŭ�� �ÿ� ���Ҹ� ��� �� �ߴ��� chgView �޼����� case ���� �����ϸ� ��

package com.example.uidesign;

import android.app.Activity; 
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

public class VirtureActivity extends Activity implements View.OnClickListener
{
	
	ImageView img;
	Drawable a1, a2, a3, a4;
	int cnt;
	Intent intent;
	
 @Override
    public void onCreate(Bundle savedInstanceState) 
 	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab3);       
	        
        cnt = 0;
        img = (ImageView)findViewById(R.id.imageView1);
        a1 = this.getResources().getDrawable(R.drawable.incall);
        a2 = this.getResources().getDrawable(R.drawable.call);
        a3 = this.getResources().getDrawable(R.drawable.discall);
        a4 = this.getResources().getDrawable(R.drawable.black);
       
	    img.setClickable(true);
	    img.setOnClickListener(this);
        
    }

 	@Override
 	protected void onResume() {
	// TODO Auto-generated method stub
 		super.onResume();
 		
 		startService(new Intent("com.sound"));
 		
 	}

	public void onClick(View v) 
 	{
 		chgView();
 	}
 	
 	private void chgView()
 	{
 		switch (cnt)
 		{
 			case 0: // ��ȭ�� ȭ��
 				
 				img.setImageDrawable(a2);
 				cnt = 1;
 				stopService(new Intent("com.sound"));
 				Handler mHandler1 = new Handler();
 		        mHandler1.postDelayed(new Runnable() {
 		          public void run()
 		          {
 		        	img.setImageDrawable(a4);
 		          }
 		        }, 1000);
 				break;
 				
 			case 1:
 				img.setImageDrawable(a2);
 				cnt = 2;
 				break;
 				
 			case 2: // ��ȭ���� ȭ��
 				img.setImageDrawable(a3);
 				cnt = 3;
 				Handler mHandler = new Handler();
 		        mHandler.postDelayed(new Runnable() {
 		          public void run()
 		          {
 		        	 finish();
 		          }
 		        }, 2000);
 				break;
 				
 			case 3: // ��ȭ���� ȭ��
 				finish();
 				break;
 			case 4:
 				
 				break;
 		}
 	}
}