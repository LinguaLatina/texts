import edu.holycross.shot.ohco2._
import edu.holycross.shot.latin._
//import edu.holycross.shot.mid.validator._
import edu.holycross.shot.mid.orthography._


val lat24: MidOrthography = Latin24Alphabet

val orthoMap:  Map[String, MidOrthography]  = Map(
  "latin24" -> lat24
)

// To tokenize a corpus, we need:
// 1. a CorpusSource
// 2. an othography
//
def tokenizable(fileBase: String, orthoLabel: String): TokenizableCorpus = {
  val corpus = CorpusSource.fromFile(s"texts/${orthoLabel}/${fileBase}.cex", cexHeader = true)
  TokenizableCorpus(corpus, orthoMap(orthoLabel))
}



def thyginus = tokenizable("hyginus", "latin23")
def tpliny = tokenizable("pliny-letters", "latin24")
