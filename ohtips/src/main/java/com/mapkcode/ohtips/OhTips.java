package com.mapkcode.ohtips;

import android.content.Context;
import android.view.View;

import com.mapkcode.ohtips.utils.TheShared;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OhTips extends BaseTip {

    private static final String OHTIPS_SHARED = "ohtips_shared";
    private static final String OHTIPS_SHARED_STATE = "ohtips_shared_state";
    private static final String OHTIPS_SHARED_COUNT = "ohtips_shared_count";
    private static final String OHTIPS_SHARED_TODAY = "ohtips_shared_today";

    private TheShared shared;
    private Context context;
    private int tipPos;
    private String[] tipsTitle, tipsMessage, tipsIcon;

    public OhTips(Context ctx){
        super(ctx, BaseTip.THEME_TIPS);
        context = ctx;

        shared = new TheShared(ctx, OHTIPS_SHARED);
        tipPos = shared.readShared(OHTIPS_SHARED_COUNT);

        initBase();
    }

    private void initBase(){

        tipsTitle   = context.getResources().getStringArray(R.array.ohtips_title);
        tipsMessage = context.getResources().getStringArray(R.array.ohtips_message);
        tipsIcon    = context.getResources().getStringArray(R.array.ohtips_icon);

    }

    /**
     * OHTIPS_SHARED_STATE :
     * 0 - continue to show
     * 1 - stop showing tips
     *
     */
    private void stopTips(){
        shared.writeShared(OHTIPS_SHARED_STATE, 1);
    }


    /**
     * Start showing tips and reset the "Do not show"
     *
     */
    public void restart(){
        shared.writeShared(OHTIPS_SHARED_STATE, 0);
        shared.writeShared(OHTIPS_SHARED_TODAY, "");
        show();
    }

    public void show(){

        if(checkToday())
            return;

        if(!canIShowTips())
            return;

        // If all the tips have been shown, then restart from 0
        if( tipsTitle.length <= tipPos )
            tipPos = 0;

        // Icons can be empty, in case we use default icon
        if( tipsIcon.length >0 ){
            int id = context.getResources().getIdentifier(
                    tipsIcon[tipPos],
                    "drawable",
                    context.getPackageName() );
            setIcon(id);
        }

        // Check if the string-array has items
        if( tipsTitle.length >0 ){

            setTitle(tipsTitle[tipPos]);
            setMessage(tipsMessage[tipPos]);

            tipPos += 1;
            shared.writeShared(OHTIPS_SHARED_COUNT, tipPos );

            saveToday();
            showDialogNow(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.dismiss();

                    if(checkboxIsSelected()){
                        stopTips();
                    }
                }
            });

        }

    }

    /**
     * saveToday will save the current date
     * into the shared_preferences
     *
     * checkToday will return true
     * if a tip has been already shown today.
     * Then do not show again till tomorrow.
     *
     */

    private void saveToday(){

        shared.writeShared(OHTIPS_SHARED_TODAY, todayIs());

    }

    private boolean checkToday(){
        return todayIs().equals(shared.readSharedString(OHTIPS_SHARED_TODAY));
    }

    private String todayIs(){
        Date now = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return dateFormat.format(now);
    }


    /**
     * Internal controls
     *
     */

    private boolean checkboxIsSelected(){
        if(checkBox!=null)
            return checkBox.isChecked() ? true : false;
        else
            return false;
    }

    private boolean canIShowTips(){

        if( shared.readShared(OHTIPS_SHARED_STATE) == 0 )
            return true;
        else
            return false;
    }
}
