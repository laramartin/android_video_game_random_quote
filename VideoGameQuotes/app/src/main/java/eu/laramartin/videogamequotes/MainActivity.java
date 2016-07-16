package eu.laramartin.videogamequotes;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    List<Quote> listOfQuotes = new ArrayList<>();

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton sharingButton = new ImageButton(this);
        sharingButton.setLayoutParams(new ViewGroup.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
        sharingButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_share_black_24dp));
        sharingButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_info_white_24dp));

        GameQuotes.initQuotes(listOfQuotes);
        Log.v("mainactivity", "quotes list: " + listOfQuotes.size());
        getNewQuote();
    }

    public void newQuote(View view) {
//        Quote actualQuote = getQuote();
//        TextView quoteTextView = (TextView) findViewById(R.id.quote);
//        TextView authorTextView = (TextView) findViewById(R.id.author);
//        TextView videoGameTextView = (TextView) findViewById(R.id.video_game);
//        quoteTextView.setText(actualQuote.phrase);
//        authorTextView.setText("-- " + actualQuote.author);
//        videoGameTextView.setText("\uD83C\uDFAE " + actualQuote.game);
        getNewQuote();
    }

    private void getNewQuote(){
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

