(ns wise-cracker.test.grammar
  (:use [wise-cracker.grammar] :reload)
  (:use [clojure.test]))

(deftest choose-rule-with-invalid-feature-set
  "Expects nil when selecting for an invalid feature set"
  (is (nil? (choose-rule {:non-existent-feature :non-existent-value}))))

(deftest choose-rule-with-valid-feature-set
  "Expects non-nil result when selecting for valid feature set"
  (is (not (nil? (choose-rule {:category :s, :number :singular})))))

(deftest choose-rule-with-only-one-possible-result
  "Expects sequence of feature sets NP - VP"
  (is (= (choose-rule {:category :s, :number :plural}) '({:category :np, :number :plural} {:category :vp, :number :plural}))))

(deftest get-rules-test-with-only-one-possible-result
  (is (= (syntax-rules {:category :s, :number :plural}) '(({:category :np, :number :plural} {:category :vp, :number :plural})))))

(deftest get-rules-test-size
  (is (= (count (syntax-rules {:category :s, :number :singular})) 2)))

