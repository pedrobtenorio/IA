import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(" Problema missionario e canibais ");
        System.out.println(" Utilizando algoritimo BFS ");
        //the algoritmn is only working to numbers lesser than 4
        State initialState = new State (3, 3, Position.LEFT, 0, 0);
        executeBFS(initialState);

    }

    private static void executeBFS(State initialState) {
        BFS search = new BFS();
        State solution = search.exec(initialState);
        printSolution(solution);
    }

    private static void printSolution(State solution) {
        if (null == solution) {
            System.out.print("\nsem solução");
        } else {
            System.out.println("\nSolução: (canibais na esquerda, missionarios na esquerda, local do barco, canibais na direita, missionarios na direita): ");
            List<State> path = new ArrayList<>();
            State state = solution;
            while(null!=state) {
                path.add(state);
                state = state.getParentState();
            }

            int depth = path.size() - 1;
            for (int i = depth; i >= 0; i--) {
                state = path.get(i);
                if (state.isGoal()) {
                    System.out.print(state);
                } else {
                    System.out.print(state + " -> ");
                }
            }
            System.out.println("\nProfundidade: " + depth);
        }
    }
}