1. 基本的排序算法

冒泡排序（Bubble Sort）
基本思想
给定一个数组，我们把数组里的元素通通倒入到水池中，这些元素将通过相互之间的比较，按照大小顺序一个一个地像气泡一样浮出水面。

实现
每一轮，从杂乱无章的数组头部开始，每两个元素比较大小并进行交换，直到这一轮当中最大或最小的元素被放置在数组的尾部，然后不断地重复这个过程，直到所有元素都排好位置。其中，核心操作就是元素相互比较。

例题分析
给定数组 [2, 1, 7, 9, 5, 8]，要求按照从左到右、从小到大的顺序进行排序。 

解题思路

从左到右依次冒泡，把较大的数往右边挪动即可。

```java
void bubble_sort(int[] nums){
    boolean hasChange = true;
    for( int i = 1; i< nums.length -1 && hasChange; i++){
        hasChange = false
        for(int j = 0; j < nums.length -1 -i; j++){
            if( nums[j] > nums[j+1]){
                swap(nums, j, j+1);
                hasChange = true;
            }
        }
    }
}
```


```js
const bubble_sort = nums =>{

    const swap = (nums, i, j)=>{
        const tmp = nums[i]
        nums[i] = nums[j]
        nums[j] = tmp
    }
    let bubbled = true
    for( let i = 0; i< nums.length-1 && bubbled; i++){
        bubbled = false
        for( let j = 0; j< nums.length -1- i; j++){
            if( nums[j]  > nums[j+1]){
                swap(nums, j, j+1);
                bubbled = true
            }
        }
    }
}
```

插入排序（Insertion Sort）

基本思想
不断地将尚未排好序的数插入到已经排好序的部分。

特点
在冒泡排序中，经过每一轮的排序处理后，数组后端的数是排好序的；而对于插入排序来说，经过每一轮的排序处理后，数组前端的数都是排好序的。

```java
 void insertSort( int[] nums){
    // 将数组的第一个元素当作已经排好序的，从第二个元素，即 i 从 1 开始遍历数组
    for( int i = 1, j, current; i< nums.length;i++){
        // 外围循环开始，把当前 i 指向的值用 current 保存
        current = nums[i]
        // 指针 j 内循环，和 current 值比较，若 j 所指向的值比 current 值大，则该数右移一位
        for( j = i-1; j >=0 && nums[j] > current; j--){
            nums[j+1] = nums[j]
        }
        nums[j+1] = current;
     }
 }
```
```js
const insertSort = nums=>{
    for( let i = 1; i< nums.length; i++){
        let j = i -1, current = nums[i];
        while( j >= 0 && nums[j] > current){
            nums[j+1] = nums[j]
            j--;
        }
        nums[j+1] =current
    }
}
```
leetcode 147


2. 常考的排序算法

归并排序（Merge Sort）
基本思想
核心是分治，就是把一个复杂的问题分成两个或多个相同或相似的子问题，然后把子问题分成更小的子问题，直到子问题可以简单的直接求解，最原问题的解就是子问题解的合并。归并排序将分治的思想体现得淋漓尽致。

实现
一开始先把数组从中间划分成两个子数组，一直递归地把子数组划分成更小的子数组，直到子数组里面只有一个元素，才开始排序。

排序的方法就是按照大小顺序合并两个元素，接着依次按照递归的返回顺序，不断地合并排好序的子数组，直到最后把整个数组的顺序排好。
空间复杂度
由于合并 n 个元素需要分配一个大小为 n 的额外数组，合并完成之后，这个数组的空间就会被释放，所以算法的空间复杂度就是 O(n)。归并排序也是稳定的排序算法
```java
void sort( int[] nums, int lo, int hi){
    if( lo >= hi) return
    int mid = lo + ( hi - lo)/ 2;
    sort(nums, lo,mid);
    sort(nums, mid +1,hi);

    //
    merge(nums,lo,mid,hi);
}

void merge( int[]nums, int lo, int,mid, int hi){
    int[] copy = nums.clone()

    int k = lo, i = lo, j= mid +1;
    while(k <= hi){
        if(k > mid){
            copy[k++] = nums[j++];
        }else if( j > hi){
            copy[k++] = nums[i++];
        }else if( copy[j]  < copy[i]){
            nums[k++] = copy[j++]
        } else {
            nums[k++] = nums[i++]
        }
    }
}
```

```js
 const mergeSort = (nums, lo, hi) =>{
    //let lo = 0, hi = nums.length - 1
    if( lo >=hi) return
    let mid = lo + (hi -lo)/2
    sort(nums,lo,mid)
    sort(nums,mid +1,hi)
    merge(A, lo,mid, hi)
 }

 const merge = (nums, lo, mid, hi)=>{
    let copy = nums.slice();
    int k = lo, i = lo; j = mid+1;
    while(k <=hi){
        if( i > mid ){
            copy[k++] = copy[j++]
        } else if (j > hi){
            copy[k++] = copy[i++]
        } else if( nums[i] >= nums[j]){
            copy[k++] = nums[j++]
        } else{
            copy[k++] = nums[i++]
        }
    }
 }
```

快速排序（Quick Sort）
LeetCode 第 215 题，给定一个尚未排好序的数组，要求找出第 k 大的数。
```java
void sort( int[] nums, int lo, int hi){
    if( lo >= hi) return;
    int p = partition(nums, lo,hi);
    sort(nums,lo,p-1);
    sort(nums,p+1,hi);
}

int partition(int[] nums, int lo, int hi){
    swap( nums, random( lo,hi), hi);
    int i,j;
    for(int i = lo,j =lo; j< hi; j++){
        if(nums[j] <= nums[hi]){
            swap(nums, i++,j)
        }
    }
    swap(nums, i, j)
}
```

拓扑排序（Topological Sort）

3. 其他排序算法

堆排序（Heap Sort）

桶排序（Bucket Sort)