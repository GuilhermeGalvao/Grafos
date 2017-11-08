import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

class Grafos {
    int[][] matriz;
    int[][] reversa;

    public Grafos() {
        this.matriz = new int[0][0];
        this.reversa = new int[0][0];
    }

    public Grafos(int tamanho) {
        this.matriz = new int[tamanho][tamanho];
        this.reversa = new int[tamanho][tamanho];
    }

    public void adicionarGrafo(int linha, int coluna, int peso) {
        this.matriz[linha][coluna] = peso;

    }

    public void adicionarReverso(int linha, int coluna, int peso) {
        this.reversa[linha][coluna] = peso;
        this.reversa[coluna][linha] = -peso;
    }
    public int[][] getMatriz(){
        return this.matriz;
    }

}
class MaxFlow {
    static int V ;//Number of vertices in graph
    public void setV(int V){this.V = V;}

    /* Returns true if there is a path from source 's' to sink
      't' in residual graph. Also fills parent[] to store the
      path */
    boolean bfs(int rGraph[][], int s, int t, int parent[]) {
        // Create a visited array and mark all vertices as not
        // visited
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;

        // Create a queue, enqueue source vertex and mark
        // source vertex as visited
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        // Standard BFS Loop
        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (visited[v] == false && rGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        // If we reached sink in BFS starting from source, then
        // return true, else false
        return (visited[t] == true);
    }

    // Returns tne maximum flow from s to t in the given graph
    int fordFulkerson(int graph[][], int s, int t) {
        int u, v;

        // Create a residual graph and fill the residual graph
        // with given capacities in the original graph as
        // residual capacities in residual graph

        // Residual graph where rGraph[i][j] indicates
        // residual capacity of edge from i to j (if there
        // is an edge. If rGraph[i][j] is 0, then there is
        // not)
        int rGraph[][] = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];

        // This array is filled by BFS and to store path
        int parent[] = new int[V];

        int max_flow = 0;  // There is no flow initially

        // Augment the flow while tere is path from source
        // to sink
        while (bfs(rGraph, s, t, parent)) {
            // Find minimum residual capacity of the edhes
            // along the path filled by BFS. Or we can say
            // find the maximum flow through the path found.
            int path_flow = Integer.MAX_VALUE;
//            System.out.println("path_flow = " + path_flow );
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);

            }

            // update residual capacities of the edges and
            // reverse edges along the path
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;

            }

            // Add path flow to overall flow
            max_flow += path_flow;
        }

        // Return the overall flow
        return max_flow;
    }
    int floydFurkenson2(int [][] grafo, int s, int t){
        int final2 = t;
        int v, u;
        int flowMax = 0;
        //grafo reverso
        int  rgrafo[][] = new int [V][V];
        int [] parents = new int [V];
//
//        for (u = 0; u < V; u++)
//            for (v = 0; v < V; v++)
//            rgrafo[u][v] = grafo[u][v];

        while(bfs(grafo, s, t, parents )){
            int path_flow = Integer.MAX_VALUE;
            for(v = t; v!= s; v = parents[v]){
                u= parents[v];
                path_flow = Math.min(path_flow, grafo[u][v]);
//               System.out.println(" path_flow " + path_flow);

            }
            for(v = t; v!= s; v = parents[v]){
                u= parents[v];
                if(grafo[u][v] != 0){
//                    System.out.println(grafo[u][v]);
                    grafo[u][v] -= path_flow;
//                    System.out.println(grafo[u][v]);
//                    System.out.println("r"+rgrafo[u][v]);
                    rgrafo[u][v] += path_flow;
//                    System.out.println("r"+rgrafo[u][v]);

                }else{
//                    System.out.println(grafo[u][v]);
                    grafo[u][v] += path_flow;
//                    System.out.println(grafo[u][v]);
//                    System.out.println("r"+rgrafo[u][v]);
                    rgrafo[u][v] += path_flow;
//                    System.out.println("r"+rgrafo[u][v]);

                }
            }



        }
//        for(int i = 0; i < V; i ++){
//            System.out.println("");
//            for(int j = 0; j < V; j ++){
//                System.out.print(rgrafo[i][j] + "\t");
//            }
//        }
//        System.out.println("");
//
//        for(int i = 0; i < V; i ++){
//            System.out.println("");
//            for(int j = 0; j < V; j ++){
//                System.out.print(grafo[i][j] + "\t");
//            }
//        }
        for(int k = 0; k < V; k ++){
            flowMax += rgrafo[k][final2];
        }


        return flowMax;
    }

}

public class TP5 {
    public static void main(String [] args){
        Grafos grafo;
        MaxFlow m = new MaxFlow();
        try {
            FileReader arq = new FileReader("Arestas.in");
//             BufferedReader lerArq = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader lerArq = new BufferedReader(arq);
            String linha; // lê a primeira linha
            String resp = "";
            linha = lerArq.readLine();
            // grafo = new Grafos(Integer.parseInt(linha));
            int tamanho = Integer.parseInt(linha);
            m.setV(Integer.parseInt(linha));
            linha = lerArq.readLine();
            //Seta o inicio
            linha = lerArq.readLine();
            String [] inicio2 = linha.split(",");
            int inicio = 0; ;
            if(inicio2.length == 1){
              inicio = Integer.parseInt(linha);
            }
            //seta o fim
            linha = lerArq.readLine();
            String [] fim2 = linha.split(",");
            int fim = 0;
            if(fim2.length == 1){
              fim = Integer.parseInt(linha);
            }
            //modificar matriz para vertices iniciais e finais(multiplos)
            if(inicio2.length > 1 && fim2.length > 1){
              resp = "i/f";
              grafo = new Grafos(tamanho + 2);
            }else if(inicio2.length > 1 ){
              resp = "i";
              grafo = new Grafos(tamanho + 1);
            }else if (fim2.length > 1){
              resp = "f";
              grafo = new Grafos(tamanho + 1);
            }else {
              grafo = new Grafos(tamanho);
            }
            //pegar as arestas
            linha = lerArq.readLine();
            while(!linha.equals("FIM")){
                String [] array = linha.split(",");
                if(inicio2.length > 1 && fim2.length > 1){
                  grafo.adicionarGrafo(Integer.parseInt(array[0]) + 1,Integer.parseInt(array[1]) + 1,Integer.parseInt(array[2]) + 1);
                }else if(fim2.length > 1 && inicio2.length == 1){
                  grafo.adicionarGrafo(Integer.parseInt(array[0]),Integer.parseInt(array[1]),Integer.parseInt(array[2]));
                }else if(fim2.length  == 1 && inicio2.length > 1){
                  grafo.adicionarGrafo(Integer.parseInt(array[0]) + 1,Integer.parseInt(array[1]) + 1,Integer.parseInt(array[2]) + 1);
                }else{
                  grafo.adicionarGrafo(Integer.parseInt(array[0]),Integer.parseInt(array[1]),Integer.parseInt(array[2]));
                }
                linha = lerArq.readLine();


            }
            if(resp.equals("i/f")){
              for(int i = 0 ; i < inicio2.length; i++){
                  grafo.adicionarGrafo(0, Integer.parseInt(inicio2[i]), Integer.MAX_VALUE);
              }
              for(int j = 0 ; j < inicio2.length; j++){
                  grafo.adicionarGrafo(tamanho - 1, Integer.parseInt(fim2[j]), Integer.MAX_VALUE);
              }
                  int maxflow = m.fordFulkerson(grafo.getMatriz(), 0, tamanho -1);
                  int maxFlow2 = m.floydFurkenson2(grafo.getMatriz(), 0, tamanho - 1);
                  System.out.println("MaxFlow = " + maxflow);
                  System.out.println("MaxFlow2 = " + maxFlow2);
            }else if(resp.equals("i")){

                for(int i = 0 ; i < inicio2.length; i++){
                    grafo.adicionarGrafo(0, Integer.parseInt(inicio2[i]), Integer.MAX_VALUE);
                }
                int maxflow = m.fordFulkerson(grafo.getMatriz(), 0, fim);
                int maxFlow2 = m.floydFurkenson2(grafo.getMatriz(), 0, fim);
                System.out.println("MaxFlow = " + maxflow);
                System.out.println("MaxFlow2 = " + maxFlow2);
            }else if(resp.equals("f")){

              for(int j = 0 ; j < inicio2.length; j++){
                  grafo.adicionarGrafo(tamanho - 1, Integer.parseInt(fim2[j]), Integer.MAX_VALUE);
              }
              int maxflow = m.fordFulkerson(grafo.getMatriz(), inicio, tamanho -1);
              int maxFlow2 = m.floydFurkenson2(grafo.getMatriz(), inicio, tamanho - 1);
              System.out.println("MaxFlow = " + maxflow);
              System.out.println("MaxFlow2 = " + maxFlow2);
            }else{
              int maxflow = m.fordFulkerson(grafo.getMatriz(), inicio, fim);
              int maxFlow2 = m.floydFurkenson2(grafo.getMatriz(), inicio, fim);
              System.out.println("MaxFlow = " + maxflow);
              System.out.println("MaxFlow2 = " + maxFlow2);
            }



        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }

    }
}
