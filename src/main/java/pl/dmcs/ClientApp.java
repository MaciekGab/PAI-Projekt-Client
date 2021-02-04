package pl.dmcs;

import pl.dmcs.client.Client;
import pl.dmcs.enums.RequestType;
import pl.dmcs.model.User;
import pl.dmcs.request.BookRequest;
import pl.dmcs.request.Request;
import pl.dmcs.request.UserRequest;
import pl.dmcs.response.BookResponse;
import pl.dmcs.response.Response;
import pl.dmcs.response.UserResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class ClientApp {

    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.trustStore","C:\\Program Files\\Java\\jre1.8.0_271\\bin\\examplestore");
        System.setProperty("javax.net.ssl.trustStorePassword","password");
        try {
            new Client().start("127.0.0.1", 1);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }


    }


}
