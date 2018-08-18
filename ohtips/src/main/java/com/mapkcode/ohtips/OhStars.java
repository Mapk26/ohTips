package com.mapkcode.ohtips;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

import com.mapkcode.ohtips.utils.TheShared;

import java.util.Calendar;
import java.util.Date;

public class OhStars extends BaseTip {

    private static final String OHTIPS_SHARED = "ohtips_shared";
    private static final String OHTIPS_STAR_DONE = "ohtips_star_done";
    private static final String OHTIPS_STAR_LAUNCHES = "ohtips_star_launches";
    private static final String OHTIPS_STAR_LAUNCHES_GONE = "ohtips_star_launches_gone";
    private static final String OHTIPS_STAR_TODAY = "ohtips_star_today";
    private static final String OHTIPS_STAR_DAYS  = "ohtips_star_days";


    private Context context;
    private TheShared shared;

    public OhStars(Context ctx){

        super(ctx, BaseTip.THEME_STARS);
        context = ctx;
        shared = new TheShared(ctx, OHTIPS_SHARED);

        initExtraUI();
        initDefaults();
        setFirstDay();


    }

    private void initExtraUI(){
        setTitle(R.string.ohtips_tit_star);
        setMessage(R.string.ohtips_txt_star);
        setIcon(R.drawable.ic_star);
        Button btn_not_now = dialog.findViewById(R.id.btn_not_now);

        btn_not_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDelay();
                dialog.dismiss();
            }
        });
    }

    public void show(){

        if( !isDone() && areLaunchesGone() || !isDone() && checkToday() ) {

            showNow();

        }
    }

    public void showNow(){
        showDialogNow(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    context.startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())
                    ));
                }

                setDone();
                dialog.dismiss();
            }
        });
    }


    /**
     * OhStars configuration
     * Launches : 10
     * Days after first launch : 7
     */

    private void initDefaults(){
        int launches = shared.readShared(OHTIPS_STAR_LAUNCHES);
        int days     = shared.readShared(OHTIPS_STAR_DAYS);

        if(launches == 0) {
            setLaunches(10);
        }

        if(days == 0){
            setDays(7);
        }
    }

    private void setDelay(){
        // reset launches
        shared.writeShared(OHTIPS_STAR_LAUNCHES, 0);
        shared.writeShared(OHTIPS_STAR_LAUNCHES_GONE, 0);
        // reset days
        shared.writeShared(OHTIPS_STAR_DAYS, 0);
        shared.writeSharedLong(OHTIPS_STAR_TODAY, 0);
    }

    public void setLaunches(int launches){

        shared.writeShared(OHTIPS_STAR_LAUNCHES, launches);
    }

    private boolean areLaunchesGone(){

        int launchesGone = shared.readShared(OHTIPS_STAR_LAUNCHES_GONE);
        int launches     = shared.readShared(OHTIPS_STAR_LAUNCHES);

        if(launchesGone < launches){
            launchesGone++;
            shared.writeShared(OHTIPS_STAR_LAUNCHES_GONE, launchesGone);
            return false;
        }else
            return true;
    }

    private void setFirstDay(){
        if(shared.readSharedLong(OHTIPS_STAR_TODAY)==0)
            saveToday();
    }

    private boolean isDone(){

        return (shared.readShared(OHTIPS_STAR_DONE) == 1);
    }

    private void setDone(){

        shared.writeShared(OHTIPS_STAR_DONE, 1);
    }


    /**
     * Check N days after first launch (installation)
     */

    private long getToday(){
        Date now = Calendar.getInstance().getTime();
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return now.getTime();
    }

    private void saveToday(){
        shared.writeSharedLong(OHTIPS_STAR_TODAY, getToday());
    }

    public void setDays(int days){
        shared.writeShared(OHTIPS_STAR_DAYS, days);
    }

    private int readDays(){
        return shared.readShared(OHTIPS_STAR_DAYS);
    }

    private boolean checkToday(){

        long daysAgo = shared.readSharedLong(OHTIPS_STAR_TODAY);
        long today = Calendar.getInstance().getTime().getTime();

        long diff = today - daysAgo;

        long daysGone = diff / (24 * 60 * 60 * 1000); // 1 day = 86400000 ms

        //Log.d("_debug", "saved day: " + daysAgo + " diff: " +diff+ " days gone: " + daysGone);

        return (daysGone >= readDays());
    }



//END
}
