(ns db-lift.core)

(defn execute [input]
  (loop [in input
         bindings {}]
    (let [[pending bindings]
             (reduce #(if (can-execute? %2 (second %1))
                        [(first %1) (merge (second %1) (execute-load %2 (second %1)))]
                        (update %1 0 conj %2)) [[] bindings] in)]
      (recur (rest input)))))
