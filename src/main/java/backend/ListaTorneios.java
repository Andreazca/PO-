package backend;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ListaTorneios implements Serializable {
    List<Torneio> lto;

    // Construtor para inicializar a lista de torneios
    public ListaTorneios() {
        lto = new ArrayList<Torneio>();
    }

    // Método para obter a lista de torneios
    public List<Torneio> getLto() {
        return lto;
    }

    // Método para obter a lista de partidas de um torneio específico
    public List<Partida> getPartida(String nomeTorneio) {
        List<Partida> p = null;
        for (Torneio t : lto) {
            if (t.getNome().equals(nomeTorneio)) {
                p = t.getListaPartidas();
            }
        }
        return p;
    }

    // Método para definir a lista de torneios
    public void setLto(List<Torneio> lto) {
        this.lto = lto;
    }

    // Método para adicionar um torneio à lista
    public void adicionarTorneio(Torneio t) {
        lto.add(t);
    }

    // Método para remover um torneio da lista
    public void removerTorneio(Torneio t) {
        lto.remove(t);
    }

    // Método para verificar se um torneio já possui o nome fornecido
    public boolean verificarNome(String nome) {
        for (Torneio t : lto) {
            if (t.getNome().equals(nome)) {
                return false;  // O torneio já existe
            }
        }
        return true;  // O nome não foi encontrado
    }

    // Método para criar um torneio e adicionar à lista
    public Torneio criarTorneio(String nome, String tipo) {
        String id = UUID.randomUUID().toString();  // Gera um ID único para o torneio
        Torneio t = new Torneio(id, nome, tipo);
        lto.add(t);
        return t;  // Retorna o torneio recém-criado
    }

    // Método para listar todos os torneios
    public List<Torneio> ListarTorneios() {
        List<Torneio> torneios = new ArrayList<>();
        if (lto.isEmpty()) {
            return null;  // Retorna null se não houver torneios
        }
        for (Torneio t : lto) {
            torneios.add(t);  // Adiciona os torneios à lista
        }
        return torneios;
    }

    // Método para encontrar um torneio pelo nome
    public Torneio encontrarTorneio(String nome) {
        for (Torneio t : lto) {
            if (t.getNome().equals(nome)) {
                return t;  // Retorna o torneio encontrado
            }
        }
        return null;  // Retorna null se o torneio não for encontrado
    }

    // Método para mudar o nome de um torneio
    public Torneio mudarNome(String nomenovo, String nomevelho) {
        for (Torneio t : lto) {
            if (t.getNome().equals(nomevelho)) {
                t.setNome(nomenovo);  // Atualiza o nome do torneio
                return t;  // Retorna o torneio com o nome atualizado
            }
        }
        return null;  // Retorna null se o torneio não for encontrado
    }

    // Método para listar os torneios de um tipo específico
    public List<Torneio> ListarTorneiosdetipo(String tipo) {
        List<Torneio> torneios = new ArrayList<>();
        if (lto.isEmpty()) {
            return null;  // Retorna null se não houver torneios
        }
        for (Torneio t : lto) {
            if (t.getJogo().equals(tipo)) {
                torneios.add(t);  // Adiciona torneios do tipo específico
            }
        }
        return torneios;
    }

    // Método para inscrever uma equipa em um torneio
    public void inscreverEquipa(String nomeTorneio, Equipa e) {
        for (Torneio t : lto) {
            if (t.getNome().equals(nomeTorneio)) {
                t.adicionarEquipa(e);  // Adiciona a equipa ao torneio
                for (Jogador jogador : e.getJogador()) {
                    jogador.addTorneio(t);  // Adiciona o torneio aos jogadores da equipa
                }
            }
        }
    }

    // Método para listar as equipas inscritas em um torneio
    public List<Equipa> listarEquipas(String nome) {
        List<Equipa> equipa = new ArrayList<>();
        for (Torneio torneio : lto) {
            if (torneio.getNome().equals(nome)) {
                if (torneio.getListaEquipas() == null) {
                    return null;  // Retorna null se não houver equipas no torneio
                } else {
                    List<Equipa> equipas = torneio.getListaEquipas();
                    if (equipas.isEmpty()) {
                        return null;  // Retorna null se não houver equipas
                    }
                    for (Equipa e : equipas) {
                        equipa.add(e);  // Adiciona as equipas à lista
                    }
                    return equipa;
                }
            }
        }
        return null;  // Retorna null se o torneio não for encontrado
    }

    // Método para listar as partidas de um torneio
    public List<Partida> listarPartidas(String nome) {
        List<Partida> partida = new ArrayList<>();
        for (Torneio torneio : lto) {
            if (torneio.getNome().equals(nome)) {
                List<Partida> partidas = torneio.getListaPartidas();
                if (partidas.isEmpty()) {
                    return null;  // Retorna null se não houver partidas
                }
                for (Partida e : partidas) {
                    partida.add(e);  // Adiciona as partidas à lista
                }
                return partida;
            }
        }
        return null;  // Retorna null se o torneio não for encontrado
    }

    // Método para remover uma partida de um torneio
    public boolean removerPartidas(String torneio, Equipa equipaCasa, Equipa equipaFora) {
        for (Torneio t : lto) {
            if (t.getNome().equals(torneio)) {
                List<Partida> partidas = t.getListaPartidas();
                for (Partida p : partidas) {
                    if (p.getEquipaA().equals(equipaCasa)) {
                        if (p.getEquipaB().equals(equipaFora)) {
                            partidas.remove(p);  // Remove a partida entre as equipas
                            return true;  // Retorna true se a partida for removida
                        }
                    }
                }
            }
        }
        return false;  // Retorna false se não encontrar a partida
    }

    // Método para verificar se há uma partida em uma data específica entre duas equipas
    public boolean dataPartida(String equipaA, String equipaB, LocalDateTime data) {
        for (Torneio t : lto) {
            List<Partida> partidas = t.getListaPartidas();
            for (Partida p : partidas) {
                if (p.getEquipaA().getNome().equals(equipaA) || p.getEquipaB().getNome().equals(equipaA)) {
                    if (p.getEquipaA().getNome().equals(equipaB) || p.getEquipaB().getNome().equals(equipaB)) {
                        if (p.getData().toLocalDate().equals(data.toLocalDate())) {
                            return false;  // Retorna false se já houver uma partida marcada
                        }
                        return true;  // Retorna true se a data for disponível
                    }
                }
            }
        }
        return true;  // Retorna true se a partida não existir
    }

    // Cria uma nova partida entre duas equipas em um torneio específico
    public Partida criarPartida(String nomeTorneio, Equipa equipaA, Equipa equipaB, LocalDateTime data) {
        for (Torneio t : lto) {
            if (t.getNome().equals(nomeTorneio)) {
                String id = UUID.randomUUID().toString();
                Partida partida = new Partida(id, equipaA, equipaB, data);
                t.adicionarPartida(partida);
                return partida;
            }
        }
        return null;
    }

    // Encontra uma equipa pelo nome dentro de um torneio
    public Equipa encontrarEquipa(String nomeTorneio, String nomeEquipa) {
        for (Torneio t : lto) {
            if (t.getNome().equals(nomeTorneio)) {
                for (Equipa e : t.getListaEquipas()) {
                    if (e.getNome().equals(nomeEquipa)) {
                        return e;
                    }
                }
            }
        }
        return null;
    }

    // Remove uma equipa e suas partidas de um torneio
    public void removerEquipa(String nomeTorneio, Equipa equipa) {
        for (Torneio t : lto) { // Itera pelos torneios
            if (t.getNome().equals(nomeTorneio)) { // Verifica se é o torneio correto
                t.removerEquipa(equipa); // Remove a equipa do torneio

                // Lista temporária para armazenar as partidas a serem removidas
                List<Partida> partidasParaRemover = new ArrayList<>();

                // Itera pelas partidas e adiciona as que envolvem a equipa à lista temporária
                for (Partida p : t.getListaPartidas()) {
                    if (p.getEquipaA().equals(equipa) || p.getEquipaB().equals(equipa)) {
                        partidasParaRemover.add(p);
                    }
                }

                // Remove as partidas identificadas da lista original
                for (Partida p : partidasParaRemover) {
                    t.removerPartida(p);
                }
            }
        }
    }


    // Verifica se existe uma partida entre duas equipas
    public boolean encontrarPartida(String nomeTorneio, String equipaA, String equipaB) {
        for (Torneio t : lto) {
            if (t.getNome().equals(nomeTorneio)) {
                for (Partida p : t.getListaPartidas()) {
                    if (p.getEquipaA().getNome().equals(equipaA) && p.getEquipaB().getNome().equals(equipaB)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Encontra partidas já terminadas
    public List<Partida> encontrarPartidasAcabadas(String nomeTorneio) {
        for (Torneio t : lto) {
            if (t.getNome().equals(nomeTorneio)) {
                List<Partida> partidas = new ArrayList<>();
                for (Partida partida : t.getListaPartidas()) {
                    if (partida.getData().isBefore(LocalDateTime.now()) && partida.getPontosEquipaA() == 0 && partida.getPontosEquipaB() == 0) {
                        partidas.add(partida);
                    }
                }
                return partidas.isEmpty() ? null : partidas;
            }
        }
        return null;
    }

    // Registra os resultados de uma partida
    public Partida registarResultadosPArtida(String nomeTorneio, String equipaA, String equipaB) {
        for (Torneio t : lto) {
            if (t.getNome().equals(nomeTorneio)) {
                List<Partida> partidas = t.getListaPartidas();
                for (Partida partida : partidas) {
                    if (partida.getEquipaA().getNome().equals(equipaA) && partida.getEquipaB().getNome().equals(equipaB)) {
                        if (partida.getData().isBefore(LocalDateTime.now())) {
                            return partida;
                        }
                    }
                }
            }
        }
        return null;
    }

    // Registra os resultados de uma partida específica
    public Partida resultadoPartida(Partida partida, String nomeTorneio, int resultadoA, int resultadoB) {
        for (Torneio t : lto) {
            if (t.getNome().equals(nomeTorneio)) {
                for (Partida p : t.getListaPartidas()) {
                    if (p.equals(partida)) {
                        p.setResultadoA(resultadoA);
                        p.setResultadoB(resultadoB);
                        p.PontuarEquipas();
                        if (resultadoA == resultadoB) {
                            p.getEquipaA().setEmpates();
                            p.getEquipaB().setEmpates();
                        } else if (resultadoA > resultadoB) {
                            p.getEquipaA().setVitorias();
                            p.getEquipaB().setDerrotas();
                        } else {
                            p.getEquipaA().setDerrotas();
                            p.getEquipaB().setVitorias();
                        }
                        return partida;
                    }
                }
            }
        }
        return null;
    }

    // Obtém a lista de partidas com resultados
    public List<Partida> resultadosPartidas(String nomeTorneio) {
        for (Torneio t : lto) {
            if (t.getNome().equals(nomeTorneio)) {
                List<Partida> partidas = new ArrayList<>();
                for (Partida partida : t.getListaPartidas()) {
                    if (partida.getData().isBefore(LocalDateTime.now()) && (partida.getPontosEquipaA() != 0 || partida.getPontosEquipaB() != 0)) {
                        partidas.add(partida);
                    }
                }
                return partidas.isEmpty() ? null : partidas;
            }
        }
        return null;
    }

    // Obtém a classificação das equipas por pontos
    public String obterClassificacao(String nomeTorneio) {
        HashMap<String, Integer> pontosPorEquipa = new HashMap<>();
        for (Torneio t : lto) {
            if (t.getNome().equals(nomeTorneio)) {
                for (Equipa equipa : t.getListaEquipas()) {
                    pontosPorEquipa.put(equipa.getNome(), 0);
                }
                List<Partida> partidas = t.getListaPartidas();
                for (Partida partida : partidas) {
                    pontosPorEquipa.put(partida.getEquipaA().getNome(), pontosPorEquipa.get(partida.getEquipaA().getNome()) + partida.getPontosEquipaA());
                    pontosPorEquipa.put(partida.getEquipaB().getNome(), pontosPorEquipa.get(partida.getEquipaB().getNome()) + partida.getPontosEquipaB());
                }
                List<HashMap.Entry<String, Integer>> classificacao = new ArrayList<>(pontosPorEquipa.entrySet());
                classificacao.sort((equipa1, equipa2) -> equipa2.getValue().compareTo(equipa1.getValue()));
                StringBuilder resultado = new StringBuilder();
                int i = 0;
                for (HashMap.Entry<String, Integer> pontos : classificacao) {
                    i++;
                    resultado.append(i).append(" ").append(pontos.getKey()).append(" - ").append(pontos.getValue()).append(" pontos\n");
                }
                return resultado.isEmpty() ? null:resultado.toString().trim(); // Remove a última quebra de linha
            }
        }
        return null;
    }


    // Lista os torneios onde uma equipa participou
    public List<Torneio> listaTorneiosEquipa(String nomeEquipa) {
        List<Torneio> torneiosEncontrados = new ArrayList<>();
        for (Torneio torneio : lto) {
            for (Equipa equipaA : torneio.getListaEquipas()) {
                if (equipaA.getNome().equals(nomeEquipa)) {
                    torneiosEncontrados.add(torneio);
                }
            }
        }
        return torneiosEncontrados.isEmpty() ? null : torneiosEncontrados;
    }

    // Obtém as próximas partidas a acontecer
    public List<Partida> proximasJornadas(String nomeTorneio) {
        List<Partida> partidas = new ArrayList<>();
        for (Torneio t : lto) {
            if (t.getNome().equals(nomeTorneio)) {
                for (Partida partida : t.getListaPartidas()) {
                    if (partida.getResultadoA() == 0 && partida.getResultadoB() == 0) {
                        partidas.add(partida);
                    }
                }
            }
        }
        return partidas.isEmpty() ? null : partidas;
    }
    public boolean encontrarTorneioTipo(String nomeTorneio, String jogo) {
        for (Torneio t : lto) {
            if (t.getNome().equals(nomeTorneio)) {
                if (t.getJogo().equals(jogo)) {
                    return true;
                }
            }
        }
        return false;
    }
}