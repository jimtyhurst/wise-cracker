(ns wise-cracker.lexicon)

(def lexicon {
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

(defn choose-randomly
  "Returns random selection from the given sequence."
  [items]
  (if (nil? items)
    nil
    (first items)) ;FIXME
  )

(defn choose-lexical-item
  "Returns random lexical item having the given feature set."
  [feature-set]
  (choose-randomly (lexicon feature-set)))
