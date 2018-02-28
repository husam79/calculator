package com.example.android.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String leftOperand = "";
    String rightOperand = "";
    String currentOperation = ""; //1: +, 2: -, 3: *, 4: /
    boolean clearScreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sqClicked(View view) {
    }

    public void acClicked(View view) {
        reset();
    }

    public void cClicked(View view) {
        clearScreen = true;

        if (currentOperation == "") {
            rightOperand = "";
        } else {
            leftOperand = "";
        }

        displayOnScreen("0");
    }

    public void divisionClicked(View view) {
        currentOperation = "/";
        clearScreen = true;
    }

    public void multiplicationClicked(View view) {
        currentOperation = "*";
        clearScreen = true;
    }

    public void minusClicked(View view) {
        currentOperation = "-";
        clearScreen = true;
    }

    public void additionClicked(View view) {
        currentOperation = "+";
        clearScreen = true;
    }

    public void digitClicked(View view) {
        Button button = (Button) view;

        if (clearScreen) {
            displayOnScreen("");
            clearScreen = false;
        }

        if (currentOperation == "") {
            leftOperand = leftOperand + button.getText().toString();
            displayOnScreen(leftOperand);
        } else {
            rightOperand = rightOperand + button.getText().toString();
            displayOnScreen(rightOperand);
        }
    }

    private void displayOnScreen(String str) {
        TextView screen = (TextView) findViewById(R.id.screen_textview);

        screen.setText(str);
    }

    private void reset() {
        clearScreen = false;
        leftOperand = "";
        rightOperand = "";
        currentOperation = "";

        displayOnScreen("0");
    }

    public void equalClicked(View view) {
        double leftOp = Double.parseDouble(leftOperand);
        double rightOp = Double.parseDouble(rightOperand);
        double result = 0;


        if (currentOperation == "+") {
            result = leftOp + rightOp;
        } else if (currentOperation == "-") {
            result = leftOp - rightOp;
        } else if (currentOperation == "*") {
            result = leftOp * rightOp;
        } else if (currentOperation == "/") {
            if (rightOp != 0) {
                result = leftOp / rightOp;
            } else {
                displayOnScreen("ERROR");

                clearScreen = true;
                leftOperand = "";
                rightOperand = "";
                currentOperation = "";
            }
        } else {
            return;
        }

        if (result == Math.floor(result)) {
            displayOnScreen(String.valueOf((int) result));
        } else {
            displayOnScreen(String.valueOf(result));
        }


        leftOperand = String.valueOf(result);
        rightOperand = "";
        currentOperation = "";

        clearScreen = true;
    }
}
