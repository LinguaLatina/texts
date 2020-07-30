// make a single markdown file suitable for converting to PDF
//
// Example invocation of pandoc to use a font including polytonic
// Greek and generate a pdf:
//
// pandoc pliny.md -s -o pliny.pdf --pdf-engine=xelatex --variable mainfont="Lucida Grande"
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._
import java.io.PrintWriter
import java.io.File

// Save yourself a little typing by predefining
// these file names:
val plinyCex = "texts/latin24/pliny-letters.cex"
val hyginusCex = "texts/latin23/hyginus.cex"

// Make Corpus object from CEX source file
def corpus(fileName: String): Corpus = {
  CorpusSource.fromFile(fileName, cexHeader = true)
}



// Format a single citable node in markdown.
def formatNode(n: CitableNode) : String = {
  s"*${n.urn.passageComponent}*. " + n.text + "\n\n"
}

// Compose a single markdown string for the corpus.
def markdown(corpus: Corpus, title: String ) = {
  // Collapse corpus by one level
  val pageUrns = corpus.nodes.map(_.urn.collapsePassageBy(1)).distinct

  val sections = for ( (pg, idx) <- pageUrns.zipWithIndex) yield {
    val currentLabel = "### " + pg.passageComponent + "\n\n"
    println("Formatting " + pg.passageComponent + s" (${idx}/${pageUrns.size})")
    val pageCorpus = corpus ~~ pg
    val nodeMd = pageCorpus.nodes.map(n => formatNode(n)).mkString("\n\n")
    currentLabel + nodeMd
  }
  s"# ${title}\n\n" + sections.mkString("\n\n")
}
