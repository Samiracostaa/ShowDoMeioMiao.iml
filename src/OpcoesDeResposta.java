import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.Random;

public class OpcoesDeResposta {

    private int idOpcoesDeResposta;
    private String opcao;
    private boolean correta;
    private int idQuestao;

    public OpcoesDeResposta(){}

    public OpcoesDeResposta(int idOpcoesDeResposta, String opcao, boolean correta, int idQuestao){

        this.idOpcoesDeResposta = idOpcoesDeResposta;
        this.opcao = opcao;
        this.correta = correta;
        this.idQuestao = idQuestao;

    }

    public static ArrayList<OpcoesDeResposta> pegarOpcoes(int idQuestao) throws SQLException {

        String pegarOpcaoSql = "SELECT * FROM opcoesDeResposta WHERE idQuestao = " +
                idQuestao + ";";
        ResultSet resultado = ConexaoBD.buscar(pegarOpcaoSql);
        ArrayList<OpcoesDeResposta> opcoes = new ArrayList<OpcoesDeResposta>();

        while (resultado.next()){
            OpcoesDeResposta opcaoEncontrada = new OpcoesDeResposta();
            opcaoEncontrada.idOpcoesDeResposta = resultado.getInt("idOpcao");
            opcaoEncontrada.opcao = resultado.getString("opcao");
            opcaoEncontrada.correta = resultado.getBoolean("correta");
            opcaoEncontrada.idQuestao = resultado.getInt("idQuestao");
            opcoes.add(opcaoEncontrada);
        }

        return opcoes;

    }

    public static String getOpcao(ArrayList<OpcoesDeResposta> opcao, int ordem){
        return opcao.get(ordem).opcao;
    }

    public static int getIdRespostaEscolhida(ArrayList<OpcoesDeResposta> respotaEscolhida, int resposta) {
        return respotaEscolhida.get(resposta).idOpcoesDeResposta;
    }

    public static boolean verificarResposta(int idQuestao, int idOpcaoEscolhida) throws SQLException {

        String verificarRespostaSql = "SELECT idOpcao FROM opcoesDeResposta " +
                "WHERE idQuestao = " + idQuestao + " AND correta = true;";
        ResultSet resultado = ConexaoBD.buscar(verificarRespostaSql);
        int idOpcaoCorreta = 0;

        while (resultado.next()){
            idOpcaoCorreta = resultado.getInt("idOpcao");
        }

        return idOpcaoCorreta == idOpcaoEscolhida;

    }

    public static ArrayList<OpcoesDeResposta> eliminacao5050(int idQuestao,ArrayList<OpcoesDeResposta> opcoes) throws SQLException {

        Random gerador = new Random();

        if(opcoes.size()==4) {

            for (int i = 0; i < 2; i++) {
                int opcaoRemover = gerador.nextInt((4-i)) + 1;

                int idRespostaEscolhida = opcoes.get((opcaoRemover-1)).idOpcoesDeResposta;

                while (verificarResposta(idQuestao, idRespostaEscolhida)) {
                    opcaoRemover = gerador.nextInt((4-i)) + 1;
                    idRespostaEscolhida = opcoes.get((opcaoRemover-1)).idOpcoesDeResposta;
                }

                opcoes.remove((opcaoRemover - 1));

            }
        }

        return opcoes;

    }

    public static ArrayList<OpcoesDeResposta> pegarAjudaUniversitarios(int idQuestao, ArrayList<OpcoesDeResposta> opcoes) throws SQLException {

        Random gerador = new Random();
        int votosRestantes = 3;
        int votosCorretos = gerador.nextInt(4) + 0;
        int tentativas = 3;
        int ultimaAlt = 1;

        if(verificarResposta(idQuestao, opcoes.get(3).idOpcoesDeResposta)){
            ultimaAlt = 2;
        }

        while(tentativas > 0 && votosCorretos < 2){
            votosCorretos = gerador.nextInt(4) + 0;
            tentativas--;
        }

        votosRestantes -= votosCorretos;

        for(int i = 0; i < opcoes.size(); i++){

            if(verificarResposta(idQuestao, opcoes.get(i).idOpcoesDeResposta)){
                opcoes.get(i).opcao += (" (Votos: " + votosCorretos + ")");
            } else{

                if(votosRestantes > 0 && i != (opcoes.size()-ultimaAlt)){
                    int votos = gerador.nextInt( (votosRestantes+1) ) + 0;
                    opcoes.get(i).opcao += (" (Votos: " + votos + ")");
                    votosRestantes -= votos;

                } else if(votosRestantes > 0 && i == (opcoes.size()-ultimaAlt)){
                    opcoes.get(i).opcao += (" (Votos: " + votosRestantes + ")");
                }


            }

        }

        return opcoes;

    }

    public static int getPosOpcaoCorreta(ArrayList<OpcoesDeResposta> opcoes){

        int posCorreta = 0;

        for(int i = 0; i < opcoes.size(); i++){

            if(opcoes.get(i).correta){
                posCorreta = i;
            }

        }

        return posCorreta;

    }

}
