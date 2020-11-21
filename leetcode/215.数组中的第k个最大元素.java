import java.util.Random;

/*
 * @lc app=leetcode.cn id=215 lang=java
 *
 * [215] 数组中的第K个最大元素
 *
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/description/
 *
 * algorithms
 * Medium (64.39%)
 * Likes:    784
 * Dislikes: 0
 * Total Accepted:    222.7K
 * Total Submissions: 345.3K
 * Testcase Example:  '[3,2,1,5,6,4]\n2'
 *
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 *
 *
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 * 说明:
 *
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 */

// @lc code=start
class Solution {
    Random random = new Random();
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0,nums.length -1, nums.length - k)
    }

    private int quickSelect(int[] nums, int lo, int hi, int index) {
        int p =  randomPartition( nums, lo, hi);
        if( p == index){
            return nums[p];
        } else {
            return p < index ? quickSelect(nums, p+1, hi, index) : quickSelect(nums, lo, p-1, index);
        }
    }

    private int randomPartition(int[] nums, int lo, int hi) {
        int i = random.nextInt(hi-lo +1);
        swap( nums,i,hi);
        return partition(nums, lo,hi);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private int partition(int[] nums, int lo, int hi) {
        int x = nums[hi], i = lo -1;
        for( int j = lo; j< hi; ++j){
            if(nums[j] <= x){
                swap(nums, ++i, j);
            }
        }
        swap(nums, i+1, hi);
        return i+1;
    }
}
// @lc code=end
