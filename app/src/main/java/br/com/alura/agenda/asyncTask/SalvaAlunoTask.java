package br.com.alura.agenda.asyncTask;

import br.com.alura.agenda.database.dao.AlunoDAO;
import br.com.alura.agenda.database.dao.TelefoneDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;

public class SalvaAlunoTask extends BaseAlunoComTelefoneTask {
    private final AlunoDAO alunoDAO;
    private final Telefone telefoneFixo;
    private final Telefone telefoneCelular;
    private final Aluno aluno;
    private final TelefoneDAO telefoneDAO;

    public SalvaAlunoTask(AlunoDAO alunoDAO, Telefone telefoneFixo, Telefone telefoneCelular, Aluno aluno, TelefoneDAO telefoneDAO, FinalizadaListener listener) {
        super(listener);
        this.alunoDAO = alunoDAO;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.aluno = aluno;
        this.telefoneDAO = telefoneDAO;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        int alunoId = alunoDAO.salva(aluno).intValue();
        vinculaAlunoComTelefone(alunoId, telefoneFixo, telefoneCelular);
        telefoneDAO.salva(telefoneFixo, telefoneCelular);
        return null;
    }
}
