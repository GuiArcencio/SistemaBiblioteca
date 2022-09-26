package app.Integracao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class Integracao {

	//private static final String USER_AGENT = "Mozilla/5.0";

	private static final String GET_DISCIPLINAS = "https://evening-tor-20872.herokuapp.com/alunos/";

	private static final String GET_GRUPODESATIVADO = "http://147.182.136.29:3000/students/deactivatedGroups?ra=";
	private static final String GET_GRUPOATUAL = "http://147.182.136.29:3000/students/";
	private static final String GET_GRUPOATUAL2 = "/activeGroups" ;



	public static int getGrupo(Long ra, int mode) {
		//mode define se procura por grupos atuais ou desativados
		//0 para desativados
		//1 para atuais
		String url = "";
		if (mode == 0){
			url = GET_GRUPODESATIVADO + ra.toString();
		}
		else {
			url = GET_GRUPOATUAL + ra.toString() + GET_GRUPOATUAL2;
		}
		String response = "";

		try{
			response = sendGET(url);
		} catch (IOException e){
			System.out.println(e.getMessage());
		}
		if (response == "")
			return -1;
		
		//divide a string para ler o número de disciplinas retornado
		String[] divisao = response.split("count\":");
		String str = divisao[1];
	
		str = str.substring(0, str.length() - 1); //remove o character } no final
		int l = Integer.parseInt(str); //converte para inteiro

		
		return l;
	}

	public static int getDisciplina(Long ra){
		String url = GET_DISCIPLINAS + ra.toString();
		String response = "";
		try{
			response = sendGET(url);
		} catch (IOException e){
			System.out.println(e.getMessage());
		}
		if (response == "")
			return -1;

		//divide a string para ler o número de disciplinas retornado
        String[] divisao = response.split("numeroDisciplinas\":");
        String str = divisao[1];

        str = str.substring(0, str.length() - 1); //remove o character } no final
        int l = Integer.parseInt(str); //converte para inteiro
		return l;
	}

	private static String sendGET(String url) throws IOException{
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		//con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			
			
			return response.toString();
		} else {
			System.out.println("GET request not worked");
			return "";
		}

	}

}
