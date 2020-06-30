/*
 * @lc app=leetcode id=26 lang=javascript
 *
 * [26] Remove Duplicates from Sorted Array
 */
/**
 * @param {number[]} nums
 * @return {number}
 */
var removeDuplicates = function (nums) {
    var ans = 0;
    for (var i = nums.length; i--;) {
        if (i === nums.length - 1) {
            ans++;
        } else if (nums[i] === nums[i + 1]) {
            nums.splice(i, 1);
        }
        else {
            ans++;
        }
    }
    return ans;
};

