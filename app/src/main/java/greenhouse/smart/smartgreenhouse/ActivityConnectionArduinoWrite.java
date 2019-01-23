package greenhouse.smart.smartgreenhouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import greenhouse.smart.smartgreenhouse.ArduinoConnectionPck.ArduinoConnectionUsb;

public class ActivityConnectionArduinoWrite extends AppCompatActivity implements Runnable {

    EditText editText;
    Button buttonSend;
    ArduinoConnectionUsb arduinoConnectionUsb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_arduino_write);

         editText = (EditText) findViewById(R.id.editText);
         buttonSend = (Button) findViewById(R.id.buttonSend);

        arduinoConnectionUsb = new ArduinoConnectionUsb(this);
        //arduinoConnectionUsb.setConnection();

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData(editText.getText().toString());
            }
        });

    }

    public void sendData(String data){
        arduinoConnectionUsb.sendData(data);
    }

    @Override
    public void onResume() {
        super.onResume();
        arduinoConnectionUsb.setConnection();
    }

    @Override
    public void run() {
        arduinoConnectionUsb.setRun();
    }
}
