/*
 * @lc app=leetcode.cn id=201 lang=java
 *
 * [201] 数字范围按位与
 *
 * https://leetcode-cn.com/problems/bitwise-and-of-numbers-range/description/
 *
 * algorithms
 * Medium (47.10%)
 * Likes:    211
 * Dislikes: 0
 * Total Accepted:    32.4K
 * Total Submissions: 63.6K
 * Testcase Example:  '5\n7'
 *
 * 给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。
 *
 * 示例 1: 
 *
 * 输入: [5,7]
 * 输出: 4
 *
 * 示例 2:
 *
 * 输入: [0,1]
 * 输出: 0
 *
 */

// @lc code=start
class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int shift = 0;
        while( m < n ){
            m >>= 1;
            n >>= 1;
            ++shift;
        }
        return m << shift;

    }
}
// @lc code=end
