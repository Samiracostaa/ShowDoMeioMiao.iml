import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EntrarTela extends JFrame implements ActionListener {

    ImageIcon fundo = new ImageIcon(getClass().getResource("/imagem/Entrar.jpg"));
    JLabel labelFundo = new JLabel(fundo);

    JTextField caixaTxtNome = new JTextField();
    JTextField caixaTxtSenha = new JTextField();

    JButton botaoConfirmar = new JButton("Confirmar");
    JButton botaoVoltar = new JButton("Voltar");

    JLabel labelNome = new JLabel("Nome");
    JLabel labelSenha = new JLabel("Senha");

    public void actionPerformed(ActionEvent e){

        if(e.getSource() == botaoConfirmar) {

            String nome = caixaTxtNome.getText();
            String senha = caixaTxtSenha.getText();

            try {

                if(Jogador.verificarSenha(nome,senha)){

                    int idJogador = Jogador.getIdJogador(nome);
                    int pontuacao = Jogador.getPontuacao(idJogador);
                    Jogador.setJogadorAtual(idJogador,nome,pontuacao,senha);
                    new MenuInicial();
                    setVisible(false);

                } else {
                    JOptionPane.showMessageDialog(null,
                            "<html>Usuário e/ou senha incorretos! <br>Tente novamente!");
                }

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        } else {
            new Main();
            setVisible(false);
        }

    }

    public EntrarTela(){

        setLayout(null);

        labelNome.setFont(new Font("Arial Black",1,12));
        labelNome.setForeground(new Color(255, 255, 255));
        labelNome.setBounds(160, 150, 120, 48);
        add(labelNome);

        caixaTxtNome.setBounds(210, 160, 200, 30);
        add(caixaTxtNome);

        labelSenha.setFont(new Font("Arial Black",1,12));
        labelSenha.setForeground(new Color(255, 255, 255));
        labelSenha.setBounds(160,300,200,30);
        add(labelSenha);

        caixaTxtSenha.setBounds(210, 310, 200, 30);
        add(caixaTxtSenha);

        botaoConfirmar.addActionListener(this);
        botaoConfirmar.setBounds(230, 370, 160, 39);
        add(botaoConfirmar);

        botaoVoltar.addActionListener(this);
        botaoVoltar.setBounds(230, 470, 160, 39);
        add(botaoVoltar);

        setTitle("Show Do Mei-Milhão");
        setSize(600,650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        labelFundo.setBounds(0, 0, 600, 650);
        add(labelFundo);

    }

}
