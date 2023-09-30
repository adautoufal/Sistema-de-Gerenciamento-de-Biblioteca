import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Usuario {
    private String nome;
    private String endereco;
    private String telefone;
    private List<Emprestimo> historicoEmprestimos;

    public Usuario(String nome, String endereco, String telefone) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.historicoEmprestimos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public List<Emprestimo> getHistoricoEmprestimos() {
        return new ArrayList<>(historicoEmprestimos);
    }

    public void renovarEmprestimo(Emprestimo emprestimo) {
        if (emprestimo != null && emprestimo.getUsuario() == this) {
            LocalDate hoje = LocalDate.now();
            if (hoje.isBefore(emprestimo.getDataDevolucaoPrevista())) {
                // Atualizar a data de devolução prevista (por exemplo, adicionar 30 dias)
                emprestimo.setDataDevolucaoPrevista(hoje.plusDays(30));
                System.out.println("Empréstimo renovado com sucesso.");
            } else {
                System.out.println("Não é possível renovar. O prazo de renovação expirou.");
            }
        } else {
            System.out.println("Empréstimo inválido para renovação.");
        }
    }

    public void registrarDevolucao(Livro livro) {
        Emprestimo emprestimo = encontrarEmprestimoPorLivro(livro);
        if (emprestimo != null) {
            double multa = emprestimo.calcularMulta();
            if (multa > 0) {
                System.out.println("Devolução registrada. Multa aplicada: $" + multa);
            } else {
                System.out.println("Devolução registrada. Sem multa.");
            }
            historicoEmprestimos.remove(emprestimo);
        } else {
            System.out.println("Não foi encontrado empréstimo correspondente para este livro.");
        }
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        historicoEmprestimos.add(emprestimo);
    }

    public Emprestimo encontrarEmprestimoPorLivro(Livro livro) {
        for (Emprestimo emprestimo : historicoEmprestimos) {
            if (emprestimo.getLivro() == livro) {
                return emprestimo;
            }
        }
        return null; // Retorna null se não encontrar empréstimo para o livro
    }

    // Outros métodos relacionados à gestão do usuário
}
