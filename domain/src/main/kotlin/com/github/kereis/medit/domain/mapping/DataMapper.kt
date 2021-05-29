package com.github.kereis.medit.domain.mapping

/**
 * # DataMapper
 *
 * Maps source ([S]) to target ([T]) and vice versa.
 */
interface DataMapper<S, T> {

    fun toTargetType(source: S): T

    fun toSourceType(target: T): S
}
