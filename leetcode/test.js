
/**
 *
 * @param {*} n
 * @param {*} k
 * @return {string} ��K��r��
 */
function getKthPermutation(n, k){

    const nums = new Array( n ).fill( 0 )
    for(let i = 0; i< n; ++i){
        nums[i] = i + 1;
    }

    const visited = new Array(n).fill( false )

    /**
     *
     * @param { [number ]} nums  ���v�r�񐔎�����
     * @param {String } res  ���O��?�r��?��
     * @param {[boolean]} used  ?�u���ێg�p??�ʒu�I����
     * @param {number} depth ���O�ʒu
     * @param {*} n �r�񌳑f?����
     * @param {*} k ��K��?��
     */
    const dfsHelper = ( nums, res, used ,depth, n, k ) => {
        if(depth === n){ // get result
            return res;
        }
        const curr = factorial( n - depth - 1 ); // ���O�I?�I�r��
        for( let i = 0; i< n; i++){
            if( used[i] ) continue; // ���}1�F ��?�p?�����s�l?
            if( curr < k ){ // ���}2�F ���O�ʒu�r�񊮐��@�C���R?�s�� K
                k -=curr;
                continue;
            }
            res += nums[i];
            used[i] = true; // ??�g�p?
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