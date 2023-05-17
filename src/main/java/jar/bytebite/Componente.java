/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jar.bytebite;

import com.github.britooo.looca.api.core.Looca;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
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

    Long LongCpu = looca.getProcessador().getFrequencia();
    double c = LongCpu.doubleValue();
    Double cpuBites = c / 1000000000;
    double totalCpu = Math.round(cpuBites * scale) / scale;

    private static final Logger logger = Logger.getLogger(Login.class.getName());

    public static void logFormatacao() throws IOException {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dataFormatada = dateFormat.format(date);

        Path path = Paths.get("C:/Logs-ByteBite/");
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        
        Path path1 = Paths.get("C:/Logs-ByteBite/Componentes/");
        if (!Files.exists(path1)) {
            Files.createDirectory(path1);
        }

        FileHandler fileHandler = new FileHandler(String.format("C:/Logs-ByteBite/Componentes/%s.txt", dataFormatada));
        fileHandler.setFormatter(new Formatter() {
            private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd >> HH:mm:ss");

            public String format(LogRecord record) {

                StringBuilder builder = new StringBuilder();
                builder.append(dateFormat.format(new Date())).append(" ");
                builder.append(record.getLevel()).append(": ");
                builder.append(record.getMessage()).append(" (");
                builder.append(record.getSourceClassName()).append(".");
                builder.append(record.getSourceMethodName()).append(")");
                builder.append(System.lineSeparator());
                return builder.toString();
            }
        }
        );
        logger.addHandler(fileHandler);
        logger.setLevel(Level.ALL);
    }

    public void inserirComponente() {
        try {
            con.update("insert into componente values(?, ?, ?);",
                    totalCpu, "GHz", 1);
            System.out.println("Inseriu um novo componente do tipo 'Cpu'.");
            logger.info("Foi inserido um novo componente do tipo 'Cpu'.");

        } catch (Exception e) {
            System.out.println("Componente do tipo 'Cpu' já existente.");
            logger.warning("Componente do tipo 'Cpu' já existe em nossa base.");

        }
        try {
            con.update("insert into componente values(?, ?, ?);",
                    ramTotal, "GB", 2);
            System.out.println("Inseriu um novo componente do tipo 'Memória ram'.");
            logger.info("Foi inserido um novo componente do tipo 'Memória ram'.");

        } catch (Exception e) {
            System.out.println("Componente do tipo 'Memória ram' já existente.");
            logger.warning("Componente do tipo 'Memória RAM' já existe em nossa base.");

        }
        try {
            con.update("insert into componente values(?, ?, ?);",
                    armazenamentoTotal, "GB", 3);
            System.out.println("Inseriu um novo componente do tipo 'Armazenamento'.");
            logger.warning("Foi inserido um novo componente do tipo 'Armazenamento'.");

        } catch (Exception e) {
            System.out.println("Componente do tipo 'Armazenamento' já existente.");
            logger.warning("Componente do tipo 'Armazenamento' já existe em nossa base.");

        }
    }

    public Integer FkComponenteParaConfigCpu() {
        return con.queryForObject("select idComponente from componente where total = ?;", Integer.class, totalCpu);
    }

    public Integer FkComponenteParaConfigRam() {
        return con.queryForObject("select idComponente from componente where total = ?;", Integer.class, ramTotal);
    }

    public Integer FkComponenteParaConfigArmazenamento() {
        return con.queryForObject("select idComponente from componente where total = ?;", Integer.class, armazenamentoTotal);
    }

    public void inserirConfiguracao(String id) {
        try {
            con.update("insert into configuracao values (?, ?);",
                    id, FkComponenteParaConfigCpu());
            con.update("insert into configuracao values (?, ?);",
                    id, FkComponenteParaConfigRam());
            con.update("insert into configuracao values (?, ?);",
                    id, FkComponenteParaConfigArmazenamento());
            System.out.println("Deu Certo a inserção de configuração");
            logger.info("As inserções no banco ocorreram da forma correta.");

        } catch (Exception e) {
            System.out.println("Erro na inserção de configuração");
            logger.severe("Houve uma falha durante a inserção dos dados da configuração");
        }
    }

    public void mostrar() {
        System.out.println(totalCpu);
    }

}
