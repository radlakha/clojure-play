(ns reverse-string
  (:require [clojure.string :as str]))

(defn reverse-string0
  "Reverse using buil-in function"
  [s]
  (str/reverse s))

(defn reverse-string1
  "Using reduce"
  [s]
  ;; reduce takes two or three arguments and returns a single value. A
  ;; function
  ;;   that takes an accumulator and the current element of the collection
  ;;   An optional initial value for the accumulator A collection to reduce
  ;;   Here is an example using reduce with all three arguments: It takes a
  ;;   function
  ;; that adds only odd values from a collection to an accumulator,
  ;; starting with an initial value of 100
  (comment
    (reduce (fn [acc x] (if (odd? x) (+ acc x) acc)) 100 [1 2 3 4 5]))
  ;; Here we use reduce to reverse a string by taking an accumulator
  (apply str (reduce conj () s)))

(defn reverse-string2
  "Using loop and recur" 
  [s]
  (loop [remaining s
         result ""]
    (if (empty? remaining)
      result
      (recur (rest remaining) 
             (str (first remaining) result)))))

(defn reverse-string
  "Reverse a string using all the implementations"
  [s]
  (let [r0 (reverse-string0 s)
        r1 (reverse-string1 s)
        r2 (reverse-string2 s)]
    ;; Return r0 if r0, r1, and r2 are the same else return nil
    (if (and (= r0 r1) (= r0 r2))
      r0
      nil)))