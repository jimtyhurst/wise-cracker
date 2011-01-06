(ns wise-cracker.grammar
  (:use [wise-cracker.random] :reload))

;Assumes context-free phrase structure rules with single symbol on
;left-hand side.
;Key is phrase symbol as left-hand-side, value is set of
;right-hand-sides, where each RHS is a sequence of feature sets, where
;each feature set represents a category.
(def syntax-rules {{:category :s} '(({:category :s, :number :singular})
                           ({:category :s, :number :plural}))
                   {:category :s, :number :singular} '(({:category :np, :number :singular} {:category :vp, :number :singular})
                                     ({:category :np, :number :mass} {:category :vp, :number :singular}))
                   {:category :s, :number :plural} '(({:category :np, :number :plural} {:category :vp, :number :plural}))
                   {:category :np} '(({:category :np, :number :singular})
                            ({:category :np, :number :plural})
                            ({:category :np, :number :mass}))
                   {:category :np, :number :singular} '(({:category :determiner, :number :singular} {:category :noun, :number :singular})
                                      ({:category :determiner, :number :singular} {:category :adjective} {:category :noun, :number :singular}))
                   {:category :np, :number :plural} '(({:category :noun, :number :plural})
                                    ({:category :adjective} {:category :noun, :number :plural})
                                    ({:category :determiner, :number :plural} {:category :noun, :number :plural})
                                    ({:category :determiner, :number :plural} {:category :adjective} {:category :noun, :number :plural}))
                   {:category :np, :number :mass} '(({:category :noun, :number :mass})
                                  ({:category :determiner, :number :mass} {:category :noun, :number :mass}))
                   {:category :vp, :number :singular} '(({:category :verb, :transitivity :intransitive, :number :singular})
                                      ({:category :verb, :transitivity :intransitive, :number :singular} {:category :adverb})
                                      ({:category :verb, :transitivity :transitive, :number :singular} {:category :np})
                                      ({:category :verb, :transitivity :transitive, :number :singular} {:category :np} {:category :adverb}))
                   {:category :vp, :number :plural} '(({:category :verb, :transitivity :intransitive, :number :plural})
                                      ({:category :verb, :transitivity :intransitive, :number :plural} {:category :adverb})
                                      ({:category :verb, :transitivity :transitive, :number :plural} {:category :np})
                                      ({:category :verb, :transitivity :transitive, :number :plural} {:category :np} {:category :adverb}))
                   })

(def phrase-categories
  #{:s ;Sentence
    :np ;Noun Phrase
    :vp ;Verb Phrase
    })

(def lexical-categories
  #{:adjective
    :adverb
    :determiner
    :noun
    :verb
    })

(defn choose-rule
  "Returns the right-hand-side for a rule whose left-hand-side is the given feature-set."
  [feature-set]
  (choose-randomly (syntax-rules feature-set)))

(defn is-phrasal?
  "Returns true iff feature-set represents a phrase category."
  [feature-set]
  (not (nil? (syntax-rules feature-set))))

