## Grafos com Java
grafo com matriz de adjacências e lista de adjacências em Java

# MATRIZ 1 
```cmd
Lista de arestas:
Aresta de Sao Paulo para Jundiai
Aresta de Sao Paulo para Limeira
Aresta de Jundiai para Sao Paulo
Aresta de Jundiai para Campinas
Aresta de Limeira para Sao Paulo
Aresta de Campinas para Jundiai

E digrafo: true

Grau de cada aresta:
Aresta de Sao Paulo para Jundiai possui grau 1
Aresta de Sao Paulo para Limeira possui grau 1
Aresta de Jundiai para Sao Paulo possui grau 1
Aresta de Jundiai para Campinas possui grau 1
Aresta de Limeira para Sao Paulo possui grau 1
Aresta de Campinas para Jundiai possui grau 1

Grafo Conexo
Grafo Ciclico

Lista de adjacencias
Sao Paulo:[1, 2]
Jundiai:[0, 3]
Limeira:[0]
Campinas:[1]
```

# MATRIZ 2 
```cmd
Matriz criada por k
2  3  8  1
2  1  2  3
9  2  7  6
0  7  6  9

Conversao para lista de adjacencias
0:[0, 1, 2, 3]
1:[0, 1, 2, 3]
2:[0, 1, 2, 3]
3:[1, 2, 3]
```


# MATRIZ 3 
```cmd
Arestas por par ordenado com respectivo peso
Aresta (1, 0) peso: 1
Aresta (2, 1) peso: 1
Aresta (3, 1) peso: 1
Aresta (3, 2) peso: 1
Aresta (4, 0) peso: 1
Aresta (4, 1) peso: 1
Aresta (4, 3) peso: 1
Aresta (5, 2) peso: 1
Aresta (5, 3) peso: 1
Aresta (5, 4) peso: 1
```

# MATRIZ 4
```cmd
Matriz triangular incompleta
0
1, 0
0, 1, 0
0, 1, 1, 0
1, 1, 0, 1, 0
0, 0, 1, 1, 1, 0

Matriz triangular completada
0  1  0  0  1  0
1  0  1  1  1  0
0  1  0  1  0  1
0  1  1  0  1  1
1  1  0  1  0  1
0  0  1  1  1  0
```


# App.java
```java
/**
 * 23/04/2024
 * @author Joao Enrique Barbosa Santos Alves
 */ 

public class App {
    public static void main(String[] args) throws Exception {
        int[][] matriz = {
            {0, 1, 1, 0},
            {1, 0, 0, 1},
            {1, 0, 0, 0},
            {0, 1, 0, 0}
        };
        
        int[][] matrizPonderada = {
            {0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 0},
            {1, 1, 0, 1, 0, 0},
            {0, 0, 1, 1, 1, 0}
        };

        String[] vertices = {"Sao Paulo", "Jundiai", "Limeira", "Campinas"};
        Grafo<String> grafo = new Grafo<String>(matriz, vertices);

        // a) Quais são as arestas do grafo?
        System.out.println("MATRIZ 1 \nLista de arestas");
        grafo.mostrarArestas();
        
        // b) É um dígrafo ou um grafo não-direcionado?
        System.out.println("E digrafo: " + grafo.ehDigrafo());

        // c) Qual é o grau de cada vértice?
        System.out.println("\nGrau de cada aresta");
        grafo.mostrarGrauArestas();

        // d) O grafo é conexo ou desconexo?
        if(grafo.ehConexo()) System.out.println("Grafo Conexo");
        else System.out.println("Grafo Desconexo");

        // e) O grafo é cíclico ou acíclico?
        if(grafo.ehCiclico()) System.out.println("Grafo Ciclico\n");
        else System.out.println("Grafo Aciclico\n");

        // f) Qual é a lista de adjacências do mesmo grafo?
        System.out.println("Lista de adjacencias");
        grafo.mostrarListaAdjacencias();
        System.out.println();

        /*
         * 2) Escreva um programa que receba como entrada um número inteiro ncorrespondente ao número de vértices e apresente como saída a matriz  e a lista de adjacências para o 
         * grafo completo Kn 
         */
        
        System.out.println("MATRIZ 2");
        Grafo<Integer> grafo2 = new Grafo<Integer>(4);
        System.out.println("Matriz criada por k");
        grafo2.mostrarMatriz();

        System.out.println();
        System.out.println("Conversao para lista de adjacencias");
        grafo2.mostrarListaAdjacencias();
        System.out.println();

        /*
         * 3) Escreva um programa que receba como entrada uma matriz de adjacências de um grafo simples, ponderado e conexo e escreva como saída as arestas (na forma de pares 
         * ordenados e seus pesos).
         */
        
        System.out.println("MATRIZ 3");
        Grafo<Integer> grafo3 = new Grafo<Integer>(matrizPonderada);
        System.out.println("Arestas por par ordenado com respectivo peso");
        grafo3.mostrarArestasComoParOrdenado();
        System.out.println();

        /* 4)  Escreva  um  programa querecebacomo  entrada uma  matriz  de  adjacências  para  o  grafo  não-direcionado  em sua  forma  triangular  inferior, conforme  
         * ilustrado  abaixoe  retorne  a  matriz completa, obtendo a parte superior considerando a simetria. 
         */

         
        System.out.println("MATRIZ 4");
        Grafo<Integer> grafo4 = new Grafo<Integer>(matrizPonderada, true);
        System.out.println("Matriz triangular completada");
        grafo4.mostrarMatriz();

    }
}
```

# Grafo.java
```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Grafo<TIPO> {
    private int[][] matriz;
    private TIPO[] vertices;
    private ArrayList<ArrayList<Integer>> listaAdjacencias;

    // cria grafo por matriz
    public Grafo(int[][] matriz, TIPO[] vertices){
        this.matriz = matriz;
        this.vertices = vertices;
        this.listaAdjacencias = new ArrayList<ArrayList<Integer>>();
        this.listaAdjacencias = this.getListaAdjacencias();
    }

    // cria grafo por nao ponderado por k
    public Grafo(int k){
        this.matriz = this.gerarMatriz(k);
        this.vertices = this.gerarVertices(k);
        this.listaAdjacencias = new ArrayList<ArrayList<Integer>>();
        this.listaAdjacencias = this.getListaAdjacencias();
    }

    // cria grafo ponderado simples por matriz
    public Grafo(int[][] matriz){
        this.matriz = matriz;
        this.vertices = this.gerarVertices(matriz.length);
        this.listaAdjacencias = new ArrayList<ArrayList<Integer>>();
        this.listaAdjacencias = this.getListaAdjacencias();
    }

    Grafo(int[][] matrizTriangular, boolean triangularInferior){
        this.matriz = this.completarMatriz(matrizTriangular);
    }

    public int[][] completarMatriz(int[][] matrizTriangular){
        int[][] matrizCompleta = new int[matrizTriangular.length][matrizTriangular.length];
        for (int i = 0; i < matrizTriangular.length; i++) {
            for (int j = 0; j < matrizTriangular.length; j++) {
                matrizCompleta[i][j] = matrizTriangular[i][j];
                matrizCompleta[j][i] = matrizTriangular[i][j];
            }
        }

        return matrizCompleta;
    }

    public void mostrarArestasComoParOrdenado(){
        String arestas = "";
        
        for (int i=0; i<matriz.length; i++) {
            for (int j=0; j<matriz[i].length; j++) {
                if(matriz[i][j] >= 1)
                    arestas+= "Aresta (" + i + ", " + j + ") peso: " + matriz[i][j] + "\n";
            }

        }
        System.out.println(arestas);
    }

    public void mostrarArestas(){
        String arestas = "";
        
        for (int i=0; i<matriz.length; i++) {
            for (int j=0; j<matriz[i].length; j++) {
                if(matriz[i][j] >= 1)
                    arestas+= "Aresta de " + this.vertices[i] + " para " + this.vertices[j] + "\n";
            }

        }
        System.out.println(arestas);
    }

    public void mostrarGrauArestas(){
        String grau = "";
        
        for (int i=0; i<matriz.length; i++) {
            for (int j=0; j<matriz[i].length; j++) {
                if(matriz[i][j] > 0)
                    grau+= "Aresta de " + this.vertices[i] + " para " + this.vertices[j] + " possui grau " + matriz[i][j]  + "\n";
            }

        }

        System.out.println(grau);
    }

    public boolean ehDigrafo(){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if(matriz[i][j] != matriz[j][i])
                    return false;
            }
        }
        return true;
    }

    public ArrayList<ArrayList<Integer>> getListaAdjacencias() {
        ArrayList<ArrayList<Integer>> listaAdjacencias = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < matriz.length; i++) {
            ArrayList<Integer> listaInterna = new ArrayList<>();
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] > 0) {
                    listaInterna.add(j);
                }
            }
            listaAdjacencias.add(listaInterna);
        }

        return listaAdjacencias;
    }

    public void mostrarMatriz(){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++)
                System.out.printf("%3d", matriz[i][j]);
            System.out.println();
        }
    }

    public void mostrarListaAdjacencias(){
        for (int i = 0; i < this.listaAdjacencias.size(); i++) {
            System.out.println(vertices[i]  + ":" + listaAdjacencias.get(i));
        }
    }

    // gera matriz por k
    private int[][] gerarMatriz(int k){
        int[][] matriz = new int[k][k];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = (int) (Math.random() * 10);
            }
        }

        return matriz;
    }

    // gera vertices por pela matriz
    @SuppressWarnings("unchecked")
    private TIPO[] gerarVertices(int k) {
        TIPO[] vertices = (TIPO[]) new Object[k];

        for (int i = 0; i < k; i++) {
            vertices[i] = (TIPO) Integer.valueOf(i);
        }
        return vertices;
    }


    // Método para verificar se o grafo é cíclico (utiliza DFS)
    public boolean ehCiclico() {
        boolean[] visitados = new boolean[vertices.length];
        boolean[] pilhaRecursao = new boolean[vertices.length];

        for (int i = 0; i < vertices.length; i++) {
            if (ehCiclicoUtil(i, visitados, pilhaRecursao)) {
                return true;
            }
        }
        return false;
    }

    private boolean ehCiclicoUtil(int v, boolean[] visitados, boolean[] pilhaRecursao) {
        if (!visitados[v]) {
            visitados[v] = true;
            pilhaRecursao[v] = true;

            for (int vizinho : listaAdjacencias.get(v)) {
                if (!visitados[vizinho] && ehCiclicoUtil(vizinho, visitados, pilhaRecursao)) {
                    return true;
                } else if (pilhaRecursao[vizinho]) {
                    return true;
                }
            }
        }
        pilhaRecursao[v] = false;
        return false;
    }

    // Método para verificar se o grafo é conexo (utiliza BFS)
    public boolean ehConexo() {
        boolean[] visitados = new boolean[vertices.length];
        Queue<Integer> fila = new LinkedList<>();
        fila.add(0); // Começar a busca a partir do vértice 0

        while (!fila.isEmpty()) {
            int vertice = fila.poll();
            visitados[vertice] = true;

            for (int vizinho : listaAdjacencias.get(vertice)) {
                if (!visitados[vizinho]) {
                    fila.add(vizinho);
                }
            }
        }

        // Verifica se todos os vértices foram visitados
        for (boolean visitado : visitados) {
            if (!visitado) {
                return false;
            }
        }
        return true;
    }
}
    