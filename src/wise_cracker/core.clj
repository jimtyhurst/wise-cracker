(ns wise-cracker.core
  (:use [wise-cracker.lexicon] :reload)
  (:use [wise-cracker.grammar] :reload))

(defn expand-lexical-item
  "Returns lexical content of feature-set."
  [feature-set accumulator]
  (conj accumulator (choose-lexical-item feature-set)))

(defn expand
  "Expands the given feature-set(s) until reaching leaf nodes."
  ([feature-set]
     (expand feature-set ()))
  ([feature-set word-list]
     (cond
      (nil? feature-set) word-list
      (list? feature-set) (cond (empty? feature-set) word-list
        :else (expand (rest feature-set) (expand (first feature-set) word-list)))
      (is-phrasal? feature-set) (expand (choose-rule feature-set) word-list)
      (is-lexical? feature-set) (expand-lexical-item feature-set word-list)
      :else ("Usage: (expand feature-set)"))))

(defn build-tree
  "Expands the given feature-set(s), building a tree representation."
  ([feature-set]
     (build-tree feature-set ()))
  ([feature-set word-list]
     ;FIXME
     word-list))

(defn speak
  "Returns a list of words (Strings) as a generated Sentence."
  []
  (reverse (expand #{:s})))

