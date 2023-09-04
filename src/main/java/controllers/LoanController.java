package controllers;
import model.Loan;
import service.BookService;
import service.LoanService;
import service.UserService;
import util.DateGenerator;
import view.LoanView;

public class LoanController {
    LoanView loanView;
    LoanService loanService;
    BookService bookService;
    UserService userService;

    public LoanController() {
        loanView = new LoanView();
        loanService = new LoanService();
        bookService = new BookService();
        userService = new UserService();
    }

    public void registerNewLoan(){
        String userId = loanView.requestUserId();

        if(userService.userIdExists(userId)){
            String bookCode = loanView.requestBookCode();
            if(bookService.bookCodeExists(bookCode)){
                if (!bookService.isBookBorrowed(bookCode)){
                    loanService.createLoan(new Loan(DateGenerator.getCurrentDate(), DateGenerator.getNextMonthDate(), Long.valueOf(bookCode), Long.valueOf(userId)));
                    bookService.updateBook("UPDATE Livro SET esta_emprestado = true WHERE codigo = '" + bookCode + "'");
                } else System.out.println("\nO livro já está emprestado");
            } else System.out.println("\nNão há livro cadastrado com o ID informado");
        } else System.out.println("\nNão há usuário cadastrado com o ID informado");
    }

    public void readAllLoans(){
        loanService.readLoan("SELECT * FROM Emprestimo");
    }

    public void readSpecificLoan(){
        String loanId = loanView.requestId();
        if(loanService.loanIdExists(loanId)){
            loanService.readLoan("SELECT * FROM Emprestimo WHERE id = " + loanId);
        } else System.out.println("\nNão há emprestimo cadastrado com o id informado");
    }

    public void update(){
        String loanId =  loanView.requestId();
        if(loanService.loanIdExists(loanId)){
            loanService.updateLoan(loanView.updateMenu(loanId));
        } else System.out.println("\nNão há emprestimo cadastrado com o id informado");
    }

    public void delete(){
        String loanId =  loanView.requestId();
        if(loanService.loanIdExists(loanId)){
            loanService.deleteLoan(loanId);
        } else System.out.println("\nNão há emprestimo cadastrado com o id informado");
    }

    public void runMenu() {
        String choice;
        do {
            choice = loanView.displayMenu();
            switch (choice) {
                case "1":
                    registerNewLoan();
                    break;
                case "2":
                    readAllLoans();
                    break;
                case "3":
                    readSpecificLoan();
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
