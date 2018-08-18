package com.mapkcode.ohtips;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.mapkcode.ohtips.utils.TheShared;


public abstract class BaseTip {

    protected static final String THEME_TIPS = "tips";
    protected static final String THEME_DIALOG = "dialog";
    protected static final String THEME_STARS = "stars";

    private static final String OHTIPS_SHARED = "ohtips_shared";
    private static final String OHTIPS_SHARED_STATE = "ohtips_shared_state";
    private static final String OHTIPS_SHARED_COUNT = "ohtips_shared_count";
    private static final String OHTIPS_SHARED_TODAY = "ohtips_shared_today";

    private static final String OHTIPS_STARS_OK = "ohtips_stars_ok";
    private static final String OHTIPS_STARS_NO = "ohtips_stars_no";
    private static final String OHTIPS_STARS_LATER = "ohtips_stars_later";


    private Context ctx;
    private TheShared shared;
    //private Activity activity;

    protected Dialog dialog;
    private TextView title, content;
    private Button btn_close;
    private ImageView icon;
    protected CheckBox checkBox;


    public BaseTip(Context context, String theme){
        ctx = context;
        //activity = (Activity) ctx;

        dialog = new Dialog(ctx);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);

        shared = new TheShared(ctx, OHTIPS_SHARED);

        initTheme(theme); // OhTips.THEME_TIPS
    }

    private void initDialogUI(){
        dialog.setContentView(R.layout.dialog_info);
        initUI();
    }

    private void initTipsUI(){
        dialog.setContentView(R.layout.dialog_tips);
        initUI();
        checkBox    = dialog.findViewById(R.id.chkDontShow);
    }

    private void initStarsUI(){
        dialog.setContentView(R.layout.dialog_stars);
        initUI();
        checkBox    = dialog.findViewById(R.id.chkDontShow);
    }

    private void initUI(){
        title       = dialog.findViewById(R.id.title);
        content     = dialog.findViewById(R.id.content);
        btn_close   = dialog.findViewById(R.id.btn_close);
        icon        = dialog.findViewById(R.id.icon);
    }



    protected void initTheme(String theme){

        switch (theme){
            case THEME_TIPS:
                initTipsUI();
                break;
            case THEME_DIALOG:
                initDialogUI();
                break;
            case THEME_STARS:
                initStarsUI();
                break;
            default:
                initTipsUI(); // basic
        }

    }

    /**
     * Setters
     *
     */

    public void setTitle(String s){
        title.setText(s);
    }

    public void setTitle(int id){
        title.setText( ctx.getString(id) );
    }

    public void setMessage(String s){
        content.setText(s);
    }

    public void setMessage(int id){
        content.setText( ctx.getString(id) );
    }

    public void setButtonText(String s){
        btn_close.setText(s);
    }

    public void setButtonText(int id){
        btn_close.setText( ctx.getString(id) );
    }

    public void setIcon(int r){
        icon.setImageResource(r);
    }

    public void setThemeColor(int c){
        int color = ctx.getResources().getColor(c);
        dialog.findViewById(R.id.header).setBackgroundColor(color);
        btn_close.setBackgroundTintList(ColorStateList.valueOf( color ));
        if(checkBox!=null)
            checkBox.setButtonTintList(ColorStateList.valueOf( color ));
    }


    /**
     * Show The Window Dialog
     *
     */
    protected void showDialogNow(View.OnClickListener action) {

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();

        if(dialog.getWindow() != null) {
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


            btn_close.setOnClickListener(action);

            dialog.show();
            dialog.getWindow().setAttributes(lp);
        }

    }







//END
}
