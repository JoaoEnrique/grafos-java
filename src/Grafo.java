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
    