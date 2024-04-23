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
