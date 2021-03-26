package com.github.kereis.medit.domain.format

abstract class MarkupLanguageParser {

    abstract fun parse(input: String): CharSequence
}
