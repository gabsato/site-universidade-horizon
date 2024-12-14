package acc.br.site_universidade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import acc.br.site_universidade.model.Aluno;
import acc.br.site_universidade.repository.AlunoRepository;

@Controller
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    // Rota para a página de login
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "login"; // Renderiza login.html
    }

    // Processar o login
    @PostMapping("/login")
    public String processarLogin(@ModelAttribute("aluno") Aluno aluno, RedirectAttributes redirectAttributes) {
        // Buscar o aluno pelo nome e senha
        Aluno alunoEncontrado = alunoRepository.findByNomeAndSenha(aluno.getNome(), aluno.getSenha());

        if (alunoEncontrado != null) {
            // Redireciona para a página de boas-vindas caso as credenciais estejam corretas
            redirectAttributes.addAttribute("id", alunoEncontrado.getId());
            return "redirect:/bemVindo/{id}";
        }

        // Mensagem de erro se o login falhar
        redirectAttributes.addFlashAttribute("mensagemErro", "Nome ou senha inválidos!");
        return "redirect:/login";  // Redireciona para a página de login em caso de falha
    }

    // Página de boas-vindas após o login
    @GetMapping("/bemVindo/{id}")
    public String bemVindo(@PathVariable("id") Long id, Model model) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
        model.addAttribute("aluno", aluno); // Adiciona o aluno ao modelo
        return "bemVindo"; // Renderiza a página de boas-vindas
    }


    // Página de registro
    @GetMapping("/registrar")
    public String registrarPage(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "registroAluno"; // Renderiza registroAluno.html
    }

    // Página para editar as informações do aluno
    @GetMapping("/editar/{id}")
    public String editarAluno(@PathVariable("id") Long id, Model model) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
        model.addAttribute("aluno", aluno);
        return "editarAluno"; // Renderiza a página editarAluno.html
    }

    @PostMapping("/editar/{id}")
    public String atualizarAluno(@PathVariable("id") Long id, @ModelAttribute("aluno") Aluno aluno, RedirectAttributes redirectAttributes) {
        // Buscar o aluno no banco de dados
        Aluno alunoExistente = alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        // Manter a data de nascimento e a senha original, caso não tenha sido alterada
        if (aluno.getSenha() == null || aluno.getSenha().isEmpty()) {
            aluno.setSenha(alunoExistente.getSenha());  // Mantém a senha anterior
        }
        aluno.setDataNascimento(alunoExistente.getDataNascimento());  // Mantém a data de nascimento

        aluno.setId(id); // Atualiza o ID para persistir as mudanças
        alunoRepository.save(aluno); // Salva os dados no banco

        redirectAttributes.addAttribute("id", aluno.getId());
        return "redirect:/bemVindo/{id}"; // Redireciona para a página de boas-vindas com o ID do aluno
    }


    // Processar registro
    @PostMapping("/registrar")
    public String processarRegistro(@ModelAttribute("aluno") Aluno aluno, RedirectAttributes redirectAttributes) {
        alunoRepository.save(aluno);
        redirectAttributes.addAttribute("id", aluno.getId());
        return "redirect:/bemVindo/{id}";
    }

    // Método para deletar o aluno
    @GetMapping("/deletar/{id}")
    public String deletarAluno(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        alunoRepository.delete(aluno); // Exclui o aluno do banco de dados

        redirectAttributes.addFlashAttribute("mensagem", "Registro cancelado com sucesso.");
        return "redirect:/login"; // Redireciona para a página de login após a exclusão
    }

}
