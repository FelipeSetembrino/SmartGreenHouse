package greenhouse.smart.smartgreenhouse;

import android.annotation.SuppressLint;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class ActivityGreenhouseTester extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greenhouse_tester);

        //Ventoinha
        LinearLayout ventoinhaLayout = (LinearLayout) findViewById(R.id.ventoinhaItem);
        TextView textViewVentoinha = (TextView) ventoinhaLayout.findViewById(R.id.textEquipName);
        textViewVentoinha.setText("Ventoinha");
        Switch switchVentoinha = (Switch) ventoinhaLayout.findViewById(R.id.switchEquipOnOff);
        //Exaustor
        LinearLayout exaustorLayout = (LinearLayout) findViewById(R.id.exaustorItem);
        TextView textViewExaustor = (TextView) exaustorLayout.findViewById(R.id.textEquipName);
        textViewExaustor.setText("Exaustor");
        Switch switchExaustor = (Switch) exaustorLayout.findViewById(R.id.switchEquipOnOff);
        //Iluminação
        LinearLayout iluminacaoLayout = (LinearLayout) findViewById(R.id.iluminacaoItem);
        TextView textViewIluminacao = (TextView) iluminacaoLayout.findViewById(R.id.textEquipName);
        textViewIluminacao.setText("Iluminação");
        Switch switchIluminacao = (Switch) iluminacaoLayout.findViewById(R.id.switchEquipOnOff);
        //Pump
        LinearLayout pumpLayout = (LinearLayout) findViewById(R.id.pumpItem);
        TextView textViewPump = (TextView) pumpLayout.findViewById(R.id.textEquipName);
        textViewPump.setText("Bomba irrigação");
        Switch switchPump = (Switch) pumpLayout.findViewById(R.id.switchEquipOnOff);
        //Umidificador
        LinearLayout umidificadorLayout = (LinearLayout) findViewById(R.id.umidificadorItem);
        TextView textViewUmidificador = (TextView) umidificadorLayout.findViewById(R.id.textEquipName);
        textViewUmidificador.setText("Umidificador");
        Switch switchUmidificador = (Switch) umidificadorLayout.findViewById(R.id.switchEquipOnOff);

    }
}
