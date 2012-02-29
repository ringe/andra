package no.rin.bookie;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.AdapterView.OnItemClickListener;

public class BookListActivity extends Activity implements OnClickListener {
    private Button newbook;
    private ListView booklist;
	private ToggleButton deletebook;
	
	/*
	 * Show Toast.
	 */
	void showToast(CharSequence msg) {
	        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	 }

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        
        booklist = (ListView) findViewById(R.id.booklist);
        booklist.setOnItemClickListener(new OnItemClickListener() {
        	

			private Intent resultIntent;

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos,
					long arg3) {
				String a = (String) booklist.getItemAtPosition(pos);
				showToast(a);
				resultIntent = new Intent();
				resultIntent.putExtra("book", a);
				setResult(Activity.RESULT_OK, resultIntent);
				finish();
			}
			
			
        });
        
        
        
        
        newbook = (Button) findViewById(R.id.newbook);
        newbook.setOnClickListener(this);
        
        deletebook = (ToggleButton) findViewById(R.id.deletebook);
        deletebook.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		finish();
        		Intent myIntent = new Intent(v.getContext(), DeleteBookActivity.class);
                startActivityForResult(myIntent, 0);
                
        	
   }
}
        );
        
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	Resources res = getResources();
		String FILENAME = res.getString(R.string.filename);
		
		deletebook = (ToggleButton) findViewById(R.id.deletebook);
		deletebook.setChecked(false);
		
		try {
			FileInputStream fis = openFileInput(FILENAME);
			DataInputStream in = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String strLine;
			ArrayList<String> out = new ArrayList<String>();
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
				out.add(strLine);
			}
			
	 		booklist.setAdapter(new ArrayAdapter<String>(this, R.layout.book, out));
			  
			//Close the input stream
			in.close();
			fis.close();		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	@Override
	public void onClick(View v) {
		Intent myIntent = new Intent(v.getContext(), NewBookActivity.class);
        startActivityForResult(myIntent, 0);
	}
}