package com.github.kereis.medit.domain.explorer.files

import com.github.kereis.medit.domain.editor.Document
import java.net.URI

/**
 * # FileLoader
 *
 * Defines interface for file loaders that can be implemented at plugin layer
 *
 * @author kereis
 * @version 1.0.0
 */
abstract class FileLoader {

    /**
     * Load a file by its [URI], read the lines and put them into a [Document]
     *
     * @return [Result] If loading is successful, then the [Result] contains a [Document],
     *                  else [Error]
     */
    abstract suspend fun load(uri: URI): Document?

    /**
     * Use the [Document] to read the lines and save them into a [java.io.File]
     *
     * @return [Result] with [Error] if saving process fails
     */
    abstract suspend fun save(document: Document): Boolean
}
