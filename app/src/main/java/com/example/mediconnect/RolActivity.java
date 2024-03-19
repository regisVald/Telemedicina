/*
package com.example.mediconnect;


import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;


import java.util.Calendar;

public class RolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol);

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
    }


}
*/