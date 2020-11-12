import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode.cn id=973 lang=java
 *
 * [973] 最接近原点的 K 个点
 *
 * https://leetcode-cn.com/problems/k-closest-points-to-origin/description/
 *
 * algorithms
 * Medium (58.78%)
 * Likes:    153
 * Dislikes: 0
 * Total Accepted:    29.9K
 * Total Submissions: 48.5K
 * Testcase Example:  '[[1,3],[-2,2]]\n1'
 *
 * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 *
 * （这里，平面上两点之间的距离是欧几里德距离。）
 *
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 *
 *
 *
 * 示例 1：
 *
 * 输入：points = [[1,3],[-2,2]], K = 1
 * 输出：[[-2,2]]
 * 解释：
 * (1, 3) 和原点之间的距离为 sqrt(10)，
 * (-2, 2) 和原点之间的距离为 sqrt(8)，
 * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 *
 *
 * 示例 2：
 *
 * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
 * 输出：[[3,3],[-2,4]]
 * （答案 [[-2,4],[3,3]] 也会被接受。）
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 *
 *
 */

// @lc code=start
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int  n=  points.length;
        random_select( points, 0, n-1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    private void random_select(int[][] points, int left , int right, int k) {
        int privotId = left + rand.nextInt( right - left + 1);
        int pivot = points[privotId][0] * points[privotId][0] + points[privotId][1] * points[privotId][1];
        swap( points, right,privotId);
        int i = left -1;
        for( int j = left; j< right; ++j){
            int dist = points[j][0] * points[j][0] + points[j][1]  + points[j][1];
            if( dist <= pivot){
                ++i;
                swap(points,i,j);
            }
        }
        ++i;
        swap( points, i, right);
        if( K< i- left +1){
            random_select(points, left, i-1, k);
        } else if( k > i-left +1){
            random_select(points, i+1, right, k - ( i-left +1));
        }
    }
    public void swap(int[][] points, int index1, int index2) {
        int[] temp = points[index1];
        points[index1] = points[index2];
        points[index2] = temp;
    }
}
// @lc code=end


// solution 2
// public int[][] kClosest(int[][] points, int K) {
//     PriorityQueue<int[]> pq =  new PriorityQueue<>( new Comparator<int[]>(){
//             public int compare( int[] array1, int[] array2) {
//                 return array2[0] - array1[0];
//             }
//     });
//     for( int i = 0; i< K; ++i){
//         pq.offer( new int[] { points[i][0] * points[i][0] + points[i][1]*points[i][1] });
//     }
//     int n = points.length;
//     for( int i = K; i < n; ++i){
//         int dist = points[i][0]^2 +  points[i][1]^2;
//         if(dist < pq.peek()[0]){
//             pq.poll();
//             pq.offer( new int[]{ dist, i});
//         }
//     }
//     int [][] ans = new int [K][2];
//     for( int i = 0; i < K; ++i){
//         ans[i] = points[pq.poll()[1]];
//     }
//     return ans;
// }
