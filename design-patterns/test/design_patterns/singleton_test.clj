(ns design-patterns.singleton-test)
(require '[clojure.test :refer :all])
(require '[design-patterns.singleton :as singleton])

(deftest singleton-test
  (testing "Singleton instance creation"
    (let [instance1 (singleton/get-instance)]
      (is (not (nil? instance1)))
      (is (= (:instanceid instance1) (:instanceid (singleton/get-instance))))
      (is (= (:instance-data instance1) "This is the singleton instance")))))

(defn test-singleton []
  (future (println (singleton/get-instance)))
  (future (println (singleton/get-instance)))
  (future (println (singleton/get-instance))))

(test-singleton)