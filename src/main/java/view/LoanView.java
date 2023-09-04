package view;

import service.BookService;
import service.UserService;

import java.util.Scanner;

public class LoanView {
    private final Scanner scanner;
    private final BookService bookService;
    private final UserService userService;

    public LoanView(){
        scanner = new Scanner(System.in);
        bookService = new BookService();
        userService = new UserService();
    }

    public String requestId() {
        System.out.print("\nDigite o ID do empréstimo: ");
        return scanner.nextLine();
    }

    public String requestBookCode() {
        System.out.print("\nDigite o id do livro: ");
        return scanner.nextLine();
    }

    public String requestUserId() {
        System.out.print("\nDigite o id do usuário: ");
        return scanner.nextLine();
    }

    public String updateMenu(String id) {
        String choice;
        String sql = "";

        do {
            System.out.println("""
                                    
                    O que deseja atualizar?
                    \t1. Livro
                    \t2. Usuário
                    """);
            System.out.print("Digite o número corresponde a opção desejada:");

            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    String bookCode = requestBookCode();
                    if(bookService.bookCodeExists(bookCode)){
                        sql = "UPDATE Emprestimo SET codigo_livro = " + bookCode + " WHERE id = '" + id + "'";
                    } else System.out.println("\nNão há livro cadastrado com o código informado!\n");
                    break;
                case "2":
                    String userId = requestUserId();
                    if (userService.userIdExists(userId)) {
                        sql = "UPDATE Emprestimo SET id_usuario = " + userId + " WHERE id = '" + id + "'";
                    }
                    break;
                default:
                    System.out.println("\nOpção inválida! Tente novamente\n");
                    break;
            }
        } while (!choice.equals("1") && !choice.equals("2"));

        return sql;
    }

    public String displayMenu() {
        System.out.println("""

                ---------- MENU - EMPRESTIMO ----------
                \t1. Registrar um novo emprestimo
                \t2. Listar todos os emprestimos
                \t3. Pesquisar um emprestimo por ID
                \t4. Atualizar informações de um emprestimo
                \t5. Excluir um emprestimo""");
        System.out.print("Digite o número correspondente a opção desejada: ");

        return scanner.nextLine();
    }
}
