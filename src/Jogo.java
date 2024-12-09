import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Random;
import java.util.ArrayList;

public class Jogo extends JFrame implements ActionListener {

    private int idJogo;
    private int idQuestao;
    private int idOpcao;
    private int idJogador;

    ArrayList<OpcoesDeResposta> opcoes = new ArrayList<OpcoesDeResposta>();
    ArrayList<Questoes> questoesDisponiveis = new ArrayList<Questoes>();

    int questao = 0;
    String categoria = "";
    Integer estagio = 1;
    Integer pontos = 0;

    ImageIcon fundo = new ImageIcon(getClass().getResource("/imagem/Perguntas.jpeg"));
    JLabel labelFundo = new JLabel(fundo);

    JLabel labelPergunta = new JLabel();

    JButton resposta1 = new JButton();
    JButton resposta2 = new JButton();
    JButton resposta3 = new JButton();
    JButton resposta4 = new JButton();

    JButton ajuda1 = new JButton("Eliminar 50%");
    JButton ajuda2 = new JButton("Univertisários");
    JButton ajuda3 = new JButton("Pular");
    JButton ajuda4 = new JButton("Desistir");

    JLabel labelInicio = new JLabel("Inicio");
    JLabel labelPontos = new JLabel("Pontos: 0/500000");
    JProgressBar barra = new JProgressBar();
    JLabel labelVitoria = new JLabel("VITÓRIA");

    public void actionPerformed(ActionEvent e){

        if(e.getSource() == resposta1){
            try {
                responder(0);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        } else if (e.getSource() == resposta2){
            try {
                responder(1);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        } else if (e.getSource() == resposta3){
            try {
                responder(2);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        } else if (e.getSource() == resposta4){
            try {
                responder(3);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        } else if (e.getSource() == ajuda1){

            try {
                opcoes = OpcoesDeResposta.eliminacao5050(idQuestao,opcoes);
                ajuda1.setEnabled(false);
                perguntar(pontos,idQuestao,estagio,opcoes);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        } else if (e.getSource() == ajuda2){

            if(opcoes.size() == 4) {
                try {
                    opcoes = OpcoesDeResposta.pegarAjudaUniversitarios(idQuestao, opcoes);
                    ajuda2.setEnabled(false);
                    perguntar(pontos, idQuestao, estagio, opcoes);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            } else {
                JOptionPane.showMessageDialog(null,
                        "Não é permitido usar junto com eliminar 50%!");
            }

        } else if (e.getSource() == ajuda3){

            try {
                questoesDisponiveis.remove(questao);
                ajuda3.setEnabled(false);
                escolherPergunta();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        } else {

            try {

                mostrarCorreta();

                Jogador.setPontuacao(pontos);

                int reiniciar = JOptionPane.showConfirmDialog(null,
                        "<html>Você desistiu <br>" +
                                "<br>Pontuação final: " + pontos +
                                "<br>Deseja jogar novamente?",
                        "Jogar novamente?", 2, 3);

                if (reiniciar == JOptionPane.OK_OPTION) {
                    new Jogo(categoria);
                    setVisible(false);

                } else {
                    new MenuInicial();
                    setVisible(false);

                }

            } catch (SQLException ex){
                throw new RuntimeException(ex);
            }
        }
    }

    public Jogo(String categoria) throws SQLException {

        this.categoria = categoria;

        setLayout(null);

        labelPergunta.setBounds(60, 40, 342, 110);
        labelPergunta.setFont(new Font("Arial Black",1,12));
        labelPergunta.setForeground(Color.WHITE);
        add(labelPergunta);

        resposta1.addActionListener(this);
        resposta1.setBounds(20, 440, 275, 21);
        add(resposta1);

        resposta2.addActionListener(this);
        resposta2.setBounds(20, 470, 275, 21);
        add(resposta2);

        resposta3.addActionListener(this);
        resposta3.setBounds(20, 500, 275, 21);
        add(resposta3);

        resposta4.addActionListener(this);
        resposta4.setBounds(20, 530, 275, 21);
        add(resposta4);

        ajuda1.addActionListener(this);
        ajuda1.setBounds(320, 470, 115, 21);
        add(ajuda1);

        ajuda2.addActionListener(this);
        ajuda2.setBounds(450, 470, 115, 21);
        add(ajuda2);

        ajuda3.addActionListener(this);
        ajuda3.setBounds(320, 500, 115, 21);
        add(ajuda3);

        ajuda4.addActionListener(this);
        ajuda4.setBounds(450, 500, 115, 21);
        add(ajuda4);

        labelInicio.setBounds(50,5,100,10);
        labelInicio.setForeground(Color.WHITE);
        add(labelInicio);

        barra.setBounds(100,5,400,10);
        barra.setMaximum(500000);
        barra.setForeground(Color.CYAN);
        add(barra);

        labelPontos.setBounds(250,20,200,10);
        labelPontos.setForeground(Color.WHITE);
        add(labelPontos);

        labelVitoria.setBounds(510,5,100,10);
        labelVitoria.setForeground(Color.WHITE);
        add(labelVitoria);

        setTitle("Show Do Mei-Milhão");
        setSize(600,650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        labelFundo.setBounds(0,0,600,650);
        add(labelFundo);

        questoesDisponiveis = Questoes.questoesDisponiveis(categoria);
        escolherPergunta();

    }

    public Jogo (int idQuestao, int idOpcao, int idJogador){

        this.idQuestao = idQuestao;
        this.idOpcao = idOpcao;
        this.idJogador = idJogador;

    }

    public void mostrarCorreta(){

        int opcaoCorreta = OpcoesDeResposta.getPosOpcaoCorreta(opcoes);

        if(opcaoCorreta == 0) {
            resposta1.setForeground(Color.GREEN);

        } else {
            resposta1.setForeground(Color.RED);
        }

        if(opcaoCorreta == 1) {
            resposta2.setForeground(Color.GREEN);

        } else {
            resposta2.setForeground(Color.RED);
        }

        if(opcaoCorreta == 2) {
            resposta3.setForeground(Color.GREEN);

        } else {
            resposta3.setForeground(Color.RED);
        }

        if(opcaoCorreta == 3) {
            resposta4.setForeground(Color.GREEN);

        } else {
            resposta4.setForeground(Color.RED);
        }

    }

    public void responder(int respostaEscolhida) throws SQLException {

        mostrarCorreta();

        int idRespostaEscolhida = OpcoesDeResposta.getIdRespostaEscolhida(opcoes,respostaEscolhida);

        if(OpcoesDeResposta.verificarResposta(idQuestao,idRespostaEscolhida)){
            JOptionPane.showMessageDialog(null," CORRETA!");
            estagio++;
            pontos += 100000;
            barra.setValue(pontos);
            labelPontos.setText("Pontos: " + pontos + "/500000");
            questoesDisponiveis.remove(questao);
            escolherPergunta();

        } else {

            Jogador.setPontuacao(pontos);

            int reiniciar = JOptionPane.showConfirmDialog(null,
                    "<html>Errada... <br> Você perdeu <br>" +
                    "<br>Pontuação final: " + pontos +
                    "<br>Deseja jogar novamente?",
                    "Jogar novamente?", 2,3);

            if(reiniciar == JOptionPane.OK_OPTION){
                new Jogo(categoria);
                setVisible(false);

            } else {
                new MenuInicial();
                setVisible(false);

            }

        }

    }

    public void escolherPergunta() throws SQLException {

        if(estagio <= 5) {

            Random gerador = new Random();

            //escolher uma questão aleatória
            questao = gerador.nextInt(questoesDisponiveis.size()) + 0;
            idQuestao = Questoes.getIdQuestao(questoesDisponiveis, questao);

            opcoes = OpcoesDeResposta.pegarOpcoes(idQuestao);
            perguntar(pontos, idQuestao, estagio, opcoes);

        } else {

            JOptionPane.showMessageDialog(null,
                    "<html>PARABÉNS!!! VOCÊ VENCEU" +
                    "<br>Pontuação final: " + pontos);
            Jogador.setPontuacao(pontos);

            new MenuInicial();
            setVisible(false);

        }

    }

    public void perguntar(int pontos,int idQuestao, int estagio, ArrayList<OpcoesDeResposta> opcoes) throws SQLException {

        labelPergunta.setText("<html>CATEGORIA: " + Questoes.getCategoria(idQuestao)
                + "<br>" + estagio + ". " + Questoes.pegarEnunciado(idQuestao));

        resposta1.setForeground(new Color(51,51,52));
        resposta2.setForeground(new Color(51,51,52));
        resposta3.setForeground(new Color(51,51,52));
        resposta4.setForeground(new Color(51,51,52));

        resposta1.setText(OpcoesDeResposta.getOpcao(opcoes, 0));
        resposta2.setText(OpcoesDeResposta.getOpcao(opcoes, 1));

        if(opcoes.size() == 4) {
            resposta3.setText(OpcoesDeResposta.getOpcao(opcoes, 2));
            resposta3.setEnabled(true);
            resposta3.setVisible(true);
            resposta4.setText(OpcoesDeResposta.getOpcao(opcoes, 3));
            resposta4.setEnabled(true);
            resposta4.setVisible(true);

        } else {
            resposta3.setEnabled(false);
            resposta3.setVisible(false);
            resposta4.setEnabled(false);
            resposta4.setVisible(false);
        }
    }

}
