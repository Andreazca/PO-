
package frontend;

import backend.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.stream.Collectors;
public class MenuAdministrador extends MenuPrincipal{
    private Consola consola=new Consola();
    private Sistema sistema;
    private MenuPrincipal menu;
    public MenuAdministrador(Sistema sistema, MenuPrincipal menu){
       this.sistema=sistema;
       this.menu=menu;
    }
      /* ADMIN */
    // Menu do administrador
    public void menuAdmin(String id) throws IOException {
        System.out.println("\nMENU ADMINISTRADOR");
        System.out.println("1. Adicionar/apagar utilizadores");
        System.out.println("2. Criar e gerir torneios");
        System.out.println("3. Agendar partidas entre as equipas inscritas");
        System.out.println("4. Registar os resultados das partidas e atualizar as classificações.");
        System.out.println("5. Acompanhar as estatísticas e resultados dos torneios");
        System.out.println("6. Sair");
        int op = consola.passarStringparaint();  // Lê a opção do administrador
        switch (op) {
            case 1:
                System.out.println("\nMENU ADICIONAR E APAGAR UTILIZADORES");
                System.out.println("1. Adicionar utilizador");
                System.out.println("2. Apagar utilizador");
                System.out.println("3. Sair");
                int opcao1 = consola.passarStringparaint();  // Lê a opção de adicionar ou apagar usuário
                switch (opcao1) {
                    case 1:
                        MenuRegistarUser(id);  // Chama o menu para registrar um usuário
                        break;
                    case 2:
                        menuApagarUser(id);  // Chama o menu para apagar um usuário
                        break;
                    case 3:
                        menuAdmin(id);  // Retorna ao menu principal do administrador
                        break;
                    default:
                        System.out.println("\nFunção não reconhecida.");  // Caso a opção seja inválida
                        menuAdmin(id);  // Retorna ao menu principal do administrador
                }
                break;
            case 2:
                menuCriarGerirTorneios(id);  // Chama o menu para criar e gerir torneios
                break;
            case 3:
                menuAgendarPartidasEquipas(id);  // Chama o menu para agendar partidas
                break;
            case 4:
                menuRegistarResultadosdasPartidas(id);  // Chama o menu para registar os resultados
                break;
            case 5:
                menuClassificacaoResultados(id);  // Chama o menu para ver classificações e resultados
                break;
            case 6:
               menu.logout();  // Chama o método de logout para sair do sistema
                break;
            default:
                System.out.println("\nFunção não reconhecida.");  // Caso a opção seja inválida
                menuAdmin(id);  // Retorna ao menu principal do administrador
        }
    }

    // Menu para registrar usuários
    public void MenuRegistarUser(String id) throws IOException {
        System.out.println("\nMENU REGISTAR UTILIZADORES");
        System.out.println("1. Treinador");
        System.out.println("2. Jogador");
        System.out.println("3. Sair");
        System.out.println("Escolha a sua Função: ");
        Scanner sc = new Scanner(System.in);
        int op = consola.passarStringparaint();  // Lê a opção de tipo de usuário
        switch (op) {
            case 1:
                System.out.println("\nDigite o seu nome");
                String nomeTreinador = sc.next();  // Lê o nome do treinador
                System.out.println("\nDigite o seu email");
                String email = sc.next();  // Lê o email do treinador
                System.out.println("\nDigite a sua password");
                String passTreinador = sc.next();  // Lê a senha do treinador
                if (sistema.getLt().verificaremail(email)) {
                    System.out.println("\nO email já existe");  // Verifica se o email já está registado
                    MenuRegistarUser(id);  // Chama novamente o menu de registo
                } else {
                    sistema.getLt().criarTreinador(nomeTreinador, email, passTreinador);  // Cria o treinador
                    System.out.println("\nO user foi criado com sucesso");
                    menuAdmin(id);  // Retorna ao menu do administrador
                }
                break;
            /* Jogador */
            case 2:
                System.out.println("\n1. Jogador EFootball");
                System.out.println("2. Jogador FPS");
                System.out.println("3. Jogador MOBA");
                System.out.println("4. Sair");
                System.out.println("Escolha a sua Função: ");
                int opcao = consola.passarStringparaint();  // Lê a opção de tipo de jogador
                switch (opcao) {
                    case 1:
                        // Processamento para registar jogador EFootball
                        System.out.println("\nDigite o seu nome completo");
                        String nomeJogador = sc.next();
                        System.out.println("\nDigite o seu nickname");
                        String nickname = sc.next();
                        System.out.println("\nDigite a sua password");
                        String passJogador = sc.next();
                        if (sistema.getLj().verificarnickname(nickname)) {
                            System.out.println("\nO nickname já existe");
                            menuAdmin(id);

                        } else {
                            System.out.println("\nEscolha a posição do jogador");
                            System.out.println("1. Guarda-Redes");
                            System.out.println("2. Defesa");
                            System.out.println("3. Médio");
                            System.out.println("4. Avançado");
                            int opcao1 = consola.passarStringparaint();
                            switch (opcao1) {
                                case 1:
                                    String posicao1="Guarda-Redes";
                                    sistema.getLj().criarJogadorEfootball(nomeJogador, nickname, passJogador,posicao1);
                                    System.out.println("\nO user foi criado com sucesso");
                                    menuAdmin(id);
                                case 2:
                                    String posicao2="Defesa";
                                    sistema.getLj().criarJogadorEfootball(nomeJogador, nickname, passJogador,posicao2);
                                    System.out.println("\nO user foi criado com sucesso");
                                    menuAdmin(id);

                                case 3:
                                    String posicao3="Médio";
                                    sistema.getLj().criarJogadorEfootball(nomeJogador, nickname, passJogador,posicao3);
                                    System.out.println("\nO user foi criado com sucesso");
                                    menuAdmin(id);

                                case 4:
                                    String posicao4 ="Avançado";
                                    sistema.getLj().criarJogadorEfootball(nomeJogador, nickname, passJogador, posicao4);
                                    System.out.println("\nO user foi criado com sucesso");
                                    menuAdmin(id);

                            }

                        }

                        break;
                    case 2:
                        // Processamento para registar jogador FPS
                        System.out.println("\nDigite o seu nome completo");
                        String nomeJogadorFPS = sc.next();
                        System.out.println("\nDigite o seu nickname");
                        String nicknameFPS = sc.next();
                        System.out.println("\nDigite a sua password");
                        String passJogadorFPS = sc.next();
                        if (sistema.getLj().verificarnickname(nicknameFPS)) {
                            System.out.println("\nO nickname já existe");
                            menuAdmin(id);

                        } else {
                            sistema.getLj().criarJogadorFPS(nomeJogadorFPS, nicknameFPS, passJogadorFPS);
                            System.out.println("\nO user foi criado com sucesso");
                            menuAdmin(id);

                        }
                        break;
                    case 3:
                        // Processamento para registar jogador MOBA
                        System.out.println("\nDigite o seu nome completo");
                        String nomeJogadorMOBA = sc.next();
                        System.out.println("\nDigite o seu nickname");
                        String nicknameMOBA = sc.next();
                        System.out.println("\nDigite a sua password");
                        String passJogadorMOBA = sc.next();
                        if (sistema.getLj().verificarnickname(nicknameMOBA)) {
                            System.out.println("\nO nickname já existe");
                            menuAdmin(id);

                        } else {
                            sistema.getLj().criarJogadorMOBA(nomeJogadorMOBA, nicknameMOBA, passJogadorMOBA);
                            System.out.println("\nO user foi criado com sucesso");
                            menuAdmin(id);
                        }
                        break;
                    case 4:
                        MenuRegistarUser(id);  // Retorna ao menu de registo
                    default:
                        System.out.println("\nFunção não reconhecida.");  // Caso a opção seja inválida
                        MenuRegistarUser(id);  // Retorna ao menu de registo
                }
                break;
            case 3:
                System.out.println("\nA sair do sistema.");
                menuAdmin(id);  // Retorna ao menu do administrador
                break;
            default:
                System.out.println("\nFunção não reconhecida.");  // Caso a opção seja inválida
                MenuRegistarUser(id);  // Retorna ao menu de registo
        }
    }

    // Menu para apagar usuários
    public void menuApagarUser(String id) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPretende apagar um jogador ou um treinador?");
        System.out.println("1. Jogador");
        System.out.println("2. Treinador");
        System.out.println("3. Sair");
        System.out.println("Escolha a sua opcao: ");
        int opcao = consola.passarStringparaint();  // Lê a opção de apagar jogador ou treinador
        switch (opcao) {
            case 1:
                System.out.println("\nNão pode remover um jogador que está a pertencer numa equipa");
                // Processamento para remover um jogador
                if(sistema.getLj().listarJogadores()==null){
                    System.out.println("Não existe jogador para remover");
                    menuAdmin(id);
                }else {
                    System.out.println(sistema.getLj().listarJogadores().stream()
                            .map(Jogador::toString) // Usa o método toString() de Jogador
                            .collect(Collectors.joining("\n")));
                    System.out.println("\nSelecione o nickname do jogador que pretende remover");
                    String nickname = sc.next();
                    if(sistema.getLj().encontrarJogador(nickname)==null){
                        System.out.println("Não existe esse jogador");
                        menuAdmin(id);
                    }
                    System.out.println("\nTem a certeza que quer remover o user?");
                    System.out.println("1. Sim");
                    System.out.println("2. Nao");
                    int i= consola.passarStringparaint();
                    switch (i){
                        case 1:
                            sistema.getLj().removerJogador(sistema.getLj().encontrarJogador(nickname));
                            System.out.println("\nO user foi removido com sucesso");
                            menuAdmin(id);
                            break;
                        case 2:
                            menuAdmin(id);
                            break;
                        default:
                            System.out.println("\nFunção não reconhecida.");
                            menuAdmin(id);
                            break;
                    }}
            case 2:
                // Processamento para remover um treinador
                if(sistema.getLt().listarTreinadores()==null){
                    System.out.println("\nNão existe treinador para remover");
                    menuAdmin(id);
                }else{
                    System.out.println(sistema.getLt().listarTreinadores().stream().map(Treinador::toString) // Usa o método toString() de Treinador
                            .collect(Collectors.joining("\n")));
                    System.out.println("\nSelecione o email do treinador que pretende remover");
                    String email = sc.next();
                    if (sistema.getLt().encontrarTreinador(email)!=null) {


                        System.out.println(sistema.getLt().encontrarTreinador(email));
                        System.out.println("\nTem a certeza que quer remover o user?");
                        System.out.println("1. Sim");
                        System.out.println("2. Nao");
                        int o = consola.passarStringparaint();
                        switch (o) {
                            case 1:
                                sistema.getLt().removerTreinador(sistema.getLt().encontrarTreinador(email));
                                System.out.println("\nO user foi removido com sucesso");
                                menuAdmin(id);
                                break;
                            case 2:
                                menuAdmin(id);
                                break;
                            default:
                                System.out.println("\nFunção não reconhecida.");
                                menuAdmin(id);
                                break;


                        }}else{
                        System.out.println("\nEsse treinador nao existe");
                        menuAdmin(id);}
                }
            case 3:
                menuAdmin(id);
                break;

            default:
                System.out.println("\nFunção não reconhecida.");
                menuApagarUser(id);
        }
    }



    // Função para mostrar o menu principal de criação e gestão de torneios
    public void menuCriarGerirTorneios(String id) throws IOException {
        System.out.println("\nMENU CRIAR GERIR TORNEIOS");
        System.out.println("1. Criar Torneio");
        System.out.println("2. Gerir Torneio");
        System.out.println("3. Sair");
        System.out.println("Escolha a sua opcao: ");
        int opcao = consola.passarStringparaint();  // Captura a opção do usuário
        switch (opcao) {
            case 1:
                menuCriarTorneio(id);  // Chama o menu para criar torneios
                break;
            case 2:
                menuGerirTorneio(id);  // Chama o menu para gerir torneios
                break;
            case 3:
                menuAdmin(id);  // Volta ao menu de administração
                break;
            default:
                System.out.println("\nFunção não reconhecida.");
                menuAdmin(id);  // Se a opção não for válida, retorna ao menu de administração
                break;
        }
    }

    // Função para criar torneios de diferentes tipos
    public void menuCriarTorneio(String id) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nMENU CRIAR TORNEIO");
        System.out.println("1. Torneio Efootball");
        System.out.println("2. Torneio FPS");
        System.out.println("3. Torneio MOBA");
        System.out.println("4. Sair");
        System.out.println("Escolha a sua opcao: ");
        int opcao = consola.passarStringparaint();  // Captura a opção do usuário
        switch (opcao) {
            case 1:
                String tipoEfootball = "Efootball";
                System.out.println("\nDigite o nome do Torneio:");
                String nomeEfootball = sc.next();  // Captura o nome do torneio Efootball
                if (sistema.getLto().verificarNome(nomeEfootball)) {
                    sistema.getLto().criarTorneio(nomeEfootball, tipoEfootball);  // Cria o torneio
                    System.out.println("\nTorneio criado com sucesso");
                    menuCriarGerirTorneios(id);  // Retorna ao menu principal de criação e gestão de torneios
                } else {
                    System.out.println("\nEsse nome já existe");
                    menuCriarTorneio(id);  // Se o nome já existe, tenta novamente
                }
                break;
            case 2:
                String tipoFPS = "FPS";
                System.out.println("\nDigite o nome do Torneio:");
                String nomeFPS = sc.next();  // Captura o nome do torneio FPS
                if (sistema.getLto().verificarNome(nomeFPS)) {
                    sistema.getLto().criarTorneio(nomeFPS, tipoFPS);  // Cria o torneio
                    System.out.println("\nTorneio criado com sucesso");
                    menuCriarGerirTorneios(id);  // Retorna ao menu principal de criação e gestão de torneios
                } else {
                    System.out.println("\nEsse nome já existe");
                    menuCriarTorneio(id);  // Se o nome já existe, tenta novamente
                }
                break;
            case 3:
                String tipoMOBA = "MOBA";
                System.out.println("\nDigite o nome do Torneio:");
                String nomeMOBA = sc.next();  // Captura o nome do torneio MOBA
                if (sistema.getLto().verificarNome(nomeMOBA)) {
                    sistema.getLto().criarTorneio(nomeMOBA, tipoMOBA);  // Cria o torneio
                    System.out.println("\nTorneio criado com sucesso");
                    menuCriarGerirTorneios(id);  // Retorna ao menu principal de criação e gestão de torneios
                } else {
                    System.out.println("\nEsse nome já existe");
                    menuCriarTorneio(id);  // Se o nome já existe, tenta novamente
                }
                break;
            case 4:
                menuCriarGerirTorneios(id);  // Retorna ao menu principal de criação e gestão de torneios
                break;
            default:
                System.out.println("\nFunção não reconhecida.");
                menuCriarTorneio(id);  // Se a opção não for válida, tenta novamente
                break;
        }
    }

    // Função para mostrar o menu de gestão de torneios
    public void menuGerirTorneio(String id) throws IOException {
        System.out.println("\nMENU GERIR TORNEIO");
        System.out.println("1. Mudar o nome do Torneio");
        System.out.println("2. Apagar Torneio");
        System.out.println("3. Apagar partidas");
        System.out.println("4. Apagar equipas que estão a participar no torneio");
        System.out.println("5. Sair");
        System.out.println("Escolha a sua opcao: ");
        int opcao = consola.passarStringparaint();  // Captura a opção do usuário
        switch (opcao) {
            case 1:
                menuMudarNomeTorneio(id);  // Chama a função para mudar o nome do torneio
                break;
            case 2:
                menuApagarTorneio(id);  // Chama a função para apagar o torneio
                break;
            case 3:
                menuRemoverPartidasTorneio(id);  // Chama a função para remover partidas do torneio
                break;
            case 4:
                menuApagarEquipaTorneio(id);  // Chama a função para apagar equipas do torneio
                break;
            case 5:
                menuAdmin(id);  // Retorna ao menu de administração
                break;
            default:
                System.out.println("\nFunção não reconhecida.");
                menuGerirTorneio(id);  // Se a opção não for válida, tenta novamente
                break;
        }
    }

    // Função para mudar o nome de um torneio
    public void menuMudarNomeTorneio(String id) throws IOException {
        Scanner sc = new Scanner(System.in);
        if (sistema.getLto().ListarTorneios() != null) {
            System.out.println(sistema.getLto().ListarTorneios().stream().map(Torneio::toString) // Usa o método toString() de Torneio para representar cada torneio
                    .collect(Collectors.joining("\n")));  // Lista todos os torneios
            System.out.println("\nEscreva o nome do Torneio que pretende alterar o nome");
            String nomeTorneio = sc.next();  // Captura o nome do torneio a ser alterado
            if (sistema.getLto().encontrarTorneio(nomeTorneio) == null) {
                System.out.println("\nEsse Torneio não existe");
                menuMudarNomeTorneio(id);  // Se o torneio não existir, tenta novamente
            } else {
                System.out.println("\nDigite o novo nome do Torneio");
                String nomeTorneio2 = sc.next();  // Captura o novo nome para o torneio
                System.out.println(sistema.getLto().mudarNome(nomeTorneio2, nomeTorneio));  // Muda o nome do torneio
                menuGerirTorneio(id);  // Retorna ao menu de gestão de torneios
            }
        } else {
            System.out.println("\nNão existem torneios de momento crie um primeiro");
            menuAdmin(id);  // Se não houver torneios, retorna ao menu de administração
        }
    }

    // Função para apagar um torneio
    public void menuApagarTorneio(String id) throws IOException {
        Scanner sc = new Scanner(System.in);
        if (sistema.getLto().ListarTorneios() != null) {
            System.out.println(sistema.getLto().ListarTorneios().stream().map(Torneio::toString) // Usa o método toString() de Torneio para representar cada torneio
                    .collect(Collectors.joining("\n")));  // Lista todos os torneios
            System.out.println("\nEscreva o nome do Torneio");
            String nomeTorneio = sc.next();  // Captura o nome do torneio a ser apagado
            if (sistema.getLto().encontrarTorneio(nomeTorneio) == null) {
                System.out.println("\nEsse torneio não existe");
                menuGerirTorneio(id);  // Se o torneio não existir, retorna ao menu de gestão
            } else {
                System.out.println("\nTem a certeza que deseja apagar o torneio");
                System.out.println("1. Sim");
                System.out.println("2. Nao");
                System.out.println("Escolha a sua opcao: ");
                int opcao = consola.passarStringparaint();  // Captura a escolha do usuário
                switch (opcao) {
                    case 1:
                        sistema.getLto().removerTorneio(sistema.getLto().encontrarTorneio(nomeTorneio));  // Remove o torneio
                        System.out.println("\nTorneio apagado com sucesso");
                        menuGerirTorneio(id);  // Retorna ao menu de gestão de torneios
                        break;
                    case 2:
                        menuGerirTorneio(id);  // Retorna ao menu de gestão de torneios se o usuário escolher não apagar
                        break;
                    default:
                        System.out.println("\nFunção não reconhecida.");
                        menuApagarTorneio(id);  // Se a opção não for válida, tenta novamente
                        break;
                }
            }
        } else {
            System.out.println("\nNão existem torneios de momento crie um primeiro");
            menuAdmin(id);  // Se não houver torneios, retorna ao menu de administração
        }
    }



    // Método para remover partidas de um torneio
    public void menuRemoverPartidasTorneio(String id) throws IOException {
        Scanner sc = new Scanner(System.in);
        // Verifica se existem torneios registados no sistema
        if(sistema.getLto().ListarTorneios() != null){
            // Exibe a lista de torneios
            System.out.println(sistema.getLto().ListarTorneios().stream().map(Torneio::toString) // Usa o método toString() de Torneio para representar cada torneio
                    .collect(Collectors.joining("\n")));  // Lista todos os torneios
            System.out.println("\nEscreva o nome do Torneio");
            String nomeTorneio = sc.next();

            // Verifica se o torneio existe
            if(sistema.getLto().encontrarTorneio(nomeTorneio) == null){
                System.out.println("\nEsse Torneio não existe");
                menuGerirTorneio(id); // Chama o menu de gestão de torneio novamente
            } else {
                // Verifica se há partidas para o torneio
                if(sistema.getLto().listarPartidas(nomeTorneio) != null){
                    // Exibe as partidas do torneio
                    System.out.println(sistema.getLto().listarPartidas(nomeTorneio).stream().map(Partida::toString) // Usa o método toString() da Partida para representar cada partida
                            .collect(Collectors.joining("\n")));  // Lista todas as partidas
                    System.out.println("\nDigite o nome da Equipa da casa");
                    String nomeEquipaA = sc.next();
                    System.out.println("\nDigite o nome da Equipa de fora");
                    String nomeEquipaB = sc.next();

                    // Verifica se as equipes existem
                    if(sistema.getLe().encontrarEquipaNome(nomeEquipaA) != null && sistema.getLe().encontrarEquipaNome(nomeEquipaB) != null){
                        // Tenta remover a partida
                        if(sistema.getLto().removerPartidas(nomeTorneio, sistema.getLe().encontrarEquipaNome(nomeEquipaA), sistema.getLe().encontrarEquipaNome(nomeEquipaB))){
                            System.out.println("\nPartida removida com sucesso");
                            menuAdmin(id); // Volta ao menu do administrador
                        } else {
                            System.out.println("\nEssa partida nao existe");
                            menuGerirTorneio(id); // Volta ao menu de gestão de torneio
                        }
                    } else {
                        System.out.println("\nUma das Equipas não existe");
                        menuGerirTorneio(id); // Volta ao menu de gestão de torneio
                    }
                } else {
                    System.out.println("Não existem partidas para remover");
                    menuGerirTorneio(id); // Volta ao menu de gestão de torneio
                }
            }
        } else {
            System.out.println("\nNão existem torneios de momento, crie um primeiro");
            menuAdmin(id); // Volta ao menu do administrador
        }
    }

    // Método para apagar uma equipa de um torneio
    public void menuApagarEquipaTorneio(String id) throws IOException {
        Scanner sc = new Scanner(System.in);
        if(sistema.getLto().ListarTorneios() != null){
            System.out.println(sistema.getLto().ListarTorneios().stream().map(Torneio::toString) // Usa o método toString() de Torneio para representar cada torneio
                    .collect(Collectors.joining("\n")));  // Lista todos os torneios
            System.out.println("\nEscreva o nome do Torneio");
            String nomeTorneio = sc.next();

            // Verifica se o torneio existe
            if(sistema.getLto().encontrarTorneio(nomeTorneio) == null) {
                System.out.println("\nEsse Torneio não existe");
                menuGerirTorneio(id); // Chama o menu de gestão de torneio
            } else {
                // Verifica se existem equipas no torneio
                if(sistema.getLto().listarEquipas(nomeTorneio) != null){
                    System.out.println(sistema.getLto().listarEquipas(nomeTorneio).stream().map(Equipa::toString) // Usa o método toString() de Equipa para representar cada equipa
                            .collect(Collectors.joining("\n")));  // Lista todas as equipas
                    System.out.println("\nDigite o nome da Equipa que quer remover");
                    String nomeEquipa = sc.next();

                    // Verifica se a equipa está no torneio
                    if(sistema.getLto().encontrarEquipa(nomeTorneio, nomeEquipa) == null){
                        System.out.println("\nEssa equipa não existe no Torneio");
                        menuGerirTorneio(id); // Volta ao menu de gestão de torneio
                    } else {
                        // Pergunta se o usuário tem certeza sobre a remoção da equipa
                        System.out.println("\nTem a certeza que deseja apagar a Equipa");
                        System.out.println("1. Sim");
                        System.out.println("2. Nao");
                        System.out.println("Escolha a sua opcao: ");
                        int opcao = consola.passarStringparaint();

                        switch (opcao) {
                            case 1:
                                // Remove a equipa
                                sistema.getLto().removerEquipa(nomeTorneio, sistema.getLto().encontrarEquipa(nomeTorneio, nomeEquipa));
                                System.out.println("\nEquipa apagada com sucesso");
                                menuGerirTorneio(id); // Volta ao menu de gestão de torneio
                                break;

                            case 2:
                                menuGerirTorneio(id); // Volta ao menu de gestão de torneio
                                break;

                            default:
                                System.out.println("\nFunção não reconhecida.");
                                menuApagarEquipaTorneio(id); // Repete o processo
                                break;
                        }
                    }
                } else {
                    System.out.println("\nNão existem equipas no Torneio");
                    menuGerirTorneio(id); // Volta ao menu de gestão de torneio
                }
            }
        } else {
            System.out.println("\nNão existem torneios de momento, crie um primeiro");
            menuAdmin(id); // Volta ao menu do administrador
        }
    }

    // Método para agendar partidas entre equipas
    public void menuAgendarPartidasEquipas(String id) throws IOException {
        Scanner sc = new Scanner(System.in);
        if(sistema.getLto().ListarTorneios() != null){
            System.out.println(sistema.getLto().ListarTorneios().stream().map(Torneio::toString) // Usa o método toString() de Torneio para representar cada torneio
                    .collect(Collectors.joining("\n")));  // Lista todos os torneios
            System.out.println("\nEscreva o nome do Torneio que pretende agendar partidas");
            String nomeTorneio = sc.next();
            if(sistema.getLto().encontrarTorneio(nomeTorneio) == null) {
                System.out.println("Esse torneio não existe");
                menuAdmin(id);
            }
            if (sistema.getLto().listarPartidas(nomeTorneio)== null) {
                System.out.println("Não existem partidas");
            }else {
                System.out.println(sistema.getLto().listarPartidas(nomeTorneio).stream().map(Partida::toString) // Usa o método toString() da Partida para representar cada partida
                        .collect(Collectors.joining("\n")));  // Lista todas as partidas
            }
            // Verifica se existem equipas no torneio
            if(sistema.getLto().listarEquipas(nomeTorneio) != null){
                System.out.println(sistema.getLto().listarEquipas(nomeTorneio).stream().map(Equipa::toString) // Usa o método toString() da Equipa para representar cada equipa
                        .collect(Collectors.joining("\n")));  // Lista todas as equipas
                System.out.println("\nEscolhe a primeira equipa que quer na partida");
                String nomeEquipaA = sc.next();

                // Verifica se a primeira equipa existe
                if(sistema.getLe().encontrarEquipaNome(nomeEquipaA) == null){
                    System.out.println("\nEssa equipa não existe");
                    menuAgendarPartidasEquipas(id); // Repete o processo
                } else {
                    System.out.println("\nEscolhe a segunda equipa que quer na partida");
                    String nomeEquipaB = sc.next();

                    // Verifica se a segunda equipa existe e se não são as mesmas
                    if(nomeEquipaA.equals(nomeEquipaB)){
                        System.out.println("\nNão pode criar uma partida com duas equipas iguais");
                        menuCriarGerirTorneios(id); // Volta ao menu de criação/gestão de torneios
                    }
                    if(sistema.getLe().encontrarEquipaNome(nomeEquipaB) == null){
                        System.out.println("\nEssa equipa não existe");
                        menuAgendarPartidasEquipas(id); // Repete o processo
                    } else {
                        // Verifica se a partida já foi agendada
                        if (sistema.getLto().encontrarPartida(nomeTorneio, nomeEquipaA, nomeEquipaB)){
                            LocalDateTime data = consola.passarStringParaData();

                            // Verifica se a data já está disponível
                            if (sistema.getLto().dataPartida(nomeEquipaA, nomeEquipaB, data)){
                                System.out.println(sistema.getLto().criarPartida(nomeTorneio, sistema.getLe().encontrarEquipaNome(nomeEquipaA), sistema.getLe().encontrarEquipaNome(nomeEquipaB), data));
                                menuAdmin(id); // Volta ao menu do administrador
                            } else {
                                System.out.println("\nUma das equipas já tem um jogo marcado para essa data");
                                menuGerirTorneio(id); // Volta ao menu de gestão de torneio
                            }
                        } else {
                            System.out.println("\nEssa partida já existe");
                            menuCriarGerirTorneios(id); // Volta ao menu de criação/gestão de torneios
                        }
                    }
                }
            } else {
                System.out.println("\nNão existe torneio ou equipas no torneio");
                menuAdmin(id); // Volta ao menu do administrador
            }
        } else {
            System.out.println("\nNão existem torneios para poder agendar, crie primeiro um torneio");
            menuAdmin(id); // Volta ao menu de criação de torneio
        }
    }

    // Método para registrar os resultados das partidas
    public void menuRegistarResultadosdasPartidas(String id) throws IOException {
        Scanner sc = new Scanner(System.in);
        if(sistema.getLto().ListarTorneios() != null) {
            System.out.println(sistema.getLto().ListarTorneios().stream().map(Torneio::toString) // Usa o método toString() de Torneio para representar cada torneio
                    .collect(Collectors.joining("\n")));  // Lista todos os torneios);
            System.out.println("\nEscreva o nome do Torneio que pretende registar o resultado das partidas");
            String nomeTorneio = sc.next();

            // Verifica se o torneio existe
            if(sistema.getLto().encontrarTorneio(nomeTorneio) == null) {
                System.out.println("\nEsse Torneio não existe");
                menuAdmin(id); // Volta ao menu do administrador
            } else {
                // Verifica se há partidas acabadas para o torneio
                if(sistema.getLto().encontrarPartidasAcabadas(nomeTorneio) == null) {
                    System.out.println("\nAinda não existem partidas para registar resultados");
                    menuAdmin(id); // Volta ao menu do administrador
                } else {
                    System.out.println(sistema.getLto().encontrarPartidasAcabadas(nomeTorneio).stream().map(Partida::toString) // Usa o método toString() da Partida para representar cada partida
                            .collect(Collectors.joining(", ")));  // Lista todas as partidas
                    System.out.println("\nEscolha a partida que pretende registar os resultados");
                    System.out.println("\nEscreva o nome equipa da casa");
                    String nomeEquipaA = sc.next();
                    System.out.println("\nEscreva o nome equipa de fora");
                    String nomeEquipaB = sc.next();

                    // Solicita o resultado da partida
                    System.out.println("\nEscreva o resultado da partida");
                    System.out.println("\nQuantos pontos fez a equipa de casa");
                    int resultadoA = consola.passarStringparaint();
                    System.out.println("\nQuantos pontos fez a equipa de fora");
                    int resultadoB = consola.passarStringparaint();

                    // Tenta registar o resultado
                    if (sistema.getLto().registarResultadosPArtida(nomeTorneio, nomeEquipaA, nomeEquipaB) == null){
                        System.out.println("\nNão foi possivel colocar o resultado da partida");
                        menuRegistarResultadosdasPartidas(id); // Repete o processo
                    } else {
                        // Registra as estatísticas das equipes e jogadores
                        sistema.getLj().registarEstatisticas(nomeEquipaA, resultadoA, resultadoB, nomeEquipaB);
                        sistema.getLj().registarEstatisticasJogadores(nomeEquipaA);
                        sistema.getLj().registarEstatisticasJogadores(nomeEquipaB);
                        System.out.println("\nResultado da partida registado com sucesso" + sistema.getLto().resultadoPartida(sistema.getLto().registarResultadosPArtida(nomeTorneio, nomeEquipaA, nomeEquipaB), nomeTorneio, resultadoA, resultadoB));
                        menuAdmin(id); // Volta ao menu do administrador
                    }
                }
            }
        }else{
            System.out.println("Não existem torneios");
            menuAdmin(id);
        }
    }

    // Método para visualizar a classificação e resultados das partidas
    public void menuClassificacaoResultados(String id) throws IOException {
        Scanner sc = new Scanner(System.in);
        if(sistema.getLto().ListarTorneios() != null) {
            System.out.println(sistema.getLto().ListarTorneios().stream().map(Torneio::toString) // Usa o método toString() de Torneio para representar cada torneio
                    .collect(Collectors.joining("\n ")));  // Lista todos os torneios);
            System.out.println("\nEscreva o nome do Torneio que pretende registar o resultado das partidas");
            String nomeTorneio = sc.next();

            // Verifica se o torneio existe
            if(sistema.getLto().encontrarTorneio(nomeTorneio) == null) {
                System.out.println("\nEsse Torneio não existe");
                menuAdmin(id); // Volta ao menu do administrador
            } else {
                // Exibe opções para visualização
                System.out.println("\n1. Classificação geral do Torneio");
                System.out.println("2. Pretende visualizar os resultados Torneio");
                System.out.println("3. Sair");
                System.out.println("Selecione a opção que deseja");
                int opcao = consola.passarStringparaint();

                switch (opcao) {
                    case 1:
                        if (sistema.getLto().obterClassificacao(nomeTorneio)==null){
                            System.out.println("Não existem equipas no torneio");
                            menuAdmin(id);
                        }
                        // Exibe a classificação do torneio
                        System.out.println("Classificação");
                        System.out.println(sistema.getLto().obterClassificacao(nomeTorneio));
                        System.out.println("\nSelecione qualquer botão para sair");
                        String resultadoA = sc.next();
                        menuAdmin(id); // Volta ao menu do administrador
                        break;

                    case 2:
                        if (sistema.getLto().resultadosPartidas(nomeTorneio)==null){
                            System.out.println("Não existem resultados partidas");
                            menuAdmin(id);
                        }
                        // Exibe os resultados das partidas
                        System.out.println("Resultados");
                        System.out.println(sistema.getLto().resultadosPartidas(nomeTorneio).stream().map(Partida::toString) // Usa o método toString() da Partida para representar cada partida
                                .collect(Collectors.joining("\n")));  // Lista todas as partidas
                        System.out.println("\nSelecione qualquer botão para sair");
                        String resultadoB = sc.next();
                        menuAdmin(id); // Volta ao menu do administrador
                        break;

                    case 3:
                        menuAdmin(id); // Volta ao menu do administrador
                        break;

                    default:
                        System.out.println("\nFunção não reconhecida");
                        menuClassificacaoResultados(id); // Repete o processo
                        break;
                }
            }
        }else {
            System.out.println("Não existe torneios");
            menuAdmin(id);
        }
    }

    
}
