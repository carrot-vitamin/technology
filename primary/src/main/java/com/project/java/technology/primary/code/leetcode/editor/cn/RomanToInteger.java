//罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。 
//
// 字符          数值
//I             1
//V             5
//X             10
//L             50
//C             100
//D             500
//M             1000 
//
// 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做 XXVII, 即为 XX + V + I
//I 。 
//
// 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5
// 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况： 
//
// 
// I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。 
// X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
// C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。 
// 
//
// 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。 
//
// 
//
// 示例 1: 
//
// 输入: "III"
//输出: 3 
//
// 示例 2: 
//
// 输入: "IV"
//输出: 4 
//
// 示例 3: 
//
// 输入: "IX"
//输出: 9 
//
// 示例 4: 
//
// 输入: "LVIII"
//输出: 58
//解释: L = 50, V= 5, III = 3.
// 
//
// 示例 5: 
//
// 输入: "MCMXCIV"
//输出: 1994
//解释: M = 1000, CM = 900, XC = 90, IV = 4. 
//
// 
//
// 提示： 
//
// 
// 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。 
// IC 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。 
// 关于罗马数字的详尽书写规则，可以参考 罗马数字 - Mathematics 。 
// 
// Related Topics 数学 字符串 
// 👍 1051 👎 0

package com.project.java.technology.primary.code.leetcode.editor.cn;


import java.util.HashMap;
import java.util.Map;

/**
 * 2020-09-25 10:49:26
 */
public class RomanToInteger {

    public static void main(String[] args) {
        Solution solution = new RomanToInteger().new Solution();
        System.out.println(solution.romanToInt("CM"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {

        Map<String, Integer> convertMap = new HashMap<String, Integer>() {
            private static final long serialVersionUID = 8996249267319685163L;

            {
                put("I", 1);
                put("V", 5);
                put("X", 10);
                put("L", 50);
                put("C", 100);
                put("D", 500);
                put("M", 1000);
                put("IV", 4);
                put("IX", 9);
                put("XL", 40);
                put("XC", 90);
                put("CD", 400);
                put("CM", 900);
            }
        };

        public int romanToInt(String s) {
            char[] chars = s.toCharArray();
            int i = 0;
            int count = 0;

            int length = chars.length;

            while (i < length) {
                if (i == length - 1) {
                    count += convertMap.get(String.valueOf(chars[i]));
                    break;
                } else if (i == length - 2) {
                    String key = String.valueOf(new char[] {chars[i], chars[i + 1]});
                    if (convertMap.containsKey(key)) {
                        count += convertMap.get(key);
                    } else {
                        count += (convertMap.get(String.valueOf(chars[i])) + convertMap.get(String.valueOf(chars[i + 1])));
                    }
                    break;
                } else {
                    char a = chars[i], b = chars[i + 1], c = chars[i + 2];

                    char [] m = new char[] {a, b};
                    char [] n = new char[] {b, c};

                    if (convertMap.containsKey(String.valueOf(m))) {
                        count += convertMap.get(String.valueOf(m));
                        i += 2;
                    } else if (convertMap.containsKey(String.valueOf(n))) {
                        count += convertMap.get(String.valueOf(a)) + convertMap.get(String.valueOf(n));
                        i += 3;
                    } else {
                        count += convertMap.get(String.valueOf(a));
                        i ++;
                    }

                }
            }

            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}