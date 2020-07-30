// make markdown files to browse a text
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._
import java.io.PrintWriter

// Save yourself a little typing by predefining
// these file names:
val plinyCex = "texts/latin24/pliny-letters.cex"
val hyginusCex = "texts/latin23/hyginus.cex"

// Make Corpus object from CEX source file
def corpus(fileName: String): Corpus = {
  CorpusSource.fromFile(fileName, cexHeader = true)
}


// Compose yml header for a page
def header (title: String): String = {
  s"---\nlayout: page\ntitle: ${title}\n---\n\n"
}

// Format a single citable node in markdown.
def formatNode(n: CitableNode) : String = {
  s"${n.urn.passageComponent.split("\\.").last}. " + n.text + "\n\n"
}

// Compose footer for a page.
def navlinks(prev: String, nxt: String) : String = {
  val previousLink = if (prev.isEmpty) {"previous: -"} else {
    s"previous: [${prev}](../${prev}/)"
  }
  val nextLink = if (nxt.isEmpty) {"next: -"} else {
    s"next: [${nxt}](../${nxt}/)"
  }
  s"| --- | --- |\n| ${previousLink} | ${nextLink} |"
}


// Compose markdown for a single page.
def composePage(title: String, prev: String, next: String, nodes: Vector[CitableNode], includeYaml: Boolean) : String = {
  val hdr = if (includeYaml) { header(title) } else { "" }
  val body = nodes.map(n => formatNode(n)).mkString("\n\n")
  val footer: String = navlinks(prev, next)

  hdr + body + "\n\n---\n\n" + footer
}


// Compose a write to disk pages in markdown format for each
// section of text.
def writeCorpus(corpus: Corpus, title: String, dir: String = "browsable", includeYaml: Boolean = false ) = {
  // Collapse corpus by one level
  val pageUrns = corpus.nodes.map(_.urn.collapsePassageBy(1)).distinct

  for ( (pg, idx) <- pageUrns.zipWithIndex) {
    val current = pg.passageComponent
    val prev = if (idx == 0) {""} else { pageUrns(idx - 1).passageComponent}
    val next = if (idx == (pageUrns.size - 1)) {""} else { pageUrns(idx + 1).passageComponent}
    val pageCorpus = corpus ~~ pg
    val markdown = composePage(title  +  current, prev, next, pageCorpus.nodes, includeYaml)

    val fileName = dir + "/" + current + ".md"
    new PrintWriter(fileName){write(markdown); close;}
    println("Write " + fileName + s" (${idx}/${pageUrns.size})")
  }
}
