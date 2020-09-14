/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number[]}
 */
var inorderTraversal = function(root) {
    const res  = [];
    let predecessor = null;
    while( root ){
        // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
        if( root.left ){
            predecessor = root.left;
            while( predecessor.right && predecessor.right !=root){
                predecessor = predecessor.right;
            }
            // 让 predecessor 的右指针指向 root，继续遍历左子树
            if(!predecessor.right){
                predecessor.right  =root;
                root = root.left;
            }
            //左子树已经访问完了，
            else{
                res.push(root.val);
                predecessor.right = null;
                root = root.right
            }
        } else{
            res.push(root.val);
            root = root.right;
        }
    }
    return res;
}