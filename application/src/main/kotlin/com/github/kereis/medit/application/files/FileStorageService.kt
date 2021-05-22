package com.github.kereis.medit.application.files
//
// import com.github.kereis.medit.domain.async.Result
// import com.github.kereis.medit.domain.editor.Document
// import com.github.kereis.medit.domain.editor.History
// import com.github.kereis.medit.domain.explorer.files.File
// import com.github.kereis.medit.domain.mapping.DataMapper
// import kotlinx.coroutines.CoroutineDispatcher
// import kotlinx.coroutines.Job
// import kotlinx.coroutines.withContext
// import java.io.IOException
// import java.nio.file.Path
//
// /**
//  * # FileStorageService
//  *
//  * This service takes care of file discovery and IO operations
//  *
//  * @version 1.0.0
//  * @author kereis
//  */
// class FileStorageService(
//     dispatcher: CoroutineDispatcher,
//     private val fileToJavaNIOFileMapper: DataMapper<File, java.io.File>
// ) {
//
//     private val coroutineContext = Job() + dispatcher
//
//     fun findFile(path: Path): File? {
//         val file = path.toFile()
//
//         return if (file.exists()) {
//             fileToJavaNIOFileMapper.from(file)
//         } else {
//             null
//         }
//     }
//
//     /**
//      * Saves a document asynchronously in a Kotlin coroutine
//      * @return
//      *  - [Result.State.Done] if operation is successful
//      *  - [Result.Failure] if operation fails (i. e. [IOException])
//      */
//     suspend fun saveDocument(
//         document: Document
//     ): Result<Nothing, Error> =
//         withContext(coroutineContext) {
//             val javaFile = fileToJavaNIOFileMapper.to(document.file)
//
//             if (!javaFile.exists())
//                 javaFile.mkdirs()
//
//             try {
//                 javaFile.writeText(document.content)
//             } catch (e: IOException) {
//                 return@withContext Result.Failure(Error(e))
//             }
//
//             return@withContext Result.State.Done
//         }
//
//     /**
//      * Loads document in Kotlin coroutine
//      * @return
//      *  - [Result.Success] with [Document] if successful
//      *  - [Result.Failure] on error
//      */
//     suspend fun loadDocument(file: File): Result<Document, Error> =
//         withContext(coroutineContext) {
//             val javaFile = fileToJavaNIOFileMapper.to(file)
//
//             if (!javaFile.exists())
//                 return@withContext Result.Failure(Error("file not found: $file"))
//
//             val content = javaFile.readText()
//
//             return@withContext Result.Success(
//                 Document("", content, File.updateAccessTime(file), History())
//             )
//         }
// }