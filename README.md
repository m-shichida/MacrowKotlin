## Basic Usage

To use MacrowKotlin, define a class with rules for placeholder replacement as shown below:

```kotlin
class MyMacros : MacrowKotlin() {
    init {
        rule("something") {
            "trouble"
        }
        rule("length") { obj ->
            (obj as? List<*>)?.size?.toString() ?: ""
        }
    }
}

val macros = MyMacros()

val result1 = macros.apply("#{something} happened")
// Output: "trouble happened"

val array = listOf(1, 2)
val result2 = macros.apply("object length is #{length}", array)
// Output: "object length is 2"
```

## Custom Usage

```kotlin
class OverridePrefixAndSuffix : MacrowKotlin() {
    override val prefix = "##"
    override val suffix = "##"

    init {
        rule("something") {
            "trouble"
        }
    }
}

val customMacros = OverridePrefixAndSuffix()

val result = customMacros.apply("##hoge## happened")
// Output: "trouble happened"
```
