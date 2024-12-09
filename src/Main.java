import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {

    ImageIcon fundo = new ImageIcon(getClass().getResource("/imagem/TelaInicial.jpg"));
    JLabel labelFundo = new JLabel(fundo);

    JButton botaoEntrar = new JButton("Entrar");
    JButton botaoRegistrar = new JButton("Registrar");
    JButton botaoSair = new JButton("Sair");

    public void actionPerformed(ActionEvent e){

        if(e.getSource() == botaoEntrar) {
            new EntrarTela();
            setVisible(false);

        } else if(e.getSource() == botaoRegistrar){
            new RegistrarTela();
            setVisible(false);

        } else {
            System.exit(0);
        }

    }

    public Main(){

        setLayout(null);

        botaoEntrar.addActionListener(this);
        botaoEntrar.setBounds(230, 370, 160, 39);
        add(botaoEntrar);

        botaoRegistrar.addActionListener(this);
        botaoRegistrar.setBounds(230, 420, 160, 39);
        add(botaoRegistrar);

        botaoSair.addActionListener(this);
        botaoSair.setBounds(230,470,160,39);
        add(botaoSair);

        setTitle("Show Do Mei-Milhão");
        setSize(600,650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        labelFundo.setBounds(0,0,600,650);
        add(labelFundo);

    }

    public static void main(String[] args) {

        new Main();

    }

}