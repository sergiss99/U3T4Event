package dam.android.sergis.u3t4event;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment{

    private TimePickerDialog.OnTimeSetListener piker;
    private Calendar c;

    //Todo Ex4: Recibimos el listener y el calendar para saber la hora y restablecerla en caso de que hubiera una anterior
    public TimePickerFragment(EventDataActivity eventDataActivity, Calendar cal) {
        piker=eventDataActivity;
        c=Calendar.getInstance();
        c=cal;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), piker, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }


}