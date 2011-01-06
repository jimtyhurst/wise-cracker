(ns wise-cracker.lexicon
  (:use [wise-cracker.random] :reload))

(def lexicon {{:category :adjective} '("blue" "distinguished" "good-looking" "mean" "peaceful" "pure" "short" "small" "tainted" "tall")
              {:category :adverb} '("calmly" "often" "peacefully" "quietly" "serenely" "slowly" "softly")
              {:category :determiner, :number :singular} '("a" "every")
              {:category :determiner, :number :plural} '("most" "some")
              {:category :determiner, :number :mass} '("little" "much")
              {:category :noun, :number :singular} '("man" "ocean" "person" "river" "road" "star" "stream" "tree" "woman")
              {:category :noun, :number :plural} '("cultural norms" "laws" "men" "oceans" "people" "rivers" "roads" "rules" "stars" "streams" "trees" "women")
              {:category :noun, :number :mass} '("distrust" "ecstasy" "enlightenment" "faithfulness" "goodness" "joy" "kindness" "love" "lovingkindness" "patience" "peace" "serenity" "trust" "water")
              {:category :verb, :transitivity :intransitive, :number :singular} '( "explodes" "languishes" "laughs" "listens" "rocks" "shivers")
              {:category :verb, :transitivity :intransitive, :number :plural} '( "explode" "languish" "laugh" "listen" "rock" "shiver")
              {:category :verb, :transitivity :transitive, :number :singular} '("contemplates" "follows" "listens to" "longs for" "loves" "meanders to" "seeks" "touches" "waits for")
              {:category :verb, :transitivity :transitive, :number :plural} '("contemplate" "follow" "listen to" "long for" "love" "meander to" "seek" "touch" "wait for")
              })

(defn choose-lexical-item
  "Returns random lexical item having the given feature set."
  [feature-set]
  (choose-randomly (lexicon feature-set)))

(defn is-lexical?
  "Returns true iff feature-set is lexical, i.e. represents a lexical category."
  [feature-set]
  (not (nil? (lexicon feature-set))))

