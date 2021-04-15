package br.com.alura.agenda.asyncTask;

import android.os.AsyncTask;

import br.com.alura.agenda.database.dao.AlunoDAO;
import br.com.alura.agenda.database.dao.TelefoneDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;

public class SalvaAlunoTask extends AsyncTask<Void, Void,Void> {
    private final AlunoDAO alunoDAO;
    private final Telefone telefoneFixo;
    private final Telefone telefoneCelular;
    private final Aluno aluno;
    private final TelefoneDAO telefoneDAO;
    private final QuandoAlunoSalvoListener listener;

    public SalvaAlunoTask(AlunoDAO alunoDAO, Telefone telefoneFixo, Telefone telefoneCelular, Aluno aluno, TelefoneDAO telefoneDAO, QuandoAlunoSalvoListener listener) {
        this.alunoDAO = alunoDAO;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.aluno = aluno;
        this.telefoneDAO = telefoneDAO;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        int alunoId = alunoDAO.salva(aluno).intValue();
        vinculaAlunoComTelefone(alunoId, telefoneFixo, telefoneCelular);
        telefoneDAO.salva(telefoneFixo, telefoneCelular);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.quandoSalvo();

    }

    private void vinculaAlunoComTelefone(int alunoId, Telefone... telefones) {
        for (Telefone telefone : telefones) {
            telefone.setAlunoId(alunoId);
        }
    }

    public interface QuandoAlunoSalvoListener{
        void quandoSalvo();
    }
}
