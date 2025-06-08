(ns design-patterns.strategy-test
  (:require [design-patterns.strategy :refer :all])
  (:require [clojure.test :refer :all]))

(deftest test-execute-payment
  (testing "Payment execution with different methods"
    (is (= {:type :credit-card
            :amount 100
            :card-number "1234-5678-9012-3456"
            :expiration-date "12/2025"}
           (execute-payment :credit-card 100 {:card-number "1234-5678-9012-3456" :expiration-date "12/2025"})))

    (is (= {:type :paypal
            :amount 100
            :email "example@example.com"}
           (execute-payment :paypal 100 {:email "example@example.com"})))))