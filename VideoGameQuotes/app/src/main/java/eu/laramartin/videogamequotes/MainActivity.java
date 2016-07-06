package eu.laramartin.videogamequotes;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    int lastRandomNum = 0;

//    Quote katamari = new Quote(
//            "We are moved to tears by the size of this thing.",
//            "King of All Cosmos",
//            "Katamari Damacy");
//
//    Quote lastOfUs = new Quote(
//            "Endure and survive.",
//            "Ellie",
//            "The Last of Us");
//
//    Quote borderlands2 = new Quote(
//            "Nothing is more badass than treating a woman with respect.",
//            "Mr. Torgue",
//            "Borderlands 2"
//            );
//
//    Quote mgs = new Quote(
//            "It's easy to forget what a sin is in the middle of a battlefield.",
//            "Solid Snake",
//            "Metal Gear Solid"
//            );
//
//    Quote marioBross = new Quote(
//            "Thank you Mario! But our Princess is in another castle!",
//            "Toad",
//            "Super Mario Bros."
//            );
//
//    Quote guildWars2 = new Quote(
//            "Knowledge is useless if it is not used.",
//            "Gixx",
//            "Guild Wars 2"
//            );
//
//    Quote persona4 = new Quote(
//            "Working for a better tomorrow isn't just one-time thing. The tomorrows keep coming.",
//            "Yu Narukami",
//            "Persona 4"
//            );
//
//    Quote danganronpa2 = new Quote(
//            "Belive in yourself... If you don't have that... it doesn't matter how many talents you have, you still won't be able to hold your head up high...",
//            "Chiaki",
//            "Danganronpa 2"
//            );
//
//    Quote zeroWing = new Quote(
//            "All your base are belong to us",
//            "Cats",
//            "Zero Wing"
//            );

    List<Quote> listOfQuotes = new ArrayList<>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    GameQuotes.initQuotes(listOfQuotes);

//    public void fillArray(){
//        listOfQuotes.add(katamari);
//        listOfQuotes.add(lastOfUs);
//        listOfQuotes.add(borderlands2);
//        listOfQuotes.add(mgs);
//        listOfQuotes.add(marioBross);
//        listOfQuotes.add(guildWars2);
//        listOfQuotes.add(persona4);
//        listOfQuotes.add(danganronpa2);
//        listOfQuotes.add(zeroWing);
//    }

    private int randomNumber() {
        Random r = new Random();
        int length = listOfQuotes.size();
        int num = r.nextInt(length - 1) + 1;
        if (num != lastRandomNum) {
            lastRandomNum = num;
            return num;
        } else {
            return randomNumber();
        }
    }

    private Quote getQuote() {
        Quote actualQuote = listOfQuotes.get(randomNumber());
        return actualQuote;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton sharingButton = new ImageButton(this);
        sharingButton.setLayoutParams(new ViewGroup.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
        sharingButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_share_black_24dp));
        sharingButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_info_white_24dp));
//        fillArray();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void newQuote(View view) {
        Quote actualQuote = getQuote();
        TextView quoteTextView = (TextView) findViewById(R.id.quote);
        TextView authorTextView = (TextView) findViewById(R.id.author);
        TextView videoGameTextView = (TextView) findViewById(R.id.video_game);
        quoteTextView.setText(actualQuote.phrase);
        authorTextView.setText("-- " + actualQuote.author);
        videoGameTextView.setText("\uD83C\uDFAE " + actualQuote.game);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu resource file.
        getMenuInflater().inflate(R.menu.menu, menu);
        // Return true to display menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_share:
                share();
                return true;
            case R.id.menu_item_info:
                infoAboutMe();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void share() {
        Quote actualQuote = listOfQuotes.get(lastRandomNum);
        String shareBody = "\"" + actualQuote.phrase + "\"" +
                "\n-- " + actualQuote.author +
                "\n" + "\uD83C\uDFAE " + actualQuote.game;
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Game Quote");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "share with"));
    }

    private void infoAboutMe() {
        new AlertDialog.Builder(this)
                .setTitle("About Me")
                .setMessage("This application has been developed by Lara Martin.")
                .setIcon(R.drawable.ic_error_black_24dp)
                .show();

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://eu.laramartin.videogamequotes/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://eu.laramartin.videogamequotes/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

