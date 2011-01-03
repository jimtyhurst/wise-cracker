(ns wise-cracker.test.lexicon
  (:use [wise-cracker.lexicon] :reload)
  (:use [clojure.test]))

(deftest choose-lexical-item-with-invalid-feature-set
  "Expects nil when selecting for an invalid feature set"
  (is (nil? (choose-lexical-item #{:non-existent-feature}))))

(deftest choose-lexical-item-test-membership
  "Expects that selected item is from lexical items having the given feature set."
  (let [feature-set #{:verb :transitive :singular}
        is-selected-item? (fn [item] (partial = (choose-lexical-item feature-set)))]
    (is (not (nil? (some is-selected-item? (lexicon feature-set)))))))

(deftest choose-lexical-item-dummy-implementation
  "Expects first item in list, because random selection not implemented yet."
  (is (= (choose-lexical-item #{:verb :transitive :singular}) "follows")))
