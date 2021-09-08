package com.example.sakutuke;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePick extends DialogFragment{
    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),(MainActivity)getActivity(), year,  month, day);
    }

  /*  @Override
    public void onDateSet(android.widget.DatePicker view, int year,
                          int monthOfYear, int dayOfMonth) {
        SharedPreferences prefer = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = prefer.edit();
        editor.putInt("year", year);
        editor.putInt("month", monthOfYear + 1);
        editor.putInt("day", dayOfMonth);
        editor.commit();
        Log.i("test","a."+year+"," + monthOfYear+","+dayOfMonth);


    }
*/

}
