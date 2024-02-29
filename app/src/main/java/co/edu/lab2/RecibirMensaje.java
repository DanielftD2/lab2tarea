package co.edu.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecibirMensaje extends AppCompatActivity {
    List<String> mensajes = new ArrayList<>();
    public static final String EXTRA_MESSAGE = "mensaje";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibir_mensaje);
        Intent intent = getIntent();
        String mensaje = intent.getStringExtra(EXTRA_MESSAGE);
        TextView vistaMensaje = findViewById(R.id.mensaje);
        if (mensaje != null && !mensaje.isEmpty()) {
            mensajes.add(mensaje + "\n");
            String formattedMessages = formatMessages();
            vistaMensaje.setText(formattedMessages);
        } else {
            vistaMensaje.setText("Esta vacío");
        }
    }

    public void onSendMessage(View view) {
        EditText mensajeView = findViewById(R.id.mensajeenviado);
        String mensaje = mensajeView.getText().toString().trim();
        if (!mensaje.isEmpty()) {
            mensajes.add("Comerciante: "+mensaje + "\n");
            String formattedMessages = formatMessages();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(EXTRA_MESSAGE, formattedMessages);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(EXTRA_MESSAGE, "Esta vacío");
            startActivity(intent);
        }
    }

    private String formatMessages() {
        StringBuilder formattedMessages = new StringBuilder();
        for (String msg : mensajes) {
            formattedMessages.append(msg);
        }
        return formattedMessages.toString();
    }
}