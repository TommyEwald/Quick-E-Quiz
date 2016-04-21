package de.hwr_berlin.quick_e_quiz;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import de.hwr_berlin.quick_e_quiz.network.Loader;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by EwaldT on 30.03.2016.
 */
public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public QuestionActivity c;
    public Dialog d;
    public Button yes;
    public TextView tv_time;
    public TextView tv_fault;
    public TextView tv_score;
    public CustomDialogClass(QuestionActivity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_highscore_dialog);
        setCanceledOnTouchOutside(false);
        yes = (Button) findViewById(R.id.btnOk);
        yes.setOnClickListener(this);
        tv_time = (TextView) this.findViewById(R.id.time);
        tv_fault = (TextView) this.findViewById(R.id.fault);
        tv_score = (TextView) this.findViewById(R.id.score);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                // Send score to server
                String name = ((EditText) findViewById(R.id.username)).getText().toString();
                final int score = c.getScore();
                Loader.insertScore(name, score).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Intent highscoreIntent = new Intent(c, HighscoreActivity.class);
                        c.startActivity(highscoreIntent);
                        c.finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(c, "Highscore konnte nicht an den Server gesendet werden.", Toast.LENGTH_SHORT).show();
                        c.finish();
                    }
                });
                break;
            default:
                break;
        }
        dismiss();
    }
    @Override
    public void onBackPressed()
    {
        c.finish();
    }
}
