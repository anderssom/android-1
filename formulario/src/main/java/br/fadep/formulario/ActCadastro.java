package br.fadep.formulario;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.fadep.formulario.adapter.PessoaAdapter;
import br.fadep.formulario.model.Pessoa;

public class ActCadastro extends Activity {

    private TextView txtNome,txtCpf, txtRg, txtEmail;
    private EditText edtNome, edtCpf, edtRg, edtEmail;
    private Button btnNovo, btnSalvar,btnExcluir;
    private Spinner spnSexo;
    private Pessoa pessoa = null;
    private PessoaAdapter pessoaAdapter;
    private ListView listView;
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
        btnNovo = (Button) findViewById(R.id.btnNovo);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);
        btnSalvar = (Button)findViewById(R.id.btnSalvar);
        listView = (ListView) findViewById(R.id.listView);

        pessoaAdapter = new PessoaAdapter(this);
        listView.setAdapter(pessoaAdapter);

        List<String> sexos = new ArrayList<>();
        sexos.add("Masculino");
        sexos.add("Feminino");
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,sexos.toArray());
        spnSexo.setAdapter(adapter);


        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pessoa = null;
                limpar();
            }
        });
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluir();
            }
        });
        btnSalvar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View vi){
                salvar();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pessoa = (Pessoa)pessoaAdapter.getItem(position);
                setPessoa(pessoa);
            }
        });


    }

    public void limpar(){
        edtEmail.setText("");
        edtRg.setText("");
        edtCpf.setText("");
        edtNome.setText("");
    }

    public void excluir(){
        if (pessoa != null){
            pessoaAdapter.removePessoa(pessoa);
        }else {
            Toast.makeText(this,"Favor selecionar uma pessoa!",Toast.LENGTH_LONG).show();
        }
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
        if (pessoa == null) {
            pessoa = new Pessoa();
        }
        pessoa.setNome(edtNome.getText().toString());
        pessoa.setRg(edtRg.getText().toString());
        pessoa.setCpf(edtCpf.getText().toString());
        pessoa.setEmail(edtEmail.getText().toString());
        pessoa.setSexo(spnSexo.getSelectedItem().toString());
        if (pessoaAdapter.contains(pessoa)){
            pessoaAdapter.updatePessoa(pessoa);
        }else {
            pessoaAdapter.addPessoa(pessoa);
        }
        pessoa = null;
    }

    public void setPessoa(Pessoa pes){
        this.pessoa = pes;
        edtCpf.setText(pessoa.getCpf());
        edtRg.setText(pessoa.getRg());
        edtNome.setText(pessoa.getNome());
        edtEmail.setText(pessoa.getEmail());
        SpinnerAdapter adapter = spnSexo.getAdapter();
        String sexo = pessoa.getSexo();
        for (int w = 0; w < adapter.getCount();w++){
            if (sexo.equals(adapter.getItem(w))){
                spnSexo.setSelection(w);
                break;
            }
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
        edtNome.setText(state.getString("nome"));
        edtCpf.setText(state.getString("cpf"));
        edtRg.setText(state.getString("rg"));
        edtEmail.setText(state.getString("email"));
    }
}
