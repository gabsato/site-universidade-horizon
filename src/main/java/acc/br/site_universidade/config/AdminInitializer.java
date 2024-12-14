package acc.br.site_universidade.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import acc.br.site_universidade.model.Aluno;
import acc.br.site_universidade.repository.AlunoRepository;

@Component
public class AdminInitializer implements CommandLineRunner {

    @Autowired
    private AlunoRepository alunoRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void run(String... args) throws Exception {
        // Verificar se o admin já existe
        if (alunoRepository.findByNome("admin") == null) {
            // Criar um aluno admin com senha criptografada
            Aluno admin = new Aluno();
            admin.setNome("admin");
            admin.setSenha(passwordEncoder.encode("admin")); // Criptografar a senha
            admin.setDataNascimento(null);  // Preencha com valores necessários, se houver
            alunoRepository.save(admin);
        }
    }
}
