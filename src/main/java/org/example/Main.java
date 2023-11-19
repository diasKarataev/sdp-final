package org.example;


import org.example.Adapter.EmailService;
import org.example.Adapter.EmailServiceImpl;
import org.example.Adapter.JoinMembershipNotificationEmailAdapter;
import org.example.Adapter.RegistrationNotificationEmailAdapter;
import org.example.Decorator.PersonalMembership;
import org.example.Decorator.PoolMembership;
import org.example.Factory.*;
import org.example.Observer.MembershipEmailNotification;
import org.example.Singleton.*;
import org.example.Strategy.CreditCardPaymentStrategy;
import org.example.Strategy.CashPaymentStrategy;
import org.example.Strategy.ShoppingCart;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmailServiceImpl basicEmailService = new EmailServiceImpl();

        MembershipEmailNotification emailNotification = new MembershipEmailNotification();

        ShoppingCart shoppingCart = new ShoppingCart();

        UserManagement userManagement = UserManagement.getInstance();
        MembershipManagement membershipManagement = MembershipManagement.getInstance();



        User currentUser = null;
        Scanner scanner = new Scanner(System.in);

        User admin = new User("admin", "admin@admin.admin", "admin");
        admin.setRole(ERole.ADMIN);
        userManagement.addUser(admin);

        while (true) {
            System.out.print("Введите команду (или 'exit' для выхода): ");
            String command = scanner.nextLine();

            switch (command) {
                case "!" -> {
                    System.out.println("! - help");
                    System.out.println("/reg - регистрация");
                    System.out.println("/login - вход");
                    System.out.println("/buy-membership - покупка абонемента");
                    System.out.println("/upgrade-membership - добавить опций в абонемент");
                    System.out.println("/cart - корзина");
                    System.out.println("/checkout - оплата");
                    System.out.println("/admin/users - показать пользователей");
                    System.out.println("/admin/members - показать членов клуба");
                    System.out.println("/admin/notify - отправить рассылку членам клуба");
                }
                case "/reg" -> {
                    System.out.println("** Регистрация **\n");
                    System.out.println("Введите имя:");
                    String regName = scanner.next();
                    System.out.println("Введите почту:");
                    String regEmail = scanner.next();
                    if (regEmail.contains("@") && userManagement.getUserNameByEmail(regEmail) == null) {
                        System.out.println("Введите пароль:");
                        String regPassword = scanner.next();
                        User registeredUser = new User(regName, regEmail, regPassword);
                        registeredUser.setRole(ERole.USER);
                        userManagement.addUser(registeredUser);
                        // Отправить сообщение об успешной регистрации пользователю
                        EmailService emailService = new RegistrationNotificationEmailAdapter(basicEmailService);
                        emailService.sendEmail(regEmail);
                    } else {
                        System.out.println("Введите другую почту");
                    }
                }
                case "/login" -> {
                    System.out.println("Введите почту:");
                    String loginEmail = scanner.next();
                    System.out.println("Введите пароль:");
                    String loginPassword = scanner.next();
                    if (userManagement.getUserPasswordByEmail(loginEmail).equals(loginPassword)) {
                        User loginUser = new User(userManagement.getUserNameByEmail(loginEmail), loginEmail, loginPassword);
                        loginUser.setRole(userManagement.getRolePasswordByEmail(loginEmail));
                        currentUser = loginUser;
                        System.out.println("Здравствуйте " + currentUser.getName());
                    } else {
                        System.out.println("Пользователь не найде/неправильный пароль, повторите попытку");
                    }
                }
                case "/buy-membership" -> {
                    assert currentUser != null;
                    System.out.println("Выберите абонемент:" +
                            "\n 1. Стандартный абонемент - 20000" +
                            "\n 2. Абонемент с бассейном - 30000" +
                            "\n 3. Абонемент с персональным тренером - 60000"
                    );
                    Membership membership = null;
                    int chooseMembership = scanner.nextInt();
                    switch (chooseMembership) {
                        case 1 -> {
                            membership = new DefaultMembershipFactory().createMembership(shoppingCart);
                        }
                        case 2 -> {
                            membership = new PoolMembershipFactory().createMembership(shoppingCart);
                        }
                        case 3 -> {
                            membership = new PersonalMembershipFactory().createMembership(shoppingCart);
                        }
                        default -> {

                        }
                    }
                    shoppingCart.addToCart(membership);
                }
                case "/upgrade-membership" -> {
                    assert currentUser != null;
                    Membership currentMembership = shoppingCart.getProductList().get(0);
                    System.out.println("Улучшение абонемента:" +
                            "\n 1. Добавить пропуск в бассейн - 10000" +
                            "\n 2. Добавить персонального треннера 40000 тг"
                    );
                    int c = scanner.nextInt();
                    switch (c) {
                        case 1 -> currentMembership = new PoolMembership(currentMembership);
                        case 2 -> currentMembership = new PersonalMembership(currentMembership);
                    }
                    shoppingCart.updateProduct(currentMembership);
                }
                case "/cart" -> {
                    assert currentUser != null;
                    for (Membership m : shoppingCart.getProductList()) {
                        System.out.println(m.getDescription());
                    }
                    System.out.println("\nСтоимость:" + shoppingCart.getAmount());
                    System.out.println("/checkout для оплаты");
                }
                case "/checkout" -> {
                    assert currentUser != null;
                    System.out.println("Выберите способ оплаты \n 1. Кредитная карта \n 2. Наличные");
                    int choose = scanner.nextInt();
                    switch (choose) {
                        case 1 -> shoppingCart.setPaymentStrategy(new CreditCardPaymentStrategy());
                        case 2 -> shoppingCart.setPaymentStrategy(new CashPaymentStrategy());
                        default -> System.out.println("Неверная команда");
                    }
                    shoppingCart.checkout(shoppingCart.getAmount());
                    GymMember newMember = new GymMember();
                    newMember.setMembership(shoppingCart.getProductList().get(0));
                    newMember.setUser(currentUser);
                    membershipManagement.addMember(newMember);
                    emailNotification.addMailingSubscriber(currentUser);

                    // Отправка сообщения об успешной оплате
                    EmailService service = new JoinMembershipNotificationEmailAdapter(basicEmailService);
                    service.sendEmail(currentUser.getEmail());
                }
                case "/admin" -> {
                    assert currentUser != null;
                    assert currentUser.getRole().equals(ERole.ADMIN);
                    String cp = scanner.next();
                    switch (cp) {
                        case "/members" -> {
                            for (GymMember member : membershipManagement.getMembers()) {
                                System.out.println(member.getUser().toString());
                            }
                        }
                        case "/notify" -> {
                            System.out.println("Введите тему рассылки");
                            String topic = scanner.next();
                            System.out.println("Введите сообщение");
                            String message = scanner.next();
                            emailNotification.notifyMembers(topic, message);
                        }
                        case "/users" -> {
                            for (User user : userManagement.getUsers()) {
                                System.out.println(user.toString());
                            }
                        }
                        default -> {
                        }
                    }
                }
                case "/exit" -> {
                    System.out.println("Выход из программы.");
                    scanner.close();
                }
            }
        }
    }
}
