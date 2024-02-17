package com.macrowkotlin

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MacrowKotlinTest {
    class HogeMacrow : MacrowKotlin() {
        init {
            rule("hoge") { "fuga" }
            rule("length") { getLength(it) }
        }

        private fun getLength(value: Any?): String {
            return when (value) {
                is List<*> -> value.size.toString()
                is String -> value.length.toString()
                else -> "0"
            }
        }
    }

    @Test
    fun testWithApplyRule() {
        val hoge = HogeMacrow()
        val result1 = hoge.apply("#{hoge} happened")
        val list = listOf(1, 2)
        val result2 = hoge.apply("[1, 2] length is #{length}", list)
        val result3 = hoge.apply("hoge length is #{length}", "hoge")
        assertEquals("fuga happened", result1)
        assertEquals("[1, 2] length is 2", result2)
        assertEquals("hoge length is 4", result3)
    }

    @Test
    fun testWithApplyRuleWhenNoRule() {
        val result = HogeMacrow().apply("#{fuga} happened")
        assertEquals("fuga happened", result)
    }

    @Test
    fun testWithApplyPrefixAndSuffix() {
        class OverridePrefixAndSuffix : MacrowKotlin() {
            override val prefix = "##"
            override val suffix = "##"

            init {
                rule("hoge") { "fuga" }
            }
        }

        val result = OverridePrefixAndSuffix().apply("##hoge## happened")
        assertEquals("fuga happened", result)
    }
}
