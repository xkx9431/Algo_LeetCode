/*
 * @lc app=leetcode.cn id=392 lang=javascript
 *
 * [392] 判断子序列
 *
 * https://leetcode-cn.com/problems/is-subsequence/description/
 *
 * algorithms
 * Easy (48.89%)
 * Likes:    239
 * Dislikes: 0
 * Total Accepted:    54.8K
 * Total Submissions: 110.6K
 * Testcase Example:  '"abc"\n"ahbgdc"'
 *
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 *
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
 *
 *
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 * 示例 1:
 * s = "abc", t = "ahbgdc"
 *
 * 返回 true.
 *
 * 示例 2:
 * s = "axc", t = "ahbgdc"
 *
 * 返回 false.
 *
 * 后续挑战 :
 *
 * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T
 * 的子序列。在这种情况下，你会怎样改变代码？
 *
 * 致谢:
 *
 * 特别感谢 @pbrother 添加此问题并且创建所有测试用例。
 *
 */

// @lc code=start
/**
 * @param {string} s
 * @param {string} t
 * @return {boolean}
 */
var isSubsequence = function(s, t) {
    const n = s.length, m = t.length;
    let i = 0, j = 0;
    let f = new Array( m+1).fill( new Array(26).fill(0) );
    for(let i = 0; i<26; i++){
        f[m][i] = m;
    }

    for(let i = m-1; i>=0;i--){
        for(let j = 0; j< 26; j++){
            if(t[i].charCodeAt() == j + 97){
                f[i][j] = i;
            }else{
                f[i][j] = f[i+1][j];
            }
        }
    }

    let add  = 0;
    for(let i = 0; i< n;i++){
        const cur = s[i].charCodeAt() - 97;
        if(f[add][cur] === m ){
            return false;
        }else{
            add = f[add][cur] +1 ;
        }
    }
    return true;
};
// @lc code=end
// console.log(isSubsequence("abc","ahbgdc"))