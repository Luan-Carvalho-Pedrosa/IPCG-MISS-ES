package tzin_ltda.ipcg_missoes.operation.comon;

public class PercentCalculator {

    public static double calcularPresenca(int presentes, int ausentes){

    
        return ((double) presentes / (presentes + ausentes)) * 100;
    }
    
}
