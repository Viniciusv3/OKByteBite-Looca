/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jar.bytebite;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author ViniciusJesus
 */
public class Hardware {
    
    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConnection();
    void inserirDados(){
        try{
                con.update("insert into logHardware values(?, ?, ?, ?, ?);",
                        null, null, null, null, null, null, null);
        
        }catch(Exception e){
            System.out.println("Erro de Syntax");
        }
    }
    
    void inserirHardware(){
        try{
                con.update("insert into hardware values(?, ?, ?, ?, ?, ?, ?);",
                        null, null, null, null, null, null, null);
        
        }catch(Exception e){
            System.out.println("Erro de Syntax");
        }
    }
}
