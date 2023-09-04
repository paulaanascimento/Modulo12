package view;

import model.Author;

import java.util.Scanner;

public class AuthorView {
    private final Scanner scanner;

    public AuthorView(){
        scanner = new Scanner(System.in);
    }

    public Author requestName(){
        System.out.print("\nDigite o nome do autor: ");
        return new Author(scanner.nextLine());
    }

    public String requestAuthorId(){
        System.out.print("\nDigite o ID do autor:");
        return scanner.nextLine();
    }

    public String requestNewName(){
        System.out.print("\nDigite o novo nome do autor:");
        return scanner.nextLine();
    }

    public String displayMenu() {
        System.out.println("""

                ---------- MENU - AUTOR ----------
                \t1. Registrar um novo autor
                \t2. Listar todos os autores
                \t3. Pesquisar um autor por ID
                \t4. Atualizar informações de um autor
                \t5. Excluir um autor""");
        System.out.print("Digite o número correspondente a opção desejada: ");

        return scanner.nextLine();
    }
}
