
import java.util.Map;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;


class Astar {
  public PriorityQueue<Node> queue;
  private Tabuleiro inicial;
  private Tabuleiro target;
  private long startTime;

  //contadores
  private int nosGerados;
  private int nosVisitados;

  public Astar(Tabuleiro inicial,Tabuleiro target,long time){
    queue = new PriorityQueue<Node>();
    this.inicial = inicial;
    this.target = target;
    nosGerados = 0;
    nosVisitados = 0;
    startTime = time;
  }

  public void generalSearchAlgorithm(){
    queue.add(new Node(inicial,0,0,target));
    ++nosGerados;
    while (!queue.isEmpty()) {
      Node node = queue.poll();
      ++nosVisitados;
      if (node.tabu.equals(target)) {
        System.out.println("Numero minimo de jogadas encontradas:"+" "+node.altura+" em "+(double)(System.currentTimeMillis() - startTime) / 1000.0 + " segundos.");
        System.out.println("Numero de nos visitados: "+nosVisitados+" \nNumero de nos gerados: "+nosGerados);
        node.tabu.caminhoPrint(node.altura);
        return;
      }
      else{
          for(Tabuleiro tabu: node.tabu.makeDescendents(null)){
            ++nosGerados;
            Node aux = new Node(tabu, node.altura+1,0,target);
            queue.add(aux);
          }
        }

    }
    System.err.println("Erro: Solucao nao encontrada!!!");
  }
}

class Gulosa {
  public PriorityQueue<Node> queue;
  private Tabuleiro inicial;
  private Tabuleiro target;
  private long startTime;

  //contadores
  private int nosGerados;
  private int nosVisitados;

  public Gulosa(Tabuleiro inicial,Tabuleiro target,long time){
    queue = new PriorityQueue<Node>();
    this.inicial = inicial;
    this.target = target;
    nosGerados = 0;
    nosVisitados = 0;
    startTime = time;
  }

  public void generalSearchAlgorithm(){
    queue.add(new Node(inicial,0,1,target));
    ++nosGerados;
    while (!queue.isEmpty()) {
      Node node = queue.poll();
      ++nosVisitados;
      if (node.tabu.equals(target)) {
        System.out.println("Numero minimo de jogadas encontradas:"+" "+node.altura+" em "+(double)(System.currentTimeMillis() - startTime) / 1000.0 + " segundos.");
        System.out.println("Numero de nos visitados: "+nosVisitados+" \nNumero de nos gerados: "+nosGerados);
        node.tabu.caminhoPrint(node.altura);
        return;
      }
      else{
          for(Tabuleiro tabu: node.tabu.makeDescendents(null)){
            ++nosGerados;
            queue.add(new Node(tabu, node.altura+1,1,target));
          }
        }
    }
    System.err.println("Erro: Solucao nao encontrada!!!");
  }
}

class Dfs {
  public LinkedList<Node> queue;
  private Tabuleiro inicial;
  private Tabuleiro target;
  public HashSet<Tabuleiro> mapa = null;
  private long startTime;

  //contadores
  private int nosGerados;
  private int nosVisitados;

  public Dfs(Tabuleiro inicial,Tabuleiro target,long time){
    queue = new LinkedList<Node>();
    mapa = new HashSet<Tabuleiro>();
    this.inicial = inicial;
    this.target = target;
    nosGerados = 0;
    nosVisitados = 0;
    startTime = time;
  }

  public void generalSearchAlgorithm(){
    queue.add(new Node(inicial,0,3,target));
    ++nosGerados;
    while (!queue.isEmpty()) {
      Node node = queue.poll();
      ++nosVisitados;
      mapa.add(node.tabu);
      if (node.tabu.equals(target)) {
        System.out.println("Numero minimo de jogadas encontradas:"+" "+node.altura+" em "+(double)(System.currentTimeMillis() - startTime) / 1000.0 + " segundos.");
        System.out.println("Numero de nos visitados: "+nosVisitados+" \nNumero de nos gerados: "+nosGerados);
        node.tabu.caminhoPrint(node.altura);
        return;
      }
      else{
            boolean flag = false;
            for(Tabuleiro tabu: node.tabu.makeDescendents(mapa)){
              flag = true;
              ++nosGerados;
              queue.addFirst(new Node(tabu, node.altura+1,3,target));
            }
            if(!flag){
              node.tabu.pai = null;
              mapa.remove(node.tabu);
            }
        }
    }
    System.err.println("Erro: Solucao nao encontrada!!!");
  }
}

class IDfs {
  public LinkedList<Node> queue;
  private Tabuleiro inicial;
  private Tabuleiro target;
  public HashSet<Tabuleiro> mapa = null;
  private int max;
  private long startTime;

  //contadores
  private int nosGerados;
  private int nosVisitados;

  public IDfs(Tabuleiro inicial,Tabuleiro target, int max, long time){
    queue = new LinkedList<Node>();
    this.inicial = inicial;
    this.target = target;
    this.max = max;
    startTime = time;
    nosGerados = 0;
    nosVisitados = 0;
  }
  public void generalSearchAlgorithm(){
    for (int i = 1;i<max ;++i ) {
      Integer resul = generalSearchAlgorithm(i);
      if(resul != null){
        break;
      }
    }
  }

  private Integer generalSearchAlgorithm(int x){
    queue.add(new Node(inicial,0,3,target));
    ++nosGerados;
    while (!queue.isEmpty()) {
      Node node = queue.poll();
      ++nosVisitados;
      if (node.tabu.equals(target)) {
        System.out.println("Numero minimo de jogadas encontradas:"+" "+node.altura+" em "+(double)(System.currentTimeMillis() - startTime) / 1000.0 + " segundos.");
        System.out.println("Numero de nos visitados: "+nosVisitados+" \nNumero de nos gerados: "+nosGerados);
        node.tabu.caminhoPrint(node.altura);
        return node.altura;
      }
      else{
          if(node.altura<x)
            for(Tabuleiro tabu: node.tabu.makeDescendents(mapa)){
              ++nosGerados;
              queue.addFirst(new Node(tabu, node.altura+1,3,target));
            }
      }
    }
    return null;
  }
}

class Bfs {
  public Queue<Node> queue;
  private Tabuleiro inicial;
  private Tabuleiro target;
  private long startTime;

  //contadores
  private int nosGerados;
  private int nosVisitados;

  public Bfs(Tabuleiro inicial,Tabuleiro target, long time){
    queue = new LinkedList<Node>();
    this.inicial = inicial;
    this.target = target;
    nosGerados = 0;
    nosVisitados = 0;
    startTime = time;
  }

  public void generalSearchAlgorithm(){
    queue.add(new Node(inicial,0,3,target));
    ++nosGerados;
    while (!queue.isEmpty()) {
      Node node = queue.poll();
      ++nosVisitados;
      if (node.tabu.equals(target)) {
        System.out.println("Numero minimo de jogadas encontradas:"+" "+node.altura+" em "+(double)(System.currentTimeMillis() - startTime) / 1000.0 + " segundos.");
        System.out.println("Numero de nos visitados: "+nosVisitados+" \nNumero de nos gerados: "+nosGerados);
        node.tabu.caminhoPrint(node.altura);
        return;
      }
      else{
            for(Tabuleiro tabu: node.tabu.makeDescendents(null)){
              Node aux = new Node(tabu, node.altura+1,3,target);
              ++nosGerados;
              queue.add(aux);
            }
      }
    }
    System.err.println("Erro: Solucao nao encontrada!!!");
  }
}
