package controllers;

import model.Author;
import service.*;
import view.AuthorView;

public class AuthorController {
    private final AuthorView authorView;
    private final AuthorService authorService;

    public AuthorController(){
        authorView = new AuthorView();
        authorService = new AuthorService();
    }

    public void registerNewAuthor(){
        Author author = authorView.requestName();
        authorService.createAuthor(author);
    }

    public void readAllAuthors(){
        authorService.readAuthor("SELECT * FROM Autor");
    }

    public void readSpecificAuthor(){
        String authorId =  authorView.requestAuthorId();
        if(authorService.authorIdExists(authorId)){
            authorService.readAuthor("SELECT * FROM Autor WHERE id = " + authorId);
        } else System.out.println("\nNão há autor cadastrado com o id informado");
    }

    public void update(){
        String authorId =  authorView.requestAuthorId();
        if(authorService.authorIdExists(authorId)){
            Author author = new Author(authorView.requestNewName());
            author.setId(Long.parseLong(authorId));
            authorService.updateAuthor(author);
        } else System.out.println("\nNão há autor cadastrado com o id informado");
    }

    public void delete(){
        String authorId =  authorView.requestAuthorId();
        if(authorService.authorIdExists(authorId)){
            authorService.deleteAuthor(authorId);
        } else System.out.println("\nNão há autor cadastrado com o id informado");
    }

    public void runMenu() {
        String choice;
        do {
            choice = authorView.displayMenu();
            switch (choice) {
                case "1":
                    registerNewAuthor();
                    break;
                case "2":
                    readAllAuthors();
                    break;
                case "3":
                    readSpecificAuthor();
                    break;
                case "4":
                    update();
                    break;
                case "5":
                    delete();
                    break;
                default:
                    System.out.println("\nOpção inválida. Tente novamente");
            }
        } while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") && !choice.equals("5"));
    }
}
