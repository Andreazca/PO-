package backend;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Classe que representa um jogador de MOBA (Multiplayer Online Battle Arena), com estatísticas específicas como kills, deaths, assists,
 * e o personagem mais jogado.
 * Herda da classe Jogador.
 */
public class JogadorMOBA extends Jogador implements Serializable {
    private int kills; // Número de kills do jogador
    private int deaths; // Número de mortes do jogador
    private int assists; // Número de assistências do jogador
    private HashMap<String, Integer> personagemMaisJogado; // Mapa de personagens mais jogados com a quantidade de vezes que cada um foi jogado
    private String personagem; // Personagem mais jogado pelo jogador

    /**
     * Construtor para inicializar um jogador de MOBA com id, nome, nickname e senha.
     * Utiliza o construtor da classe base Jogador.
     */
    public JogadorMOBA(String idJogador, String nome_completo, String nickname, String password) {
        super(idJogador, nome_completo, nickname, password); // Chama o construtor da classe base Jogador
        this.personagemMaisJogado = null; // Inicializa o mapa de personagens mais jogados como null
    }

    /**
     * Retorna o mapa de personagens mais jogados pelo jogador.
     */
    public HashMap<String, Integer> getPersonagemMaisJogado() {
        return personagemMaisJogado;
    }

    /**
     * Define o personagem mais jogado, incrementando a quantidade de vezes que foi jogado.
     * Se o personagem ainda não estiver registrado, ele é adicionado com o valor inicial 1.
     */
    public void setPersonagemMaisJogado(String personagem) {
        if (personagemMaisJogado == null) {
            personagemMaisJogado = new HashMap<>(); // Inicializa o mapa de personagens
        }
        if (personagemMaisJogado.containsKey(personagem)) {
            // Incrementa a quantidade de vezes que o personagem foi jogado
            personagemMaisJogado.put(personagem, personagemMaisJogado.get(personagem) + 1);
            System.out.println("Adicionado com sucesso");
        } else {
            // Adiciona a chave com o valor inicial 1
            personagemMaisJogado.put(personagem, 1);
            System.out.println("Adicionado com sucesso");
        }
    }

    /**
     * Retorna o personagem mais jogado, ou uma mensagem indicando que nenhum personagem foi registrado.
     */
    public String getPersonagem() {
        if (personagemMaisJogado == null || personagemMaisJogado.isEmpty()) {
            personagem = "Nenhum personagem registrado."; // Caso o mapa esteja vazio
        } else {
            int maiorQuantidade = 0;
            // Itera pelo mapa para encontrar o personagem mais jogado
            for (HashMap.Entry<String, Integer> entry : personagemMaisJogado.entrySet()) {
                if (entry.getValue() > maiorQuantidade) {
                    maiorQuantidade = entry.getValue();
                    personagem = entry.getKey();
                }
            }
        }

        return personagem;
    }

    // Métodos getters e setters para acessar e modificar as estatísticas do jogador

    public int getKills() {
        return kills;
    }

    public void setKills(int kill) {
        this.kills = kills + kill; // Incrementa o número de kills
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int death) {
        this.deaths = deaths + death; // Incrementa o número de mortes
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assist) {
        this.assists = assists + assist; // Incrementa o número de assistências
    }

    /**
     * Retorna uma string com os detalhes básicos do jogador (nome, nickname, etc.) usando o método da classe base Jogador.
     */
    @Override
    public String toString() {
        return super.toString(); // Chama o método toString da classe base Jogador
    }

    /**
     * Retorna uma string com detalhes completos sobre o jogador, incluindo o personagem mais jogado, kills, assistências e mortes.
     */
    @Override
    public String toString2() {
        return super.toString() + "\n Personagem mais jogado: " + getPersonagem() + "\n Kills: " + kills + "\n Assists: " + assists + "\n Deaths: " + deaths;
    }
}
