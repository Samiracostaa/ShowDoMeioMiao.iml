import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaRegras extends JFrame implements ActionListener {

    ImageIcon fundo = new ImageIcon(getClass().getResource("/imagem/Entrar.jpg"));
    JLabel labelFundo = new JLabel(fundo);

    JLabel regras = new JLabel("<html>REGRAS: <br>" +
            "Show do Mei Mião é um jogo de perguntas e respostas " +
            "onde lhe são dadas 4 alternativas para uma questão.<br>" +
            "Acerte a correta e passe para a<br>" +
            "próxima pergunta; <br>caso erre, perderá o progresso. <br>" +
            "Ao acertar 5 perguntas você vencerá o jogo. <br>" +
            "Cada questão tem uma categoria, podendo variar entre: <br>" +
            "   Português; matématica; história; variedades. <br>" +
            "Ao clicar em iniciar o jogo, você poderá escolher <br>" +
            "entre jogar uma categoria específica ou todas as <br>" +
            "categorias. <br>" +
            "Para cada pergunta você poderá pedir uma entre as <br>" +
            "seguintes ajudas: <br>" +
            "   Eliminar 50% das alternativas erradas; <br>" +
            "   Pedir ajuda para os universitários; <br>" +
            "   Pular para a próxima questão. <br>" +
            "Cada ajuda só pode ser usada uma vez por partida. <br>" +
            "E caso queira parar o jogo, poderá desistir para <br>" +
            "encerrar a partida. <br>");

    JButton botaoVoltar = new JButton("Voltar");

    public void actionPerformed(ActionEvent e){

        new MenuInicial();
        setVisible(false);

    }

    public TelaRegras(){

        setLayout(null);

        regras.setBounds(130,100,400,450);
        regras.setForeground(new Color(255,255,255));
        regras.setFont(new Font("Arial Black", 1, 12));
        add(regras);

        botaoVoltar.addActionListener(this);
        botaoVoltar.setBounds(230,570,160,39);
        add(botaoVoltar);

        setTitle("Show Do Mei-Milhão");
        setSize(600,650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        labelFundo.setBounds(0,0,600,650);
        add(labelFundo);

    }

}
