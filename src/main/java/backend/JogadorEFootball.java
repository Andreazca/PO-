package backend;

import java.io.Serializable;

/**
 * Classe que representa um jogador de eFootball, com informações adicionais sobre sua posição, gols e assistências.
 * Herda da classe Jogador.
 */
public class JogadorEFootball extends Jogador implements Serializable {
    private String posicao; // Posição do jogador (ex: atacante, defensor, meio-campista)
    private int golosMarcadosSofridos; // Gols marcados e sofridos pelo jogador
    private int assistencias; // Número de assistências realizadas pelo jogador

    /**
     * Construtor para inicializar o jogador de eFootball com id, nome, nickname, senha e posição.
     * Utiliza o construtor da classe base Jogador.
     */
    public JogadorEFootball(String idJogador, String nome_completo, String nickname, String password, String posicao) {
        super(idJogador, nome_completo, nickname, password); // Chama o construtor da classe base Jogador
        this.posicao = posicao;
        this.golosMarcadosSofridos = 0;
        this.assistencias = 0;
    }

    // Métodos getters e setters para acessar e modificar os atributos da posição, gols e assistências

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public int getGolosMarcadosSofridos() {
        return golosMarcadosSofridos;
    }

    /**
     * Incrementa o número de gols marcados e sofridos.
     */
    public void setGolosMarcadosSofridos(int golosMarcados) {
        this.golosMarcadosSofridos = golosMarcados + golosMarcadosSofridos;
    }

    public int getAssistencias() {
        return assistencias;
    }

    /**
     * Incrementa o número de assistências do jogador.
     */
    public void setAssistencias(int assistencia) {
        this.assistencias = assistencia + assistencias;
    }

    /**
     * Retorna uma string com os detalhes básicos do jogador, incluindo sua posição.
     */
    @Override
    public String toString() {
        String jogador = super.toString(); // Chama o método toString da classe base Jogador
        return jogador + "\n Posição:" + posicao;
    }

    /**
     * Retorna uma string com detalhes mais completos sobre o jogador, incluindo posição, gols e assistências.
     */
    @Override
    public String toString2() {
        String jogador = super.toString2(); // Chama o método toString2 da classe base Jogador
        return jogador + "\n Posição:" + posicao + " \n Golos:" + golosMarcadosSofridos + " \n Assistências:" + assistencias;
    }
}
