(ns wise-cracker.grammar
  (:use [wise-cracker.random] :reload))

;Assumes context-free phrase structure rules with single symbol on
;left-hand side.
;Key is phrase symbol as left-hand-side, value is set of
;right-hand-sides, where each RHS is a sequence of feature sets, where
;each feature set represents a category.
(def syntax-rules {#{:s} '((#{:s :singular})
                           (#{:s :plural}))
                   #{:s :singular} '((#{:np :singular} #{:vp :singular})
                                     (#{:np :mass} #{:vp :singular}))
                   #{:s :plural} '((#{:np :plural} #{:vp :plural}))
                   #{:np} '((#{:np :singular})
                            (#{:np :plural})
                            (#{:np :mass}))
                   #{:np :singular} '((#{:determiner :singular} #{:noun :singular})
                                      (#{:determiner :singular} #{:adjective} #{:noun :singular}))
                   #{:np :plural} '((#{:noun :plural})
                                    (#{:adjective} #{:noun :plural})
                                    (#{:determiner :plural} #{:noun :plural})
                                    (#{:determiner :plural} #{:adjective} #{:noun :plural}))
                   #{:np :mass} '((#{:noun :mass})
                                  (#{:adjective} #{:noun :mass}))
                   #{:vp :singular} '((#{:verb :intransitive :singular})
                                      (#{:verb :intransitive :singular} #{:adverb})
                                      (#{:verb :transitive :singular} #{:np})
                                      (#{:verb :transitive :singular} #{:np} #{:adverb}))
                   #{:vp :plural} '((#{:verb :intransitive :plural})
                                      (#{:verb :intransitive :plural} #{:adverb})
                                      (#{:verb :transitive :plural} #{:np})
                                      (#{:verb :transitive :plural} #{:np} #{:adverb}))
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

