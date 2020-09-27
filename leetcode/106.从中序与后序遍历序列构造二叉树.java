import java.util.HashMap;
import java.util.Map;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode.cn id=106 lang=java
 *
 * [106] 从中序与后序遍历序列构造二叉树
 *
 * https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/
 *
 * algorithms
 * Medium (69.39%)
 * Likes:    312
 * Dislikes: 0
 * Total Accepted:    55.2K
 * Total Submissions: 78.6K
 * Testcase Example:  '[9,3,15,20,7]\n[9,15,7,20,3]'
 *
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 *
 * 返回如下的二叉树：
 *
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 *
 *
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int post_idx;
    int[] postorder;
    int[]  inorder;
    Map<Integer,Integer> idx_map = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        post_idx = postorder.length - 1;

        int idx = 0;
        for(Integer val : inorder){
            idx_map.put(val, idx++);
        }


        return helperBuildTree( 0, inorder.length -1 );


    }

    private TreeNode helperBuildTree(int in_left, int in_right) {
        if(in_left > in_right){
            return null;
        }
        int root_val = postorder[post_idx];
        TreeNode root = new TreeNode( root_val );
        int index = idx_map.get(root_val);

        post_idx --;
        root.right = helperBuildTree(index +1, in_right);
        root.left = helperBuildTree(in_left, index-1);
        return root;
    }
}
// @lc code=end
