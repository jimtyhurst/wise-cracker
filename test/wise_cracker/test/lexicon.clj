(ns wise-cracker.test.lexicon
  (:use [wise-cracker.lexicon] :reload)
  (:use [clojure.test]))

(deftest choose-lexical-item-with-invalid-feature-set
  "Expects nil when selecting for an invalid feature set"
  (is (nil? (choose-lexical-item {:non-existent-feature :non-existent-value}))))

(deftest is-lexical-test
  "Expects true only for complete match of feature set as key in lexicon."
  (is (is-lexical? {:category :noun, :number :singular}))
  (is (not (is-lexical? {:category :non-existent-category}))))

(deftest choose-lexical-item-test-membership
  "Expects that selected item is from lexical items having the given feature set."
  (let [feature-set {:category :verb, :transitivity :transitive, :number :singular}
        is-selected-item? (fn [item] (partial = (choose-lexical-item feature-set)))]
    (is (not (nil? (some is-selected-item? (lexicon feature-set)))))))

