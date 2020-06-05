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

        var nowLeftBoard = 0;

        var nowRightMaxIndex = height.size - 1

        var nowRightBoard = 0

        for(i in height.indices) {
            println("i = ".plus(i))

            if(map.containsKey(i) || height[i] <= nowLeftBoard) {
                println("continue1")

                continue
            }
            else if(i >= nowRightMaxIndex) {
                break
            }
            else {

                if(height[nowRightMaxIndex] >= height[i]) {
                    var max = height[i] * (nowRightMaxIndex - i)

                    println("max1 = ".plus(max))

                    map[i] = max

                    nowLeftBoard = maxOf(height[i], nowLeftBoard)

                    continue
                }
                else {
                    while (nowRightMaxIndex > i) {
                        println("nowRightMaxIndex".plus(nowRightMaxIndex))

                        if(height[nowRightMaxIndex] >= height[i]) {
                            var max = height[i] * (nowRightMaxIndex - i)

                            println("max1 = ".plus(max))

                            map[i] = max

                            nowLeftBoard = maxOf(height[i], nowLeftBoard)

                            break
                        }

                        if(map.containsKey(nowRightMaxIndex) || height[nowRightMaxIndex] < nowRightBoard) {
                            nowRightMaxIndex --

                            continue
                        }

                        var max = height[nowRightMaxIndex] * (nowRightMaxIndex - i)

                        println("max2 = ".plus(max))

                        map[nowRightMaxIndex] = max

                        nowRightBoard = maxOf(height[nowRightMaxIndex], nowRightBoard)

                        println("nowRightBoard".plus(nowRightBoard))

                        nowRightMaxIndex --
                    }
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
        assertEquals(49, ContainerWithMostWater_MySelf(intArrayOf(1,8,6,2,5,4,8,3,7)))
        assertEquals(24, ContainerWithMostWater_MySelf(intArrayOf(1,2,3,4,5,25,24,3,4)))
        assertEquals(72, ContainerWithMostWater_MySelf(intArrayOf(9,6,14,11,2,2,4,9,3,8)))
        assertEquals(112, ContainerWithMostWater_MySelf(intArrayOf(14,0,12,3,8,3,13,5,14,8)))
        assertEquals(55, ContainerWithMostWater_MySelf(intArrayOf(4,4,2,11,0,11,5,11,13,8)))
        assertEquals(42, ContainerWithMostWater_MySelf(intArrayOf(4,6,4,4,6,2,6,7,11,2)))

    }
}
