package acc.br.site_universidade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import acc.br.site_universidade.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Aluno findByNomeAndSenha(String nome, String senha);
}
