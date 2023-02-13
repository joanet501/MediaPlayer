package com.example.mediaplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.Random;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button playButton, stopButton, nextButton, prevButton, repeatButton, randomButton;
    private MediaPlayer mediaPlayer;
    private ArrayList<Integer> songList;
    private int currentSongIndex = 0;
    private boolean isRepeating = false;
    private boolean isRandom = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        stopButton = findViewById(R.id.stopButton);
        nextButton = findViewById(R.id.nextButton);
        prevButton = findViewById(R.id.prevButton);
        repeatButton = findViewById(R.id.repeatButton);
        randomButton = findViewById(R.id.randomButton);

        // Initialize the song list
        songList = new ArrayList<>();
        songList.add(R.raw.song1);
        songList.add(R.raw.song2);
        songList.add(R.raw.song3);
        songList.add(R.raw.song4);
        songList.add(R.raw.song5);
        songList.add(R.raw.song6);
        songList.add(R.raw.song7);
        songList.add(R.raw.song8);
        songList.add(R.raw.song9);
        songList.add(R.raw.song10);

        // Set the play button to start playing the first song
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSong();
            }
        });

        // Set the stop button to pause the current song
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null) {
                    mediaPlayer.pause();
                }
            }
        });

        // Set the next button to play the next song
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextSong();
            }
        });

        // Set the previous button to play the previous song
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prevSong();
            }
        });

        // Set the repeat button to toggle repeating the current song
        repeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRepeating = !isRepeating;
            }
        });

        // Set the random button to toggle playing random songs
        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRandom = !isRandom;
            }
        });

        // Set up the MediaPlayer to play the songs
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (isRepeating) {
                    playSong();
                } else if (isRandom) {
                    Random random = new Random();
                    currentSongIndex = random.nextInt(songList.size());
                    playSong();
                } else {
                    nextSong();
                }
            }
        });
    }

    // Play the current song
    private void playSong() {
        mediaPlayer.reset();
        int songId = songList.get(currentSongIndex);
        mediaPlayer = MediaPlayer.create(this, songId);
        mediaPlayer.start();
    }

    // Play the next song
    private void nextSong() {
        currentSongIndex = (currentSongIndex + 1) % songList.size();
        playSong();
    }

    // Play the previous song
    private void prevSong() {
        currentSongIndex = (currentSongIndex - 1 + songList.size()) % songList.size();
        playSong();
    }
}
