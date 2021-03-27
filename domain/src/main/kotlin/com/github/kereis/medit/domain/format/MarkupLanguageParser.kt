package com.kereis.github.medit.domain.format

abstract class MarkupLanguageParser {

    abstract fun parse(input: String): CharSequence
}
