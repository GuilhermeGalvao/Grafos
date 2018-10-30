import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.InputStream;
import java.io.InputStreamReader;

class Dicionario {
    String [] dicionario;
    int contador = 0;
    int tamanho;

    public Dicionario(){
        dicionario = new String[1];
    }

    public Dicionario(int tamanho){
            dicionario = new String[tamanho];
        this.tamanho = tamanho;
    }
    public void add(String algo){
        if(!verify(algo)){
            if(contador < tamanho) {
                dicionario[contador] = algo;
                contador++;
            }
        }
    }
    public int search(String algo){
        int resp = 0;
        for(int i = 0; i < dicionario.length; i ++){
            if(algo.equals(dicionario[i])) {
                resp = i;
            }
        }
        return resp;
    }
    public String search(int algo){
        String resp = "Do not exist";
        for(int i = 0; i < dicionario.length; i ++){
            if(algo == i){
                resp = dicionario[i];
            }
        }
        return resp;
    }
    public boolean verify(String string){
        boolean resp = false;
        for(int i = 0; i < dicionario.length; i ++){
            if(string.equals(dicionario[i])){
                return true;
            }
        }
        return resp;
    }
    public void mostrar(){
        for(int i = 0; i < dicionario.length; i ++){
            System.out.println(dicionario[i]);
        }
    }

}


class Grafo extends Dicionario{
    int [][] matriz ;
    int[][] matrizComplementar ;
    int tamanho;
    public Grafo(){
        matriz = new int[1][1];
        matrizComplementar = new int [1][1];
        tamanho = 1;
    }

    public Grafo(int tamanho){
        matriz = new int[tamanho][tamanho];
        matrizComplementar = new int [tamanho][tamanho];
        this.tamanho = tamanho;
    }
    public void buildByNumber(int linha, int coluna, int numero){
        matriz[linha][coluna] = numero;
    }
    public boolean ifExist(int linha, int coluna){
        int verificador = matriz[coluna][linha];
        if(verificador != 0){
            return true;
        }else {
            return false;
        }
    }
    public int verificarG(int vertice){
        int contador = 0;
        int verificador ;
        for(int j = 0; j < tamanho; j ++){
            verificador = matriz[j][vertice];
            if(verificador != 0){
                contador ++;
            }

        }
        return contador;
    }
    public int verificarQV(){
        int contador = 0;
        int verificador ;
        for(int i = 0; i < tamanho; i ++) {
            for (int j = 0; j < tamanho; j++) {
                verificador = matriz[i][j];
                if (verificador != 0) {
                    contador++;
                }

            }
        }
        return contador;
    }
    public boolean verificarCompleto(){
        boolean resp = true;
        int verificador ;
        for(int i = 0; i < tamanho; i ++) {
            for (int j = 0; j < tamanho; j++) {
                if(i != j) {
                    verificador = matriz[i][j];
                    if (verificador == 1) {
                        resp = false;
                    }
                }

            }
        }
        return false;
    }
    public void completarMatrizUmZero(){
        int verificador;
        int um = 1;
        for(int i = 0; i < tamanho; i ++){
            for (int j = 0; j < tamanho; j++){
                verificador = matriz[i][j];
                if( i < j) {
                    if (verificador == 0) {
                        System.out.println(i + "," + j + "," + um);
                    }
                }
            }
        }
    }
    public void mostrar(){
        for(int i = 0; i < tamanho; i ++){
            for (int j = 0; j < tamanho; j++){
                if( i > j)
                    System.out.print(matriz[i][j]);
            }
            System.out.print("  ");
        }
    }
    public void mostrar1(Dicionario dic){
        for(int i = 0; i < tamanho; i ++){
            for (int j = 0; j < tamanho; j++){
                if( i > j)
                    if(matriz[i][j] == 1)
                    System.out.print(dic.dicionario[i] + dic.dicionario[j] + matriz[i][j]);
            }
            System.out.print("  ");
        }
    }
    public String retirarVirgula(String string){
        String resp = "";

        for(int i = 0; i < tamanho - 2; i ++) {
            resp += string.charAt(i);
        }
        return resp;
    }

    public void mostrar(Dicionario dic){
        String resp = "";
        ArrayList<String> list = new ArrayList<>();
       for(int i =0; i < tamanho; i ++){
           System.out.print(dic.dicionario[i] + ";");
           for(int j =0; j < tamanho; j ++) {
               if (matriz[i][j] == 1) {
                   resp += dic.dicionario[j] + ",";
               }
           }

            String [] array = resp.split(",");
           String modificada = "";
               if(dic.dicionario[i].equals("Iniciação à Pesquisa")){
                  modificada += array[1] + "," + array[0];
                }else{
                   if(array.length == 1) {
                       modificada += array[0];
                   }else{
                       modificada += array[0] + "," + array[1];
                   }
               }
           System.out.print(modificada);
           resp = "";
           System.out.println("");
       }
    }

}
public class TP2{

    public static void main(String [] args){
        Grafo matriz = new Grafo(11);
        Dicionario dic = new Dicionario(11);
        Scanner ler = new Scanner(System.in);
        try {
            BufferedReader lerArq = new BufferedReader(new InputStreamReader(System.in));
            String linha; // lê a primeira linha
            linha = lerArq.readLine();
            ArrayList<String> list = new ArrayList<>();
            while(linha != null){
                String [] array = linha.split(";");
                dic.add(array[0]);
                list.add(linha);
                linha = lerArq.readLine();
            }
            for(String x: list){
               if(x.charAt(x.length() - 1) != ';'){
                   String array2[] = x.split(";");
                   String array[] = array2[1].split(",");
                   for(int j = 0; j < array.length; j ++) {
                       matriz.buildByNumber(dic.search(array2[0]),dic.search(array[j]), 1 );
                   }
               }
            }
            lerArq.close();
            matriz.mostrar(dic);
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }

        System.out.println();
    }
}

