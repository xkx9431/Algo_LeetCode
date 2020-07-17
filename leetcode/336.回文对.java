import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
 * @lc app=leetcode.cn id=336 lang=java
 *
 * [336] 回文对
 *
 * https://leetcode-cn.com/problems/palindrome-pairs/description/
 *
 * algorithms
 * Hard (34.04%)
 * Likes:    76
 * Dislikes: 0
 * Total Accepted:    4.6K
 * Total Submissions: 13.4K
 * Testcase Example:  '["abcd","dcba","lls","s","sssll"]'
 *
 * 给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
 *
 * 示例 1:
 *
 * 输入: ["abcd","dcba","lls","s","sssll"]
 * 输出: [[0,1],[1,0],[3,2],[2,4]]
 * 解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 *
 *
 * 示例 2:
 *
 * 输入: ["bat","tab","cat"]
 * 输出: [[0,1],[1,0]]
 * 解释: 可拼接成的回文串为 ["battab","tabbat"]
 *
 */


// @lc code=start
class TrieNode {
    int index;
    ArrayList<Integer> palindromes;
    HashMap< Character,TrieNode> children;
    TrieNode(){
        index = -1;
        children = new HashMap<>();
        palindromes = new ArrayList<Integer>();
    }
}
class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        TrieNode root = new TrieNode();
        for(int i = 0; i< words.length;i++){
            addWord(root,words[i],i);
        } // create trie
        for(int i =0; i< words.length; i++){
            search( words,i,root,res);
        }
        return res;
    }

    private void search(String[] words, int i, TrieNode root, List<List<Integer>> res) {
        // k1 > k2，且 s1 剩下的字符能构成回文，就把这对组合添加到结果中
        // k1=k2 或 k1<k2，只需要把回文列表里的字符串都和 s1 组合即可 
        for(int j=0; j< words[i].length(); j++) {
            if( root.index>=0 && root.index !=i && isPalindrome(words[i], j, words[i].length()-1)){
                res.add(Arrays.asList(i,root.index));
            }
            root = root.children.get(words[i].charAt(j));
            if( root==null) return;
        }
        for(int j: root.palindromes ) {
            if(i == j ) {
                continue;
            }
            res.add(Arrays.asList(i,j));
        }
    }

    private void addWord(TrieNode root, String string, int index) {
        for(int i = string.length()-1; i>=0; i-- ){
            char ch = string.charAt(i);
            if(!root.children.containsKey(ch)){
                root.children.put(ch, new TrieNode());
            }
            if(isPalindrome( string, 0, i )){
                root.palindromes.add( index);
            }
            root = root.children.get(ch);
        }
        root.palindromes.add(index);
        root.index = index;
    }

    private boolean isPalindrome(String string, int i, int i2) {
        while( i< i2){
            if(string.charAt(i++) != string.charAt(i2--)) return false;
        }
        return true;
    }
}
// @lc code=end
