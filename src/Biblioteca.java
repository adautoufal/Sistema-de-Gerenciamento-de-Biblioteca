import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Biblioteca {
    private List<Livro> livros;
    private List<Usuario> usuarios;
    private List<Emprestimo> emprestimos;

    public Biblioteca() {
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void removerLivro(Livro livro) {
        livros.remove(livro);
    }

    public List<Livro> listarLivrosDisponiveis() {
        List<Livro> disponiveis = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.isDisponivel()) {
                disponiveis.add(livro);
            }
        }
        return disponiveis;
    }

    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void emprestarLivro(Livro livro, Usuario usuario) {
        if (livro.isDisponivel()) {
            livro.setDisponivel(false);
            LocalDate dataEmprestimo = LocalDate.now(); // Data atual como data de empréstimo
            LocalDate dataDevolucaoPrevista = dataEmprestimo.plusDays(15); // Exemplo: 15 dias a partir da data de empréstimo
            Emprestimo emprestimo = new Emprestimo(dataEmprestimo, dataDevolucaoPrevista, livro, usuario);
            emprestimos.add(emprestimo);
            System.out.println("Empréstimo efetuado com sucesso.");
        } else {
            System.out.println("Este livro já foi emprestado.");
        }
    }

    public void registrarDevolucao(Livro livro) {
        Emprestimo emprestimo = encontrarEmprestimoPorLivro(livro);
        if (emprestimo != null) {
            livro.setDisponivel(true);
            emprestimos.remove(emprestimo);
        }
    }

    public Emprestimo encontrarEmprestimoPorLivro(Livro livro) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLivro() == livro) {
                return emprestimo;
            }
        }
        return null; // Retorna null se não encontrar empréstimo para o livro
    }

    public Usuario encontrarUsuarioPorNome(String nomeUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equalsIgnoreCase(nomeUsuario)) {
                return usuario;
            }
        }
        return null; // Retorna null se o usuário não for encontrado
    }

    public Livro encontrarLivroPorTitulo(String tituloLivro) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(tituloLivro)) {
                return livro;
            }
        }
        return null; // Retorna null se o livro não for encontrado
    }


    // Outros métodos relacionados à gestão da biblioteca
}
