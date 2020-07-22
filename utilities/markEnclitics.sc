



// Recursively cycle through substition list,
// and replace all occurrences of each item found in
// a string with a hyphenated version of the specified
// enclitic.
def substituteEnclitic(
  txt: String,
  enclitic: String,
  sub: String,
  subList: Vector[String]) :  String = {
    val hyphenate = sub.replaceFirst(enclitic + "$", "-" + enclitic)

    if (subList.isEmpty) {
      txt.replaceAll(sub, hyphenate)

    } else {
      val newText =   txt.replaceAll(sub, hyphenate)
      substituteEnclitic(newText, enclitic, subList.head, subList.tail)
    }
  }


import scala.io.Source

// For all items listed in a file of substition strings,
// hyphenate occurrences of given enclitic in a text read
// from a file.
def modifyText(
  srcFile: String,
  substitionListFile: String,
  enclitic: String = "que") : String = {
  val txt  = Source.fromFile(srcFile).getLines.mkString("\n")
  val substitutionList = Source.fromFile(substitionListFile).getLines.toVector
  val modified = substituteEnclitic(txt, enclitic, substitutionList.head,  substitutionList.tail)
  modified
}
