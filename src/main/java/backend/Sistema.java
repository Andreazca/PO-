package backend;

import java.io.*;
import java.time.LocalDateTime;
import java.util.UUID;
import java.time.format.DateTimeFormatter;

public class Sistema implements Serializable {
    private ListaJogadores lj = new ListaJogadores(); // Lista de jogadores
    private ListaTreinadores lt = new ListaTreinadores(); // Lista de treinadores
    private ListaAdministradores la = new ListaAdministradores(); // Lista de administradores
    private ListaEquipas le = new ListaEquipas(); // Lista de equipas
    private ListaTorneios lto = new ListaTorneios(); // Lista de torneios

    // Métodos getter para acessar as listas
    public ListaJogadores getLj() { return lj; }
    public ListaTreinadores getLt() { return lt; }
    public ListaAdministradores getLa() { return la; }
    public ListaEquipas getLe() { return le; }
    public ListaTorneios getLto() { return lto; }

    // Método que inicializa o sistema com dados predefinidos
    public void adicionar() {
        //Criar Administradores
        if (getLa().getAdministrador().isEmpty()) {
            String idAdmi1 = UUID.randomUUID().toString();
            Administrador Admin1 = new Administrador(idAdmi1, "Admin", "admin", "1234");
            // Verifica e adiciona administradores se ainda não existirem
            if (!getLa().verificaremail(Admin1.getEmail())) {
                getLa().adicionarAdministrador(Admin1);
            }
        }

        //Criar Torneio
        Torneio t = getLto().encontrarTorneio("Torneio_Efootball"); // Verifica se o torneio já existe
        if (t == null) {
            t = getLto().criarTorneio("Torneio_Efootball", "Efootball"); // Cria o torneio caso não exista
        }
        String nome = t.getNome();

        // Criar Treinadores e Equipas
        if (getLt().getLt().isEmpty()) {
            String idTreinador1 = UUID.randomUUID().toString();
            String idTreinador2 = UUID.randomUUID().toString();
            String idTreinador3 = UUID.randomUUID().toString();
            Treinador treinador1 = new Treinador(idTreinador1, "Treiandor1", "treiandor1", "1234");
            Treinador treinador2 = new Treinador(idTreinador2, "Treiandor2", "treiandor2", "1234");
            Treinador treinador3 = new Treinador(idTreinador3, "Treiandor3", "treiandor3", "1234");
            // Verifica e adiciona treinadores, além de criar equipas associadas
            if (!getLt().verificaremail(treinador1.getEmail())) {
                getLt().adicionartreinador(treinador1);
            }
            if (!getLe().verificarnome("Equipa1")) {
                Equipa equipa1 = getLe().criarEquipaEfootball("Equipa1", treinador1);
                getLto().inscreverEquipa(nome, equipa1);
            }

            if (!getLt().verificaremail(treinador2.getEmail())) {
                getLt().adicionartreinador(treinador2);
            }
            if (!getLe().verificarnome("Equipa2")) {
                Equipa equipa2 = getLe().criarEquipaEfootball("Equipa2", treinador2);
                getLto().inscreverEquipa(nome, equipa2);
            }

            if (!getLt().verificaremail(treinador3.getEmail())) {
                getLt().adicionartreinador(treinador3);
            }
            if (!getLe().verificarnome("Equipa3")) {
                Equipa equipa3 = getLe().criarEquipaEfootball("Equipa3", treinador3);
                getLto().inscreverEquipa(nome, equipa3);
            }
        }

        // Criar Jogadores
        if (getLj().getJogadores().isEmpty()) {
            String idJogador1 = UUID.randomUUID().toString();
            String idJogador2 = UUID.randomUUID().toString();
            JogadorEFootball jogador1 = new JogadorEFootball(idJogador1, "Jogador1", "jogador1", "1234", "Defesa");
            JogadorEFootball jogador2 = new JogadorEFootball(idJogador2, "Jogador2", "jogador2", "1234", "Guarda-Redes");
            // Verifica e adiciona jogadores se não existirem
            if (!getLj().verificarnickname(jogador1.getNickname())) {
                getLj().adicionarjogador(jogador1);
            }
            if (!getLj().verificarnickname(jogador2.getNickname())) {
                getLj().adicionarjogador(jogador2);
            }
        }

        // Criar Partidas
        if (getLto().getPartida(nome).isEmpty()) { // Verifica se já existem partidas para o torneio
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            // Definindo as datas e horários das partidas
            LocalDateTime dataHoraJogo = LocalDateTime.parse("15/10/2024 10:30", formato);
            LocalDateTime dataHoraJogos = LocalDateTime.parse("15/05/2024 10:30", formato);
            LocalDateTime dataHoraJog = LocalDateTime.parse("15/07/2024 10:30", formato);
            // Criação das partidas entre as equipas
            Equipa equipa1 = getLe().encontrarEquipaNome("Equipa1");
            Equipa equipa2 = getLe().encontrarEquipaNome("Equipa2");
            Equipa equipa3 = getLe().encontrarEquipaNome("Equipa3");

            if (equipa1 != null && equipa2 != null) {
                getLto().criarPartida(nome, equipa1, equipa2, dataHoraJogo);
                getLto().criarPartida(nome, equipa2, equipa1, dataHoraJogos);
            }
            if (equipa3 != null && equipa1 != null) {
                getLto().criarPartida(nome, equipa3, equipa1, dataHoraJog);
            }
        }
    }

    // Método para salvar o estado atual do sistema em um arquivo
    public void gravarFicheiro() throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream("SistemaEstado")))) {
            out.writeObject(this); // Serializa o objeto    Sistema
            System.out.println("Estado do sistema salvo com sucesso.");
        } catch (IOException ex) {
            System.err.println("Erro ao gravar o estado do sistema: " + ex.getMessage());
        }
    }

    // Método para carregar o estado do sistema a partir de um arquivo
    public void lerFicheiro() throws IOException {
        try (ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream("SistemaEstado")))) {
            Object obj = in.readObject();
            if (obj instanceof Sistema) { // Verifica se o objeto lido é do tipo Sistema
                Sistema sistemaLido = (Sistema) obj;
                // Atualiza os dados do sistema com os valores carregados
                this.lj = sistemaLido.getLj() ;
                this.lt = sistemaLido.getLt();
                this.la = sistemaLido.getLa();
                this.le = sistemaLido.getLe();
                this.lto = sistemaLido.getLto();
                System.out.println("\nEstado do sistema carregado com sucesso.");
            } else {
                System.out.println("\nErro: O ficheiro não contém um objeto do tipo Sistema.");
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("\nErro ao ler o estado do sistema: " + ex.getMessage());
        }
    }

    // Método para realizar login e retornar o tipo de usuário (Administrador, Treinador ou Jogador)
    public int login(String email, String password) {
        if (getLa().validarlogin(email, password)) {
            return 1; // Administrador
        } else if (getLt().validarlogin(email, password)) {
            return 2; // Treinador
        } else if (getLj().validarlogin(email, password)) {
            return 3; // Jogador
        }
        return 0; // Falha no login
    }
}
