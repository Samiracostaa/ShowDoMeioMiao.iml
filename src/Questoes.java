import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Questoes {

    private int idQuestao;
    private String enunciado;
    private String categoria;

    public Questoes(){}

    public Questoes(String enunciado, String categoria){

        this.enunciado = enunciado;
        this.categoria = categoria;

    }

    public static ArrayList<Questoes> questoesDisponiveis(String categoria) throws SQLException {

        String questoesDisponiveiSql = "SELECT * FROM questoes";

        if(!categoria.isEmpty()){
            questoesDisponiveiSql += " WHERE categoria = '" + categoria + "';";
        } else {
            questoesDisponiveiSql += ";";
        }

        ResultSet resultado = ConexaoBD.buscar(questoesDisponiveiSql);
        ArrayList<Questoes> questoesDisponiveis = new ArrayList<Questoes>();

        while(resultado.next()){

            Questoes questaoEncontrada = new Questoes();
            questaoEncontrada.idQuestao = resultado.getInt("idQuestao");
            questaoEncontrada.enunciado = resultado.getString("enunciado");
            questaoEncontrada.categoria = resultado.getString("categoria");
            questoesDisponiveis.add(questaoEncontrada);
        }

        return questoesDisponiveis;

    }

    public static String pegarEnunciado(int idQuestao) throws SQLException {

        String pegarEnunciadoSql = "SELECT enunciado FROM questoes WHERE idQuestao = " +
                idQuestao + ";";

        ResultSet resultado = ConexaoBD.buscar(pegarEnunciadoSql);
        String enunciado = "";

        while(resultado.next()){
            enunciado = resultado.getString("enunciado");
        }

        return enunciado;

    }

    public static int getIdQuestao(ArrayList<Questoes> questoesDisponiveis,int questao){
        return questoesDisponiveis.get(questao).idQuestao;
    }

    public static String getCategoria(int idQuestao) throws SQLException {

        String pegarCategoriaSql = "SELECT categoria FROM questoes WHERE idQuestao = " +
                idQuestao + ";";
        ResultSet resultado = ConexaoBD.buscar(pegarCategoriaSql);
        String categoria = "";

        while (resultado.next()){
            categoria = resultado.getString("categoria");
        }

        return categoria;

    }

}
