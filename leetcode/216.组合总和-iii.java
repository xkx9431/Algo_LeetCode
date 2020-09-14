import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=216 lang=java
 *
 * [216] 组合总和 III
 *
 * https://leetcode-cn.com/problems/combination-sum-iii/description/
 *
 * algorithms
 * Medium (71.69%)
 * Likes:    164
 * Dislikes: 0
 * Total Accepted:    33.3K
 * Total Submissions: 45.8K
 * Testcase Example:  '3\n7'
 *
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 *
 *
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。 
 *
 *
 * 示例 1:
 *
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 *
 *
 * 示例 2:
 *
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 *
 */

// @lc code=start
class Solution {
    List<Integer> temp = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        dfs(1,9,k,n);
        return ans;
    }

    public void dfs(int cur, int n, int k,int target) {
        if(temp.size()> k || (n-cur +1) + temp.size() < k ){
            return;
        }
        if(temp.size() == k){
            int sum = 0;
            for(int num: temp){
                sum += num;
            }
            if(sum ==  target){
                ans.add(new ArrayList<Integer>(temp));
                return;
            }
        }
        temp.add(cur);
        dfs(cur+1,n,k,target);
        temp.remove(temp.size() -1);
        dfs(cur +1,n,k,target);

    }
}
// @lc code=end
