一.解题思路：
1. 判空已及长度为奇数返回false
2. 长度为0返回true
3. 新建一个char数组作为栈，长度为s的字符数，建一个指针i指向栈的起始位置0
4. 遍历s的每个字符
5. 碰到左括号"("，则将右括号")"入栈，并将指针向后移动一位
6. 碰到左中括号"["，则将右中括号"]"入栈，并将指针向后移动一位
7. 碰到左大括号"{"，则将右大括号"}"入栈，并将指针向后移动一位
8. 如果指针在0位置(表示右半边符号在左半边符号之前出现),返回false
9. 如果指针所在位置符号不等于当前遍历的符号,返回false
6. 遍历结束，如果指针位置在0，则全部匹配上，放回true，否则标示存在符号没有匹配上的，返回false

二.时间复杂度：o(N)
三.空间复杂度：o(N))
四.执行用时: 0 ms,超过来100%的java提交记录
五.内存消耗: 36.7 MB,超过了82%的java提交记录
六.java代码：
```
class Solution {
    public boolean isValid(String s) {
        if (s == null || (s.length() & 1) == 1) {
            return false;
        }
        if (s.length() == 0) {
            return true;
        }
        char[] chars = new char[s.length()];
        int i = 0;
        for(char c : s.toCharArray()) {
            if ('(' == c) {
                chars[i ++] = ')';
            } else if ('{' == c) {
                chars[i ++] = '}';
            } else if ('[' == c) {
                chars[i ++] = ']';
            } else if (i == 0 || chars[-- i] != c) {
                return false;
            }
        }
        return i == 0;
    }
}
```