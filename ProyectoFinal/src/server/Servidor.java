package server;

import java.io.*;
import java.net.*;

public class Servidor extends Thread {
    
    public static void main(String args[]) {
        ServerSocket sfd = null;
        try {
        	
        	System.out.println("Iniciando Conexi�n.");
            sfd = new ServerSocket(7000);
            System.out.println("Aceptando conexiones por la IP: "+sfd.getInetAddress());
        } catch (IOException ioe) {
            System.out.println("Comunicaci�n con el servidor rechazada." + ioe);
            System.exit(1);
        }

        while (true) {
            try {
                Socket clientSocket = sfd.accept();
                System.out.println("Conexi�n aceptada de: " + clientSocket.getInetAddress());
                
                DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
                FileOutputStream fileOutputStream = new FileOutputStream("tienda_backup.dat");
                
                byte[] buffer = new byte[4096];
                int bytesRead;
                
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }
                
                fileOutputStream.close();
                inputStream.close();
                clientSocket.close();
                sfd.close();
                
                System.out.println("Operaci�n satisfactoria. Backup realizado correctamente!");
            } catch (IOException ioe) {
                System.out.println("Error al manejar la conexi�n: " + ioe);
            }
        }
    }
}