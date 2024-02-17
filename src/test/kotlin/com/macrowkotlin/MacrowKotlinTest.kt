package com.macrowkotlin

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MacrowKotlinTest {
    class HogeMacrow : MacrowKotlin() {
        init {
            rule("something") {
                "trouble"
            }
            rule("length") { obj ->
                (obj as? List<*>)?.size?.toString() ?: ""
            }
        }
    }

    @Test
    fun testWithApplyRule() {
        val macrow = HogeMacrow()
        val result1 = macrow.apply("#{something} happened")
        val result2 = macrow.apply("[1, 2] length is #{length}", listOf(1, 2))
        assertEquals("trouble happened", result1)
        assertEquals("[1, 2] length is 2", result2)
    }

    @Test
    fun testWithApplyRuleWhenNoRule() {
        val result = HogeMacrow().apply("#{hoge} happened")
        assertEquals("hoge happened", result)
    }

    @Test
    fun testWithApplyPrefixAndSuffix() {
        class OverridePrefixAndSuffix : MacrowKotlin() {
            override val prefix = "##"
            override val suffix = "##"

            init {
                rule("something") {
                    "trouble"
                }
            }
        }

        val result = OverridePrefixAndSuffix().apply("##something## happened")
        assertEquals("trouble happened", result)
    }
}
