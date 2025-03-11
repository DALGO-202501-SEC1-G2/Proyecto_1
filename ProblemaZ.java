import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ProblemaZ {

	public static void main(String[] args) throws Exception {
		ProblemaZ instancia = new ProblemaZ();
		try (InputStreamReader is = new InputStreamReader(System.in); BufferedReader br = new BufferedReader(is);) {
			String line = br.readLine();
			int casos = Integer.parseInt(line);
			line = br.readLine();
			for (int i = 0; i < casos && line != null && line.length() > 0 && !"0".equals(line); i++) {
				final String[] dataStr = line.split(" ");
				final int[] numeros = Arrays.stream(dataStr).mapToInt(f -> Integer.parseInt(f)).toArray();
				int[] respuestas = instancia.procesarNumeros(numeros);
				System.out.println(respuestas[0] + " " + respuestas[1]);
				line = br.readLine();
			}
		}
	}

	public int [] procesarNumeros(int[] numeros) {
		int [] respuestas = {0,0};
		for(int i=0;i<numeros.length;i++) {
			if(numeros[i]%2==0) {
				respuestas[0]++;
				respuestas[1]+=numeros[i];
			}
		}
		return respuestas;
	}

}
