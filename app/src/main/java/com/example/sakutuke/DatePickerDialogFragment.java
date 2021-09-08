package com.example.sakutuke;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;
public class DatePickerDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) { //ダイアログを生成する処理
        Calendar calendar = Calendar.getInstance(); //現在の日時情報を持つカレンダーオブジェクトを取得
        final MainActivity mainActivity = (MainActivity) getActivity(); //現在のアクティビティを取得
        DatePickerDialog datePickerDialog = new DatePickerDialog(   //DatePickerDialogオブジェクトを生成
                mainActivity,   //第１引数(Activity)
                new DatePickerDialog.OnDateSetListener() {  //第２引数(日付が設定されたときのリスナ)
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) { //日付が設定されたときの処理
                        String dateText = (month + 1) + "月" + dayOfMonth + "日"; //"〇月×日"の文字列を生成
                       // mainActivity.setTextToEditText(dateText);   //EditTextに文字列を設定
                    }
                },
                calendar.get(Calendar.YEAR),    //第３引数(西暦初期値)
                calendar.get(Calendar.MONTH),   //第４引数(月初期値)
                calendar.get(Calendar.DAY_OF_MONTH) //第５引数(日初期値)
        );
        return datePickerDialog;
    }
}
