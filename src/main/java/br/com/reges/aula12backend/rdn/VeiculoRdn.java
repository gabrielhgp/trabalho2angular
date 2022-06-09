package br.com.reges.aula12backend.rdn;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.reges.aula12backend.modelos.Veiculo;


public class VeiculoRdn {
    
        public int inserir(Veiculo veiculo) throws SQLException {

        StringBuilder sql = new StringBuilder();
        int Afetadaslinhas = 0;

        sql.append("INSERT INTO veiculo                               ");
        sql.append("            (id                                   ");   
        sql.append("            ,modelo                               ");
        sql.append("            ,marca                                ");
        sql.append("            ,cor 				                   ");
        sql.append("            ,ano )		                           ");
        sql.append("     values                                       ");
        sql.append("              (?                                  ");
        sql.append("              ,?                                  ");
        sql.append("              ,?                                  ");
        sql.append("              ,?                                  ");
        sql.append("              ,?             )                    ");


        Connection conn = new ConnectionFactory().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql.toString());

        stmt.setInt(1, veiculo.getId());
        stmt.setString(2, veiculo.getModelo());
        stmt.setString(3, veiculo.getMarca());
        stmt.setString(4, veiculo.getCor());
        stmt.setInt(5, veiculo.getAno());


        Afetadaslinhas = stmt.executeUpdate();

        System.out.print("linhas: " + Afetadaslinhas);

        stmt.close();
        conn.close();

        return Afetadaslinhas;
    }

    public ArrayList<Veiculo> obterTodos() {

        ArrayList<Veiculo> retorno = new ArrayList<Veiculo>();

        try {

            StringBuilder str = new StringBuilder();

            str.append("select  a.id                           ");
            str.append("        ,a.modelo                      ");
            str.append("        ,a.marca		               ");
            str.append("        ,a.cor						   ");
            str.append("        ,a.ano		                   ");
            str.append(" from veiculo a                        ");

    
            Connection conn = new ConnectionFactory().getConnection();

       
            Statement stmt = conn.createStatement();

        
            ResultSet rs = stmt.executeQuery(str.toString());

   
            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                
                veiculo.setId(rs.getInt("id"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setMarca(rs.getString("marca"));                
                veiculo.setCor(rs.getString("cor"));
                veiculo.setAno(rs.getInt("ano"));


          
                retorno.add(veiculo);

            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

        return retorno;
    }

    public int excluir(int id) {

        int linhasAfetadas = 0;

        try {

            String str = "delete from veiculo where id = ?";

            
            Connection conn = new ConnectionFactory().getConnection();

       
            PreparedStatement statement = conn.prepareStatement(str);
            statement.setInt(1, id);

     
            linhasAfetadas = statement.executeUpdate();

          
            conn.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return linhasAfetadas;
    }

    public int alterar(Veiculo veiculo) {

        StringBuilder str = new StringBuilder();
        int linhasAfetadas = 0;

        try {

            str.append(" update veiculo set modelo = ?                       ");
            str.append("                    ,marca = ?  	 	          ");
            str.append("                    ,cor = ?                     ");
            str.append("                    ,ano = ?		              ");
            str.append("where id = ?                                     ");

      
            Connection conn = new ConnectionFactory().getConnection();

       
            PreparedStatement stmt = conn.prepareStatement(str.toString());

      

            stmt.setString(1, veiculo.getModelo());
            stmt.setString(2, veiculo.getMarca());
            stmt.setString(3, veiculo.getCor());
            stmt.setInt(4, veiculo.getAno());
            stmt.setInt(5, veiculo.getId());


        
         
              linhasAfetadas = stmt.executeUpdate();

         
            stmt.close();
            conn.close();

        } catch (SQLException e) {

            System.out.println(e);
        }

        return linhasAfetadas;

    }

    public Veiculo obterPorId(int id) {

        Veiculo veiculo = new Veiculo();

        try {

            StringBuilder str = new StringBuilder();

            str.append("select  a.id                          ");
            str.append("        ,a.modelo                     ");
            str.append("        ,a.marca		               ");
            str.append("        ,a.cor			               ");
            str.append("        ,a.ano		                   ");
            str.append(" from veiculo a                       ");
            str.append(" where a.id = ?                       ");

         
            Connection conn = new ConnectionFactory().getConnection();

                   
            PreparedStatement stmt = conn.prepareStatement(str.toString());

          
            stmt.setInt(1, id);

   
            ResultSet rs = stmt.executeQuery();

   
            while (rs.next()) {
                //Veiculo veiculo = new veiculo();

                veiculo.setId(rs.getInt("id"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setAno(rs.getInt("ano"));
   

            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

        return veiculo;
    }

    
    
    
}
