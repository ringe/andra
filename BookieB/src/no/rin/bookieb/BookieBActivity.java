package no.rin.bookieb;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BookieBActivity extends Activity {
	private Button getbooks;
	private Button newbook;
	private View bookName;
	public static final String book = "no.rin.bookie.action.NEW_BOOK";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);


		getbooks = (Button) findViewById(R.id.getbooks);
		getbooks.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent();
				i.setClassName( "no.rin.bookie", "no.rin.bookie.BookListActivity" );
				startActivityForResult(i, 0);


			}
		}
				);
		
		newbook = (Button) findViewById(R.id.newbook);
		newbook.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent();
				i.setClassName( "no.rin.bookie", "no.rin.bookie.NewBookActivity" );
				startActivityForResult(i, 0);

			}
		}
				);


	}
	
	@Override
	public void onActivityResult(int req, int res, Intent data){
		super.onActivityResult(req, res, data);
		switch(req){
		case(0) : {
			bookName = findViewById(R.id.bookname);
			if(res == Activity.RESULT_OK){
				Bundle extras = getIntent().getExtras();
				if(data != null)
				{
					String s = data.getStringExtra("book");

					if(s != null)
						((TextView) bookName).setText(s);
				}
				//String s = (String) data.getDataString() ;

			}
		}
		}

	}

}