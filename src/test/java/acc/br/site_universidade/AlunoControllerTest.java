package acc.br.site_universidade;

import acc.br.site_universidade.model.Aluno;
import acc.br.site_universidade.repository.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AlunoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private AlunoRepository alunoRepository;

    @Test
    public void testRegistrarAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome("Fulano Da Silva");
        aluno.setDataNascimento(LocalDate.of(2002, 5, 20));
        aluno.setCep("101001000");
        aluno.setCidade("São Paulo");
        aluno.setCurso("Tecnologia");
        aluno.setSenha("senha123");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Aluno> request = new HttpEntity<>(aluno, headers);

        String url = "http://localhost:" + port + "/registro";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);

        Aluno alunoSalvo = alunoRepository.findByNome("Fulano Da Silva");
        assertThat(alunoSalvo).isNotNull();
        assertThat(alunoSalvo.getNome()).isEqualTo("Fulano Da Silva");
    }
}
