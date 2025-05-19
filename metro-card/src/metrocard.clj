;; We will calculate fare for metro journey given
;; the start zone and end zone, week day of travel and time of start of travel

(ns metrocard
  (:require [clojure.test :refer :all]))


;; Fares are calculated as below
;; Within Zone 1
;;   Peak Hours 30, Off-Peak Hours 25
;; Within Zone 2
;;   Peak Hours 25, Off-Peak Hours 20
;; Between Zone 1 and Zone 2
;;   Peak Hours 35, Off-Peak Hours 30

;; def a datastructure to store the fares for easy lookup
(def fares
  {:zone1 {:peak 30 :off-peak 25}
   :zone2 {:peak 25 :off-peak 20}
   :between {:peak 35 :off-peak 30}})


;; implement a function to find out if the journey is classified
;; as peak or off-peak based on following info.
;; The Day of the week is passed as strings like mon, tue, wed and so on
;; Time of journey start is passed in string as HH:MM format
;; Peak hour is defined as 
;; Monday to Friday Time of start of journey between: 
;;   07:00 - 10:30, 17:00 - 20:00
;; Saturday to Sunday Time of start of journey between: 
;;   09:00 - 11:00, 18:00 - 22:00
(defn peak-hour? [DayOfTheWeek TimeOfJourney]
  (let [day-of-week (clojure.string/lower-case DayOfTheWeek)
        time-of-journey (clojure.string/split TimeOfJourney #":")
        hour (Integer/parseInt (first time-of-journey))
        minute (Integer/parseInt (second time-of-journey))]
    (cond
      (#{"mon" "tue" "wed" "thu" "fri"} day-of-week)
      (or (and (>= hour 7) (< hour 10))
          (and (= hour 10) (< minute 30))
          (and (>= hour 17) (< hour 20)))
      (#{"sat" "sun"} day-of-week)
      (or (and (>= hour 9) (< hour 11))
          (and (> hour 18) (< hour 22))))))

;; (defn calc-fare [DayOfTheWeek TimeOfJourney ZoneFrom ZoneTo]
;;   (let [fare (if (= ZoneFrom ZoneTo)
;;                (get-in fares [ZoneFrom (if (peak-hour? DayOfTheWeek TimeOfJourney) :peak :off-peak)])
;;                (get-in fares [:between (if (peak-hour? DayOfTheWeek TimeOfJourney) :peak :off-peak)]))]
;;     fare))


;; Refactor below code to use thread-first macro
(defn calc-fare [DayOfTheWeek TimeOfJourney ZoneFrom ZoneTo]
  (let [fare (if (= ZoneFrom ZoneTo)
               (get-in fares [ZoneFrom (if (peak-hour? DayOfTheWeek TimeOfJourney) :peak :off-peak)])
               (get-in fares [:between (if (peak-hour? DayOfTheWeek TimeOfJourney) :peak :off-peak)]))]
    fare))



