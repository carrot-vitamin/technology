//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 示例 1: 
//
// 输入: ["flower","flow","flight"]
//输出: "fl"
// 
//
// 示例 2: 
//
// 输入: ["dog","racecar","car"]
//输出: ""
//解释: 输入不存在公共前缀。
// 
//
// 说明: 
//
// 所有输入只包含小写字母 a-z 。 
// Related Topics 字符串 
// 👍 1282 👎 0

package com.project.java.technology.primary.code.leetcode.editor.cn;


/**
 * 2020-09-25 17:03:18
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        Solution solution = new LongestCommonPrefix().new Solution();
        System.out.println(solution.longestCommonPrefix(new String[] {}));



    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            } else if (strs.length == 1) {
                return strs[0];
            }

            for (String s : strs) {
                if (s == null || "".equals(s) || "".equals(s.trim())) {
                    return "";
                }
            }

            String shorted = strs[0];
            int i = shorted.length();
            for (String s : strs) {
                if (s.length() < i) {
                    shorted = s;
                    i = s.length();
                }
            }

            String s = "";
            for (int j = shorted.length(); j > 0; j --) {
                String prefix = shorted.substring(0, j);
                if (isCommonPrefix(prefix, strs)) {
                    s = prefix;
                    break;
                }
            }

            return s;

        }

        private boolean isCommonPrefix(String s, String[] strs) {
            boolean r = true;
            for (String s1 : strs) {
                if (!s1.startsWith(s)) {
                    r = false;
                    break;
                }
            }
            return r;
        }



    }
//leetcode submit region end(Prohibit modification and deletion)

}