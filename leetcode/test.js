
/**
 *
 * @param {*} n
 * @param {*} k
 * @return {string} 第K大排列
 */
function getKthPermutation(n, k){

    const nums = new Array( n ).fill( 0 )
    for(let i = 0; i< n; ++i){
        nums[i] = i + 1;
    }

    const visited = new Array(n).fill( false )

    /**
     *
     * @param { [number ]} nums  需要排列数字数列
     * @param {String } res  当前已?排列?果
     * @param {[boolean]} used  ?志是否使用??位置的数字
     * @param {number} depth 当前位置
     * @param {*} n 排列元素?个数
     * @param {*} k 第K大?果
     */
    const dfsHelper = ( nums, res, used ,depth, n, k ) => {
        if(depth === n){ // get result
            return res;
        }
        const curr = factorial( n - depth - 1 ); // 当前的?的排列数
        for( let i = 0; i< n; i++){
            if( used[i] ) continue; // 剪枝1： 已?用?数字不考?
            if( curr < k ){ // 剪枝2： 当前位置排列完成后，仍然?不到 K
                k -=curr;
                continue;
            }
            res += nums[i];
            used[i] = true; // ??使用?
            return dfsHelper( nums, res, used, depth+1, n, k);
        }
        return null;
    }

    return dfsHelper( nums, '', visited , 0 , n, k)
}

function factorial(n){
    let res = 1;
    while( n> 0 ){
        res *= n--;
    }
    return res;
}

console.log( getKthPermutation(3, 1) )