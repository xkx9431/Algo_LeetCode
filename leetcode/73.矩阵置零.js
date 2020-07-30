/**
 * @param {number[][]} matrix
 * @return {void} Do not return anything, modify matrix in-place instead.
 */
var setZeroes = function(matrix) {

    const rowLen = matrix.length;
    const colLen = matrix[0].length;

    let isFirstColHasZero = false;
    for( let i = 0; i < rowLen; i++){
        if(matrix[i][0] === 0){
            isFirstColHasZero  = true;
        }
        for( let j = 1; j< colLen; j++){
            if(matrix[i][j] === 0){
                matrix[i][0] = 0;
                matrix[0][j] = 0;
            }
        }
    }

    for(let i = 1; i< rowLen; i++){
        for(let j = 1; j< colLen; j++){
            if(matrix[i][0] === 0 || matrix[0][j] === 0){
                matrix[i][j] = 0;
            }
        }
    }
    if(matrix[0][0] === 0){
        for(let j = 0; j<colLen; j++){
            matrix[0][j] = 0;
        }
    }
    if(isFirstColHasZero){
        for(let i=0; i< rowLen;i++){
            matrix[i][0] = 0;
        }
    }
};
let matrix = [[1,1,1],[1,0,1],[1,1,1]];
setZeroes(matrix);
console.log( matrix )