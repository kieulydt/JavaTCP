/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Ki
 */
public class Sclient {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // need host and port, we want to connect to the ServerSocket at port 7777
        Socket client = new Socket("localhost", 7777);
        System.out.println("Connected!");

        // get the output stream from the socket.
        OutputStream outputStream = client.getOutputStream();
        // create an object output stream from the output stream so we can send an object through it
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        InputStream inputStream = client.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(inputStream);
        // make a bunch of messages to send.
//        List<Message> messages = new ArrayList<>();
//        messages.add(new Message("Hello from the other side!"));
//        messages.add(new Message("How are you doing?"));
//        messages.add(new Message("What time is it?"));
//        messages.add(new Message("Hi hi hi hi."));
        
//        System.out.println("Sending messages to the ServerSocket");
        objectOutputStream.writeObject("B16DCAT");
        Student student= new Student(0, "a", 0);
        student =(Student) ois.readObject();
        if( student.getGpa()>=3.2 ) {
            student.setGpaLetter("A");
        }
        else if( student.getGpa()>=2.5){
            student.setGpaLetter("B");
        } 
                else student.setGpaLetter("C");
        System.out.println(student);
        objectOutputStream.writeObject(student);
        System.out.println("Closing socket and terminating program.");
        client.close();
    }
}