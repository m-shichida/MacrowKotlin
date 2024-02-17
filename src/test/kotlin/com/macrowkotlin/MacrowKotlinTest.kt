package com.macrowkotlin

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MacrowKotlinTest {
    class HogeMacrow: MacrowKotlin() {
        override val rules: Rules = mutableMapOf(
            "hoge" to { "fuga" }
        )
    }

    @Test
    fun testWithApplyRule() {
        val hoge = HogeMacrow()
        val result = hoge.apply("#{hoge} happened")
        assertEquals("fuga happened", result)
    }

    @Test
    fun testWithApplyRuleWhenNoRule() {
        val hoge = HogeMacrow()
        val result = hoge.apply("#{fuga} happened")
        assertEquals("fuga happened", result)
    }
}
