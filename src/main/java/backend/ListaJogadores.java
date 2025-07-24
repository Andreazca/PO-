package backend;

import frontend.Consola;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Classe responsável pela gestão de uma lista de jogadores.
 * Permite adicionar, remover, modificar jogadores, e também
 * registrar estatísticas e verificar a associação entre jogadores e equipas.
 */
public class ListaJogadores implements Serializable {
    private final Consola consola; // Objeto para exibir e registrar estatísticas na interface
    List<Jogador> lj; // Lista de jogadores

    /**
     * Construtor que inicializa a lista de jogadores e cria um objeto Consola.
     */
    public ListaJogadores() {
        lj = new ArrayList<>();
        this.consola = new Consola();
    }

    /**
     * Adiciona um jogador à lista de jogadores.
     *
     * @param j O jogador a ser adicionado
     */
    public void adicionarjogador(Jogador j) {
        lj.add(j);
    }

    /**
     * Remove um jogador da lista de jogadores.
     *
     * @param j O jogador a ser removido
     */
    public void removerJogador(Jogador j) {
        lj.remove(j);
    }

    /**
     * Retorna a lista de jogadores.
     *
     * @return Lista de jogadores
     */
    public List<Jogador> getJogadores() {
        return lj;
    }

    /**
     * Verifica se um nickname já está registrado entre os jogadores.
     *
     * @param nickname O nickname a ser verificado
     * @return true se o nickname existir, false caso contrário
     */
    public boolean verificarnickname(String nickname) {
        for (Jogador jogador : lj) {
            if (jogador.getNickname().equals(nickname)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Cria um novo jogador do tipo Efootball, atribui um ID único e o adiciona à lista de jogadores.
     *
     * @param nome O nome do jogador
     * @param nickname O nickname do jogador
     * @param password A senha do jogador
     * @param posicao A posição do jogador no jogo
     */
    public void criarJogadorEfootball(String nome, String nickname, String password, String posicao) {
        String id = UUID.randomUUID().toString(); // Gera um ID único para o jogador
        JogadorEFootball jogadorEFootball = new JogadorEFootball(id, nome, nickname, password, posicao);
        adicionarjogador(jogadorEFootball); // Adiciona o jogador à lista
    }

    /**
     * Cria um novo jogador do tipo FPS, atribui um ID único e o adiciona à lista de jogadores.
     *
     * @param nome O nome do jogador
     * @param nickname O nickname do jogador
     * @param password A senha do jogador
     */
    public void criarJogadorFPS(String nome, String nickname, String password) {
        String id = UUID.randomUUID().toString(); // Gera um ID único para o jogador
        JogadorFPS jogadorFPS = new JogadorFPS(id, nome, nickname, password);
        adicionarjogador(jogadorFPS); // Adiciona o jogador à lista
    }

    /**
     * Cria um novo jogador do tipo MOBA, atribui um ID único e o adiciona à lista de jogadores.
     *
     * @param nome O nome do jogador
     * @param nickname O nickname do jogador
     * @param password A senha do jogador
     */
    public void criarJogadorMOBA(String nome, String nickname, String password) {
        String id = UUID.randomUUID().toString(); // Gera um ID único para o jogador
        JogadorMOBA jogadorMOBA = new JogadorMOBA(id, nome, nickname, password);
        adicionarjogador(jogadorMOBA); // Adiciona o jogador à lista
    }

    /**
     * Valida o login de um jogador com base no nickname (email) e na senha fornecida.
     *
     * @param email O email (nickname) do jogador
     * @param password A senha do jogador
     * @return true se o login for válido, false caso contrário
     */
    public boolean validarlogin(String email, String password) {
        for (Jogador jogador : lj) {
            if (jogador.getNickname().equals(email) && jogador.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Encontra o ID de um jogador com base no seu nickname.
     *
     * @param nickname O nickname do jogador
     * @return O ID do jogador ou null se o jogador não for encontrado
     */
    public String encontrarid(String nickname) {
        for (Jogador jogador : lj) {
            if (jogador.getNickname().equals(nickname)) {
                return jogador.getIdJogador();
            }
        }
        return null;
    }

    /**
     * Encontra um jogador na lista com base no seu nickname.
     *
     * @param nickname O nickname do jogador
     * @return O jogador correspondente ou null se o jogador não for encontrado
     */
    public Jogador encontrarJogador(String nickname) {
        for (Jogador jogador : lj) {
            if (jogador.getNickname().equals(nickname)) {
                return jogador;
            }
        }
        return null;
    }

    /**
     * Modifica o nome de um jogador com base no seu ID.
     *
     * @param id O ID do jogador
     * @param nome O novo nome do jogador
     */
    public void mudarNome(String id, String nome) {
        for (Jogador jogador : lj) {
            if (jogador.getIdJogador().equals(id)) {
                jogador.setNome_completo(nome); // Modifica o nome do jogador
            }
        }
    }

    /**
     * Encontra o nickname de um jogador com base no seu ID.
     *
     * @param id O ID do jogador
     * @return O nickname do jogador ou null se o jogador não for encontrado
     */
    public String encontrarNome(String id) {
        for (Jogador jogador : lj) {
            if (jogador.getIdJogador().equals(id)) {
                return jogador.getNickname();
            }
        }
        return null;
    }

    /**
     * Modifica o nickname de um jogador com base no seu ID.
     *
     * @param id O ID do jogador
     * @param nickname O novo nickname do jogador
     */
    public void mudarNickname(String id, String nickname) {
        for (Jogador jogador : lj) {
            if (jogador.getIdJogador().equals(id)) {
                jogador.setNickname(nickname); // Modifica o nickname do jogador
            }
        }
    }

    /**
     * Lista todos os jogadores que não estão associados a nenhuma equipa.
     *
     * @return Lista de jogadores sem equipa ou null se não houver jogadores sem equipa
     */
    public List<Jogador> listarJogadores() {
        List<Jogador> jogadores = new ArrayList<>();
        if (lj.isEmpty()) {
            return null;
        }
        for (Jogador jogador : lj) {
            if (jogador.getSuaequipa() == null) {
                jogadores.add(jogador); // Adiciona jogadores sem equipa à lista
            }
        }
        return jogadores.isEmpty() ? null : jogadores;
    }

    /**
     * Lista jogadores que não estão associados a nenhuma equipa, filtrando por tipo de equipa.
     *
     * @param tipoEquipa O tipo de equipa (Efootball, FPS, MOBA)
     * @return Lista de jogadores do tipo especificado ou null se não houver jogadores correspondentes
     */
    public List<Jogador> listarJogadoresTipo(String tipoEquipa) {
        String modalidade = "Jogador" + tipoEquipa;
        List<Jogador> jogadores = new ArrayList<>();
        if (lj.isEmpty()) {
            return null;
        }

        for (Jogador jogador : lj) {
            if (jogador.getSuaequipa() == null) { // Só jogadores sem equipa
                if (modalidade.equals("JogadorEfootball")) {
                    if (jogador instanceof JogadorEFootball) {
                        jogadores.add(jogador);
                    }
                }
                if (modalidade.equals("JogadorFPS")) {
                    if (jogador instanceof JogadorFPS) {
                        jogadores.add(jogador);
                    }
                }
                if (modalidade.equals("JogadorMOBA")) {
                    if (jogador instanceof JogadorMOBA) {
                        jogadores.add(jogador);
                    }
                }
            }
        }
        return jogadores;
    }

    /**
     * Verifica o nome da equipa a qual um jogador está associado com base no seu ID.
     *
     * @param id O ID do jogador
     * @return O nome da equipa do jogador ou null se o jogador não tiver equipa
     */
    public String verificarEquipa(String id) {
        for (Jogador jogador : lj) {
            if (jogador.getIdJogador().equals(id)) {
                if (jogador.getSuaequipa() != null) {
                    return jogador.getSuaequipa().getNome(); // Retorna o nome da equipa
                }
            }
        }
        return null;
    }

    /**
     * Retorna as estatísticas de um jogador com base no seu nickname.
     *
     * @param nickname O nickname do jogador
     * @return As estatísticas do jogador ou null se o jogador não for encontrado
     */
    public String estatisticasbase(String nickname) {
        for (Jogador jogador : lj) {
            if (jogador.getNickname().equals(nickname)) {
                return jogador.toString2(); // Chama o método toString2 do jogador para exibir suas estatísticas
            }
        }
        return null;
    }

    /**
     * Registra as estatísticas de jogadores após uma partida entre duas equipas.
     *
     * @param nomeEquipaA O nome da equipa A
     * @param resultadoA O resultado da equipa A
     * @param resultadoB O resultado da equipa B
     * @param nomeEquipaB O nome da equipa B
     */
    public void registarEstatisticas(String nomeEquipaA, int resultadoA, int resultadoB, String nomeEquipaB) {
        for (Jogador jogador : lj) {
            Equipa equipa = jogador.getSuaequipa(); // Recupera a equipa do jogador
            if (equipa != null) {
                String nomeEquipa = equipa.getNome();
                if (nomeEquipa.equals(nomeEquipaA)) {
                    if (resultadoA == resultadoB) {
                        jogador.setEmpates(); // Registra empate
                    } else if (resultadoA > resultadoB) {
                        jogador.setVitorias(); // Registra vitória
                    } else {
                        jogador.setDerrotas(); // Registra derrota
                    }
                }
                if (nomeEquipa.equals(nomeEquipaB)) {
                    if (resultadoA == resultadoB) {
                        jogador.setEmpates(); // Registra empate
                    } else if (resultadoA > resultadoB) {
                        jogador.setDerrotas(); // Registra derrota
                    } else {
                        jogador.setVitorias(); // Registra vitória
                    }
                }
            }
        }
    }

    /**
     * Registra as estatísticas específicas de jogadores de acordo com o tipo de jogo (FPS, EFootball, MOBA).
     *
     * @param nomeEquipa O nome da equipa cujos jogadores terão suas estatísticas registradas
     */
    public void registarEstatisticasJogadores(String nomeEquipa) {
        for (Jogador jogador : lj) {
            if (jogador.getSuaequipa() != null && jogador.getSuaequipa().getNome().equals(nomeEquipa)) {
                if (jogador instanceof JogadorFPS) {
                    consola.registarEstatisticaFPS(jogador); // Registra as estatísticas de um jogador FPS
                }
                if (jogador instanceof JogadorEFootball) {
                    String posicao = ((JogadorEFootball) jogador).getPosicao();
                    consola.registarEstatisticaEfootball(jogador, posicao); // Registra as estatísticas de um jogador Efootball
                }
                if (jogador instanceof JogadorMOBA) {
                    HashMap<String, Integer> personagem = ((JogadorMOBA) jogador).getPersonagemMaisJogado();
                    consola.registarEstatisticaMOBA(jogador, personagem); // Registra as estatísticas de um jogador MOBA
                }
            }
        }
    }
}
