(ns wise-cracker.test.random
  (:use [wise-cracker.random] :reload)
  (:use [clojure.test]))

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

(deftest choose-randomly-from-one-member-list
  "Expects the single member."
  (let [expected-member '({:category :np, :number :plural} {:category :vp, :number :plural})
        items '(({:category :np, :number :plural} {:category :vp, :number :plural}))]
    (is (= (count items) 1))
    (is (= (nth items 0) expected-member))
    (is (= (choose-randomly items) expected-member))))

