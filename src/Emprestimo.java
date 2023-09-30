import java.time.LocalDate;

public class Emprestimo {
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private Livro livro;
    private Usuario usuario;

    public Emprestimo(LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista, Livro livro, Usuario usuario) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.livro = livro;
        this.usuario = usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public double calcularMulta() {
        LocalDate hoje = LocalDate.now();
        if (hoje.isAfter(dataDevolucaoPrevista)) {
            // Lógica para calcular a multa (por exemplo, $1 por dia de atraso)
            long diasAtraso = hoje.until(dataDevolucaoPrevista).getDays();
            double multa = diasAtraso * 0.2; // $1 por dia
            return multa;
        }
        return 0; // Sem multa se não houver atraso
    }

    // Outros métodos relacionados à gestão dos empréstimos
}
