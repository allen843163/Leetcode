package com.example.kotlintestproject

import androidx.collection.ArrayMap
import org.junit.Test

import org.junit.Assert.*
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.abs
import kotlin.math.log10
import kotlin.math.max

/**
 * Question : https://leetcode.com/problems/3sum/
 */
class LeetCodeQ15 {
    /**
     * 參考作法:https://www.itread01.com/content/1545883407.html
     * (排序後再做)
     */
    fun ThreeSum_MySelf_BetterSolution(nums: IntArray): List<List<Int>> {
        println("start")

        var ansList = listOf<List<Int>>()

        var questionBuf : Int? = null

        Arrays.sort(nums)

        println("question sort : ".plus(nums.contentToString()))

        for(i in 0 .. nums.size - 3) {
            println("i = ".plus(i))

            if(questionBuf != null && questionBuf == nums[i]) {
                continue
            }
            var leftIndex = i + 1

            var rightIndex = nums.size - 1

            var leftBuf : Int? = null

            var rightBuf : Int? = null

            while (leftIndex < rightIndex) {
                println("Lindex = ".plus(leftIndex))
                println("Rindex = ".plus(rightIndex))

                var sumBuf = (nums[leftIndex] + nums[rightIndex])

                if(leftBuf != null && leftBuf == nums[leftIndex]) {
                    leftIndex ++

                    continue
                }
                else if(rightBuf != null && rightBuf == nums[rightIndex]) {
                    rightIndex --

                    continue
                }
                if(sumBuf + nums[i] == 0) {
                    ansList = ansList.plus(listOf(listOf(nums[i], nums[leftIndex], nums[rightIndex])))

                    leftBuf = nums[leftIndex]

                    rightBuf = nums[rightIndex]
                }
                else if(sumBuf > Math.abs(nums[i])) {
                    rightIndex --
                }
                else {
                    leftIndex ++
                }
            }

            questionBuf = nums[i]
        }
        return ansList
    }

    /**
     * Too Slow
     */
    fun ThreeSum_MySelf(nums: IntArray): List<List<Int>> {
        println("start")

        var ansList = listOf<List<Int>>()

        var questionMap = HashMap<Int, Int>()

        nums.forEachIndexed { index, i ->
            questionMap.put(i, index)
        }

        var map1 = HashMap<Int, Int>()

        for(i in 0 .. nums.size -3) {
            println("i = ".plus(i))

            if(map1.containsKey(nums[i])) {
                continue
            }

            var map2 = HashMap<Int, Int>()

            for(z in i+1 until nums.size) {
                var buf = (nums[i] + nums[z]) * -1

                println("buf = ".plus(buf))

                if(map2.containsKey(nums[z])) {
                    continue
                }
                if(map1.containsKey(nums[z]) || map1.containsKey(buf)) {
                    continue
                }

                println("quesbuf = ".plus(questionMap.get(buf)))

                if(questionMap.containsKey(buf) && questionMap.get(buf) != z && questionMap.get(buf) != i) {
                    ansList = ansList.plus(listOf(listOf(nums[i], nums[z], buf)))

                    println("insert = ".plus(nums[i]).plus("/" + nums[z]).plus("/" +buf))

                    map2.put(nums[z], z)

                    map2.put(buf, z)
                }
            }

            map1.put(nums[i], i)
        }

        return ansList
    }


    @ExperimentalStdlibApi
    @Test
    fun addition_isCorrect() {
        assertEquals(listOf(listOf(-1, -1, 2), listOf(-1, 0, 1)), ThreeSum_MySelf_BetterSolution(arrayListOf(-1,0,1,2,-1,-4).toIntArray()))
        assertEquals(listOf(listOf(-1, 0, 1)), ThreeSum_MySelf_BetterSolution(arrayListOf(1,-1,-1,0).toIntArray()))
        assertEquals(
            listOf(listOf(-4, -2, 6), listOf(-4, 0, 4),
                listOf(-4, 1 , 3), listOf(-4, 2, 2),
                listOf(-2, -2, 4), listOf(-2, 0, 2)
            )
            , ThreeSum_MySelf_BetterSolution(arrayListOf(-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6).toIntArray()))
    }
}
