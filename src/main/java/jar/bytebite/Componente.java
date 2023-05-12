/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jar.bytebite;

import com.github.britooo.looca.api.core.Looca;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author ViniciusJesus
 */
public class Componente {

    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConnection();
    Looca looca = new Looca();
    double scale = Math.pow(10, 2);

    Double ramTotalSemFormatar = Double.valueOf(looca.getMemoria().getTotal());
    Double ramTotalSemFormatado = ramTotalSemFormatar / 1073141824.00;
    Double ramTotal = Math.round(ramTotalSemFormatado * scale) / scale;

    Long longArmazenamento = looca.getGrupoDeDiscos().getTamanhoTotal();
    double a = longArmazenamento.doubleValue();
    Double armazenamentoBites = a / (1024 * 1024 * 1024);
    double armazenamentoTotal = Math.round(armazenamentoBites * scale) / scale;

    Long totalCpu = looca.getProcessador().getFrequencia() / 1000000000;

    public void inserirComponente() {
        try {
            con.update("insert into componente values(?, ?, ?);",
                    totalCpu, "GHz", 1);
            con.update("insert into componente values(?, ?, ?);",
                    ramTotal, "GB", 2);
            con.update("insert into componente values(?, ?, ?);",
                    armazenamentoTotal, "GB", 3);
            System.out.println("Deu Certo");
            

        } catch (Exception e) {
            System.out.println("Componentes ja existente");
        }
    }
}
