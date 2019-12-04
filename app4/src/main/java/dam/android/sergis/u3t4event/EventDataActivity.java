package dam.android.sergis.u3t4event;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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

public class EventDataActivity extends AppCompatActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{

    private TextView tvEventName;
    private RadioGroup rgpriority;
    private DatePicker dpDate;
    private TimePicker tpTime;
    private Button btAccept;
    private Button btCancel;
    private String priority="Normal";
    private EditText etPlace;

    //TODO ex1-1: declaramos el Bundle como variable local
    private Bundle inputData;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_data);

        setUI();

        inputData=getIntent().getExtras();

        tvEventName.setText(inputData.getString("EventName"));
        lastValues();
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    private void lastValues() {
        if(inputData.getString("Data")!=null) {
            tpTime.setHour(Integer.parseInt(inputData.getString("hour")));
            tpTime.setMinute(Integer.parseInt(inputData.getString("minute")));
            etPlace.setText(inputData.getString("place"));


            int year = Integer.parseInt(inputData.getString("year"));
            int month = inputData.getInt("month");
            int day = Integer.parseInt(inputData.getString("day"));

            dpDate.init(year, month, day, null);

            if(inputData.getString("priority")=="Low"){
                rgpriority.check(R.id.rbLow);
            }else if(inputData.getString("priority")=="Normal"){
                rgpriority.check(R.id.rbNormal);
            }else{
                rgpriority.check(R.id.rbHigh);
            }


        }
    }

    private void setUI() {
        tvEventName=findViewById(R.id.tvEventName);
        rgpriority= findViewById(R.id.rgPriority);
        dpDate=findViewById(R.id.dpDate);
        rgpriority.check(R.id.rbNormal);
        tpTime=findViewById(R.id.tpTime);
        btAccept=findViewById(R.id.btAccept);
        btCancel=findViewById(R.id.btCancel);
        etPlace=findViewById(R.id.etPlace);
        btAccept.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        rgpriority.setOnCheckedChangeListener(this);

        getRotation(this);


    }

    // TODO Ex2: Obtenemos la rotacion actual para segun esta mostrar el spinner solo o spinner y calendar

    public void getRotation(Context context) {
        final int rotation = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getOrientation();
        switch (rotation) {
            case Surface.ROTATION_0:
            case Surface.ROTATION_180:
                dpDate.setSpinnersShown(true);
                dpDate.setCalendarViewShown(false);
                break;
            case Surface.ROTATION_90:
            default:
                dpDate.setSpinnersShown(true);
                dpDate.setCalendarViewShown(true);
        }

    }


        @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v){
        Intent activityResult=new Intent();
        Bundle eventData =new Bundle();
        switch (v.getId()){
            case R.id.btAccept:

                eventData.putString("Priority",priority);
                eventData.putString("Place",etPlace.getText().toString());
                eventData.putString("Day", String.valueOf(dpDate.getDayOfMonth()));
                eventData.putString("Month", String.valueOf(dpDate.getMonth()));
                eventData.putString("Year", String.valueOf(dpDate.getYear()));
                eventData.putString("Hour", String.valueOf(tpTime.getHour()));
                eventData.putString("Minute", String.valueOf(tpTime.getMinute()));

                break;
            case R.id.btCancel:
                //TODO ex1-1: obtenemos los valores que teniamos anteriormente, pasados a traves del bundle,
                // y los asignamos a el nuevo Bundle
                eventData.putString("EventData",inputData.getString("Data"));
                break;
        }

        activityResult.putExtras(eventData);
        setResult(RESULT_OK,activityResult);

        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton h=findViewById(R.id.rbHigh);
        RadioButton n=findViewById(R.id.rbNormal);
        RadioButton l=findViewById(R.id.rbLow);


        switch (checkedId){
            case R.id.rbLow:
                priority=l.getText().toString();
                break;
            case R.id.rbNormal:
                priority=n.getText().toString();
                break;
            case R.id.rbHigh:
                priority=h.getText().toString();
                break;
        }
    }


}
