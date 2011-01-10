(ns wise-cracker.test.format
  (:use [wise-cracker.format] :reload)
  (:use [clojure.test]))

(deftest format-nil
  "Expects empty string"
  (is (= (format-nodes nil) "")))

(deftest format-empty-list
  "Expects empty string"
  (is (= (format-nodes ()) "")))

(deftest format-string-test
  "Expects the given string"
  (let [expected-string "anything"]
    (is (= (format-nodes expected-string) expected-string))))

(deftest format-list-test
  "Expects concatenation of strings in the given list."
  (is (= (format-nodes '("Alan" "Bob")) "Alan Bob ")))

(deftest format-node-test
  "Expects category structure."
  (let [expected-word "tall"
        actual-node {:feature-set {:category :adjective}, :children (), :lexical-content expected-word}]
    (is (= (format-nodes actual-node) "[:adjective tall]"))))

(deftest format-phrasal-node-test
  "Expects hierarchical phrase structure."
  (let [actual-det-node {:feature-set {:category :determiner, :number :singular}, :children (), :lexical-content "a"}
        actual-noun-node {:feature-set {:category :noun, :number :singular}, :children (), :lexical-content "snake"}
        actual-np-node {:feature-set {:category :np, :number :singular}, :children (list actual-det-node actual-noun-node), :lexical-content nil}]
    (is (= (format-nodes actual-np-node) "[:np [:determiner a] [:noun snake] ]"))
    (is (= (format-nodes {:feature-set {:category :np, :number :singular}, :children '({:feature-set {:category :determiner, :number :singular}, :children (), :lexical-content "every"} {:feature-set {:category :noun, :number :singular}, :children (),:lexical-content "person"}), :lexical-content nil}) "[:np [:determiner every] [:noun person] ]"))))

