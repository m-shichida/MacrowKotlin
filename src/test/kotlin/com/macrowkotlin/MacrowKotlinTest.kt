package com.macrowkotlin

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MacrowKotlinTest {
    @Test
    fun testWithApply() {
        val macrowKotlin = MacrowKotlin()
        val result = macrowKotlin.apply("Something")
        assertEquals("Something happened", result)
    }
}
