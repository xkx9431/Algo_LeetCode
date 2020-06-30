/*
 * @lc app=leetcode id=27 lang=javascript
 *
 * [27] Remove Element
 */
/**
 * @param {number[]} nums
 * @param {number} val
 * @return {number}
 */
var removeElement = function (nums, val) {
    var ans = 0;
    for (var i = nums.length; i--;) {
        if (nums[i] !== val) {
            ans++
        }
        else {
            nums.splice(i, 1)
        }
    }
    return ans

};

