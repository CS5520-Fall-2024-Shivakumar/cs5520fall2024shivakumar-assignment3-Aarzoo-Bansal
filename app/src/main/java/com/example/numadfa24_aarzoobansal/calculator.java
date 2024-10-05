package com.example.numadfa24_aarzoobansal;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;



public class calculator extends AppCompatActivity {

    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            display = findViewById(R.id.input);
            return insets;
        });
    }

    private void updateText(String newStr, View view){
        String oldStr = display.getText().toString();
        if(getString(R.string.cal_display).equals(oldStr)){
            oldStr="";
        }
        String finalMsg="";
        String lastDigit="";

        if(oldStr != ""){
            lastDigit = oldStr.substring(oldStr.length()-1);
        }

        if((lastDigit.equals("+") || (lastDigit.equals("-")) || oldStr.isEmpty() ) && (newStr.equals("+") || newStr.equals("-"))){

            if(oldStr.isEmpty()){
                Snackbar.make(view, "Invalid Format Use. Please select a digit", Snackbar.LENGTH_SHORT).show();
                return;
            }
            Snackbar.make(view, "Operation should be followed by a number.", Snackbar.LENGTH_SHORT).show();
            return;
        }

        finalMsg = oldStr + newStr;
        display.setText(finalMsg);
    }

    public void clickZeroButton(View view){
        updateText("0", view);

    }

    public void clickOneButton(View view){
        updateText("1", view);

    }

    public void clickTwoButton(View view){
        updateText("2", view);

    }

    public void clickThreeButton(View view){
        updateText("3", view);

    }

    public void clickFourButton(View view){
        updateText("4", view);

    }

    public void clickFiveButton(View view){
        updateText("5", view);
    }

    public void clickSixButton(View view){
        updateText("6", view);
    }

    public void clickSevenButton(View view){
        updateText("7", view);
    }

    public void clickEightButton(View view){
        updateText("8", view);
    }

    public void clickNineButton(View view){
        updateText("9", view);
    }

    public void clickAddButton(View view){
        updateText("+", view);
    }

    public void clickSubtractButton(View view){
        updateText("-", view);
    }

    public void clickEqualsButton(View view){

        String exp = display.getText().toString();

        String lastDigit = String.valueOf(exp.charAt(exp.length()-1));

        if(lastDigit.equals("+") || lastDigit.equals("-") || getString(R.string.cal_display).equals(exp)){
            Snackbar.make(view, "Invalid Expression.", Snackbar.LENGTH_SHORT).show();
            return;
        }

        Expression expression = new ExpressionBuilder(exp)
                .build();
        double result = expression.evaluate();
        String[] ans = String.valueOf(result).split("\\.");
        display.setText(ans[0]);

    }

    public void clickCancelButton(View view){
        String displayVal = display.getText().toString();
        if(getString(R.string.cal_display).equals(displayVal)){
            return;
        }
        String newVal = displayVal.substring(0,displayVal.length()-1);
        if(newVal.isEmpty()){
            display.setText(getString(R.string.cal_display));
            return;
        }
        display.setText(newVal);
    }
}