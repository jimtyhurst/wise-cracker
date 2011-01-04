(ns wise-cracker.lexicon
  (:use [wise-cracker.random] :reload))

(def lexicon {#{:adjective} '("blue" "handsome" "lowly" "peaceful" "pure" "short" "small" "tainted" "tall")
              #{:adverb} '("calmly" "often" "peacefully" "quietly" "serenely" "slowly" "softly")
              #{:determiner :singular} '("a" "every")
              #{:determiner :plural} '("most" "some" "the")
              #{:noun :singular} '("man" "ocean" "person" "river" "road" "star" "stream" "tree" "woman")
              #{:noun :plural} '("men" "oceans" "people" "rivers" "roads" "stars" "streams" "trees" "women")
              #{:noun :mass} '("distrust" "love" "lovingkindness" "serenity" "trust" "water")
              #{:verb :intransitive :singular} '("listens" "rocks" "shivers" "sucks")
              #{:verb :intransitive :plural} '("listen" "rock" "shiver" "suck")
              #{:verb :transitive :singular} '("contemplates" "follows" "listens to" "longs for" "loves" "meanders to" "seeks" "touches" "waits for")
              #{:verb :transitive :plural} '("contemplate" "follow" "listen to" "long for" "love" "meander to" "seek" "touch" "wait for")
              })

(defn choose-lexical-item
  "Returns random lexical item having the given feature set."
  [feature-set]
  (choose-randomly (lexicon feature-set)))

(defn is-lexical?
  "Returns true iff feature-set is lexical, i.e. represents a lexical category."
  [feature-set]
  (not (nil? (lexicon feature-set))))

