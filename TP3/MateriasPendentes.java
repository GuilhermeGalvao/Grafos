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
    int contador2 = 0;
    int tamanho;
    int [] dicionario2;

    /**
     * Construtor padrão
     */
    public Dicionario(){
        dicionario = new String[1];
        dicionario2 = new int[1];
    }

    /**
     * Construtor
     * @param tamanho
     */
    public Dicionario(int tamanho){
            dicionario = new String[tamanho];
            dicionario2 = new int[tamanho];
        this.tamanho = tamanho;
    }

    /**
     * Mostra quantos periodos faltam
     * @return
     */
    public int periodosFaltam(){
      int maiorPeriodo = 0;
      for(int i = 0 ; i < dicionario2.length; i ++){
        if(dicionario2[i] > maiorPeriodo){
          maiorPeriodo = dicionario2[i];
        }
      }
      return maiorPeriodo;
    }

    /**
     * adiciona no primeiro dicionario
     * @param algo
     */
    public void add(String algo){
        //int i = 0;
        if(!verify(algo)){
            if(contador < tamanho) {
                dicionario[contador] = algo;
                contador++;
            }
        }
    }

    /**
     * Adiciona no segundo dicionario
     * @param posicao
     * @param periodo
     */
    public void add2(int posicao, int periodo){
        dicionario2[posicao] = periodo;
    }

    /**
     * Procura o nome da materia e retorna a posicao dela
     * @param algo
     * @return
     */
    public int search(String algo){
        int resp = 0;
        for(int i = 0; i < dicionario.length; i ++){
            if(algo.equals(dicionario[i])) {
                resp = i;
            }
        }
        return resp;
    }

    /**
     * Procurar na posicao
     * @param algo
     * @return
     */
    public String search(int algo){
        String resp = "Do not exist";
        for(int i = 0; i < dicionario.length; i ++){
            if(algo == i){
                resp = dicionario[i];
            }
        }
        return resp;
    }

    /**
     * Verifica se ja existe
     * @param string
     * @return
     */
    public boolean verify(String string){
        boolean resp = false;
        for(int i = 0; i < dicionario.length; i ++){
            if(string.equals(dicionario[i])){
                return true;
            }
        }
        return resp;
    }

    /**
     * mostrar o dicionario
     */
    public void mostrar(){
        for(int i = 0; i < dicionario.length; i ++){
            System.out.println(dicionario[i]);
        }
    }

    /**
     * Mostra todos os periodos ainda nao funcional
     * @param peridoDesejado
     * @return
     */
    public int getPerido(int peridoDesejado){
       String [ ] periodo;
      int contadorPeriodo = 0;
       int n = 0;
      for(int i = 0; i < dicionario.length; i ++){
        if(dicionario2[i] == peridoDesejado){
          contadorPeriodo ++;
        }
      }
       periodo = new String[contadorPeriodo];
       for(int j = 0;j < dicionario.length; j ++){
         if(dicionario2[j] == peridoDesejado){
           periodo[n] = dicionario[j];
         }
    }
    return contadorPeriodo;
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

    /**
     * Construtor
     * @param tamanho
     */
    public Grafo(int tamanho){
        matriz = new int[tamanho][tamanho];
        matrizComplementar = new int [tamanho][tamanho];
        this.tamanho = tamanho;
    }

    /**
     * Mostra todas as ligações
     * @param dic
     * @param linha
     * @return
     */
    public ArrayList<String> ligacoes(Dicionario dic, int linha){
      ArrayList<String> list = new ArrayList<>();
        for(int i = 0 ; i < matriz.length; i ++){
          if(matriz[linha][i] == 1){
            String vertice = dic.search(i);
            list.add(vertice);

          }
        }
        return list;
    }

    /**
     * coloca na posição da linha e coluna um numero desejado
     * @param linha
     * @param coluna
     * @param numero
     */
    public void buildByNumber(int linha, int coluna, int numero){
        matriz[linha][coluna] = numero;
    }

    /**
     * Verifica se existe algo na posição Linha-coluna
     * @param linha
     * @param coluna
     * @return
     */
    public boolean ifExist(int linha, int coluna){
        int verificador = matriz[coluna][linha];
        if(verificador != 0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * Verifica o grau do vertice
     * @param vertice
     * @return
     */
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

    /**
     * verifica quantas posições tem 0
     * @return
     */
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

    /**
     * Metodo para verificar se o metodo está completo ou seja todas as posições tem 1
     * @return
     */
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

    /**
     * Completa a matriz com 1
     */
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

    /**
     * Metodo para mostrar o grafo
     */
    public void mostrar(){
        for(int i = 0; i < tamanho; i ++){
            for (int j = 0; j < tamanho; j++){
                if( i > j)
                    System.out.print(matriz[i][j]);
            }
            System.out.print("  ");
        }
    }

    /**
     * Metodo para mostrar o grafo ligando a matriz
     * @param dic
     */
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

    /**
     * Retira a virgula da string
     * @param string
     * @return
     */
    public String retirarVirgula(String string){
        String resp = "";

        for(int i = 0; i < tamanho - 2; i ++) {
            resp += string.charAt(i);
        }
        return resp;
    }

    /**
     * Metodo para mostrar a matriz(grafo)
     * @param dic
     */
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

    /**
     * Metodo que verifica se existe 1 na linha desejada
     * @param linha
     * @return
     */
    public boolean temUm(int linha){
      boolean resp = true;
      for(int i = 0 ; i < matriz.length; i ++){
        if(matriz[linha][i] == 1){
          return false;
        }
      }
      return resp;
    }

    /**
     * Metodo para verificar se existe o numero 1 na coluna desejada
     * @param coluna
     * @return
     */
    public boolean temUmColuna(int coluna){
      boolean resp = true;
      for(int i = 0 ; i < matriz.length; i ++){
        if(matriz[i][coluna] == 1){
          return false;
        }
      }
      return resp;
    }

    /**
     * Metodo que retorna uma ArrayList com todos os vértices não apontados
     * @param dic
     * @return
     */
    public ArrayList<String> naoApontado(Dicionario dic){
      ArrayList<String> list = new ArrayList<>();
      for(int i = 0; i < matriz.length; i ++){
        //true = nao tem 1 ou seja linha vazia
        if(temUm(i)){
           String vertice = dic.search(i);
           list.add(vertice);
        }
      }
      return list;
    }
    /**
     * Metodo para saber se existe ciclo
     * @return
     */
    public boolean ciclo(){
      boolean resp = false;
      for(int i = 0; i < matriz.length; i ++){
        for(int j = 0 ; j < matriz.length; j ++){
          if(matriz[i][j] == 1){
            return true;
          }
        }
      }
      return resp;
    }

    /**
     * Metodo verifica se a matriz está vazia
     * @return
     */
    public boolean vazio () {
  		boolean resp = true;
  		for (int i = 0; i < matriz.length; i++) {
  			for (int j = 0; j < matriz.length; j++ ) {
  				if (matriz[i][j] != 0) {
  					resp = false;
  					i = matriz.length;
  					j = matriz.length;
  				}
  			}
  		}
  		return resp;
  	}

    /**
     * Metodo para saber se existe arcos de entrada no grafo
     * @param dest
     * @return
     */
    public boolean temArcoDeEntrada(int dest) {
  		boolean resp = true;
  		// int cont = 0;

  		for (int ori = 0; ori < matriz.length ; ori++ ) {
  			if (matriz[dest][ori] != 0) {
  				return false;
  			}
  		}
  		// if (cont == matriz.length) {
  			// resp = true;
  		// }
  		return resp;
  	}

      /**
     * Metodo de khan
     * @param dic
     */
    public void khan(Dicionario dic){
      ArrayList<String> S = naoApontado(dic);
      ArrayList<String> L = new ArrayList<>();
      ArrayList<String> Nova = new ArrayList<>();
      String last = S.get(S.size() - 1);
      String atual = "";
      int periodo = 1;
      int n = 1;
      while(!S.isEmpty()){
        String tmp = S.remove(0);
        dic.add2(dic.search(tmp), periodo);

        L.add(tmp);
        for (int i = 0; i < matriz.length ; i++ ) {
        		 if (matriz[i][dic.search(tmp)] == 1) {
        			 matriz[i][dic.search(tmp)] = 0;
            if (temArcoDeEntrada(i) == true) {
                  S.add(dic.search(i));
            }
         }
       }
       if(tmp.equals(last) && S.size() != 0){
            periodo ++;
           last = S.get(S.size() -1);
        }
    }
      if (vazio() == true) {
        	for(String x : L){
            System.out.print(x + "-->");
          }
          System.out.println("");
    	} else {
    			System.out.println("CICLO");
  		}
      System.out.println("" + dic.periodosFaltam());
    }
}

/**
 * @author Guilherme Galvão de OLiveira Silva
 */
public class MateriasPendentes{
    public static boolean existe(String k, ArrayList<String> j){
      boolean resp = false;
      for(String x: j){
        // System.out.println(x + "\t" + k);
        if(k.equals(x)){
          return true;
      }
    }

    // System.out.println("Nao encontrou");


      return resp;
    }

    /**
     * Metodo main
     * @param args
     */
    public static void main(String [] args){
        Grafo matriz = new Grafo(55);
        Dicionario dic = new Dicionario(55);
        Scanner ler = new Scanner(System.in);
        Grafo matriz2 = new Grafo(8);
        Dicionario dic2 = new Dicionario(8);
        try {
            // FileReader arq = new FileReader("materias.in");
            BufferedReader lerArq = new BufferedReader(new InputStreamReader(System.in));
            // BufferedReader lerArq = new BufferedReader(arq);
            String linha; // lê a primeira linha
            linha = lerArq.readLine();
            linha = lerArq.readLine();
           // System.out.println(linha);
            ArrayList<String> list = new ArrayList<>();
            while(!linha.equals("8")){
                if(linha.contains(";")) {
                    String[] array = linha.split(";");
                    dic.add(array[0]);
                    list.add(linha);
                    linha = lerArq.readLine();
                }else{
                    dic.add(linha);
                    list.add(linha);
                    linha = lerArq.readLine();
                }
            }
            for(String x: list){
               if(x.contains(";")){
                   String array[] = x.split(";");
                   //String array[] = array2[1].split(",");
                   //System.out.println(array[0]);
                   for(int j = 1; j < array.length; j ++) {
                      //System.out.println(array[j]);
                       matriz.buildByNumber(dic.search(array[0]),dic.search(array[j]), 1 );
                   }
               }
            }

            // System.out.println("" + linha);
            linha = lerArq.readLine();
            ArrayList<String> listaNova = new ArrayList<>();
            int n = 0;
            while(n != 8){
                dic2.add(linha);
                listaNova.add(linha);
                n ++;
                linha = lerArq.readLine();
            }
            n = 0;
            for(String x: listaNova){
              ArrayList<String> teste = matriz.ligacoes(dic, dic.search(x));
              // System.out.println(""+ x +"\t"+ dic.search(x));
              for(String k: teste){
                // System.out.println(k);
                if(existe(k,listaNova)){
                  matriz2.buildByNumber(dic2.search(x), dic2.search(k), 1);
                }
              }
            }
            // matriz2.mostrar(dic2);


            lerArq.close();
            //matriz.mostrar(dic);
            //dic.mostrar();
            // ArrayList<String> lol = matriz.naoApontado(dic);
            // for(String x: lol){
            //   System.out.println(x + ":");
            // }
             matriz2.khan(dic2);
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }

        System.out.println();
    }
}
