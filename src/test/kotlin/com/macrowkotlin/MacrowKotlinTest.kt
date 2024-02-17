package com.macrowkotlin

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MacrowKotlinTest {
    class HogeMacrow : MacrowKotlin() {
        override val rules: Rules = mutableMapOf(
            "hoge" to { "fuga" }
        )
    }

    @Test
    fun testWithApplyRule() {
        val result = HogeMacrow().apply("#{hoge} happened")
        assertEquals("fuga happened", result)
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

            override val rules: Rules = mutableMapOf(
                "hoge" to { "fuga" }
            )
        }

        val result = OverridePrefixAndSuffix().apply("##hoge## happened")
        assertEquals("fuga happened", result)
    }
}
