package dam.android.sergis.u3t4event;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment{
    private DatePickerDialog.OnDateSetListener piker;
    private Calendar c ;

    //Todo Ex4: Recibimos el listener y el calendar para saber la fecha y restablecerla en caso de que hubiera una anterior
    public DatePickerFragment(EventDataActivity eventDataActivity, Calendar cal) {
        piker=eventDataActivity;
        c=Calendar.getInstance();
        c=cal;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        /* Use the current date as the default date in the picker */
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), piker, year, month, day);
    }


}