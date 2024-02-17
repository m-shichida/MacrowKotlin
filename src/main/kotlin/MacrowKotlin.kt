package com.macrowkotlin

open class MacrowKotlin {
    open val prefix = "#\\{"
    open val suffix = "\\}"
    private val rules: MutableMap<String, (Any?) -> String> = mutableMapOf()

    fun rule(keyword: String, value: (Any?) -> String) {
        rules[keyword] = value
    }

    fun apply(str: String, obj: Any? = null): String {
        val keyword = extractKeyword(str)
        val value = if (obj == null) {
            rules[keyword]?.invoke(keyword) ?: keyword
        } else {
            rules[keyword]?.invoke(obj) ?: keyword
        }
        return str.replace(Regex("${prefix}(.+?)${suffix}"), value)
    }

    private fun extractKeyword(str: String): String {
        val regex = Regex("${prefix}(.+?)${suffix}")
        val matchResult = regex.find(str)
        return matchResult?.groupValues?.get(1) ?: ""
    }
}
