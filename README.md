# simpleLzwAlgorithm
simple Lempel-Ziv-Welch compression algorithm for ASCII characters

This LZW algortihm works with a pre-existing dictionary which consists of all printable ASCII characters.
Restrictions: the output of Decoder.action() is lowercase.
Error Avoidance: the input String is checkes for non-ASCII characters
