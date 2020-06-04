package com.example.kotlintestproject

import org.junit.Test

import org.junit.Assert.*
import java.lang.StringBuilder
import java.util.*

/**
 * Question : https://leetcode.com/problems/string-to-integer-atoi/
 */
class LeetCodeQ8 {

    fun myAtoi_MySelf(str: String): Int {
        var ans = str.trim()

        if(ans.isEmpty() || !((ans[0].toInt() in 48..57) || ans[0].toInt() == 45 || ans[0].toInt() == 43)) {
            println("return 0")

            return 0
        }

        var hasNumber = false

        var symbol1 = false

        var error = false

        var ansIndex = 0

        var finalAns : String

        var questionArray = ans.toCharArray()

        for(i in questionArray.indices) {
            var value = questionArray[i]

            println("wtf?".plus(value))

            if(value.toInt() in 48..57) {
                hasNumber = true

                ansIndex = i
            }
            else if(value.toInt().equals(45) || value.toInt().equals(43)) {
                if(hasNumber) {
                    break
                }
                if(symbol1) {
                    error = true

                    break
                }
                ansIndex = i

                symbol1 = true
            }
            else {
                break
            }
        }


        if(!hasNumber || error) {
            return 0
        }

        finalAns = String(questionArray.copyOfRange(0, ansIndex + 1))

        println("ans = ".plus(finalAns))

        finalAns.toIntOrNull().let {
            if(it == null) {
                if(finalAns[0].toInt() == 45) {
                    return Int.MIN_VALUE
                }
                else {
                    return Int.MAX_VALUE
                }
            }
            else {
                return it
            }
        }
    }

    @ExperimentalStdlibApi
    @Test
    fun addition_isCorrect() {
        assertEquals(42, myAtoi_MySelf("42"))
        assertEquals(-42, myAtoi_MySelf("      -42"))
        assertEquals(-42, myAtoi_MySelf("   -42"))
        assertEquals(4193, myAtoi_MySelf("4193 with words"))
        assertEquals(-2147483648, myAtoi_MySelf("-91283472332"))
        assertEquals(3, myAtoi_MySelf("3.14159"))
        assertEquals(1, myAtoi_MySelf("+1"))
        assertEquals(0, myAtoi_MySelf("+-1"))
        assertEquals(-12, myAtoi_MySelf("  -0012a42"))
    }
}
