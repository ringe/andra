package no.rin.convyrt;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

/*
 * The ConvyrtActivity shows a unit conversion tool
 */
public class ConvyrtActivity extends Activity {
	private Spinner mSpinner;
	private Spinner uSpinner;
	private EditText aText;
	private ListView theResults;
	private Locale myLocale;

	/*
	 * Show Toast.
	 */
	void showToast(CharSequence msg) {
	        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	 }
	
	/** Called to perform conversion 
	 * @throws Exception */
	public void DoConversion() throws Exception {
		Converter converter = new Converter();
		int m = mSpinner.getSelectedItemPosition();
		int u = uSpinner.getSelectedItemPosition();
		
		// Extract amount for conversion
		int amount =  Integer.parseInt(aText.getText().toString());
		
		// Use english field names for converting
		Resources res = getResources();
		enableEnglish(res);
		String[] measures = res.getStringArray(R.array.measures_array);
		String measure = measures[m];
		String uri = "array/" + measure.toLowerCase() + "_array";
	    int unit_array = res.getIdentifier(uri, null, getPackageName());
		String[] units = res.getStringArray(unit_array);
		String unit = units[u];
		
		// Localize output
		disableEnglish(res);
		units = res.getStringArray(unit_array);
		
		// Make conversion
		ArrayList<String> out = converter.convert(measure, unit, amount, units);
		theResults.setAdapter(new ArrayAdapter<String>(this, R.layout.list_view, out));
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Prepare fields
        mSpinner = (Spinner) findViewById(R.id.measures);
        uSpinner = (Spinner) findViewById(R.id.units);
        aText = (EditText) findViewById(R.id.amount);
        theResults = (ListView) findViewById(R.id.results);
        
        // Load measure values to the measures spinner
        ArrayAdapter<CharSequence> mAdapter = ArrayAdapter.createFromResource(
                this, R.array.measures_array, android.R.layout.simple_spinner_item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mAdapter);
        
        // Make changes on measures selection
        mSpinner.setOnItemSelectedListener(
        		new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int selected, long id) {
						// Get resources
						Resources res = getResources();
						
						// Get selected measure
						String[] measures = res.getStringArray(R.array.measures_array);
						String sel = measures[selected];
						
						// Display which measure was selected
						showToast("Convyrt " + sel);
						
						// Get english unit array identifier
						enableEnglish(res);
						String[] ms = res.getStringArray(R.array.measures_array);
						String sels = ms[selected];
						String uri = "array/" + sels.toLowerCase() + "_array";
						disableEnglish(res);
						
						// Find the unit array id
					    int unit_array = res.getIdentifier(uri, null, getPackageName());
					    
				        // Load unit values to the units spinner
				        ArrayAdapter<CharSequence> uAdapter = ArrayAdapter.createFromResource(
				                parent.getContext(), unit_array, android.R.layout.simple_spinner_item);
				        uAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				        uSpinner.setAdapter(uAdapter);
				        
				        // Perform initial conversion
				        try {
							DoConversion();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// nothing
					}
        		}
        );
        
        // Convert when unit choice changes.
        uSpinner.setOnItemSelectedListener(
        		new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int selected, long id) {
						try {
							DoConversion();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// nothing
					}}
		);
        
        // Convert when amount changes
        aText.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {
				// nothing
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// nothing
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				try {
					DoConversion();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
        	
        });
    }
    
    /*
     * Enable English names on resources
     */
    private void enableEnglish(Resources res)  {
    	myLocale = Locale.getDefault();
		Configuration newConfig = new Configuration(res.getConfiguration());
		newConfig.locale = Locale.ENGLISH;
		res.updateConfiguration(newConfig, null);
    }
    
    /*
     *  Reset the user locale
     */
    private void disableEnglish(Resources res) {
    	Configuration newConfig = new Configuration(res.getConfiguration());
		newConfig.locale = myLocale;
		res.updateConfiguration(newConfig, null);
    }
}