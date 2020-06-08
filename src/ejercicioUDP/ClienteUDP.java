package ejercicioUDP;
/* 
 * NOMBRE: MAYDA MARTHA ADUVIRI CONDORI
 * PARALELO: JUEVES 16:00 - 18:00
 * DOCENTE: LIC. RAMIRO GALLARDO
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteUDP {
	public static void main(String[] args) {
		System.out.println("--------------------CLIENTE-----------------------");
		try {
			//Creacion del socket
			DatagramSocket socketUDP = new DatagramSocket();
			int puerto = 6543;
			InetAddress host = InetAddress.getByName("localhost");
			//Buffer para leer en consola
			BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
			
			String cad;
			
			//while para realizar varias peticiones
			while(true){
				//Leemos en consola
				cad = sc.readLine();
				if(cad.equals("0")) break;
				//Creamos un vector de bytes de la cadena
				byte [] men = cad.getBytes();
				//Enviar datos a traves del socket
				DatagramPacket peticion = new DatagramPacket(men, cad.length(), host, puerto);
				//Enviamos al servidor
				socketUDP.send(peticion);
				
				//Recibimos el mensaje del servidor en un vector de bytes
				byte [] bufer = new byte[10000];
				DatagramPacket mensaje = new DatagramPacket(bufer, bufer.length);
				socketUDP.receive(mensaje);

				System.out.println("\n RESPUESTA DEL SERVIDOR >>> \n Numero de palabras en la frase----> "+ new String(mensaje.getData()));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
