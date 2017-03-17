package br.fadep.formulario;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActSomar extends Activity {

    private Button btnSomar;
    private EditText edtNumero1, edtNumero2;
    private EditText edtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_somar);
        btnSomar = (Button) findViewById(R.id.btnSomar);
        edtNumero1 = (EditText)findViewById(R.id.edtNumero1);
        edtNumero2 = (EditText)findViewById(R.id.edtNumero2);
        edtResultado = (EditText)findViewById(R.id.txtResultado);
//        btnSomar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });

    }

    public void calcular(View view) {
        String msg = "Favor informar o número!";
        if (edtNumero1.getText().toString().isEmpty() ){
            Toast.makeText(ActSomar.this,msg,Toast.LENGTH_LONG).show();
            return;
        }

        if (edtNumero2.getText().toString().isEmpty()){
            Toast.makeText(ActSomar.this, msg,Toast.LENGTH_SHORT).show();
            return;
        }
        String texto = edtNumero1.getText().toString();
        Double numero1 = Double.parseDouble(texto);
        texto = edtNumero2.getText().toString();
        Double numero2 = Double.parseDouble(texto);
        Double resultado = numero1 + numero2;
        edtResultado.setText(String.valueOf(resultado));
    }
}
