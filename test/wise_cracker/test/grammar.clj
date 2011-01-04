(ns wise-cracker.test.grammar
  (:use [wise-cracker.grammar] :reload)
  (:use [clojure.test]))

(deftest choose-rule-with-invalid-feature-set
  "Expects nil when selecting for an invalid feature set"
  (is (nil? (choose-rule #{:non-existent-feature}))))

(deftest choose-rule-with-valid-feature-set
  "Expects nil when selecting for an invalid feature set"
  (is (not (nil? (choose-rule #{:s :singular})))))

(deftest choose-rule-with-only-one-possible-result
  "Expects sequence of feature sets NP - VP"
  (is (= (choose-rule #{:s :singular}) '(#{:np :singular} #{:vp :singular}))))

(deftest get-rules-test
  (is (= (syntax-rules #{:s :singular}) '((#{:np :singular} #{:vp :singular})))))

(deftest get-rules-test-size
  (is (= (count (syntax-rules #{:s :singular})) 1)))

