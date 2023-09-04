package view;

import java.util.Scanner;

public class UserView {
    private final Scanner scanner;

    public UserView(){
        scanner = new Scanner(System.in);
    }

    public String requestId() {
        System.out.print("\nDigite o ID do usuário:");
        return scanner.nextLine();
    }

    public String requestName() {
        System.out.print("\nDigite o nome do usuário:");
        return scanner.nextLine();
    }

    public String requestPhone() {
        System.out.print("\nDigite o telefone do usuário:");
        return scanner.nextLine();
    }

    public String requestEmail() {
        System.out.print("\nDigite o email do usuário:");
        return scanner.nextLine();
    }

    public String updateMenu(String id) {
        String choice;
        String sql = "";

        do {
            System.out.println("""
                                    
                    O que deseja atualizar?
                    \t1. Nome
                    \t2. Telefone
                    \t3. Email
                    """);
            System.out.print("Digite o número corresponde a opção desejada:");

            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    String name = requestName();
                    sql = "UPDATE Usuario SET nome = '" + name + "' WHERE id = '" + id + "'";
                    break;
                case "2":
                    String phone = requestPhone();
                    sql = "UPDATE Usuario SET telefone = '" + phone + "' WHERE id = '" + id + "'";
                    break;
                case "3":
                    String email = requestEmail();
                    sql = "UPDATE Usuario SET email = '" + email + "' WHERE id = '" + id + "'";
                    break;
                default:
                    System.out.println("\nOpção inválida! Tente novamente\n");
            }
        } while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3"));

        return sql;
    }

    public String displayMenu() {
        System.out.println("""

                ---------- MENU - USUÁRIO ----------
                \t1. Registrar um novo usuário
                \t2. Listar todos os usuários
                \t3. Pesquisar um usuário por ID
                \t4. Atualizar informações de um usuário
                \t5. Excluir um usuário""");
        System.out.print("Digite o número correspondente a opção desejada: ");

        return scanner.nextLine();
    }
}
