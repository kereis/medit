package com.github.kereis.medit.domain.explorer.files

import java.net.URISyntaxException
import java.time.OffsetDateTime
import org.junit.Assert
import org.junit.Test

class FileReferenceTest {

    @Test
    fun `Instantiate without exception`() {
        try {
            FileReference(
                1,
                "TestFile.md",
                "file:///TestFile.md",
                OffsetDateTime.now()
            )
        } catch (e: URISyntaxException) {
            Assert.fail(e.localizedMessage)
        }
    }

    @Test
    fun `Instantiate with exception`() {
        Assert.assertThrows(URISyntaxException::class.java) {
            FileReference(
                1,
                "TestFile.md",
                "++#+#+",
                OffsetDateTime.now()
            )
        }
    }
}
