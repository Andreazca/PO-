package backend;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Classe que representa uma lista de equipas. Permite adicionar, remover e procurar equipas,
 * bem como criar equipas de diferentes tipos (Efootball, FPS, MOBA) e gerenciar jogadores dentro dessas equipas.
 */
public class ListaEquipas implements Serializable {
    private List<Equipa> le; // Lista de equipas

    /**
     * Construtor que inicializa a lista de equipas como uma ArrayList.
     */
    public ListaEquipas() {
        this.le = new ArrayList<Equipa>();
    }

    /**
     * Adiciona uma equipa à lista.
     */
    public void adicionarEquipa(Equipa e) {
        le.add(e);
    }

    /**
     * Remove uma equipa da lista.
     */
    public void removerEquipa(Equipa e) {
        le.remove(e);
    }

    /**
     * Retorna a lista de equipas.
     */
    public List<Equipa> getLe() {
        return le;
    }

    /**
     * Verifica se já existe uma equipa com o nome fornecido.
     *
     * @param nome O nome da equipa a ser verificado
     * @return true se o nome da equipa já existir, false caso contrário
     */
    public boolean verificarnome(String nome) {
        if (le.isEmpty()) {
            return false;
        }
        for (Equipa equipa : le) {
            if (equipa.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Cria uma nova equipa de tipo Efootball, atribui um ID único e adiciona um treinador.
     *
     * @param nome O nome da equipa
     * @param treinador O treinador da equipa
     * @return A equipa criada
     */
    public Equipa criarEquipaEfootball(String nome, Treinador treinador) {
        String id = UUID.randomUUID().toString(); // Gera um ID único para a equipa
        EquipaEfootball equipaEfootball = new EquipaEfootball(id, nome, treinador);
        adicionarEquipa(equipaEfootball); // Adiciona a equipa à lista
        treinador.setEquipagerida(equipaEfootball); // Atribui a equipa ao treinador
        return equipaEfootball;
    }

    /**
     * Cria uma nova equipa de tipo FPS, atribui um ID único e adiciona um treinador.
     *
     * @param nome O nome da equipa
     * @param treinador O treinador da equipa
     * @return A equipa criada
     */
    public Equipa criarEquipaFPS(String nome, Treinador treinador) {
        String id = UUID.randomUUID().toString(); // Gera um ID único para a equipa
        EquipaFPS equipaFPS = new EquipaFPS(id, nome, treinador);
        adicionarEquipa(equipaFPS); // Adiciona a equipa à lista
        treinador.setEquipagerida(equipaFPS); // Atribui a equipa ao treinador
        return equipaFPS;
    }

    /**
     * Cria uma nova equipa de tipo MOBA, atribui um ID único e adiciona um treinador.
     *
     * @param nome O nome da equipa
     * @param treinador O treinador da equipa
     * @return A equipa criada
     */
    public Equipa criarEquipaMOBA(String nome, Treinador treinador) {
        String id = UUID.randomUUID().toString(); // Gera um ID único para a equipa
        EquipaMOBA equipaMOBA = new EquipaMOBA(id, nome, treinador);
        adicionarEquipa(equipaMOBA); // Adiciona a equipa à lista
        treinador.setEquipagerida(equipaMOBA); // Atribui a equipa ao treinador
        return equipaMOBA;
    }

    /**
     * Encontra o tipo de uma equipa com base no seu ID.
     *
     * @param id O ID da equipa
     * @return O tipo da equipa ("Efootball", "FPS", "MOBA") ou "Tipo não encontrado" se a equipa não for encontrada
     */
    public String encontrarTipo(String id) {
        for (Equipa equipa : le) {
            if (equipa.getId().equals(id)) {
                if (equipa instanceof EquipaEfootball) {
                    return "Efootball";
                } else if (equipa instanceof EquipaFPS) {
                    return "FPS";
                } else if (equipa instanceof EquipaMOBA) {
                    return "MOBA";
                }
            }
        }
        return "Tipo não encontrado";
    }

    /**
     * Modifica o nome de uma equipa com base no seu ID.
     *
     * @param id O ID da equipa
     * @param nome O novo nome para a equipa
     * @return A equipa modificada ou null se a equipa não for encontrada
     */
    public Equipa mudarNome(String id, String nome) {
        for (Equipa equipa : le) {
            if (equipa.getId().equals(id)) {
                equipa.setNome(nome);
                return equipa;
            }
        }
        return null;
    }

    /**
     * Adiciona um jogador a uma equipa com base no ID da equipa.
     *
     * @param jogador O jogador a ser adicionado
     * @param id O ID da equipa
     */
    public void adicionarJogadorEquipa(Jogador jogador, String id) {
        for (Equipa equipa : le) {
            if (equipa.getId().equals(id)) {
                equipa.adicionarJogador(jogador); // Adiciona o jogador à equipa
                jogador.setSuaequipa(equipa); // Atribui a equipa ao jogador
            }
        }
    }

    /**
     * Remove um jogador de uma equipa com base no nome da equipa.
     *
     * @param jogador O jogador a ser removido
     * @param nome O nome da equipa
     * @return true se o jogador foi removido com sucesso, false caso contrário
     */
    public boolean removerJogadorEquipa(Jogador jogador, String nome) {
        for (Equipa equipa : le) {
            if (equipa.getNome().equals(nome)) {
                for (Jogador jogador1 : equipa.getJogador()) {
                    if (jogador1.equals(jogador)) {
                        equipa.removerJogador(jogador); // Remove o jogador da equipa
                        jogador.setSuaequipa(null); // Limpa a referência da equipa no jogador
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Encontra uma equipa com base no ID.
     *
     * @param id O ID da equipa
     * @return A equipa correspondente ao ID fornecido ou null se a equipa não for encontrada
     */
    public Equipa encontrarEquipaid(String id) {
        for (Equipa equipa : le) {
            if (equipa.getId().equals(id)) {
                return equipa;
            }
        }
        return null;
    }

    /**
     * Encontra o nome de uma equipa com base no ID.
     *
     * @param id O ID da equipa
     * @return O nome da equipa ou null se a equipa não for encontrada
     */
    public String encontrarNome(String id) {
        for (Equipa equipa : le) {
            if (equipa.getId().equals(id)) {
                return equipa.getNome();
            }
        }
        return null;
    }

    /**
     * Encontra uma equipa com base no nome.
     *
     * @param nome O nome da equipa
     * @return A equipa correspondente ao nome fornecido ou null se a equipa não for encontrada
     */
    public Equipa encontrarEquipaNome(String nome) {
        for (Equipa equipa : le) {
            if (equipa.getNome().equals(nome)) {
                return equipa;
            }
        }
        return null;
    }

    /**
     * Lista todos os jogadores de uma equipa com base no ID da equipa.
     *
     * @param id O ID da equipa
     * @return A lista de jogadores ou null se não houver jogadores na equipa
     */
    public List<Jogador> listarJogadores(String id) {
        List<Jogador> jogadors = new ArrayList<>();
        for (Equipa equipa : le) {
            if (equipa.getId().equals(id)) {
                if (equipa.getJogador().isEmpty()) {
                    return null; // Se não houver jogadores, retorna null
                } else {
                    return equipa.getJogador(); // Retorna a lista de jogadores da equipa
                }
            }
        }
        return null;
    }
}
