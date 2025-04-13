// Space Complexity : O(h)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Approach -

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //Topological sort Kahn's algorithm
        List<List<Integer>> adj = new ArrayList<>();    //adjacency list
        int[] indegree = new int[numCourses];   //indegree of all nodes

        for(int i=0; i<numCourses; i++) {   //creating adjacency list
            adj.add(new ArrayList<>());
        }

        for(int[] prereq: prerequisites) {  //loop through prerequisites and add edges
            adj.get(prereq[1]).add(prereq[0]);
            indegree[prereq[0]]++;  //update indegrees
        }

        Queue<Integer> queue = new LinkedList<>();  //queue for topo sort
        for(int i=0; i<numCourses; i++) {   //add nodes with 0 indegree in queue to start BFS
            if(indegree[i] == 0) {
                queue.add(i);
            }
        }

        int visitedCount = 0;   //count for visited nodes
        while(!queue.isEmpty()) {   //loop till queue is not empty
            int node = queue.poll();    //remove front node
            visitedCount++; //increase visited count

            for(int neighbor: adj.get(node)) {  //loop in adjacency list of node
                indegree[neighbor]--;   //decrease indegree of neighbor
                if(indegree[neighbor] == 0) {   //if indegree becomes 0 add to queue
                    queue.add(neighbor);
                }
            }
        }

        return visitedCount == numCourses;  //return if visitedCount is same as numCourses
    }

    public static void main(String[] args) {
        CourseSchedule cs = new CourseSchedule();

        int[][] prerequisites1 = {{1, 0}, {2, 1}, {3, 2}};
        System.out.println("Can finish all courses? " + cs.canFinish(4, prerequisites1)); // true
    }
}
