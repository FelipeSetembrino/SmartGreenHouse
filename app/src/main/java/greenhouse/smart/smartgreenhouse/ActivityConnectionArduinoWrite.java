package greenhouse.smart.smartgreenhouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    }

    @Override
    public void onResume() {
        super.onResume();
        arduinoConnectionUsb.setConnection(getIntent());
    }

    @Override
    public void run() {
        arduinoConnectionUsb.setRun();
    }
}
