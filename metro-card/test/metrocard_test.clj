(ns metrocard-test
  (:require [clojure.test :refer :all]
            [metrocard :refer :all]))

;; unit test to test peak hour calculation
(deftest test-peak-hour
  (is (= (peak-hour? "mon" "09:00") true))
  (is (= (peak-hour? "mon" "11:00") false))
  (is (= (peak-hour? "sat" "10:00") true))
  (is (= (peak-hour? "sun" "19:00") true))
  (is (= (peak-hour? "sun" "23:00") false)))


;; unit test to calculate fares
(deftest test-calc-fare
  (is (= (calc-fare "mon" "10:20" :zone2 :zone1) 35))
  (is (= (calc-fare "mon" "10:45" :zone1 :zone1) 25))
  (is (= (calc-fare "mon" "16:15" :zone1 :zone1) 25))
  (is (= (calc-fare "mon" "18:15" :zone1 :zone1) 30))
  (is (= (calc-fare "mon" "09:00" :zone1 :zone1) 30))
  (is (= (calc-fare "mon" "11:00" :zone1 :zone1) 25))
  (is (= (calc-fare "sat" "10:00" :zone2 :zone2) 25))
  (is (= (calc-fare "sun" "19:00" :zone2 :zone2) 25))
  (is (= (calc-fare "mon" "09:00" :zone1 :zone2) 35))
  (is (= (calc-fare "mon" "11:00" :zone1 :zone2) 30)))