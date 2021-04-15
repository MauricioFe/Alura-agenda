package br.com.alura.agenda.asyncTask;

import android.os.AsyncTask;
import android.widget.TextView;

import br.com.alura.agenda.database.dao.TelefoneDAO;
import br.com.alura.agenda.model.Telefone;

public class BuscaPrimeiroTelefoneDoAlunoTask extends AsyncTask<Void, Void, Telefone> {

    private final TelefoneDAO dao;
    private final int alunoId;
    private final TextView campoTelefone;

    public BuscaPrimeiroTelefoneDoAlunoTask(TelefoneDAO dao, int alunoId, TextView telefone) {
        this.dao = dao;
        this.alunoId = alunoId;
        this.campoTelefone = telefone;
    }


    @Override
    protected Telefone doInBackground(Void... voids) {
        Telefone primeiroTelefone = dao.buscaPrimeiroTelefonDoAluno(alunoId);
        return primeiroTelefone;
    }

    @Override
    protected void onPostExecute(Telefone primeiroTelefone) {
        super.onPostExecute(primeiroTelefone);
        campoTelefone.setText(primeiroTelefone.getNumero());
    }
}
