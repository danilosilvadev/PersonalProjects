package personalapps.focus.View;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import personalapps.focus.Model.MyService;
import personalapps.focus.R;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    public int counter = 0;
    public int interval;
    public int limit;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    CountDownTimer count;
    boolean flagTimer = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SoundEffect
        final MediaPlayer tonSound = MediaPlayer.create(this, R.raw.ton);

        //Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.stopwatch);

        //Timer
        final TextView timer = (TextView) findViewById(R.id.timer);
        final Button play = (Button) findViewById(R.id.play);
        final Button pause = (Button) findViewById(R.id.pause);
        final Button restart = (Button) findViewById(R.id.restart);
        restart.setVisibility(View.INVISIBLE);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flagTimer = true;
                pause.setVisibility(View.INVISIBLE);
                play.setVisibility(View.VISIBLE);
                restart.setVisibility(View.INVISIBLE);
                //7200000, 72000
                count = new CountDownTimer(limit, interval) {
                    public void onTick(long millisUntilFinished) {
                        timer.setText(String.valueOf(counter));
                        play.setVisibility(View.INVISIBLE);
                        pause.setVisibility(View.VISIBLE);
                        if (counter == 100) {
                            tonSound.start();
                            cancel();
                            onFinish();
                        } else {
                            counter++;
                        }
                        pause.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                pause.setVisibility(View.INVISIBLE);
                                play.setVisibility(View.VISIBLE);
                                restart.setVisibility(View.INVISIBLE);
                                counter--;
                                cancel();
                            }
                        });
                    }

                    @Override
                    public void onFinish() {
                        timer.setText("100");
                        pause.setVisibility(View.INVISIBLE);
                        play.setVisibility(View.INVISIBLE);
                        restart.setVisibility(View.VISIBLE);
                        restart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                counter = 0;
                                timer.setText(String.valueOf(counter));
                                restart.setVisibility(View.INVISIBLE);
                                pause.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                }.start();
            }

        });
        //Spinner
        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                switch (position) {
                    case 0:
                        counter = 0;
                        interval = 100;
                        timer.setText("0");
                        play.setVisibility(View.INVISIBLE);
                        pause.setVisibility(View.INVISIBLE);
                        restart.setVisibility(View.INVISIBLE);
                        break;

                    case 1:
                        counter = 0;
                        interval = 72000;
                        limit = 7200300;
                        timer.setText("0");
                        play.setVisibility(View.VISIBLE);
                        pause.setVisibility(View.INVISIBLE);
                        restart.setVisibility(View.INVISIBLE);
                        if (flagTimer) {
                            count.cancel();
                        }
                        break;

                    case 2:
                        counter = 0;
                        interval = 140000;
                        limit = 14000300;
                        timer.setText("0");
                        play.setVisibility(View.VISIBLE);
                        pause.setVisibility(View.INVISIBLE);
                        restart.setVisibility(View.INVISIBLE);
                        count.cancel();
                        if (flagTimer) {
                            count.cancel();
                        }
                        break;

                    case 3:
                        counter = 0;
                        interval = 216000;
                        limit = 21600300;
                        timer.setText("0");
                        play.setVisibility(View.VISIBLE);
                        pause.setVisibility(View.INVISIBLE);
                        restart.setVisibility(View.INVISIBLE);
                        count.cancel();
                        if (flagTimer) {
                            count.cancel();
                        }
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void startService(View view) {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    public void stopService(View view) {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

}
