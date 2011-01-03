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

(deftest get-random-number-test-single-value
  "Expects value [0,upper-limit)"
  (let [upper-limit 1
        r (get-random-number upper-limit)]
    (is (= r 0))))

(deftest get-random-number-test
  "Expects value [0,upper-limit)"
  (let [upper-limit 3
        r (get-random-number upper-limit)]
    ;FIXME - Need to call many times, but was not able to put 'is'
    ;within 'loop'
    (is (and (> r -1) (< r upper-limit)))))

