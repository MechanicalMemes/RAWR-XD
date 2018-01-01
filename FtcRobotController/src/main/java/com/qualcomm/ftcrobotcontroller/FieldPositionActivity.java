package com.qualcomm.ftcrobotcontroller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class FieldPositionActivity extends Activity {
    public enum FieldPostion {
        BLUE_TOP,
        BLUE_BOTTOM,
        RED_TOP,
        RED_BOTTOM
    }

    private FieldPostion chosenPositon = FieldPostion.BLUE_TOP;
    private TextView chosenText;
    private TextView qualityText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_position);
        Button buttonRedBottom = (Button) findViewById(R.id.button_red_bottom);
        Button buttonRedTop = (Button) findViewById(R.id.button_red_top);
        Button buttonBlueTop = (Button) findViewById(R.id.button_blue_top);
        Button buttonBlueBottom = (Button) findViewById(R.id.button_blue_bottom);

        Button buttonSet = (Button)findViewById(R.id.button_set);

        buttonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("POSITION", chosenPositon);
                setResult(Activity.RESULT_OK,resultIntent);
                finish();

            }
        });

        chosenText = (TextView) findViewById(R.id.textView_selected);
        qualityText = (TextView)findViewById(R.id.textView_quality);
        buttonRedBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosenPositon = FieldPostion.RED_BOTTOM;
                updateChosenText();
            }
        });
        buttonRedTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosenPositon = FieldPostion.RED_TOP;
                updateChosenText();
            }
        });
        buttonBlueTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosenPositon = FieldPostion.BLUE_TOP;
                updateChosenText();
            }
        });
        buttonBlueBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosenPositon = FieldPostion.BLUE_BOTTOM;
                updateChosenText();
            }
        });

        SeekBar qualitySeek = (SeekBar)findViewById(R.id.seekBar_quality);
        qualitySeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                switch (i){
                    case 0:
                        qualityText.setText("VERY FAST");
                        qualityText.setTextColor(Color.RED);
                        break;
                    case 1:
                        qualityText.setText("FAST");
                        qualityText.setTextColor(Color.YELLOW);
                        break;
                    case 2:
                        qualityText.setText("BALANCED :)");
                        qualityText.setTextColor(Color.GREEN);
                        break;
                    case 3:
                        qualityText.setText("SLOW");
                        qualityText.setTextColor(Color.YELLOW);
                        break;
                    case 4:
                        qualityText.setText("VERY SLOW");
                        qualityText.setTextColor(Color.RED);
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    @SuppressLint("ResourceAsColor")
    private void updateChosenText(){

        FieldPositonData.fieldPostion = chosenPositon;
        switch(chosenPositon){
            case RED_BOTTOM:
                chosenText.setText("Red Bottom");
                chosenText.setTextColor(Color.RED);
                break;
            case RED_TOP:
                chosenText.setText("Red Top");
                chosenText.setTextColor(Color.RED);
                break;
            case BLUE_BOTTOM:
                chosenText.setText("Blue Bottom");
                chosenText.setTextColor(Color.BLUE);
                break;
            case BLUE_TOP:
                chosenText.setText("Blue Top");
                chosenText.setTextColor(Color.BLUE);
                break;

        }

    }

    public FieldPostion getChosenPositon() {
        return chosenPositon;
    }
}
