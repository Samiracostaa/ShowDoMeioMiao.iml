import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TelaPerfil extends JFrame implements ActionListener {

    ImageIcon fundo = new ImageIcon(getClass().getResource("/imagem/Entrar.jpg"));
    JLabel labelFundo = new JLabel(fundo);

    JLabel titulo = new JLabel("SEU PERFIL");

    String nomeAtual = Jogador.getNomeAtual();
    int idAtual = Jogador.getIdJogador(nomeAtual);

    JLabel labelJogador = new JLabel("<html>Nome: " + nomeAtual +
            "<br>Recorde Pessoal: " + Jogador.getPontuacao(idAtual));

    JButton botaoAtualizar = new JButton("Atualizar perfil");
    JButton botaoDeletar = new JButton("Deletar perfil");
    JButton botaoVoltar = new JButton("Voltar");

    public void actionPerformed(ActionEvent e){

        if(e.getSource() == botaoAtualizar){
            new TelaAtualizarPerfil();
            setVisible(false);

        } else if(e.getSource() == botaoDeletar){
            int confrimacao = JOptionPane.showConfirmDialog(null,
                    "Você tem certeza que deseja deletar seu perfil?",
                    "Confirmar deleção de perfil",
                    2,3);

            if(confrimacao == JOptionPane.OK_OPTION){
                Jogador.deletarPerfil();
                Jogador.resetJogadorAtual();

                new Main();
                setVisible(false);

            }

        } else {
            new MenuInicial();
            setVisible(false);

        }

    }

    public TelaPerfil() throws SQLException {

        setLayout(null);

        labelJogador.setBounds(230,200,160,80);
        labelJogador.setFont(new Font("Arial Black",1,12));
        labelJogador.setForeground(new Color(255,255,255));
        add(labelJogador);

        botaoAtualizar.addActionListener(this);
        botaoAtualizar.setBounds(230,470,160,39);
        add(botaoAtualizar);

        botaoDeletar.addActionListener(this);
        botaoDeletar.setBounds(230,520,160,39);
        add(botaoDeletar);

        botaoVoltar.addActionListener(this);
        botaoVoltar.setBounds(230,570,160,39);
        add(botaoVoltar);

        setTitle("Show Do Mei-Milhão");
        setSize(600,650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        titulo.setBounds(110,10,400,39);
        titulo.setFont(new Font("Arial Black",1,50));
        titulo.setForeground(new Color(255,255,255));
        add(titulo);

        labelFundo.setBounds(0,0,600,650);
        add(labelFundo);

    }

}
