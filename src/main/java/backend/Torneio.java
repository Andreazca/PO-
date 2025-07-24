package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Torneio implements Serializable {
    private String id; // ID único do torneio
    private String nome; // Nome do torneio
    private String jogo; // Tipo de jogo (ex: "Futebol", "Efootball")
    List<Equipa> listaEquipas; // Lista de equipas inscritas no torneio
    List<Partida> listaPartidas; // Lista de partidas programadas para o torneio

    // Construtor da classe Torneio, inicializa as listas de equipas e partidas
    public Torneio(String id, String nome, String jogo) {
        this.id = id;
        this.nome = nome;
        this.jogo = jogo;
        this.listaEquipas = new ArrayList<>(); // Inicializa a lista de equipas
        this.listaPartidas = new ArrayList<>(); // Inicializa a lista de partidas
    }

    // Métodos getter e setter para os atributos do torneio
    public String getId() {
        return id; // Retorna o ID do torneio
    }
    public void setId(String id) {
        this.id = id; // Define o ID do torneio
    }

    public String getNome() {
        return nome; // Retorna o nome do torneio
    }
    public void setNome(String nome) {
        this.nome = nome; // Define o nome do torneio
    }

    public String getJogo() {
        return jogo; // Retorna o tipo de jogo do torneio
    }
    public void setJogo(String jogo) {
        this.jogo = jogo; // Define o tipo de jogo do torneio
    }

    public List<Equipa> getListaEquipas() {
        return listaEquipas; // Retorna a lista de equipas
    }
    public void setListaEquipas(List<Equipa> listaEquipas) {
        this.listaEquipas = listaEquipas; // Define a lista de equipas
    }

    public List<Partida> getListaPartidas() {
        return listaPartidas; // Retorna a lista de partidas
    }
    public void setListaPartidas(List<Partida> listaPartidas) {
        this.listaPartidas = listaPartidas; // Define a lista de partidas
    }

    // Método para adicionar uma equipa ao torneio
    public void adicionarEquipa(Equipa equipa) {
        listaEquipas.add(equipa); // Adiciona a equipa à lista de equipas
    }

    // Método para adicionar uma partida ao torneio
    public void adicionarPartida(Partida partida) {
        listaPartidas.add(partida); // Adiciona a partida à lista de partidas
    }

    // Método para remover uma equipa do torneio
    public void removerEquipa(Equipa equipa) {
        listaEquipas.remove(equipa); // Remove a equipa da lista de equipas
    }

    // Método para remover uma partida do torneio
    public void removerPartida(Partida partida) {
        listaPartidas.remove(partida); // Remove a partida da lista de partidas
    }

    // Método toString para representar o torneio como uma string
    public String toString() {
        return nome + "\nTipo de jogo: " + jogo; // Exibe o nome e o tipo de jogo do torneio
    }
}
