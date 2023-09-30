import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        // Adicione alguns livros à biblioteca
        Livro livro1 = new Livro("Java", "John Smith", "1234567890", "Publisher A", 2020);
        Livro livro2 = new Livro("Python", "Alice Johnson", "9876543210", "Publisher B", 2019);
        Livro livro3 = new Livro("C", "David Brown", "4567890123", "Publisher C", 2021);

        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);
        biblioteca.adicionarLivro(livro3);

        // Adicione alguns usuários à biblioteca
        Usuario usuario1 = new Usuario("Marcos", "Rua A, 123", "(82) 1234-5678");
        Usuario usuario2 = new Usuario("Maria", "Avenida B, 456", "(81) 9876-5432");
        Usuario usuario3 = new Usuario("Pedro", "Rua C, 789", "(11) 2222-3333");

        biblioteca.adicionarUsuario(usuario1);
        biblioteca.adicionarUsuario(usuario2);
        biblioteca.adicionarUsuario(usuario3);

        while (true) {
            System.out.println("Bem-vindo à Biblioteca!");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Listar livros disponíveis");
            System.out.println("2 - Empréstimo de livro");
            System.out.println("3 - Devolução de livro");
            System.out.println("4 - Sair");
            
            int escolha = -1;

            try {
                escolha = scanner.nextInt();
                // Processar a entrada do usuário
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }

            switch (escolha) {
                case 1:
                    listarLivrosDisponiveis(biblioteca);
                    break;
                case 2:
                    realizarEmprestimo(biblioteca, scanner);
                    break;
                case 3:
                    realizarDevolucao(biblioteca, scanner);
                    break;
                case 4:
                    System.out.println("Obrigado por usar a Biblioteca. Adeus!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        }
    }

    private static void listarLivrosDisponiveis(Biblioteca biblioteca) {
        System.out.println("Livros disponíveis:");
        for (Livro livro : biblioteca.listarLivrosDisponiveis()) {
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("ISBN: " + livro.getIsbn());
            System.out.println("---------------");
        }
    }

    private static void realizarEmprestimo(Biblioteca biblioteca, Scanner scanner) {
        scanner.nextLine(); // Consumir a nova linha pendente após nextInt()
        System.out.println("Digite o nome do usuário:");
        String nomeUsuario = scanner.nextLine();
        System.out.println("Digite o título do livro:");
        String tituloLivro = scanner.nextLine();

        Usuario usuario = biblioteca.encontrarUsuarioPorNome(nomeUsuario);
        Livro livro = biblioteca.encontrarLivroPorTitulo(tituloLivro);

        if (usuario != null && livro != null) {
            biblioteca.emprestarLivro(livro, usuario);
        } else {
            System.out.println("Usuário ou livro não encontrado. Verifique os dados e tente novamente.");
        }
    }

    private static void realizarDevolucao(Biblioteca biblioteca, Scanner scanner) {
        scanner.nextLine(); // Consumir a nova linha pendente após nextInt()
        System.out.println("Digite o título do livro:");
        String tituloLivro = scanner.nextLine();

        Livro livro = biblioteca.encontrarLivroPorTitulo(tituloLivro);

        if (livro != null) {
            // Encontre o empréstimo correspondente ao livro
            Emprestimo emprestimo = biblioteca.encontrarEmprestimoPorLivro(livro);

            if (emprestimo != null) {
                double multa = emprestimo.calcularMulta();
                if (multa > 0) {
                    System.out.println("Devolução registrada. Multa aplicada: $" + multa);
                } else {
                    System.out.println("Devolução registrada. Sem multa.");
                }

                // Registrar a devolução do livro
                biblioteca.registrarDevolucao(livro);

            } else {
                System.out.println("Não foi encontrado empréstimo correspondente para este livro.");
            }
        } else {
            System.out.println("Livro não encontrado. Verifique o título e tente novamente.");
        }
    }
}
