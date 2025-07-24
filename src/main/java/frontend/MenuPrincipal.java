package frontend;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import backend.*;


public class MenuPrincipal {
    private Sistema sistema;
    private Consola consola;
    private MenuAdministrador administrador;
    private MenuTreinador treinador;
    private MenuJogador jogador;
    public static void main(String[] args) throws IOException {
        MenuPrincipal menu = new MenuPrincipal();// Cria o menu principal
        menu.sistema = new Sistema();  // Instância do sistema que gere os dados
        menu.consola = new Consola();// Instância da consola para lidar com entradas e saídas

        menu.sistema.lerFicheiro();  // Lê os dados do arquivo
        menu.sistema.adicionar();  // Adiciona dados ao sistema
        menu.jogador=new MenuJogador(menu.sistema, menu);
        menu.administrador=new MenuAdministrador(menu.sistema, menu);
        menu.treinador=new MenuTreinador(menu.sistema, menu);
        menu.login();  // Inicia o processo de login
    }

    // Método de login
    public void login() throws IOException {
        Scanner sc = new Scanner(System.in);  // Scanner para ler entradas do usuário
        System.out.println("\nIntroduza o seu nickname ou email");
        String email = sc.next();  // Lê o email ou nickname
        System.out.println("\nIntroduza a sua password");
        String pass = sc.next();  // Lê a senha
        int opcao = sistema.login(email, pass);  // Verifica se o login é válido
        if (opcao == 1) {
            String idAdmin = sistema.getLa().encontrarid(email);// Encontra o ID do administrador
            administrador.menuAdmin(idAdmin);  // Exibe o menu do administrador
        } else if (opcao == 2) {
            String idTreinador = sistema.getLt().encontrarid(email);  // Encontra o ID do treinador

            treinador.menuTreinador(idTreinador);  // Exibe o menu do treinador
        } else if (opcao == 3) {
            String idJogador = sistema.getLj().encontrarid(email);  // Encontra o ID do jogador
            jogador.menuJogador(idJogador);  // Exibe o menu do jogador
        } else if (opcao == 0) {
            System.out.println("\nLogin ou Senha incorreta");  // Caso o login falhe
            login();  // Chama o método de login novamente
        }
    }
    /* LOGIN DE SAÍDA */
// Exibe as opções para o jogador sair ou realizar login novamente
    public void logout() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n1. Deseja iniciar sessão");
        System.out.println("2. Deseja sair da app");
        int opcao = consola.passarStringparaint();  // Captura a opção escolhida
        switch (opcao) {
            case 1:
                login();  // Realiza o login do jogador
                break;
            case 2:
                System.out.println("A aplicação será encerrada.");
                sistema.gravarFicheiro();  // Grava as alterações no sistema
                System.exit(0);  // Encerra a aplicação
                break;
            default:
                System.out.println("Função não reconhecida.");
                logout();  // Se a opção não for válida, reexibe o menu de logout
        }
    }}
