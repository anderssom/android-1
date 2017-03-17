package br.fadep.formulario;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class ActCalculadora extends Activity {

    private EditText edtVisor;
    private Double numero1, numero2, resultado;
    private String operacao;
    private Boolean limparVisor = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
        edtVisor = (EditText)findViewById(R.id.edtDisplay);
    }

    public void onClickNumero(View view){
        Button btn = (Button)view;
        String value = edtVisor.getText().toString();
        if (limparVisor){
            value = "";
            limparVisor = false;
        }
        value = value + btn.getText();
        edtVisor.setText(value);
    }
    public void onClickOperacao(View view){
        Button btn = (Button) view;
        operacao = btn.getText().toString();
        String value = edtVisor.getText().toString();
        value = value.replaceAll(",",".");
        numero1 = Double.parseDouble(value);
        limparVisor = true;
    }

    public void onClickCalcular(View view){
        String value = edtVisor.getText().toString();
        value = value.replaceAll(",",".");
        numero2 = Double.parseDouble(value);
        switch (operacao){
            case "+":
                resultado = numero1 + numero2;
                break;
            case "-":
                resultado = numero1 - numero2;
                break;
            case "*" :
                resultado = numero1 * numero2;
                break;
            case "/":
                resultado = numero1 / numero2;
                break;
        }
        DecimalFormat df = new DecimalFormat();
        edtVisor.setText(df.format(resultado));
        limparVisor = true;
        numero1 = numero2;
    }
}




