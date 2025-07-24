package frontend;

import backend.Jogador;
import backend.Partida;
import backend.Sistema;
import backend.Torneio;

import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MenuTreinador extends MenuPrincipal{
    private Consola consola=new Consola();
    private Sistema sistema;
    private MenuPrincipal menu;

    public MenuTreinador(Sistema sistema, MenuPrincipal menu){
        this.sistema=sistema;
        this.menu=menu;
    }
    /* MENU TREINADOR */

    // Método principal para exibir o menu do treinador e gerir as opções
    public void menuTreinador(String id) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nMENU TREINADOR");
        // Exibe as opções do menu do treinador
        System.out.println("1. Criar e gerir a equipa (adicionar jogadores, remover, editar)");
        System.out.println("2. Inscrever a equipa em torneios.");
        System.out.println("3. Resultados");
        System.out.println("4. Sair");

        // Captura a opção escolhida pelo treinador
        int opcao = consola.passarStringparaint();

        // Processa a escolha do treinador
        switch (opcao) {
            case 1:
                menuCriarGerirEquipa(id);  // Gerir a equipa
                break;
            case 2:
                menuInscreverEquipa(id);  // Inscrever a equipa em torneios
                break;
            case 3:
                menuResultados(id);  // Visualizar resultados
                break;
            case 4:
                menu.logout();  // Realizar logout
                break;
            default:
                System.out.println("\nFunção não reconhecida.");
                menuTreinador(id);  // Reexibe o menu caso a opção não seja válida
                break;
        }
    }

    // Menu para criar ou gerir a equipa
    public void menuCriarGerirEquipa(String id) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nMENU GERIR EQUIPA");

        // Verifica se o treinador já tem uma equipa
        if(sistema.getLt().verificarEquipa(id)){
            // Exibe as opções de gestão da equipa existente
            System.out.println("\n1. Adicionar Jogador");
            System.out.println("2. Remover Jogador");
            System.out.println("3. Mudar nome Equipa");
            System.out.println("4. Sair");

            // Captura a escolha do treinador
            int opcao = consola.passarStringparaint();
            switch (opcao) {
                case 1:
                    adicionarJogadorEquipa(id, sistema.getLt().encontrarEquipa(id));  // Adicionar jogador à equipa
                    break;
                case 2:
                    removerJogadorEquipa(id, sistema.getLt().encontrarEquipa(id));  // Remover jogador da equipa
                    break;
                case 3:
                    menueditarEquipa(id, sistema.getLt().encontrarEquipa(id));  // Editar nome da equipa
                    break;
                case 4:
                    menuTreinador(id);  // Voltar ao menu principal do treinador
                    break;
                default:
                    System.out.println("\nFunção não reconhecida.");
                    menuCriarGerirEquipa(id);  // Reexibe o menu caso a opção não seja válida
                    break;
            }
        } else {
            // Se o treinador não tem uma equipa, ele pode criar uma nova
            System.out.println("\nQue tipo de Equipa quer criar?");
            System.out.println("1. Efootball");
            System.out.println("2. FPS");
            System.out.println("3. MOBA");

            // Captura a opção de criação da equipa
            int opcao = consola.passarStringparaint();
            switch (opcao) {
                case 1:
                    System.out.println("\nDigite o nome da sua Equipa");
                    String nomeEfootball = sc.next();

                    // Verifica se o nome da equipa já existe
                    if(sistema.getLe().verificarnome(nomeEfootball)){
                        System.out.println("\nEsse nome já existe");
                        menuCriarGerirEquipa(id);  // Se o nome já existir, reexibe o menu
                    } else {
                        // Cria a equipa Efootball
                        System.out.println("\nEquipa adicionada com sucesso.\n " + sistema.getLe().criarEquipaEfootball(nomeEfootball, sistema.getLt().encontrarTreinadorid(id)));
                        menuTreinador(id);  // Volta ao menu principal do treinador
                    }
                    break;
                case 2:
                    System.out.println("\nDigite o nome da sua Equipa");
                    String nomeFPS = sc.next();
                    if(sistema.getLe().verificarnome(nomeFPS)){
                        System.out.println("\nEsse nome já existe");
                        menuCriarGerirEquipa(id);  // Se o nome já existir, reexibe o menu
                    } else {
                        System.out.println("\nEquipa adicionada com sucesso.\n " + sistema.getLe().criarEquipaFPS(nomeFPS, sistema.getLt().encontrarTreinadorid(id)));
                        menuTreinador(id);  // Volta ao menu principal do treinador
                    }
                    break;
                case 3:
                    System.out.println("\nDigite o nome da sua Equipa");
                    String nomeMOBA = sc.next();
                    if(sistema.getLe().verificarnome(nomeMOBA)){
                        System.out.println("\nEsse nome já existe");
                        menuCriarGerirEquipa(id);  // Se o nome já existir, reexibe o menu
                    } else {
                        System.out.println("\nEquipa adicionada com sucesso.\n " + sistema.getLe().criarEquipaMOBA(nomeMOBA, sistema.getLt().encontrarTreinadorid(id)));
                        menuTreinador(id);  // Volta ao menu principal do treinador
                    }
                    break;
                default:
                    System.out.println("\nFunção não reconhecida.");
                    menuCriarGerirEquipa(id);  // Reexibe o menu caso a opção não seja válida
                    break;
            }
        }
    }

    // Método para adicionar um jogador à equipa
    public void adicionarJogadorEquipa(String id, String idEquipa) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nMENU ADICIONAR JOGADOR");
        String tipo = sistema.getLe().encontrarTipo(idEquipa);  // Obtém o tipo de equipa
        System.out.println("Jogador" + tipo);

        // Lista jogadores disponíveis para o tipo de equipa
        if(sistema.getLj().listarJogadoresTipo(tipo) != null) {
            System.out.println(sistema.getLj().listarJogadoresTipo(tipo).stream()
                    .map(Jogador::toString) // Usa o método toString() de Jogador
                    .collect(Collectors.joining("\n")));
            System.out.println("\nSelecione o nickname do jogador que quer adicionar");
            String nickname = sc.next();

            // Verifica se o jogador existe
            if(sistema.getLj().encontrarJogador(nickname) == null) {
                System.out.println("\nEsse jogador não existe");
                menuCriarGerirEquipa(id);  // Reexibe o menu caso o jogador não exista
            } else {
                // Adiciona o jogador à equipa
                sistema.getLe().adicionarJogadorEquipa(sistema.getLj().encontrarJogador(nickname), idEquipa);
                System.out.println("\nO jogador foi adicionado com sucesso.");
                menuCriarGerirEquipa(id);  // Volta ao menu de gestão da equipa
            }
        } else {
            System.out.println("\nNão há jogadores para adicionar");
            menuCriarGerirEquipa(id);  // Reexibe o menu caso não haja jogadores
        }
    }

    // Método para remover um jogador da equipa
    public void removerJogadorEquipa(String id, String idEquipa) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nMENU REMOVER JOGADOR");

        // Verifica se a equipa tem jogadores
        if(sistema.getLe().listarJogadores(idEquipa) != null) {
            System.out.println(sistema.getLe().listarJogadores(idEquipa).stream()
                    .map(Jogador::toString) // Usa o método toString() de Jogador
                    .collect(Collectors.joining("\n")));
            System.out.println("\nSelecione o nickname do jogador que quer remover");
            String nickname = sc.next();

            // Verifica se o jogador existe
            if(sistema.getLj().encontrarJogador(nickname) == null) {
                System.out.println("\nEsse jogador não existe");
                menuCriarGerirEquipa(id);  // Reexibe o menu caso o jogador não exista
            }

            String nomeEquipa = sistema.getLe().encontrarNome(idEquipa);

            // Remove o jogador da equipa
            if(sistema.getLe().removerJogadorEquipa(sistema.getLj().encontrarJogador(nickname), nomeEquipa)) {
                System.out.println("\nO jogador foi removido com sucesso.");
                menuCriarGerirEquipa(id);  // Volta ao menu de gestão da equipa
            } else {
                System.out.println("\nEsse jogador não existe nesta equipa");
                menuCriarGerirEquipa(id);  // Reexibe o menu caso o jogador não esteja na equipa
            }
        } else {
            System.out.println("\nNão tem jogadores no seu clube");
            menuCriarGerirEquipa(id);  // Reexibe o menu caso não haja jogadores na equipa
        }
    }

    // Método para editar a equipa (como mudar o nome)
    public void menueditarEquipa(String id, String idEquipa) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nMENU EDITAR");
        System.out.println("Antigo nome:" + sistema.getLe().encontrarNome(idEquipa));
        System.out.println("\nDigite o novo nome");
        String nome = sc.next();
        System.out.println("\nTem a certeza que quer mudar o nome?");
        System.out.println("1. Sim");
        System.out.println("2. Não");

        // Captura a confirmação para mudar o nome
        int opcao = consola.passarStringparaint();
        switch(opcao) {
            case 1:
                // Altera o nome da equipa
                System.out.println(sistema.getLe().mudarNome(idEquipa, nome));
                menuTreinador(id);  // Volta ao menu principal do treinador
                break;
            case 2:
                menuTreinador(id);  // Volta ao menu principal do treinador sem realizar mudanças
                break;
            default:
                System.out.println("\nFunção não reconhecida");
                menueditarEquipa(id, idEquipa);  // Reexibe o menu caso a opção não seja válida
                break;
        }
    }

    // Método para inscrever a equipa em um torneio
    public void menuInscreverEquipa(String id) throws IOException {
        Scanner sc = new Scanner(System.in);

        // Verifica se o treinador já tem uma equipa
        if (!sistema.getLt().verificarEquipa(id)) {
            System.out.println("\nCrie uma equipa primeiro");
            menuCriarGerirEquipa(id);  // Redireciona para a criação ou gestão de equipa
        }

        // Obtém as informações da equipa
        String idEquipa = sistema.getLt().encontrarEquipa(id);
        String tipo = sistema.getLe().encontrarTipo(idEquipa);
        String nomeEquipa = sistema.getLe().encontrarNome(idEquipa);

        System.out.println("\nListar Torneios");

        // Verifica se existem torneios disponíveis para o tipo da equipa
        if(sistema.getLto().ListarTorneiosdetipo(tipo) != null) {
            System.out.println(sistema.getLto().ListarTorneiosdetipo(tipo).stream().map(Torneio::toString) // Usa o método toString() de Torneio para representar cada torneio
                    .collect(Collectors.joining("\n")));  // Lista todos os torneios);
            System.out.println("\nSelecione o nome do torneio");
            String nome = sc.next();
            if (!sistema.getLto().encontrarTorneioTipo(nome,tipo)){
                System.out.println("O torneio nao foi encontrado");
                menuTreinador(id);
            }
            // Verifica se a equipa já está inscrita no torneio
            if (sistema.getLto().encontrarEquipa(nome, nomeEquipa) != null) {
                System.out.println("\nA equipa já está inscrita neste torneio ou o torneio não existe");
                menuTreinador(id);  // Volta ao menu principal do treinador
            } else {
                // Inscreve a equipa no torneio
                sistema.getLto().inscreverEquipa(nome, sistema.getLe().encontrarEquipaid(idEquipa));
                System.out.println("\nA sua equipa foi inscrita no torneio");
                menuTreinador(id);  // Volta ao menu principal do treinador
            }
        } else {
            System.out.println("\nNão existem torneios disponíveis");
            menuTreinador(id);  // Volta ao menu principal do treinador se não houver torneios
        }
    }

    // Método para exibir os resultados da equipa
    public void menuResultados(String id) throws IOException {
        Scanner sc = new Scanner(System.in);

        // Verifica se o treinador já tem uma equipa
        if (!sistema.getLt().verificarEquipa(id)) {
            System.out.println("\nCrie uma equipa primeiro");
            menuCriarGerirEquipa(id);  // Redireciona para a criação ou gestão de equipa
        }

        // Obtém as informações da equipa
        String idEquipa = sistema.getLt().encontrarEquipa(id);
        String nomeEquipa = sistema.getLe().encontrarNome(idEquipa);

        // Verifica se a equipa está inscrita em algum torneio
        if (sistema.getLto().listaTorneiosEquipa(nomeEquipa) == null) {
            System.out.println("\nNão está inscrito em nenhum torneio");
            menuTreinador(id);  // Volta ao menu principal do treinador
        } else {
            System.out.println("\nListar Torneios");
            System.out.println(sistema.getLto().listaTorneiosEquipa(nomeEquipa).stream().map(Torneio::toString) // Usa o método toString() do Torneio para representar cada torneio
                    .collect(Collectors.joining("\n")));  // Lista todos os torneio
            System.out.println("\nEscreva o nome do torneio que pretende visualizar");
            String nomeTorneio = sc.next();

            // Verifica se o torneio existe
            if (sistema.getLto().encontrarTorneio(nomeTorneio) == null) {
                System.out.println("\nEsse torneio não existe");
                menuTreinador(id);  // Volta ao menu principal do treinador
            } else {
                // Exibe as opções de resultados
                System.out.println("\n1. Ver classificação geral");
                System.out.println("2. Ver próximas jornadas");
                System.out.println("3. Ver resultados");
                System.out.println("4. Sair");
                System.out.println("Escolha a opção desejada");

                int opcao = consola.passarStringparaint();
                switch (opcao) {
                    case 1:
                        System.out.println(sistema.getLto().obterClassificacao(nomeTorneio));
                        System.out.println("\nPrima qualquer botão para sair");
                        String botao = sc.next();
                        menuTreinador(id);  // Volta ao menu principal do treinador
                        break;

                    case 2:
                        if (sistema.getLto().proximasJornadas(nomeTorneio) == null) {
                            System.out.println("\nNão existem próximas jornadas");
                            menuTreinador(id);  // Volta ao menu principal do treinador
                        }
                        System.out.println("\nPróximas Jornadas");
                        System.out.println(sistema.getLto().proximasJornadas(nomeTorneio).stream().map(Partida::toString) // Usa o método toString() da Partida para representar cada partida
                                .collect(Collectors.joining("\n")));  // Lista todas as partidas
                        System.out.println("\nPrima qualquer botão para sair");
                        String botaoA = sc.next();
                        menuTreinador(id);  // Volta ao menu principal do treinador
                        break;

                    case 3:
                        if (sistema.getLto().resultadosPartidas(nomeTorneio) == null) {
                            System.out.println("\nNão existem resultados ainda");
                            menuTreinador(id);  // Volta ao menu principal do treinador
                        }
                        System.out.println("\nResultados das Jornadas");
                        System.out.println(sistema.getLto().resultadosPartidas(nomeTorneio).stream().map(Partida::toString) // Usa o método toString() da Partida para representar cada partida
                                .collect(Collectors.joining("\n")));  // Lista todas as partidas
                        System.out.println("\nPrima qualquer botão para sair");
                        String botaoB = sc.next();
                        menuTreinador(id);  // Volta ao menu principal do treinador
                        break;

                    case 4:
                        menuTreinador(id);  // Volta ao menu principal do treinador
                        break;

                    default:
                        System.out.println("\nFunção não reconhecida");
                        menuResultados(id);  // Reexibe o menu caso a opção não seja válida
                        break;
                }
            }
        }
    }
}
