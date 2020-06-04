package com.example.kotlintestproject

import androidx.collection.ArrayMap
import org.junit.Test

import org.junit.Assert.*

/**
 * Question : https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
class LeetCodeQ3 {

    fun lengthOfLongestSubstring_MySelf(s: String): Int {
        var map = HashMap<Char, Int>()

        var min = 0

        var max = 0

        var ans = 0

        s.toCharArray().let {
            if(it.size == 0) {
                ans = 0
            }
            else {
                it.forEachIndexed { index, c ->
                    when(map.containsKey(c)) {
                        true -> {
                            if(map.getValue(c) >= min) {
                                min = map.getValue(c) +1
                            }
                        }
                    }

                    map.put(c, index)

                    max = index + 1

                    ans = maxOf(max - min, ans)
                }
            }
        }

        return ans
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(2, lengthOfLongestSubstring_MySelf("au"))
        assertEquals(1, lengthOfLongestSubstring_MySelf(" "))
        assertEquals(3, lengthOfLongestSubstring_MySelf("pwwkew"))
        assertEquals(2, lengthOfLongestSubstring_MySelf("aab"))
        assertEquals(5, lengthOfLongestSubstring_MySelf("ascdvdf"))
        assertEquals(3, lengthOfLongestSubstring_MySelf("abcabcbb"))
        assertEquals(2, lengthOfLongestSubstring_MySelf("cdd"))
        assertEquals(3, lengthOfLongestSubstring_MySelf("ohomm"))
        assertEquals(2, lengthOfLongestSubstring_MySelf("abba"))
    }
}
