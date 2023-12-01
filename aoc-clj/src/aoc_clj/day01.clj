(ns aoc-clj.day01
  (:require [aoc-clj.utils :as utils]
            [clojure.string :as str]))



(def input (utils/read-input 1))


(defn remove-chars [s]
  (str/replace s #"\D" ""))

(defn remove-in-between-digits [s]
  (str (first s) (last s)))


(defn part-1 [input]
  (->> input
       (str/split-lines)
       (map remove-chars)
       (map remove-in-between-digits)
       (map utils/to-int)
       (apply +)))


(def words-number {"one" "o1e"
                   "two" "t2o"
                   "three" "t3e"
                   "four" "f4r"
                   "five" "f5e"
                   "six" "s6x"
                   "seven" "s7n"
                   "eight" "e8t"
                   "nine" "n9e"})


(defn replace-word [word entry]
  (str/replace word (first entry) (last entry)))

(defn replace-all-words [s]
  (loop [entries  words-number
         res s]
    (if (empty? entries)
      res
      (recur (rest entries)
             (replace-word res (first entries))))))

(defn part-2 [input]

  (->> input
       (str/split-lines)
       (map replace-all-words)
       (map remove-chars)
       (map remove-in-between-digits)
       (map utils/to-int)
       (apply +)))

(part-2  input)