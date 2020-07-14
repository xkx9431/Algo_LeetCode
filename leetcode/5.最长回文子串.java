/*
 * @lc app=leetcode.cn id=5 lang=java
 *
 * [5] 最长回文子串
 *
 * https://leetcode-cn.com/problems/longest-palindromic-substring/description/
 *
 * algorithms
 * Medium (31.03%)
 * Likes:    2420
 * Dislikes: 0
 * Total Accepted:    315.4K
 * Total Submissions: 1M
 * Testcase Example:  '"babad"'
 *
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 *
 *
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 *
 */

// @lc code=start
class Solution {
    public String longestPalindrome(String s) {
        if( s== null || s.length() < 1) return "";
        int start = 0, end = 0;
        for(int i = 0; i< s.length();i++){
            int len1 = expand(s,i,i);
            int len2 = expand(s,i,i+1);
            int len =  Math.max(len1,len2);
            if(len > end -start){
                start = i - (len-1)/2;
                end = i + len/2;
            }
        }
        return s.substring(start, end+1);
    }

    private int expand(String s , int left, int right){
        int l = left, r = right;
        while(l>=0 && r<s.length() && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
        }
        return r-l -1;
    }
}
// @lc code=end
