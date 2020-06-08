package ejercicioUDP;
/* 
 * NOMBRE: MAYDA MARTHA ADUVIRI CONDORI
 * PARALELO: JUEVES 16:00 - 18:00
 * DOCENTE: LIC. RAMIRO GALLARDO
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.StringTokenizer;

public class ServidorUDP {
	
	public static String recuperar(String cad, int tam){
		String res = "";
		for (int i = 0; i < tam; i++) {
			res+= cad.charAt(i);
		}
		return res;
	}

	public static void main(String[] args) {
		System.out.println("[*] Servidor UDP Iniciado");
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
				
				String res = new String(peticion.getData());
				
				String x= recuperar(res, peticion.getLength());
				
			    StringTokenizer st = new StringTokenizer(x);
			    String con = String.valueOf(st.countTokens());
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
