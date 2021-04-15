package br.com.alura.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import br.com.alura.agenda.model.Telefone;

@Dao
public interface TelefoneDAO {

    @Query("SELECT t.* FROM telefone t " +
            "inner join Aluno a " +
            "on t.aluno_id = a.id " +
            "where t.aluno_id = :alunoId limit 1")
    Telefone buscaPrimeiroTelefonDoAluno(int alunoId);
}
