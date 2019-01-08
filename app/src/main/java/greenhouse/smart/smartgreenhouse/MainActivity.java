package greenhouse.smart.smartgreenhouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonGrowType = (Button) findViewById(R.id.buttonGrowType);
        buttonGrowType.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,GrowTypeActivity.class));
            }
        });

        Button buttonConnectionArduino = (Button) findViewById(R.id.buttonConnectionArduino);
        buttonConnectionArduino.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ConnectionArduinoActivity.class));
            }
        });

        Button buttonGreenhouseTester = (Button) findViewById(R.id.buttonGreenhouseTester);
        buttonGreenhouseTester.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ActivityGreenhouseTester.class));
            }
        });

    }

}
