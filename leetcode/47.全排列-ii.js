/*
 * @lc app=leetcode.cn id=47 lang=javascript
 *
 * [47] 全排列 II
 *
 * https://leetcode-cn.com/problems/permutations-ii/description/
 *
 * algorithms
 * Medium (59.67%)
 * Likes:    416
 * Dislikes: 0
 * Total Accepted:    95.6K
 * Total Submissions: 157.6K
 * Testcase Example:  '[1,1,2]'
 *
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 * ⁠ [1,1,2],
 * ⁠ [1,2,1],
 * ⁠ [2,1,1]
 * ]
 *
 */

// @lc code=start
/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var permuteUnique = function(nums) {
    const ans = [];
    const vis = new Array( nums.length).fill(false);
    const backtrack = ( idx,perm) =>{
        if(idx === nums.length ){
            ans.push(perm.slice())
            return
        }
        for( let i = 0; i< nums.length; ++i){
            if( vis[i] || (i > 0 && nums[i] === nums[i - 1] && !vis[i-1])){
                continue;
            }
            perm.push( nums[i]);
            vis[i] = true;
            backtrack( idx +1,perm);
            vis[i] = false;
            perm.pop();
        }
    }

    nums.sort( (x,y) => x -y );
    backtrack(0, [] );
    return ans;

};
// @lc code=end
