package eu.laramartin.videogamequotes;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton sharingButton = new ImageButton(this);
        sharingButton.setLayoutParams(new ViewGroup.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
        sharingButton.setImageIcon(Icon.createWithContentUri("@android:drawable/ic_menu_share"));
    }

//    this method is called when "new quote" button is clicked

    public void newQuote(View view) {
        TextView quoteTextView = (TextView) findViewById(R.id.quote);
        TextView authorTextView = (TextView) findViewById(R.id.author);
        TextView videoGameTextView = (TextView) findViewById(R.id.video_game);
        quoteTextView.setText("We are moved to tears by the size of this thing.");
        authorTextView.setText("-- King of All Cosmos");
        videoGameTextView.setText("\uD83C\uDFAE Katamari Damacy");
    }

//    private ShareActionProvider mShareActionProvider;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu resource file.
        getMenuInflater().inflate(R.menu.menu, menu);

//        // Locate MenuItem with ShareActionProvider
//        MenuItem item = menu.findItem(R.id.menu_item_share);
//
//        // Fetch and store ShareActionProvider
//        mShareActionProvider = (ShareActionProvider) item.getActionProvider();

        // Return true to display menu
        return true;
    }

//    // Call to update the share intent
//    private void setShareIntent(Intent shareIntent) {
//        if (mShareActionProvider != null) {
//            mShareActionProvider.setShareIntent(shareIntent);
//        }
//    }
}

