//239
//https://leetcode-cn.com/problems/sliding-window-maximum

/**
 * Note: The returned array must be malloced, assume caller calls free().
 */

typedef struct {
    int *data;
    int head, tail, capacity;
} MyCircularDeque;

/** Initialize your data structure here. Set the size of the deque to be k. */

MyCircularDeque* myCircularDequeCreate(int k) {
    MyCircularDeque* obj = malloc(sizeof(MyCircularDeque));
    obj->data = malloc(sizeof(int) * (k + 1));
    obj->head = 0;
    obj->tail = 0;
    obj->capacity = k + 1;
    for (int i = 0; i < obj->capacity; i++) {
        obj->data[i] = -1;
    }
    return obj;
}

/** Checks whether the circular deque is empty or not. */
bool myCircularDequeIsEmpty(MyCircularDeque* obj) {
    return obj->head == obj->tail;
}

/** Checks whether the circular deque is full or not. */
bool myCircularDequeIsFull(MyCircularDeque* obj) {
    return obj->head == (obj->tail + 1) % obj->capacity;
}

/** Adds an item at the front of Deque. Return true if the operation is successful. */
bool myCircularDequeInsertFront(MyCircularDeque* obj, int value) {
    if (myCircularDequeIsFull(obj))
        return false;
    obj->head = (obj->head - 1 + obj->capacity) % obj->capacity;
    obj->data[obj->head] = value;
    return true;
}

/** Adds an item at the rear of Deque. Return true if the operation is successful. */
bool myCircularDequeInsertLast(MyCircularDeque* obj, int value) {
    if (myCircularDequeIsFull(obj))
        return false;
    obj->data[obj->tail] = value;
    obj->tail = (obj->tail + 1) % obj->capacity;
    return true;
}

/** Deletes an item from the front of Deque. Return true if the operation is successful. */
bool myCircularDequeDeleteFront(MyCircularDeque* obj) {
    if (myCircularDequeIsEmpty(obj))
        return false;
    obj->head = (obj->head + 1) % obj->capacity;
    return true;
}

/** Deletes an item from the rear of Deque. Return true if the operation is successful. */
bool myCircularDequeDeleteLast(MyCircularDeque* obj) {
    if (myCircularDequeIsEmpty(obj))
        return false;
    obj->tail = (obj->tail - 1 + obj->capacity) % obj->capacity;
    return true;
}

/** Get the front item from the deque. */
int myCircularDequeGetFront(MyCircularDeque* obj) {
    if (myCircularDequeIsEmpty(obj))
        return -1;
    return obj->data[obj->head];
}

/** Get the last item from the deque. */
int myCircularDequeGetRear(MyCircularDeque* obj) {
    if (myCircularDequeIsEmpty(obj))
        return -1;
    return obj->data[(obj->tail - 1 + obj->capacity) % obj->capacity];
}

/**
 * Your MyCircularDeque struct will be instantiated and called as such:
 * MyCircularDeque* obj = myCircularDequeCreate(k);
 * bool param_1 = myCircularDequeInsertFront(obj, value);
 
 * bool param_2 = myCircularDequeInsertLast(obj, value);
 
 * bool param_3 = myCircularDequeDeleteFront(obj);
 
 * bool param_4 = myCircularDequeDeleteLast(obj);
 
 * int param_5 = myCircularDequeGetFront(obj);
 
 * int param_6 = myCircularDequeGetRear(obj);
 
 * bool param_7 = myCircularDequeIsEmpty(obj);
 
 * bool param_8 = myCircularDequeIsFull(obj);
 
 * myCircularDequeFree(obj);
*/

int* maxSlidingWindow(int* nums, int numsSize, int k, int* returnSize){
    if (numsSize * k == 0) {
        *returnSize = 0;
        return NULL;
    }
    if (k == 1) {
        *returnSize = numsSize;
        return nums;
    }

    *returnSize = numsSize - k + 1;
    int *ans = (int *)malloc(sizeof(int) * *returnSize);
    MyCircularDeque* dq = myCircularDequeCreate(k);

    for (int i = 0; i < numsSize; i++) {
        while (!myCircularDequeIsEmpty(dq) && nums[i] >= nums[myCircularDequeGetRear(dq)]) {
            myCircularDequeDeleteLast(dq);
        }
        myCircularDequeInsertLast(dq, i);
        if (myCircularDequeGetFront(dq) + k == i) {
            myCircularDequeDeleteFront(dq);
        }
        if (i >= k - 1) {
            ans[i - k + 1] = nums[myCircularDequeGetFront(dq)];
        }
    }
    return ans;
}
