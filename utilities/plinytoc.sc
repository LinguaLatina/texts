// make a table of contents by selecting title elements
// of Hyginus
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._
import java.io.PrintWriter
import java.io.File

// Save yourself a little typing by predefining
// these file names:
val plinyCex = "texts/latin24/pliny-letters.cex"

// Make Corpus object from CEX source file
def corpus(fileName: String = plinyCex): Corpus = {
  CorpusSource.fromFile(fileName, cexHeader = true)
}



// Compose a single markdown string for the corpus.
def markdown(corpus: Corpus, title: String ) = {
  /*
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
  */
}

def formatNode(cn: CitableNode): String = {
  val psg = cn.urn.passageComponent
  "- " + cn.text + ": [" + psg + "](../" + psg + "/)"
}

val c = corpus(plinyCex)


val titles = c.nodes.filter(_.urn.passageComponent.endsWith("greeting"))
val tnodes = titles.map( cn => CitableNode(cn.urn.collapsePassageBy(1), cn.text))


val headerYaml = "---\ntitle: \"Pliny, *Epistulae*: contents\"\nlayout: page\n---\n\n"

val header = "# \"Pliny, *Epistulae*: contents\"\n\n"
val md = header + tnodes.map(cn => formatNode(cn)).mkString("\n")

new PrintWriter("pliny-toc.md"){write(md); close;}
