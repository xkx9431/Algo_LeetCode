import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode.cn id=109 lang=java
 *
 * [109] 有序链表转换二叉搜索树
 *
 * https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/description/
 *
 * algorithms
 * Medium (72.96%)
 * Likes:    292
 * Dislikes: 0
 * Total Accepted:    40.2K
 * Total Submissions: 54.2K
 * Testcase Example:  '[-10,-3,0,5,9]'
 *
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 *
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 * ⁠     0
 * ⁠    / \
 * ⁠  -3   9
 * ⁠  /   /
 * ⁠-10  5
 *
 *
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    ListNode globalHead;
    public TreeNode sortedListToBST(ListNode head) {
        globalHead = head;
        int length = getLength( head );
        return buildTree( 0, length -1);
    }

    public int getLength( ListNode head ){
        int ret = 0;
        while(head!=null){
            ++ret;
            head = head.next;
        }
        return ret;
    }

    private TreeNode buildTree(int left, int right) {
        if(left > right ){
            return null;
        }
        int mid = (left + right + 1 ) / 2;
        TreeNode root = new TreeNode();
        root.left = buildTree(0, mid-1);
        root.val = globalHead.val;
        globalHead = globalHead.next;
        root.right = buildTree(mid+1, right);
        return root;
    }
}
// @lc code=end
