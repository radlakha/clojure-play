(defproject reverse-string "0.1.0-SNAPSHOT"
  :description "reverse-string exercise."
  :url "https://github.com/exercism/clojure/tree/main/exercises/practice/reverse-string"
  :dependencies [[org.clojure/clojure "1.11.1"]]
  :test-selectors {;; Selects all tests with the :task metadata key matching the given value.
                  :task (fn [m task]
                          (when-let [task-value (get m :task)]
                            (= task task-value)))})  
