package frontend;

import backend.Sistema;
import backend.Torneio;

import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MenuJogador extends MenuPrincipal{
    private Consola consola=new Consola();
    private Sistema sistema;
    private MenuPrincipal menu;

    public MenuJogador(Sistema sistema, MenuPrincipal menu){
        this.sistema=sistema;
        this.menu=menu;
    }

    /* MENU JOGADOR */
// Exibe o menu de opções do jogador
    public void menuJogador(String id) throws IOException {
        System.out.println("\nMENU JOGADOR");
        System.out.println("1. Dados pessoais");
        System.out.println("2. Torneios");
        System.out.println("3. Estatísticas pessoais");
        System.out.println("4. Sair");
        int opcao = consola.passarStringparaint();  // Captura a opção escolhida
        switch (opcao) {
            case 1:
                menuDadosJogadores(id);  // Exibe o menu de dados pessoais
                break;
            case 2:
                menuVerTorneiosParticipa(id);  // Exibe o menu de torneios em que o jogador participa
                break;
            case 3:
                menuEstatisticas(id);  // Exibe o menu de estatísticas pessoais
                break;
            case 4:
                menu.logout();  // Realiza o logout do jogador
                break;
            default:
                System.out.println("\nFunção não reconhecida.");
                menuJogador(id);  // Se a opção não for válida, reexibe o menu
        }
    }

    /* MENU DADOS PESSOAIS */
// Exibe as opções de alterar os dados pessoais do jogador
    public void menuDadosJogadores(String id) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nDADOS PESSOAIS");

        // Exibe as informações do jogador com base no seu ID
        System.out.println(sistema.getLj().encontrarJogador(sistema.getLj().encontrarNome(id)));
        System.out.println("1. Quer alterar o nome?");
        System.out.println("2. Quer alterar o nickname?");
        System.out.println("3. Sair");

        int opcao = consola.passarStringparaint();  // Captura a opção escolhida
        switch (opcao) {
            case 1:
                // Permite ao jogador mudar seu nome
                System.out.println("\nSelecione o seu nome");
                String nome = sc.next();
                sistema.getLj().mudarNome(id, nome);  // Atualiza o nome do jogador
                System.out.println("\nO nome foi mudado com sucesso");
                menuJogador(id);  // Volta ao menu principal do jogador
                break;
            case 2:
                // Permite ao jogador mudar seu nickname
                System.out.println("\nSelecione o seu novo nickname");
                String nickname = sc.next();
                if (sistema.getLj().verificarnickname(nickname)) {
                    System.out.println("\nEsse nickname já existe");
                    menuDadosJogadores(id);  // Se o nickname já existir, reexibe o menu de dados pessoais
                } else {
                    sistema.getLj().mudarNickname(id, nickname);  // Atualiza o nickname do jogador
                    System.out.println("\nO nickname foi alterado com sucesso");
                    menuJogador(id);  // Volta ao menu principal do jogador
                }
                break;
            case 3:
                menuJogador(id);  // Volta ao menu principal do jogador sem fazer alterações
                break;
            default:
                System.out.println("\nFunção não reconhecida.");
                menuDadosJogadores(id);  // Se a opção não for válida, reexibe o menu de dados pessoais
        }
    }

    // Exibe os torneios nos quais o jogador participa ou participou
    public void menuVerTorneiosParticipa(String id) throws IOException {
        Scanner sc = new Scanner(System.in);

        // Verifica se o jogador tem uma equipa
        if (sistema.getLj().verificarEquipa(id) == null) {
            System.out.println("\nNão tem equipa");
            menuJogador(id);  // Se o jogador não tem uma equipa, volta ao menu principal
        } else {
            String nome = sistema.getLj().verificarEquipa(id);  // Obtém o nome da equipa do jogador
            if (sistema.getLto().listaTorneiosEquipa(nome) == null) {
                System.out.println("\nA equipa não está inscrita em nenhum torneio");
                menuJogador(id);  // Se a equipa não estiver inscrita em torneios, volta ao menu principal
            } else {
                System.out.println("\nListar Torneios que participa ou participou");
                System.out.println(sistema.getLto().listaTorneiosEquipa(nome).stream().map(Torneio::toString) // Usa o método toString() do Torneioo para representar cada torneio
                        .collect(Collectors.joining("\n")));    // Lista todos os  torneios
                System.out.println("\nDigite o torneio que quer ver as estatísticas");
                String nomeTorneio = sc.next();
                if (sistema.getLto().encontrarTorneio(nomeTorneio) == null) {
                    System.out.println("\nEsse torneio pode já não existir");
                    menuJogador(id);  // Se o torneio não existir, volta ao menu principal
                } else {
                    System.out.println(sistema.getLto().obterClassificacao(nomeTorneio));  // Exibe a classificação do torneio
                    menuJogador(id);  // Volta ao menu principal do jogador
                }
            }
        }
    }

    // Exibe as estatísticas pessoais do jogador
    public void menuEstatisticas(String id) throws IOException {
        System.out.println("\nEstatísticas");

        // Exibe as estatísticas do jogador
        System.out.println(sistema.getLj().estatisticasbase(sistema.getLj().encontrarNome(id)));
        menuJogador(id);  // Volta ao menu principal do jogador
    }

}
