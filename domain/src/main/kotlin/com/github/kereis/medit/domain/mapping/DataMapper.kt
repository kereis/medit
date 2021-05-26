package com.github.kereis.medit.domain.mapping

/**
 * # DataMapper
 *
 * Maps source ([S]) to target ([T]) and vice versa.
 */
interface DataMapper<S, T> {

    fun to(source: S): T

    fun from(target: T): S
}
