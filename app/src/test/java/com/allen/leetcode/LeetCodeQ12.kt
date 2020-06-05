package com.example.kotlintestproject

import androidx.collection.ArrayMap
import org.junit.Test

import org.junit.Assert.*
import kotlin.math.log10
import kotlin.math.max

/**
 * Question : https://leetcode.com/problems/integer-to-roman/
 */
class LeetCodeQ12 {
    fun IntegertoRoman_MySelf(num: Int): String {
        println("start")

        var question = num

        var ans = StringBuffer("")

        (question/1000).let {
            for(i in 0 until it) {
                ans.append("M")
            }
        }

        (question % 1000 / 100).let {
            if(it == 9) {
                ans.append("CM")
            }
            else if(it in 5..8) {
                ans.append("D")

                for(i in 0 until (it - 5)) {
                    ans.append("C")
                }
            }
            else if(it == 4){
                ans.append("CD")
            }
            else {
                for(i in 0 until it) {
                    ans.append("C")
                }
            }
        }

        (question % 100 / 10).let {
            if(it == 9) {
                ans.append("XC")
            }
            else if(it in 5..8) {
                ans.append("L")

                for(i in 0 until (it - 5)) {
                    ans.append("X")
                }
            }
            else if(it == 4){
                ans.append("XL")
            }
            else {
                for(i in 0 until it) {
                    ans.append("X")
                }
            }
        }

        (question % 10).let {
            if(it == 9) {
                ans.append("IX")
            }
            else if(it in 5..8) {
                ans.append("V")

                for(i in 0 until (it - 5)) {
                    ans.append("I")
                }
            }
            else if(it == 4){
                ans.append("IV")
            }
            else {
                for(i in 0 until it) {
                    ans.append("I")
                }
            }
        }

        println(ans)

        return ans.toString()
    }
//    fun IntegertoRoman_MySelf(num: Int): String {
//        println("start")
//
//        var question = num
//
//        var ans = StringBuffer("")
//
//        ans.append(compute(question, 1000, "", "", "M"))
//        ans.append(compute(question, 100, "M", "D", "C"))
//        ans.append(compute(question, 10, "C", "L", "X"))
//        ans.append(compute(question, 1, "X", "V", "I"))
//
//        println(ans)
//
//        return ans.toString()
//    }
//
//    fun compute(question : Int, divisor : Int, maxStr : String, midStr : String, minStr : String) : StringBuffer{
//        var ansStr = StringBuffer("")
//
//        var modNum = 10 * divisor
//
//        println("modNum".plus(modNum))
//
//        (question % modNum / divisor).let {
//            println("it".plus(it))
//
//            if(it == 9) {
//                ansStr.append(minStr).append(maxStr)
//            }
//            else if(it in 5..8) {
//                ansStr.append(midStr)
//
//                for(i in 0 until (it - 5)) {
//                    ansStr.append(minStr)
//                }
//            }
//            else if(it == 4){
//                ansStr.append(minStr).append(midStr)
//            }
//            else {
//                for(i in 0 until it) {
//                    ansStr.append(minStr)
//                }
//            }
//        }
//
//        return ansStr
//    }


    @ExperimentalStdlibApi
    @Test
    fun addition_isCorrect() {
        assertEquals("MCMXCIV", IntegertoRoman_MySelf(1994))
        assertEquals("IX", IntegertoRoman_MySelf(9))
    }
}
