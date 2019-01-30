import java.io.File
import java.nio.file.Files
import java.nio.file.Paths


//The number of characters to extract from the file name.
 var PLAYLIST_NUMBER_SIZE  = 4

fun main(args: Array<String>): Unit {



    when {
        args.isEmpty() -> println("Directory path can't be blank.")
        else -> {
            val location = args[0].trim()

            when {
                location.isBlank() -> {
                    println("Please enter an existing directory path.")

                }
                else -> {
                    val folderExists = Files.exists(Paths.get(location))

                    when {
                        folderExists -> {
                            File(location).listFiles().forEach {


                                renameFiles(it, location)

                            }
                        }


                    }
                }

            }
            println("Files have been successfully renamed")
        }
    }


}


fun renameFiles(file: File, location: String) {
    when {
        file.isFile && !file.isDirectory && !file.isHidden -> {

            try{
                val filename = file.name
                val endExtractionPoint: Int = filename.indexOf('.')
                val startExtractionPoint: Int = endExtractionPoint -  PLAYLIST_NUMBER_SIZE
                val videoNumber: String = filename.substring(startExtractionPoint, endExtractionPoint)

                val newFileName = "$location/$videoNumber-$filename"
                val newFile = File(newFileName)
                file.renameTo(newFile)
            }
            catch (e: StringIndexOutOfBoundsException){
                println("Files not renamed. Try again")
            }


        }
    }




}

