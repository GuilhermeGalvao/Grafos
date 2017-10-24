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

    public Dicionario(){
        dicionario = new String[1];
        dicionario2 = new int[1];
    }

    public Dicionario(int tamanho){
            dicionario = new String[tamanho];
            dicionario2 = new int[tamanho];
        this.tamanho = tamanho;
    }
    public int periodosFaltam(){
      int maiorPeriodo = 0;
      for(int i = 0 ; i < dicionario2.length; i ++){
        if(dicionario2[i] > maiorPeriodo){
          maiorPeriodo = dicionario2[i];
        }
      }
      return maiorPeriodo;
    }
    public void add(String algo){
        //int i = 0;
        if(!verify(algo)){
            if(contador < tamanho) {
//                vertice x = new vertice(algo, contador);
                // if( dicionario [0] != null) {
                //while (dicionario[i] != null) {
                //    i++;
                //  }
                dicionario[contador] = algo;
                contador++;
                // }else{
                //     dicionario[0] = algo;
                // }
            }
        }
    }
    public void add2(int posicao, int periodo){
        dicionario2[posicao] = periodo;
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
    // public void mostrardic2(){
    //
    //   for(int i = 0; i < 8; i ++){
    //     System.out.println(i + " =" + periodo[i]);
    //   }
    //
    // }
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



class ConstructorMatriz3 extends Dicionario{
    int [][] matriz ;
    int[][] matrizComplementar ;
    int tamanho;
    public ConstructorMatriz3(){
        matriz = new int[1][1];
        matrizComplementar = new int [1][1];
        tamanho = 1;
    }

    public ConstructorMatriz3(int tamanho){
        matriz = new int[tamanho][tamanho];
        matrizComplementar = new int [tamanho][tamanho];
        this.tamanho = tamanho;
    }

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
    public boolean temUm(int linha){
      boolean resp = true;
      for(int i = 0 ; i < matriz.length; i ++){
        if(matriz[linha][i] == 1){
          return false;
        }
      }
      return resp;
    }
    public boolean temUmColuna(int coluna){
      boolean resp = true;
      for(int i = 0 ; i < matriz.length; i ++){
        if(matriz[i][coluna] == 1){
          return false;
        }
      }
      return resp;
    }

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
    public void arestasNaoApontadas(ArrayList<String> S,Dicionario dic,int coluna){
      // ArrayList<String> list = new ArrayList<>();

    }
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
  	public boolean jaExisteDentro(String k,ArrayList<String> list){
        boolean resp = false;
        for(String x: list){
            if(k.equals(x)){
                return true;
            }
        }
        return resp;
    }
    // public ArrayList<String> periodosFromS(String y,String y2 ,ArrayList<String> S){
    //     ArrayList<String> list = new ArrayList<>();
    //     if(y2.equals(null)){
    //         for (int i = 0; i < S.size(); i++) {
    //             if (y.equals(S.get(i))) {
    //                     list.add(S.get(i));
    //             }
    //         }
    //     }else {
    //         for (int i = 0; i < S.size(); i++) {
    //             if (y.equals(S.get(i))) {
    //                 if (!y.equals(y2)) {
    //                     list.add(S.get(i));
    //                 }
    //             }
    //         }
    //     }
    //     return list;
    // }
    // public ArrayList<ArrayList<String>> periodos(ArrayList<String> lista, ArrayList<String> S){
  	//     ArrayList<ArrayList<String>> list = new ArrayList<>();
  	//     for(int i = 0; i < lista.size(); i ++){
  	//         if(i != lista.size() - 1 ) {
    //             ArrayList<String> S2 = periodosFromS(lista.get(i), lista.get(i + 1), S);
    //             list.add(S2);
    //         }else{
    //             ArrayList<String> S2 = periodosFromS(lista.get(i),null, S);
    //             list.add(S2);
    //         }
    //     }
  	//     return list ;
    // }


    public void khan(Dicionario dic){
      ArrayList<String> S = naoApontado(dic);
      ArrayList<String> L = new ArrayList<>();
      ArrayList<String> Nova = new ArrayList<>();
      String last = S.get(S.size() - 1);
      String atual = "";
      int periodo = 1;
      // ArrayList<ArrayList<String>> list;
      int n = 1;
      while(!S.isEmpty()){
        String tmp = S.remove(0);
        dic.add2(dic.search(tmp), periodo);
        // System.out.println(dic.search(dic.search(i)) + "-" + periodo);

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
    // System.out.println("---------CIMASSS----------");
      // dic.mostrardic2();


            //System.out.println("-------------------");

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
public class TP3Novo{
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
    public static void main(String [] args){
        ConstructorMatriz3 matriz = new ConstructorMatriz3(55);
        Dicionario dic = new Dicionario(55);
        Scanner ler = new Scanner(System.in);
        ConstructorMatriz3 matriz2 = new ConstructorMatriz3(8);
        Dicionario dic2 = new Dicionario(8);
        try {
            FileReader arq = new FileReader("materias.in");
//             BufferedReader lerArq = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader lerArq = new BufferedReader(arq);
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
