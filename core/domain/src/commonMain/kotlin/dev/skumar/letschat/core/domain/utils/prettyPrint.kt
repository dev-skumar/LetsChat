package dev.skumar.letschat.core.domain.utils


fun Any.prettyPrint() = toString().let { toString ->

    var indentLevel = 0
    val indentWidth = 8

    fun padding() = "".padStart(indentLevel * indentWidth)
    buildString {
        var ignoreSpace = false
        toString.onEach { char ->
            when (char) {
                '(', '[', '{' -> {
                    indentLevel++
                    appendLine(char)
                    append(padding())
                }

                ')', ']', '}' -> {
                    indentLevel--
                    appendLine()
                    append(padding())
                    append(char)
                }

                ',' -> {
                    appendLine(char)
                    append(padding())
                    ignoreSpace = true
                }

                ' ' -> {
                    if (!ignoreSpace) append(char)
                    ignoreSpace = false
                }

                '=' -> {
                    append(" = ")
                }

                else -> append(char)
            }
        }
    }
}