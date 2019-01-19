package greenhouse.smart.smartgreenhouse;

import android.hardware.usb.UsbManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;

import greenhouse.smart.smartgreenhouse.ArduinoConnectionPck.ArduinoConnection;

public class ConnectionArduinoActivity extends AppCompatActivity {

    ArduinoConnection arduinoConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_arduino);

        arduinoConnection = new ArduinoConnection(this);
        //arduinoConnection.setConnection();

    }

}
