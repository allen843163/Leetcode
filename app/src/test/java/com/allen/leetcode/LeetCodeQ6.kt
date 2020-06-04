package com.example.kotlintestproject

import org.junit.Test

import org.junit.Assert.*
import java.lang.StringBuilder

/**
 * Question : https://leetcode.com/problems/zigzag-conversion/
 */
class LeetCodeQ6 {
    fun ZigZagConversion_MySelf(s: String, numRows: Int): String {
        if(s.length <= numRows || numRows == 1) {
            return s
        }

        var strLen = s.length

        var columnInterval = numRows * 2 -2

        var ansStrBuilder = StringBuilder()

        for(pointer in 0 until numRows) {
            var step = 0

            if(pointer == 0 || pointer == numRows-1) {
                while (pointer + columnInterval * step < strLen) {
                    ansStrBuilder.append(s[pointer + columnInterval * step])

                    step++
                }
            }
            else {
                while (pointer + columnInterval * step < strLen) {
                    var appendPointer = pointer + columnInterval * step

                    ansStrBuilder.append(s[appendPointer])

                    if((numRows - 1 - pointer) * 2 + appendPointer < strLen) {
                        ansStrBuilder.append(s[(numRows - 1 - pointer) * 2 + appendPointer])
                    }

                    step++
                }
            }

        }

        return ansStrBuilder.toString()
    }

    @ExperimentalStdlibApi
    @Test
    fun addition_isCorrect() {
        assertEquals("PINALSIGYAHRPI", ZigZagConversion_MySelf("PAYPALISHIRING", 4))
        assertEquals("A", ZigZagConversion_MySelf("A", 4))
        assertEquals("AB", ZigZagConversion_MySelf("AB", 1))
        assertEquals("ABCED", ZigZagConversion_MySelf("ABCDE", 4))
    }
}
