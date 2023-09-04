package controllers;

import model.User;
import service.UserService;
import view.UserView;

public class UserController {
    UserView userView;
    UserService userService;

    public UserController() {
        this.userView = new UserView();
        this.userService = new UserService();
    }

    public void registerNewUser(){
        userService.createUser(new User(userView.requestName(), userView.requestPhone(), userView.requestEmail()));
    }

    public void readAllUsers(){
        userService.readUser("SELECT * FROM Usuario");
    }

    public void readSpecificUser(){
        String userId = userView.requestId();
        if(userService.userIdExists(userId)){
            userService.readUser("SELECT * FROM Usuario WHERE id = " + userId);
        } else System.out.println("\nNão há usuário cadastrado com o id informado");
    }

    public void update(){
        String userId =  userView.requestId();
        if(userService.userIdExists(userId)){
            userService.updateUser(userView.updateMenu(userId));
        } else System.out.println("\nNão há autor cadastrado com o id informado");
    }

    public void delete(){
        String userId =  userView.requestId();
        if(userService.userIdExists(userId)){
            userService.deleteUser(userId);
        } else System.out.println("\nNão há usuario cadastrado com o id informado");
    }

    public void runMenu() {
        String choice;
        do {
            choice = userView.displayMenu();
            switch (choice) {
                case "1":
                    registerNewUser();
                    break;
                case "2":
                    readAllUsers();
                    break;
                case "3":
                    readSpecificUser();
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
