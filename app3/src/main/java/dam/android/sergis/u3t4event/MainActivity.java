package dam.android.sergis.u3t4event;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST=0;
    private EditText etEventName;
    private TextView tvCurrentData;
    private Bundle bundle;
//TODO ex1-2: declaramos el array de meses

    private String[] months;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
    }

    private void setUI() {
        etEventName=findViewById(R.id.etEventName);
        tvCurrentData=findViewById(R.id.tvCurrentData);
        bundle=new Bundle();
        tvCurrentData.setText("");

        //TODO ex1-2: inicializamos el array obteniendo los valores del array de strings.xml
        months = getResources().getStringArray(R.array.months);
    }

    public void editEventData(View v){
        Intent intent=new Intent(this, EventDataActivity.class);

        intent.putExtras(bundle);

        startActivityForResult(intent,REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==REQUEST && resultCode==RESULT_OK){
            if(data.getStringExtra("Month")!=null) {
                String month = months[Integer.parseInt(data.getStringExtra("Month"))];
                String priority = data.getStringExtra("Priority");
                String place = data.getStringExtra("Place");
                String day = data.getStringExtra("Day");
                String year = data.getStringExtra("Year");
                String hour = data.getStringExtra("Hour");
                String minute = data.getStringExtra("Minute");

                bundle.putString("EventName", etEventName.getText().toString());
                //TODO ex1-1: añadimos los valores actuales al bundle para poder restablecerlos al pulsar cancelar en la otra activity
                bundle.putString("Data", tvCurrentData.getText().toString());


                bundle.putInt("month", Integer.parseInt(data.getStringExtra("Month")));
                bundle.putString("priority", priority);
                bundle.putString("place", place);
                bundle.putString("day", day);
                bundle.putString("year", year);
                bundle.putString("hour", hour);
                bundle.putString("minute", minute);

                tvCurrentData.setText("Priority: " + priority + "\n" +
                        "Place: " + place + "\n" +
                        "Date: " + day + " " + month + " " + year + "\n" +
                        "Time: " + hour + ":" + minute);
            }
        }
    }


    // TODO: Ex1-3 metodo para restablecer el posible mensaje voltear la pantalla
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        tvCurrentData.setText(savedInstanceState.getString("data"));

        super.onRestoreInstanceState(savedInstanceState);


    }

    // TODO: Ex1-3 metodo para guardar el valor del posible mensaje para que al girar la pantalla no se pierda
    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putString("error",tvCurrentData.getText().toString());


        super.onSaveInstanceState(outState);


    }
}
