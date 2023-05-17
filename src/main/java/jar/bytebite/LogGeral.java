/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jar.bytebite;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.temperatura.Temperatura;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.crypto.Data;
import java.time.LocalDateTime;

/**
 *
 * @author BeatrizHellenCavalca
 */
public class LogGeral {

    Componente componente = new Componente();
    Captura captura = new Captura();
    Looca looca = new Looca();

    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String dataFormatada = dateFormat.format(date);
    public Double ramTotal = componente.ramTotal;
    public Double ramDisponivel = captura.ramDisponivel;
    public Double armazenamentoTotal = componente.armazenamentoTotal;
    public Long armazenamentoUso = componente.longArmazenamento;
    public Double totalCpu = componente.totalCpu;
    public Double porcUsoCpu = captura.porcUsoCpu;
    public String fabricante = looca.getSistema().getFabricante();
    public Integer arquitetura = looca.getSistema().getArquitetura();
    public Integer janelas = looca.getGrupoDeJanelas().getTotalJanelas();
    public Temperatura temperatura = looca.getTemperatura();
    public String sistemaOperacional = looca.getSistema().getSistemaOperacional();
//    Path path = Paths.get("C:/Logs-ByteBite/");
    Path path = Paths.get("C:/Logs-ByteBite/Geral/");

    public void genereteInfos() throws IOException {

        String mensagem = "\n\n_____________________Informações fixas_____________________\n"
                + "\nSistema Opéracional                       >> " + sistemaOperacional
                + "\nFabricante                                >> " + fabricante
                + "\nArquitetura                               >> " + arquitetura
                + "\n\n______________Informações do sistema de captura_____________"
                + "\nRAM TOTAL                                 >>" + ramTotal + " GB"
                + "\nRAM DISPONÍVEL                            >>" + ramDisponivel + " GB"
                + "\nCPU TOTAL                                 >>" + totalCpu + " GHz"
                + "\nCPU EM USO                                >>" + porcUsoCpu + " GHz"
                + "\nTEMPERATURA DA CPU                        >>" + temperatura + " C°"
                + "\nARMAZENAMENTO TOTAL                       >>" + armazenamentoTotal + "GB"
                + "\nARMAZENAMENTO EM USO                      >>" + armazenamentoUso + " GB"
                + "\nJANELAS ABERTAS                           >> " + janelas + " U";

        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }

        File log = new File(String.format("C:/Logs-ByteBite/Geral/%s.txt", dataFormatada));

        if (!log.exists()) {
            log.createNewFile();
        }

        FileWriter fw = new FileWriter(log, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(mensagem);
        bw.newLine();
        bw.close();
        fw.close();
    }

    public void genereteErroLogin() throws IOException {

        String mensagem = "--------------------------------------------------------------\n"
                + "Data da captura :" + date
                + "\nFalha >> Login preenchido incorretamente";

//        Path path = Paths.get("C:/Logs-ByteBite/");
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        File log = new File(String.format("C:/Logs-ByteBite/Geral/%s.txt", dataFormatada));
        if (!log.exists()) {
            log.createNewFile();
        }

        FileWriter fw = new FileWriter(log, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(mensagem);
        bw.newLine();
        bw.close();
        fw.close();
    }

    public void genereteLoginSucesso() throws IOException {

        String mensagem = "--------------------------------------------------------------\n"
                + "Data da captura :" + date
                + "\nSucesso >> Login realizado com sucesso!";

//        Path path = Paths.get("C:/Logs-ByteBite/");
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        File log = new File(String.format("C:/Logs-ByteBite/Geral/%s.txt", dataFormatada));

        if (!log.exists()) {
            log.createNewFile();
        }

        FileWriter fw = new FileWriter(log, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(mensagem);
        bw.newLine();
        bw.close();
        fw.close();
    }

    private Date Date() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
