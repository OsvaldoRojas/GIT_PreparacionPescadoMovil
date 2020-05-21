package com.grupo.pinsa.libraries.dialogos;

import android.app.DatePickerDialog;
import android.content.Context;

import androidx.annotation.NonNull;

import java.util.Calendar;

/**
 * Created by Juan J. Palomera on 25/10/2018
 */

class DialogoCalendar extends DatePickerDialog {
    private static int DAY_OF_MONTH;
    private static int MONTH;
    private static int YEAR;

    public DialogoCalendar(@NonNull Context context, OnDateSetListener listener) {
        super(context, listener, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        DAY_OF_MONTH = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        MONTH = Calendar.getInstance().get(Calendar.MONTH);
        YEAR = Calendar.getInstance().get(Calendar.YEAR);
    }

    public DialogoCalendar setDay(int day) {
        return setDate(day, MONTH, YEAR);
    }

    public DialogoCalendar setMonth(int month) {
        return setDate(DAY_OF_MONTH, month, YEAR);
    }

    public DialogoCalendar setYear(int year) {
        return setDate(DAY_OF_MONTH, MONTH, year);
    }

    public DialogoCalendar setDate(int year, int month, int day) {
        super.updateDate(year, month, day);
        return this;
    }

    public DialogoCalendar setMinDate(long milisegundos) {
        super.getDatePicker().setMinDate(milisegundos);

        return this;
    }

    public DialogoCalendar setMaxDate(long milisegundos) {
        super.getDatePicker().setMaxDate(milisegundos);
        return this;
    }
}
