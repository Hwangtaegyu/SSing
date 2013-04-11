package com.example.uidesign;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class PhoneAddressActivity extends Activity {
	  ListView listPerson;
	  ArrayList listNumber = new ArrayList();
	  ArrayList listName = new ArrayList();
//	  ArrayList persons = new ArrayList();
	  ArrayList uNumber = new ArrayList();
	  ArrayList uName = new ArrayList();
	  ArrayList searchList = new ArrayList();
	  ArrayList searchListNum = new ArrayList();
	  ArrayList check = new ArrayList();
	  
	  boolean searchTrue;
	  Button btn;
	  String sql;
	  EditText edit;
	  Button search_btn;
	  
	

		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_phone_address);
	        btn = (Button)findViewById(R.id.btn_add);
	        listPerson = (ListView)findViewById(R.id.list_view);
	        getList();
	        searchTrue = false;
	        search_btn = (Button)findViewById(R.id.search_btn);
	        edit = (EditText)findViewById(R.id.search_edit);
	        
	        listPerson.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	        
	        btn.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {

					SQLiteOpenHelper dbHelper = new DBManager(PhoneAddressActivity.this, "reg.db");
					SQLiteDatabase db = dbHelper.getWritableDatabase();
								        
			       
			        if(searchTrue == true){
			        	//�˻������� listview
			        	for(int i=0; i<searchList.size(); i++){
							if(listPerson.isItemChecked(i) == true){
								listName.add(searchList.get(i).toString());
								listNumber.add(searchListNum.get(i).toString());
							}
						}
						 for(int i=0; i<listName.size(); i++){
					    		sql = "insert into members values(null, '"+listNumber.get(i).toString()+"', '"+listName.get(i).toString()+"');";
					    		db.execSQL(sql);
					    }
			        }else{
			        	//�˻� ������ �� listview
			        	for(int i=0; i<uName.size(); i++){
							if(listPerson.isItemChecked(i) == true){
								listName.add(uName.get(i).toString());
								listNumber.add(uNumber.get(i).toString());
							}
						}
						 for(int i=0; i<listName.size(); i++){
					    		sql = "insert into members values(null, '"+listNumber.get(i).toString()+"', '"+listName.get(i).toString()+"');";
					    		db.execSQL(sql);
					    }
			        }
					
			    	finish();
				}
			});
	        
	        search_btn.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					String search;
					search = edit.getText().toString();
					if(search.equals(null)){//�˻�â�� �ƹ��͵� �Է¾����� ��
						searchTrue = false;
						Toast.makeText(PhoneAddressActivity.this, "null", Toast.LENGTH_LONG).show();
						ArrayAdapter adp = new ArrayAdapter(PhoneAddressActivity.this, android.R.layout.simple_list_item_multiple_choice, uName);
						//����Ʈ�信 ǥ��
						listPerson.setAdapter(adp);
					}else{
						searchTrue = true;
						for(int i=0; i<uName.size(); i++){
							if(search.equals(uName.get(i).toString())){
								searchList.add(uName.get(i).toString());
								searchListNum.add(uNumber.get(i).toString());
							}
						}
						if(searchList.size() == 0){
							Toast.makeText(PhoneAddressActivity.this, "�˻������ �����ϴ�.", Toast.LENGTH_LONG).show();
							searchTrue = false;
						}else{
							ArrayAdapter adp = new ArrayAdapter(PhoneAddressActivity.this, android.R.layout.simple_list_item_multiple_choice, searchList);
							//����Ʈ�信 ǥ��
							listPerson.setAdapter(adp);
						}
					}
				}
			});
	        
//	        listPerson.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//
//	        	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
//	        		
//	        			
//	        	}
//	        });
	        
	        listPerson.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					if(searchTrue == true){
						AlertDialog alert = new AlertDialog.Builder(PhoneAddressActivity.this)
		                // �޽����� �����Ѵ�.
		                .setTitle(searchList.get(position).toString())
		                .setMessage(searchListNum.get(position).toString())
		                
		                // positive ��ư�� �����Ѵ�.
		                .setPositiveButton("Ȯ��", new DialogInterface.OnClickListener()
		                {
		                    // positive ��ư�� ���� Ŭ�� �̺�Ʈ ó���� �����Ѵ�.
		                    public void onClick(DialogInterface dialog, int which)
		                    {
		                    	dialog.dismiss();
		                    }
		                }).show();
					}else{
						AlertDialog alert = new AlertDialog.Builder(PhoneAddressActivity.this)
		                // �޽����� �����Ѵ�.
		                .setTitle(uName.get(position).toString())
		                .setMessage(uNumber.get(position).toString())
		                
		                // positive ��ư�� �����Ѵ�.
		                .setPositiveButton("Ȯ��", new DialogInterface.OnClickListener()
		                {
		                    // positive ��ư�� ���� Ŭ�� �̺�Ʈ ó���� �����Ѵ�.
		                    public void onClick(DialogInterface dialog, int which)
		                    {
		                    	dialog.dismiss();
		                    }
		                }).show();
					}
					
	                
	                
	                // ������ ���� ���� �˷� ���̾�α׸� ȭ�鿡 �����ش�.
	                 
					
					return false;
				}
	        	
			});
	        
	       
	    }
	    
	    
	    public void getList(){
	        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
	        String[] projection = new String[] {           
	               ContactsContract.CommonDataKinds.Phone.NUMBER,
	               ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
	       };
	         
	       String[] selectionArgs = null;
	       //����
	       String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
	        //��ȸ�ؼ� �����´�
	       Cursor contactCursor = managedQuery(uri, projection, null, selectionArgs, sortOrder);
	       //������ ���� array ����
	       if(contactCursor.moveToFirst()){       
	    	   do{
//	    		   persons.add(contactCursor.getString(1)); 
	               uNumber.add(contactCursor.getString(0));
	               uName.add(contactCursor.getString(1));
	           }while(contactCursor.moveToNext());

	       }
	       //����Ʈ�� ������ adapter ����      
	       ArrayAdapter adp = new ArrayAdapter(PhoneAddressActivity.this, android.R.layout.simple_list_item_multiple_choice, uName);
	       //����Ʈ�信 ǥ��
	       listPerson.setAdapter(adp);
	   }
}