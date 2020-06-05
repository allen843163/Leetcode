package com.example.kotlintestproject

import org.junit.Test

import org.junit.Assert.*

/**
 * Question : https://leetcode.com/problems/container-with-most-water/
 */
class LeetCodeQ11 {
    fun ContainerWithMostWater_MySelf(height: IntArray): Int {
        println("start")

        var map = HashMap<Int, Int>()

        var searchLength =  (height.size / 2) + (height.size % 2)

        var nowLeftBoard = 0;

        var nowRightMaxIndex = height.size - 1

        var nowRightBoard = 0

        for(i in 0 .. searchLength) {
            println("i = ".plus(i))

            if(map.containsKey(i)) {
                continue
            }
            else if(height[i] <= nowLeftBoard) {
                continue
            }

            for(z in nowRightMaxIndex downTo i) {
                println("z = ".plus(z))

                if(map.containsKey(z)) {
                    continue
                }
                else if(height[z] <= nowRightBoard) {
                    continue
                }

                if(height[z] >= height[i]) {
                    var max = height[i] * (z - i)

                    println("max = ".plus(max))

                    map[i] = max

                    nowLeftBoard = maxOf(height[i], nowLeftBoard)

                    nowRightMaxIndex = z

                    break
                }
                else {
                    var max = height[z] * (z - i)

                    println("max = ".plus(max))

                    map[z] = max

                    nowRightBoard = maxOf(height[z], nowRightBoard)
                }
            }
        }

        return map.maxBy {
            it.value
        }?.value?:0
    }

    @ExperimentalStdlibApi
    @Test
    fun addition_isCorrect() {
//        assertEquals(49, ContainerWithMostWater_MySelf(intArrayOf(1,8,6,2,5,4,8,3,7)))
        assertEquals(24, ContainerWithMostWater_MySelf(intArrayOf(1,2,3,4,5,25,24,3,4)))
        assertEquals(72, ContainerWithMostWater_MySelf(intArrayOf(9,6,14,11,2,2,4,9,3,8)))

    }
}
