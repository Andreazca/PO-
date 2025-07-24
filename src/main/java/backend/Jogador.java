package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um jogador de eFootball, incluindo suas estatísticas e torneios.
 */
public class Jogador implements Serializable {
    private String idJogador; // ID único do jogador
    private String nome_completo; // Nome completo do jogador
    private String nickname; // Apelido (nickname) do jogador
    private int numeroPartidas; // Número de partidas jogadas
    private int vitorias; // Número de vitórias do jogador
    private int derrotas; // Número de derrotas do jogador
    private int empates; // Número de empates do jogador
    private String password; // Senha do jogador
    private Equipa suaequipa; // Equipa à qual o jogador pertence
    private List<Torneio> listaTorneios; // Lista de torneios em que o jogador participa

    /**
     * Construtor para inicializar um jogador com id, nome, nickname e senha.
     */
    public Jogador(String idJogador, String nome_completo, String nickname, String password) {
        this.idJogador = idJogador;
        this.nome_completo = nome_completo;
        this.nickname = nickname;
        this.password = password;
        this.vitorias = 0;
        this.derrotas = 0;
        this.empates = 0;
        this.suaequipa = null;
        this.listaTorneios = new ArrayList<Torneio>();
    }

    // Métodos getters e setters para acessar e modificar os atributos do jogador

    public String getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(String idJogador) {
        this.idJogador = idJogador;
    }

    public String getNome_completo() {
        return nome_completo;
    }

    public void setNome_completo(String nome_completo) {
        this.nome_completo = nome_completo;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Equipa getSuaequipa() {
        return suaequipa;
    }

    public void setSuaequipa(Equipa suaequipa) {
        this.suaequipa = suaequipa;
    }

    public int getVitorias() {
        return vitorias;
    }

    /**
     * Incrementa o número de vitórias e o total de partidas jogadas.
     */
    public void setVitorias() {
        this.vitorias = vitorias + 1;
        this.numeroPartidas = numeroPartidas + 1;
    }

    public int getDerrotas() {
        return derrotas;
    }

    /**
     * Incrementa o número de derrotas e o total de partidas jogadas.
     */
    public void setDerrotas() {
        this.derrotas = derrotas + 1;
        this.numeroPartidas = numeroPartidas + 1;
    }

    public int getEmpates() {
        return empates;
    }

    /**
     * Incrementa o número de empates e o total de partidas jogadas.
     */
    public void setEmpates() {
        this.empates = empates + 1;
        this.numeroPartidas = numeroPartidas + 1;
    }

    public List<Torneio> getListaTorneios() {
        return listaTorneios;
    }

    public void setListaTorneios(List<Torneio> listaTorneios) {
        this.listaTorneios = listaTorneios;
    }

    /**
     * Adiciona um torneio à lista de torneios do jogador.
     */
    public void addTorneio(Torneio torneio) {
        this.listaTorneios.add(torneio);
    }

    /**
     * Remove um torneio da lista de torneios do jogador.
     */
    public void removeTorneio(Torneio torneio) {
        this.listaTorneios.remove(torneio);
    }

    /**
     * Retorna uma string com o nome e nickname do jogador.
     */
    public String toString() {
        return "Jogador\n nome=" + nome_completo + "\n nickname=" + nickname;
    }

    /**
     * Retorna uma string com detalhes adicionais do jogador, como estatísticas de jogos, vitórias, derrotas e empates.
     */
    public String toString2() {
        return "Jogador\n nome=" + nome_completo + "\n nickname=" + nickname + "\n Jogos:" + numeroPartidas + "\n Vitorias:" + vitorias + "\n Derrotas:" + derrotas + "\n Empates:" + empates;
    }
}
