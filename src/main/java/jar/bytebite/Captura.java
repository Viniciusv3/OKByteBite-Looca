package jar.bytebite;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.temperatura.Temperatura;
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
public class Captura {

    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConnection();
    Looca looca = new Looca();
    Sistema sistema = looca.getSistema();
    Memoria memoria = looca.getMemoria();
    Processador cpu = looca.getProcessador();
    DiscoGrupo discoGrupo = looca.getGrupoDeDiscos();
    Temperatura temperatura = looca.getTemperatura();
    double scale = Math.pow(10, 2);

//    public void mostrarDados() {
    //        Processador
    Double porcUsoCpu = cpu.getUso();
    Long LongCpu = looca.getProcessador().getFrequencia();
    double c = LongCpu.doubleValue();
    Double cpuBites = c / 1000000000;
    double totalCpu = Math.round(cpuBites * scale) / scale;

//        Memória Ram
    Long longMemoriaD = memoria.getDisponivel();
    double d = longMemoriaD.doubleValue();
    Double memoriaDisponivelBites = d / (1024 * 1024 * 1024);
    double ramDisponivel = Math.round(memoriaDisponivelBites * scale) / scale;

    Double ramTotalSemFormatar = Double.valueOf(looca.getMemoria().getTotal());
    Double ramTotalSemFormatado = ramTotalSemFormatar / 1073141824.00;
    Double ramTotal = Math.round(ramTotalSemFormatado * scale) / scale;

//        Janelas
    Integer janelasTotal = looca.getGrupoDeJanelas().getTotalJanelas();

//        Armazenamento
    Long longArmazenamento = discoGrupo.getTamanhoTotal();
    double a = longArmazenamento.doubleValue();
    Double armazenamentoBites = a / (1024 * 1024 * 1024);
    double armazenamentoTotal = Math.round(armazenamentoBites * scale) / scale;

//        Long longArmazenamentoEmUso = discoGrupo.getDiscos().get(0).getBytesDeLeitura();
//        double aEmUso = longArmazenamentoEmUso.doubleValue();
//        Double armazenamentoEmUsoBites = aEmUso / (1024*1024*1024);
//        double armazenamentoEmUso = Math.round(armazenamentoEmUsoBites*scale)/scale;
//        System.out.println("Processador Uso:");
//        System.out.println(porcUsoCpu);
//        System.out.println("Temperatura processador:");
//        System.out.println(temperaturaCpu);
//        System.out.println("Memória RAM total/disponivel/uso:");
//        System.out.println(ramTotal);
//        System.out.println(ramDisponivel);
//        System.out.println(ramEmUso);
//        System.out.println("Total janelas:");
//        System.out.println(janelasTotal);
//        System.out.println("Armazenamento total/emUso");
//        System.out.println(armazenamentoTotal);
//        System.out.println(armazenamentoEmUso);
//    }
//        public void mostrar(){
//            System.out.println(looca.getGrupoDeDiscos().getDiscos().get(0).getBytesDeEscritas());
//        }
    private static final Logger logger = Logger.getLogger(Login.class.getName());

    public static void logFormatacao() throws IOException {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dataFormatada = dateFormat.format(date);
        
         Path path = Paths.get("C:/Logs-ByteBite/Capturas/");
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }

        FileHandler fileHandler = new FileHandler(String.format("C:/Logs-ByteBite/Capturas/%s.txt", dataFormatada));
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

    public Integer retornarFkConfigCpu(String id, String senha) {
        return con.queryForObject("select idConfiguracao from configuracao as c join maquina as m on c.fk_maquina = m.idMaquina join componente as comp on c.fk_componente = comp.idComponente where m.idMaquina = ? and m.senha = ? and comp.total = ?; ", Integer.class, id, senha, totalCpu);
    }

    public Integer retornarFkConfigRam(String id, String senha) {
        return con.queryForObject("select idConfiguracao from configuracao as c join maquina as m on c.fk_maquina = m.idMaquina join componente as comp on c.fk_componente = comp.idComponente where m.idMaquina = ? and m.senha = ? and comp.total = ?; ", Integer.class, id, senha, ramTotal);
    }

    public Integer retornarFkConfigArmazenamento(String id, String senha) {
        return con.queryForObject("select idConfiguracao from configuracao as c join maquina as m on c.fk_maquina = m.idMaquina join componente as comp on c.fk_componente = comp.idComponente where m.idMaquina = ? and m.senha = ? and comp.total = ?; ", Integer.class, id, senha, armazenamentoTotal);
    }

    public void inserirNoBanco(String id, String senha, String data, String hora) {
        //CPU
        Double porcUsoCpu = cpu.getUso();
        Double temperaturaCpu = (Math.random() * 20) + 45;
        //Memória ram
        Long longMemoriaU = memoria.getEmUso();
        double u = longMemoriaU.doubleValue();
        Double memoriaEmUsoBites = u / (1024 * 1024 * 1024);
        double ramEmUso = Math.round(memoriaEmUsoBites * scale) / scale;
        //Armazenamento 
        Double armazenamentoEmUsoSemFormatar = Double.valueOf(discoGrupo.getDiscos().get(0).getBytesDeLeitura());
        Double armazenamentoEmUsoSemFormatado = armazenamentoEmUsoSemFormatar / 1000000000.00;
        Double armazenamentoEmUso = Math.round(armazenamentoEmUsoSemFormatado * scale) / scale;

        try {
            con.update("insert into log_captura values(?, ?, ?, ?, ?);",
                    data, hora, porcUsoCpu, retornarFkConfigCpu(id, senha), 1);
            con.update("insert into log_captura values(?, ?, ?, ?, ?);",
                    data, hora, temperaturaCpu, retornarFkConfigCpu(id, senha), 2);
            System.out.println("Inseriu no banco os dados da CPU");
            con.update("insert into log_captura values(?, ?, ?, ?, ?);",
                    data, hora, ramEmUso, retornarFkConfigRam(id, senha), 1);
            System.out.println("Inseriu no banco os dados da mamória ram");
//            con.update("insert into log_captura values(?, ?, ?, ?);",
//                    data, hora, janelasTotal, retornaxxx);
//            System.out.println("Inseriu no banco os dados das janelas");
            con.update("insert into log_captura values(?, ?, ?, ?, ?);",
                    data, hora, armazenamentoEmUso, retornarFkConfigArmazenamento(id, senha), 1);
            System.out.println("Inseriu no banco os dados do armazenamento");
            logger.info("Os dados do armazenamento foram inseridos corretamente em nosso banco.");

        } catch (Exception e) {
            System.out.println("Erro ao inserir dados.");
            logger.severe("Houve uma falha durante a inserção dos dados do armazenamento.");
        }
    }

    public void mostrar() {
//        System.out.println(totalCpu);
    }

    public void mostrarInfoSistema() {

        System.out.println(sistema.toString());
    }

}
