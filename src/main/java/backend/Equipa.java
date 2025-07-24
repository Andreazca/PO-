package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma equipa de jogadores com informações sobre vitórias, derrotas e empates.
 */
public class Equipa implements Serializable {
    private String id; // ID único da equipa
    private String nome; // Nome da equipa
    private List<Jogador> je; // Lista de jogadores da equipa
    private int vitorias; // Número de vitórias da equipa
    private int derrotas; // Número de derrotas da equipa
    private int empates; // Número de empates da equipa
    private Treinador treinador; // Treinador da equipa

    /**
     * Construtor para inicializar a equipa com um id, nome e treinador.
     */
    public Equipa(String id, String nome, Treinador treinador) {
        this.id = id;
        this.nome = nome;
        this.je = new ArrayList<>();
        this.vitorias = 0;
        this.derrotas = 0;
        this.empates = 0;
        this.treinador = treinador;
    }

    // Métodos para adicionar e remover jogadores na lista de jogadores da equipa

    /**
     * Adiciona um jogador à equipa.
     */
    public void adicionarJogador(Jogador j) {
        je.add(j);
    }

    /**
     * Remove um jogador da equipa.
     */
    public void removerJogador(Jogador j) {
        je.remove(j);
    }

    /**
     * Retorna a lista de jogadores da equipa.
     */
    public List<Jogador> getJogador() {
        return je;
    }

    // Métodos getters e setters para acessar e modificar os atributos da equipa

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVitorias() {
        return vitorias;
    }

    /**
     * Incrementa o número de vitórias da equipa.
     */
    public void setVitorias() {
        this.vitorias = 1 + vitorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    /**
     * Incrementa o número de derrotas da equipa.
     */
    public void setDerrotas() {
        this.derrotas = 1 + derrotas;
    }

    public int getEmpates() {
        return empates;
    }

    /**
     * Incrementa o número de empates da equipa.
     */
    public void setEmpates() {
        this.empates = 1 + empates;
    }

    /**
     * Retorna o nome da equipa.
     */
    @Override
    public String toString() {
        return nome;
    }
}
