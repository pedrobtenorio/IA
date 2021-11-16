import java.util.ArrayList;
import java.util.List;

enum Position {RIGHT, LEFT}

public class State {

    private int cannibalLeft;
    private int missionaryLeft;
    private int cannibalRight;
    private int missionaryRight;
    private Position boat;

    private State parentState;

    public State(int cannibalLeft, int missionaryLeft, Position boat,
                 int cannibalRight, int missionaryRight) {
        this.cannibalLeft = cannibalLeft;
        this.missionaryLeft = missionaryLeft;
        this.boat = boat;
        this.cannibalRight = cannibalRight;
        this.missionaryRight = missionaryRight;
    }

    public boolean isGoal() {
        return cannibalLeft == 0 && missionaryLeft == 0;
    }

    public boolean isValid() {
        return missionaryLeft >= 0 && missionaryRight >= 0 && cannibalLeft >= 0 && cannibalRight >= 0
            && (missionaryLeft == 0 || missionaryLeft >= cannibalLeft)
            && (missionaryRight == 0 || missionaryRight >= cannibalRight);
    }

    //here we return the valid sucessors states
    public List<State> checkAction() {
        List<State> successors = new ArrayList<>();
        if (boat == Position.LEFT) {
            testAndAdd(successors, new State(cannibalLeft, missionaryLeft - 2, Position.RIGHT,
                cannibalRight, missionaryRight + 2)); // Two missionaries cross left to right.
            testAndAdd(successors, new State(cannibalLeft - 2, missionaryLeft, Position.RIGHT,
                cannibalRight + 2, missionaryRight)); // Two cannibals cross left to right.
            testAndAdd(successors, new State(cannibalLeft - 1, missionaryLeft - 1, Position.RIGHT,
                cannibalRight + 1, missionaryRight + 1)); // One missionary and one cannibal cross left to right.
            testAndAdd(successors, new State(cannibalLeft, missionaryLeft - 1, Position.RIGHT,
                cannibalRight, missionaryRight + 1)); // One missionary crosses left to right.
            testAndAdd(successors, new State(cannibalLeft - 1, missionaryLeft, Position.RIGHT,
                cannibalRight + 1, missionaryRight)); // One cannibal crosses left to right.
        } else {
            testAndAdd(successors, new State(cannibalLeft, missionaryLeft + 2, Position.LEFT,
                cannibalRight, missionaryRight - 2)); // Two missionaries cross right to left.
            testAndAdd(successors, new State(cannibalLeft + 2, missionaryLeft, Position.LEFT,
                cannibalRight - 2, missionaryRight)); // Two cannibals cross right to left.
            testAndAdd(successors, new State(cannibalLeft + 1, missionaryLeft + 1, Position.LEFT,
                cannibalRight - 1, missionaryRight - 1)); // One missionary and one cannibal cross right to left.
            testAndAdd(successors, new State(cannibalLeft, missionaryLeft + 1, Position.LEFT,
                cannibalRight, missionaryRight - 1)); // One missionary crosses right to left.
            testAndAdd(successors, new State(cannibalLeft + 1, missionaryLeft, Position.LEFT,
                cannibalRight - 1, missionaryRight)); // One cannibal crosses right to left.
        }
        return successors;
    }

    private void testAndAdd(List<State> successors, State newState) {
        if (newState.isValid()) {
            newState.setParentState(this);
            successors.add(newState);
        }
    }

    public State getParentState() {
        return parentState;
    }

    public void setParentState(State parentState) {
        this.parentState = parentState;
    }

    @Override
    public String toString() {
        if (boat == Position.LEFT) {
            return "(" + cannibalLeft + "," + missionaryLeft + ",L,"
                + cannibalRight + "," + missionaryRight + ")";
        } else {
            return "(" + cannibalLeft + "," + missionaryLeft + ",R,"
                + cannibalRight + "," + missionaryRight + ")";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof State)) {
            return false;
        }
        State s = (State) obj;
        return (s.cannibalLeft == cannibalLeft && s.missionaryLeft == missionaryLeft
            && s.boat == boat && s.cannibalRight == cannibalRight
            && s.missionaryRight == missionaryRight);
    }
}