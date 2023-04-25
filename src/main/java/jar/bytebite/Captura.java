package jar.bytebite;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.janelas.JanelaGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.temperatura.Temperatura;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author ViniciusJesus
 */
public class Captura {

    Looca looca = new Looca();
    Sistema sistema = looca.getSistema();
    Memoria memoria = looca.getMemoria();
    Processador cpu = looca.getProcessador();
    DiscoGrupo discoGrupo = looca.getGrupoDeDiscos();
    Temperatura temperatura = looca.getTemperatura();
    double scale = Math.pow(10, 2);

    public void mostrarDados() {
        //        Processador
        Double porcUsoCpu = cpu.getUso();

//        Double temperaturaCpu = temperatura.getTemperatura();
        Double temperaturaCpu = (Math.random() * 20) + 45;

//        Memória Ram
        Long longMemoriaD = memoria.getDisponivel();
        double d = longMemoriaD.doubleValue();
        Double memoriaDisponivelBites = d / (1024 * 1024 * 1024);
        double ramDisponivel = Math.round(memoriaDisponivelBites * scale) / scale;

        Long longMemoriaU = memoria.getEmUso();
        double u = longMemoriaU.doubleValue();
        Double memoriaEmUsoBites = u / (1024 * 1024 * 1024);
        double ramEmUso = Math.round(memoriaEmUsoBites * scale) / scale;

        Double ramTotalSemFormatar = Double.valueOf(looca.getMemoria().getTotal());
        ramTotalSemFormatar = ramTotalSemFormatar / 1073141824.00;
        Double ramTotal = Math.round(ramTotalSemFormatar * scale) / scale;

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
        Double armazenamentoEmUsoSemFormatar = Double.valueOf(discoGrupo.getDiscos().get(0).getBytesDeLeitura());
        armazenamentoEmUsoSemFormatar = armazenamentoEmUsoSemFormatar / 1000000000.00;
        Double armazenamentoEmUso = Math.round(armazenamentoEmUsoSemFormatar * scale) / scale;

        System.out.println("Processador Uso:");
        System.out.println(porcUsoCpu);
        System.out.println("Temperatura processador:");
        System.out.println(temperaturaCpu);
        System.out.println("Memória RAM total/disponivel/uso:");
        System.out.println(ramTotal);
        System.out.println(ramDisponivel);
        System.out.println(ramEmUso);
        System.out.println("Total janelas:");
        System.out.println(janelasTotal);
        System.out.println("Armazenamento total/emUso");
        System.out.println(armazenamentoTotal);
        System.out.println(armazenamentoEmUso);
    }

    public void mostrarInfoSistema() {

        System.out.println(sistema.toString());
    }
}
