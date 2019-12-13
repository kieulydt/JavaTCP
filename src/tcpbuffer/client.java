package tcpbuffer;

import java.io.*;
import java.net.*;
 
public class client {
 
   public static void main(String[] args) {
 
       // Địa chỉ máy chủ.
       final String serverHost = "localhost";
 
       Socket socketOfClient = null;
       BufferedWriter os = null;
       BufferedReader is = null;
 
       try {
           // Gửi yêu cầu kết nối tới Server đang lắng nghe
           // trên máy 'localhost' cổng 9999.
            Socket client = new Socket("localhost", 7777);
            System.out.println("Connected!");
 
           // Tạo luồng đầu ra tại client (Gửi dữ liệu tới server)
           os = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
 
           // Luồng đầu vào tại Client (Nhận dữ liệu từ server).
           is = new BufferedReader(new InputStreamReader(client.getInputStream()));
 
       } catch (UnknownHostException e) {
           System.err.println("Don't know about host " + serverHost);
           return;
       } catch (IOException e) {
           System.err.println("Couldn't get I/O for the connection to " + serverHost);
           return;
       }
 
       try {
           // Ghi dữ liệu vào luồng đầu ra của Socket tại Client.
           os.write("B16DCAT017");
           os.newLine(); // kết thúc dòng
           os.flush();  // đẩy dữ liệu đi.
           String responseLine=is.readLine();
           System.out.println(responseLine);
           // Đọc dữ liệu trả lời từ phía server
           // Bằng cách đọc luồng đầu vào của Socket tại Client.
           String strRes=responseLine;
           int k = strRes.indexOf(';');
           String temp_1="",temp_2="";
           String requestId = strRes.substring(0, k);
           for (int i = k+1; i < strRes.length(); i++) {
                if ((strRes.charAt(i) >= 48 && strRes.charAt(i) <= 57)
                        || (strRes.charAt(i) >= 65 && strRes.charAt(i) <= 90)
                        || (strRes.charAt(i) >= 97 && strRes.charAt(i) <= 122)) {
                    temp_1 += strRes.charAt(i);
                } else {
                    temp_2 += strRes.charAt(i);
                }
            }
            
            String strMsg = requestId+ ";" + temp_1 + "," + temp_2;
            os.write(strMsg);
           os.newLine(); // kết thúc dòng
           os.flush();  // đẩy dữ liệu đi.
           os.close();
           is.close();
           
       } catch (UnknownHostException e) {
           System.err.println("Trying to connect to unknown host: " + e);
       } catch (IOException e) {
           System.err.println("IOException:  " + e);
       }
   }
 
}