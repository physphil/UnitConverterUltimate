package com.physphil.android.unitconverterultimate;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.physphil.android.unitconverterultimate.ui.ConverterPagerAdapter;
import com.physphil.android.unitconverterultimate.ui.SetDecimalSeparatorDialogFragment;
import com.physphil.android.unitconverterultimate.ui.SetDecimalsDialogFragment;
import com.physphil.android.unitconverterultimate.ui.SetSeparatorDialogFragment;
import com.physphil.android.unitconverterultimate.util.Constants;
import com.physphil.android.unitconverterultimate.util.Conversions;
import com.physphil.android.unitconverterultimate.util.Convert;
import com.physphil.android.unitconverterultimate.util.Globals;
import com.physphil.android.unitconverterultimate.util.Util;


public class MainActivity extends ActionBarActivity{
	
	private static final int RATE_LIMIT = 40;
	
	private int selectedConversion = 0;
	private ActionBar actionBar;
	private FragmentManager fragmentManager;
	private ConverterPagerAdapter fragmentPagerAdapter;
	private ViewPager viewPager;
	private int theme;
	private SharedPreferences preferences;
	
	//Class variables required for navigation drawer implementation
	private ListView mDrawerList;
	private String[] mDrawerOptions;
	private DrawerLayout mDrawerLayout;
    private RelativeLayout mNavigationDrawer;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		preferences = getPreferences(MODE_PRIVATE);
		theme = preferences.getInt(Constants.SETTINGS_THEME, Constants.THEME_DARK);
		
		if (theme == Constants.THEME_LIGHT){
			this.setTheme(R.style.AppBaseTheme_Light);
		}
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//If Holo light theme is used, use light icons and change SWAP text color 
		if(theme == Constants.THEME_LIGHT){			
			ImageView swapImage = (ImageView) findViewById(R.id.swapImage);
			swapImage.setImageResource(R.drawable.ic_swap_light);
			
			TextView swapText = (TextView) findViewById(R.id.swapText);
			swapText.setTextColor(getResources().getColor(R.color.icsGray));
		}
		
		//Use v7-appcompat library for action bar support dating back to API level 7
		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		
		//Initialize fragment manager and pager adapter to handle swipe navigation
		fragmentManager = getSupportFragmentManager();
		fragmentPagerAdapter = new ConverterPagerAdapter(fragmentManager, this);
		
		//Create reference to pager and assign listener to respond to swipes
		viewPager = (ViewPager) findViewById(R.id.mainViewPager);
		ViewPager.SimpleOnPageChangeListener pageChangeListener = new ViewPager.SimpleOnPageChangeListener(){
			
			@Override
        	public void onPageSelected(int position){
				super.onPageSelected(position);
				//Send currently focused view to drop-down listener
				mDrawerList.performItemClick(mDrawerList.getAdapter().getView(position, null, null), position, mDrawerList.getAdapter().getItemId(position));
			}
		};
				
		viewPager.setOnPageChangeListener(pageChangeListener);
		viewPager.setAdapter(fragmentPagerAdapter);
				
		//Initialize drawer list and set adapter for list view
		mDrawerOptions = getResources().getStringArray(R.array.conversions);
		mDrawerList = (ListView) findViewById(R.id.nav_drawer);
		mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mDrawerOptions));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerList.setDivider(null);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.mainLayout);
        mNavigationDrawer = (RelativeLayout) findViewById(R.id.navigation_drawer);
        TextView header = (TextView) findViewById(R.id.drawer_header);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlayStore("com.physphil.android.shareables");
                mDrawerLayout.closeDrawer(mNavigationDrawer);
            }
        });

		//Adjust background of navigation drawer for light theme
		if(theme == Constants.THEME_LIGHT){
			mNavigationDrawer.setBackgroundColor(getResources().getColor(R.color.icsWhite));
//			mDrawerList.setBackgroundColor(getResources().getColor(R.color.icsWhite));
		}
		
		//Define drawer toggle to adjust action bar when drawer is opened/closed
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawerOpen, R.string.drawerClose){
			
			public void onDrawerClosed(View view){
				actionBar.setTitle(mTitle);
			}
			
			public void onDrawerOpened(View view){
				actionBar.setTitle(R.string.app_name);
			}
		};
		
		mDrawerLayout.setDrawerListener(mDrawerToggle);
				
		//Disable on-screen soft keyboard when user clicks on toValue EditText view
		EditText toValueText = (EditText) findViewById(R.id.toValue);
		toValueText.setInputType(InputType.TYPE_NULL);

		//Add long-click listener to EditText view to copy value to clipboard
		toValueText.setOnLongClickListener(new View.OnLongClickListener() {			
			@Override
			public boolean onLongClick(View view) {
				copyToClipboard(view);
				return false;
			}
		});
		
		//Instantiate conversions HashMap
		Globals.conversions = Conversions.getInstance().getConversions();
	}


    @Override
	public void onResume(){
		super.onResume();
		
		//Restore UI State for FromValue and Selected Conversion from Shared Preferences file, default to 0 for both if nothing saved
		EditText fromValueText = (EditText) findViewById(R.id.fromValue);
		
		int conversion = preferences.getInt(Constants.SETTINGS_CONVERSION, 0);
		String fromValue = preferences.getString(Constants.SETTINGS_FROMVALUE, null); //default value for fromValue comes from xml layout
	
		if (fromValue != null){
			fromValueText.setText(fromValue);
			fromValueText.setSelection(fromValueText.getText().length());
		}		

		//Add listener to text input field, convert value on each character change. 
		//Listener added after fromValue is set to default, Convert will be called from onVisible() when fragment is shown
		fromValueText.addTextChangedListener(fromValueTextWatcher);
		
		//Restore selected conversion
		mDrawerList.performItemClick(mDrawerList.getAdapter().getView(conversion, null, null), conversion, mDrawerList.getAdapter().getItemId(conversion));
		
		//Display dialog to inform user of new navigation
		boolean firstRun = preferences.getBoolean(Constants.SETTINGS_SHOW_NAV_DIALOG, true);
		if(firstRun){
			//Display dialog to indicate new navigation method
			showNavigationDialog();
		}
		
		// Check if it's time to ask user to rate app
		checkToRate();	
	}
	
	@Override
	public void onPause(){
		super.onPause();
		
		//Save the current UI state of FromValue and Selected Conversion to shared preferences file, to be restored on app restart
		EditText fromValueText = (EditText) findViewById(R.id.fromValue);
		String fromValue = fromValueText.getText().toString();
		
		SharedPreferences.Editor editor = preferences.edit();
		editor.putInt(Constants.SETTINGS_CONVERSION, selectedConversion);
		editor.putString(Constants.SETTINGS_FROMVALUE, fromValue);
		editor.commit();
		
		fromValueText.removeTextChangedListener(fromValueTextWatcher);
	}

    @Override
	protected void onPostCreate(Bundle savedInstanceState){
		super.onPostCreate(savedInstanceState);
		//Sync toggle state after onRestoreInstanceState has occurred
		mDrawerToggle.syncState();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig){
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	@Override
	public void setTitle(CharSequence title){
		mTitle = title;
		actionBar.setTitle(title);
	}
	
	/**
	 * Show New Navigation Dialog to user the first time the app is opened
	 */
	private void showNavigationDialog(){
		
		new AlertDialog.Builder(this)
			.setTitle(R.string.dialogNewNavTitle)
			.setMessage(R.string.dialogNewNavMessage)
			.setPositiveButton(R.string.buttonOK, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					// Mark in shared preferences so dialog is not shown again
					SharedPreferences.Editor editor = preferences.edit();
					editor.putBoolean(Constants.SETTINGS_SHOW_NAV_DIALOG, false);
					editor.commit();					
				}
			})
			.setCancelable(false)
			.show();
	}
	
	//Define listener to convert value whenever from value changes
	public TextWatcher fromValueTextWatcher = new TextWatcher(){

		@Override
		public void afterTextChanged(Editable s) {
			convert();
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};
	
	private void convert(){
		//Get radiogroup IDs of selected conversion		
		int fromGroupId = getRadioGroupId(true);
		int toGroupId = getRadioGroupId(false);
		
		//String[] mConversions = getResources().getStringArray(R.array.conversions);
		
		//Call appropriate convert function based on selected conversion
		if(selectedConversion == Conversions.TEMPERATURE){
			Convert.convertTempValue(this);
		}
		else{
			Convert.convertValue(this, fromGroupId, toGroupId);			
		}
	}
	
	//Called from SWAP ImageView in xml
	public void swapValues(View view){
		
		RadioGroup fromButtons = (RadioGroup) findViewById(getRadioGroupId(true));
		RadioGroup toButtons = (RadioGroup) findViewById(getRadioGroupId(false));
		
		//Only proceed if radio buttons can be found
		if(fromButtons != null && toButtons != null){
			int fromId = fromButtons.getCheckedRadioButtonId();
			int toId = toButtons.getCheckedRadioButtonId();
			
			String fromIdString = getResources().getResourceName(fromId);
			String toIdString = getResources().getResourceName(toId);
			
			//Swap To and From string ID names
			String newToIdString = fromIdString.replaceFirst("from", "to");
			String newFromIdString = toIdString.replaceFirst("to", "from");
			
			//Get integer IDs corresponding to string ID names
			int newFromId = getResources().getIdentifier(newFromIdString, "id", this.getPackageName());
			int newToId = getResources().getIdentifier(newToIdString, "id", this.getPackageName());
			
			//Don't swap values if the from and to units are the same
			if(fromId != newFromId && toId != newToId){
				
				//Set actual buttons instead of using RadioGroup.check, as that calls onCheckedChangedListener 3 times
				RadioButton newFromButton = (RadioButton) findViewById(newFromId);
				RadioButton newToButton = (RadioButton) findViewById(newToId);
				
				//Setting new checked button will call onCheckedChangedListener, which will do conversion
				newFromButton.setChecked(true);
				newToButton.setChecked(true);
			}
		}
	}
	
	//Obtain radio group ID from resource array
	private int getRadioGroupId(boolean isFromUnit){
		int id = 0;
		TypedArray buttonGroupIds;
		
		if(isFromUnit){
			buttonGroupIds = getResources().obtainTypedArray(R.array.fromRadioGroupIds);
		}
		else{
			buttonGroupIds = getResources().obtainTypedArray(R.array.toRadioGroupIds);
		}
		
		id = buttonGroupIds.getResourceId(selectedConversion, Conversions.AREA);
		buttonGroupIds.recycle();
		return id;
	}
			
	//Copy result of conversion to clipboard on long press
	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void copyToClipboard(View v){
		int sdk = android.os.Build.VERSION.SDK_INT;
		EditText toValueText = (EditText) findViewById(R.id.toValue);
		
		//Use appropriate Android clipboard service based on SDK version
		if(sdk >= android.os.Build.VERSION_CODES.HONEYCOMB){
			android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
			ClipData clip = ClipData.newPlainText("Conversion Result", toValueText.getText().toString());
			clipboard.setPrimaryClip(clip);
		}
		else{
			android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
			clipboard.setText(toValueText.getText().toString());
		}
		Toast.makeText(this, getString(R.string.copiedToClipboard), Toast.LENGTH_SHORT).show();
	}
	
	@SuppressLint("NewApi")
	private void reload(){
		int apiLevel = android.os.Build.VERSION.SDK_INT;
		
		//For Honeycomb and up reset activity to apply new theme
		if (apiLevel >= android.os.Build.VERSION_CODES.HONEYCOMB){
			MainActivity.this.recreate();
		}
		//For Gingerbread and below, finish current activity and start it again to reset and apply new theme
		else{
			Intent restart = getIntent();
			restart.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			finish();
			startActivity(restart);
		}
	}
	
	//Start play store to rate app
	private void rateApp(){
		openPlayStore(getPackageName());
	}

	//Start play store to download shareables
	private void openPlayStore(String packageName){
		Uri uri = Uri.parse("market://details?id=" + packageName);
		Intent goToPlayStore = new Intent(Intent.ACTION_VIEW, uri);

		try{
			startActivity(goToPlayStore);
		}
		catch(ActivityNotFoundException e){
			Toast.makeText(this, getString(R.string.errorGooglePlay), Toast.LENGTH_LONG).show();
		}
	}
	
	/**
	 * Prompt user to rate app
	 */
	private void nagToRate(){
		
		new AlertDialog.Builder(this)
			.setTitle(R.string.dialogRateTitle)
			.setMessage(R.string.dialogRateMessage)
			.setPositiveButton(R.string.buttonRate, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which){
					rateApp();
					
					// Never ask user to rate again
					SharedPreferences.Editor editor = preferences.edit();
					editor.putBoolean(Constants.SETTINGS_HAS_RATED, true);
					editor.commit();
				}
			})
			.setNegativeButton(R.string.buttonNoThanks, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			})
			.setNeutralButton(R.string.buttonAlreadyDone, new DialogInterface.OnClickListener() {
								
				public void onClick(DialogInterface dialog, int which) {
					
					// Never ask to rate again
					SharedPreferences.Editor editor = preferences.edit();
					editor.putBoolean(Constants.SETTINGS_HAS_RATED, true);
					editor.commit();
				}
			})
			.show();
	}
	
	/**
	 * Check if it's time to prompt user to rate app. User should be prompted every RATE_LIMITth time app is opened, and if they have not rated before.
	 */
	private void checkToRate(){
		
		int openCount = preferences.getInt(Constants.SETTINGS_APP_OPEN_COUNT, RATE_LIMIT - 10);
		
		if(openCount >= RATE_LIMIT){
			
			openCount = 0;
			
			// If user has never rated before, ask to rate
			if(!preferences.getBoolean(Constants.SETTINGS_HAS_RATED, false)){
				
				nagToRate();				
			}
		}
		else{
			
			openCount++;
		}
		
		SharedPreferences.Editor editor = preferences.edit();
		editor.putInt(Constants.SETTINGS_APP_OPEN_COUNT, openCount);
		editor.commit();
	
	}
	
	//Send email to developer to request a new unit
	private void sendUnitRequest(){
		Intent sendRequest = new Intent(Intent.ACTION_SEND);
		sendRequest.setType("message/rfc822");
		sendRequest.putExtra(Intent.EXTRA_EMAIL, new String[]{Constants.EMAIL_ADDRESS});
		sendRequest.putExtra(Intent.EXTRA_SUBJECT, Constants.EMAIL_SUBJECT);
		
		try{
			startActivity(Intent.createChooser(sendRequest, getString(R.string.emailChooser)));
		}
		catch (android.content.ActivityNotFoundException ex){
			Toast.makeText(this, getString(R.string.errorNoEmailClient), Toast.LENGTH_LONG).show();
		}
	}

    /**
     * Start activity to display donation options
     */
    private void startDonationActivity(){
        startActivity(new Intent(this, DonateActivity.class));
    }

	/**
	 * Show help dialog to user
	 */
	private void showHelpDialog(){
		
		new AlertDialog.Builder(this)
			.setTitle(R.string.dialogHelp)
			.setMessage(R.string.dialogHelpText)
		
			.setNegativeButton(R.string.buttonOK, new DialogInterface.OnClickListener() {			
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//Exit back to main program
					dialog.cancel();
				}			
			})
			
			.show();
	}
	
	//Toggle between light and dark themes
	private void toggleTheme(){
		//Currently 0 = holo dark, 1 = holo light (dark action bar)

		SharedPreferences.Editor editor = preferences.edit();
		
		int currentTheme = preferences.getInt(Constants.SETTINGS_THEME, Constants.THEME_DARK);
		
		if (currentTheme == Constants.THEME_DARK){
			currentTheme = Constants.THEME_LIGHT;
		}
		else{
			currentTheme = Constants.THEME_DARK;
		}
		
		editor.putInt(Constants.SETTINGS_THEME, currentTheme);
		editor.commit();
		reload();
	}
	
	//Create bundle to be passed to dialog fragments, so the conversion can be called when the dialog is dismissed
	private Bundle createDialogBundle(){
		Bundle args = new Bundle();
		
		args.putInt(Constants.SETTINGS_CONVERSION, selectedConversion);
		args.putInt(Constants.FROM_GROUP_ID, getRadioGroupId(true));
		args.putInt(Constants.TO_GROUP_ID, getRadioGroupId(false));
		
		return args;
	}
	
	// Clear value currently in fromValue field
	private void clearValue(){
		TextView fromValue = (TextView) findViewById(R.id.fromValue);
		fromValue.setText("");
	}

    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		//getSupportMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
			
		//Display dialog corresponding to selected option from menu
		switch(item.getItemId()){
		
			case(android.R.id.home):
	
				//If drawer is open, close it, and vice versa
				if (mDrawerLayout.isDrawerOpen(mNavigationDrawer)) {
	                mDrawerLayout.closeDrawer(mNavigationDrawer);
	            } 
				else {
	                mDrawerLayout.openDrawer(mNavigationDrawer);
	            }
				break;
			
			case(R.id.menuSetDecimalPlaces):
				SetDecimalsDialogFragment decimalsFragment = new SetDecimalsDialogFragment();
				decimalsFragment.setArguments(createDialogBundle());
				decimalsFragment.show(fragmentManager, "setNoDecimals");
				break;
	
			case(R.id.menuSetSeparator):
				SetSeparatorDialogFragment separatorFragment = new SetSeparatorDialogFragment();
				separatorFragment.setArguments(createDialogBundle());
				separatorFragment.show(fragmentManager, "setSeparator");
				break;
	
			case(R.id.menuSetDecimalSeparator):
				SetDecimalSeparatorDialogFragment decimalSeparatorFragment = new SetDecimalSeparatorDialogFragment();
				decimalSeparatorFragment.setArguments(createDialogBundle());
				decimalSeparatorFragment.show(fragmentManager, "setDecimalSeparator");
				break;
	
			case(R.id.menuHelp):
//				HelpDialogFragment helpFragment = new HelpDialogFragment();
//				helpFragment.show(fragmentManager, "helpMenu");
				showHelpDialog();
				break;

            case(R.id.menuDonate):
                startDonationActivity();
                break;
	
			case(R.id.menuRate):
				rateApp();
				break;
	
			case(R.id.menuRequest):
				sendUnitRequest();
				break;
	
			case(R.id.menuToggle):
				toggleTheme();
				break;
				
			case(R.id.menuClear):
				clearValue();
				break;
	
			default:
				return super.onOptionsItemSelected(item);

		}
		return true;
	}
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            selectedConversion = position;
            EditText fromValue = (EditText) findViewById(R.id.fromValue);

            //Set view pager to selected item, which creates the fragment and caches the surrounding ones for smooth swiping
            //The fragment is always created by viewpager and visible before continuing in this listener
            viewPager.setCurrentItem(selectedConversion, false);

            //Call convert function from each fragment when it's visible. Viewpager has previously created selected fragment and surrounding ones
            int fromButtonGroupId = getRadioGroupId(true);
            int toButtonGroupId = getRadioGroupId(false);

            if (selectedConversion == Conversions.TEMPERATURE) {

                //Allow negative value inputs for each temperature, call direct temperature conversion
                fromValue.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                Convert.convertTempValue(MainActivity.this);
            } else {
                //Only allow positive values, call onFragmentVisible to handle general conversion case
                fromValue.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                Util.onFragmentVisible(MainActivity.this, fromButtonGroupId, toButtonGroupId);
            }

            //Highlight selected item, update title, close drawer
            mDrawerList.setItemChecked(position, true);
            setTitle(mDrawerOptions[position]);
//                mDrawerLayout.closeDrawer(mDrawerList);
            mDrawerLayout.closeDrawer(mNavigationDrawer);
		}
		
	}
}