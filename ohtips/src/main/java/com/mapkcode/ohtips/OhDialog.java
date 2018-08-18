package com.mapkcode.ohtips;

import android.content.Context;
import android.view.View;

public class OhDialog extends BaseTip {

    public OhDialog(Context ctx){
        super(ctx, BaseTip.THEME_DIALOG);
    }

    /**
     * Show custom info dialog
     * without the checkbox for don't show again
     *
     */
    public void show(){

        this.showDialogNow(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });
    }

}
