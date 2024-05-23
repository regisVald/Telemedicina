package com.example.mediconnect;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class activity_prices extends AppCompatActivity {

    private ListView pricesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prices);

        pricesListView = findViewById(R.id.pricesListView);

        // Dummy data for demonstration
        String[] services = new String[]{
                "Consulta General",
                "Consulta Especialista",
                "Examen de Sangre",
                "Radiografía"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.item_price,
                R.id.serviceName,
                services
        );

        pricesListView.setAdapter(adapter);
    }
}
