import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.InputStream;
import java.io.InputStreamReader;
/**
  *@author Guilherme Galvão de Oliveira Silva
  *Classe para o puzzle-8
  *@param String matriz = matriz
  *@param String og = origem
*/
class p8{
  String matriz ;
  String og;

  public p8(){
    this.matriz = "";
    this.og = "";

  }

  public p8(String matriz, String str){
    this.matriz = matriz;
    this.og = str;

  }
  //clone da classe p8
  public String clon(){
    String novo = new String (matriz);

    return novo;
  }
  //adicionar a um puzzle-8 valores
  public  void add(String matriz, String add){
    this.matriz = matriz;
    this.og = add;
  }
  //clone da classe p8
  public p8 clone( ){
    return new p8(this.matriz,this.og);
  }
  //verificar se o puzzle-8 está completo
  public boolean taCompleto(){
    if(matriz.equals("123456780")){
      return true;
    }else{
      return false;
    }
  }
}
/**
  *@author Guilherme Galvão de Oliveira Silva
  *Classe para o vertice que contem puzzle-8's
  *@param vertice referencial para o pai
  *@param char cor = cor do vertice
  *@param int distancia = distancia do vertice a origem
  *@param p8 atual = puzzle-8 atual
  *@param ArrayList<p8> list = lista de puzzle-8 para guardar suas arestas
*/
class vertice{
  vertice pai;
  char cor;
  int distancia;

  p8 atual;
  ArrayList<p8> list;


  public vertice(p8 puzzle){
    this.atual = puzzle;
    this.list = new ArrayList<p8>();

    this.distancia = -1;
    this.cor ='B';
    this.pai = null;//foi comprar cigarro
  }
  //adicionar  a lista suas arestas
  public void add(p8 p){
    list.add(p);

  }



}
/**
 * No da arvore binaria
 * @author Guilherme Galvão de Oliveira Silva
 */
class No {
    public String elemento; // Conteudo do no.
    public No esq, dir;  // Filhos da esq e dir.

    /**
     * Construtor da classe.
     * @param elemento Conteudo do no.
     */
    public No(String elemento) {
        this(elemento, null, null);
    }

    /**
     * Construtor da classe.
     * @param elemento Conteudo do no.
     * @param esq No da esquerda.
     * @param dir No da direita.
     */
    public No(String elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

/**
 * Arvore binaria de pesquisa
 * @author Max do Val Machado
 */
 class ArvoreBinaria {
    private No raiz; // Raiz da arvore.

    /**
     * Construtor da classe.
     */
    public ArvoreBinaria() {
        raiz = null;
    }

    /**
     * Metodo publico iterativo para pesquisar elemento.
     * @param x Elemento que sera procurado.
     * @return <code>true</code> se o elemento existir,
     * <code>false</code> em caso contrario.
     */
    public boolean pesquisar(String x) {
        return pesquisar(x, raiz);
    }

    /**
     * Metodo privado recursivo para pesquisar elemento.
     * @param x Elemento que sera procurado.
     * @param i No em analise.
     * @return <code>true</code> se o elemento existir,
     * <code>false</code> em caso contrario.
     */
    private boolean pesquisar(String x, No i) {
      boolean resp;
        if (i == null) {
         resp = false;

      } else if (x.equals(i.elemento)) {
         resp = true;

      } else if (Integer.parseInt(x) < Integer.parseInt(i.elemento)) {
         resp = pesquisar(x, i.esq);

      } else {
         resp = pesquisar(x, i.dir);
      }
      return resp;
    }

    /**
     * Metodo publico iterativo para exibir elementos.
     */
    public void mostrarCentral() {
        System.out.print("[ ");
        mostrarCentral(raiz);
        System.out.println("]");
    }

    /**
     * Metodo privado recursivo para exibir elementos.
     * @param i No em analise.
     */
    private void mostrarCentral(No i) {
        if (i != null) {
            mostrarCentral(i.esq); // Elementos da esquerda.
            System.out.print(i.elemento + " "); // Conteudo do no.
            mostrarCentral(i.dir); // Elementos da direita.
        }
    }

    /**
     * Metodo publico iterativo para exibir elementos.
     */
    public void mostrarPre() {
        System.out.print("[ ");
        mostrarPre(raiz);
        System.out.println("]");
    }

    /**
     * Metodo privado recursivo para exibir elementos.
     * @param i No em analise.
     */
    private void mostrarPre(No i) {
        if (i != null) {
            System.out.print(i.elemento + " "); // Conteudo do no.
            mostrarPre(i.esq); // Elementos da esquerda.
            mostrarPre(i.dir); // Elementos da direita.
        }
    }

    /**
     * Metodo publico iterativo para exibir elementos.
     */
    public void mostrarPos() {
        System.out.print("[ ");
        mostrarPos(raiz);
        System.out.println("]");
    }

    /**
     * Metodo privado recursivo para exibir elementos.
     * @param i No em analise.
     */
    private void mostrarPos(No i) {
        if (i != null) {
            mostrarPos(i.esq); // Elementos da esquerda.
            mostrarPos(i.dir); // Elementos da direita.
            System.out.print(i.elemento + " "); // Conteudo do no.
        }
    }


    /**
     * Metodo publico iterativo para inserir elemento.
     * @param x Elemento a ser inserido.
     * @throws Exception Se o elemento existir.
     */
    public void inserir(String x) throws Exception {
        raiz = inserir(x, raiz);
    }

    /**
     * Metodo privado recursivo para inserir elemento.
     * @param x Elemento a ser inserido.
     * @param i No em analise.
     * @return No em analise, alterado ou nao.
     * @throws Exception Se o elemento existir.
     */
    private No inserir(String x, No i) throws Exception {
        if (i == null) {
         i = new No(x);

      } else if (Integer.parseInt(x) < Integer.parseInt(i.elemento)) {
         i.esq = inserir(x, i.esq);

      } else if (Integer.parseInt(x) > Integer.parseInt(i.elemento)) {
         i.dir = inserir(x, i.dir);

      } else {
         throw new Exception("Erro ao inserir!");
      }

        return i;
    }

    /**
     * Metodo publico iterativo para remover elemento.
     * @param x Elemento a ser removido.
     * @throws Exception Se nao encontrar elemento.
     */
     public void remover(String x) throws Exception {
        raiz = remover(x, raiz);
    }

    /**
     * Metodo privado recursivo para remover elemento.
     * @param x Elemento a ser removido.
     * @param i No em analise.
     * @return No em analise, alterado ou nao.
     * @throws Exception Se nao encontrar elemento.
     */
    private No remover(String x, No i) throws Exception {

        if (i == null) {
         throw new Exception("Erro ao remover!");

      } else if (Integer.parseInt(x) < Integer.parseInt(i.elemento)) {
         i.esq = remover(x, i.esq);

      } else if (Integer.parseInt(x) > Integer.parseInt(i.elemento)) {
         i.dir = remover(x, i.dir);

      // Sem no a direita.
      } else if (i.dir == null) {
         i = i.esq;

      // Sem no a esquerda.
      } else if (i.esq == null) {
         i = i.dir;

      // No a esquerda e no a direita.
      } else {
         i.esq = antecessor(i, i.esq);
        }

        return i;
    }

    /**
     * Metodo para trocar no removido pelo antecessor.
     * @param i No que teve o elemento removido.
     * @param j No da subarvore esquerda.
     * @return No em analise, alterado ou nao.
     */
    private No antecessor(No i, No j) {

      // Existe no a direita.
        if (j.dir != null) {
         // Caminha para direita.
            j.dir = antecessor(i, j.dir);

      // Encontrou o maximo da subarvore esquerda.
        } else {
            i.elemento = j.elemento; // Substitui i por j.
            j = j.esq; // Substitui j por j.ESQ.
        }
        return j;
    }
}
/**
  * Metodo main e outros para rodar o BFS
  * @author Guilherme Galvão de Oliveira Silva
*/


public class TP1Extra{
  /**
    *metodo main
  */
  public static void main(String[]args) {
    try{
      List<vertice> graph = new ArrayList<vertice>();
      BufferedReader lerArq = new BufferedReader(new InputStreamReader(System.in));
      String linha = lerArq.readLine();
      int numero = Integer.parseInt(linha);
      linha = lerArq.readLine();
      // String teste = linha;
      int i = 1;
      while(numero > 0){

            if(isPossble(linha)){
                p8 tmp = new p8( linha, "" );
                vertice origem = new vertice(tmp);
                // List <p8> list = movimentos(tmp);

               graph.add(origem);

               bfs(graph);
               graph.clear();
            }else{
                // System.out.println("ERRO IMPOSSIVEL DE RESOLVER TAL PUZZLE");
            }
          linha = lerArq.readLine();
          numero --;
    }
      lerArq.close();
    }
    catch (IOException e) {
       System.err.printf("Erro na abertura do arquivo: %s.\n",
       e.getMessage());
    }
  }
  /**
    *metodo de busca em largura
    *@param List<vertice> grafo = grafo para poder rodar a busca em largura
    *@param ArvoreBinaria arvore = arvore para otimizar a busca
  */
  public static void bfs(List <vertice> grafo){

    ArvoreBinaria arvore = new ArvoreBinaria();
  try{
    vertice s = grafo.get(0);
    s.cor = 'C';
    s.distancia = 0;
    s.pai = null;

    Queue <vertice> fila = new LinkedList<>();
    fila.add(s);

    while(!fila.isEmpty()){
      vertice u = fila.remove();
      List <p8> tmp = movimentos(u.atual);
      for(p8 i : tmp){
        if(!arvore.pesquisar(i.matriz)){
          u.add(i);
          vertice novo = new vertice(i);
          if(novo.cor == 'B'){
            novo.cor = 'C';
            novo.distancia = u.distancia + 1;
            novo.pai = u;
            arvore.inserir(novo.atual.matriz);
            fila.add(novo);
            grafo.add(novo);
          }
        }
          if(u.atual.taCompleto()){

            fila.clear();
            printPath(u, 0);
          }
        }
        u.cor = 'P';
      }
    }
    catch(Exception e){
      System.out.println("erro na arvore");
    }

  }
  /**
   *metodo de veriicacao para saber se o puzzle-8 já existe no grafo
   *@param boolean resp = verificacao
   *@param List<vertice> list = lista de puzzle-8
   *@param p8 String = puzzle-8 para verificacao
  */
  public static boolean contains (List<vertice> list, p8 string){
    boolean resp = false;
    for( vertice x : list ) {
      if( x.atual.equals(string.matriz) ){
        resp = true;
      }
    }
    return resp;
  }
  /**
    *Metodo para gerar movimentos a partir da chegada de possiveis movimentos do metodo de hasSwitch();
    *@param String algo = recebe possiveis movimentos do puzzle-8
    *@param String [] array = split para separar os movimentos
    *@param String [] listaMovimentos = todos os movimentos possiveis
  */
  public static List<p8> movimentos(p8 puzzle){
    String algo = hasSwitch(puzzle);
    String[]array = algo.split(",");
    String [] listaMovimentos = new String[array.length] ;

    for(int i = 0; i < array.length; i ++ ){
        listaMovimentos[i] = array[i];
    }
    p8 original = puzzle;
    List<p8> movimentos = new ArrayList<>();

    String matriz = puzzle.matriz;
    int j = 0;
    for(int i = 0; i < matriz.length(); i ++){
        if(matriz.charAt(i) == '0'){
              j = i;
        }
    }

    char n;
    for(int k = 0; k < array.length; k ++){
        char[] c = matriz.toCharArray( );
        p8 novo = new p8( );
        String novoS = "";
        if(array[k].equals("DOWN") && !puzzle.og.equals("UP") ){
          n = c[j + 3];
          c[j + 3] = '0';
          c[j] = n;
          novoS = new String(c);
          novo.add(novoS, "DOWN" );
          movimentos.add(novo);
        }else if(array[k].equals("UP") && !puzzle.og.equals("DOWN")){
          n = c[j - 3];
          c[j - 3] = '0';
          c[j] = n;
          novoS = new String(c);
          novo.add(novoS, "UP");
          movimentos.add(novo.clone( ));
        }else if(array[k].equals("RIGHT") && !puzzle.og.equals("LEFT")){
          n = c[j + 1];
          c[j + 1] = '0';
          c[j] = n;
          novoS = new String(c);
          novo.add(novoS, "RIGHT");
          movimentos.add(novo);
        } else if(array[k].equals("LEFT") && !puzzle.og.equals("RIGHT")){
          n = c[j - 1];
          c[j - 1] = '0';
          c[j] = n;
          novoS = new String(c);
          novo.add(novoS, "LEFT");
          movimentos.add(novo);
        }

  }


    //movimentos = mudar(puzzle, listaMovimentos) ;
    // for(p8 x : movimentos){
    //     mostrar(x.matriz);
    //     //System.out.println(x);
    //
    // }

    //System.out.println(movimentos.get(3).matriz);

    return movimentos;
  }
  /**
    *
  */
  public static boolean isPossble(String a){
    int contador = 0;
    boolean resp = false;
    for(int i =0; i < a.length(); i ++){
      for(int j = 0;j < a.length() ;j ++){
          if(Character.getNumericValue(a.charAt(i)) < Character.getNumericValue(a.charAt(j))){
            contador ++;
          }
      }
    }
    if(contador % 2 == 0){
      resp = true;
    }
    return resp;
  }
  /**
    *Metodo para gerar movimentos para um puzzle-8
    *@param String opcoes = guarda todas as opcoes de movimentos do puzzle-8
  */
  public static String hasSwitch(p8 puzzle){
  String opcoes = "";

  int k = 0;
  for(int i = 0; i < puzzle.matriz.length(); i ++){
    if(puzzle.matriz.charAt(i) == '0'){
      k = i;
    }
  }
  if(k == 4){
    opcoes += "UP,DOWN,RIGHT,LEFT";
  }else if(k == 0){
    opcoes += "DOWN,RIGHT";
  }else if(k == 1){
    opcoes += "DOWN,RIGHT,LEFT";
  }else if( k == 2){
    opcoes += "DOWN,LEFT";
  }else if(k == 3){
    opcoes += "DOWN,RIGHT,UP";
  }else if(k == 5){
    opcoes += "DOWN,LEFT,UP";
  }else if(k == 6){
    opcoes += "UP,RIGHT";
  }else if( k == 7){
    opcoes += "UP,RIGHT,LEFT";
  }else if( k == 8){
    opcoes += "UP,LEFT";
  }
  return opcoes;
  }
  public static void mostrar(String matriz){

    for(int i = 0; i < matriz.length(); i ++){
      if(i == 3 || i == 6 ){
          System.out.println("");
          System.out.print(matriz.charAt(i));
      }else{
          System.out.print(matriz.charAt(i));
      }
    }
  }
  /**
    *Metodo para mostrar o caminho do puzzle-8 ate a raiz dele
  */
    public static void printPath( vertice i, int contador ) {

      if( i.pai != null ) {
        printPath(i.pai, ++contador);
        System.out.println(i.atual.matriz);
      } else {
        System.out.println("" + contador);
        // System.out.println(i.atual.matriz + "\n");
    }
  }


}
