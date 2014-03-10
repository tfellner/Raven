package at.fellnertroyer.raven.layout;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import at.fellnertroyer.raven.R;
import at.fellnertroyer.raven.R.layout;
import at.fellnertroyer.raven.R.menu;

public class NewGroup extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_group);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_group, menu);
		return true;
	}

}
