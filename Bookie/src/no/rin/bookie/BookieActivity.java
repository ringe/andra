package no.rin.bookie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BookieActivity extends Activity implements OnClickListener {
	private Button newbook;
	private Button deletebook;
	private View bookName;
	private Button getbooks;

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
		setContentView(R.layout.main);

		bookName = findViewById(R.id.bookName);



		newbook = (Button) findViewById(R.id.newbook);
		newbook.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(v.getContext(), NewBookActivity.class);
				startActivityForResult(myIntent, 0);

			}
		}
				);

		deletebook = (Button) findViewById(R.id.deletebook);
		deletebook.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(v.getContext(), DeleteBookActivity.class);
				startActivityForResult(myIntent, 0);


			}
		}
				);
		
		getbooks = (Button) findViewById(R.id.getbooks);
		getbooks.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(v.getContext(), BookListActivity.class);
				startActivityForResult(myIntent, 0);


			}
		}
				);

	}

	@Override
	public void onActivityResult(int req, int res, Intent data){
		super.onActivityResult(req, res, data);
		switch(req){
			case(0) : {
				bookName = findViewById(R.id.bookName);
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
	
	@Override
	public void onResume() {
		super.onResume();
		
		
	}

	@Override
	public void onClick(View v) {
		Intent myIntent = new Intent(v.getContext(), NewBookActivity.class);
		startActivityForResult(myIntent, 0);
	}
}