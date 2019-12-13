/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

/**
 *
 * @author Ki
 */
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Sserver {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // don't need to specify a hostname, it will be the current machine
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("ServerSocket awaiting connections...");
        Socket socket = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
        System.out.println("Connection from " + socket + "!");

        // get the input stream from the connected socket
        InputStream inputStream = socket.getInputStream();
        // create a DataInputStream so we can read data from it.
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream obo= new ObjectOutputStream(outputStream);
        // read the list of messages from the socket
//        List<Message> listOfMessages = (List<Message>) objectInputStream.readObject();
//        System.out.println("Received [" + listOfMessages.size() + "] messages from: " + socket);
        System.out.println(objectInputStream.readObject());
        Student student= new Student(1,"B16DCAT017", (float) 2.55);
        // print out the text of every message
//        System.out.println("All messages:");
        obo.writeObject(student);
        System.out.println(objectInputStream.readObject());
        System.out.println("Closing sockets.");
        ss.close();
        socket.close();
    }
}
