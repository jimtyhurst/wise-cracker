(ns wise-cracker.lexicon)

(def lexicon {#{:adjective} '("blue" "handsome" "lowly" "short" "small" "tall")
              #{:adverb} '("often" "quietly" "slowly" "softly")
              #{:determiner :singular} '("a" "every" "some" "the")
              #{:determiner :plural} '("some" "the")
              #{:noun :singular} '("man" "ocean" "person" "river" "road" "star" "stream" "tree" "woman")
              #{:noun :plural} '("men" "oceans" "people" "rivers" "roads" "stars" "streams" "trees" "women")
              #{:verb :intransitive :singular} '("listens" "rocks" "sucks")
              #{:verb :intransitive :plural} '("listen" "rock" "suck")
              #{:verb :transitive :singular} '("follows"  "longs for" "loves" "waits for")
              #{:verb :transitive :plural} '("follow"  "long for" "love" "wait for")
              })

(def random-number-generator (new java.util.Random))

(defn get-random-number
  "Returns a pseudorandom, uniformly distributed integer value between 0 (inclusive) and the specified value (exclusive)."
  [upper-limit]
  (. random-number-generator nextInt upper-limit))

(defn choose-randomly
  "Returns random selection from the given sequence."
  [items]
  (if (nil? items)
    nil
    (get items (get-random-number (count items))))
  )

(defn choose-lexical-item
  "Returns random lexical item having the given feature set."
  [feature-set]
  (choose-randomly (lexicon feature-set)))
