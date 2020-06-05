package com.example.kotlintestproject

import androidx.collection.ArrayMap
import org.junit.Test

import org.junit.Assert.*

/**
 * Question : https://leetcode.com/problems/longest-palindromic-substring/
 */
class LeetCodeQ5 {
    /**
     * 馬拉車算法（Manacher‘s Algorithm）
     * 參考:https://www.cxyxiaowu.com/2665.html
     * (從中間擴散開尋找最長，並將陣列長度統一成奇數，方便處理 (baab || bab) <--這種狀況)
     */
    @ExperimentalStdlibApi
    fun longestPalindrome_BetterSolution(s: String) : String{
        if(s.length == 0 || s.length == 1) {
            return s
        }

        var array = CharArray(s.length * 2 + 1)

        array.set(0, '#')

        s.forEachIndexed { index, c ->
            array.set(1 + index * 2 , c)
            array.set(1 + (index * 2) + 1 , '#')
        }

        println("Array : ".plus(array.concatToString()))

        var maxStep = 0

        var ansPointer = 0

        var maxSize = array.size -1

        for(pointer in 0.. maxSize) {

            var step = 0

            if(maxStep >= maxSize - pointer || maxStep >= pointer) {
                continue
            }

            while (array[pointer + step].equals(array[pointer - step])) {

                step ++

                if(pointer + step >= array.size || pointer - step < 0) {
                    break
                }
            }

            if(step > maxStep) {

                maxStep = step - 1

                ansPointer = pointer
            }

        }

        println("step : ".plus(maxStep))

        println("ansPointer : ".plus(ansPointer))

        var ansStr = String(array.copyOfRange(ansPointer - maxStep, ansPointer + maxStep)).replace("#", "")

        return ansStr
    }

    fun longestPalindrome_MySelf(s: String): String {
        println("start : ".plus(s))

        var question = s.toCharArray()

        var max = question.size - 1

        if(max == 0 || max == -1) {
            return s
        }

        println("max : ".plus(max))

        var min = 0

        var ansStart = 0

        var  ansLen = 0

        var finalAnsStart = 0

        var  finalAnsLen = 0


        for(index1 in max downTo min) {
            if(finalAnsLen > index1 - min) {

                return String(question.copyOfRange(finalAnsStart, finalAnsStart + finalAnsLen +1))
            }

            println("index1 : ".plus(index1))

            var checkIndex = index1

            for(index2 in min .. checkIndex) {

                println("index2 : ".plus(index2))

                if(finalAnsLen > checkIndex - index2) {

                     break
                }

                for(pointer in index2 .. checkIndex) {

                    println("checkIndex : ".plus(checkIndex))

                    println("pointer : ".plus(pointer))

                    if(pointer >= checkIndex) {
                        if(ansLen > finalAnsLen) {
                            finalAnsStart = ansStart

                            finalAnsLen = ansLen
                            println("ans : ".plus(ansLen))
                        }

                        break
                    }
                    else if(question[pointer].equals(question[checkIndex])) {
                        if(checkIndex - pointer > ansLen) {
                            ansStart = pointer

                            ansLen = checkIndex - pointer

                            println("temp ans : ".plus(ansLen))
                        }

                        checkIndex --
                    }
                    else {
                        checkIndex = index1

                        ansLen = 0

                        ansStart = 0

                        break
                    }
                }
            }
        }

        if(finalAnsLen == 0) {
            return question[0].toString()
        }

        return String(question.copyOfRange(finalAnsStart, finalAnsStart + finalAnsLen +1))
    }

    @ExperimentalStdlibApi
    @Test
    fun addition_isCorrect() {
//        assertEquals("bccccb", longestPalindrome_MySelf("abccccbd"))
//        assertEquals("a", longestPalindrome_MySelf("ac"))
//        assertEquals("aba", longestPalindrome_MySelf("abadd"))
//        assertEquals("aaabaaa", longestPalindrome_MySelf("aaaabaaa"))

        assertEquals("bccccb", longestPalindrome_BetterSolution("abccccbd"))
        assertEquals("c", longestPalindrome_BetterSolution("ac"))
        assertEquals("aba", longestPalindrome_BetterSolution("abadd"))
        assertEquals("aaabaaa", longestPalindrome_BetterSolution("aaaabaaa"))
    }
}
