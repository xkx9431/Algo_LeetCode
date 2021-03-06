
/**
 *
 * @param {*} n
 * @param {*} k
 * @return {string} æKårñ
 */
function getKthPermutation(n, k){

    const nums = new Array( n ).fill( 0 )
    for(let i = 0; i< n; ++i){
        nums[i] = i + 1;
    }

    const visited = new Array(n).fill( false )

    /**
     *
     * @param { [number ]} nums  ùvrññ
     * @param {String } res  Oß?rñ?Ê
     * @param {[boolean]} used  ?u¥Ûgp??ÊuI
     * @param {number} depth OÊu
     * @param {*} n rñ³f?¢
     * @param {*} k æKå?Ê
     */
    const dfsHelper = ( nums, res, used ,depth, n, k ) => {
        if(depth === n){ // get result
            return res;
        }
        const curr = factorial( n - depth - 1 ); // OI?Irñ
        for( let i = 0; i< n; i++){
            if( used[i] ) continue; // }1F ß?p?sl?
            if( curr < k ){ // }2F OÊurñ®¬@C¹R?s K
                k -=curr;
                continue;
            }
            res += nums[i];
            used[i] = true; // ??gp?
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