package br.fadep.formulario;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ActCadastro extends Activity {

    private TextView txtNome,txtCpf, txtRg, txtEmail;
    private EditText edtNome, edtCpf, edtRg, edtEmail;
    private Button btnCancelar, btnSalvar;
    private Spinner spnSexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        spnSexo = (Spinner)findViewById(R.id.spnSexo);
        txtNome = (TextView)findViewById(R.id.txtNome);
        txtCpf = (TextView)findViewById(R.id.txtCpf);
        txtRg = (TextView)findViewById(R.id.txtRg);
        txtEmail = (TextView)findViewById(R.id.txtEmail);
        edtNome = (EditText)findViewById(R.id.edtNome);
        edtCpf = (EditText)findViewById(R.id.edtCpf);
        edtRg = (EditText)findViewById(R.id.edtRg);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnSalvar = (Button)findViewById(R.id.btnSalvar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar(v);
            }
        });
        btnSalvar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View vi){
                salvar();
            }
        });

        List<String> sexos = new ArrayList<>();
        sexos.add("Masculino");
        sexos.add("Feminino");
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,sexos.toArray());
        spnSexo.setAdapter(adapter);

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    public void cancelar(View view){
        finish();
    }

    public void salvar(){
        if (edtNome.getText().toString().isEmpty()){
            Toast.makeText(this,"Favor preencher o Nome",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (edtCpf.getText().toString().isEmpty()){
            Toast.makeText(this,"Favor preencher o CPF",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (edtRg.getText().toString().isEmpty()){
            Toast.makeText(this,"Favor preencher o RG",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (edtEmail.getText().toString().isEmpty()){
            Toast.makeText(this,"Favor preencher o E-mail",
                    Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putString("nome", edtNome.getText().toString());
        state.putString("cpf", edtCpf.getText().toString());
        state.putString("rg", edtRg.getText().toString());
        state.putString("email", edtEmail.getText().toString());
        state.putString("sexo",spnSexo.
                getSelectedItem().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
//        SpinnerAdapter adapter = spnSexo.getAdapter();

//        spnSexo.setSelection();
        edtNome.setText(state.getString("nome"));
        edtCpf.setText(state.getString("cpf"));
        edtRg.setText(state.getString("rg"));
        edtEmail.setText(state.getString("email"));

    }
}
