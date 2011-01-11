(ns wise-cracker.format)

(def open-bracket "[")

(def close-bracket "]")

(defn format-nodes
  "Returns string representation of tree structure."
  ([displayable]
     (cond (nil? displayable) ""
           (string? displayable) displayable
           ;FIXME - fails for node with children, although see the
           ;unit tests for format-nodes
           (map? displayable) (str open-bracket ((displayable :feature-set) :category) " " (format-nodes (displayable :lexical-content)) (format-nodes (displayable :children)) close-bracket)
           (list? displayable) (cond (empty? displayable) ""
                        :else (str (format-nodes (first displayable)) " " (format-nodes (rest displayable)))))))
