package com.example.sam.blutoothsocketreceiver;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;

public class Super_Scouting extends ActionBarActivity {
    TextView teamNumber1;
    TextView teamNumber2;
    TextView teamNumber3;
    String matchNumber;
    String teamNumberOne;
    String teamNumberTwo;
    String teamNumberThree;
    String defensesOnField;
    String alliance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.super_scouting);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Intent next = getIntent();
        defensesOnField = next.getExtras().getString("defensesChecked");
        matchNumber = next.getExtras().getString("matchNumber");
        teamNumberOne = next.getExtras().getString("teamNumberOne");
        teamNumberTwo = next.getExtras().getString("teamNumberTwo");
        teamNumberThree = next.getExtras().getString("teamNumberThree");
        alliance = next.getExtras().getString("alliance");

        teamNumber1 = (TextView) findViewById(R.id.team1);
        teamNumber2 = (TextView) findViewById(R.id.team2);
        teamNumber3 = (TextView) findViewById(R.id.team3);

        teamNumber1.setText(teamNumberOne);
        teamNumber1.setTextColor(Color.BLUE);
        teamNumber2.setText(teamNumberTwo);
        teamNumber2.setTextColor(Color.BLUE);
        teamNumber3.setText(teamNumberThree);
        teamNumber3.setTextColor(Color.BLUE);


        ArrayList<String> data = new ArrayList<>(Arrays.asList("Speed", "Torque", "Defense", "Evasion", "Ball Control", "Cross Eff."));
        RelativeLayout teamOneRelativeLayout = (RelativeLayout) findViewById(R.id.scoutTeam1);
        RelativeLayout teamTwoRelativeLayout = (RelativeLayout) findViewById(R.id.scoutTeam2);
        RelativeLayout teamThreeRelativeLayout = (RelativeLayout) findViewById(R.id.scoutTeam3);

        View anchor = teamNumber1;
        View anchor2 = teamNumber2;
        View anchor3 = teamNumber3;

        for (String title : data) {
            RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            View counter = createCounter(title);
            counter.setId(1 + data.indexOf(title));
            param.addRule(RelativeLayout.BELOW, anchor.getId());
            param.addRule(RelativeLayout.CENTER_HORIZONTAL);
            counter.setLayoutParams(param);
            teamOneRelativeLayout.addView(counter);
            anchor = counter;
        }
        for (String title : data) {
            RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            View counter = createCounter(title);
            counter.setId(2 + data.indexOf(title));
            param.addRule(RelativeLayout.BELOW, anchor2.getId());
            param.addRule(RelativeLayout.CENTER_HORIZONTAL);
            counter.setLayoutParams(param);
            teamTwoRelativeLayout.addView(counter);
            anchor2 = counter;
        }
        for (String title : data) {
            RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            View counter = createCounter(title);
            counter.setId(2 + data.indexOf(title));
            param.addRule(RelativeLayout.BELOW, anchor3.getId());
            param.addRule(RelativeLayout.CENTER_HORIZONTAL);
            counter.setLayoutParams(param);
            teamThreeRelativeLayout.addView(counter);
            anchor3 = counter;
        }
    }


    private View createCounter(String title) {

        LayoutInflater inflater = getLayoutInflater();
        View counter = inflater.inflate(R.layout.counter, null);
        TextView dataNameTextView = (TextView)counter.findViewById(R.id.dataName);
        dataNameTextView.setText(title);
        final TextView incrementor = (TextView) counter.findViewById(R.id.scoreCounter);
        Button plusButton = (Button)counter.findViewById(R.id.plusButton);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = Integer.parseInt(incrementor.getText().toString());
                current++;
                if(current > 3){
                    incrementor.setText(Integer.toString(3));
                }else {
                    incrementor.setText(Integer.toString(current));
                }
            }
        });
        Button minusButton = (Button)counter.findViewById(R.id.minusButton);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = Integer.parseInt(incrementor.getText().toString());
                current--;
                if (current < 0) {
                    incrementor.setText(Integer.toString(0));
                }else{
                    incrementor.setText(Integer.toString(current));
                }

            }
        });
        return counter;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.submit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.submit) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}


