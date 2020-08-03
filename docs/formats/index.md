

# Formats and editorial conventions

## Citable texts

- texts are citable by URN
- files are in CEX format.  They have a header line `#!ctsdata` followed by one line per citable passage.  Each line has two columns delimited by the `#` character. The first column is the passage's URN, the second its text contents.


## Overview of orthographies and character usage



- `latin23`: Latin alphabet with 23 alphabetic characters, and no distinction of *i/j* or *u/v*
- `latin24`: Latin alphabet with 24 alphabetic characters.  Vocalic *u* and consonantal *v* are distinct, but there is but no distinction of *i/j*.
- `latin25`: Latin alphabet with 25 alphabetic characters, including vocalic *u* and *i*, and consonantal *v* and *j*.



## Full list of recognized/allowed characters

EXPLICIT LIST TBA

Notes on four characters with special semantics:

1. The new line character `\n` separates records of citable nodes in the CEX files.
2. The pound sign `#` separate columns on a given line in the CEX files.
3. Since CEX is a line-oriented format, we use the pipe `|` to represent line breaks/new line characters within the textual contents of a citable node.
4.  Since it is not possible to infer enclitic usage automatically (is `ratione` the nominative singular of `ratio` + enclitic `ne` or the ablative singular?), we use the hyphen `-` to mark enclitic boundaries.

Because these characters have a special meaning in the structure of our data, they may not be used within the Latin text content of a citable node.

You may of course treat these characters however like for display purposes (replace `|` with `\n` and remove `-`, for example).
