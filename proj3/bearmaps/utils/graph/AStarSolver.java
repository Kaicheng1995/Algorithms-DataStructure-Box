package bearmaps.utils.graph;

import bearmaps.utils.pq.MinHeapPQ;
import edu.princeton.cs.algs4.Stopwatch;
import org.apache.commons.math3.analysis.function.Min;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    SolverOutcome outcome;
    List<Vertex> solution = new ArrayList<>();
    double solutionWeight;
    int numStatesExplored = 0;
    double explorationTime;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        HashMap<Vertex, WeightedEdge<Vertex>> EdgeTo = new HashMap<>();
        HashMap<Vertex, Double> distTo = new HashMap<>();
        MinHeapPQ<Vertex> fringe = new MinHeapPQ<Vertex>();
        fringe.insert(start, 0 + input.estimatedDistanceToGoal(start, end));
        distTo.put(start, 0.0);


        while (fringe.size() != 0) {
            Vertex temp = fringe.poll();
            numStatesExplored += 1;
            double timeElapsed = sw.elapsedTime();
            if (timeElapsed > timeout) {
                outcome = SolverOutcome.TIMEOUT;
                solutionWeight = 0;
                solution = new ArrayList<>();
                explorationTime = timeElapsed;
                return;
            }
            if (temp.equals(end)) {
                outcome = SolverOutcome.SOLVED;
                solutionWeight = distTo.get(temp);
                while (!temp.equals(start)) {
                    WeightedEdge<Vertex> e = EdgeTo.get(temp);
                    solution.add(temp);
                    temp = e.from();
                }
                solution.add(start);
                Collections.reverse(solution);
                explorationTime = timeElapsed;
                return;
            } else {
                for (WeightedEdge<Vertex> e : input.neighbors(temp)) {
                    if (!distTo.containsKey(e.to()) || (e.weight() + distTo.get(e.from())) < distTo.get(e.to())) {
                        EdgeTo.put(e.to(), e);
                        distTo.put(e.to(), e.weight() + distTo.get(e.from()));
                        if (fringe.contains(e.to())) {
                            fringe.changePriority(e.to(), distTo.get(e.to()) + input.estimatedDistanceToGoal(e.to(), end));
                        } else {
                            fringe.insert(e.to(), distTo.get(e.to()) + input.estimatedDistanceToGoal(e.to(), end));
                        }
                    }
                }
            }
        }
        outcome = SolverOutcome.UNSOLVABLE;
        solution = new ArrayList<>();
        solutionWeight = 0;
        explorationTime = sw.elapsedTime();
    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List<Vertex> solution() {
        return solution;
    }

    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    @Override
    public int numStatesExplored() {
        return numStatesExplored;
    }

    @Override
    public double explorationTime() {
        return explorationTime;
    }
}
