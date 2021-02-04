package pl.dmcs.client;

import pl.dmcs.enums.RequestType;
import pl.dmcs.enums.ResponseStatus;
import pl.dmcs.model.Book;
import pl.dmcs.model.User;
import pl.dmcs.request.BookRequest;
import pl.dmcs.request.Request;
import pl.dmcs.request.UserRequest;
import pl.dmcs.response.BookResponse;
import pl.dmcs.response.Response;
import pl.dmcs.response.UserResponse;

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {

    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    final Scanner scanner = new Scanner(System.in);

    public void start(String host, int port) throws IOException {

//          SSL
        SSLSocketFactory sslSocketFactory =
                (SSLSocketFactory) SSLSocketFactory.getDefault();

        clientSocket = sslSocketFactory.createSocket(host, port);
//        clientSocket = new Socket(host, port);
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());

        try {
            while (true) {
                Request request = getRequest(scanner);

                out.writeObject(request);

                if (request.isQuit()) {
                    stop();
                    break;
                }

                Response response = (Response) in.readObject();

                if ( RequestType.REMIND_PASSWORD.equals(request.getRequestType())){
                    UserResponse userResponse = (UserResponse) response;
                    showRemindedPassoword(userResponse);
                }
                if( RequestType.REGISTRATION.equals(request.getRequestType())){
                    UserResponse userResponse = (UserResponse) response;
                    showRegisteredUser(userResponse);
                }
                if( RequestType.LOGIN.equals(request.getRequestType())){
                    if(ResponseStatus.OK.equals(response.getStatus())){
                        while (true){
                            request = getSearchType(scanner);
                            if (request.isQuit()) {
                                break;
                            }
                            out.writeObject(request);
                            BookResponse bookResponse = (BookResponse) in.readObject();
                            showFoundBooks(bookResponse);
                        }
                    }
                    else{
                        System.out.println(response.getMessage());
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showRegisteredUser(UserResponse response) {
        if(ResponseStatus.OK.equals(response.getStatus())){
            System.out.println("Dodano użytkownika " + response.getUser().getLogin() + " z hasłem " + response.getUser().getPassword());
        }
        else {
            System.out.println(response.getMessage());
        }
    }

    private void showRemindedPassoword(UserResponse response) {
        if(ResponseStatus.OK.equals(response.getStatus())){
            System.out.println("Twoje hasło to "+ response.getUser().getPassword());
        }
        else{
            System.out.println(response.getMessage());
        }
    }

    private void showFoundBooks(BookResponse response) {
        List<Book> books = response.getBooks();
        if(books.size() == 0){
            System.out.println("Brak książek z taką frazą.");
        }
        else {
            for (Book book : books) {
                System.out.println(book.getAuthor());
                System.out.println(book.getTitle());
                System.out.println(book.getCategory());
                System.out.println();
            }
        }
    }

    private Request getSearchType(Scanner scanner) {
        System.out.println("---MENU---");
        System.out.println("Po czym wyszukiwać?");
        System.out.println("[1] Autor");
        System.out.println("[2] Tytuł");
        System.out.println("[3] Gatunek");
        System.out.println("[Inne] Wyloguj");
        switch (scanner.nextLine()){
            case "1":
                return handleBookRequest(scanner, BookRequest.SearchBy.author);
            case "2":
                return handleBookRequest(scanner, BookRequest.SearchBy.title);
            case "3":
                return handleBookRequest(scanner, BookRequest.SearchBy.category);
            default:
                return new Request(RequestType.QUIT);
        }
    }

    private BookRequest handleBookRequest(Scanner scanner, BookRequest.SearchBy searchBy) {
        Book book = new Book();
        System.out.println("Podaj wyszukiwaną frazę ");
        book.setAuthor(scanner.nextLine());
        return new BookRequest(RequestType.GET_BOOKS, searchBy,book.getAuthor());
    }

    public Request getRequest(Scanner scanner) {
        System.out.println("---MENU---");
        System.out.println("[1] Zarejestruj się");
        System.out.println("[2] Zaloguj się");
        System.out.println("[3] Przypomnij hasło");
        System.out.println("[Inne] Wyjdź");

        switch (scanner.nextLine()) {
            case "1":
                return handleUser(scanner, RequestType.REGISTRATION);
            case "2":
                return handleUser(scanner, RequestType.LOGIN);
            case "3":
                return handleUser(scanner, RequestType.REMIND_PASSWORD);
            default:
                return new Request(RequestType.QUIT);
        }
    }

    public UserRequest handleUser(Scanner scanner, RequestType requestType) {
        User user = new User();

        System.out.println("Podaj login:");
        user.setLogin(scanner.nextLine());

        if (!requestType.equals(RequestType.REMIND_PASSWORD)) {
            System.out.println("Podaj hasło:");
            user.setPassword(scanner.nextLine());
        }

        return new UserRequest(requestType, user);
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
