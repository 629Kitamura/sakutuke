package com.example.sakutuke;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity implements OnDateSetListener   {
    private ArrayList<miyakonojyo> ondolist;
    private EditText _etDatePicker;
    private TextView textView,tvDate1,tvDate2;
    private  DatePickerDialog datePickerDialog;
    private Spinner spinner2,spinner3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        ondolist = new ArrayList<miyakonojyo>();

       // ListView listView=findViewById(R.id.list_view1);

        tvDate1 = findViewById(R.id.textView3);
        tvDate2 = findViewById(R.id.textView4);
        tvDate2.setText("");
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        hinsyuset();
        tikiset();

    try {
        SharedPreferences prefer = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefer.edit();

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id ) {

                spinner2 = (Spinner) parent;
                String Item1 = (String) spinner2.getSelectedItem();
                editor.putString("hinsyu", Item1);
                editor.commit();
                Log.i("test3", Item1);
            }
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner3 = (Spinner) parent;
                String Item2 = (String) spinner3.getSelectedItem();
                editor.putString("tiki",Item2);
                editor.putInt("num",position);
                editor.commit();
                Log.i("test3", Item2);

                ondolist = new ArrayList<miyakonojyo>();
                readCSV(position+1);
            }

            public  void  onNothingSelected(AdapterView<?> arg0){
            }
        });

        }catch (Exception e){
                Toast.makeText(this,"spinner error",Toast.LENGTH_LONG);
    }



        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                int year = prefs.getInt("year",2021);
                int month = prefs.getInt("month",10);
                int day = prefs.getInt("day",10);

                String hinsyu = prefs.getString("hinsyu","RM110");
                String tiki = prefs.getString("tiki","??????");
                int number = prefs.getInt("num",0);

                String date1 = year+"/"+month+"/"+day;
                String date2 = month+"/"+day;

                double sum = 0;
                int i = 0;
                int ondo = 0;
                String Item = null;
                switch (hinsyu) {
                    case ("RM110"):
                        Item= "1100";
                        ondo = Integer.parseInt(Item);
                        break;
                    case ("RM115"):
                        Item = "1150";
                        ondo = Integer.parseInt(Item);
                        break;
                    case ("RM118"):
                        Item = "1180";
                        ondo = Integer.parseInt(Item);
                        break;
                    case ("RM125"):
                        Item = "1250";
                        ondo = Integer.parseInt(Item);
                        break;
                    case ("RM127"):
                        Item = "1270";
                        ondo = Integer.parseInt(Item);
                        break;
                    case ("RM135"):
                        Item = "1350";
                        ondo = Integer.parseInt(Item);
                        break;
                    default:
                }

                Log.i("test2", String.valueOf(ondo));
                Log.i("test2", String.valueOf(number));

/************************????????????????????????***********************************/
             try {
                 /*for (i = 0; i < ondolist.size(); i++) {
                     if (ondolist.get(i).getDate().contains(date1)) {
                         break;
                     }

                 }*/
                 //????????????????????????????????????
                 for (i = 0; i < ondolist.size(); i++) {
                     String[] date22 = ondolist.get(i).getDate().split("/");
                     String date3 = date22[1]+"/"+date22[2];
                     if(date3.contains(date2)){
                         break;
                     }
                 }

                 Log.i("test",i+"??????"+ondolist.get(i).getDate()+"j??????");

                 while (sum < ondo) {
                     sum += ondolist.get(i).getOndo();
                     i++;
                 }
                 Log.i("test",i+"??????"+ondolist.get(i).getDate()+","+sum);

                 String resultDate = ondolist.get(i).getDate();
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                 //1?????????
                 Calendar cal2 = Calendar.getInstance();
                 cal2.setTime(sdf.parse(resultDate));
                 cal2.add(Calendar.DATE,1);
                 Date d1 = cal2.getTime();
                 Log.i("test",sdf.format(d1)+"??????");
                 //1?????????
                 Calendar cal3 = Calendar.getInstance();
                 cal3.setTime(sdf.parse(resultDate));
                 cal3.add(Calendar.DATE,-1);
                 Date d2 = cal3.getTime();
                 Log.i("test",sdf.format(d2)+"??????");

                 //????????????????????????
                 String[] resuleDate1 = sdf.format(d1) .split("/");
                 String[] resuleDate2 = sdf.format(d2) .split("/");

                 tvDate2.setText("??????????????????"+resuleDate2[1]+"???"+resuleDate2[2]+"??????"+resuleDate1[1]+"???"+resuleDate1[2]+"???");
                 tvDate2.setTextSize(20);
                 tvDate2.setTextColor(Color.BLACK);
             }catch (Exception e){

             }
            }
        });
/*************************************************************************/
    TextView text1 = findViewById(R.id.textView6);
    text1.setText("?????????");
    text1.setTextSize(20);
    text1.setTextColor(Color.BLACK);

        TextView text2 = findViewById(R.id.textView7);
        text2.setText("?????????");
        text2.setTextSize(20);
        text2.setTextColor(Color.BLACK);

    TextView tvComment = findViewById(R.id.textView8);
    tvComment.setTextColor(Color.BLACK);
    tvComment.setText("????????????????????????????????????\n" +
            "??????4??????RM110???RM120???\n" +
            "??????5??????RM120???RM130???\n" +
            "??????6??????RM120??????130?????????");








    }
/************************************CSV????????????*********************************************/
        public void readCSV(int x) {
            try {
                InputStream inputStream = getResources().getAssets().open("aaa.csv");
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                int i = 1;
                while ((line = bufferedReader.readLine()) != null) {
                    if (i > 3) {
                        miyakonojyo array = new miyakonojyo();
                        StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                        String V1 = stringTokenizer.nextToken();
                        String V2 = stringTokenizer.nextToken();
                        String V3 = stringTokenizer.nextToken();
                        String V4 = stringTokenizer.nextToken();
                        String V5 = stringTokenizer.nextToken();
                        String V6 = stringTokenizer.nextToken();
                        String V7 = stringTokenizer.nextToken();
                        String V8 = stringTokenizer.nextToken();
                        String V9 = stringTokenizer.nextToken();
                        String V10 = stringTokenizer.nextToken();
                        String V11 = stringTokenizer.nextToken();
                        String V12 = stringTokenizer.nextToken();
                        String V13 = stringTokenizer.nextToken();
                        String V14 = stringTokenizer.nextToken();
                        String V15 = stringTokenizer.nextToken();
                        String V16 = stringTokenizer.nextToken();
                        String V17 = stringTokenizer.nextToken();
                        String V18 = stringTokenizer.nextToken();
                        String V19 = stringTokenizer.nextToken();

                        double V22 = 0.0;
                        switch (x){
                            case 1://??????
                                V22 =  (Double.parseDouble(V2));
                                break;
                            case 2://??????
                                V22 =  (Double.parseDouble(V3));
                                break;
                            case 3://??????
                                V22 =  (Double.parseDouble(V4));
                                break;
                            case 4://??????
                                V22 =  (Double.parseDouble(V5));
                                break;
                            case 5://?????????
                                V22 =  (Double.parseDouble(V6));
                                break;
                            case 6://??????
                                V22 =  (Double.parseDouble(V7));
                                break;
                            case 7://?????????
                                V22 =  (Double.parseDouble(V8));
                                break;
                            case 8://??????
                                V22 =  (Double.parseDouble(V9));
                                break;
                            case 9://??????
                                V22 =  (Double.parseDouble(V10));
                                break;
                            case 10://??????
                                V22 =  (Double.parseDouble(V11));
                                break;
                            case 11://??????
                                V22 =  (Double.parseDouble(V12));
                                break;
                            case 12://??????
                                V22 =  (Double.parseDouble(V13));
                                break;
                            case 13://??????
                                V22 =  (Double.parseDouble(V14));
                                break;
                            case 14://??????
                                V22 =  (Double.parseDouble(V15));
                                break;
                            case 15://?????????
                                V22 =  (Double.parseDouble(V16));
                                break;
                            case 16://?????????
                                V22 =  (Double.parseDouble(V17));
                                break;
                            case  17://?????????
                                V22 =  (Double.parseDouble(V18));
                                break;
                            case  18://??????
                                V22 =  (Double.parseDouble(V19));
                                break;
                            default:


                        }


                        array.setDate(V1);
                        array.setOndo(V22);
                        System.out.println(array.getDate() + "," + array.getOndo());
                        ondolist.add(array);
                      //  Log.i("test", ondolist.get(i - 4).getDate() + "??????");
                    }
                    i++;

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
/**************************???????????????**************************************/
    @Override
    public void onDateSet(android.widget.DatePicker view, int year,
                          int monthOfYear, int dayOfMonth) {
            SharedPreferences prefer = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = prefer.edit();
            editor.putInt("year", year);
            editor.putInt("month", monthOfYear + 1);
            editor.putInt("day", dayOfMonth);
            editor.commit();
            Log.i("test", year + "," + (monthOfYear + 1) + "," + dayOfMonth);
            String date = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;

            tvDate1.setText(date);
    }
        public void showDatePickerDialog (View v){
            DialogFragment newFragment = new DatePick();
            newFragment.show(getSupportFragmentManager(), "datePicker");
    }
/********************************???????????????***************************************/
    public void hinsyuset() {
        try {
            SharedPreferences prefer = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = prefer.edit();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);

            adapter.add("RM110");
            adapter.add("RM115");
            adapter.add("RM118");
            adapter.add("RM125");
            adapter.add("RM127");
            adapter.add("RM135");

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter);
        }catch (Exception e){
            Toast.makeText(this,"error",Toast.LENGTH_LONG);
        }
    }//??????????????? tkm0on.hatenablog.com/entry/2015/05/19/163735
/******************************???????????????*************************************/
     public void tikiset() {
        try {
            SharedPreferences prefer = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = prefer.edit();
            ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);

            adapter3.add("???????????????");
            adapter3.add("???????????????");
            adapter3.add("???????????????");
            adapter3.add("???????????????");
            adapter3.add("??????????????????");
            adapter3.add("???????????????");
            adapter3.add("??????????????????");
            adapter3.add("???????????????");
            adapter3.add("???????????????");
            adapter3.add("???????????????");
            adapter3.add("???????????????");
            adapter3.add("???????????????");
            adapter3.add("???????????????");
            adapter3.add("???????????????");
            adapter3.add("??????????????????");
            adapter3.add("?????????????????????");
            adapter3.add("?????????????????????");
            adapter3.add("??????????????????");

            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner3.setAdapter(adapter3);

        }catch (Exception e){
        }
    }


}
