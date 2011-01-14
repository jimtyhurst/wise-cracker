(ns wise-cracker.format)

(def open-bracket "[")

(def close-bracket "]")

(defn format-nodes
  "Returns string representation of tree structure."
  ([displayable]
     (cond (nil? displayable) ""
           (string? displayable) displayable
           (map? displayable) (str open-bracket ((displayable :feature-set) :category) " " (format-nodes (displayable :lexical-content)) (format-nodes (displayable :children)) close-bracket)
           (seq? displayable) (cond (empty? displayable) ""
                        :else (str (format-nodes (first displayable)) " " (format-nodes (rest displayable)))))))
