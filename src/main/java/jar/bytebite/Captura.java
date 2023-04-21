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

/**
 *
 * @author ViniciusJesus
 */
public class Captura {
    public static void main(String[] args) {
        //        Instãncias
        Looca looca = new Looca(); 
        
//        Falta frequencia maxima do processador
        Sistema sistema = looca.getSistema();
        Memoria memoria = looca.getMemoria();
        Processador processador = looca.getProcessador();
        DiscoGrupo discoGrupo = looca.getGrupoDeDiscos();
        JanelaGrupo janelaGrupo = looca.getGrupoDeJanelas();
        Temperatura temperatura = looca.getTemperatura();
        DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();
        List<Disco> discos = grupoDeDiscos.getDiscos();
             
        double scale = Math.pow(10, 2);
        
//        Processador
        Double frequenciaUsoCpu = processador.getUso();
        frequenciaUsoCpu = frequenciaUsoCpu * 10; 
        frequenciaUsoCpu = Math.round(frequenciaUsoCpu*scale)/scale;
        
        Double temperaturaCpu = temperatura.getTemperatura();

//        Memória Ram
        Long longMemoriaD = memoria.getDisponivel();
        double d = longMemoriaD.doubleValue();
        Double memoriaDisponivelBites = d /(1024*1024*1024);
        double ramDisponivel = Math.round(memoriaDisponivelBites*scale)/scale;

        Long longMemoriaU = memoria.getEmUso();
        double u = longMemoriaU.doubleValue();
        Double memoriaEmUsoBites = u / (1024*1024*1024);
        double ramEmUso = Math.round(memoriaEmUsoBites*scale)/scale;
        
        Double ramTotalSemFormatar = Double.valueOf(looca.getMemoria().getTotal());
        ramTotalSemFormatar = ramTotalSemFormatar / 1073141824.00;
        Double ramTotal = Math.round(ramTotalSemFormatar*scale)/scale;
        
//        Janelas
        Integer janelasTotal = looca.getGrupoDeJanelas().getTotalJanelas();

//        Armazenamento
        Long longArmazenamento = discoGrupo.getTamanhoTotal();
        double a = longArmazenamento.doubleValue();
        Double armazenamentoBites = a / (1024*1024*1024);
        double armazenamentoTotal = Math.round(armazenamentoBites*scale)/scale;
        
//        Long longArmazenamentoEmUso = discoGrupo.getDiscos().get(0).getBytesDeLeitura();
//        double aEmUso = longArmazenamentoEmUso.doubleValue();
//        Double armazenamentoEmUsoBites = aEmUso / (1024*1024*1024);
//        double armazenamentoEmUso = Math.round(armazenamentoEmUsoBites*scale)/scale;
        Double armazenamentoEmUsoSemFormatar = Double.valueOf(discoGrupo.getDiscos().get(0).getBytesDeLeitura());
        armazenamentoEmUsoSemFormatar = armazenamentoEmUsoSemFormatar / 100000000.00 - armazenamentoTotal;
        Double armazenamentoEmUso = Math.round(armazenamentoEmUsoSemFormatar*scale)/scale;
        
        
//        System.out.println(sistema.toString());
//        System.out.println("Processador Uso:");
//        System.out.println(frequenciaUsoCpu);
//        System.out.println("Temperatura processador:");
//        System.out.println(temperaturaCpu);
//        System.out.println("Memória RAM total/disponivel/uso:");
//        System.out.println(ramTotal);
//        System.out.println(ramDisponivel);
//        System.out.println(ramEmUso);
//        System.out.println("Total janelas:");
//        System.out.println(janelasTotal);
//        System.out.println("Armazenamento total/disponivel");
//        System.out.println(armazenamentoTotal);
//        System.out.println(armazenamentoEmUso);
        
        
        
        System.out.println("DEVERIA PARAR");
        
        
    }
}
