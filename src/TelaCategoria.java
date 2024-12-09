import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Scanner;

public class TelaCategoria extends JFrame implements ActionListener {

    ImageIcon fundo = new ImageIcon(getClass().getResource("/imagem/Categoria.jpg"));
    JLabel labelFundo = new JLabel(fundo);

    JRadioButton portugues = new JRadioButton("Português");
    JRadioButton matematica = new JRadioButton("Matemática");
    JRadioButton historia = new JRadioButton("História");
    JRadioButton variedades = new JRadioButton("Variedades");
    JRadioButton todas = new JRadioButton("Todas as categorias",true);
    ButtonGroup categoria = new ButtonGroup();

    JButton botaoConfirmar = new JButton("Confirmar");
    JButton botaoCancelar = new JButton("Cancelar");

    public void actionPerformed(ActionEvent e){

        if(e.getSource() == botaoConfirmar){

            String categoriaEscolhida = "";

            if(portugues.isSelected()){
                categoriaEscolhida = portugues.getText();

            } else if (matematica.isSelected()){
                categoriaEscolhida = "Matemática";

            } else if (historia.isSelected()){
                categoriaEscolhida = "História";

            } else if (variedades.isSelected()){
                categoriaEscolhida = "Variedades";
            }

            try {
                new Jogo(categoriaEscolhida);
                setVisible(false);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        } else {
            new MenuInicial();
            setVisible(false);

        }

    }

    public TelaCategoria(){

        setLayout(null);

        categoria.add(portugues);
        categoria.add(matematica);
        categoria.add(historia);
        categoria.add(variedades);
        categoria.add(todas);

        portugues.setBounds(230, 270, 160, 39);
        add(portugues);

        matematica.setBounds(230, 320, 160, 39);
        add(matematica);

        historia.setBounds(230,370,160,39);
        add(historia);

        variedades.setBounds(230,420,160,39);
        add(variedades);

        todas.setBounds(230,470,160,39);
        add(todas);

        botaoConfirmar.addActionListener(this);
        botaoConfirmar.setBounds(230,520,160,39);
        add(botaoConfirmar);

        botaoCancelar.addActionListener(this);
        botaoCancelar.setBounds(230,570,160,39);
        add(botaoCancelar);

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
