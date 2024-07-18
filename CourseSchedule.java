import java.util.*;

public class CourseSchedule {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        //In the given problem, each course is a vertex and each prerequisite pair is an edge
        HashMap<Integer, List<Integer>> map = new HashMap<>(); //O(v+e) S.C
        int[] indegrees = new int[numCourses]; //the count of incoming edges to a vertex is indegrees
        for(int[] prerequisite : prerequisites) { //iterate over the prerequisite requirements
            int dependent = prerequisite[0]; //first position in array is the dependent course
            int independent = prerequisite[1]; //second position is the independent course
            indegrees[dependent]++; //increment the indegree count for each dependent course
            if(!map.containsKey(independent)) { //if map doesn't already contain the independent key
                map.put(independent, new ArrayList<>());
            }
            map.get(independent).add(dependent); //add the list of dependent courses as value to map
        }

        Queue<Integer> q = new LinkedList<>(); //adding a course to q confirms the course can be done
        int count = 0; //to count number of courses we already traversed and felt it can be completed
        for(int i=0; i<indegrees.length; i++) { //initial iteration over the indegrees array
            if(indegrees[i] == 0) { //to check if any course has no dependents
                q.add(i); //hence, it can be completed
                count++; //incrementing the courses that can be completed
            }
        }

        //if in initial iteration itself
        if(count == numCourses) return true; //we see all courses can be completed i.e., no dependents for any course
        if(count == 0) return false; //we see no course can be completed i.e., circular dependency

        while(!q.isEmpty()) { //B.F.S, O(v+e) T.C, sum of vertices and edges
            int parent = q.poll(); //find the independent course
            List<Integer> children = map.get(parent); //get list of its dependent courses
            if(children != null) { //since independent courses with no dependents can also be added
                for(int child : children) { //for each dependent course
                    indegrees[child]--; //decrease its indegree
                    if(indegrees[child] == 0) { //if it eventually becomes 0
                        q.add(child); //add that dependent course to q as it became newly independent
                        count++; //increment the count of completed courses
                    }//if there are no children added to q, it means we came out of BFS
                }
            }
        }
        return count == numCourses; //if completed courses is same as total courses, return true else false
    }

    public static void main(String[] args) {
        int numCourses1 = 5;
        int[][] prerequisites1 = {{0,1},{1,2},{1,3},{3,4},{2,4}};

        System.out.println("Can the student finish " + numCourses1 + " courses with prerequisites being "
        + Arrays.deepToString(prerequisites1) + " : " + canFinish(numCourses1, prerequisites1));

        int numCourses2 = 3;
        int[][] prerequisites2 = {{0,1},{1,2},{2,0}};

        System.out.println("Can the student finish " + numCourses2 + " courses with prerequisites being "
                + Arrays.deepToString(prerequisites2) + " : " + canFinish(numCourses2, prerequisites2));
    }
}