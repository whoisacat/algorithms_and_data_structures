fun main() {
    val wordBreaker = WordBreaker()
    println(wordBreaker.wordBreak_("abcede",
        listOf("abc", "abcd", "de","ce")))
}

class WordBreaker {
    fun wordBreak_(s: String, wordDict: List<String>): Boolean {
        val words: Set<String> = HashSet(wordDict)
        val n = s.length
        val f = BooleanArray(n + 1)
        f[0] = true
        for (i in 1..n) {
            for (j in 0 until i) {
                if (f[j] && words.contains(s.substring(j, i))) {
                    f[i] = true
                    break
                }
            }
        }
        return f[n]
    }
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val dic = ArrayList(wordDict);
        for (i in wordDict.lastIndex downTo 1) {
            val d = ArrayList(dic)
            d.remove(dic[i])
            if (wordBreakDown(wordDict[i], d)) {
                dic.remove(wordDict[i])
            }
        }
        return wordBreakDown(s, dic)
    }

    fun wordBreakDown(s: String, wordDict: List<String>): Boolean {

        if (s.isNullOrBlank()) return true
        var result = false
        for (word in wordDict) {
            if (result) break
            if (s.length >= word.length && s.substring(0, word.length) == word) {
                val second = s.substring(word.length, s.length)
                if (second.isEmpty()) {
                    result = true
                    break
                }
                else result = wordBreakDown(second, wordDict)
            }
        }
        return result
    }
}