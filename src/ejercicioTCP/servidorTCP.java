package ejercicioTCP;
/* 
 * NOMBRE: MAYDA MARTHA ADUVIRI CONDORI
 * PARALELO: JUEVES 16:00 - 18:00
 * DOCENTE: LIC. RAMIRO GALLARDO
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class servidorTCP {
	public static void main(String[] args) throws IOException {
		ServerSocket socketServidor = null;
		Socket socketCliente = null;
		BufferedReader entrada = null;
		PrintWriter salida = null;
		
		System.out.println("[*] Servidor TCP iniciado");
		System.out.println("Esperando cliente...");
		try {
			socketServidor = new ServerSocket(4673);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			while(true){
				socketCliente = socketServidor.accept();
				entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
				salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())), true);
				
				while(true){
					//recibimos mensaje del cliente
					String cad = entrada.readLine();

					if(cad.equals("s")) break;
					System.out.println(cad);
					
					int c = Integer.parseInt(cad);
					//enviando al servidor
					
					switch(c){
					case 1:
						salida.println("papel");
					case 2:
						salida.println("piedra");
					case 3:
						salida.println("tijera");
					}
					
				}					
				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		entrada.close();
		salida.close();
		socketServidor.close();
		socketCliente.close();
	}
}
