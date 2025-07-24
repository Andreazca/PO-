package frontend;

import backend.Sistema;

import javax.swing.*;

public class LoginGUI {
    public JPanel panelLogin;
    public JTextField txtEmail;
    public JPasswordField txtSenha;
    public JButton btnEntrar;

    private Sistema sistema;

    public LoginGUI(Sistema sistema) {
        this.sistema = sistema;

        btnEntrar.addActionListener(e -> {
            String email = txtEmail.getText().trim();
            String senha = new String(txtSenha.getPassword()).trim();

            if (email.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
                return;
            }

            int opcao = sistema.login(email, senha);

            switch (opcao) {
                case 1 -> {
                    String idAdmin = sistema.getLa().encontrarid(email);
                    JOptionPane.showMessageDialog(null, "Bem-vindo, Administrador!");
                    // Abrir TelaAdmin
                }
                case 2 -> {
                    String idTreinador = sistema.getLt().encontrarid(email);
                    JOptionPane.showMessageDialog(null, "Bem-vindo, Treinador!");
                    // Abrir TelaTreinador
                }
                case 3 -> {
                    String idJogador = sistema.getLj().encontrarid(email);
                    JOptionPane.showMessageDialog(null, "Bem-vindo, Jogador!");
                    // Abrir TelaJogador
                }
                default -> JOptionPane.showMessageDialog(null, "Email ou senha inválidos.");
            }
        });
    }

    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        sistema.lerFicheiro();
        sistema.adicionar();

        JFrame frame = new JFrame("Login");
        frame.setContentPane(new LoginGUI(sistema).panelLogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
