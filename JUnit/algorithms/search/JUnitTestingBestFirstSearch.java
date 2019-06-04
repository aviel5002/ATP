package algorithms.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JUnitTestingBestFirstSearch {

    @Test
    void getName() {
        ASearchingAlgorithm test=new BestFirstSearch();
        assertEquals("Best first search",test.getName());
    }

    @Test
    void solve() {
        ASearchingAlgorithm test=new BestFirstSearch();
        assertNull(test.solve(null));
    }

    @Test
    void remove() {
        ASearchingAlgorithm test=new BestFirstSearch();
        assertNull(((BestFirstSearch) test).remove());

    }

    @Test
    void getNumberOfNodesEvaluated() {
        ASearchingAlgorithm test=new BestFirstSearch();
        assertEquals(0,test.getNumberOfNodesEvaluated());
        ((BestFirstSearch) test).remove();
        assertEquals(0,test.getNumberOfNodesEvaluated());
    }

}