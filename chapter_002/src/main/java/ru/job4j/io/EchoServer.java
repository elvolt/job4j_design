package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String message = "";
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        Pattern pattern = Pattern.compile("\\?msg=\\S+");
                        Matcher matcher = pattern.matcher(str);
                        while (matcher.find()) {
                            message = matcher.group().split("msg=")[1].trim();
                        }
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                    if (message.equals("Buy")) {
                        break;
                    }
                }
            }
        }
    }
}