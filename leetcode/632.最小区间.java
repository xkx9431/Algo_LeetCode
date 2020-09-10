import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode.cn id=632 lang=java
 *
 * [632] 最小区间
 *
 * https://leetcode-cn.com/problems/smallest-range-covering-elements-from-k-lists/description/
 *
 * algorithms
 * Hard (58.75%)
 * Likes:    236
 * Dislikes: 0
 * Total Accepted:    15K
 * Total Submissions: 25.6K
 * Testcase Example:  '[[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]'
 *
 * 你有 k 个升序排列的整数列表。找到一个最小区间，使得 k 个列表中的每个列表至少有一个数包含在其中。
 *
 * 我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。
 *
 *
 *
 * 示例：
 *
 * 输入：[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * 输出：[20,24]
 * 解释：
 * 列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
 * 列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
 * 列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 给定的列表可能包含重复元素，所以在这里升序表示 >= 。
 * 1 <= k <= 3500
 * -10^5 <= 元素的值 <= 10^5
 * 对于使用Java的用户，请注意传入类型已修改为List<List<Integer>>。重置代码模板后可以看到这项改动。
 *
 *
 */

// @lc code=start
class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        int size = nums.size();
        Map<Integer, List<Integer>> indices = new HashMap<Integer, List<Integer>>();
        int xMin = Integer.MAX_VALUE, xMax = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            for (int x : nums.get(i)) {
                List<Integer> list = indices.getOrDefault(x, new ArrayList<Integer>());
                list.add(i);
                indices.put(x, list);
                xMin = Math.min(xMin, x);
                xMax = Math.max(xMax, x);
            }
        }

        int[] freq = new int[size];
        int inside = 0;
        int left = xMin, right = xMin - 1;
        int bestLeft = xMin, bestRight = xMax;

        while (right < xMax) {
            right++;
            if (indices.containsKey(right)) {
                for (int x : indices.get(right)) {
                    freq[x]++;
                    if (freq[x] == 1) {
                        inside++;
                    }
                }
                while (inside == size) {
                    if (right - left < bestRight - bestLeft) {
                        bestLeft = left;
                        bestRight = right;
                    }
                    if (indices.containsKey(left)) {
                        for (int x: indices.get(left)) {
                            freq[x]--;
                            if (freq[x] == 0) {
                                inside--;
                            }
                        }
                    }
                    left++;
                }
            }
        }

        return new int[]{bestLeft, bestRight};
    }
}

// @lc code=end
/**
 * class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        int rangeLeft = 0,rangeRight = Integer.MAX_VALUE;
        int minRange = rangeRight - rangeLeft;
        int max = Integer.MIN_VALUE;
        int size = nums.size();
        int[] next = new int[size];
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer index1, Integer index2){
                return nums.get(index1).get(next[index1]) - nums.get(index2).get(next[index2]);
            }
        } );
        for( int i = 0; i< size; i++){
            priorityQueue.offer(i);
            max = Math.max(max,nums.get(i).get(0));
        }
        while(true){
            int minIndex = priorityQueue.poll();
            int curRange = max - nums.get(minIndex).get(next[minIndex]);
            if(curRange < minRange){
                minRange = curRange;
                rangeLeft = nums.get(minIndex).get(next[minIndex]);
                rangeRight = max;
            }
            next[minIndex]++;
            if(next[minIndex] == nums.get(minIndex).size()){
                break;
            }
            priorityQueue.offer(minIndex);
            max = Math.max(max, nums.get(minIndex).get(next[minIndex]));
        }
        return new int[]{rangeLeft,rangeRight};
    }
}
 */