package no.rin.bookieb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Reciever extends BroadcastReceiver {
	
	
	CharSequence toastText = "You added this book: ";
	int duration = Toast.LENGTH_LONG;
	private Toast toast;

	@Override
	public void onReceive(Context context, Intent intent) {
		
		String book = intent.getStringExtra("book");
		
		toast = Toast.makeText(context, toastText + book, duration);
		toast.show();
	}

}
