import edu.holycross.shot.ohco2._
import edu.holycross.shot.latin._
//import edu.holycross.shot.mid.validator._
import edu.holycross.shot.mid.orthography._


val linglatOrthos:  Map[String, MidOrthography]  = {
  "latin24" -> Latin24Alphabet
}

// To tokenize a corpus, we need:
// 1. a CorpusSource
// 2. an othography
//
def tokenizable(fileBase: String, orthoLabel: String): TokenizableCorpus = {
  val corpus = CorpusSource.fromFile(s"texts/${orthoLabel}/${fileBase}.cex", cexHeader = true)
  TokenizableCorpus(corpus, orthoMap(orthoLabel))
}
