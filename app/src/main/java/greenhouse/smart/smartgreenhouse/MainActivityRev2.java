package greenhouse.smart.smartgreenhouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivityRev2 extends AppCompatActivity {

    private LinearLayout itemConnection, itemCalibration, itemCustom, itemType;
    private ImageView itemConnectionImg, itemCalibrationImg, itemCustomImg, itemTypeImg;
    private TextView itemConnectionText, itemCalibrationText, itemCustomText, itemTypeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rev2);

        itemConnection = (LinearLayout) findViewById(R.id.activity_connected_greenhouse);
        itemConnectionImg = (ImageView) itemConnection.findViewById(R.id.imageItemMainActivity);
        itemConnectionText = (TextView) itemConnection.findViewById(R.id.textItemMainActivity);
        itemConnectionImg.setBackgroundResource(R.drawable.connect_icon);
        itemConnectionText.setText("Connect");

        itemCalibration= (LinearLayout) findViewById(R.id.activity_tester);
        itemCalibrationImg = (ImageView) itemCalibration.findViewById(R.id.imageItemMainActivity);
        itemCalibrationText = (TextView) itemCalibration.findViewById(R.id.textItemMainActivity);
        itemCalibrationImg.setBackgroundResource(R.drawable.calibration_icon);
        itemCalibrationText.setText("Test");

        itemCustom = (LinearLayout) findViewById(R.id.activity_set_grow);
        itemCustomImg = (ImageView) itemCustom.findViewById(R.id.imageItemMainActivity);
        itemCustomText = (TextView) itemCustom.findViewById(R.id.textItemMainActivity);
        itemCustomImg.setBackgroundResource(R.drawable.custom_grow_icon);
        itemCustomText.setText("Create your grow");

        itemType = (LinearLayout) findViewById(R.id.activity_grow_type);
        itemTypeImg = (ImageView) itemType.findViewById(R.id.imageItemMainActivity);
        itemTypeText = (TextView) itemType.findViewById(R.id.textItemMainActivity);
        itemTypeImg.setBackgroundResource(R.drawable.select_type_icon);
        itemTypeText.setText("Select your Grow");


    }
}
