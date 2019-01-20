package greenhouse.smart.smartgreenhouse;

import android.app.AlertDialog;
import android.hardware.usb.UsbManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

import greenhouse.smart.smartgreenhouse.ArduinoConnectionPck.ArduinoConnection;

public class ConnectionArduinoActivity extends AppCompatActivity {

    ArduinoConnection arduinoConnection;
    TextView textViewTestConnection;
    Button buttonTestConnection, buttonTestLer;

    AlertDialog.Builder builder1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_arduino);

        textViewTestConnection = (TextView) findViewById(R.id.textViewTestConnection);
        buttonTestConnection = (Button) findViewById(R.id.buttonTestConnection);
        buttonTestLer = (Button) findViewById(R.id.buttonTestReturn);

        arduinoConnection = new ArduinoConnection(this);
        arduinoConnection.setConnection();

        buttonTestConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduinoConnection.setArduinoData("OK");
                String test = arduinoConnection.getArduinoData();
                textViewTestConnection.setText(test);
            }
        });

        buttonTestLer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String test = arduinoConnection.getArduinoData();
                textViewTestConnection.setText(test);
            }
        });

    }

}
