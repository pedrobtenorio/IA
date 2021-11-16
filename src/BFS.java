import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class BFS {

    public State exec(State initialState) {
        if (initialState.isGoal()) {
            return initialState;
        }
        Queue<State> frontier = new LinkedList<>();
        Set<State> visited = new HashSet<>();
        frontier.add(initialState);
        while (true) {
            if (frontier.isEmpty()) {
                return null;
            }
            State state = frontier.poll();
            visited.add(state);
            List<State> successors = state.checkAction();
            for (State child : successors) {
                if (!visited.contains(child) || !frontier.contains(child)) {
                    if (child.isGoal()) {
                        return child;
                    }
                    frontier.add(child);
                }
            }
        }
    }
}