package com.example.mediconnect;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class Consulta extends AppCompatActivity {

    private EditText editTextClienteId, editTextMontoConsulta;
    private Spinner spinnerMedico, spinnerTipoPago;
    private Button buttonSeleccionarFecha, buttonGuardar;
    private TextView textViewFechaConsulta;

    public Consulta(int i, String s, double v, String efectivo, String date) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        editTextClienteId = findViewById(R.id.editTextClienteId);
        editTextMontoConsulta = findViewById(R.id.editTextMontoConsulta);
        spinnerMedico = findViewById(R.id.spinnerMedico);
        spinnerTipoPago = findViewById(R.id.spinnerTipoPago);
        buttonSeleccionarFecha = findViewById(R.id.buttonSeleccionarFecha);
        buttonGuardar = findViewById(R.id.buttonGuardar);
        textViewFechaConsulta = findViewById(R.id.textViewFechaConsulta);

        // Configurar el spinner de médico con una lista de opciones
        ArrayAdapter<CharSequence> medicoAdapter = ArrayAdapter.createFromResource(this,
                R.array.medicos_array, android.R.layout.simple_spinner_item);
        medicoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMedico.setAdapter(medicoAdapter);

        // Configurar el spinner de tipo de pago con una lista de opciones
        ArrayAdapter<CharSequence> tipoPagoAdapter = ArrayAdapter.createFromResource(this,
                R.array.tipo_pago_array, android.R.layout.simple_spinner_item);
        tipoPagoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoPago.setAdapter(tipoPagoAdapter);

        // Agregar un listener al botón de seleccionar fecha
        buttonSeleccionarFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDatePicker();
            }
        });

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatos();
            }
        });
    }

    private void mostrarDatePicker() {
        // Obtener la fecha actual
        Calendar calendario = Calendar.getInstance();
        int año = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int día = calendario.get(Calendar.DAY_OF_MONTH);

        // Crear un DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Actualizar el TextView con la fecha seleccionada
                        String fechaSeleccionada = dayOfMonth + "/" + (month + 1) + "/" + year;
                        textViewFechaConsulta.setText(fechaSeleccionada);
                    }
                }, año, mes, día);

        // Mostrar el DatePickerDialog
        datePickerDialog.show();
    }

    private void guardarDatos() {
        // Obtener los valores ingresados por el usuario
        int clienteId = Integer.parseInt(editTextClienteId.getText().toString());
        double montoConsulta = Double.parseDouble(editTextMontoConsulta.getText().toString());

        // Aquí puedes obtener el médico, tipo de pago y fecha de consulta seleccionados
        String medicoSeleccionado = spinnerMedico.getSelectedItem().toString();
        String tipoPagoSeleccionado = spinnerTipoPago.getSelectedItem().toString();
        String fechaConsulta = textViewFechaConsulta.getText().toString();

        Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
    }
}
