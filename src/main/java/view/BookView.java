package view;

import service.AuthorService;

import java.util.Scanner;

public class BookView {
    private final Scanner scanner;
    private final AuthorService authorService;

    public BookView() {
        scanner = new Scanner(System.in);
        authorService = new AuthorService();
    }

    public String requestBookCode() {
        System.out.print("\nDigite o código do livro:");
        return scanner.nextLine();
    }

    public String requestTitle() {
        System.out.print("\nDigite o título do livro:");
        return scanner.nextLine();
    }

    public String requestGenre() {
        System.out.print("\nDigite o genero do livro:");
        return scanner.nextLine();
    }

    public String requestAuthorName() {
        System.out.print("\nDigite o nome do autor do livro:");
        return scanner.nextLine();
    }

    public Boolean requestBookStatus() {
        String choice;
        boolean isBorrowed = false;

        do {
            System.out.println("""

                    ---------- SELECIONAR STATUS ----------
                    \t1. Está emprestado
                    \t2. Não está emprestado
                    """);
            System.out.print("Digite o número corresponde a opção desejada:");

            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    isBorrowed = true;
                    break;
                case "2":
                    break;
                default:
                    System.out.println("\nOpção inválida! Tente novamente!");
                    break;
            }
        } while (!choice.equals("1") && !choice.equals("2"));

        return isBorrowed;
    }

    public String updateMenu(String bookCode) {
        String choice;
        String sql = "";

        do {
            System.out.println("""
                                    
                    O que deseja atualizar?
                    \t1. Título
                    \t2. Genero
                    \t3. Autor
                    \t4. Status
                    """);
            System.out.print("Digite o número corresponde a opção desejada:");

            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    String title = requestTitle();
                    sql = "UPDATE Livro SET titulo = '" + title + "' WHERE codigo = '" + bookCode + "'";
                    break;
                case "2":
                    String genre = requestGenre();
                    sql = "UPDATE Livro SET genero = '" + genre + "' WHERE codigo = '" + bookCode + "'";
                    break;
                case "3":
                    String author = requestAuthorName();
                    long authorId = authorService.getAuthorIdByName(author);
                    if (authorId != -1) {
                        sql = "UPDATE Livro SET id_autor = " + authorId + " WHERE codigo = '" + bookCode + "'";
                    } else System.out.println("\nPor favor, cadastre o autor antes de continuar!\n");
                    break;
                case "4":
                    boolean status = requestBookStatus();
                    sql = "UPDATE Livro SET esta_emprestado = " + status + " WHERE codigo = '" + bookCode + "'";
                    break;
                default:
                    System.out.println("\nOpção inválida! Tente novamente");
                    break;
            }
        } while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4"));

        return sql;
    }

    public String displayMenu() {
        System.out.println("""

                ---------- MENU - LIVRO ----------
                \t1. Registrar um novo livro
                \t2. Listar todos os livros
                \t3. Pesquisar um livro por ID
                \t4. Atualizar informações de um livro
                \t5. Excluir um livro""");
        System.out.print("Digite o número correspondente a opção desejada: ");

        return scanner.nextLine();
    }

}
