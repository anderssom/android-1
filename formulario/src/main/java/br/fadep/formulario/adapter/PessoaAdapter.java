package br.fadep.formulario.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.fadep.formulario.R;
import br.fadep.formulario.model.Pessoa;

public class PessoaAdapter extends BaseAdapter {

    private Context context;
    private List<Pessoa> lista = new ArrayList<>();

    public PessoaAdapter(Context context, List<Pessoa> lista) {
        this.context = context;
        this.lista = lista;
    }

    public PessoaAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Pessoa getItem(int position) {
        if (lista.size() > position){
            return lista.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_lista,parent,false);
        TextView txtNome = (TextView) convertView.findViewById(R.id.txtNome);
        TextView txtCpf = (TextView) convertView.findViewById(R.id.txtCpf);
        TextView txtRg = (TextView) convertView.findViewById(R.id.txtRg);
        TextView txtEmail = (TextView) convertView.findViewById(R.id.txtEmail);
        TextView txtSexo = (TextView) convertView.findViewById(R.id.txtSexo);

        Pessoa pessoa = lista.get(position);
        txtCpf.setText(pessoa.getCpf());
        txtRg.setText(pessoa.getRg());
        txtNome.setText(pessoa.getNome());
        txtSexo.setText(pessoa.getSexo());
        txtEmail.setText(pessoa.getEmail());

        return convertView;
    }

    public void addPessoa(Pessoa pessoa){
        lista.add(pessoa);
        notifyDataSetChanged();
    }

    public void removePessoa(Pessoa pessoa){
        lista.remove(pessoa);
        notifyDataSetChanged();
    }

    public void updatePessoa(Pessoa pessoa){
        int index = lista.indexOf(pessoa);
        lista.set(index,pessoa);
        notifyDataSetChanged();
    }

    public Boolean contains(Pessoa pes){
        return lista.contains(pes);
    }
}
