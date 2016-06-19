package eu.laramartin.videogamequotes;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;

import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    int lastRandomNum = 0;

    Quote katamari = new Quote(
            "We are moved to tears by the size of this thing.",
            "King of All Cosmos",
            "Katamari Damacy");

    Quote lastOfUs = new Quote(
            "Endure and survive.",
            "Ellie",
            "The Last of Us");

    Quote borderlands2 = new Quote(
            "Nothing is more badass than treating a woman with respect.",
            "Mr. Torgue",
            "Borderlands 2"
            );

    Quote mgs = new Quote(
            "It's easy to forget what a sin is in the middle of a battlefield.",
            "Solid Snake",
            "Metal Gear Solid"
            );

    Quote marioBross = new Quote(
            "Thank you Mario! But our Princess is in another castle!",
            "Toad",
            "Super Mario Bros."
            );

    Quote guildWars2 = new Quote(
            "Knowledge is useless if it is not used.",
            "Gixx",
            "Guild Wars 2"
            );

    Quote persona4 = new Quote(
            "Working for a better tomorrow isn't just one-time thing. The tomorrows keep coming.",
            "Yu Narukami",
            "Persona 4"
            );

    Quote danganronpa2 = new Quote(
            "Belive in yourself... If you don't have that... it doesn't matter how many talents you have, you still won't be able to hold your head up high...",
            "Chiaki",
            "Danganronpa 2"
            );

    Quote zeroWing = new Quote(
            "All your base are belong to us",
            "Cats",
            "Zero Wing"
            );

    List<Quote> listOfQuotes = new ArrayList<>();

    public void fillArray(){
        listOfQuotes.add(katamari);
        listOfQuotes.add(lastOfUs);
        listOfQuotes.add(borderlands2);
        listOfQuotes.add(mgs);
        listOfQuotes.add(marioBross);
        listOfQuotes.add(guildWars2);
        listOfQuotes.add(persona4);
        listOfQuotes.add(danganronpa2);
        listOfQuotes.add(zeroWing);
    }

    private int randomNumber(){
        Random r = new Random();
        int length = listOfQuotes.size();
        int num = r.nextInt(length - 1) + 1;
        if (num != lastRandomNum){
            lastRandomNum = num;
            return num;
        } else {
            return randomNumber();
        }
    }

    private Quote getQuote(){
        Quote actualQuote = listOfQuotes.get(randomNumber());
        return actualQuote;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton sharingButton = new ImageButton(this);
        sharingButton.setLayoutParams(new ViewGroup.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
        sharingButton.setImageDrawable(getDrawable(R.drawable.ic_share_black_24dp));
        sharingButton.setImageDrawable(getDrawable(R.drawable.ic_info_white_24dp));
        fillArray();
    }

//    this method is called when "new Quote" button is clicked

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
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Game Quote");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "share with"));
    }

    private void infoAboutMe(){
        new AlertDialog.Builder(this)
                .setTitle("About Me")
                .setMessage("This application has been developed by Lara Martin.")
                .setIcon(R.drawable.ic_error_black_24dp)
                .show();

    }
}

