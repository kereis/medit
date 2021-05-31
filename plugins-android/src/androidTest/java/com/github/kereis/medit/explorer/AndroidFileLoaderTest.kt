package com.github.kereis.medit.explorer

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.kereis.medit.TestUtils.retrieveAppContext
import com.github.kereis.medit.domain.editor.Document
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith
import java.io.File

@RunWith(AndroidJUnit4::class)
class AndroidFileLoaderTest {

    @Rule
    @JvmField // Make sure JUnit detects this field as public field on JVM
    val tempFolder = TemporaryFolder()

    val coroutineDispatcher = Dispatchers.IO

    @Test
    fun test_loadMarkdownFile() {
        val expectedContent =
            """
            # test_MarkdownFile.md
            This is a test file in order to test `AndroidFileLoader`s loading functionality.
            """

        val testMarkdownFile = provideMarkdownFile(expectedContent)

        val androidFileLoader = AndroidFileLoader(
            dispatcher = coroutineDispatcher,
            context = retrieveAppContext()
        )

        val loadedDocument: Document?
        runBlocking {
            loadedDocument = androidFileLoader.load(testMarkdownFile.toURI())
        }

        Assert.assertNotNull(loadedDocument)

        val actualContent = loadedDocument?.content

        Assert.assertEquals(expectedContent, actualContent)
    }

    @Test
    @Throws(Exception::class)
    fun test_saveAndLoadMarkdownFile() {
        val expectedContent =
            """
            # test_MarkdownFile.md
            This is a test file in order to test `AndroidFileLoader`s saving functionality.
            """

        val testMarkdownFile = provideMarkdownFile("")

        val androidFileLoader = AndroidFileLoader(
            dispatcher = coroutineDispatcher,
            context = retrieveAppContext()
        )

        // 1. Load empty file once and check if it's actually empty
        val loadedDocument: Document?
        runBlocking {
            loadedDocument = androidFileLoader.load(testMarkdownFile.toURI())
        }
        Assert.assertNotNull(loadedDocument)

        val actualContent = loadedDocument!!.content
        Assert.assertTrue(actualContent.isEmpty())

        // 2. Set content of file to expected content and save
        loadedDocument.content = expectedContent
        val saveSuccessful: Boolean
        runBlocking {
            saveSuccessful = androidFileLoader.save(loadedDocument)
        }

        Assert.assertTrue(saveSuccessful)

        // 3. Reload same markdown file and check if loaded content matches expected content
        val reloadedDocument: Document?
        runBlocking {
            reloadedDocument = androidFileLoader.load(testMarkdownFile.toURI())
        }
        Assert.assertNotNull(reloadedDocument)

        val reloadedActualContent = reloadedDocument!!.content

        Assert.assertEquals(expectedContent, reloadedActualContent)
    }

    @Test
    @Throws(Exception::class)
    fun test_saveMarkdownFile() {
        val expectedContent =
            """
            # test_MarkdownFile.md
            This is a test file in order to test `AndroidFileLoader`s loading functionality.
            """

        val testMarkdownFile = provideMarkdownFile("")

        val androidFileLoader = AndroidFileLoader(
            dispatcher = coroutineDispatcher,
            context = retrieveAppContext()
        )

        val loadedDocument: Document?
        runBlocking {
            loadedDocument = androidFileLoader.load(testMarkdownFile.toURI())
        }
        Assert.assertNotNull(loadedDocument)

        val actualContent = loadedDocument!!.content
        Assert.assertTrue(actualContent.isEmpty())

        loadedDocument.content = expectedContent

        val saveSuccessful: Boolean
        runBlocking {
            saveSuccessful = androidFileLoader.save(loadedDocument)
        }

        Assert.assertTrue(saveSuccessful)
    }

    private fun provideMarkdownFile(content: String): File {
        val testMarkdownFile = tempFolder.newFile("test_MarkdownFile.md")

        testMarkdownFile.writeText(content)

        return testMarkdownFile
    }
}
