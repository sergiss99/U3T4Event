package dam.android.sergis.u3t4event;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import static dam.android.sergis.u3t4event.R.id.date;

public class EventDataActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private TextView tvEventName;
    private TextView tvDate;
    private TextView tvTime;
    private RadioGroup rgpriority;
    private Button btAccept;
    private Button btCancel;
    private String priority = "Normal";
    private EditText etPlace;
    private Calendar cal;
    private Button date;
    private Button time;

    //TODO ex1-1: declaramos el Bundle como variable local
    private Bundle inputData;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_data);

        setUI();

        inputData = getIntent().getExtras();

        tvEventName.setText(inputData.getString("EventName"));
        lastValues();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void lastValues() {
        // TODO: Ex1-3 obtenemos los valores pasados dese el main activity y los asignamos, fecha, hora, lugar ...

        if (inputData.getString("Data") != null) {
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(inputData.getString("hour")));
            cal.set(Calendar.MINUTE, Integer.parseInt(inputData.getString("minute")));

            etPlace.setText(inputData.getString("place"));


            int year = Integer.parseInt(inputData.getString("year"));
            int month = inputData.getInt("month");
            int day = Integer.parseInt(inputData.getString("day"));

            cal.set(year, month, day);

            if (inputData.getString("priority") == "Low") {
                rgpriority.check(R.id.rbLow);
            } else if (inputData.getString("priority") == "Normal") {
                rgpriority.check(R.id.rbNormal);
            } else {
                rgpriority.check(R.id.rbHigh);
            }

            tvTime.setText(cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE));
            tvDate.setText(cal.get(Calendar.DAY_OF_MONTH) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.YEAR));


        }
    }

    private void setUI() {
        tvEventName = findViewById(R.id.tvEventName);
        rgpriority = findViewById(R.id.rgPriority);
        rgpriority.check(R.id.rbNormal);
        btAccept = findViewById(R.id.btAccept);
        btCancel = findViewById(R.id.btCancel);
        etPlace = findViewById(R.id.etPlace);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        btAccept.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        rgpriority.setOnCheckedChangeListener(this);
        date.setOnClickListener(this);
        time.setOnClickListener(this);
        cal = Calendar.getInstance();

        tvDate = findViewById(R.id.tvDate);
        tvTime = findViewById(R.id.tvTime);


    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        Intent activityResult = new Intent();
        Bundle eventData = new Bundle();
        switch (v.getId()) {
            case R.id.btAccept:

                eventData.putString("Priority", priority);
                eventData.putString("Place", etPlace.getText().toString());
                eventData.putString("Day", String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
                eventData.putString("Month", String.valueOf(cal.get(Calendar.MONTH)));
                eventData.putString("Year", String.valueOf(cal.get(Calendar.YEAR)));
                eventData.putString("Hour", String.valueOf(cal.get(Calendar.HOUR_OF_DAY)));
                eventData.putString("Minute", String.valueOf(cal.get(Calendar.MINUTE)));

                activityResult.putExtras(eventData);
                setResult(RESULT_OK, activityResult);

                finish();
                break;
            case R.id.btCancel:
                //TODO ex1-1: obtenemos los valores que teniamos anteriormente, pasados a traves del bundle,
                // y los asignamos a el nuevo Bundle

                eventData.putString("Priority", inputData.getString("priority"));
                eventData.putString("Place", inputData.getString("place"));
                eventData.putString("Day", inputData.getString("day"));
                eventData.putString("Month", inputData.getString("month"));
                eventData.putString("Year", inputData.getString("year"));
                eventData.putString("Hour", inputData.getString("hour"));
                eventData.putString("Minute", inputData.getString("minute"));

                activityResult.putExtras(eventData);
                setResult(RESULT_OK, activityResult);

                finish();
                break;
                //TODO Ex4: Creamos los fragment y los mostramos
            case R.id.date:
                DialogFragment newFragment2 = new DatePickerFragment(this, cal);
                newFragment2.show(getSupportFragmentManager(), "datePicker");
                break;
            case R.id.time:
                DialogFragment newFragment = new TimePickerFragment(this, cal);
                newFragment.show(getSupportFragmentManager(), "timePicker");
                break;


        }


    }


    //TODO Ex4: Metodos para establecer la hora y la fecha una vez seleccionadas

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        cal.set(Calendar.MINUTE, minute);

        tvTime.setText(cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {

        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);

        tvDate.setText(cal.get(Calendar.DAY_OF_MONTH) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.YEAR));

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton h = findViewById(R.id.rbHigh);
        RadioButton n = findViewById(R.id.rbNormal);
        RadioButton l = findViewById(R.id.rbLow);


        switch (checkedId) {
            case R.id.rbLow:
                priority = l.getText().toString();
                break;
            case R.id.rbNormal:
                priority = n.getText().toString();
                break;
            case R.id.rbHigh:
                priority = h.getText().toString();
                break;
        }
    }


}
