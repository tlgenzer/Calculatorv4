package com.travisgenzer.calculatorv4;

import android.os.Bundle;
import android.view.View;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tipcalculatorv4.R;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private TipCalculator tipCalc;
    public NumberFormat money = NumberFormat.getCurrencyInstance();
    private EditText billEditText;
    private EditText tipEditText;
    private EditText groupEditText;
    private boolean visibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tipCalc = new TipCalculator(0.17f, 100.0f, 1f);
        setContentView(R.layout.activity_main);

        visibility = true;

        billEditText = (EditText)findViewById(R.id.amount_bill);
        tipEditText = (EditText)findViewById(R.id.amount_tip_percent);
        groupEditText = (EditText)findViewById(R.id.amount_group_number);

        TextChangeHandler tch = new TextChangeHandler();
        billEditText.addTextChangedListener(tch);
        tipEditText.addTextChangedListener(tch);
        groupEditText.addTextChangedListener(tch);

        final Button button = findViewById(R.id.hideBtn);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setContentView(R.layout.activity_main);
                System.out.println(visibility);
                if(visibility)
                {
                findViewById(R.id.amount_total).setVisibility(View.GONE);
                    visibility = false;
                }
                else {
                findViewById(R.id.amount_total).setVisibility(View.VISIBLE);
                visibility = true; }
            }
        });
    }

    public void calculate()
    {
        String billString = billEditText.getText().toString();
        String tipString = tipEditText.getText().toString();
        String groupString = groupEditText.getText().toString();

        TextView tipTextView = (TextView)findViewById(R.id.amount_tip);
        TextView totalTextView = (TextView)findViewById(R.id.amount_total);
        TextView groupTextView = (TextView)findViewById(R.id.amount_group_number);


        try {
            // convert billString and tipString to floats
            float billAmount = Float.parseFloat(billString);
            int tipPercent = Integer.parseInt(tipString);
            int groupNumber = Integer.parseInt(groupString);
            // update the Model
            tipCalc.setBill(billAmount);
            tipCalc.setTip(.01f * tipPercent);
            tipCalc.setGroup(groupNumber);
            // ask Model to calculate tip and total amounts
            float tip = tipCalc.tipAmount();
            float total = tipCalc.totalAmount();

            // update the View with formatted tip and total amounts
            tipTextView.setText(money.format(tip));
            totalTextView.setText(money.format(total));
        } catch(NumberFormatException nfe) {
            // pop up an alert view here
        }
    }

    private class TextChangeHandler implements TextWatcher {
        public void afterTextChanged(Editable e) {
            calculate();
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int after) {
        }
    }


}