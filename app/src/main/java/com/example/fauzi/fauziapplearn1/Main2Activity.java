package com.example.fauzi.fauziapplearn1;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    EditText mTextNama;
    Button mButtonOrder,mButtonPlus,mButtonMin;
    TextView mTextHarga,mTextQty;
    Spinner mSpinnerMenu;
    RadioGroup mRadioGroup;
    RadioButton mRadioItem1, mRadioItem2;

    int harga = 0,qty = 0;
    List<String> mListMenu = new ArrayList<>();
    //custom variable to get context
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_main2);
        mContext = getApplicationContext();
        mTextNama = (EditText) findViewById(R.id.mTextNama);
        mButtonOrder = (Button) findViewById(R.id.mButtonOrder);
        mTextHarga = (TextView) findViewById(R.id.mTextHarga);
        mButtonPlus = (Button)findViewById(R.id.mButtonPlus);
        mButtonMin = (Button)findViewById(R.id.mButtonMin);
        mTextQty = (TextView)findViewById(R.id.mTextQty);
        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        mRadioItem1 = (RadioButton) findViewById(R.id.mRadioItem1);
        mRadioItem2 = (RadioButton) findViewById(R.id.mRadioItem2);
        mRadioItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Item 1 diklik", Toast.LENGTH_SHORT).show();
            }
        });
        mRadioItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"Item 2 diklik", Toast.LENGTH_SHORT).show();
            }
        });


        mSpinnerMenu = (Spinner) findViewById(R.id.mSpinnerMenu);
        mListMenu.add("---------");
        mListMenu.add("Martabak");
        mListMenu.add("Piscok Bakar Ice Cream Sandwich");
        mListMenu.add("Lumpia Basah");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,mListMenu);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerMenu.setAdapter(dataAdapter);

        /*mButtonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Hi, nama saya" + mTextNama.getText(),Toast.LENGTH_LONG).show();
                mTextHarga.setText(mTextNama.getText());
            }
        });*/
    }

    public void onClickOrder(View view){
//        Toast.makeText(getApplicationContext(),"Hi, nama saya" + mTextNama.getText(),Toast.LENGTH_LONG).show();
//        mTextHarga.setText(mTextNama.getText());
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL,"arieridwansyah@gmail.com");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,mTextNama.getText());
        emailIntent.putExtra(Intent.EXTRA_TEXT,
                "Saya Pesan "
                + mSpinnerMenu.getSelectedItem()
                + " sebanyak "
                + mTextQty.getText()
                + " Seharga "
                 + mTextHarga.getText()
                );
        try {
         startActivity(Intent.createChooser(emailIntent,"Send mail..."));
            finish();
        }catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(this,"There is no email client installed",Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickPlus(View view){
        harga = harga + 5;
        qty = qty + 1;
        //automatic jadi string jika ditambah dengan string
        mTextHarga.setText("$" + harga);

    }

    public void onClickMin(View view){
        if(harga != 0){
            harga = harga - 5;
            qty = qty - 1;
        }else{
            harga = 0;
            qty = 0;
        }

        mTextHarga.setText("$" + harga);
        mTextQty.setText(qty+"");

    }

    public void onClickreset(View view){
        harga = 0;
        qty = 0;
        mTextNama.setText("");
        mTextHarga.setText("$"+harga);
        mTextQty.setText(qty+"");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
