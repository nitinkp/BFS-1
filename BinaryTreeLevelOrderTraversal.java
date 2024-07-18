import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result; //null check

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root); //O(h) amortized S.C where h is height of tree

        while(!q.isEmpty()) { //B.F.S, O(n) T.C where n is number of nodes
            List<Integer> list = new ArrayList<>();
            int levelSize = q.size(); //at each traversal, size of q is the count of nodes in that level
            for(int i=0; i<levelSize; i++) { //iterating over all nodes at a level
                TreeNode curr = q.poll(); //take the first node out
                assert curr != null;
                list.add(curr.val); //add it to a new list
                if(curr.left != null) { //check its left child
                    q.add(curr.left); //add it to q if present
                }
                if(curr.right != null) { //check right child
                    q.add(curr.right); //add to q similarly
                }
            }
            result.add(list); //add all individual level-order lists to the result list
        }
        return result;
    }

    public static void main(String[] args) {
        // Test case 1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);

        // Test case 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);
        root2.right.left = new TreeNode(6);
        root2.right.right = new TreeNode(7);

        // Add more test cases if needed

        // Run test cases
        printLevelOrderTraversal(levelOrder(root1));
        printLevelOrderTraversal(levelOrder(root2));
    }

    public static void printLevelOrderTraversal(List<List<Integer>> levels) {
        for (List<Integer> level : levels) {
            System.out.println(level);
        }
        System.out.println();
    }
}