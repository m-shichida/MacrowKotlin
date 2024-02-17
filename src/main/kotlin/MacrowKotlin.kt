package com.macrowkotlin

public typealias Rules = MutableMap<String, (Any?) -> String>

open class MacrowKotlin {
    open val prefix = "#\\{"
    open val suffix = "\\}"
    open val rules: Rules = mutableMapOf()

    fun apply(str: String): String {
        val keyword = extractKeyword(str)
        val value = rules[keyword]?.invoke(keyword) ?: keyword
        return str.replace(Regex("${prefix}(.+?)${suffix}"), value)
    }

    private fun extractKeyword(str: String): String {
        val regex = Regex("${prefix}(.+?)${suffix}")
        val matchResult = regex.find(str)
        return matchResult?.groupValues?.get(1) ?: ""
    }
}
