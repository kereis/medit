package com.github.kereis.medit.adapter.android

import androidx.core.net.toUri
import androidx.documentfile.provider.DocumentFile
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith

/**
 * Tests [DocumentFileToJavaFileMapper] in an emulated Android device.
 *
 * These tests cannot be run as the Android API in simple unit tests are mocked to return null.
 * Changing `android.testOptions.unitTests.returnDefaultValues` to `true` did not have an effect.
 *
 * @version 1.0.0
 * @author kereis
 */
@RunWith(AndroidJUnit4::class)
class DocumentFileToJavaFileMapperInstrumentedTest {

    @Rule
    @JvmField
    val temporaryFolder = TemporaryFolder()

    private val documentFileToJavaFileMapper = DocumentFileToJavaFileMapper()

    @Test
    fun `DocumentFile to File mapping test`() {
        val fileName = "my_test_file_as_java_file"
        val javaFile = temporaryFolder.newFile(fileName)

        val androidFile = documentFileToJavaFileMapper.from(javaFile)

        assertEquals(javaFile.toUri(), androidFile.uri)
    }

    @Test
    fun `File to DocumentFile mapping test`() {
        val fileName = "my_test_file_as_android_file"
        val testFile = temporaryFolder.newFile(fileName)
        val androidFile = DocumentFile.fromFile(testFile)

        val javaFile = documentFileToJavaFileMapper.to(androidFile)

        assertEquals(androidFile.uri, javaFile.toUri())
    }
}