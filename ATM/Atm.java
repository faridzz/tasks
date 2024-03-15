// A Java program for a Atm

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Atm {

  // initialize socket and input output streams
  private Socket socket = null;
  private DataInputStream input = null;
  private DataInputStream inputS = null;
  private DataOutputStream out = null;

  // constructor to put ip address and port
  public Atm(String address, int port) {
    // establish a connection
    try {
      socket = new Socket(address, port);

      System.out.println("Connected");

      // takes input from terminal
      input = new DataInputStream(System.in);
      //get data from server
      inputS =
        new DataInputStream(new BufferedInputStream(socket.getInputStream()));
      // sends output to the socket
      out = new DataOutputStream(socket.getOutputStream());
    } catch (UnknownHostException u) {
      System.out.println(u);
    } catch (IOException i) {
      System.out.println(i);
    }
    // string to read message from input
    String line = null;
    System.out.println("How much do you want to withdraw from your account?");
    // keep reading until "End" is input
    while (!"End".equals(line)) {
      try {
        line = input.readLine();
        try {
          // Try parsing the input as an Integer
          int amount = Integer.parseInt(line);
          // If successful, use it as an integer
          out.writeUTF(String.valueOf(amount));
          //show data from server
          String respon = inputS.readUTF();
          System.out.println("result : " + respon);
          whatHappen(respon);
        } catch (NumberFormatException e) {
          // If parsing as Integer fails, use it as a string
          System.out.println("pls enter a number :");
          continue;
        }
      } catch (IOException i) {
        System.out.println(i);
      }
    }

    // close the connection
    try {
      input.close();

      out.close();

      socket.close();
    } catch (IOException i) {
      System.out.println(i);
    }
  }

  public static void whatHappen(String mesage) {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println("do u want to continue ? yes or no : ");
      String answer = scanner.nextLine();
      if (answer.equals("yes")) {
        System.out.println("This feature is not available in the demo version");
      } else if (answer.equals("no")) {
        System.out.println("ok bye");
        System.exit(0);
      }
    }
  }

  public static void main(String[] args) {
    Atm client = new Atm("127.0.0.1", 5000);
  }
}
