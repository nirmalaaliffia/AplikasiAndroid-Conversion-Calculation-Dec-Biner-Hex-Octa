package com.binaryconvert.converter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.text.TextWatcher;

import static android.text.InputType.TYPE_CLASS_NUMBER;

public class Main2ActivityCalculation extends AppCompatActivity {

    private TextView hasil;
    private EditText nilai1, nilai2;
    private Button btnkurang,btntambah,btnkali,btnbagi,btnconvert,btncalculate,btnmod;

    private Spinner spinnercalcu1, spinnercalcu2, spinnerhasil;
    public String[] spinnilai1item = new String[]{"Decimal","Octal"};
    public String[] spinnilai2item = new String[]{"Decimal","Octal"};
    public String[] spinhasilitem = new String[]{"Decimal","Octal"};
    private ArrayAdapter<String> spinAdapter;
    private ArrayAdapter<String> spinAdapter2;

    private int spinPosisi,spinposisi2;
    private long hasilconvert1;
    private long hasilconvert2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_calculation);

        spinPosisi=0;
        nilai1 = (EditText) findViewById(R.id.calcu1Text);
        nilai1.addTextChangedListener(new textwatcher1(nilai1));
        nilai2 = (EditText) findViewById(R.id.calcu2Text);
        nilai2.addTextChangedListener(new textwatcher1(nilai2));
        hasil = (TextView) findViewById(R.id.hasilView);


        btnkurang = (Button) findViewById(R.id.buttonMinus);

        btntambah = (Button) findViewById(R.id.buttonPlus);

        btnkali = (Button) findViewById(R.id.buttonX);

        btnbagi = (Button) findViewById(R.id.buttonBagi);

        btnmod = (Button) findViewById(R.id.buttonMod);

        btncalculate = (Button) findViewById(R.id.btncalcu);
        btncalculate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                opencalcu();
            }
        });

        btnconvert = (Button) findViewById(R.id.btnconvert);
        btnconvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openconvert();
            }
        });


        spinnercalcu1 = (Spinner) findViewById(R.id.spinnercalcu1);
        spinAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,spinnilai1item);
        spinnercalcu1.setAdapter(spinAdapter);
        spinnercalcu1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                spinPosisi = i;
                nilai1.setText("");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnercalcu2 = (Spinner) findViewById(R.id.spinnercalcu2);
        spinAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,spinnilai2item);
        spinnercalcu2.setAdapter(spinAdapter2);
        spinnercalcu2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                spinposisi2 = i;
                nilai2.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        btnmod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertfirst();
                duMod();
            }
        });

        btnkurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertfirst();
               dosubstract();
            }
        });

        btntambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertfirst();
                dosum();
            }
        });

        btnkali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertfirst();
                domultiple();
            }
        });

        btnbagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertfirst();
                dodivide();
            }
        });
    }

    private void duMod() {
        long a=this.hasilconvert1;
        long b= this.hasilconvert2;
        long hasilcalculation=(a%b);
        hasil.setText("" + hasilcalculation);
        hasil.setTextSize(20);
    }


    private void dodivide() {
        long a=this.hasilconvert1;
        long b= this.hasilconvert2;
        long hasilcalculation=(a/b);
        hasil.setText("" + hasilcalculation);
        hasil.setTextSize(20);
    }

    private void domultiple() {
        long a=this.hasilconvert1;
        long b= this.hasilconvert2;
        long hasilcalculation=(a*b);
        hasil.setText("" + hasilcalculation);
        hasil.setTextSize(20);
    }

    private void dosum() {
        long a=this.hasilconvert1;
        long b= this.hasilconvert2;
        long hasilcalculation=(a+b);
        hasil.setText("" + hasilcalculation);
        hasil.setTextSize(20);
    }

    private void dosubstract() {
        long a= this.hasilconvert1;
        long b =this.hasilconvert2;
        long hasilcalculation=(a-b);
       hasil.setText("" + hasilcalculation);
       hasil.setTextSize(20);
    }


    private void convertvalue1() {
        String value1 = nilai1.getText().toString();
        long tmp1;
    String container;
        //KOONVERT KE DESIMAL DULU SMUANYA, STELAH ITU DI LAKUKAN CALCULATE, ABISTU DIRUBAH KE HASILNYA
        if (!checkingInputValidation()) {
            switch (spinPosisi) {
                case 0: //decimal
                    try {
                        tmp1=Integer.parseInt(value1);
                        sethasilconvert1(tmp1);
                    } catch (Exception e) {
                        nilai1.setError("Something Wrong");
                        requestFocus(nilai1);
                    }
                    break;

                case 1: //oktal
                    try {
                        tmp1=Long.parseLong(value1,8);
                        sethasilconvert1(tmp1);

                    } catch (Exception e) {
                        nilai1.setError("Something Wrong");
                        requestFocus(nilai1);
                    }
                    break;
            }
        }
    }

    private void convertvalue2() {
        String value2 = nilai2.getText().toString();
        long tmp2;
        String container;
        //KOONVERT KE DESIMAL DULU SMUANYA, STELAH ITU DI LAKUKAN CALCULATE, ABISTU DIRUBAH KE HASILNYA
        if (!checkingInputValidation()) {
            switch (spinposisi2) {
                case 0: //decimal
                    try {
                        //   hasil.setText(value1);
                        //  hasil.setTextSize(20);
                        tmp2=Integer.parseInt(value2);
                        sethasilconvert2(tmp2);
                    } catch (Exception e) {
                        nilai2.setError("Something Wrong");
                        requestFocus(nilai2);
                    }
                    break;
                case 1: //oktal
                    try {
                        tmp2=Long.parseLong(value2,8);
                        sethasilconvert2(tmp2);
                        //  hasil.setText(""+Long.parseLong(value1,8));
                        //    hasil.setTextSize(20);
                        //     tmp1=tmp1+Integer.parseInt(value1,8);
                    } catch (Exception e) {
                        nilai2.setError("Something Wrong");
                        requestFocus(nilai2);
                    }
                    break;
            }
        }
    }



    private void convertfirst() {
        convertvalue1();
        convertvalue2();
    }

    private boolean checkingInputValidation() {
        String gettingInput = nilai1.getText().toString();
        if (nilai1.getText().toString().trim().isEmpty()) {
            nilai1.setError("Silahkan inputkan nilai yang akan dikonversikan");
            requestFocus(nilai1);
            return true;
        } else if (spinPosisi == 2 && gettingInput.matches(".*[8-9].*")) {
            nilai1.setError("Masukkan nilai input hanya interval dari 0 sampai 7");
            requestFocus(nilai1);
            return true;
        }else if (gettingInput.length() > 15) {
            nilai1.setError("Masukkan nilai input maksimal 6 digit");
            requestFocus(nilai1);
            return true;
        }
        return false;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
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

    public void sethasilconvert1(long hasilconvert1) {
        this.hasilconvert1 = hasilconvert1;

    }

    public void sethasilconvert2(long hasilconvert2) {
        this.hasilconvert2 = hasilconvert2;
    }




    private class textwatcher1 implements TextWatcher {
        private View view;

        private textwatcher1(View view) {
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
        if (spinPosisi == 0) { //decimal
            nilai1.setInputType(TYPE_CLASS_NUMBER);
            nilai2.setInputType(TYPE_CLASS_NUMBER);
        }  else{ //octal
            nilai1.setInputType(TYPE_CLASS_NUMBER);
            nilai2.setInputType(TYPE_CLASS_NUMBER);
        }
    }



}
