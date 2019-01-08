package greenhouse.smart.smartgreenhouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GrowTypeActivity extends AppCompatActivity {

    String listViewTitle[] = {
            "Alface",
            "Tomate Cereja",
            "Salsinha",
            "Hortelã",
            "Cenoura",
            "Gengibre"
    };
    int listViewImage[] = {
            R.drawable.alface,
            R.drawable.tomate_cereja,
            R.drawable.salsinha,
            R.drawable.hortela,
            R.drawable.cenoura,
            R.drawable.gengibre
    };

    String from[] = {"listview_item_title", "listview_image"};
    int to[] = {R.id.listview_item_title, R.id.listview_image};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grow_type);

        List<HashMap<String, String>> listHashMap = new ArrayList<>();

        for(int i = 0; i < listViewTitle.length; i++){
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put(from[0], listViewTitle[i]);
            hashMap.put(from[1], Integer.toString(listViewImage[i]));
            listHashMap.add(hashMap);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), listHashMap, R.layout.activity_list_view_item, from, to);
        ListView listViewGrowType = (ListView) findViewById(R.id.list_view_grow_type);
        listViewGrowType.setAdapter(simpleAdapter);

    }
}
