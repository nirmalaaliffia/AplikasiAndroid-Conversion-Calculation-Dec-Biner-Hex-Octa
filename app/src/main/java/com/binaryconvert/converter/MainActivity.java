package com.binaryconvert.converter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.text.Editable;
import android.text.TextWatcher;

import static android.text.InputType.TYPE_CLASS_NUMBER;
import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS;

public class MainActivity extends AppCompatActivity {
    private TextView  decimal, binary, hexa, octal;
    private EditText input;
    private Button convert, btncalcu, btnconvert;

    private Spinner spinConversionSelection;
    public String[] spinConversionItem = new String[]{"Decimal", "Binary", "Octal", "Hexadecimal"};
    private ArrayAdapter<String> spinAdapter;
    private int spinPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinPosition = 0;
        input = (EditText) findViewById(R.id.input);
        input.addTextChangedListener(new MyTextWatcher(input));
        decimal = (TextView) findViewById(R.id.decText);
        binary = (TextView) findViewById(R.id.binText);
        hexa = (TextView) findViewById(R.id.hexText);
        octal = (TextView) findViewById(R.id.oktalText);
        convert = (Button) findViewById(R.id.Convert);
        btnconvert = (Button) findViewById(R.id.btnconvert);
        btnconvert.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openconvert();
            }
        });

        btncalcu = (Button) findViewById(R.id.btncalcu);
        btncalcu.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                opencalcu();
            }
        });
        

        spinConversionSelection = (Spinner) findViewById(R.id.spinConversionSelection);
        spinAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,spinConversionItem);
        spinConversionSelection.setAdapter(spinAdapter);
        spinConversionSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
               spinPosition = i;
               input.setText("");
               decimal.setText("");
               binary.setText("");
               octal.setText("");
               hexa.setText("");
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {
             }
        });

        convert.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                conversion();
            }
        });
    }

    //BUTTON OPEN CAONVERT
    private void openconvert() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //BUTTON OPEN CALCU
    private void opencalcu() {
        Intent intent = new Intent(this,Main2ActivityCalculation.class);
        startActivity(intent);
    }


    private void conversion() {
        convertDecimal();
        convertBinary();
        convertHexa();
        convertOctal();
    }

    //DECIMAL
    private void convertDecimal() {
        String value = input.getText().toString();
        if (!checkingInputValidation()) {
            switch (spinPosition) {
                case 0:
                    try {
                        decimal.setText(value);
                        decimal.setTextSize(20);
                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }
                    break;
                case 1:
                    try {
                        decimal.setText("" + Long.parseLong(value, 2));
                        decimal.setTextSize(20);
                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }
                    break;
                case 2:
                    try {
                        decimal.setText("" + Long.parseLong(value, 8));
                        decimal.setTextSize(20);
                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }
                    break;
                case 3:
                    try {
                        int decnum;
                        decnum = convdec(value);
                        decimal.setText("" + decnum);
                        decimal.setTextSize(20);
                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }

                    break;
            }
        }
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    //cek input
    private boolean checkingInputValidation() {
        String gettingInput = input.getText().toString();
        if (input.getText().toString().trim().isEmpty()) {
            input.setError("Silahkan inputkan nilai yang akan dikonversikan");
            requestFocus(input);
            return true;
        } else if (gettingInput.matches(".*[G-Z].*") || gettingInput.matches(".*[g-z].*")) {
            input.setError("Masukkan huruf kapital antara A Sampai F");
            requestFocus(input);
            return true;
        }else if (spinPosition == 2 && gettingInput.matches(".*[8-9].*")) {
            input.setError("Masukkan nilai input hanya interval dari 0 sampai 7");
            requestFocus(input);
            return true;
        }else if (spinPosition == 1 && gettingInput.matches(".*[2-9].*")) {
            input.setError("Masukkan nilai input 0 atau 1");
            requestFocus(input);
            return true;
        }else if (gettingInput.length() > 15) {
            input.setError("Masukkan nilai input maksimal 6 digit");
            requestFocus(input);
            return true;
        }
        return false;
    }

    //BINER
    private void convertBinary() {
        String value = input.getText().toString();

        if (!checkingInputValidation()) {
            switch (spinPosition) {
                case 0:
                    try {
                        binary.setText("" + Long.toBinaryString(Long.valueOf(value)));
                        binary.setTextSize(20);
                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }
                    break;
                case 1:
                    try {
                        binary.setText(value);
                        binary.setTextSize(20);
                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }
                    break;
                case 2:
                    try {
                        binary.setText("" + Long.toBinaryString(Long.parseLong(value, 8)));
                        binary.setTextSize(20);
                    } catch (Exception e) {
                        input.setError("Something Wrong 1234");
                        requestFocus(input);
                    }
                    break;
                case 3:
                    try {
                        binary.setText("" + Long.toBinaryString(Long.parseLong(value, 16)));
                        binary.setTextSize(20);
                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }
                    break;
            }
        }
    }

    //HEXA
    private void convertHexa() {
        String value = input.getText().toString();

        if (!checkingInputValidation()) {
            switch (spinPosition) {
                case 0:
                    try {
                        hexa.setText("" + Long.toHexString(Long.valueOf(value)));
                        hexa.setTextSize(20);
                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }

                    break;
                case 1:
                    try {
                        hexa.setText("" + Long.toHexString(Long.parseLong(value, 2)));
                        hexa.setTextSize(20);
                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }

                    break;
                case 2:
                    try {
                        hexa.setText("" + Long.toHexString(Long.parseLong(value, 8)));
                        hexa.setTextSize(20);
                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }
                    break;

                case 3:
                    try {
                        hexa.setText(value);
                        hexa.setTextSize(20);
                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }
                    break;
            }
        }
    }

    private int convdec(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return val;
    }

        //OCTAL
    private void convertOctal() {
        String value = input.getText().toString();

        if (!checkingInputValidation()) {
            switch (spinPosition) {
                case 0:
                    try {
                        octal.setText("" + Long.toOctalString(Long.valueOf(value)));
                        octal.setTextSize(20);
                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }

                    break;
                case 1:
                    try {
                        long l = Long.parseLong(value, 2);
                        octal.setText("" + Long.toOctalString(l));
                        octal.setTextSize(20);

                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }
                    break;
                case 2:
                    try {
                        octal.setText(value);
                        octal.setTextSize(20);

                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }
                    break;

                case 3:
                    try {
                        octal.setText("" + Long.toOctalString(Long.parseLong(value, 16)));
                        octal.setTextSize(20);

                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }
                    break;

            }
        }
    }


    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }


        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input:
                    validateInput();
                    break;

            }
        }
    }
    private void validateInput() {
        if (spinPosition == 0) { //decimal
            input.setInputType(TYPE_CLASS_NUMBER);
        } else if (spinPosition == 1) { //biner
            input.setInputType(TYPE_CLASS_NUMBER);
        } else if (spinPosition == 2) { //octal
            input.setInputType(TYPE_CLASS_NUMBER);
        } else { //hexa
            input.setInputType(TYPE_TEXT_FLAG_CAP_CHARACTERS);
        }
    }


}
