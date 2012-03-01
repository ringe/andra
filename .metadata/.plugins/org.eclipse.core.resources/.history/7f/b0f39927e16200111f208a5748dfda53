package no.rin.bookieb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Reciever extends BroadcastReceiver {
	
	private Toast toast;

	@Override
	public void onReceive(Context context, Intent intent) {
		
		String book = intent.getStringExtra("book");
		int duration = Toast.LENGTH_SHORT;
		toast = Toast.makeText(context, book, duration);
		toast.show();
	}

}
