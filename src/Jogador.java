import javax.swing.*;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Jogador extends JFrame {

    private int idJogador;
    private String nome;
    private int pontuacao;
    private String senha;

    private static ArrayList<Jogador> listaJogadores = new ArrayList<Jogador>();
    private static Jogador jogadorAtual = new Jogador();

    public Jogador(){}

    public Jogador(int idJogador, String nome, int pontuacao, String senha){

        this.idJogador = idJogador;
        this.nome = nome;
        this.pontuacao = pontuacao;
        this.senha = senha;

    }

    public static boolean verificarNickJaExiste(String nick) throws SQLException {

        String VerificarNickSql = "SELECT nome FROM jogadores WHERE nome = '" +
                nick + "';";
        ResultSet resultado = ConexaoBD.buscar(VerificarNickSql);
        listaJogadores = new ArrayList<Jogador>();

        while(resultado.next()){
            Jogador jogadorEncontrado = new Jogador();
            jogadorEncontrado.nome = resultado.getString("nome");
            listaJogadores.add(jogadorEncontrado);
        }

        return listaJogadores.size() != 0;

    }

    public static boolean verificarSenha(String nick, String senha) throws SQLException{

        String VerificarSql = "SELECT senha FROM jogadores WHERE nome = '" +
                nick + "';";

        ResultSet resultado = ConexaoBD.buscar(VerificarSql);
        boolean senhaCorreta = false;

        while(resultado.next()) {
            String jogadorSenha = resultado.getString("senha");
            senhaCorreta = senha.equals(jogadorSenha);
        }

        return senhaCorreta;

    }

    public static int getIdJogador(String nome) throws SQLException {

        String pegarIdJogadorSql = "SELECT idJogador FROM jogadores WHERE nome = '" +
                nome + "';";
        ResultSet resultado = ConexaoBD.buscar(pegarIdJogadorSql);
        int idJogador = 0;

        while(resultado.next()){
            idJogador = resultado.getInt("idJogador");
        }

        return idJogador;

    }

    public static int getPontuacao(int idJogador) throws SQLException {

        String pegarPontuacaoSql = "SELECT pontuacao FROM jogadores WHERE idJogador = " +
                idJogador + ";";
        ResultSet resultado = ConexaoBD.buscar(pegarPontuacaoSql);
        int pontuacao = 0;

        while(resultado.next()){
            pontuacao = resultado.getInt("pontuacao");
        }

        return pontuacao;

    }

    public static void setJogadorAtual(int idJogador, String nome, int pontuacao, String senha) throws SQLException {
        jogadorAtual.idJogador = idJogador;
        jogadorAtual.nome = nome;
        jogadorAtual.pontuacao = pontuacao;
        jogadorAtual.senha = senha;
    }

    public static void resetJogadorAtual(){
        jogadorAtual = new Jogador();
    }

    public static void deletarPerfil(){

        String deletarPefilSql = "DELETE FROM jogadores WHERE idJogador = "+
                jogadorAtual.idJogador + ";";
        boolean perfilExcluido = ConexaoBD.deletar(deletarPefilSql);

        if(perfilExcluido){
            JOptionPane.showMessageDialog(null,
                    "Perfil deletado com sucesso!");

        } else {
            JOptionPane.showMessageDialog(null,
                    "Falha ao deletar perfil.");
        }

    }

    public static void editarPerfil(String novoNome, String novaSenha) throws SQLException{

        String mensagem = "";
        boolean nomeInvalido = false;

        if(!novoNome.isEmpty() && !verificarNickJaExiste(novoNome)){
            String atualizarNomeSQL = "UPDATE jogadores SET nome = '" +
                    novoNome + "' WHERE idJogador = " + jogadorAtual.idJogador + ";";
            boolean nomeAlterado = ConexaoBD.atualizar(atualizarNomeSQL);
            jogadorAtual.nome = novoNome;

            if(novaSenha.isEmpty()){
                mensagem = "Nome atualizado com sucesso!";
            }

        } else if (verificarNickJaExiste(novoNome)){
            JOptionPane.showMessageDialog(null,"Nome inválido!");
            nomeInvalido = true;
        }

        if (!novaSenha.isEmpty()) {
            String atualizarSenhaSql = "UPDATE jogadores SET senha = '" +
                    novaSenha + "' WHERE idJogador = " + jogadorAtual.idJogador + ";";
            boolean senhaAlterada = ConexaoBD.atualizar(atualizarSenhaSql);
            jogadorAtual.senha = novaSenha;

            if(!novoNome.isEmpty() && !nomeInvalido){
                mensagem = "Perfil atualizado com sucesso!";
            } else {
                mensagem = "Senha atualizada com sucesso";
            }

        }

        if(!mensagem.isEmpty()) {
            JOptionPane.showMessageDialog(null, mensagem);
        }

    }

    public static void setPontuacao(int pontuacao){

        if(pontuacao > jogadorAtual.pontuacao) {
            String setPontuacaoSql = "UPDATE jogadores SET pontuacao = " +
                    pontuacao + " WHERE idJogador = " +
                    jogadorAtual.idJogador + ";";

            boolean pontuacaoAtualizada = ConexaoBD.atualizar(setPontuacaoSql);
            jogadorAtual.pontuacao = pontuacao;
        }

    }

    public static ArrayList<String> getRanking() throws SQLException {

        ArrayList<String> ranking = new ArrayList<String>();

        String getRankingSql = "SELECT nome,pontuacao FROM jogadores ORDER BY pontuacao DESC";
        ResultSet resultado = ConexaoBD.buscar(getRankingSql);

        while (resultado.next()){
            String jogadorEncontrado = (resultado.getString("nome") + ": " +
                    resultado.getInt("pontuacao") + " pnts");

            if(jogadorEncontrado.equals( (jogadorAtual.nome + ": " +
                    jogadorAtual.pontuacao + " pnts") )){
                jogadorEncontrado += " (VOCÊ)";
            }

            ranking.add(jogadorEncontrado);
        }

        return ranking;
    }

    public static String getNomeAtual(){ return jogadorAtual.nome; }

}
