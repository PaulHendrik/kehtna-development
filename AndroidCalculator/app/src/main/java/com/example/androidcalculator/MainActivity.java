package com.example.androidcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button nine;
    private Button eight;
    private Button seven;
    private Button add;
    private TextView expression;
    private TextView result;
    private Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expression = findViewById(R.id.expression);
        result = findViewById(R.id.result);
        clear = findViewById(R.id.clear);
        add = findViewById(R.id.add);

        //buttonTest = findViewById(R.id.button_test);
        //buttonTest.setOnClickListener(Toast.makeText(getApplicationContext(), "Button pressed", Toast.LENGTH_LONG).show());
    }

    public void onCalculatorButton(View view) {
        Button button = (Button) view;
        String text = button.getText().toString();
        //Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        expression.append(text);

        Button seven = findViewById(R.id.seven);

        if (add.isPressed()) {
            System.out.println(5+6-1*2);
        }

        if (clear.isPressed()) {
            clearAll();
        }
        //seven.setOnClickListener(addText("7", true));


    }

    public void addText(String text, Boolean canClear) {
        if (canClear == true) {
            result.setText("");
            expression.append(text);
        } else {
            expression.append(result.getText());
            expression.append(text);
            result.setText("");
        }
    }

    public void clearAll() {
        expression.setText("");
        result.setText("");
    }

    public void goToSecondActivity(View view) {
        Intent intent = new Intent(this, SecondaryActivity.class);
        intent.putExtra("Result", result.toString());
        startActivity(intent);
    }
}
