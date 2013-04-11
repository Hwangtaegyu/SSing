package com.example.uidesign;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity; 
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Tab2Activity extends Activity 
{
	String sql, phone_num;
	Cursor cursor;
	ListView lv;
	ArrayList addList = new ArrayList();
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab2);
        
        Typeface face=Typeface.createFromAsset(getAssets(), "fonts/DroidSans.ttf");
        lv = (ListView)findViewById(R.id.listView1);
       
        SQLiteOpenHelper dbHelper = new DBManager(Tab2Activity.this, "reg.db");
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		sql = "select *from members;";
	    	
	    cursor = db.rawQuery(sql, null);
	    if(cursor.getCount()>0){
	     startManagingCursor(cursor);
	     DBAdapter dbAdapter = new DBAdapter(Tab2Activity.this, cursor);
	     lv.setAdapter(dbAdapter);
	    }
       
	    lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				cursor.moveToPosition(position);
				// TODO Auto-generated method stub
				AlertDialog alert = new AlertDialog.Builder(Tab2Activity.this)
                // �޽����� �����Ѵ�.
                .setMessage("�����Ͻðڽ��ϱ�?")
                // positive ��ư�� �����Ѵ�.
                .setPositiveButton("Ȯ��", new DialogInterface.OnClickListener()
                {
                    // positive ��ư�� ���� Ŭ�� �̺�Ʈ ó���� �����Ѵ�.
                    public void onClick(DialogInterface dialog, int which)
                    {
                    	
                    	phone_num = cursor.getString(cursor.getColumnIndex("phone_num"));
                    	
                    	SQLiteOpenHelper dbHelper = new DBManager(Tab2Activity.this, "reg.db");
                		SQLiteDatabase db = dbHelper.getWritableDatabase();
                		
                		sql = "delete from members where phone_num = '"+phone_num+"';";
                		db.execSQL(sql);
                        // ���̾�α׸� ȭ�鿡�� ������� �Ѵ�.
                        dialog.dismiss();
//                                  			
            			sql = "select *from members;";
            	    	
            		    cursor = db.rawQuery(sql, null);
            		    if(cursor.getCount() > 0){
            		     startManagingCursor(cursor);
            		     DBAdapter dbAdapter = new DBAdapter(Tab2Activity.this, cursor);
            		     lv.setAdapter(dbAdapter);
            		    }
                        Toast.makeText(Tab2Activity.this, "�����Ͽ����ϴ�.", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("���", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						 dialog.dismiss();
					}
				})
                // ������ ���� ���� �˷� ���̾�α׸� ȭ�鿡 �����ش�.
                .show();
				return false;
			}
		});
        
    }
		
	  @Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			SQLiteOpenHelper dbHelper = new DBManager(Tab2Activity.this, "reg.db");
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			sql = "select *from members;";
		    cursor = db.rawQuery(sql, null);
		    
		    if(cursor.getCount()>0){
		    	 startManagingCursor(cursor);
		    	 DBAdapter dbAdapter = new DBAdapter(Tab2Activity.this, cursor);
		    	 lv.setAdapter(dbAdapter);
			}else if(cursor.getCount() == 0){
				
			}
		}
 	public boolean onCreateOptionsMenu(Menu menu) {
 		menu.add(0, 0, 0, "���");
// 		menu.add(0, 1, 0, "����");
 		
 		return true;
 	}
 	
 	public boolean onOptionsItemSelected(MenuItem item){
    	switch (item.getItemId()){
    	case 0:
    		Intent intent = new Intent(Tab2Activity.this, PhoneAddressActivity.class);
        	startActivity(intent);
    		break;
//    	case 1:
//    		Intent intent2 = new Intent(Tab2Activity.this, DeleteAddressActivity.class);
//        	startActivity(intent2);
//    		break;
    	
    	}
    	return true;
    }
}
