package frontend;

import backend.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Scanner;

public class Consola implements Serializable {

    // Método que converte uma string em LocalDateTime, validando o formato
    public LocalDateTime passarStringParaData() {
        Scanner sc = new Scanner(System.in);
        LocalDateTime dataHoraJogo = null;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); // Formato de data e hora

        // Loop para garantir que a data e hora fornecidas sejam válidas
        while (dataHoraJogo == null) {
            try {
                System.out.println("\nDigite a data e hora do jogo (formato: dd/MM/yyyy HH:mm):");
                String entrada = sc.nextLine(); // Entrada do usuário
                dataHoraJogo = LocalDateTime.parse(entrada, formato); // Tenta converter a entrada em LocalDateTime
                if (dataHoraJogo.isBefore(LocalDateTime.now())) {
                    System.out.println("\nA data e hora não podem ser anteriores à data atual. Tente novamente.");
                    dataHoraJogo = null; // Reseta a variável para continuar no loop
                }
            } catch (DateTimeParseException e) {
                System.out.println("\nFormato inválido! Tente novamente."); // Se o formato for inválido
            }
        }

        return dataHoraJogo; // Retorna a data/hora válida
    }

    // Método para converter uma string em um número inteiro, tratando exceções
    public int passarStringparaint(){
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        // Loop até que uma entrada válida seja fornecida
        while (true) {
            String entrada = sc.nextLine(); // Lê a entrada do usuário

            try {
                opcao = Integer.parseInt(entrada); // Tenta converter a entrada em um número inteiro
                break; // Se a conversão for bem-sucedida, sai do loop
            } catch (NumberFormatException e) {
                System.out.println("\nPor favor, digite um número inteiro."); // Se a conversão falhar
            }
        }
        return opcao; // Retorna o número inteiro fornecido
    }


    // Método para registrar as estatísticas de um jogador no FPS
    public void registarEstatisticaFPS(Jogador jogador){
        System.out.println("\nDigite as estaticas do jogador:"+jogador);
        System.out.println("\nDigite os tiros que o jogador realizou");
        int tiros = passarStringparaint();// Obtém os tiros
        System.out.println("\nDigite os tiros acertados que o jogador realizou");
        int tirosAcertados = passarStringparaint();// Obtém os tiros acertados
        if (tiros<tirosAcertados){
            registarEstatisticaFPS(jogador);
        }else{
        System.out.println("\nDigite os headshots do jogador");
        int headshots = passarStringparaint(); // Obtém o número de headshots
        (((JogadorFPS) jogador)).setTiros(tiros); // Define os tiros
        (((JogadorFPS) jogador)).setTirosAcertados(tirosAcertados);
        (((JogadorFPS) jogador)).setHeadshots(headshots); // Define os headshots
    }}

    // Método para registrar as estatísticas de um jogador no Efootball
    public void registarEstatisticaEfootball(Jogador jogador, String posicao){
        System.out.println("\nDigite as estaticas do jogador:"+jogador);
        // Verifica a posição do jogador para capturar as estatísticas apropriadas
        if (posicao.equals("Guarda-Redes")){ // Se o jogador for um Guarda-Redes
            System.out.println("\nDigite os golos sofridos do Guarda-Redes");
            int golosSofr = passarStringparaint(); // Obtém os gols sofridos
            (((JogadorEFootball) jogador)).setGolosMarcadosSofridos(golosSofr); // Define os gols sofridos
            System.out.println("\nDigite as assistências do jogador");
            int assistenciasgr = passarStringparaint(); // Obtém as assistências
            (((JogadorEFootball) jogador)).setAssistencias(assistenciasgr); // Define as assistências
        } else { // Se o jogador não for um Guarda-Redes
            System.out.println("\nDigite os golos marcados do Jogador");
            int golos = passarStringparaint(); // Obtém os gols marcados
            (((JogadorEFootball) jogador)).setGolosMarcadosSofridos(golos); // Define os gols marcados
            System.out.println("\nDigite as assistências do Jogador");
            int assistencias = passarStringparaint(); // Obtém as assistências
            (((JogadorEFootball) jogador)).setAssistencias(assistencias); // Define as assistências
        }
    }

    // Método para registrar as estatísticas de um jogador no MOBA
    public void registarEstatisticaMOBA(Jogador jogador, HashMap<String, Integer> personagemMaisJogado){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nDigite as estaticas do jogador:"+jogador);
        if (personagemMaisJogado == null){ // Se o jogador ainda não tem personagens jogadas
            System.out.println("\nAinda não tem personagens jogadas");
        } else {
            System.out.println(personagemMaisJogado); // Exibe os personagens jogados
        }
        System.out.println("\nDigite a personagem que o jogador jogou");
        String personagem = sc.next(); // Obtém o nome da personagem
        System.out.println("\nDigite as kills do jogador");
        int kills = passarStringparaint(); // Obtém as kills
        System.out.println("\nDigite as deaths do jogador");
        int deaths = passarStringparaint(); // Obtém as mortes
        System.out.println("\nDigite as assists do jogador");
        int assists = passarStringparaint(); // Obtém as assistências
        (((JogadorMOBA) jogador)).setPersonagemMaisJogado(personagem); // Define a personagem mais jogada
        (((JogadorMOBA) jogador)).setKills(kills); // Define as kills
        (((JogadorMOBA) jogador)).setDeaths(deaths); // Define as mortes
        (((JogadorMOBA) jogador)).setAssists(assists); // Define as assistências
    }

}
