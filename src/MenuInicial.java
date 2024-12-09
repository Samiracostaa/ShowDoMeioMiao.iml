import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MenuInicial extends JFrame implements ActionListener {

    ImageIcon fundo = new ImageIcon(getClass().getResource("/imagem/TelaInicial.jpg"));
    JLabel labelFundo = new JLabel(fundo);

    JButton botaoJogar = new JButton("Jogar");
    JButton botaoVerRanking = new JButton("Ver ranking");
    JButton botaoPerfil = new JButton("Seu perfil");
    JButton botaoVerRegras = new JButton("Ver regras");
    JButton botaoVoltar = new JButton("Voltar");

    public void actionPerformed(ActionEvent e){

        if(e.getSource() == botaoJogar){
            new TelaCategoria();
            setVisible(false);

        } else if (e.getSource() == botaoVerRanking){

            try {
                new TelaRanking();
                setVisible(false);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


        } else if (e.getSource() == botaoPerfil){

            try {
                new TelaPerfil();
                setVisible(false);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        } else if (e.getSource() == botaoVerRegras){
            new TelaRegras();
            setVisible(false);

        } else {
            new Main();
            setVisible(false);

        }

    }

    public MenuInicial(){

        setLayout(null);

        botaoJogar.addActionListener(this);
        botaoJogar.setBounds(230, 370, 160, 39);
        add(botaoJogar);

        botaoVerRanking.addActionListener(this);
        botaoVerRanking.setBounds(230, 420, 160, 39);
        add(botaoVerRanking);

        botaoPerfil.addActionListener(this);
        botaoPerfil.setBounds(230,470,160,39);
        add(botaoPerfil);

        botaoVerRegras.addActionListener(this);
        botaoVerRegras.setBounds(230,520,160,39);
        add(botaoVerRegras);

        botaoVoltar.addActionListener(this);
        botaoVoltar.setBounds(230,570,160,39);
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