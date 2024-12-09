import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class TelaRanking extends JFrame implements ActionListener {

    ImageIcon fundo = new ImageIcon(getClass().getResource("/imagem/Entrar.jpg"));
    JLabel labelFundo = new JLabel(fundo);

    JLabel listaRanking = new JLabel();
    JScrollPane scroll = new JScrollPane(listaRanking);

    JButton botaoVoltar = new JButton("Voltar");

    public void actionPerformed(ActionEvent e){

        new MenuInicial();
        setVisible(false);

    }

    public TelaRanking() throws SQLException {

        setLayout(null);

        ArrayList<String> ranking = Jogador.getRanking();
        String jogadoresRanking = "<html>";

        for(int i = 0; i < ranking.size(); i++){

            jogadoresRanking += ( (i+1) + ". " + ranking.get(i) + "<br>" );

        }

        listaRanking.setText(jogadoresRanking);

        scroll.setBounds(80,100,400,450);
        add(scroll);

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
