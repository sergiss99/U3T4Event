package dam.android.sergis.u3t4event;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

public class EventDataActivity extends AppCompatActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{

    private TextView tvEventName;
    private RadioGroup rgpriority;
    private DatePicker dpDate;
    private TimePicker tpTime;
    private Button btAccept;
    private Button btCancel;
    private String priority="Normal";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_data);

        setUI();

        Bundle inputData=getIntent().getExtras();

        tvEventName.setText(inputData.getString("EventName"));
    }

    private void setUI() {
        tvEventName=findViewById(R.id.tvEventName);
        rgpriority= findViewById(R.id.rgPriority);
        dpDate=findViewById(R.id.dpDate);
        rgpriority.check(R.id.rbNormal);
        tpTime=findViewById(R.id.tpTime);
        btAccept=findViewById(R.id.btAccept);
        btCancel=findViewById(R.id.btCancel);

        btAccept.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        rgpriority.setOnCheckedChangeListener(this);

    }

    @Override
    public void onClick(View v){
        Intent activityResult=new Intent();
        Bundle eventData =new Bundle();
        switch (v.getId()){
            case R.id.btAccept:
                String[] month={"January","February","March","April","May","June","July","August","September","October","November","December"};
                eventData.putString("EventData","Priority: "+priority+"\n"+
                        "Month: "+month[dpDate.getMonth()]+"\n"+
                        "Day: "+dpDate.getDayOfMonth()+"\n"+
                        "Year: "+dpDate.getYear());
                break;
            case R.id.btCancel:
                eventData.putString("EventData","");
                break;
        }

        activityResult.putExtras(eventData);
        setResult(RESULT_OK,activityResult);

        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rbLow:
                priority="Low";
                break;
            case R.id.rbNormal:
                priority="Normal";
                break;
            case R.id.rbHigh:
                priority="High";
                break;
        }
    }
}
