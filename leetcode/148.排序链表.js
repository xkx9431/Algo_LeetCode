/*
 * @lc app=leetcode.cn id=148 lang=javascript
 *
 * [148] 排序链表
 *
 * https://leetcode-cn.com/problems/sort-list/description/
 *
 * algorithms
 * Medium (67.01%)
 * Likes:    834
 * Dislikes: 0
 * Total Accepted:    106.4K
 * Total Submissions: 158.3K
 * Testcase Example:  '[4,2,1,3]'
 *
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 * 进阶：
 *
 *
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 *
 *
 * 示例 2：
 *
 *
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 *
 *
 * 示例 3：
 *
 *
 * 输入：head = []
 * 输出：[]
 *
 *
 *
 *
 * 提示：
 *
 *
 * 链表中节点的数目在范围 [0, 5 * 10^4] 内
 * -10^5 
 *
 *
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @return {ListNode}
 */
// merge
const merge = (head1,head2)=>{
    const dummyHead = new ListNode(0);
    let tmp = dummyHead, tmp1 = head1,tmp2 = head2;
    while( tmp1!== null && tmp2!== null){
        if( tmp1.val <= tmp2.val){
            tmp.next = tmp1
            tmp1 = tmp1.next
        } else {
            tmp.next = tmp2
            tmp2 = tmp2.next
        }
        tmp = tmp.next
    }
    if(tmp1 !== null){
        tmp.next = tmp1
    }
    if(tmp2 !== null){
        tmp.next = tmp2
    }
    return dummyHead.next
}

// split
const toSortList = ( head, tail) =>{
    if(head === null){
        return head;
    }
    // 1  not contains tail
    if(head.next === tail){
        head.next = null;
        return head;
    }
    let slow = head,fast = head;
    while(fast.next !== tail ){
        slow = slow.next;
        fast = fast.next;
        if(fast.next !=  tail){
            fast = fast.next
        }
    }
    const mid = slow;
    // ! 1 why not mid, mid.next
    return merge(toSortList(head,mid),toSortList(mid,tail))
}

var sortList = function(head) {

    return sortList(head,null)
};
// @lc code=end
