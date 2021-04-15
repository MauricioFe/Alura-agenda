package br.com.alura.agenda.asyncTask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agenda.database.dao.AlunoDAO;
import br.com.alura.agenda.database.dao.TelefoneDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;
import br.com.alura.agenda.model.TipoTelefone;

public class EditaAlunoTask extends AsyncTask<Void,Void,Void> {
    private final AlunoDAO alunoDAO;
    private final Telefone telefoneFixo;
    private final Telefone telefoneCelular;
    private final Aluno aluno;
    private final TelefoneDAO telefoneDAO;
    private final List<Telefone> telefonesDoAluno;
    private final AlunoEditadoListener listener;

    public EditaAlunoTask(AlunoDAO alunoDAO, Telefone telefoneFixo, Telefone telefoneCelular, Aluno aluno, TelefoneDAO telefoneDAO,
                          List<Telefone> telefonesDoAluno, AlunoEditadoListener listener) {
        this.alunoDAO = alunoDAO;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.aluno = aluno;
        this.telefoneDAO = telefoneDAO;
        this.telefonesDoAluno = telefonesDoAluno;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        alunoDAO.edita(aluno);
        vinculaAlunoComTelefone(aluno.getId(), telefoneFixo, telefoneCelular);
        atualizaIdsDosTelefones(telefoneFixo, telefoneCelular);
        telefoneDAO.atualiza(telefoneFixo, telefoneCelular);
        return null;
    }

    private void atualizaIdsDosTelefones(Telefone telefoneFixo, Telefone telefoneCelular) {
        for (Telefone telefone : telefonesDoAluno) {
            if (telefone.getTipo() == TipoTelefone.FIXO) {
                telefoneFixo.setId(telefone.getId());
            } else {
                telefoneCelular.setId(telefone.getId());
            }
        }
    }

    private void vinculaAlunoComTelefone(int alunoId, Telefone... telefones) {
        for (Telefone telefone : telefones) {
            telefone.setAlunoId(alunoId);
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.quandoEditar();

    }

    public interface AlunoEditadoListener{
        void quandoEditar();
    }

}
