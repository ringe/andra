package no.rin.bookie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewBookActivity extends Activity  implements OnClickListener {

	private EditText title;
	private Button save;
	public static final String NEW_BOOK = "no.rin.bookie.NEW_BOOK";
	


	/*
	 * Show Toast.
	 */
	void showToast(CharSequence msg) {
	        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	 }
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.newbook);
        
        title = (EditText) findViewById(R.id.title); 
        
        save = (Button) findViewById(R.id.savebook);
        save.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		Resources res = getResources();
		String FILENAME = res.getString(R.string.filename);
		String string = title.getText().toString();

		FileOutputStream fos;
		try {
			fos = openFileOutput(FILENAME, Context.MODE_APPEND);
			fos.write(string.getBytes());
			//showToast("saved?");
			fos.close();		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    	Intent intent = new Intent(NEW_BOOK);
    	intent.putExtra("book", string);
    	sendBroadcast(intent);
    	
		finish();
	}
}
