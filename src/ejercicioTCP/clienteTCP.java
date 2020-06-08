package ejercicioTCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class clienteTCP {
	public static void main(String[] args) throws IOException{
		Socket socketCliente = null;
		BufferedReader entrada = null;
		PrintWriter salida = null;
		
		System.out.println("-----------------MENU CLIENTE------------------------");
		System.out.println("Opcion 1 \nOpcion 2 \nOpcion 3 \nSalir");
		System.out.println("(Presione s(salir) o un numero)");

		try {
			socketCliente = new Socket("localhost", 4673);
			entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
			salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())), true);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			//Enviar varios mensajes al servidor hasta poner exit
			
			while(true){
				//mensaje por consola
				String cad = sc.readLine();
				salida.println(cad);
				if(cad.equals("s")) break;
				//recibiendo el mensaje del servidor 
				cad = entrada.readLine();
				System.out.println("Respuesta del servidor--->>> "+ cad);
			}	
		} catch (Exception e) {
			System.out.println(e);
		}
		salida.close();
		entrada.close();
		sc.close();
		socketCliente.close();
	}
}
