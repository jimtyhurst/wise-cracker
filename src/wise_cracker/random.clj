(ns wise-cracker.random)

(def seed (.getTime (new java.util.Date)))

(def random-number-generator (new java.util.Random seed))

(defn get-random-number
  "Returns a pseudorandom, uniformly distributed integer value between 0 (inclusive) and the specified value (exclusive)."
  [upper-limit]
  (. random-number-generator nextInt upper-limit))

(defn choose-randomly
  "Returns random selection from the given sequence."
  [items]
  (if (nil? items)
    nil
    (nth items (get-random-number (count items)))))

