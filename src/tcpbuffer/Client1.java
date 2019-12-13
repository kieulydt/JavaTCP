/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpbuffer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class Client1 {
    public static void main(String[] args) {
        BufferedWriter os = null;
        BufferedReader is = null;
        try {
            Socket client = new Socket("localhost", 7777);
            os = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            is = new BufferedReader(new InputStreamReader(client.getInputStream()));
            os.write("B16DCAT102");
            os.newLine();
            os.flush();
            String s = is.readLine();
            String[] result = s.split(";");
            String temp1="", temp2 = "";
            for(int i=0; i<result[1].length(); i++){
                if((result[1].charAt(i)>= 'a' && result[1].charAt(i)<='z')
                        || (result[1].charAt(i)>='A' && result[1].charAt(i)<='Z')
                        || (result[1].charAt(i)>='0' && result[1].charAt(i)<='9'))
                    temp1 += result[1].charAt(i);
                else
                    temp2 += result[1].charAt(i);
            }
            String kq = "";
            kq = result[0] + ";" + temp1 + "," + temp2;
            os.write(kq);
            os.newLine();
            os.flush();
            os.close();
            is.close();
        } catch (IOException ex) {
            Logger.getLogger(Client1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
