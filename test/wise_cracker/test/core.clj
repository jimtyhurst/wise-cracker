(ns wise-cracker.test.core
  (:use [wise-cracker.core] :reload)
  (:use [clojure.test]))

(deftest expand-lexical-item-test
  (let [actual-item (expand-lexical-item #{:adjective} ())]
    (is (not (nil? actual-item)))
    (is (= (count actual-item) 1)))
  )

