package com.mapkcode.ohtipsexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mapkcode.ohtips.OhDialog;
import com.mapkcode.ohtips.OhStars;
import com.mapkcode.ohtips.OhTips;

public class MainActivity extends AppCompatActivity {

    private OhTips ohTips;
    private OhDialog ohDialog;
    private OhStars ohStars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ohTips = new OhTips(this);
        ohTips.setButtonText("Nice!");


        ohDialog = new OhDialog(this);
        ohStars = new OhStars(this);


        //showNow(findViewById(R.id.button));

        ohStars.setLaunches(10);
        ohStars.setDays(1);
        ohStars.show();

    }

    public void showNow(View view) {
        ohTips.setThemeColor(R.color.purple_200);
        ohTips.show();
    }

    public void restartTips(View view){
        ohTips.setThemeColor(R.color.red_600);
        ohTips.restart();
    }

    public void simpleDialog(View view) {
        ohDialog.setIcon(R.drawable.ic_speaker_notes);
        ohDialog.setTitle("Simple Dialog");
        ohDialog.setMessage("This is a simple dialog created with ohDialog class!");
        ohDialog.setThemeColor(R.color.green_400);
        ohDialog.show();

    }

    public void showRate(View view) {
        ohStars.showNow();
    }
}
