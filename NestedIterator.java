// Time Complexity : O(1)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Yes. Had to learn

/*
341. Flatten Nested List Iterator
    Stack holds Iterator of the list and we peek through the stack and 
    - if we find a list, convert into a iterator and add it to stack
    - if we find a number, assign the nextElement from stack.peek().next() and return true
    - if stack.peek().hasNext() is false, i.e., the next element is absent in the iterator of the top element in stack, so we pop it out 
*/

import java.util.*;

interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a
    // nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a
    // single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested
    // list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> stack;
    NestedInteger currEl;

    public NestedIterator(List<NestedInteger> nestedList) {

        this.stack = new Stack<>();
        this.stack.push(nestedList.iterator());

    }

    @Override
    public Integer next() { // Time complexity - O(1); Space Complexity - O(1)
        return currEl.getInteger();
    }

    @Override
    public boolean hasNext() {

        while (!stack.isEmpty()) {
            if (!stack.peek().hasNext()) {// we removed all elements from the Iterator of .peek() NestedInteger
                stack.pop();
            } else if ((currEl = stack.peek().next()).isInteger()) { // assign the stack.peek().next() to currEl & check
                                                                     // if integer
                return true;
            } else {
                stack.push(currEl.getList().iterator()); // if its here, the currEl is not an integer, so its a list so
                                                         // add it to stack as an iterator.
            }
        }

        return false;

    }
}