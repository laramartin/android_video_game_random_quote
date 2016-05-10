package eu.laramartin.videogamequotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}