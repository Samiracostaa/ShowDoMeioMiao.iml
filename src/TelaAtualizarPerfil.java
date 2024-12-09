import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TelaAtualizarPerfil extends JFrame implements ActionListener {

    ImageIcon fundo = new ImageIcon(getClass().getResource("/imagem/Entrar.jpg"));
    JLabel labelFundo = new JLabel(fundo);

    JTextField caixaTxtNovoNome = new JTextField();
    JTextField caixaTxtNovaSenha = new JTextField();

    JButton botaoConfirmar = new JButton("Confirmar");
    JButton botaoCancelar = new JButton("Cancelar");

    JLabel labelNovoNome = new JLabel("Novo nome");
    JLabel labelNovaSenha = new JLabel("Nova senha");

    public void actionPerformed(ActionEvent e){

        if(e.getSource() == botaoConfirmar){
            String novoNome = caixaTxtNovoNome.getText();
            String novaSenha = caixaTxtNovaSenha.getText();

            try {
                Jogador.editarPerfil(novoNome,novaSenha);
                new TelaPerfil();
                setVisible(false);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        } else {

            try {
                new TelaPerfil();
                setVisible(false);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }

    }

    public TelaAtualizarPerfil(){

        setLayout(null);

        labelNovoNome.setFont(new Font("Arial Black",1,12));
        labelNovoNome.setForeground(new Color(255, 255, 255));
        labelNovoNome.setBounds(120, 150, 120, 48);
        add(labelNovoNome);

        caixaTxtNovoNome.setBounds(210, 160, 200, 30);
        add(caixaTxtNovoNome);

        labelNovaSenha.setFont(new Font("Arial Black",1,12));
        labelNovaSenha.setForeground(new Color(255, 255, 255));
        labelNovaSenha.setBounds(120,300,200,30);
        add(labelNovaSenha);

        caixaTxtNovaSenha.setBounds(210, 310, 200, 30);
        add(caixaTxtNovaSenha);

        botaoConfirmar.addActionListener(this);
        botaoConfirmar.setBounds(230, 370, 160, 39);
        add(botaoConfirmar);

        botaoCancelar.addActionListener(this);
        botaoCancelar.setBounds(230, 470, 160, 39);
        add(botaoCancelar);

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
