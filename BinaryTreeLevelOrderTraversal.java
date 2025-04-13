// Time Complexity : O(n)
// Space Complexity : O(h) for DFS, O(w) for BFS
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Approach - DFS - Use recursion and pass a `level` counter. If the level doesn't exist in the result list yet, create a new sublist. Add the node’s value to its corresponding level.
// BFS: Use a queue to process nodes level-by-level. For each level, add all children of the current level's nodes into the queue.


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class BinaryTreeLevelOrderTraversal {
    //DFS approach
    List<List<Integer>> result;
    public List<List<Integer>> levelOrderDFS(TreeNode root) {
        this.result = new ArrayList<>();
        if(root == null){
            return result;
        }
        helper(root, 0);    //treenode root and int level is passed
        return result;
    }

    private void helper(TreeNode root, int level) {
        if(root == null) {
            return;
        }

        //logic
        if(result.size() == level) {
            result.add(new ArrayList<>());
        }

        helper(root.left, level + 1);

        result.get(level).add(root.val);

        helper(root.right, level + 1);
    }

    public List<List<Integer>> levelOrderBFS(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while(!queue.isEmpty()) {
            res.add(new ArrayList<>());
            int level_length = queue.size();
            for(int i=0; i<level_length; i++) {
                TreeNode front = queue.remove();
                res.get(level).add(front.val);
                if(front.left != null)  queue.add(front.left);
                if(front.right != null)  queue.add(front.right);
            }
            level++;
        }

        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3, null, new TreeNode(6)));

        BinaryTreeLevelOrderTraversal bt = new BinaryTreeLevelOrderTraversal();

        System.out.println("Level Order (BFS): " + bt.levelOrderBFS(root));
        System.out.println("Level Order (DFS): " + bt.levelOrderDFS(root));
    }
}
