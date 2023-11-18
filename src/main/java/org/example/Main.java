package org.example;


import org.example.Adapter.EmailService;
import org.example.Adapter.EmailServiceImpl;
import org.example.Adapter.RegistrationNotificationEmailAdapter;
import org.example.Observer.ProcessNotification;
import org.example.Strategy.ShoppingCart;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmailServiceImpl basicEmailService = new EmailServiceImpl();
        ShoppingCart shoppingCart = new ShoppingCart();


        Store store = Store.getInstance();
        User currentUser = null;
        Scanner scanner = new Scanner(System.in);

        User admin = new User("admin", "admin", "admin");
        admin.setRole(ERole.ADMIN);
        store.addUser(admin);

        while (true) {
            System.out.print("Введите команду (или 'exit' для выхода): ");
            String command = scanner.nextLine();

            if ("!".equalsIgnoreCase(command)) {
                System.out.println("! - help");
                System.out.println("/reg - for sign up");
                System.out.println("/login - for sign in");
            }

            if ("/reg".equalsIgnoreCase(command)) {
                System.out.println("Register");
                System.out.println("Введите имя:");
                String name = scanner.next();
                System.out.println("Введите почту:");
                String email = scanner.next();
                System.out.println("Введите пароль:");
                String password = scanner.next();
                User registeredUser = new User(name,email, password);
                registeredUser.setRole(ERole.USER);
                store.addUser(registeredUser);

                // Отправить сообщение об успешной регистрации пользователю
                EmailService emailService = new RegistrationNotificationEmailAdapter(basicEmailService);
                emailService.sendEmail(email);
            }
            if ("/login".equalsIgnoreCase(command)) {
                System.out.println("Введите почту:");
                String email = scanner.next();
                System.out.println("Введите пароль:");
                String password = scanner.next();
                if(store.getUserPasswordByEmail(email).equals(password)){
                    User loginUser = new User(store.getUserNameByEmail(email), email, password);
                    loginUser.setRole(store.getRolePasswordByEmail(email));
                    currentUser = loginUser;
                    System.out.println("Здравствуйте " + currentUser.getName());
                } else {
                    System.out.println("Неправильный пароль, повторите попытку");
                }
            }

            if ("/addproduct".equalsIgnoreCase(command)) {
                assert currentUser != null;
                if(currentUser.getRole() == ERole.ADMIN){
                    System.out.println("Введите название товара:");
                    String title = scanner.next();
                    System.out.println("Введите описание товара:");
                    String description = scanner.next();
                    System.out.println("Введите цену товара в тенге:");
                    int price = scanner.nextInt();
                    Product product = new Product(title,description,price);
                    store.addProduct(product);
                } else {
                    System.out.println("У вас нет прав");
                }
            }
            if("/products".equalsIgnoreCase(command)){
                for(Product product : store.getProducts()){
                    System.out.println(product.toString());
                }
            }

            if("/addorder".equalsIgnoreCase(command)){
                assert currentUser != null;
                System.out.println("Введите id товара");
                int id = scanner.nextInt();
                System.out.println("Введите адрес");
                String address = scanner.next();
                Order order = new Order(currentUser, store.getProductById(id),address);
                store.addOrder(order);
            }

            if("/orders".equalsIgnoreCase(command)) {
                assert currentUser != null;
                if (currentUser.getRole() == ERole.ADMIN) {
                    for (Order order : store.getOrders()) {
                        System.out.println(order.getOrderedProduct().toString());
                        System.out.println(order.getOrderedUser().toString());
                        System.out.println(order.getAddress());
                    }
                }
            }

            if("/processorder".equalsIgnoreCase(command)) {
                assert currentUser != null;
                if (currentUser.getRole().equals(ERole.ADMIN)) {
                    ProcessNotification notification = new ProcessNotification();
                   while (true){
                       System.out.println("Введите id заказа либо введите команду /send");
                       String id = scanner.next();
                       if(id.equalsIgnoreCase("/send")){
                           notification.notifyMembers();
                           break;
                       }
                       // TODO добавить проверку на совпадение int
                       else {
                        notification.addObserver(store.getOrderById(Integer.parseInt(id)).getOrderedUser());
                       }
                   }
                }
            }

            if("/myorders".equalsIgnoreCase(command)) {
                System.out.println(store.getOrdersByUser(currentUser).toString());
            }

            if ("/exit".equalsIgnoreCase(command)) {
                assert currentUser != null;
                System.out.println(currentUser.getEmail());
                System.out.println("Выход из программы.");
                break;
            }
        }
        scanner.close();
    }
}
