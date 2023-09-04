import controllers.AuthorController;
import controllers.BookController;
import controllers.LoanController;
import controllers.UserController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthorController authorController = new AuthorController();
        BookController bookController = new BookController();
        UserController userController = new UserController();
        LoanController loanController = new LoanController();

        while (true) {
            System.out.print("""
                    
                    --------------- MENU PRINCIPAL ---------------
                    \t1. MENU - AUTOR
                    \t2. MENU - LIVRO
                    \t3. MENU - USUARIO
                    \t4. MENU - EMPRESTIMO
                    \t5. ENCERRAR APLICAÇÃO
                    """);
            System.out.print("Digite o número correspondente a opção desejada: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    authorController.runMenu();
                    break;
                case "2":
                    bookController.runMenu();
                    break;
                case "3":
                    userController.runMenu();
                    break;
                case "4":
                    loanController.runMenu();
                    break;
                case "5":
                    System.out.println("\nEncerrando aplicação...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nOpção inválida! Tente novamente!\n");
                    break;
            }
        }
    }
}
