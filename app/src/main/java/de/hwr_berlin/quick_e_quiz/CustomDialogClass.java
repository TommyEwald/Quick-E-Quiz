package de.hwr_berlin.quick_e_quiz;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by EwaldT on 30.03.2016.
 */
public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes;

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_highscore_dialog);
        yes = (Button) findViewById(R.id.btnOk);
        yes.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                Intent highscoreIntent = new Intent(c, HighscoreActivity.class);
                c.startActivity(highscoreIntent);
                break;
            default:
                break;
        }
        dismiss();
    }
}
