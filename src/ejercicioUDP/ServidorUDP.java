package ejercicioUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorUDP {
	
	public static int contarPalabras(String frase) {
		int con= 0;
		for (int i = 0; i < frase.length(); i++) {
			if(frase.charAt(i) == ' '){
				con+=1;
			}
		}
		return con+1;
	}
	
	public static void main(String[] args) {
		System.out.println("**Servidor UDP Iniciado**");
		System.out.println("Esperando cliente...");
		try {
			DatagramSocket socketUDP = new DatagramSocket(6543);
			//Recibimos un vector de bytes del cliente
			byte [] bufer = new byte[10000];
			//el while es para recibir varias peticiones de cliente
			while(true){
				DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);
				//Almacenamos lo que nos envio el cliente
				socketUDP.receive(peticion);
				
				//Llamamos al metodo  contar palabras
				String frase = new String(peticion.getData()); 
				String con= String.valueOf(contarPalabras(frase));
				//Para enviar al cliente
				byte [] enviar = con.getBytes();
				DatagramPacket mensaje = new DatagramPacket(enviar, con.length() , peticion.getAddress(), peticion.getPort());
				socketUDP.send(mensaje);
				//Imprimimos los datos, antes convirtiendo en caracteres
				System.out.println("DATOS DEL CLIENTE: "+ new String(peticion.getData()));	
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
