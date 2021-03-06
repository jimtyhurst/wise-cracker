(ns wise-cracker.test.core
  (:use [wise-cracker.core] :reload)
  (:use [clojure.test]))

(deftest expand-lexical-item-test
  (let [actual-item (expand-lexical-item {:category :adjective} ())]
    (is (not (nil? actual-item)))
    (is (= (count actual-item) 1))
    (is (list? actual-item))
    (is (string? (first actual-item)))))

(deftest expand-lexical-item-to-node-test
  "Expects certain structure of node, although precise content is random."
  (let [actual-item (expand-lexical-item-to-node {:category :adjective})]
    (is (not (nil? actual-item)))
    (is (map? actual-item))
    (is (map? (actual-item :feature-set)))
    (is (string? (actual-item :lexical-content)))
    (is (list? (actual-item :children)))
    (is (empty? (actual-item :children)))))

(deftest speak-structure-test-s-sg
  "Expects partial structure based on given feature-set, even though full structure cannot be determined completely, because it is generated randomly."
  (let [actual-structure (speak-structure {:category :s, :number :singular})]
    (is (re-find #"\[:s \[:np " actual-structure))))
