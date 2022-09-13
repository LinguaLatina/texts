using CitableBase
using CitableText
using CitableCorpus
using Orthography
using LatinOrthography
using SplitApplyCombine


f = joinpath(pwd(), "texts", "latin23", "hyginus.cex")
c = fromcex(f, CitableTextCorpus,FileReader)
o = latin23()




tkns = tokenize(c, o)

lexprs = filter(pr -> pr[2] == LexicalToken(), tkns)
lexstrs = map(pr -> pr[1].text, lexprs)
grouped = group(lexstrs)
counts = []
for k in keys(grouped)
    push!(counts, (k, length(grouped[k])))
end
sorted = sort(counts, by = pr -> pr[2], rev = true)

open("wordcounts.csv", "w") do io
    write(io, join(map(pr -> string(pr[1],",",pr[2]), sorted) ,"\n"))
end