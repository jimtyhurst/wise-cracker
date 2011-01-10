(ns wise-cracker.core
  (:use [wise-cracker.lexicon] :reload)
  (:use [wise-cracker.grammar] :reload)
  (:use [wise-cracker.format] :reload))

(defn expand-lexical-item
  "Returns lexical content of feature-set."
  [feature-set accumulator]
  (conj accumulator (choose-lexical-item feature-set)))

(defn expand-to-strings
  "Expands the given feature-set(s) until reaching leaf nodes."
  ([feature-set]
     (expand-to-strings feature-set ()))
  ([feature-set word-list]
     (cond
      (nil? feature-set) word-list
      (list? feature-set) (cond (empty? feature-set) word-list
        :else (expand-to-strings (rest feature-set) (expand-to-strings (first feature-set) word-list)))
      (is-phrasal? feature-set) (expand-to-strings (choose-rule feature-set) word-list)
      (is-lexical? feature-set) (expand-lexical-item feature-set word-list)
      :else ("Usage: (expand-to-strings feature-set)"))))

(defn speak
  "Returns a list of words (Strings) as a generated Sentence."
  []
  (reverse (expand-to-strings {:category :s})))

(defstruct node :feature-set :children :lexical-content)

(defn build-node
  "Returns a node holding the given data."
  [feature-set children lexical-content]
  (struct-map node :feature-set feature-set, :children children, :lexical-content lexical-content))

(defn expand-lexical-item-to-node
  "Returns node constructed for the given feature-set."
  [feature-set]
  (build-node feature-set () (choose-lexical-item feature-set)))

(defn expand-to-nodes
  "Expands the given feature-set(s) until reaching leaf nodes, accumulating a tree along the way."
  ([feature-set]
     (cond
      (nil? feature-set) nil
      (list? feature-set) (map expand-to-nodes feature-set)
      (is-phrasal? feature-set) (build-node feature-set (map expand-to-nodes (choose-rule feature-set)) nil)
      (is-lexical? feature-set) (build-node feature-set () (choose-lexical-item feature-set))
      :else ("Usage: (expand-to-nodes feature-set)"))))

(defn build-tree
  "Expands the given feature-set(s), building a tree representation."
  ([]
     (build-tree {:category :s}))
  ([feature-set]
     (expand-to-nodes feature-set)))

(defn speak-structure
  "Returns a string representation of generated phrase structure."
  []
  (format-nodes (build-tree {:category :s})))

