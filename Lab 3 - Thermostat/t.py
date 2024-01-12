class Solution:
    def climbStairs(self, n: int) -> int:
        temp1 = 1
        temp2 = 2
        # To avoid the situation that the input is 1 or 2.
        if n==1:
            return temp1
        elif n==2:
            return temp2
        else:
            for _ in range(2,n):
                temp = temp1+temp2
                temp1 = temp2
                temp2 = temp
            return temp
