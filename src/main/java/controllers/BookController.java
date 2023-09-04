package controllers;

import model.Book;
import service.*;
import view.BookView;

public class BookController {
    private final BookView bookView;
    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(){
        bookView = new BookView();
        bookService = new BookService();
        authorService = new AuthorService();
    }

    public void registerNewBook(){
        String author = bookView.requestAuthorName();
        long authorId = authorService.getAuthorIdByName(author);

        if(authorId != -1){
            Book book = new Book(bookView.requestTitle(), bookView.requestGenre(), authorId);
            bookService.createBook(book);
        } else System.out.println("\nPor favor, cadastre o autor antes de continuar!");
    }

    public void readAllBook(){
        bookService.readBook("SELECT * FROM Livro");
    }

    public void readSpecificBook(){
        String bookCode =  bookView.requestBookCode();
        if(bookService.bookCodeExists(bookCode)){
            bookService.readBook("SELECT * FROM Livro WHERE codigo = " + bookCode);
        } else System.out.println("\nNão há livro cadastrado com o codigo informado");
    }

    public void update(){
        String bookCode =  bookView.requestBookCode();
        if(bookService.bookCodeExists(bookCode)){
            bookService.updateBook(bookView.updateMenu(bookCode));
        } else System.out.println("\nNão há autor cadastrado com o id informado");
    }

    public void delete(){
        String bookCode =  bookView.requestBookCode();
        if(bookService.bookCodeExists(bookCode)){
            bookService.deleteBook(bookCode);
        } else System.out.println("\nNão há livro cadastrado com o codigo informado");
    }

    public void runMenu() {
        String choice;
        do {
            choice = bookView.displayMenu();
            switch (choice) {
                case "1":
                    registerNewBook();
                    break;
                case "2":
                    readAllBook();
                    break;
                case "3":
                    readSpecificBook();
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
